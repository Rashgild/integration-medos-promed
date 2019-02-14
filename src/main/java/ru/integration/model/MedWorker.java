package ru.integration.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "medworker")
public class MedWorker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Basic
    @Column(name = "medworker_id")//promed_id
    private Integer medWorkerId;

    @JoinColumn(name = "person_id")
    @OneToOne
    @JsonProperty("Person_id")
    private Person personId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMedWorkerId() {
        return medWorkerId;
    }

    public void setMedWorkerId(Integer medWorkerId) {
        this.medWorkerId = medWorkerId;
    }

    public Person getPersonId() {
        return personId;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MedWorkerList {

        @JsonProperty("error_code")
        private Integer error;

        @JsonProperty("data")
        private Map<String, String> medworkers;

        public Integer getError() {
            return error;
        }

        public void setError(Integer error) {
            this.error = error;
        }

        public Map<String, String> getMedworkers() {
            return medworkers;
        }

        public void setMedworkers(Map<String, String> medworkers) {
            this.medworkers = medworkers;
        }
    }
}
