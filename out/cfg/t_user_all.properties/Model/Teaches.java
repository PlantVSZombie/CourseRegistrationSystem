package cn.edu.jlu.ccst.glzz.system.Model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 表名：teaches
 *
 * @author glzz
 */
@Table(name = "teaches")
public class Teaches {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**  数据库字段：ID */
    private String id;
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

    /**  数据库字段：teaches.ID */
    public void setId(String id) {
        this.id = id;
    }

    /**  数据库字段：teaches.ID */
    public String getId() {
        return this.id;
    }

    /**  数据库字段：teaches.course_id */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    /**  数据库字段：teaches.course_id */
    public String getCourseId() {
        return this.courseId;
    }

    /**  数据库字段：teaches.sec_id */
    public void setSecId(String secId) {
        this.secId = secId;
    }

    /**  数据库字段：teaches.sec_id */
    public String getSecId() {
        return this.secId;
    }

    /**  数据库字段：teaches.semester */
    public void setSemester(String semester) {
        this.semester = semester;
    }

    /**  数据库字段：teaches.semester */
    public String getSemester() {
        return this.semester;
    }

    /**  数据库字段：teaches.year */
    public void setYear(BigDecimal year) {
        this.year = year;
    }

    /**  数据库字段：teaches.year */
    public BigDecimal getYear() {
        return this.year;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((id == null) ? 0 : id.hashCode());

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

        Teaches other = (Teaches) obj;

        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Teaches [");
        sb.append("id=").append(id);
        sb.append(", ");
        sb.append("courseId=").append(courseId);
        sb.append(", ");
        sb.append("secId=").append(secId);
        sb.append(", ");
        sb.append("semester=").append(semester);
        sb.append(", ");
        sb.append("year=").append(year);
        sb.append("]");

        return sb.toString();
    }
}
