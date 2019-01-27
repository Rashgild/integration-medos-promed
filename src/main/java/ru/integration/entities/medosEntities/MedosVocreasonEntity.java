package ru.integration.entities.medosEntities;

import javax.persistence.*;


@Entity
@Table(name = "vocreason", schema = "sqluser", catalog = "riams")
public class MedosVocreasonEntity {
    private Long id;
    private String promedcode;
    private String promedcode2;

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
    @Column(name = "promedcode")
    public String getPromedcode() {
        return promedcode;
    }
    public void setPromedcode(String promedcode) {
        this.promedcode = promedcode;
    }

    @Basic
    @Column(name = "promedcode2")
    public String getPromedcode2() {
        return promedcode2;
    }
    public void setPromedcode2(String promedcode2) {
        this.promedcode2 = promedcode2;
    }
}
