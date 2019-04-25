package ru.integration.deprecated.api.vocsync;

import java.util.HashMap;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ru.integration.dao.DaoImpl;
import ru.integration.deprecated.entities.PersonEntity;
import ru.integration.deprecated.entities.medosEntities.MedosVocidc10Entity;
import ru.integration.deprecated.entities.schedule.FreeDateEntity;
import ru.integration.deprecated.entities.schedule.FreeTimeEntity;
import ru.integration.deprecated.entities.schedule.MedWorkerEntity;
import ru.integration.deprecated.vocEntity.MedStaffFactById;
import ru.integration.deprecated.vocEntity.MedosMedservice;
import ru.integration.deprecated.vocEntity.VocDiag;
import ru.integration.deprecated.vocEntity.VocLpuSection;
import ru.integration.deprecated.vocEntity.VocLpuSectionById;
import ru.integration.deprecated.vocEntity.VocLpuSectionProfile;
import ru.integration.deprecated.vocEntity.VocMedSpecOmc;
import ru.integration.deprecated.vocEntity.VocMedSpecOmcMO;
import ru.integration.deprecated.vocEntity.VocMedStaffFactList;
import ru.integration.deprecated.vocEntity.VocMedStaffFactMO;
import ru.integration.deprecated.vocEntity.VocUslugaComplex;
import ru.integration.util.GlobalVariables;



@Path("/vocOperations")
public class VocOperations {

 /*   @GET
    @Path("/medSpecOmsByMOSync")
    @Produces("application/json;charset=UTF-8")
    public static String sync() {
        HashMap<String, String> params = new HashMap<>();
        params.put("lpu_id", GlobalVariables.lpu_id);
        String json = creteGetRequest("api/MedSpecOms/MedSpecOmsByMO", params);
        new DaoImpl().saveList(new VocMedSpecOmcMO().parseJSON(json));
        return "";
    }*/

  /*  @GET
    @Path("/medSpecOmsSync")
    @Produces("application/json;charset=UTF-8")
    public static String medSpecOmsSync() {
        String json = syncVocRefbook("dbo.MedSpecOms");
        new DaoImpl().saveList(new VocMedSpecOmc().parseJSON(json));
        return json;
    }*/
/*

    @GET
    @Path("/medStaffFactByMOSync")
    @Produces("application/json;charset=UTF-8")
    public static String medStaffFactByMOSync() {
        List<VocMedSpecOmcMO> vocMedSpecOmcMOS = (List<VocMedSpecOmcMO>) new DaoImpl<>().getAllE("VocMedSpecOmcMO");
        for (VocMedSpecOmcMO v : vocMedSpecOmcMOS) {
            HashMap<String, String> params = new HashMap<>();
            params.put("lpu_id", GlobalVariables.lpu_id);
            params.put("MedSpecOms_id", v.getMedSpecOmc_id());
            String json = creteGetRequest("api/MedStaffFact/MedStaffFactByMO", params);
            new DaoImpl().saveList(new VocMedStaffFactMO().parseJSON(json, Integer.valueOf(v.getMedSpecOmc_id())));
        }
        return "jsonend";
    }

    @GET
    @Path("/lpuSectionListByMO")
    @Produces("application/json;charset=UTF-8")
    public static String LpuSectionListByMO() {
        HashMap<String, String> params = new HashMap<>();
        params.put("lpu_id", GlobalVariables.lpu_id);
        String json = creteGetRequest("api/Lpu/LpuSectionListByMO", params);

        new DaoImpl().saveList(new VocLpuSection().parseJSON(json));
        return json;
    }
*/

  /*  @GET
    @Path("/lpuSectionProfile")
    @Produces("application/json;charset=UTF-8")
    public static String lpuSectionProfile() {
        String json = syncVocRefbook("dbo.LpuSectionProfile");
        new DaoImpl().saveList(new VocLpuSectionProfile().parseJSON(json));
        return json;
    }
*/
  /*  @GET
    @Path("/medStaffFactListByMOandProfileSync")
    @Produces("application/json;charset=UTF-8")
    public static String medStaffFactListByMOandProfile() {
        List<VocLpuSectionById> vocLpuSectionProfiles = (List<VocLpuSectionById>) new DaoImpl<>().getAllE("VocLpuSectionById");
        for (VocLpuSectionById v : vocLpuSectionProfiles) {
            HashMap<String, String> params = new HashMap<>();
            params.put("lpu_id", GlobalVariables.lpu_id);
            params.put("LpuSectionProfile_id", String.valueOf(v.getLpuSectionProfile_id()));
            String json = creteGetRequest("api/medstafffact/MedStaffFactListByMOandProfile", params);
            System.out.println(json);
            new DaoImpl().saveList(new VocMedStaffFactList().parseJSON(json, String.valueOf(v.getLpuSectionProfile_id())));
        }
        return "jsonend";
    }*/

