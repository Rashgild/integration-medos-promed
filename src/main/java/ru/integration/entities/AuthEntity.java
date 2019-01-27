package ru.integration.entities;

import javax.persistence.*;


@Entity
@Table(name = "auth", schema = "public", catalog = "integration")
public class AuthEntity {

    private Integer id;
    private String PHPSESSID;
    private Boolean isExpire;

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
    @Column(name = "PHPSESSID")
    public String getPHPSESSID() {
        return PHPSESSID;
    }
    public void setPHPSESSID(String PHPSESSID) {
        this.PHPSESSID = PHPSESSID;
    }

    @Basic
    @Column(name = "isExpire")
    public Boolean getExpire() {
        return isExpire;
    }
    public void setExpire(Boolean expire) {
        isExpire = expire;
    }
}
