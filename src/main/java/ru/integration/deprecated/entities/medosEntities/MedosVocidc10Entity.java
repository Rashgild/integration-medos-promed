package ru.integration.deprecated.entities.medosEntities;

import javax.persistence.*;


@Entity
@Table(name = "vocidc10", schema = "sqluser", catalog = "riams")
public class MedosVocidc10Entity {

    private Long id;
    private String name;
    private String code;
    private String promedcode;


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
    @Column(name = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "promedcode")
    public String getPromedcode() {
        return promedcode;
    }
    public void setPromedcode(String promedcode) {
        this.promedcode = promedcode;
    }
}
