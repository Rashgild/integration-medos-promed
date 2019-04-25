package ru.integration.deprecated.vocEntity;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ru.integration.util.Methods;


@Entity
@Table(name = "vocMedStaffFactMO", schema = "public", catalog = "integration")
public class VocMedStaffFactMO {

    private Integer id;
    private Integer medStaffFact;
    private Integer medSpec_id;
    private Integer person_id;
    private String personSurName_SurName;
    private String personFirName_FirName;
    private String personSecName_SecName;

    private String medosWorkCalendar_id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "medStaffFact")
    public Integer getMedStaffFact() {
        return medStaffFact;
    }

    public void setMedStaffFact(Integer medStaffFact) {
        this.medStaffFact = medStaffFact;
    }

    @Basic
    @Column(name = "medSpec_id")
    public Integer getMedSpec_id() {
        return medSpec_id;
    }

    public void setMedSpec_id(Integer medSpec_id) {
        this.medSpec_id = medSpec_id;
    }

    @Basic
    @Column(name = "person_id")
    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    @Basic
    @Column(name = "personSurName_SurName")
    public String getPersonSurName_SurName() {
        return personSurName_SurName;
    }

    public void setPersonSurName_SurName(String personSurName_SurName) {
        this.personSurName_SurName = personSurName_SurName;
    }

    @Basic
    @Column(name = "personFirName_FirName")
    public String getPersonFirName_FirName() {
        return personFirName_FirName;
    }

    public void setPersonFirName_FirName(String personFirName_FirName) {
        this.personFirName_FirName = personFirName_FirName;
    }

    @Basic
    @Column(name = "personSecName_SecName")
    public String getPersonSecName_SecName() {
        return personSecName_SecName;
    }

    public void setPersonSecName_SecName(String personSecName_SecName) {
        this.personSecName_SecName = personSecName_SecName;
    }


    @Basic
    @Column(name = "medosWorkCalendar_id")
    public String getMedosWorkCalendar_id() {
        return medosWorkCalendar_id;
    }

    public void setMedosWorkCalendar_id(String medosWorkCalendar_id) {
        this.medosWorkCalendar_id = medosWorkCalendar_id;
    }

    public List<VocMedStaffFactMO> parseJSON(String json, Integer medSpec_id) {

        JsonParser parser = new JsonParser();
        JsonObject jparse = parser.parse(json).getAsJsonObject();

        if (Methods.checkCode(jparse)) {
            List<VocMedStaffFactMO> vocMedStaffFactMOS = new ArrayList<>();
            JsonArray data = jparse.getAsJsonArray("data");
            for (JsonElement medspecs : data) {

                try {
                    JsonObject medcpec = medspecs.getAsJsonObject();
                    VocMedStaffFactMO vocMedStaffFactMO = new VocMedStaffFactMO();
                    if (!medcpec.get("MedStaffFact_id").toString().equals("null")) {
                        vocMedStaffFactMO.setMedStaffFact(medcpec.get("MedStaffFact_id").getAsInt());
                    }
                    if (!medcpec.get("Person_id").toString().equals("null")) {
                        vocMedStaffFactMO.setPerson_id(medcpec.get("Person_id").getAsInt());
                    }
                    if (!medcpec.get("PersonSurName_SurName").toString().equals("null")) {
                        vocMedStaffFactMO.setPersonSurName_SurName(medcpec.get("PersonSurName_SurName").getAsString());
                    }
                    if (!medcpec.get("PersonFirName_FirName").toString().equals("null")) {
                        vocMedStaffFactMO.setPersonFirName_FirName(medcpec.get("PersonFirName_FirName").getAsString());
                    }
                    if (!medcpec.get("PersonSecName_SecName").toString().equals("null")) {
                        vocMedStaffFactMO.setPersonSecName_SecName(medcpec.get("PersonSecName_SecName").getAsString());
                    }
                    vocMedStaffFactMO.setMedSpec_id(medSpec_id);

                    vocMedStaffFactMOS.add(vocMedStaffFactMO);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return vocMedStaffFactMOS;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "VocMedStaffFactMO{"
                + "id=" + id
                + ", medStaffFact=" + medStaffFact
                + ", medSpec_id=" + medSpec_id
                + ", person_id=" + person_id
                + ", personSurName_SurName='" + personSurName_SurName + '\''
                + ", personFirName_FirName='" + personFirName_FirName + '\''
                + ", personSecName_SecName='" + personSecName_SecName + '\''
                + ", medosWorkCalendar_id='" + medosWorkCalendar_id + "\'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VocMedStaffFactMO that = (VocMedStaffFactMO) o;

        if (medStaffFact != null ? !medStaffFact.equals(that.medStaffFact) : that.medStaffFact != null) {
            return false;
        }
        return medosWorkCalendar_id != null
                ? medosWorkCalendar_id.equals(that.medosWorkCalendar_id)
                : that.medosWorkCalendar_id == null;
    }

    @Override
    public int hashCode() {
        int result = medStaffFact != null ? medStaffFact.hashCode() : 0;
        result = 31 * result + (medosWorkCalendar_id != null ? medosWorkCalendar_id.hashCode() : 0);
        return result;
    }
}
