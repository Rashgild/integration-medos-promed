package ru.integration.deprecated.api.schedule;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ru.integration.dao.DaoImpl;
import ru.integration.deprecated.entities.PersonEntity;
import ru.integration.deprecated.entities.schedule.BusyDateEntity;
import ru.integration.deprecated.entities.schedule.BusyTimeEntity;
import ru.integration.deprecated.entities.schedule.ListBusyDateTimeEntity;
import ru.integration.deprecated.entities.schedule.MedosWorkcalendardayEntity;
import ru.integration.deprecated.entities.schedule.MedosWorkcalendartimeEntity;
import ru.integration.deprecated.vocEntity.VocMedStaffFactMO;
import ru.integration.util.GlobalVariables;

import static ru.integration.util.Methods.calcTime;
import static ru.integration.util.Methods.checkCode;
import static ru.integration.util.Methods.checkJsonObj;
import static ru.integration.util.Methods.checkJsonObjGetInteger;
import static ru.integration.util.Methods.getCurrent_date;
import static ru.integration.util.Methods.getCurrent_time;
import static ru.integration.util.Methods.getDate;
import static ru.integration.util.Methods.getTime;


@Path("/calendarSync")
public class CalendarApi {

    /*@GET
    @Path("/getListBusyDateTimes")
    @Produces("application/json;charset=UTF-8")
    public static String getListBusyDateTimes(@QueryParam("timbeBeg") String timbeBeg,
                                              @QueryParam("timbeEnd") String timbeEnd) {

        if (timbeBeg == null || timbeBeg.equals("")) timbeBeg = getCurrent_date();
        if (timbeEnd == null || timbeEnd.equals("")) timbeEnd = getCurrent_date();

        HashMap<String, String> params = new HashMap<>();
        params.put("Lpu_id", GlobalVariables.lpu_id);
        params.put("TimeTableGraf_beg", timbeBeg);
        params.put("TimeTableGraf_end", timbeEnd);

       /// String json = creteGetRequest("api/TimeTableGraf/TimeTableGrafbyMO", params);
        String json="";
        new ListBusyDateTimeEntity().saveJson(json);
        return json;
    }*/

