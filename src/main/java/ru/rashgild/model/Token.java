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
public class Token {

    @JsonProperty("error_code")
    private String errorCode;

    @JsonProperty("sess_id")
    private String sessId;
}