  /*  @GET
    @Path("/getFreeDate")
    @Produces("application/json;charset=UTF-8")
    public static String timeTableGrafFreeDateSync() {

        List<VocMedStaffFactMO> vocMedStaffFactMOS = (List<VocMedStaffFactMO>) new DaoImpl<>()
                .getAllE("VocMedStaffFactMO");
        String jsonend = "";
        for (VocMedStaffFactMO v : vocMedStaffFactMOS) {
            HashMap<String, String> params = new HashMap<>();
            params.put("MedStaffFact_id", String.valueOf(v.getMedStaffFact()));
            params.put("TimeTableGraf_beg", "2018-02-05");
            params.put("TimeTableGraf_end", "2018-02-11");

            String json = creteGetRequest("api/TimeTableGraf/TimeTableGrafFreeDate", params);
            System.out.println(json);
            new DaoImpl().saveList(new FreeDateEntity().parseJSON(json, v.getMedStaffFact()));
        }
        return "{\"jsonend\":\"ok\"}";
    }

    @GET
    @Path("/getFreeTime")
    @Produces("application/json;charset=UTF-8")
    public static String timeTableGrafFreeTimeSync() {

        //medSpecOmsByMOSync
        List<FreeDateEntity> timeTableGrafFreeDates = (List<FreeDateEntity>) new DaoImpl<>()
                .getAllE("FreeDateEntity", " isSync=false or isSync is null");
        String jsonend = "";
        for (FreeDateEntity v : timeTableGrafFreeDates) {
            HashMap<String, String> params = new HashMap<>();
            params.put("MedStaffFact_id", String.valueOf(v.getMedStaffFact_id()));
            params.put("TimeTableGraf_begTime", String.valueOf(v.getTimeTableGraf_begTime()));
            String json = creteGetRequest("api/TimeTableGraf/TimeTableGrafFreeTime", params);
            System.out.println(json);
            new DaoImpl().saveList(new FreeTimeEntity().parseJSON(json, v.getTimeTableGraf_begTime()));
            params = new HashMap<>();
            params.put("isSync", "true");
            new DaoImpl<>().updateRecord("FreeDateEntity", params, "where id=" + v.getId());
        }
        return "{\"jsonend\":\"ok\"}";
    }

    @GET
    @Path("/getLpuSectionById")
    @Produces("application/json;charset=UTF-8")
    public static String LpuSectionById() {

        List<VocLpuSection> vocLpuSections = (List<VocLpuSection>) new DaoImpl<>().getAllE("VocLpuSection");
        for (VocLpuSection v : vocLpuSections) {
            HashMap<String, String> params = new HashMap<>();
            params.put("LpuSection_id", String.valueOf(v.getLpuSection_id()));
            String json = creteGetRequest("api/Lpu/LpuSectionById", params);
            System.out.println(json);
            new DaoImpl().saveList(new VocLpuSectionById().parseJSON(json, v.getLpuSection_id()));
        }
        return "{\"jsonend\":\"ok\"}";
    }

    @GET
    @Path("/getMedStaffFactById")
    @Produces("application/json;charset=UTF-8")
    public static String getMedStaffFactById() {

        List<VocMedStaffFactList> medStaffFactListEntity = (List<VocMedStaffFactList>) new DaoImpl<>().getAllE("VocMedStaffFactList");

        for (VocMedStaffFactList v : medStaffFactListEntity) {
            HashMap<String, String> params = new HashMap<>();
            params.put("MedStaffFact_id", String.valueOf(v.getMedStaffFact_id()));
            String json = creteGetRequest("api/MedStaffFact/MedStaffFactById", params);
            System.out.println(json);
            new DaoImpl().saveList(new MedStaffFactById().parseJSON(json, v.getMedStaffFact_id()));
        }
        return "{\"jsonend\":\"ok\"}";
    }

    @GET
    @Path("/getPersonbyMedstaff")
    @Produces("application/json;charset=UTF-8")
    public static String getPersonbyMedstaff() {

        List<MedStaffFactById> medStaffFactListEntity = (List<MedStaffFactById>)
                new DaoImpl<>().getAllE("MedStaffFactById");

        for (MedStaffFactById v : medStaffFactListEntity) {

            HashMap<String, String> params = new HashMap<>();
            params.put("MedWorker_id", String.valueOf(v.getMedPersonal_id()));
            String json = creteGetRequest("api/MedWorkerById", params);

            MedWorkerEntity medWorkerEntity = new MedWorkerEntity().parseJSON(json, v.getMedStaffFact_id());
            new DaoImpl().save(medWorkerEntity);

            System.out.println(medWorkerEntity.getPerson_id());
            new DaoImpl().saveList(new PersonEntity().parseJSON(
                    getPersonById(medWorkerEntity.getPerson_id()), v.getMedStaffFact_id()));
        }
        return "{\"jsonend\":\"ok\"}";
    }

    @GET
    @Path("/getMedworker")
    @Produces("application/json;charset=UTF-8")
    public static String getMedworker(@QueryParam("personId") String personId) {

        HashMap<String, String> params = new HashMap<>();
        params.put("Person_id", personId);
        return creteGetRequest("api/MedWorker", params);

    }

    @GET
    @Path("/getMedworkerById")
    @Produces("application/json;charset=UTF-8")
    public static String getMedworkerbyid(@QueryParam("medWorkerId") String medWorkerId) {

        HashMap<String, String> params = new HashMap<>();
        params.put("MedWorker_id", medWorkerId);
        return creteGetRequest("api/MedWorkerById", params);

    }

    @GET
    @Path("/syncVocRefbook")
    @Produces("application/json;charset=UTF-8")
    public static String syncVocRefbook(String tableName) {
        HashMap<String, String> params = new HashMap<>();
        params.put("Refbook_TableName", tableName);
        return creteGetRequest("api/Refbook", params);
    }*/

