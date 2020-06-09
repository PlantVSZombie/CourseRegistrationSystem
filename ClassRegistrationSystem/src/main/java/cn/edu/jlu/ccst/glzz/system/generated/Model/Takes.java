package cn.edu.jlu.ccst.glzz.system.generated.Model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 表名：takes
 *
 * @author glzz
 */
@Table(name = "takes")
public class Takes {
    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**  数据库字段：student_id */
    private String studentId;
    @Id
    @Column(name = "class_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**  数据库字段：class_id */
    private Integer classId;

    /**  数据库字段：status */
    private String status;

    /**  数据库字段：ismajor */
    private Integer ismajor;

    /**  数据库字段：grade */
    private String grade;

    /**  数据库字段：takes.student_id */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**  数据库字段：takes.student_id */
    public String getStudentId() {
        return this.studentId;
    }

    /**  数据库字段：takes.class_id */
    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    /**  数据库字段：takes.class_id */
    public Integer getClassId() {
        return this.classId;
    }

    /**  数据库字段：takes.status */
    public void setStatus(String status) {
        this.status = status;
    }

    /**  数据库字段：takes.status */
    public String getStatus() {
        return this.status;
    }

    /**  数据库字段：takes.ismajor */
    public void setIsmajor(Integer ismajor) {
        this.ismajor = ismajor;
    }

    /**  数据库字段：takes.ismajor */
    public Integer getIsmajor() {
        return this.ismajor;
    }

    /**  数据库字段：takes.grade */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**  数据库字段：takes.grade */
    public String getGrade() {
        return this.grade;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) +
            ((studentId == null) ? 0 : studentId.hashCode());

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

        Takes other = (Takes) obj;

        if (studentId == null) {
            if (other.studentId != null) {
                return false;
            }
        } else if (!studentId.equals(other.studentId)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Takes [");
        sb.append("studentId=").append(studentId);
        sb.append(", ");
        sb.append("classId=").append(classId);
        sb.append(", ");
        sb.append("status=").append(status);
        sb.append(", ");
        sb.append("ismajor=").append(ismajor);
        sb.append(", ");
        sb.append("grade=").append(grade);
        sb.append("]");

        return sb.toString();
    }
}
