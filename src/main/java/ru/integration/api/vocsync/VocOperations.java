package ru.integration.api.vocsync;

import java.util.HashMap;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ru.integration.util.GlobalVariables;
import ru.integration.dao.DaoImpl;
import ru.integration.entities.PersonEntity;
import ru.integration.entities.medosEntities.MedosVocidc10Entity;
import ru.integration.entities.schedule.FreeDateEntity;
import ru.integration.entities.schedule.FreeTimeEntity;
import ru.integration.entities.schedule.MedWorkerEntity;
import ru.integration.vocentities.*;
import ru.rashgild.integration.vocentities.*;

import static ru.integration.api.Person.getPersonById;
import static ru.integration.util.Methods.creteGetRequest;


@Path("/vocOperations")
public class VocOperations {

    @GET
    @Path("/medSpecOmsByMOSync")
    @Produces("application/json;charset=UTF-8")
    public static String sync() {

        HashMap<String, String> params = new HashMap<>();
        params.put("lpu_id", GlobalVariables.lpu_id);
        String json = creteGetRequest("api/MedSpecOms/MedSpecOmsByMO", params);
        new DaoImpl().saveList(new VocMedSpecOmcMO().parseJSON(json));
        return "";
    }

    @GET
    @Path("/medSpecOmsSync")
    @Produces("application/json;charset=UTF-8")
    public static String medSpecOmsSync() {
        String json = syncVocRefbook("dbo.MedSpecOms");
        new DaoImpl().saveList(new VocMedSpecOmc().parseJSON(json));
        return json;
        /*select *  from vocmedspecomcmo vmo
        left join vocmedspecomc vm on vm.promedid = vmo.medspecomc_id*/
    }


