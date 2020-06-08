package cn.edu.jlu.ccst.glzz.system.Model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 表名：section
 *
 * @author glzz
 */
@Table(name = "section")
public class Section {
    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**  数据库字段：course_id */
    private String courseId;
    @Id
    @Column(name = "sec_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**  数据库字段：sec_id */
    private String secId;
    @Id
    @Column(name = "semester")
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**  数据库字段：semester */
    private String semester;
    @Id
    @Column(name = "year")
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**  数据库字段：year */
    private BigDecimal year;

    /**  数据库字段：building */
    private String building;

    /**  数据库字段：room_number */
    private String roomNumber;

    /**  数据库字段：time_slot_id */
    private String timeSlotId;

    /**  数据库字段：section.course_id */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    /**  数据库字段：section.course_id */
    public String getCourseId() {
        return this.courseId;
    }

    /**  数据库字段：section.sec_id */
    public void setSecId(String secId) {
        this.secId = secId;
    }

    /**  数据库字段：section.sec_id */
    public String getSecId() {
        return this.secId;
    }

    /**  数据库字段：section.semester */
    public void setSemester(String semester) {
        this.semester = semester;
    }

    /**  数据库字段：section.semester */
    public String getSemester() {
        return this.semester;
    }

    /**  数据库字段：section.year */
    public void setYear(BigDecimal year) {
        this.year = year;
    }

    /**  数据库字段：section.year */
    public BigDecimal getYear() {
        return this.year;
    }

    /**  数据库字段：section.building */
    public void setBuilding(String building) {
        this.building = building;
    }

    /**  数据库字段：section.building */
    public String getBuilding() {
        return this.building;
    }

    /**  数据库字段：section.room_number */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**  数据库字段：section.room_number */
    public String getRoomNumber() {
        return this.roomNumber;
    }

    /**  数据库字段：section.time_slot_id */
    public void setTimeSlotId(String timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    /**  数据库字段：section.time_slot_id */
    public String getTimeSlotId() {
        return this.timeSlotId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) +
            ((courseId == null) ? 0 : courseId.hashCode());

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

        Section other = (Section) obj;

        if (courseId == null) {
            if (other.courseId != null) {
                return false;
            }
        } else if (!courseId.equals(other.courseId)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Section [");
        sb.append("courseId=").append(courseId);
        sb.append(", ");
        sb.append("secId=").append(secId);
        sb.append(", ");
        sb.append("semester=").append(semester);
        sb.append(", ");
        sb.append("year=").append(year);
        sb.append(", ");
        sb.append("building=").append(building);
        sb.append(", ");
        sb.append("roomNumber=").append(roomNumber);
        sb.append(", ");
        sb.append("timeSlotId=").append(timeSlotId);
        sb.append("]");

        return sb.toString();
    }
}
