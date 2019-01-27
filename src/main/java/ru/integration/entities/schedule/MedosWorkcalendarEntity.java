package ru.integration.entities.schedule;

import javax.persistence.*;


@Entity
@Table(name = "workcalendar", schema = "sqluser", catalog = "riams")
public class MedosWorkcalendarEntity {
    private Long id;
    private Boolean autogenerate;
    private Long afterdaysgenerate;

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
    @Column(name = "autogenerate")
    public Boolean getAutogenerate() {
        return autogenerate;
    }
    public void setAutogenerate(Boolean autogenerate) {
        this.autogenerate = autogenerate;
    }

    @Basic
    @Column(name = "afterdaysgenerate")
    public Long getAfterdaysgenerate() {
        return afterdaysgenerate;
    }

    public void setAfterdaysgenerate(Long afterdaysgenerate) {
        this.afterdaysgenerate = afterdaysgenerate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedosWorkcalendarEntity that = (MedosWorkcalendarEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (autogenerate != null ? !autogenerate.equals(that.autogenerate) : that.autogenerate != null) return false;
        if (afterdaysgenerate != null ? !afterdaysgenerate.equals(that.afterdaysgenerate) : that.afterdaysgenerate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (autogenerate != null ? autogenerate.hashCode() : 0);
        result = 31 * result + (afterdaysgenerate != null ? afterdaysgenerate.hashCode() : 0);
        return result;
    }
}
