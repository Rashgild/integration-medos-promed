package ru.rashgild.service;

import com.sun.jersey.api.client.ClientResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.rashgild.dao.TapDao;
import ru.rashgild.model.dto.ResponseModel;
import ru.rashgild.model.dto.TapRequest;
import ru.rashgild.model.medos.Tap;
import ru.rashgild.model.medos.Visit;
import ru.rashgild.model.medos.WorkStaff;
import ru.rashgild.model.promed.*;
import ru.rashgild.model.promed.dictionary.LpuSectionDictionary;
import ru.rashgild.model.promed.dictionary.MedstaffDictionary;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("TapService")
@Slf4j
@RequiredArgsConstructor
public class TapServiceImpl implements TapService {

    @Value("${lpu.id}")
    private String lpuId;

    private final TapDao tapDao;
    private final PersonService personService;
    private final WorkPlaceService workPlaceService;
    private final DictionaryService dictionaryService;
    private static final Long LPU_POLYCLICNIC_UNIT = 82L;

    @Override
    public ClientResponse getTapFromMedos(String date) {
        return tapDao.getTapFromMedos(date);
    }

    @Override
    public TapRequest convertingTap(Tap tap) {
        Evn evn = new Evn();
        List<EvnVisits> visits = new ArrayList<>();
        EvnXmlDiary evnXmlDiary = new EvnXmlDiary();
        int countVisits = tap.getVisitsCount();
        TapRequest tapRequest = new TapRequest();

        for (Visit visit : tap.getVisits()) {
            log.info("try process:{} ", visit.getMedosId());

            if(tap.getResultClass() == null) {
                tapRequest.setResponseModels(new ResponseModel(visit.getMedosId().toString(), "resultClass is empty"));
                break;
            }
        //TODO replace : checking
            if (!visit.hasDiag()) {
                tapRequest.setResponseModels(new ResponseModel(visit.getMedosId().toString(), "diagnosis is empty"));
                break;
            }

            //TODO replace : checking
            if (visit.getDiary().isEmpty()) {
                tapRequest.setResponseModels(new ResponseModel(visit.getMedosId().toString(), "diary is empty"));
                break;
            }

            //TODO replace : checking
            Person person = personService.getPersonFromPromed(tap.getPatient());
            if (person == null || person.getPersonId() == 0) {
                tapRequest.setResponseModels(new ResponseModel(visit.getMedosId().toString(), "person not found."));
                break;
            }

            //TODO check if previous doc is that
            //TODO replace : checking
            WorkStaff workStaff = visit.getWorkStaff();
            Person doctor = personService.getPersonBySnils(workStaff.getSnils());

            if (doctor == null || doctor.getPersonId() == 0) {
                tapRequest.setResponseModels(new ResponseModel(visit.getMedosId().toString(), "doctor not found"));
                break;
            }

            log.info("{}>>>>>>>>>>> doc id", doctor.getPersonId());
            //TODO replace : checking
            List<WorkPlaceDto> workPlaceDtos = workPlaceService.getWorkPlaceByPersonId(doctor.getPersonId());
            if (workPlaceDtos == null) {
                tapRequest.setResponseModels(new ResponseModel(visit.getMedosId().toString(), "doctor workPlace not found. bomr!"));
                break;
            }

            //TODO replace : checking
            workPlaceDtos = workPlaceDtos.stream().filter(workPlaceDto ->
                    workPlaceDto.getEndDate() == null && lpuId.equals(workPlaceDto.getLpuId().toString()))
                    .collect(Collectors.toList());

            MedstaffDictionary medstaffDictionary = null;
            for (WorkPlaceDto dto : workPlaceDtos) {

                LpuSectionDictionary lpuSection = dictionaryService.getLpuSections()
                        .stream()
                        .filter(lpuSectionDictionary -> lpuSectionDictionary.getLpuSectionId().equals(dto.getLpuSectionId()) && LPU_POLYCLICNIC_UNIT.equals(lpuSectionDictionary.getLpuUnitId()))
                        .findFirst().orElse(null);

                if (lpuSection != null) {
                    medstaffDictionary = dictionaryService.getDictionaryMedstaff(
                            lpuSection.getLpuSectionId(),
                            dto.getMedWorkerId());
                }

                if (medstaffDictionary != null) {
                    break;
                }
            }

            if (medstaffDictionary == null) {
                tapRequest.setResponseModels(new ResponseModel(visit.getMedosId().toString(), "doctor has no workfunc. bomr!"));
                break;
            }

            if (Boolean.TRUE.equals(visit.getFirstVisit())) {
                evn.setPersonId(person.getPersonId());
                evn.setMedStaffFactId(medstaffDictionary.getMedstaffId());
                evn.setLpuSectionId(medstaffDictionary.getLpuSectionId());

                evn.setResultDeseaseTypeId(visit.getDeseaseTypeId());
                evn.setServiceTypeId(visit.getServiceTypeId());
                evn.setVisitTypeId(visit.getVisitType(countVisits));
                evn.setEvnSetDt(visit.getVisitDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

                evn.setEvnPlNumCard(tap.getNumCard().toString());
                evn.setDiagLid(tap.getDiagLid());
                evn.setDiagId(tap.getDiagLid());
                evn.setTreatmentClassId(1);
                evn.setPayTypeId(61);
                evn.setMedicalCareKindId(87);
                evn.setResultClassId(tap.getResultClass());
                evn.setIsFinish("1");

                evnXmlDiary.setEvnXmlData(visit.getDiary());

                tapRequest.setEvnDiary(evnXmlDiary);
                tapRequest.setEvn(evn);

            } else {
                EvnVisit evnVisit = new EvnVisit();
                evnVisit.setMedStaffFactId(medstaffDictionary.getMedstaffId());
                evnVisit.setLpuSectionId(medstaffDictionary.getLpuSectionId());

                evnVisit.setServiceTypeId(visit.getServiceTypeId());
                evnVisit.setDiagId(visit.getDiagId());
                evnVisit.setTreatmentClassId(1);
                evnVisit.setMedicalCareKindId(87);
                evnVisit.setPayTypeId(61);
                evnVisit.setEvnSetDt(visit.getVisitDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                evnVisit.setVisitTypeId(visit.getVisitType(countVisits));
                evnVisit.setMedosId(visit.getMedosId());

                EvnVisits evnVisits = new EvnVisits();
                evnVisits.setVisit(evnVisit);

                evnXmlDiary = new EvnXmlDiary();
                evnXmlDiary.setEvnXmlData(visit.getDiary());
                evnVisits.setDiary(evnXmlDiary);

                visits.add(evnVisits);
            }
        }
        if (!visits.isEmpty()) {
            tapRequest.setVisits(visits);
        }

        return tapRequest;
    }

    @Override
    public List<ResponseModel> exportTaps(List<TapRequest> requestList) {

        List<ResponseModel> responseModels = new ArrayList<>();
        requestList.stream()
                .filter(tapRequest -> !tapRequest.isHasError() && tapRequest.getEvn() != null)
                .forEach(tapRequest -> {
                    ResponseModel responseModel = new ResponseModel();
                    log.info("try send {}", tapRequest.getEvn().getEvnPlNumCard());
                    Evn evn = tapRequest.getEvn();
                    EvnResponse.ResponseData evnResponse = this.sendTapToPromed(evn);
                    if (evnResponse.isSuccess()) {
                        EvnXmlDiary evnXmlDiary = tapRequest.getEvnDiary();
                        evnXmlDiary.setEvnId(evnResponse.getVisitId());

                        if (evn.getEvnPlNumCard() != null) {
                            tapDao.sendResultToMedos(evn.getEvnPlNumCard(), evnResponse.getBaseId());
                        }

                        if (evn.getMedosId() != null) {
                            tapDao.sendResultToMedos(evn.getMedosId().toString(), evnResponse.getVisitId());
                        }

                        //TODO check result
                        String evnXmlDiaryResponse = this.sendDiaryToPromedString(evnXmlDiary);
                        log.info(evnXmlDiaryResponse);

                        String baseId = evnResponse.getBaseId();
                        List<EvnVisits> evnVisits = new ArrayList<>();
                        if (tapRequest.getVisits() != null) {
                            evnVisits = tapRequest.getVisits();
                        }

                        for (EvnVisits visit : evnVisits) {
                            EvnVisit evnVisit = visit.getVisit();
                            evnVisit.setEvnPlBaseId(Integer.valueOf(baseId));
                            EvnVisitResponse.ResponseData visitResponse = this.sendVisitToPromed(evnVisit);
                            EvnXmlDiary diary = visit.getDiary();
                            diary.setEvnId(visitResponse.getVisitId());

                            evnXmlDiaryResponse = this.sendDiaryToPromedString(diary);
                            log.info(evnXmlDiaryResponse);
                        }
                        responseModel.setMessage("Ok");
                    } else {
                        responseModel.setError(evnResponse.getErrorMessage());
                        responseModel.setMessage("send error");
                    }
                    responseModel.setObject("visit = " + evn.getEvnPlNumCard());
                    responseModels.add(responseModel);
                });
        return responseModels;
    }

    @Override
    public EvnResponse.ResponseData sendTapToPromed(Evn evn) {
        ClientResponse response = tapDao.sendTapToPromed(evn);
        return response.getEntity(EvnResponse.ResponseData.class);
    }

    @Override
    public EvnVisitResponse.ResponseData sendVisitToPromed(EvnVisit visit) {
        ClientResponse response = tapDao.sendVisitToPromed(visit);
        return response.getEntity(EvnVisitResponse.ResponseData.class);
    }

    @Override
    public EvnXmlDiaryResponse.ResponseData sendDiaryToPromed(EvnXmlDiary diary) {
        ClientResponse response = tapDao.sendDiaryToPromed(diary);
        return response.getEntity(EvnXmlDiaryResponse.ResponseData.class);
    }

    @Override
    public String sendDiaryToPromedString(EvnXmlDiary diary) {
        ClientResponse response = tapDao.sendDiaryToPromed(diary);
        return response.getEntity(String.class);
    }
}