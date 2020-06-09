package cn.edu.jlu.ccst.glzz.system.generated.Model;

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
    @Column(name = "time_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**  数据库字段：time_id */
    private Integer timeId;

    /**  数据库字段：day */
    private Integer day;

    /**  数据库字段：start_time */
    private String startTime;

    /**  数据库字段：end_time */
    private String endTime;

    /**  数据库字段：time_slot.time_id */
    public void setTimeId(Integer timeId) {
        this.timeId = timeId;
    }

    /**  数据库字段：time_slot.time_id */
    public Integer getTimeId() {
        return this.timeId;
    }

    /**  数据库字段：time_slot.day */
    public void setDay(Integer day) {
        this.day = day;
    }

    /**  数据库字段：time_slot.day */
    public Integer getDay() {
        return this.day;
    }

    /**  数据库字段：time_slot.start_time */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**  数据库字段：time_slot.start_time */
    public String getStartTime() {
        return this.startTime;
    }

    /**  数据库字段：time_slot.end_time */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**  数据库字段：time_slot.end_time */
    public String getEndTime() {
        return this.endTime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((timeId == null) ? 0 : timeId.hashCode());

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

        if (timeId == null) {
            if (other.timeId != null) {
                return false;
            }
        } else if (!timeId.equals(other.timeId)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TimeSlot [");
        sb.append("timeId=").append(timeId);
        sb.append(", ");
        sb.append("day=").append(day);
        sb.append(", ");
        sb.append("startTime=").append(startTime);
        sb.append(", ");
        sb.append("endTime=").append(endTime);
        sb.append("]");

        return sb.toString();
    }
}
