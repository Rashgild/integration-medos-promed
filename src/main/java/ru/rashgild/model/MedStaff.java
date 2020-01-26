package ru.rashgild.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class MedStaff {

    private Integer id;

    private Integer medsaffId;

    @JsonProperty("MedPersonal_id")
    private Integer medPersonalId;

    @JsonProperty("Staff_id")
    private Integer staffId;

    @JsonProperty("BeginDate")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginDate;

    @JsonProperty("EndDate")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @JsonProperty("LpuSection_id")
    private Integer lpuSectionId;

    @JsonProperty("Lpu_id")
    private Integer lpuId;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    public static class MedstaffList {

        @JsonProperty("error_code")
        private Integer error;

        @JsonProperty("data")
        private List<MedStaff> staffList;
    }
}
