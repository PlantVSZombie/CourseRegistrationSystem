package cn.edu.jlu.ccst.glzz.system.generated.Model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 表名：prereq
 *
 * @author glzz
 */
@Table(name = "prereq")
public class Prereq {
    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**  数据库字段：course_id */
    private String courseId;
    @Id
    @Column(name = "prereq_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**  数据库字段：prereq_id */
    private String prereqId;

    /**  数据库字段：prereq.course_id */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    /**  数据库字段：prereq.course_id */
    public String getCourseId() {
        return this.courseId;
    }

    /**  数据库字段：prereq.prereq_id */
    public void setPrereqId(String prereqId) {
        this.prereqId = prereqId;
    }

    /**  数据库字段：prereq.prereq_id */
    public String getPrereqId() {
        return this.prereqId;
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

        Prereq other = (Prereq) obj;

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
        sb.append("Prereq [");
        sb.append("courseId=").append(courseId);
        sb.append(", ");
        sb.append("prereqId=").append(prereqId);
        sb.append("]");

        return sb.toString();
    }
}
