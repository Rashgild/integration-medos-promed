package ru.integration.model.schedule;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusyDateTimeList {

    @JsonProperty("data")
    private List<BusyDateTime> busyDateTimeList;

    public List<BusyDateTime> getBusyDateTimeList() {
        return busyDateTimeList;
    }

    public void setBusyDateTimeList(List<BusyDateTime> busyDateTimeList) {
        this.busyDateTimeList = busyDateTimeList;
    }
}
