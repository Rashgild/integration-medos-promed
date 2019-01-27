package ru.integration.api.policlinicCase;


import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.json.JSONException;

import ru.integration.dao.DaoImpl;
import ru.integration.entities.medosEntities.MedosMedcaseEntity;

@Path("/PoliclinicTest")
public class PoliclinicTest {


    private static List<MedosMedcaseEntity> checkVisit(List<MedosMedcaseEntity> visits) {
        List<MedosMedcaseEntity> falseVisits = new ArrayList<>();

        for (MedosMedcaseEntity visit : visits) {
            if (!visit.getDtype().equals("Visit")) falseVisits.add(visit);
        }
        return falseVisits;
    }

   /* private static boolean checkVisit2(List<MedosMedcaseEntity> visits){

        for(MedosMedcaseEntity visit : visits){
            if(!visit.getDtype().equals("Visit")) return false;
        }
        return true;
    }*/

    @GET
    @Path("/getPolic1")
    @Produces("application/json;charset=UTF-8")
    public static String getPoliclinicCases(@QueryParam("timeEnd") String timeEnd) throws JSONException {

        long start = System.currentTimeMillis();


        List<MedosMedcaseEntity> policlinicCases = new DaoImpl().getMedosEntityList("MedosMedcaseEntity m",
                "(m.upload is null or m.upload =false) " +
                        "and m.servicestream_id = 1 " +
                        "and m.datefinish ='" + timeEnd + "' " +
                        "and m.dtype='PolyclinicMedCase' " +
                        "and (m.noactuality is null or m.noactuality=false)");


        int size = policlinicCases.size();
        String policlinicCase_id = "";
        int index = 0;
        for (MedosMedcaseEntity mcase : policlinicCases) {
            index++;
            if (index == size) {
                policlinicCase_id += mcase.getId();
            } else policlinicCase_id += mcase.getId() + ",\n";
        }


        List<MedosMedcaseEntity> visits = new DaoImpl().getMedosEntityList("MedosMedcaseEntity m",
                "m.parent_id in(" + policlinicCase_id + ")");

        List<MedosMedcaseEntity> falseVis = checkVisit(visits);
        List<MedosMedcaseEntity> falseCase = new ArrayList<>();


        for (MedosMedcaseEntity aa : policlinicCases) {
            for (MedosMedcaseEntity bb : falseVis) {
                if (aa.getId().equals(bb.getParent_id())) {
                    falseCase.add(aa);
                }
            }
        }

        for (MedosMedcaseEntity aa : falseCase) {
            policlinicCases.remove(aa);
        }

        System.out.println("Count cases>>>" + policlinicCases.size());
        long finish = System.currentTimeMillis();
        long timeConsumedMillis = (finish - start);
        return String.valueOf(timeConsumedMillis);
    }

    /*@GET
    @Path("/getPolic2")
    @Produces("application/json;charset=UTF-8")
    public static String getPolic2(@QueryParam("timeEnd") String timeEnd) throws JSONException {

        long start = System.currentTimeMillis();
        List<MedosMedcaseEntity> policlinicCases =new DaoImpl().getMedosEntityList("MedosMedcaseEntity m",
                "(m.upload is null or m.upload =false) " +
                        "and m.servicestream_id = 1 " +
                        "and m.datefinish ='"+timeEnd+"' " +
                        "and m.dtype='PolyclinicMedCase' " +
                        "and (m.noactuality is null or m.noactuality=false)");


        List<MedosMedcaseEntity> goodCases = new ArrayList<>();
        for(MedosMedcaseEntity mcase : policlinicCases){
            List<MedosMedcaseEntity> visits =new DaoImpl().getMedosEntityList("MedosMedcaseEntity m",
                    "m.parent_id="+mcase.getId());
            if(checkVisit2(visits)) goodCases.add(mcase);
        }


        System.out.println("count good cases>>>"+goodCases.size());
        long finish = System.currentTimeMillis();
        long timeConsumedMillis =(finish - start);
        return String.valueOf(timeConsumedMillis);
    }*/
}