  /*  @GET
    @Path("/getDiag")
    public static String getDiag() {

        new DaoImpl().saveList(new VocDiag().parseJSON(syncVocRefbook("dbo.Diag")));
        return "{'ok':'0'}";
    }
*/
    @GET
    @Path("/syncDiag")
    public static String syncDiag() {

        List<VocDiag> diagEntities = new DaoImpl().getEntityList("VocDiag", " issync is null");
        List<MedosVocidc10Entity> medosVocidc10Entities = new DaoImpl().getMedosEntityList("MedosVocidc10Entity");

        for (VocDiag d : diagEntities) {
            for (MedosVocidc10Entity d2 : medosVocidc10Entities) {

                if (d2.getCode().equals(d.getCode())) {
                    HashMap<String, String> params = new HashMap<>();
                    params.put("promedcode", "\'" + d.getPromedId() + "\'");
                    new DaoImpl().updateRecordM("MedosVocidc10Entity", params, " where id=" + d2.getId());

                    params = new HashMap<>();
                    params.put("issync", "'true'");
                    new DaoImpl().updateRecord("VocDiag", params, " where id=" + d.getId());
                }
            }
        }
        return "{'ok':'0'}";
    }

   /* @GET
    @Path("/getUsluga")
    public static String getUsluga() {
        new DaoImpl().saveList(new VocUslugaComplex().parseJson(syncVocRefbook("dbo.UslugaComplex")));
        return "{'ok':'0'}";
    }*/

    @GET
    @Path("/syncUsluga")
    public static String syncUsluga() {
        List<VocUslugaComplex> uslugaComplexEntities = new DaoImpl().getEntityList("VocUslugaComplex", " issync is null");
        List<MedosMedservice> medosMedserviceEntities = new DaoImpl().getMedosEntityList("MedosMedservice");

        for (VocUslugaComplex d : uslugaComplexEntities) {
            for (MedosMedservice d2 : medosMedserviceEntities) {

                if (d2.getCode().equals(d.getCode())) {
                    HashMap<String, String> params = new HashMap<>();
                    params.put("promedcode", "\'" + d.getPromedId() + "\'");
                    new DaoImpl().updateRecordM("MedosMedservice", params, " where id=" + d2.getId());

                    params = new HashMap<>();
                    params.put("issync", "'true'");
                    new DaoImpl().updateRecord("VocUslugaComplex", params, " where id=" + d.getId());
                }
            }
        }
        return "{'ok':'0'}";
    }
}
