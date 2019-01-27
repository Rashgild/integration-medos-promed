package ru.integration.entities.medosEntities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;


@Entity
@Table(name = "medcase", schema = "sqluser", catalog = "riams")
public class MedosMedcaseEntity {

    private String dtype;
    private Long id;
    private String username;
    private Date datestart;
    private Boolean emergency;
    private Date printdate;
    private Time printtime;
    private BigDecimal uet;
    private Date createdate;
    private Boolean noactuality;
    private Long isprint;
    private Long isdiagnosiscreate;
    private Long isdiarycreate;
    private Date dateexecute;
    private Time timeexecute;
    private Integer privilegerecipeamount;
    private Date nextvisitdate;
    private Integer medserviceamount;
    private Date datefinish;
    private Time entrancetime;
    private Time dischargetime;
    private Date rwdate;
    private String rwnumber;
    private Date orderdate;
    private String ordernumber;
    private String externalid;
    private String supplytype;
    private String supplyordernumber;
    private Boolean rwexamination;
    private Boolean aidsexamination;
    private Boolean ambulancetreatment;
    private Boolean relativemessage;
    private Boolean medicalaid;
    private Boolean provisional;
    private String dischargeepicrisis;
    private String attendant;
    private Boolean rarecase;
    private Boolean compulsorytreatment;
    private Date lawcourtdesiciondate;
    private Boolean incapacity;
    private Date transferdate;
    private Time transfertime;
    private String roomnumber;
    private String bednumber;
    private String motherward;
    private Long isclosespo;
    private Date operationdate;
    private Long complication;
    private Boolean hotelservices;
    private Date editdate;
    private String editusername;
    private Time createtime;
    private Time edittime;

    private Long patient_id;
    private Long parent_id;
    private Long visitResult_id;
    private Long workfunctionexecute_id;
    private Long visitreason_id;
    private Long workplacetype_id;
    private Long servicestream_id;
    private Long medservice_id;
    private Long hospitalization_id;
    private Long idc10_id;
    private Boolean upload;

    @Basic
    @Column(name = "upload")
    public Boolean getUpload() {
        return upload;
    }
    public void setUpload(Boolean upload) {
        this.upload = upload;
    }

    @Basic
    @Column(name = "idc10_id")
    public Long getIdc10_id() {
        return idc10_id;
    }
    public void setIdc10_id(Long idc10_id) {
        this.idc10_id = idc10_id;
    }

    @Basic
    @Column(name = "workfunctionexecute_id")
    public Long getWorkfunctionexecute_id() {
        return workfunctionexecute_id;
    }
    public void setWorkfunctionexecute_id(Long workfunctionexecute_id) {
        this.workfunctionexecute_id = workfunctionexecute_id;
    }

    @Basic
    @Column(name = "visitResult_id")
    public Long getVisitResult_id() {
        return visitResult_id;
    }
    public void setVisitResult_id(Long visitResult_id) {
        this.visitResult_id = visitResult_id;
    }

    @Basic
    @Column(name = "visitreason_id")
    public Long getVisitreason_id() {
        return visitreason_id;
    }
    public void setVisitreason_id(Long visitreason_id) {
        this.visitreason_id = visitreason_id;
    }

    @Basic
    @Column(name = "workplacetype_id")
    public Long getWorkplacetype_id() {
        return workplacetype_id;
    }
    public void setWorkplacetype_id(Long workplacetype_id) {
        this.workplacetype_id = workplacetype_id;
    }

    @Basic
    @Column(name = "servicestream_id")
    public Long getServicestream_id() {
        return servicestream_id;
    }
    public void setServicestream_id(Long servicestream_id) {
        this.servicestream_id = servicestream_id;
    }

    @Basic
    @Column(name = "medservice_id")
    public Long getMedservice_id() {
        return medservice_id;
    }
    public void setMedservice_id(Long medservice_id) {
        this.medservice_id = medservice_id;
    }

    @Basic
    @Column(name = "hospitalization_id")
    public Long getHospitalization_id() {
        return hospitalization_id;
    }
    public void setHospitalization_id(Long hospitalization_id) {
        this.hospitalization_id = hospitalization_id;
    }

