package ru.integration.deprecated.entities;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ru.integration.dao.DaoImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import static ru.integration.util.Methods.checkCode;
import static ru.integration.util.Methods.checkJsonObj;
import static ru.integration.util.Methods.checkJsonObjGetInteger;


@Entity
@Table(name = "person", schema = "public", catalog = "integration")
public class PersonEntity {

    private Integer id;
    private Integer mis_id;
    private Integer promedPerson_id;
    private String personSurName_SurName;
    private String personFirName_FirName;
    private String personSecName_SecName;

    private Date personBirthDay_BirthDay;//ДР
    private Integer person_Sex_id;//Пол
    private String personPhone_Phone;   //Телефон
    private String personSnils_Snils; //СНИЛС
    private Integer socStatus_id; //Соцстатус
    private Integer uAddress_id;    //Адрес
    private Integer pAddress_id;
    private Integer bAddress_id;
    private Integer org_id; //место работы dbo.Org
    private Integer post_id;    //должность dbo.Post

    private Integer medstaffFact_id; // для работников

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "personsurname_surname")
    public String getPersonSurName_SurName() {
        return personSurName_SurName;
    }

    public void setPersonSurName_SurName(String personSurName_SurName) {
        this.personSurName_SurName = personSurName_SurName;
    }

    @Basic
    @Column(name = "personfirname_firName")
    public String getPersonFirName_FirName() {
        return personFirName_FirName;
    }

    public void setPersonFirName_FirName(String personFirName_FirName) {
        this.personFirName_FirName = personFirName_FirName;
    }

    @Basic
    @Column(name = "personsecname_secname")
    public String getPersonSecName_SecName() {
        return personSecName_SecName;
    }

    public void setPersonSecName_SecName(String personSecName_SecName) {
        this.personSecName_SecName = personSecName_SecName;
    }

    @Basic
    @Column(name = "personbirthday_birthday")
    public Date getPersonBirthDay_BirthDay() {
        return personBirthDay_BirthDay;
    }

    public void setPersonBirthDay_BirthDay(Date personBirthDay_BirthDay) {
        this.personBirthDay_BirthDay = personBirthDay_BirthDay;
    }

    @Basic
    @Column(name = "person_sex_id")
    public Integer getPerson_Sex_id() {
        return person_Sex_id;
    }

    public void setPerson_Sex_id(Integer person_Sex_id) {
        this.person_Sex_id = person_Sex_id;
    }

    @Basic
    @Column(name = "personphone_phone")
    public String getPersonPhone_Phone() {
        return personPhone_Phone;
    }

    public void setPersonPhone_Phone(String personPhone_Phone) {
        this.personPhone_Phone = personPhone_Phone;
    }


    @Basic
    @Column(name = "personsnils_snils")
    public String getPersonSnils_Snils() {
        return personSnils_Snils;
    }

    public void setPersonSnils_Snils(String personSnils_Snils) {
        this.personSnils_Snils = personSnils_Snils;
    }

    @Basic
    @Column(name = "socstatus_id")
    public Integer getSocStatus_id() {
        return socStatus_id;
    }

    public void setSocStatus_id(Integer socStatus_id) {
        this.socStatus_id = socStatus_id;
    }

    @Basic
    @Column(name = "org_id")
    public Integer getOrg_id() {
        return org_id;
    }

    public void setOrg_id(Integer org_id) {
        this.org_id = org_id;
    }


    @Basic
    @Column(name = "post_id")
    public Integer getPost_id() {
        return post_id;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }

    @Basic
    @Column(name = "mis_id")
    public Integer getMis_id() {
        return mis_id;
    }

    public void setMis_id(Integer mis_id) {
        this.mis_id = mis_id;
    }

    @Basic
    @Column(name = "promedperson_id")
    public Integer getPromedPerson_id() {
        return promedPerson_id;
    }

    public void setPromedPerson_id(Integer promedPerson_id) {
        this.promedPerson_id = promedPerson_id;
    }

    @Basic
    @Column(name = "uaddress_id")
    public Integer getuAddress_id() {
        return uAddress_id;
    }

    public void setuAddress_id(Integer uAddress_id) {
        this.uAddress_id = uAddress_id;
    }

    @Basic
    @Column(name = "paddress_id")
    public Integer getpAddress_id() {
        return pAddress_id;
    }

    public void setpAddress_id(Integer pAddress_id) {
        this.pAddress_id = pAddress_id;
    }

    @Basic
    @Column(name = "baddress_id")
    public Integer getbAddress_id() {
        return bAddress_id;
    }

    public void setbAddress_id(Integer bAddress_id) {
        this.bAddress_id = bAddress_id;
    }


    public Integer getMedstaffFact_id() {
        return medstaffFact_id;
    }

    public void setMedstaffFact_id(Integer medstaffFact_id) {
        this.medstaffFact_id = medstaffFact_id;
    }

    @Override
    public String toString() {
        return "PersonEntity{" +
                "id=" + id +
                ", mis_id=" + mis_id +
                ", Person_id=" + promedPerson_id +
                ", personSurName_SurName='" + personSurName_SurName + '\'' +
                ", personFirName_FirName='" + personFirName_FirName + '\'' +
                ", personSecName_SecName='" + personSecName_SecName + '\'' +
                ", personBirthDay_BirthDay=" + personBirthDay_BirthDay +
                ", person_Sex_id=" + person_Sex_id +
                ", personPhone_Phone='" + personPhone_Phone + '\'' +
                ", personSnils_Snils='" + personSnils_Snils + '\'' +
                ", socStatus_id=" + socStatus_id +
                ", uAddress_id=" + uAddress_id +
                ", pAddress_id=" + pAddress_id +
                ", bAddress_id=" + bAddress_id +
                ", org_id=" + org_id +
                ", post_id=" + post_id +
                '}';
    }


    public List<PersonEntity> parseJSON(String json, Integer medstaffId) {

        JsonParser parser = new JsonParser();
        JsonObject jparse = parser.parse(json).getAsJsonObject();

        if (checkCode(jparse)) {
            List<PersonEntity> personEntities = new ArrayList<>();
            JsonArray data = jparse.getAsJsonArray("data");
            for (JsonElement medspecs : data) {
                try {
                    JsonObject sect = medspecs.getAsJsonObject();
                    PersonEntity personEntity = new PersonEntity();

                    personEntity.setPromedPerson_id(checkJsonObjGetInteger(sect, "Person_id"));
                    personEntity.setPersonSurName_SurName(checkJsonObj(sect, "PersonSurName_SurName"));
                    personEntity.setPersonSecName_SecName(checkJsonObj(sect, "PersonFirName_FirName"));
                    personEntity.setPersonFirName_FirName(checkJsonObj(sect, "PersonSecName_SecName"));
                    personEntity.setPersonBirthDay_BirthDay(Date.valueOf(checkJsonObj(sect, "PersonBirthDay_BirthDay")));
                    personEntity.setPerson_Sex_id(checkJsonObjGetInteger(sect, "Person_Sex_id"));
                    personEntity.setPersonPhone_Phone(checkJsonObj(sect, "PersonPhone_Phone"));
                    personEntity.setPersonSnils_Snils(checkJsonObj(sect, "PersonSnils_Snils"));
                    personEntity.setSocStatus_id(checkJsonObjGetInteger(sect, "SocStatus_id"));
                    personEntity.setuAddress_id(checkJsonObjGetInteger(sect, "UAddress_id"));
                    personEntity.setpAddress_id(checkJsonObjGetInteger(sect, "PAddress_id"));

                    personEntity.setMedstaffFact_id(medstaffId);
                    personEntities.add(personEntity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return personEntities;
        } else return null;
    }

    public void saveJson(String json) {
        List<PersonEntity> list = new PersonEntity().parseJSON(json, 0);
        new DaoImpl().saveList(list);
    }
}
