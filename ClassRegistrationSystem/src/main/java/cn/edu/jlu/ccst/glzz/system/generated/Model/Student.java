package cn.edu.jlu.ccst.glzz.system.generated.Model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 表名：student
 *
 * @author glzz
 */
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**  数据库字段：student_id */
    private String studentId;

    /**  数据库字段：password */
    private String password;

    /**  数据库字段：name */
    private String name;

    /**  数据库字段：dept_name */
    private String deptName;

    /**  数据库字段：tot_cred */
    private BigDecimal totCred;

    /**  数据库字段：student.student_id */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**  数据库字段：student.student_id */
    public String getStudentId() {
        return this.studentId;
    }

    /**  数据库字段：student.password */
    public void setPassword(String password) {
        this.password = password;
    }

    /**  数据库字段：student.password */
    public String getPassword() {
        return this.password;
    }

    /**  数据库字段：student.name */
    public void setName(String name) {
        this.name = name;
    }

    /**  数据库字段：student.name */
    public String getName() {
        return this.name;
    }

    /**  数据库字段：student.dept_name */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**  数据库字段：student.dept_name */
    public String getDeptName() {
        return this.deptName;
    }

    /**  数据库字段：student.tot_cred */
    public void setTotCred(BigDecimal totCred) {
        this.totCred = totCred;
    }

    /**  数据库字段：student.tot_cred */
    public BigDecimal getTotCred() {
        return this.totCred;
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

        Student other = (Student) obj;

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
        sb.append("Student [");
        sb.append("studentId=").append(studentId);
        sb.append(", ");
        sb.append("password=").append(password);
        sb.append(", ");
        sb.append("name=").append(name);
        sb.append(", ");
        sb.append("deptName=").append(deptName);
        sb.append(", ");
        sb.append("totCred=").append(totCred);
        sb.append("]");

        return sb.toString();
    }
}
