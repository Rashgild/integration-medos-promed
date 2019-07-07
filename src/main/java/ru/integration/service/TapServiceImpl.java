package ru.integration.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.integration.dao.TapDao;
import ru.integration.model.medosEntity.Tap;
import ru.integration.model.medosEntity.Visit;
import ru.integration.model.promedEntity.Evn;
import ru.integration.model.promedEntity.EvnVisit;
import ru.integration.model.promedEntity.EvnVisits;
import ru.integration.model.promedEntity.EvnXmlDiary;
import ru.integration.model.promedEntity.Person;

@Service("TapService")
public class TapServiceImpl implements TapService {

    @Autowired
    private TapDao tapDao;

    @Autowired
    private PersonService personService;

    @Override
    public ClientResponse getTapFromMedos(String date) {
        return tapDao.getTapFromMedos(date);
    }

    private List<EvnVisits> visits = new ArrayList<>();

    @Override
    public void convertingTaps(List<Tap> taps) {
        int countNullMedstaff = 0;
        int coutnNullPatient = 0;

        for (Tap tap : taps) {
            boolean breakFlag = false;
            Evn evn = new Evn();
            EvnXmlDiary evnXmlDiary = new EvnXmlDiary();
            int countVisits = tap.getVisitsCount();

            for (Visit visit : tap.getVisits()) {

                if (!visit.checkWarnFields()) {
                    System.out.println("Medstaff or lpu nullable. Go next>>");
                    countNullMedstaff++;
                    breakFlag = true;
                    break;
                }

                if (visit.getFirstVisit()) {
                    Person person = personService.getPersonFromPromed(tap.getPatient());

                    if (person != null && person.getPersonId() != 0) {
                        evn.setPersonId(person.getPersonId());
                    } else {
                        System.out.println("Person is null. Go next>>>");
                        coutnNullPatient++;
                        breakFlag = true;
                        break;
                    }
                    evn.setMedStaffFactId(visit.getMedStaffId());
                    evn.setLpuSectionId(visit.getLpuSectionId());
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

                } else {

                    EvnVisit evnVisit = new EvnVisit();
                    evnVisit.setMedStaffFactId(visit.getMedStaffId());
                    evnVisit.setLpuSectionId(visit.getLpuSectionId());
                    evnVisit.setServiceTypeId(visit.getServiceTypeId());
                    evnVisit.setDiagId(visit.getDiagId());
                    evnVisit.setTreatmentClassId(1);
                    evnVisit.setMedicalCareKindId(87);
                    evnVisit.setPayTypeId(61);
                    evnVisit.setEvnSetDt(visit.getVisitDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    evnVisit.setVisitTypeId(visit.getVisitType(countVisits));

                    EvnVisits evnVisits = new EvnVisits();
                    evnVisits.setVisit(evnVisit);

                    evnXmlDiary = new EvnXmlDiary();
                    evnXmlDiary.setEvnXmlData(visit.getDiary());
                    evnVisits.setDiary(evnXmlDiary);

                    ObjectMapper om = new ObjectMapper();
                    try {
                        evnVisit.setMedosId(123);
                        String a = om.writeValueAsString(evnVisit);
                        System.out.println(a);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }

                    visits.add(evnVisits);
                }
            }
            if (breakFlag) {
                continue;
            }
        }

        System.out.println(countNullMedstaff);
        System.out.println(coutnNullPatient);
    }
}