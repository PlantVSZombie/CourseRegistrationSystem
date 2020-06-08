package cn.edu.jlu.ccst.glzz.system.Model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 表名：course
 *
 * @author glzz
 */
@Table(name = "course")
public class Course {
    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**  数据库字段：course_id */
    private String courseId;

    /**  数据库字段：title */
    private String title;

    /**  数据库字段：dept_name */
    private String deptName;

    /**  数据库字段：credits */
    private BigDecimal credits;

    /**  数据库字段：course.course_id */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    /**  数据库字段：course.course_id */
    public String getCourseId() {
        return this.courseId;
    }

    /**  数据库字段：course.title */
    public void setTitle(String title) {
        this.title = title;
    }

    /**  数据库字段：course.title */
    public String getTitle() {
        return this.title;
    }

    /**  数据库字段：course.dept_name */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**  数据库字段：course.dept_name */
    public String getDeptName() {
        return this.deptName;
    }

    /**  数据库字段：course.credits */
    public void setCredits(BigDecimal credits) {
        this.credits = credits;
    }

    /**  数据库字段：course.credits */
    public BigDecimal getCredits() {
        return this.credits;
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

        Course other = (Course) obj;

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
        sb.append("Course [");
        sb.append("courseId=").append(courseId);
        sb.append(", ");
        sb.append("title=").append(title);
        sb.append(", ");
        sb.append("deptName=").append(deptName);
        sb.append(", ");
        sb.append("credits=").append(credits);
        sb.append("]");

        return sb.toString();
    }
}
