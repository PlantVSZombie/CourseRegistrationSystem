package cn.edu.jlu.ccst.glzz.system.generated.Model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 表名：sec_time_place
 *
 * @author glzz
 */
@Table(name = "sec_time_place")
public class SecTimePlace {
    @Id
    @Column(name = "class_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**  数据库字段：class_id */
    private Integer classId;
    @Id
    @Column(name = "time_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**  数据库字段：time_id */
    private Integer timeId;
    @Id
    @Column(name = "classroom_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**  数据库字段：classroom_id */
    private Integer classroomId;

    /**  数据库字段：sec_time_place.class_id */
    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    /**  数据库字段：sec_time_place.class_id */
    public Integer getClassId() {
        return this.classId;
    }

    /**  数据库字段：sec_time_place.time_id */
    public void setTimeId(Integer timeId) {
        this.timeId = timeId;
    }

    /**  数据库字段：sec_time_place.time_id */
    public Integer getTimeId() {
        return this.timeId;
    }

    /**  数据库字段：sec_time_place.classroom_id */
    public void setClassroomId(Integer classroomId) {
        this.classroomId = classroomId;
    }

    /**  数据库字段：sec_time_place.classroom_id */
    public Integer getClassroomId() {
        return this.classroomId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) +
            ((classId == null) ? 0 : classId.hashCode());

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

        SecTimePlace other = (SecTimePlace) obj;

        if (classId == null) {
            if (other.classId != null) {
                return false;
            }
        } else if (!classId.equals(other.classId)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SecTimePlace [");
        sb.append("classId=").append(classId);
        sb.append(", ");
        sb.append("timeId=").append(timeId);
        sb.append(", ");
        sb.append("classroomId=").append(classroomId);
        sb.append("]");

        return sb.toString();
    }
}
