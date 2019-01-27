package ru.integration.entities.schedule;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;


@Entity
@Table(name = "workcalendartime", schema = "sqluser", catalog = "riams")
public class MedosWorkcalendartimeEntity {
    private Long id;
    private Time timefrom;
    private Boolean additional;
    private Boolean rest;
    private String prepatientinfo;
    private String createprerecord;
    private Date createdateprerecord;
    private Time createtimeprerecord;
    private Date createdate;
    private Time createtime;
    private String createusername;
    private Long service;
    private String phone;
    private Long prescription;
    private Long prehospital;
    private String patientcomment;
    private Boolean isdeleted;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "timefrom")
    public Time getTimefrom() {
        return timefrom;
    }

    public void setTimefrom(Time timefrom) {
        this.timefrom = timefrom;
    }

    @Basic
    @Column(name = "additional")
    public Boolean getAdditional() {
        return additional;
    }
    public void setAdditional(Boolean additional) {
        this.additional = additional;
    }


    @Basic
    @Column(name = "rest")
    public Boolean getRest() {
        return rest;
    }
    public void setRest(Boolean rest) {
        this.rest = rest;
    }

    @Basic
    @Column(name = "prepatientinfo")
    public String getPrepatientinfo() {
        return prepatientinfo;
    }
    public void setPrepatientinfo(String prepatientinfo) {
        this.prepatientinfo = prepatientinfo;
    }

    @Basic
    @Column(name = "createprerecord")
    public String getCreateprerecord() {
        return createprerecord;
    }
    public void setCreateprerecord(String createprerecord) {
        this.createprerecord = createprerecord;
    }

    @Basic
    @Column(name = "createdateprerecord")
    public Date getCreatedateprerecord() {
        return createdateprerecord;
    }
    public void setCreatedateprerecord(Date createdateprerecord) {
        this.createdateprerecord = createdateprerecord;
    }

    @Basic
    @Column(name = "createtimeprerecord")
    public Time getCreatetimeprerecord() {
        return createtimeprerecord;
    }

    public void setCreatetimeprerecord(Time createtimeprerecord) {
        this.createtimeprerecord = createtimeprerecord;
    }

    @Basic
    @Column(name = "createdate")
    public Date getCreatedate() {
        return createdate;
    }
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    @Basic
    @Column(name = "createtime")
    public Time getCreatetime() {
        return createtime;
    }
    public void setCreatetime(Time createtime) {
        this.createtime = createtime;
    }

    @Basic
    @Column(name = "createusername")
    public String getCreateusername() {
        return createusername;
    }
    public void setCreateusername(String createusername) {
        this.createusername = createusername;
    }

    @Basic
    @Column(name = "service")
    public Long getService() {
        return service;
    }
    public void setService(Long service) {
        this.service = service;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "prescription")
    public Long getPrescription() {
        return prescription;
    }
    public void setPrescription(Long prescription) {
        this.prescription = prescription;
    }

    @Basic
    @Column(name = "prehospital")
    public Long getPrehospital() {
        return prehospital;
    }
    public void setPrehospital(Long prehospital) {
        this.prehospital = prehospital;
    }

    @Basic
    @Column(name = "patientcomment")
    public String getPatientcomment() {
        return patientcomment;
    }
    public void setPatientcomment(String patientcomment) {
        this.patientcomment = patientcomment;
    }

    @Basic
    @Column(name = "isdeleted")
    public Boolean getIsdeleted() {
        return isdeleted;
    }
    public void setIsdeleted(Boolean isdeleted) {
        this.isdeleted = isdeleted;
    }

    @Override
    public String toString() {
        return "MedosWorkcalendartimeEntity{" +
                "id=" + id +
                ", timefrom=" + timefrom +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedosWorkcalendartimeEntity that = (MedosWorkcalendartimeEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (timefrom != null ? !timefrom.equals(that.timefrom) : that.timefrom != null) return false;
        if (additional != null ? !additional.equals(that.additional) : that.additional != null) return false;
        if (rest != null ? !rest.equals(that.rest) : that.rest != null) return false;
        if (prepatientinfo != null ? !prepatientinfo.equals(that.prepatientinfo) : that.prepatientinfo != null)
            return false;
        if (createprerecord != null ? !createprerecord.equals(that.createprerecord) : that.createprerecord != null)
            return false;
        if (createdateprerecord != null ? !createdateprerecord.equals(that.createdateprerecord) : that.createdateprerecord != null)
            return false;
        if (createtimeprerecord != null ? !createtimeprerecord.equals(that.createtimeprerecord) : that.createtimeprerecord != null)
            return false;
        if (createdate != null ? !createdate.equals(that.createdate) : that.createdate != null) return false;
        if (createtime != null ? !createtime.equals(that.createtime) : that.createtime != null) return false;
        if (createusername != null ? !createusername.equals(that.createusername) : that.createusername != null)
            return false;
        if (service != null ? !service.equals(that.service) : that.service != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (prescription != null ? !prescription.equals(that.prescription) : that.prescription != null) return false;
        if (prehospital != null ? !prehospital.equals(that.prehospital) : that.prehospital != null) return false;
        if (patientcomment != null ? !patientcomment.equals(that.patientcomment) : that.patientcomment != null)
            return false;
        if (isdeleted != null ? !isdeleted.equals(that.isdeleted) : that.isdeleted != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (timefrom != null ? timefrom.hashCode() : 0);
        result = 31 * result + (additional != null ? additional.hashCode() : 0);
        result = 31 * result + (rest != null ? rest.hashCode() : 0);
        result = 31 * result + (prepatientinfo != null ? prepatientinfo.hashCode() : 0);
        result = 31 * result + (createprerecord != null ? createprerecord.hashCode() : 0);
        result = 31 * result + (createdateprerecord != null ? createdateprerecord.hashCode() : 0);
        result = 31 * result + (createtimeprerecord != null ? createtimeprerecord.hashCode() : 0);
        result = 31 * result + (createdate != null ? createdate.hashCode() : 0);
        result = 31 * result + (createtime != null ? createtime.hashCode() : 0);
        result = 31 * result + (createusername != null ? createusername.hashCode() : 0);
        result = 31 * result + (service != null ? service.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (prescription != null ? prescription.hashCode() : 0);
        result = 31 * result + (prehospital != null ? prehospital.hashCode() : 0);
        result = 31 * result + (patientcomment != null ? patientcomment.hashCode() : 0);
        result = 31 * result + (isdeleted != null ? isdeleted.hashCode() : 0);
        return result;
    }
}