    @GET
    @Path("/medStaffFactByMOSync")
    @Produces("application/json;charset=UTF-8")
    public static String medStaffFactByMOSync() {

        List<VocMedSpecOmcMO> vocMedSpecOmcMOS = (List<VocMedSpecOmcMO>) new DaoImpl<>().getAllE("VocMedSpecOmcMO");
        String jsonend = "";
        for (VocMedSpecOmcMO v : vocMedSpecOmcMOS) {
            HashMap<String, String> params = new HashMap<>();
            params.put("lpu_id", GlobalVariables.lpu_id);
            params.put("MedSpecOms_id", v.getMedSpecOmc_id());
            String json = creteGetRequest("api/MedStaffFact/MedStaffFactByMO", params);
            jsonend += json;
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

    @GET
    @Path("/lpuSectionProfile")
    @Produces("application/json;charset=UTF-8")
    public static String lpuSectionProfile() {
        String json = syncVocRefbook("dbo.LpuSectionProfile");
        new DaoImpl().saveList(new VocLpuSectionProfile().parseJSON(json));
        return json;
    }


    @GET
    @Path("/medStaffFactListByMOandProfileSync")
    @Produces("application/json;charset=UTF-8")
    public static String medStaffFactListByMOandProfile() {

        //medSpecOmsByMOSync
        List<VocLpuSectionById> vocLpuSectionProfiles = (List<VocLpuSectionById>) new DaoImpl<>().getAllE("VocLpuSectionById");
        for (VocLpuSectionById v : vocLpuSectionProfiles) {
            HashMap<String, String> params = new HashMap<>();
            params.put("lpu_id", GlobalVariables.lpu_id);
            params.put("LpuSectionProfile_id", String.valueOf(v.getLpuSectionProfile_id()));
            String json = creteGetRequest("api/medstafffact/MedStaffFactListByMOandProfile", params);
            System.out.println(json);
            new DaoImpl().saveList(new VocMedStaffFactListEntity().parseJSON(json, String.valueOf(v.getLpuSectionProfile_id())));
        }
        return "jsonend";
    }

    @GET
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


    /**-------------------------------------------------------------------------------------------------------------------**/
    /**-------------------------------------------------------------------------------------------------------------------**/
    /**
     * -------------------------------------------------------------------------------------------------------------------
     **/

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

        List<VocMedStaffFactListEntity> medStaffFactListEntity = (List<VocMedStaffFactListEntity>) new DaoImpl<>().getAllE("VocMedStaffFactListEntity");

        for (VocMedStaffFactListEntity v : medStaffFactListEntity) {
            HashMap<String, String> params = new HashMap<>();
            params.put("MedStaffFact_id", String.valueOf(v.getMedStaffFact_id()));
            String json = creteGetRequest("api/MedStaffFact/MedStaffFactById", params);
            System.out.println(json);
            new DaoImpl().saveList(new MedStaffFactByIdEntity().parseJSON(json, v.getMedStaffFact_id()));
        }
        return "{\"jsonend\":\"ok\"}";
    }

    @GET
    @Path("/getPersonbyMedstaff")
    @Produces("application/json;charset=UTF-8")
    public static String getPersonbyMedstaff() {

        List<MedStaffFactByIdEntity> medStaffFactListEntity = (List<MedStaffFactByIdEntity>)
                new DaoImpl<>().getAllE("MedStaffFactByIdEntity");

        for (MedStaffFactByIdEntity v : medStaffFactListEntity) {

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


    /**
     * Получить работника по id персоны
     */
    @GET
    @Path("/getMedworker")
    @Produces("application/json;charset=UTF-8")
    public static String getMedworker(@QueryParam("personId") String personId) {

        HashMap<String, String> params = new HashMap<>();
        params.put("Person_id", personId);
        return creteGetRequest("api/MedWorker", params);

    }

    /**
     * Получить работника по id работника
     */
    @GET
    @Path("/getMedworkerById")
    @Produces("application/json;charset=UTF-8")
    public static String getMedworkerbyid(@QueryParam("medWorkerId") String medWorkerId) {

        HashMap<String, String> params = new HashMap<>();
        params.put("MedWorker_id", medWorkerId);
        return creteGetRequest("api/MedWorkerById", params);

    }


    /**-------------------------------------------------------------------------------------------------------------------**/
    /**-------------------------------------------------------------------------------------------------------------------**/
    /**
     * -------------------------------------------------------------------------------------------------------------------
     **/

    @GET
    @Path("/syncVocRefbook")
    @Produces("application/json;charset=UTF-8")
    public static String syncVocRefbook(String tableName) {
        HashMap<String, String> params = new HashMap<>();
        params.put("Refbook_TableName", tableName);
        return creteGetRequest("api/Refbook", params);
    }

    @GET
    @Path("/getDiag")
    public static String getDiag() {

        new DaoImpl().saveList(new VocDiagEntity().parseJSON(syncVocRefbook("dbo.Diag")));
        return "{'ok':'0'}";
    }

    @GET
    @Path("/syncDiag")
    public static String syncDiag() {

        List<VocDiagEntity> diagEntities = new DaoImpl().getEntityList("VocDiagEntity", " issync is null");
        List<MedosVocidc10Entity> medosVocidc10Entities = new DaoImpl().getMedosEntityList("MedosVocidc10Entity");

        for (VocDiagEntity d : diagEntities) {
            for (MedosVocidc10Entity d2 : medosVocidc10Entities) {

                if (d2.getCode().equals(d.getCode())) {
                    HashMap<String, String> params = new HashMap<>();
                    params.put("promedcode", "\'" + d.getPromedId() + "\'");
                    new DaoImpl().updateRecordM("MedosVocidc10Entity", params, " where id=" + d2.getId());

                    params = new HashMap<>();
                    params.put("issync", "'true'");
                    new DaoImpl().updateRecord("VocDiagEntity", params, " where id=" + d.getId());
                }
            }
        }
        return "{'ok':'0'}";
    }

    @GET
    @Path("/getUsluga")
    public static String getUsluga() {


        new DaoImpl().saveList(new VocUslugaComplexEntity().parseJSON(syncVocRefbook("dbo.UslugaComplex")));
        return "{'ok':'0'}";
    }

    @GET
    @Path("/syncUsluga")
    public static String syncUsluga() {

        List<VocUslugaComplexEntity> uslugaComplexEntities = new DaoImpl().getEntityList("VocUslugaComplexEntity", " issync is null");
        List<MedosMedserviceEntity> medosMedserviceEntities = new DaoImpl().getMedosEntityList("MedosMedserviceEntity");

        for (VocUslugaComplexEntity d : uslugaComplexEntities) {
            for (MedosMedserviceEntity d2 : medosMedserviceEntities) {

                if (d2.getCode().equals(d.getCode())) {
                    HashMap<String, String> params = new HashMap<>();
                    params.put("promedcode", "\'" + d.getPromedId() + "\'");
                    new DaoImpl().updateRecordM("MedosMedserviceEntity", params, " where id=" + d2.getId());

                    params = new HashMap<>();
                    params.put("issync", "'true'");
                    new DaoImpl().updateRecord("VocUslugaComplexEntity", params, " where id=" + d.getId());
                }
            }
        }
        return "{'ok':'0'}";
    }
}
