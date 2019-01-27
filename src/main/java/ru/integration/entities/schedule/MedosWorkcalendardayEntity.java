package ru.integration.entities.schedule;

import java.sql.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "workcalendarday", schema = "sqluser", catalog = "riams")
public class MedosWorkcalendardayEntity {
    private Long id;
    private Date calendardate;
    private Boolean holiday;
    private Boolean isdeleted;

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
    @Column(name = "calendardate")
    public Date getCalendardate() {
        return calendardate;
    }

    public void setCalendardate(Date calendardate) {
        this.calendardate = calendardate;
    }

    @Basic
    @Column(name = "holiday")
    public Boolean getHoliday() {
        return holiday;
    }

    public void setHoliday(Boolean holiday) {
        this.holiday = holiday;
    }

    @Basic
    @Column(name = "isdeleted")
    public Boolean getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Boolean isdeleted) {
        this.isdeleted = isdeleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedosWorkcalendardayEntity that = (MedosWorkcalendardayEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (calendardate != null ? !calendardate.equals(that.calendardate) : that.calendardate != null) return false;
        if (holiday != null ? !holiday.equals(that.holiday) : that.holiday != null) return false;
        if (isdeleted != null ? !isdeleted.equals(that.isdeleted) : that.isdeleted != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (calendardate != null ? calendardate.hashCode() : 0);
        result = 31 * result + (holiday != null ? holiday.hashCode() : 0);
        result = 31 * result + (isdeleted != null ? isdeleted.hashCode() : 0);
        return result;
    }
}
