package ru.integration.api.policlinicCase;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ru.integration.entities.EvnPLBaseEntity;
import ru.integration.entities.medosEntities.*;
import ru.integration.util.Methods;
import ru.integration.dao.DaoImpl;
import ru.integration.entities.EvnVizitPLEntity;
import ru.rashgild.integration.entities.medosEntities.*;

import static ru.integration.api.Person.getPerson;
import static ru.integration.util.Methods.checkCode;


@Path("/Policlinic")
public class Policlinic {

    @GET
    @Path("/getPoliclinicCase")
    @Produces("application/json;charset=UTF-8")
    public static String getPoliclinicCase(@QueryParam("timeEnd") String timeEnd) {

        System.out.println(timeEnd);
        List<MedosMedcaseEntity> policlinicCases = new DaoImpl().getMedosEntityList("MedosMedcaseEntity m",
                "(m.upload is null or m.upload =false) and servicestream_id = 1 and datefinish ='" + timeEnd + "' and dtype='PolyclinicMedCase' and (noactuality is null or noactuality=false) and" +
                        "(select isnodiagnosis from MedosVocworkfunctionEntity " +
                        "where id =(select workfunction_id from MedosWorkfunctionEntity where id = " +
                        "(select min(vis.workfunctionexecute_id) from MedosMedcaseEntity vis where vis.parent_id = m.id))) is null" +
                        " and (select count(id) from MedosMedcaseEntity vis where (vis.noactuality is null or vis.noactuality = false) " +
                        "and vis.visitResult_id!=11 and vis.parent_id = m.id)>0 " +
                        "and 1= all(select servicestream_id from MedosMedcaseEntity vis where parent_id=m.id)");//Все servicestream == 1

      /*  List<MedosMedcaseEntity> policlinicCases = new DaoImpl().getMedosEntityList("MedosMedcaseEntity m",
                "(m.upload is null or m.upload =false) and servicestream_id = 1 and datefinish ='"+timeEnd+"' and dtype='PolyclinicMedCase' and (noactuality is null or noactuality=false) and" +
                        "(select isnodiagnosis from MedosVocworkfunctionEntity " +
                        "where id =(select workfunction_id from MedosWorkfunctionEntity where id = " +
                        "(select min(vis.workfunctionexecute_id) from MedosMedcaseEntity vis where vis.parent_id = m.id))) is null" +
                        " and (select count(id) from MedosMedcaseEntity vis where (vis.noactuality is null or vis.noactuality = false) " +
                        "and vis.visitResult_id!=11 and vis.parent_id = m.id)>0 " +
                        "and 1= all(select servicestream_id from MedosMedcaseEntity vis where parent_id=m.id) " + //Все servicestream == 1
                        "and (select count(wf.id) from workfunction wf where wf.promedcode_workstaff is not null and wf.id =" +
                        " any(select vis.workfunctionexecute_id from medcase vis where vis.parent_id = m.id))>0"); //Все врачи синхронизировны servicestream == 1*/
       /* List<MedosMedcaseEntity> policlinicCases = new DaoImpl().getMedosEntityList("MedosMedcaseEntity m",
                " m.id = 9466965");*/
        //System.out.println(policlinicCases.get(0).getId());


        System.out.println(policlinicCases.size());
        JSONArray jtap = new JSONArray();

        for (MedosMedcaseEntity policlinicCase : policlinicCases) {
            Long firstVisitId = 0L;
            System.out.println(policlinicCase.getUpload());
            if (policlinicCase.getUpload() != null && policlinicCase.getUpload()) continue;


            JSONObject json = new JSONObject();
            json
                    .put("EvnPL_NumCard", policlinicCase.getId())
                    .put("EvnPL_IsFinish", policlinicCase.getDatefinish() != null ? "1" : "0");

           /* MedosMedcaseEntity firstVisit = (MedosMedcaseEntity) new DaoImpl().getEntityM("MedosMedcaseEntity",
                    "servicestream_id is not null and datestart =(select min(datestart) " +
                            "from MedosMedcaseEntity where (servicestream_id is not null) and parent_id  = "+policlinicCase.getId()+") and parent_id ="+policlinicCase.getId());*/
            System.out.println("Ищу firstVisit in " + policlinicCase.getId());
            MedosMedcaseEntity firstVisit = (MedosMedcaseEntity) new DaoImpl().getEntityM("MedosMedcaseEntity",
                    " servicestream_id is not null and id =(select min(id) " +
                            "from MedosMedcaseEntity where (servicestream_id is not null) and parent_id  = " + policlinicCase.getId() + " and noactuality is not true) and parent_id =" + policlinicCase.getId());

            firstVisitId = firstVisit.getId();

            if (policlinicCase.getDatefinish() != null) {

                System.out.println("Case>>>" + policlinicCase.getId());
                System.out.println("FirstVisit>>>" + firstVisitId);
                MedosVocvisitresultEntity vocvisitresult = (MedosVocvisitresultEntity) new DaoImpl().getEntityM("MedosVocvisitresultEntity", "id=" + firstVisit.getVisitResult_id());
                MedosDiagnosisEntity diag = (MedosDiagnosisEntity) new DaoImpl().getEntityM("MedosDiagnosisEntity", "medcase_id=" + firstVisit.getId());

                MedosVocidc10Entity mkb = (MedosVocidc10Entity) new DaoImpl().getEntityM("MedosVocidc10Entity", "id=" + diag.getIdc10_id());

                json
                        .put("ResultClass_id", vocvisitresult.getPromedcode1())
                        .put("ResultDeseaseType_id", vocvisitresult.getPromedcode2())
                        .put("Diag_lid", mkb.getPromedcode());
            }


            /***первый визит*/
            List<MedosMedcaseEntity> visits = new DaoImpl().getMedosEntityList("MedosMedcaseEntity vis", "parent_id=" + policlinicCase.getId() +
                    " and servicestream_id is not null and visitresult_id != 11 and (noactuality  = false or noactuality is null)  and (select islab from MedosVocworkfunctionEntity where id =(select workfunction_id from MedosWorkfunctionEntity where id=vis.workfunctionexecute_id))is null");

            //System.out.println("<<SQL>>>");
            JSONArray jvisit = new JSONArray();
            for (MedosMedcaseEntity visit : visits) {
                JSONObject firstVis = new JSONObject();


                System.out.println("VisitId>>>" + visit.getId());

                MedosWorkfunctionEntity wf = (MedosWorkfunctionEntity) new DaoImpl().getEntityM("MedosWorkfunctionEntity", "id=" + visit.getWorkfunctionexecute_id());
                MedosVocreasonEntity vr = (MedosVocreasonEntity) new DaoImpl().getEntityM("MedosVocreasonEntity", "id=" + visit.getVisitreason_id());
                MedosVocworkplacetypeEntity vwr = (MedosVocworkplacetypeEntity) new DaoImpl().getEntityM("MedosVocworkplacetypeEntity", "id=" + visit.getWorkplacetype_id());
                MedosVocservicestreamEntity vss = (MedosVocservicestreamEntity) new DaoImpl().getEntityM("MedosVocservicestreamEntity", "id=" + visit.getServicestream_id());
                MedosDiagnosisEntity diag = (MedosDiagnosisEntity) new DaoImpl().getEntityM("MedosDiagnosisEntity", "medcase_id=" + visit.getId());
                MedosVocidc10Entity mkb = (MedosVocidc10Entity) new DaoImpl().getEntityM("MedosVocidc10Entity", "id=" + diag.getIdc10_id());
                MedosVochospitalizationEntity vh = (MedosVochospitalizationEntity) new DaoImpl().getEntityM("MedosVochospitalizationEntity", "id=" + visit.getHospitalization_id());

                try {

                    firstVis.put("Evn_setDT", isNull(visit.getDatestart() + " " + visit.getTimeexecute(), "Evn_setDT") ? "" : visit.getDatestart() + " " + visit.getTimeexecute())
                            .put("LpuSection_id", isNull(wf.getPromedcodeLpusection(), "LpuSection_id") ? null : wf.getPromedcodeLpusection())
                            .put("MedStaffFact_id", isNull(wf.getPromedcodeWorkstaff(), "MedStaffFact_id") ? null : wf.getPromedcodeWorkstaff())
                            .put("TreatmentClass_id", isNull(vr.getPromedcode(), "TreatmentClass_id") ? null : vr.getPromedcode())
                            .put("ServiceType_id", isNull(vwr.getPromedcode(), "ServiceType_id") ? null : vwr.getPromedcode())
                            .put("VizitType_id", isNull(vr.getPromedcode2(), "VizitType_id") ? null : vr.getPromedcode2())
                            .put("PayType_id", isNull(vss.getPromedcode(), "PayType_id") ? null : vss.getPromedcode());


                    System.out.println(">>>" + visit.getId());
                    firstVis.put("Mes_id", isNull(wf.getPromedcodeLpusection(), "Mes_id") ? null : wf.getPromedcodeLpusection());
                    firstVis.put("Diag_id", isNull(mkb.getPromedcode(), "Diag_id") ? null : mkb.getPromedcode());
                    firstVis.put("DeseaseType_id", isNull(vh.getPromedcode(), "DeseaseType_id") ? null : vh.getPromedcode());
                    firstVis.put("MedicalCareKind_id", "87");
                    firstVis.put("MedosId", visit.getId());
                    firstVis.put("firstVisit", visit.getId().equals(firstVisitId) ? "true" : "false");
                    System.out.println("<<<" + visit.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //TODO услуга, нужно но не надо
                //MedosMedcaseEntity serv = (MedosMedcaseEntity) new DaoImpl().getEntityM("MedosMedcaseEntity", "parent_id=" + policlinicCase.getId() + " and dtype='ServiceMedCase'");

                /*if (serv != null && serv.getId() != null && serv.getId() != 0) {
                    MedosMedserviceEntity ms = (MedosMedserviceEntity) new DaoImpl().getEntityM("MedosMedserviceEntity", "id=" + serv.getMedservice_id());
                    firstVis.put("UslugaComplex_uid", ms.getPromedCode());
                }*/

                jvisit.put(firstVis);
            }

            MedosPatientEntity patientEntity = (MedosPatientEntity) new DaoImpl().getEntityM("MedosPatientEntity", "id=" + policlinicCase.getPatient_id());

            //System.out.println("<<MakePatient>>");
            JSONObject patient = new JSONObject();
            patient.put("lastname", isNull(patientEntity.getLastname(), "lastname") ? null : patientEntity.getLastname())
                    .put("firstname", isNull(patientEntity.getFirstname(), "firstname") ? null : patientEntity.getFirstname())
                    .put("middlename", isNull(patientEntity.getMiddlename(), "middlename") ? null : patientEntity.getMiddlename())
                    .put("birthday", isNull(String.valueOf(patientEntity.getBirthday()), "birthday") ? null : patientEntity.getBirthday())
                    .put("snils", isNull(patientEntity.getSnils(), "snils") ? null : patientEntity.getSnils());

            //System.out.println("<<PutJson>>");
            json.put("visits", jvisit);
            json.put("patient", patient);
            //System.out.println("<<PutJsonJSON>>");
            jtap.put(json);
            //System.out.println("<<AllGood>>");
        }

        //System.out.println(new JSONObject().put("tap",jtap).toString());
        return new JSONObject().put("tap", jtap).toString();
    }

    private static Boolean isNull(String field, String name) {
        if (field == null) {
            System.out.println(">>>!" + name);
            return true;
        } else {
            return false;
        }
    }

    @GET
    @Path("/parseJSON")
    @Produces("application/json;charset=UTF-8")
    public static String parseJSON(String json) {

        JsonParser parser = new JsonParser();
        JsonObject jparse = parser.parse(json).getAsJsonObject();
        JsonArray array = jparse.getAsJsonArray("tap");

        for (JsonElement user : array) {

            JsonObject userObject = user.getAsJsonObject();
            JsonElement projectElement = userObject.get("patient");
            jparse = parser.parse(projectElement.toString()).getAsJsonObject();
            Integer personId = parsePesoinIdJSON(getPerson(jparse.get("lastname").getAsString()
                    , jparse.get("firstname").getAsString()
                    , ""
                    , jparse.get("birthday").getAsString()
                    , jparse.get("snils").getAsString()));
            //System.out.println("try1)personId>>"+personId);

            if (personId == 0) {
                personId = parsePesoinIdJSON(getPerson(jparse.get("lastname").getAsString()
                        , jparse.get("firstname").getAsString()
                        , jparse.get("middlename").getAsString()
                        , jparse.get("birthday").getAsString()
                        , ""));
                //System.out.println("try2)personId>>"+personId);
            }


            EvnPLBaseEntity evn = new EvnPLBaseEntity();
            System.out.println("NumCard>>" + userObject.get("EvnPL_NumCard").getAsString());
            evn.setEvnPl_NumCard(userObject.get("EvnPL_NumCard").getAsString());
            evn.setPromedPerson_id(String.valueOf(personId));
            evn.setResultDeseaseType_id((userObject.get("ResultDeseaseType_id").getAsString()));
            evn.setEvnPl_IsFinish((userObject.get("EvnPL_IsFinish").getAsString()));
            evn.setDiag_lid((userObject.get("Diag_lid").getAsString()));
            evn.setResultClass_id((userObject.get("ResultClass_id").getAsString()));

            JsonArray visits = userObject.getAsJsonArray("visits");
            for (JsonElement visit : visits) {
                JsonObject vis = visit.getAsJsonObject();

                if (vis.get("firstVisit").getAsString().equals("true")) {

                    evn.setEvn_setDT(vis.get("Evn_setDT").getAsString());
                    evn.setDiag_id(vis.get("Diag_id").getAsString());
                    evn.setDeseaseType_id(vis.get("DeseaseType_id").getAsString());
                    evn.setVizitType_id(vis.get("VizitType_id").getAsString());


                    if (vis.has("Mes_id")) {
                        if (vis.get("Mes_id") != null) {
                            evn.setMes_id(vis.get("Mes_id").getAsString());
                            evn.setMedStaffFact_id(vis.get("MedStaffFact_id").getAsString());
                            evn.setLpuSection_id(vis.get("LpuSection_id").getAsString());
                            evn.setError(false);
                        }
                    }

                    evn.setTreatmentClass_id(vis.get("TreatmentClass_id").getAsString());
                    evn.setMedicalCareKind_id(vis.get("MedicalCareKind_id").getAsString());
                    evn.setServiceType_id(vis.get("ServiceType_id").getAsString());
                    evn.setPayType_id(vis.get("PayType_id").getAsString());

                    new DaoImpl().save(evn);
                } else {
                    EvnVizitPLEntity vizitPL = new EvnVizitPLEntity();
                    vizitPL.setEvn_setDT(vis.get("Evn_setDT").getAsString());
                    vizitPL.setDiag_id(vis.get("Diag_id").getAsString());
                    vizitPL.setDeseaseType_id(vis.get("DeseaseType_id").getAsString());
                    vizitPL.setVizitType_id(vis.get("VizitType_id").getAsString());

                    if (vis.has("Mes_id")) {
                        if (vis.get("Mes_id") != null) {
                            vizitPL.setMes_id(vis.get("Mes_id").getAsString());
                            vizitPL.setMedStaffFact_id(vis.get("MedStaffFact_id").getAsString());
                            vizitPL.setLpuSection_id(vis.get("LpuSection_id").getAsString());
                            evn.setError(false);
                        }
                    }

                    vizitPL.setTreatmentClass_id(vis.get("TreatmentClass_id").getAsString());
                    vizitPL.setMedicalCareKind_id(vis.get("MedicalCareKind_id").getAsString());
                    vizitPL.setServiceType_id(vis.get("ServiceType_id").getAsString());
                    vizitPL.setPayType_id(vis.get("PayType_id").getAsString());
                    vizitPL.setEvnParent_id(evn.getEvnPl_NumCard());
                    vizitPL.setMedos_id(vis.get("MedosId").getAsString());
                    new DaoImpl().save(vizitPL);
                }
            }
        }
        return "";
    }

    @GET
    @Path("/taps")
    @Produces("application/json;charset=UTF-8")
    public static String taps(@QueryParam("timeEnd") String timeEnd) {

        System.out.println(">>GetJson");
        String json = getPoliclinicCase(timeEnd);
        System.out.println(json);
        System.out.println(">>ParseJson");
        parseJSON(json);
        return "";
    }

    private static String formationEvnJson(EvnPLBaseEntity evn) {

        JSONObject tap = new JSONObject();
        try {

            tap.put("Person_id", evn.getPromedPerson_id().toString())
                    .put("EvnPL_NumCard", evn.getEvnPl_NumCard())
                    .put("EvnPL_IsFinish", evn.getEvnPl_IsFinish())
                    .put("Diag_lid", evn.getDiag_lid())
                    .put("ResultDeseaseType_id", evn.getDeseaseType_id())
                    .put("ResultClass_id", evn.getResultClass_id())

                    .put("Evn_setDT", evn.getEvn_setDT())
                    .put("LpuSection_id", evn.getLpuSection_id())
                    .put("MedStaffFact_id", evn.getMedStaffFact_id())
                    .put("ServiceType_id", evn.getServiceType_id())
                    .put("TreatmentClass_id", evn.getTreatmentClass_id())
                    .put("VizitType_id", evn.getVizitType_id())
                    .put("PayType_id", evn.getPayType_id())
                    .put("Diag_id", evn.getDiag_id())
                    .put("MedicalCareKind_id", evn.getMedicalCareKind_id());
            return tap.toString();


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static Integer parsePesoinIdJSON(String json) {

        JsonParser parser = new JsonParser();
        JsonObject jparse = parser.parse(json).getAsJsonObject();
        Integer personId = 0;
        if (Methods.checkCode(jparse)) {
            JsonArray data = jparse.getAsJsonArray("data");
            for (JsonElement medspecs : data) {
                JsonObject sect = medspecs.getAsJsonObject();
                personId = Methods.checkJsonObjGetInteger(sect, "Person_id");
                //System.out.println(personId);
            }
            return personId;
        } else return 0;
    }

    @GET
    @Path("/uploadDate")
    @Produces("application/json;charset=UTF-8")
    public static String uploadDate(@QueryParam("date") String date, @QueryParam("countS") String countS) {

        List<EvnPLBaseEntity> evns = (List<EvnPLBaseEntity>) new DaoImpl<>()
                .getAllE("EvnPLBaseEntity", " (isExport=false or isExport is null) and error = false and promedperson_id!='0' " +
                        "and evn_setdt like ('%" + date + "%')");


        if (countS == null || countS.equals("")) {
            countS = String.valueOf(evns.size());
        }

        int countEvns = evns.size();
        int proc = 1;

        int j = 0, count = Integer.parseInt(countS);
        for (EvnPLBaseEntity evn : evns) {

            String json = formationEvnJson(evn);
            System.out.println("req>>>" + json);
            String ret = Methods.cretePostRequest("api/EvnPLBase", json);
            System.out.println("resp>>>" + ret);
            JsonParser parser = new JsonParser();
            JsonObject jparse = parser.parse(ret).getAsJsonObject();

            String errorcode = "", error_msg = "";
            if (jparse.has("error_code") && jparse.get("error_code") != null) {
                errorcode = jparse.get("error_code").getAsString();
            }
            if (jparse.has("error_msg") && jparse.get("error_msg") != null) {
                error_msg = jparse.get("error_msg").getAsString();
            }
            if (Methods.getErrorCode(jparse).equals("0")) {
                JsonArray data = jparse.getAsJsonArray("data");
                for (JsonElement medspecs : data) {

                    JsonObject sect = medspecs.getAsJsonObject();
                    HashMap<String, String> params = new HashMap<>();
                    params.put("isExport", "'true'");
                    evn.setEvnPLBase_id(Methods.checkJsonObjGetInteger(sect, "EvnPLBase_id"));
                    params.put("evnplbase_id", Methods.checkJsonObjGetInteger(sect, "EvnPLBase_id").toString());
                    params.put("evnvizitpl_id", Methods.checkJsonObjGetInteger(sect, "EvnVizitPL_id").toString());

                    new DaoImpl().updateRecord("EvnPLBaseEntity", params, " where id=" + evn.getId());
                }

                List<EvnVizitPLEntity> visits = (List<EvnVizitPLEntity>) new DaoImpl<>()
                        .getAllE("EvnVizitPLEntity", " (isExport=false or isExport is null) and evnparent_id='" + evn.getEvnPl_NumCard() + "'");
                for (EvnVizitPLEntity visit : visits) {

                    JSONObject vis = new JSONObject();
                    vis.put("Evn_setDT", visit.getEvn_setDT())
                            .put("LpuSection_id", visit.getLpuSection_id())
                            .put("EvnPLBase_id", evn.getEvnPLBase_id())
                            .put("MedStaffFact_id", visit.getMedStaffFact_id())
                            .put("ServiceType_id", visit.getServiceType_id())
                            .put("TreatmentClass_id", visit.getTreatmentClass_id())
                            .put("VizitType_id", visit.getVizitType_id())
                            .put("PayType_id", visit.getPayType_id())
                            .put("Diag_id", visit.getDiag_id())
                            .put("MedicalCareKind_id", visit.getMedicalCareKind_id());

                    System.out.println("Visit>>>" + vis);
                    ret = Methods.cretePostRequest("api/EvnVizitPL", vis.toString());
                    System.out.println("Response>>>" + ret);
                    parser = new JsonParser();
                    jparse = parser.parse(ret).getAsJsonObject();
                    if (Methods.checkCode(jparse)) {
                        data = jparse.getAsJsonArray("data");
                        for (JsonElement medspecs : data) {

                            JsonObject sect = medspecs.getAsJsonObject();
                            HashMap<String, String> params = new HashMap<>();
                            params.put("isExport", "'true'");
                            params.put("evnvizitpl_id", Methods.checkJsonObjGetInteger(sect, "EvnVizitPL_id").toString());

                            new DaoImpl().updateRecord("EvnVizitPLEntity", params, " where id=" + visit.getId());
                        }
                    }
                }
            } else {

                HashMap<String, String> params = new HashMap<>();
                params.put("error", "'true'");
                params.put("isExport", "'false'");
                params.put("errorMsg", "'" + errorcode + "'");
                params.put("errorCode", "'" + error_msg + "'");
                new DaoImpl().updateRecord("EvnPLBaseEntity", params, " where id=" + evn.getId());
            }
            j++;
            if (j == count) break;

            System.out.println((proc * 100) / countEvns + " %");
            proc++;
        }
        //cretePostRequest()

        return "";
    }

    @GET
    @Path("/syncMedos")
    @Produces("application/json;charset=UTF-8")
    public static String syncMedos() {
        List<EvnPLBaseEntity> evns = (List<EvnPLBaseEntity>) new DaoImpl<>().getAllE("EvnPLBaseEntity", " isExport=true and issynced=false");
        int count = evns.size();
        int i = 1;
        for (EvnPLBaseEntity evn : evns) {

            HashMap<String, String> params = new HashMap<>();
            params.put("upload", "true");
            new DaoImpl().updateRecordM("MedosMedcaseEntity", params, " where id=" + evn.getEvnPl_NumCard());
            params = new HashMap<>();
            params.put("issynced", "true");
            new DaoImpl().updateRecord("EvnPLBaseEntity", params, " where id=" + evn.getId());
            System.out.println((i * 100) / count + " %");
            i++;
        }
        return "";
    }

    @GET
    @Path("/autoUploadByPeriod")
    @Produces("application/json;charset=UTF-8")
    public static String autoUploadByPeriod(@QueryParam("year") String year,
                                            @QueryParam("month") String month,
                                            @QueryParam("dateStart") String dateStart,
                                            @QueryParam("dateEnd") String dateEnd) {

        int iDateEnd = Integer.parseInt(dateEnd);
        for (int iDateStart = Integer.parseInt(dateStart); iDateStart <= iDateEnd; iDateStart++) {
            String s = "";
            if (iDateStart < 10) {
                s = "0" + iDateStart;
            } else {
                s = iDateStart + "";
            }
            String date = year + "-" + month + "-" + s;
            System.out.println("----------------------------------\n");
            System.out.println(date);
            System.out.println("----------------------------------\n");
            uploadDate(date, "");

        }
        return "AllGooD";
    }

    @GET
    @Path("/autoUpload")
    @Produces("application/json;charset=UTF-8")
    public static String syncMedos(@QueryParam("year") String year,
                                   @QueryParam("month") String month,
                                   @QueryParam("dateStart") String dateStart,
                                   @QueryParam("dateEnd") String dateEnd) {


        int iDateEnd = Integer.parseInt(dateEnd);
        for (int iDateStart = Integer.parseInt(dateStart); iDateStart <= iDateEnd; iDateStart++) {
            String s = "";
            if (iDateStart < 10) {
                s = "0" + iDateStart;
            } else {
                s = iDateStart + "";
            }

            String date = year + "-" + month + "-" + s;
            System.out.println("----------------------------------\n");
            System.out.println(date);
            System.out.println("----------------------------------\n");
            taps(date);
            uploadDate(date, "");
        }
        syncMedos();
        return "{\"status\":\"ok\"}";
    }
}
