package ru.integration.entities.medosEntities;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "patient", schema = "sqluser", catalog = "riams")
public class MedosPatientEntity {
    private Long id;
    private String firstname;
    private String lastname;
    private String middlename;
    private String snils;
    private Long inn;
    private Date birthday;
    private String passportnumber;
    private String passportseries;
    private Date passportdateissued;
    private String passportwhomissued;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "middlename")
    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    @Basic
    @Column(name = "snils")
    public String getSnils() {
        return snils;
    }

    public void setSnils(String snils) {
        this.snils = snils;
    }

    @Basic
    @Column(name = "inn")
    public Long getInn() {
        return inn;
    }
    public void setInn(Long inn) {
        this.inn = inn;
    }


    @Basic
    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "passportnumber")
    public String getPassportnumber() {
        return passportnumber;
    }
    public void setPassportnumber(String passportnumber) {
        this.passportnumber = passportnumber;
    }

    @Basic
    @Column(name = "passportseries")
    public String getPassportseries() {
        return passportseries;
    }
    public void setPassportseries(String passportseries) {
        this.passportseries = passportseries;
    }

    @Basic
    @Column(name = "passportdateissued")
    public Date getPassportdateissued() {
        return passportdateissued;
    }
    public void setPassportdateissued(Date passportdateissued) {
        this.passportdateissued = passportdateissued;
    }

    @Basic
    @Column(name = "passportwhomissued")
    public String getPassportwhomissued() {
        return passportwhomissued;
    }
    public void setPassportwhomissued(String passportwhomissued) {
        this.passportwhomissued = passportwhomissued;
    }


}
