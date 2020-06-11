package cn.edu.jlu.ccst.glzz.system.generated.Model;

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
    @Column(name = "class_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**  数据库字段：class_id */
    private Integer classId;

    /**  数据库字段：course_id */
    private String courseId;

    /**  数据库字段：sec_id */
    private Integer secId;

    /**  数据库字段：semester */
    private String semester;

    /**  数据库字段：year */
    private Integer year;

    /**  数据库字段：sec_capacity */
    private Integer secCapacity;

    /**  数据库字段：section.class_id */
    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    /**  数据库字段：section.class_id */
    public Integer getClassId() {
        return this.classId;
    }

    /**  数据库字段：section.course_id */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    /**  数据库字段：section.course_id */
    public String getCourseId() {
        return this.courseId;
    }

    /**  数据库字段：section.sec_id */
    public void setSecId(Integer secId) {
        this.secId = secId;
    }

    /**  数据库字段：section.sec_id */
    public Integer getSecId() {
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
    public void setYear(Integer year) {
        this.year = year;
    }

    /**  数据库字段：section.year */
    public Integer getYear() {
        return this.year;
    }

    /**  数据库字段：section.sec_capacity */
    public void setSecCapacity(Integer secCapacity) {
        this.secCapacity = secCapacity;
    }

    /**  数据库字段：section.sec_capacity */
    public Integer getSecCapacity() {
        return this.secCapacity;
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

        Section other = (Section) obj;

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
        sb.append("Section [");
        sb.append("classId=").append(classId);
        sb.append(", ");
        sb.append("courseId=").append(courseId);
        sb.append(", ");
        sb.append("secId=").append(secId);
        sb.append(", ");
        sb.append("semester=").append(semester);
        sb.append(", ");
        sb.append("year=").append(year);
        sb.append(", ");
        sb.append("secCapacity=").append(secCapacity);
        sb.append("]");

        return sb.toString();
    }
}
