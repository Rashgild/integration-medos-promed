package ru.rashgild.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@Getter
@Setter
public class Auth {

    public Auth() {
        this.expire = false;
    }

    private Integer id;

    @JsonProperty("sess_id")
    private String phpSessId;

    private Boolean expire;

}
