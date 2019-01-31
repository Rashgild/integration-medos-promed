package ru.integration.model;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@Entity
@Table(name = "auth")
public class Auth {

    public Auth() {
        this.expire = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Basic
    @JsonProperty("sess_id")
    @Column(name = "sess_id")
    private String phpSessId;

    @Basic
    @Column(name = "isExpire")
    private Boolean expire;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhpSessId() {
        return phpSessId;
    }

    public void setPhpSessId(String phpSessId) {
        this.phpSessId = phpSessId;
    }

    public Boolean isExpire() {
        return expire;
    }

    public void setExpire(Boolean expire) {
        this.expire = expire;
    }
}
