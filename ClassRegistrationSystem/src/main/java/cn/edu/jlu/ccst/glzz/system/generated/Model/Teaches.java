package cn.edu.jlu.ccst.glzz.system.generated.Model;

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
    @Column(name = "professor_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    /** 老师id, 数据库字段：professor_id */
    private String professorId;
    @Id
    @Column(name = "class_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**  数据库字段：class_id */
    private Integer classId;

    /** 设置老师id, 数据库字段：teaches.professor_id */
    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    /** 获取老师id, 数据库字段：teaches.professor_id */
    public String getProfessorId() {
        return this.professorId;
    }

    /**  数据库字段：teaches.class_id */
    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    /**  数据库字段：teaches.class_id */
    public Integer getClassId() {
        return this.classId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) +
            ((professorId == null) ? 0 : professorId.hashCode());

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

        if (professorId == null) {
            if (other.professorId != null) {
                return false;
            }
        } else if (!professorId.equals(other.professorId)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Teaches [");
        sb.append("professorId=").append(professorId);
        sb.append(", ");
        sb.append("classId=").append(classId);
        sb.append("]");

        return sb.toString();
    }
}
