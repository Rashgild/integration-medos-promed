package ru.integration.deprecated.entities.schedule;

import ru.integration.dao.DaoImpl;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "busyDate", schema = "public", catalog = "integration")
public class BusyDateEntity {

    private Integer id;
    private Integer MedStaffFact_id;
    private Date TimeTableGraf_begTime;
    private Boolean isSync;
    private Boolean isComplete;

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
    @Column(name = "medStaffFact_id")
    public Integer getMedStaffFact_id() {
        return MedStaffFact_id;
    }
    public void setMedStaffFact_id(Integer medStaffFact_id) {
        MedStaffFact_id = medStaffFact_id;
    }

    @Basic
    @Column(name = "timeTableGraf_begTime")
    public Date getTimeTableGraf_begTime() {
        return TimeTableGraf_begTime;
    }
    public void setTimeTableGraf_begTime(Date timeTableGraf_begTime) {
        TimeTableGraf_begTime = timeTableGraf_begTime;
    }

    @Basic
    @Column(name = "isSync")
    public Boolean getSync() {
        return isSync;
    }
    public void setSync(Boolean sync) {
        isSync = sync;
    }

    @Basic
    @Column(name = "isComplete")
    public Boolean getComplete() {
        return isComplete;
    }
    public void setComplete(Boolean complete) {
        isComplete = complete;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BusyDateEntity that = (BusyDateEntity) o;

        if (MedStaffFact_id != null ? !MedStaffFact_id.equals(that.MedStaffFact_id) : that.MedStaffFact_id != null)
            return false;
        return TimeTableGraf_begTime != null ? TimeTableGraf_begTime.equals(that.TimeTableGraf_begTime) : that.TimeTableGraf_begTime == null;
    }

    @Override
    public int hashCode() {
        int result = MedStaffFact_id != null ? MedStaffFact_id.hashCode() : 0;
        result = 31 * result + (TimeTableGraf_begTime != null ? TimeTableGraf_begTime.hashCode() : 0);
        return result;
    }

    public  Boolean sync(BusyDateEntity busyDateEntity){

        List<BusyDateEntity> busyDateEntities = new DaoImpl().getAllE("BusyDateEntity","isSync=false of isSync is null");
        for (BusyDateEntity b: busyDateEntities){
            if(b.equals(busyDateEntity)) return true;
        }
        return false;
    }
}