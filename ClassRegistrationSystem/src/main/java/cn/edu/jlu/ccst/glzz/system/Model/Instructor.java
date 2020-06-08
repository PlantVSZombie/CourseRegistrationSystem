package cn.edu.jlu.ccst.glzz.system.Model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 表名：instructor
 *
 * @author glzz
 */
@Table(name = "instructor")
public class Instructor {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**  数据库字段：ID */
    private String id;

    /**  数据库字段：name */
    private String name;

    /**  数据库字段：dept_name */
    private String deptName;

    /**  数据库字段：salary */
    private BigDecimal salary;

    /**  数据库字段：instructor.ID */
    public void setId(String id) {
        this.id = id;
    }

    /**  数据库字段：instructor.ID */
    public String getId() {
        return this.id;
    }

    /**  数据库字段：instructor.name */
    public void setName(String name) {
        this.name = name;
    }

    /**  数据库字段：instructor.name */
    public String getName() {
        return this.name;
    }

    /**  数据库字段：instructor.dept_name */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**  数据库字段：instructor.dept_name */
    public String getDeptName() {
        return this.deptName;
    }

    /**  数据库字段：instructor.salary */
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    /**  数据库字段：instructor.salary */
    public BigDecimal getSalary() {
        return this.salary;
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

        Instructor other = (Instructor) obj;

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
        sb.append("Instructor [");
        sb.append("id=").append(id);
        sb.append(", ");
        sb.append("name=").append(name);
        sb.append(", ");
        sb.append("deptName=").append(deptName);
        sb.append(", ");
        sb.append("salary=").append(salary);
        sb.append("]");

        return sb.toString();
    }
}