    @GET
    @Path("/getBusyDateTimes")
    @Produces("application/json;charset=UTF-8")
    public static String getBusyDateTimes() {

        List<ListBusyDateTimeEntity> busyDateTimeEntities
                = (List<ListBusyDateTimeEntity>)
                new DaoImpl<>().getAllE("ListBusyDateTimeEntity", " isSync=false or isSync is null");

        List<BusyTimeEntity> busyTimeEntities = new ArrayList<>();

        for (ListBusyDateTimeEntity busyDateTimeEntity : busyDateTimeEntities) {

            HashMap<String, String> params = new HashMap<>();
            params.put("TimeTableGraf_id", String.valueOf(busyDateTimeEntity.getTimeTableGraf_id()));

          //String json = creteGetRequest("api/TimeTableGraf/TimeTableGrafById", params);
          //new PersonEntity().saveJson(getPersonById(String.valueOf(busyDateTimeEntity.getPerson_id())));

            String json="";
            JsonParser parser = new JsonParser();
            JsonObject jparse = parser.parse(json).getAsJsonObject();
            if (checkCode(jparse)) {
                JsonArray data = jparse.getAsJsonArray("data");
                for (JsonElement medspecs : data) {
                    try {
                        JsonObject sect = medspecs.getAsJsonObject();

                        BusyDateEntity busyDateEntity = new BusyDateEntity();
                        BusyTimeEntity busyTimeEntity = new BusyTimeEntity();

                        int temp = checkJsonObjGetInteger(sect, "MedStaffFact_id");
                        if (temp > 0) busyDateEntity.setMedStaffFact_id(temp);
                        if (temp > 0) busyTimeEntity.setMedStaffFact_id(temp);

                        busyDateEntity.setTimeTableGraf_begTime(getDate(checkJsonObj(sect, "TimeTableGraf_begTime")));

                        busyTimeEntity.setTimeTableGraf_id(busyDateTimeEntity.getTimeTableGraf_id());
                        busyTimeEntity.setTimeTableGraf_begTime(Timestamp.valueOf(checkJsonObj(sect, "TimeTableGraf_begTime")));
                        busyTimeEntity.setTimeTableGraf_begDate(getDate(checkJsonObj(sect, "TimeTableGraf_begTime")));
                        busyTimeEntity.setTime(getTime(checkJsonObj(sect, "TimeTableGraf_begTime")));

                        temp = checkJsonObjGetInteger(sect, "TimeTableGraf_Time");
                        if (temp > 0) busyTimeEntity.setTimeTableGraf_Time(temp);
                        busyTimeEntity.setPatient_id(busyDateTimeEntity.getPerson_id());

                        busyTimeEntities.add(busyTimeEntity);

                        params = new HashMap<>();
                        params.put("isSync", "true");
                        System.out.println(">>>" + busyDateTimeEntity.getId());
                        new DaoImpl().updateRecord("ListBusyDateTimeEntity", params, "where timetablegraf_id = " + busyDateTimeEntity.getTimeTableGraf_id());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        List<BusyTimeEntity> fromBase = new DaoImpl().getAllE("BusyTimeEntity");
        busyTimeEntities = new DaoImpl().sync(busyTimeEntities);
        busyTimeEntities = new DaoImpl().sync(busyTimeEntities, fromBase);
        new DaoImpl().saveList(busyTimeEntities);
        new DaoImpl().getAllE("BusyDateEntity");
        return "";
    }

    @GET
    @Path("/syncTimeWithMedos")
    @Produces("application/json;charset=UTF-8")
    public static String syncWithMedos(@QueryParam("dateBeg") String dateBeg, @QueryParam("doc") String doc) {

        String retJSON = "";

        try {
            if (dateBeg == null || dateBeg.equals("")) {
                dateBeg = getCurrent_date();
            }
            if (doc == null || doc.equals("")) {
                doc = "1552";
            }

            retJSON += "\"doc\":\"" + doc + "\"";

            List<BusyTimeEntity> listBusyDateTimeEntity =
                    (List<BusyTimeEntity>) new DaoImpl()
                            .getEntityList("BusyTimeEntity", "timetablegraf_begdate='" + dateBeg + "' and medstafffact_id=" + doc + " and (isComplete = false or isComplete is null)");

            List<VocMedStaffFactMO> vocMedStaffFactMOS = new ArrayList<>();
            for (BusyTimeEntity busyTime : listBusyDateTimeEntity) {
                vocMedStaffFactMOS.add((VocMedStaffFactMO) new DaoImpl()
                        .getEntity("VocMedStaffFactMO", "medstafffact=" + busyTime.getMedStaffFact_id() + " and medosworkcalendar_id is not null"));
            }


            vocMedStaffFactMOS = new DaoImpl().sync(vocMedStaffFactMOS);

            List<MedosWorkcalendartimeEntity> medosWorkcalendartimeEntityList = new ArrayList<>();
            for (VocMedStaffFactMO busyDateTimeEntity : vocMedStaffFactMOS) {

                System.out.println(dateBeg + "|" + busyDateTimeEntity.getMedosWorkCalendar_id());
                MedosWorkcalendardayEntity wcday = (MedosWorkcalendardayEntity)
                        new DaoImpl().getEntityM("MedosWorkcalendardayEntity", " calendardate='" + dateBeg + "' and workcalendar_id=" + busyDateTimeEntity.getMedosWorkCalendar_id());

                if (wcday != null && wcday.getId() != null) {

                    medosWorkcalendartimeEntityList = new DaoImpl().getListEntityM("MedosWorkcalendartimeEntity",
                            "(prepatientinfo  like '%ПРОМЕД%' or reservetype_id = 2) and workcalendarday_id=" + wcday.getId());
                } else {
                    System.out.println(".>>>null");
                }
            }

            for (BusyTimeEntity bt : listBusyDateTimeEntity) {
                for (MedosWorkcalendartimeEntity wct : medosWorkcalendartimeEntityList) {

                    Time tempTime = bt.getTime();
                    if (wct.getTimefrom().equals(tempTime) || wct.getTimefrom().equals(calcTime(tempTime, 1))
                            || wct.getTimefrom().equals(calcTime(tempTime, -1))
                            || wct.getTimefrom().equals(calcTime(tempTime, 2))
                            || wct.getTimefrom().equals(calcTime(tempTime, -2))
                    ) {

                        PersonEntity personEntity = (PersonEntity) new DaoImpl()
                                .getEntity("PersonEntity", "promedPerson_id=" + bt.getPatient_id());
                        String tel = "";
                        if (personEntity.getPersonPhone_Phone() != null && !personEntity.getPersonPhone_Phone().equals("")) {
                            tel = " тел. " + personEntity.getPersonPhone_Phone();
                        }
                        String updateStr = "";
                        updateStr = personEntity.getPersonSurName_SurName() + " " + personEntity.getPersonSecName_SecName()
                                + " " + personEntity.getPersonFirName_FirName() + tel + " (" + personEntity.getPersonBirthDay_BirthDay() + ")";

                        System.out.println(updateStr);

                        HashMap<String, String> params = new HashMap<>();
                        params.put("prepatientinfo", "\'" + updateStr + "\'");
                        params.put("createprerecord", "\'IntegrationBot\'");
                        params.put("createdateprerecord", "\'" + getCurrent_date() + "\'");
                        params.put("createtimeprerecord", "\'" + getCurrent_time() + "\'");

                        new DaoImpl().updateRecordM("MedosWorkcalendartimeEntity", params, " where id=" + wct.getId());

                        params = new HashMap<>();
                        params.put("iscomplete", "'true'");
                        new DaoImpl().updateRecord("BusyTimeEntity", params, " where id=" + bt.getId());
                    }
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return "";
    }

    @GET
    @Path("/fullSync")
    @Produces("application/json;charset=UTF-8")
    public static String fullSync(@QueryParam("dateBeg") String dateBeg, @QueryParam("doc") String doc) {

//53645
      /*  String[] s = {"14695","53816","21385","1571","33437","1552","1553","1570","53837"
                ,"41250","27613","53645","1573","1547","1574","1575","53397","1576","19017","14322","49436"
                ,"23006","44462","22029","22203","52212","33380","46678","29171","33432","29748","53980",
                "53825","32828","53176","1549","53409","27834","24667","41227","14761"};
        for(String docs: s){
            System.out.println(s);
            syncWithMedos(dateBeg,docs);
        }*/

        //creteGetRequest("http://192.168.2.45:999/api/auth/", "login");

        if (dateBeg == null || dateBeg.equals("")) {
            Date today = new java.sql.Date(System.currentTimeMillis());
            dateBeg = (new Date(today.getTime() + (1000 * 60 * 60 * 24))).toString();
        }

        //getListBusyDateTimes(dateBeg, dateBeg);
        getBusyDateTimes();
        syncWithMedos(dateBeg, "14695");
        syncWithMedos(dateBeg, "53816");
        syncWithMedos(dateBeg, "21385");
        syncWithMedos(dateBeg, "1571");
        syncWithMedos(dateBeg, "33437");
        syncWithMedos(dateBeg, "1552");
        syncWithMedos(dateBeg, "1553");
        syncWithMedos(dateBeg, "1570");
        syncWithMedos(dateBeg, "53837");
        syncWithMedos(dateBeg, "41250");
        syncWithMedos(dateBeg, "27613");
        // syncWithMedos(dateBeg,"53645");
        syncWithMedos(dateBeg, "1573");
        syncWithMedos(dateBeg, "1547");
        syncWithMedos(dateBeg, "1574");
        syncWithMedos(dateBeg, "1575");
        syncWithMedos(dateBeg, "53397");

        syncWithMedos(dateBeg, "1576");
        syncWithMedos(dateBeg, "19017");
        syncWithMedos(dateBeg, "14322");
        syncWithMedos(dateBeg, "49436");
        syncWithMedos(dateBeg, "23006");
        syncWithMedos(dateBeg, "44462");
        syncWithMedos(dateBeg, "22029");

        syncWithMedos(dateBeg, "22203");
        syncWithMedos(dateBeg, "52212");
        syncWithMedos(dateBeg, "33380");
        syncWithMedos(dateBeg, "46678");
        syncWithMedos(dateBeg, "29171");
        syncWithMedos(dateBeg, "33432");
        syncWithMedos(dateBeg, "29748");
        syncWithMedos(dateBeg, "53980");
        syncWithMedos(dateBeg, "53825");
        syncWithMedos(dateBeg, "32828");
        syncWithMedos(dateBeg, "53176");

        syncWithMedos(dateBeg, "1549");
        syncWithMedos(dateBeg, "53409");
        syncWithMedos(dateBeg, "27834");
        syncWithMedos(dateBeg, "24667");
        syncWithMedos(dateBeg, "41227");
        syncWithMedos(dateBeg, "14761");
        syncWithMedos(dateBeg, "35132");

        return "";
    }
}
