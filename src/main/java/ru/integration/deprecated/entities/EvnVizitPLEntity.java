package ru.integration.deprecated.entities;

import javax.persistence.*;


@Entity
@Table(name = "evnvizit", schema = "public", catalog = "integration")
public class EvnVizitPLEntity {

    private Integer id;
    private String evnPLBase_id; // id Случая в промеде
    private String evnVizitPL_id; //id визита в промеде
    private String evnParent_id; //id родителя ТАП
    private String medos_id; // medcase id в медосе


    private String evn_setDT;
    private String diag_id;
    private String deseaseType_id;
    private String vizitType_id;
    private String mes_id;
    private String medStaffFact_id;
    private String treatmentClass_id;
    private String medicalCareKind_id;
    private String serviceType_id;
    private String payType_id;
    private String lpuSection_id;
    private Boolean isExport;

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
    @Column(name = "evnPLBase_id")
    public String getEvnPLBase_id() {
        return evnPLBase_id;
    }
    public void setEvnPLBase_id(String evnPLBase_id) {
        this.evnPLBase_id = evnPLBase_id;
    }

    @Basic
    @Column(name = "evnVizitPL_id")
    public String getEvnVizitPL_id() {
        return evnVizitPL_id;
    }
    public void setEvnVizitPL_id(String evnVizitPL_id) {
        this.evnVizitPL_id = evnVizitPL_id;
    }

    @Basic
    @Column(name = "evnParent_id")
    public String getEvnParent_id() {
        return evnParent_id;
    }
    public void setEvnParent_id(String evnParent_id) {
        this.evnParent_id = evnParent_id;
    }

    @Basic
    @Column(name = "evn_setDT")
    public String getEvn_setDT() {
        return evn_setDT;
    }
    public void setEvn_setDT(String evn_setDT) {
        this.evn_setDT = evn_setDT;
    }

    @Basic
    @Column(name = "diag_id")
    public String getDiag_id() {
        return diag_id;
    }
    public void setDiag_id(String diag_id) {
        this.diag_id = diag_id;
    }

    @Basic
    @Column(name = "deseaseType_id")
    public String getDeseaseType_id() {
        return deseaseType_id;
    }
    public void setDeseaseType_id(String deseaseType_id) {
        this.deseaseType_id = deseaseType_id;
    }

    @Basic
    @Column(name = "vizitType_id")
    public String getVizitType_id() {
        return vizitType_id;
    }
    public void setVizitType_id(String vizitType_id) {
        this.vizitType_id = vizitType_id;
    }

    @Basic
    @Column(name = "mes_id")
    public String getMes_id() {
        return mes_id;
    }
    public void setMes_id(String mes_id) {
        this.mes_id = mes_id;
    }

    @Basic
    @Column(name = "medStaffFact_id")
    public String getMedStaffFact_id() {
        return medStaffFact_id;
    }
    public void setMedStaffFact_id(String medStaffFact_id) {
        this.medStaffFact_id = medStaffFact_id;
    }

    @Basic
    @Column(name = "treatmentClass_id")
    public String getTreatmentClass_id() {
        return treatmentClass_id;
    }
    public void setTreatmentClass_id(String treatmentClass_id) {
        this.treatmentClass_id = treatmentClass_id;
    }

    @Basic
    @Column(name = "medicalCareKind_id")
    public String getMedicalCareKind_id() {
        return medicalCareKind_id;
    }
    public void setMedicalCareKind_id(String medicalCareKind_id) {
        this.medicalCareKind_id = medicalCareKind_id;
    }

    @Basic
    @Column(name = "serviceType_id")
    public String getServiceType_id() {
        return serviceType_id;
    }
    public void setServiceType_id(String serviceType_id) {
        this.serviceType_id = serviceType_id;
    }

    @Basic
    @Column(name = "payType_id")
    public String getPayType_id() {
        return payType_id;
    }
    public void setPayType_id(String payType_id) {
        this.payType_id = payType_id;
    }

    @Basic
    @Column(name = "lpuSection_id")
    public String getLpuSection_id() {
        return lpuSection_id;
    }
    public void setLpuSection_id(String lpuSection_id) {
        this.lpuSection_id = lpuSection_id;
    }

    @Basic
    @Column(name = "isexport")
    public Boolean getExport() {
        return isExport;
    }
    public void setExport(Boolean export) {
        isExport = export;
    }

    @Basic
    @Column(name = "medos_id")
    public String getMedos_id() {
        return medos_id;
    }
    public void setMedos_id(String medos_id) {
        this.medos_id = medos_id;
    }
}
