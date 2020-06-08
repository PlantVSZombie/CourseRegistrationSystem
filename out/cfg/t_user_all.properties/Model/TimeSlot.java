package cn.edu.jlu.ccst.glzz.system.Model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 表名：time_slot
 *
 * @author glzz
 */
@Table(name = "time_slot")
public class TimeSlot {
    @Id
    @Column(name = "time_slot_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**  数据库字段：time_slot_id */
    private String timeSlotId;
    @Id
    @Column(name = "day")
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**  数据库字段：day */
    private String day;
    @Id
    @Column(name = "start_hr")
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**  数据库字段：start_hr */
    private BigDecimal startHr;
    @Id
    @Column(name = "start_min")
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**  数据库字段：start_min */
    private BigDecimal startMin;

    /**  数据库字段：end_hr */
    private BigDecimal endHr;

    /**  数据库字段：end_min */
    private BigDecimal endMin;

    /**  数据库字段：time_slot.time_slot_id */
    public void setTimeSlotId(String timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    /**  数据库字段：time_slot.time_slot_id */
    public String getTimeSlotId() {
        return this.timeSlotId;
    }

    /**  数据库字段：time_slot.day */
    public void setDay(String day) {
        this.day = day;
    }

    /**  数据库字段：time_slot.day */
    public String getDay() {
        return this.day;
    }

    /**  数据库字段：time_slot.start_hr */
    public void setStartHr(BigDecimal startHr) {
        this.startHr = startHr;
    }

    /**  数据库字段：time_slot.start_hr */
    public BigDecimal getStartHr() {
        return this.startHr;
    }

    /**  数据库字段：time_slot.start_min */
    public void setStartMin(BigDecimal startMin) {
        this.startMin = startMin;
    }

    /**  数据库字段：time_slot.start_min */
    public BigDecimal getStartMin() {
        return this.startMin;
    }

    /**  数据库字段：time_slot.end_hr */
    public void setEndHr(BigDecimal endHr) {
        this.endHr = endHr;
    }

    /**  数据库字段：time_slot.end_hr */
    public BigDecimal getEndHr() {
        return this.endHr;
    }

    /**  数据库字段：time_slot.end_min */
    public void setEndMin(BigDecimal endMin) {
        this.endMin = endMin;
    }

    /**  数据库字段：time_slot.end_min */
    public BigDecimal getEndMin() {
        return this.endMin;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) +
            ((timeSlotId == null) ? 0 : timeSlotId.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        TimeSlot other = (TimeSlot) obj;

        if (timeSlotId == null) {
            if (other.timeSlotId != null) {
                return false;
            }
        } else if (!timeSlotId.equals(other.timeSlotId)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TimeSlot [");
        sb.append("timeSlotId=").append(timeSlotId);
        sb.append(", ");
        sb.append("day=").append(day);
        sb.append(", ");
        sb.append("startHr=").append(startHr);
        sb.append(", ");
        sb.append("startMin=").append(startMin);
        sb.append(", ");
        sb.append("endHr=").append(endHr);
        sb.append(", ");
        sb.append("endMin=").append(endMin);
        sb.append("]");

        return sb.toString();
    }
}
