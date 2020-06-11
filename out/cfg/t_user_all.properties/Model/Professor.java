package cn.edu.jlu.ccst.glzz.system.generated.Model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 表名：professor
 *
 * @author glzz
 */
@Table(name = "professor")
public class Professor {
    @Id
    @Column(name = "professor_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**  数据库字段：professor_id */
    private String professorId;

    /**  数据库字段：password */
    private String password;

    /**  数据库字段：professor_name */
    private String professorName;

    /**  数据库字段：dept_name */
    private String deptName;

    /**  数据库字段：professor.professor_id */
    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    /**  数据库字段：professor.professor_id */
    public String getProfessorId() {
        return this.professorId;
    }

    /**  数据库字段：professor.password */
    public void setPassword(String password) {
        this.password = password;
    }

    /**  数据库字段：professor.password */
    public String getPassword() {
        return this.password;
    }

    /**  数据库字段：professor.professor_name */
    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    /**  数据库字段：professor.professor_name */
    public String getProfessorName() {
        return this.professorName;
    }

    /**  数据库字段：professor.dept_name */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**  数据库字段：professor.dept_name */
    public String getDeptName() {
        return this.deptName;
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

        Professor other = (Professor) obj;

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
        sb.append("Professor [");
        sb.append("professorId=").append(professorId);
        sb.append(", ");
        sb.append("password=").append(password);
        sb.append(", ");
        sb.append("professorName=").append(professorName);
        sb.append(", ");
        sb.append("deptName=").append(deptName);
        sb.append("]");

        return sb.toString();
    }
}
