package ru.integration.entities;

import javax.persistence.*;


@Entity
@Table(name = "evnplbase", schema = "public", catalog = "integration")
public class EvnPLBaseEntity {

    private Integer id;//Внутренний ID
    private Integer evnPLBase_id; // ID в промеде
    private Integer evnVizitPL_id; // ID визита в промеде
    private String promedPerson_id; //ID персоны в промеде
    private String evnPl_NumCard; //№ талона. Уникальный в рамках МО.(medcase_id)
    private String evnPl_IsFinish; // Признак законченности случая. Возможные значения: 0 и 1, - где 0 – нет, 1 – да.
    private String resultClass_id; // Результат обращения (Обязательное поле, если случай закончен); dbo.ResultClass https://astrahanrelease.promedweb.ru:1234/api/Refbook?Refbook_TableName=dbo.ResultClass
    private String resultDeseaseType_id; //Результат обращения (Обязательное поле, если случай закончен); fed.ResultDeseaseType https://astrahanrelease.promedweb.ru:1234/api/Refbook?Refbook_TableName=dbo.ResultDeseaseType https://astrahanrelease.promedweb.ru:1234/api/Refbook?Refbook_TableName=fed.ResultDeseaseType
    private String diag_lid; //заключительный диагноз Обязательное поле, если случай закончен);
    private String evn_setDT; //Дата и время посещения
    private String uslugaComplex_uid;// (N,У) –код посещения; Обязательно если вид оплаты ОМС; //
    private String diag_id; //Основной диагноз (В ТЗ поле не обязательное, на форме обязательное)
    private String deseaseType_id;// характер заболевания (если заполнен диагноз, то поле обязательное)
    private String mes_id; //МЭС (передается только для первого посещения)
    private String vizitType_id; //цель посещения
    private String medStaffFact_id; //Специальность врача
    private String treatmentClass_id;//Вид обращения;
    private String medicalCareKind_id;//(N,О) - Идентификатор вида медицинской помощи
    private String serviceType_id; //Место обслуживания
    private String payType_id; //Тип оплаты
    private String lpuSection_id; //идентификатор отделения ЛПУ
    private Boolean isExport=false;
    private Boolean isSynced=false;//Синхронизировано
    private Boolean isError=true;//Синхронизировано
    private String errorMsg;//Синхронизировано
    private String errorCode;//Синхронизировано

    @Basic
    @Column(name="evnvizitpl_id")
    public Integer getEvnVizitPL_id() {
        return evnVizitPL_id;
    }
    public void setEvnVizitPL_id(Integer evnVizitPL_id) {
        this.evnVizitPL_id = evnVizitPL_id;
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
    @Column(name = "mes_id")
    public String getMes_id() {
        return mes_id;
    }
    public void setMes_id(String mes_id) {
        this.mes_id = mes_id;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",updatable = false,nullable = false)
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column (name = "evnplbase_id")
    public Integer getEvnPLBase_id() {
        return evnPLBase_id;
    }
    public void setEvnPLBase_id(Integer evnPLBase_id) {
        this.evnPLBase_id = evnPLBase_id;
    }

    @Basic
    @Column (name = "promedPerson_id")
    public String getPromedPerson_id() {
        return promedPerson_id;
    }
    public void setPromedPerson_id(String promedPerson_id) {
        this.promedPerson_id = promedPerson_id;
    }

    @Basic
    @Column (name = "evnpl_numcard")
    public String getEvnPl_NumCard() {
        return evnPl_NumCard;
    }
    public void setEvnPl_NumCard(String evnPl_NumCard) {
        this.evnPl_NumCard = evnPl_NumCard;
    }


    @Basic
    @Column (name = "evnpl_isfinish")
    public String getEvnPl_IsFinish() {
        return evnPl_IsFinish;
    }
    public void setEvnPl_IsFinish(String evnPl_IsFinish) {
        this.evnPl_IsFinish = evnPl_IsFinish;
    }

    @Basic
    @Column (name = "resultclass_id")
    public String getResultClass_id() {
        return resultClass_id;
    }
    public void setResultClass_id(String resultClass_id) {
        this.resultClass_id = resultClass_id;
    }

    @Basic
    @Column (name = "resultdeseasetype_id")
    public String getResultDeseaseType_id() {
        return resultDeseaseType_id;
    }
    public void setResultDeseaseType_id(String resultDeseaseType_id) {
        this.resultDeseaseType_id = resultDeseaseType_id;
    }

    @Basic
    @Column (name = "diag_lid")
    public String getDiag_lid() {
        return diag_lid;
    }
    public void setDiag_lid(String diag_lid) {
        this.diag_lid = diag_lid;
    }


    @Basic
    @Column(name = "uslugaComplex_uid")
    public String getUslugaComplex_uid() {
        return uslugaComplex_uid;
    }

    public void setUslugaComplex_uid(String uslugaComplex_uid) {
        this.uslugaComplex_uid = uslugaComplex_uid;
    }

    @Basic
    @Column(name = "isexport",columnDefinition="boolean default false", nullable=false)
    public Boolean getExport() {
        return isExport;
    }
    public void setExport(Boolean export) {
        isExport = export;
    }

    @Basic
    @Column(name = "issynced",columnDefinition="boolean default false", nullable=false )
    public Boolean getSynced() {
        return isSynced;
    }
    public void setSynced(Boolean synced) {
        isSynced = synced;
    }

    @Basic
    @Column(name = "error",columnDefinition="boolean default true", nullable=false )
    public Boolean getError() {
        return isError;
    }
    public void setError(Boolean error) {
        isError = error;
    }

    @Basic
    @Column(name = "errorMsg")
    public String getErrorMsg() {
        return errorMsg;
    }
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Basic
    @Column(name = "errorCode")
    public String getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
