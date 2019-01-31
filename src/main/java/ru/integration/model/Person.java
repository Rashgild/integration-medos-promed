package ru.integration.model;

import java.sql.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Basic
    @Column(name = "mis_id")
    private Integer misId;


    @Basic
    @Column(name = "promedperson_id")
    private Integer promedPersonId;

    @Basic
    @Column(name = "personsurname_surname")
    private String lastname;

    @Basic
    @Column(name = "personfirname_firName")
    private String firstname;

    @Basic
    @Column(name = "personsecname_secname")
    private String middlename;

    @Basic
    @Column(name = "personbirthday_birthday")
    private Date birthday;

    @Basic
    @Column(name = "person_sex_id")
    private Integer sexId;

    @Basic
    @Column(name = "personphone_phone")
    private String phone;

    @Basic
    @Column(name = "personsnils_snils")
    private String snils;

    @Basic
    @Column(name = "socstatus_id")
    private Integer socStatusId;

    @Basic
    @Column(name = "uaddress_id")
    private Integer uAddressId;

    @Basic
    @Column(name = "paddress_id")
    private Integer pAddressId;

    @Basic
    @Column(name = "baddress_id")
    private Integer bAddressId;

    @Basic
    @Column(name = "org_id")
    private Integer orgId; //место работы dbo.Org

    @Basic
    @Column(name = "post_id")
    private Integer postId; //должность dbo.Post

    @Basic
    @Column(name = "medstaffFact_id")
    private Integer medstaffFactId; // для работников

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
