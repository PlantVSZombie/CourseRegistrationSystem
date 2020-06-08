package cn.edu.jlu.ccst.glzz.system.Model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 表名：advisor
 *
 * @author glzz
 */
@Table(name = "advisor")
public class Advisor {
    @Id
    @Column(name = "s_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**  数据库字段：s_ID */
    private String s_ID;

    /**  数据库字段：i_ID */
    private String i_ID;

    /**  数据库字段：advisor.s_ID */
    public void setS_ID(String s_ID) {
        this.s_ID = s_ID;
    }

    /**  数据库字段：advisor.s_ID */
    public String getS_ID() {
        return this.s_ID;
    }

    /**  数据库字段：advisor.i_ID */
    public void setI_ID(String i_ID) {
        this.i_ID = i_ID;
    }

    /**  数据库字段：advisor.i_ID */
    public String getI_ID() {
        return this.i_ID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((s_ID == null) ? 0 : s_ID.hashCode());

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

        Advisor other = (Advisor) obj;

        if (s_ID == null) {
            if (other.s_ID != null) {
                return false;
            }
        } else if (!s_ID.equals(other.s_ID)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Advisor [");
        sb.append("s_ID=").append(s_ID);
        sb.append(", ");
        sb.append("i_ID=").append(i_ID);
        sb.append("]");

        return sb.toString();
    }
}