    @Basic
    @Column(name = "dtype")
    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

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
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "datestart")
    public Date getDatestart() {
        return datestart;
    }

    public void setDatestart(Date datestart) {
        this.datestart = datestart;
    }

    @Basic
    @Column(name = "emergency")
    public Boolean getEmergency() {
        return emergency;
    }

    public void setEmergency(Boolean emergency) {
        this.emergency = emergency;
    }

    @Basic
    @Column(name = "printdate")
    public Date getPrintdate() {
        return printdate;
    }

    public void setPrintdate(Date printdate) {
        this.printdate = printdate;
    }

    @Basic
    @Column(name = "printtime")
    public Time getPrinttime() {
        return printtime;
    }
    public void setPrinttime(Time printtime) {
        this.printtime = printtime;
    }

    @Basic
    @Column(name = "uet")
    public BigDecimal getUet() {
        return uet;
    }
    public void setUet(BigDecimal uet) {
        this.uet = uet;
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
    @Column(name = "noactuality")
    public Boolean getNoactuality() {
        return noactuality;
    }

    public void setNoactuality(Boolean noactuality) {
        this.noactuality = noactuality;
    }

    @Basic
    @Column(name = "isprint")
    public Long getIsprint() {
        return isprint;
    }
    public void setIsprint(Long isprint) {
        this.isprint = isprint;
    }

    @Basic
    @Column(name = "isdiagnosiscreate")
    public Long getIsdiagnosiscreate() {
        return isdiagnosiscreate;
    }
    public void setIsdiagnosiscreate(Long isdiagnosiscreate) {
        this.isdiagnosiscreate = isdiagnosiscreate;
    }

    @Basic
    @Column(name = "isdiarycreate")
    public Long getIsdiarycreate() {
        return isdiarycreate;
    }
    public void setIsdiarycreate(Long isdiarycreate) {
        this.isdiarycreate = isdiarycreate;
    }

    @Basic
    @Column(name = "dateexecute")
    public Date getDateexecute() {
        return dateexecute;
    }

    public void setDateexecute(Date dateexecute) {
        this.dateexecute = dateexecute;
    }

    @Basic
    @Column(name = "timeexecute")
    public Time getTimeexecute() {
        return timeexecute;
    }

    public void setTimeexecute(Time timeexecute) {
        this.timeexecute = timeexecute;
    }

    @Basic
    @Column(name = "privilegerecipeamount")
    public Integer getPrivilegerecipeamount() {
        return privilegerecipeamount;
    }

    public void setPrivilegerecipeamount(Integer privilegerecipeamount) {
        this.privilegerecipeamount = privilegerecipeamount;
    }

    @Basic
    @Column(name = "nextvisitdate")
    public Date getNextvisitdate() {
        return nextvisitdate;
    }

    public void setNextvisitdate(Date nextvisitdate) {
        this.nextvisitdate = nextvisitdate;
    }

    @Basic
    @Column(name = "medserviceamount")
    public Integer getMedserviceamount() {
        return medserviceamount;
    }

    public void setMedserviceamount(Integer medserviceamount) {
        this.medserviceamount = medserviceamount;
    }

    @Basic
    @Column(name = "datefinish")
    public Date getDatefinish() {
        return datefinish;
    }

    public void setDatefinish(Date datefinish) {
        this.datefinish = datefinish;
    }

    @Basic
    @Column(name = "entrancetime")
    public Time getEntrancetime() {
        return entrancetime;
    }

    public void setEntrancetime(Time entrancetime) {
        this.entrancetime = entrancetime;
    }

    @Basic
    @Column(name = "dischargetime")
    public Time getDischargetime() {
        return dischargetime;
    }

    public void setDischargetime(Time dischargetime) {
        this.dischargetime = dischargetime;
    }

    @Basic
    @Column(name = "rwdate")
    public Date getRwdate() {
        return rwdate;
    }

    public void setRwdate(Date rwdate) {
        this.rwdate = rwdate;
    }

    @Basic
    @Column(name = "rwnumber")
    public String getRwnumber() {
        return rwnumber;
    }

    public void setRwnumber(String rwnumber) {
        this.rwnumber = rwnumber;
    }

    @Basic
    @Column(name = "orderdate")
    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    @Basic
    @Column(name = "ordernumber")
    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    @Basic
    @Column(name = "externalid")
    public String getExternalid() {
        return externalid;
    }

    public void setExternalid(String externalid) {
        this.externalid = externalid;
    }

    @Basic
    @Column(name = "supplytype")
    public String getSupplytype() {
        return supplytype;
    }

    public void setSupplytype(String supplytype) {
        this.supplytype = supplytype;
    }

    @Basic
    @Column(name = "supplyordernumber")
    public String getSupplyordernumber() {
        return supplyordernumber;
    }

    public void setSupplyordernumber(String supplyordernumber) {
        this.supplyordernumber = supplyordernumber;
    }

    @Basic
    @Column(name = "rwexamination")
    public Boolean getRwexamination() {
        return rwexamination;
    }

    public void setRwexamination(Boolean rwexamination) {
        this.rwexamination = rwexamination;
    }

    @Basic
     @Column(name = "aidsexamination")
    public Boolean getAidsexamination() {
        return aidsexamination;
    }

    public void setAidsexamination(Boolean aidsexamination) {
        this.aidsexamination = aidsexamination;
    }

    @Basic
     @Column(name = "ambulancetreatment")
    public Boolean getAmbulancetreatment() {
        return ambulancetreatment;
    }

    public void setAmbulancetreatment(Boolean ambulancetreatment) {
        this.ambulancetreatment = ambulancetreatment;
    }

    @Basic
       @Column(name = "relativemessage")
    public Boolean getRelativemessage() {
        return relativemessage;
    }

    public void setRelativemessage(Boolean relativemessage) {
        this.relativemessage = relativemessage;
    }

    @Basic
     @Column(name = "medicalaid")
    public Boolean getMedicalaid() {
        return medicalaid;
    }

    public void setMedicalaid(Boolean medicalaid) {
        this.medicalaid = medicalaid;
    }

    @Basic
       @Column(name = "provisional")
    public Boolean getProvisional() {
        return provisional;
    }

    public void setProvisional(Boolean provisional) {
        this.provisional = provisional;
    }

    @Basic
     @Column(name = "dischargeepicrisis")
    public String getDischargeepicrisis() {
        return dischargeepicrisis;
    }

    public void setDischargeepicrisis(String dischargeepicrisis) {
        this.dischargeepicrisis = dischargeepicrisis;
    }

    @Basic
      @Column(name = "attendant")
    public String getAttendant() {
        return attendant;
    }

    public void setAttendant(String attendant) {
        this.attendant = attendant;
    }

    @Basic

    @Column(name = "rarecase")
    public Boolean getRarecase() {
        return rarecase;
    }

    public void setRarecase(Boolean rarecase) {
        this.rarecase = rarecase;
    }


    @Basic
       @Column(name = "compulsorytreatment")
    public Boolean getCompulsorytreatment() {
        return compulsorytreatment;
    }

    public void setCompulsorytreatment(Boolean compulsorytreatment) {
        this.compulsorytreatment = compulsorytreatment;
    }

    @Basic
        @Column(name = "lawcourtdesiciondate")
    public Date getLawcourtdesiciondate() {
        return lawcourtdesiciondate;
    }

    public void setLawcourtdesiciondate(Date lawcourtdesiciondate) {
        this.lawcourtdesiciondate = lawcourtdesiciondate;
    }

    @Basic
    @Column(name = "incapacity")
    public Boolean getIncapacity() {
        return incapacity;
    }

    public void setIncapacity(Boolean incapacity) {
        this.incapacity = incapacity;
    }

    @Basic
    @Column(name = "transferdate")
    public Date getTransferdate() {
        return transferdate;
    }

    public void setTransferdate(Date transferdate) {
        this.transferdate = transferdate;
    }

    @Basic
    @Column(name = "transfertime")
    public Time getTransfertime() {
        return transfertime;
    }

    public void setTransfertime(Time transfertime) {
        this.transfertime = transfertime;
    }

    @Basic
    @Column(name = "roomnumber")
    public String getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(String roomnumber) {
        this.roomnumber = roomnumber;
    }

    @Basic
    @Column(name = "bednumber")
    public String getBednumber() {
        return bednumber;
    }

    public void setBednumber(String bednumber) {
        this.bednumber = bednumber;
    }

    @Basic
    @Column(name = "motherward")
    public String getMotherward() {
        return motherward;
    }

    public void setMotherward(String motherward) {
        this.motherward = motherward;
    }

    @Basic
    @Column(name = "isclosespo")
    public Long getIsclosespo() {
        return isclosespo;
    }

    public void setIsclosespo(Long isclosespo) {
        this.isclosespo = isclosespo;
    }

    @Basic
    @Column(name = "operationdate")
    public Date getOperationdate() {
        return operationdate;
    }

    public void setOperationdate(Date operationdate) {
        this.operationdate = operationdate;
    }

    @Basic
    @Column(name = "complication")
    public Long getComplication() {
        return complication;
    }

    public void setComplication(Long complication) {
        this.complication = complication;
    }

    @Basic
    @Column(name = "hotelservices")
    public Boolean getHotelservices() {
        return hotelservices;
    }

    public void setHotelservices(Boolean hotelservices) {
        this.hotelservices = hotelservices;
    }

    @Basic
    @Column(name = "editdate")
    public Date getEditdate() {
        return editdate;
    }

    public void setEditdate(Date editdate) {
        this.editdate = editdate;
    }

    @Basic
    @Column(name = "editusername")
    public String getEditusername() {
        return editusername;
    }
    public void setEditusername(String editusername) {
        this.editusername = editusername;
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
    @Column(name = "edittime")
    public Time getEdittime() {
        return edittime;
    }
    public void setEdittime(Time edittime) {
        this.edittime = edittime;
    }

    @Basic
    @Column(name = "patient_id")
    public Long getPatient_id() {
        return patient_id;
    }
    public void setPatient_id(Long patient_id) {
        this.patient_id = patient_id;
    }

    @Basic
    @Column(name = "parent_id")
    public Long getParent_id() {
        return parent_id;
    }
    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }
}
