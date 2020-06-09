package cn.edu.jlu.ccst.glzz.system.generated.Model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 表名：admin
 *
 * @author glzz
 */
@Table(name = "admin")
public class Admin {
    @Id
    @Column(name = "admin_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**  数据库字段：admin_id */
    private String adminId;

    /**  数据库字段：password */
    private String password;

    /**  数据库字段：name */
    private String name;

    /**  数据库字段：admin.admin_id */
    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    /**  数据库字段：admin.admin_id */
    public String getAdminId() {
        return this.adminId;
    }

    /**  数据库字段：admin.password */
    public void setPassword(String password) {
        this.password = password;
    }

    /**  数据库字段：admin.password */
    public String getPassword() {
        return this.password;
    }

    /**  数据库字段：admin.name */
    public void setName(String name) {
        this.name = name;
    }

    /**  数据库字段：admin.name */
    public String getName() {
        return this.name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) +
            ((adminId == null) ? 0 : adminId.hashCode());

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

        Admin other = (Admin) obj;

        if (adminId == null) {
            if (other.adminId != null) {
                return false;
            }
        } else if (!adminId.equals(other.adminId)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Admin [");
        sb.append("adminId=").append(adminId);
        sb.append(", ");
        sb.append("password=").append(password);
        sb.append(", ");
        sb.append("name=").append(name);
        sb.append("]");

        return sb.toString();
    }
}
