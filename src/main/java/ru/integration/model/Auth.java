package ru.integration.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class Auth {

    public Auth() {
        this.expire = false;
    }

    private Integer id;

    @JsonProperty("sess_id")
    private String phpSessId;

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
