package cn.edu.jlu.ccst.glzz.system.Model;

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
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**  数据库字段：ID */
    private String id;

    /**  数据库字段：name */
    private String name;

    /**  数据库字段：dept_name */
    private String deptName;

    /**  数据库字段：tot_cred */
    private BigDecimal totCred;

    /**  数据库字段：student.ID */
    public void setId(String id) {
        this.id = id;
    }

    /**  数据库字段：student.ID */
    public String getId() {
        return this.id;
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

        Student other = (Student) obj;

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
        sb.append("Student [");
        sb.append("id=").append(id);
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
