package ru.integration.model.schedule;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
@Entity
@Table(name = "BusyDateTime")
public class BusyDateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Basic
    @JsonProperty("TimeTableGraf_id")
    @Column(name = "time_id")
    private Integer timeId;

    @Basic
    @JsonProperty("Person_id")
    @Column(name = "person_id")
    private Integer personId;

    @Basic
    @Column(name = "sync")
    private Boolean sync;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "journal_id")
    private BusyDateTimeList list;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTimeId() {
        return timeId;
    }

    public void setTimeId(Integer timeId) {
        this.timeId = timeId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Boolean isSync() {
        return sync;
    }

    public void setSync(Boolean sync) {
        this.sync = sync;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BusyDateTimeList {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private Integer id;

        @JsonProperty("data")
        @LazyCollection(LazyCollectionOption.FALSE)
        @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "journal")
        private List<BusyDateTime> busyDateTimeList;

        public List<BusyDateTime> getBusyDateTimeList() {
            return busyDateTimeList;
        }

        public void setBusyDateTimeList(List<BusyDateTime> busyDateTimeList) {
            this.busyDateTimeList = busyDateTimeList;
        }
    }
}
