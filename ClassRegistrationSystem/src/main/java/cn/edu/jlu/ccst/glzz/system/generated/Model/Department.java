package cn.edu.jlu.ccst.glzz.system.generated.Model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 表名：department
 *
 * @author glzz
 */
@Table(name = "department")
public class Department {
    @Id
    @Column(name = "dept_name")
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**  数据库字段：dept_name */
    private String deptName;

    /**  数据库字段：building */
    private String building;

    /**  数据库字段：department.dept_name */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**  数据库字段：department.dept_name */
    public String getDeptName() {
        return this.deptName;
    }

    /**  数据库字段：department.building */
    public void setBuilding(String building) {
        this.building = building;
    }

    /**  数据库字段：department.building */
    public String getBuilding() {
        return this.building;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) +
            ((deptName == null) ? 0 : deptName.hashCode());

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

        Department other = (Department) obj;

        if (deptName == null) {
            if (other.deptName != null) {
                return false;
            }
        } else if (!deptName.equals(other.deptName)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Department [");
        sb.append("deptName=").append(deptName);
        sb.append(", ");
        sb.append("building=").append(building);
        sb.append("]");

        return sb.toString();
    }
}
