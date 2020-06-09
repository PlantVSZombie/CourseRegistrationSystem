package cn.edu.jlu.ccst.glzz.system.generated.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 表名：flow_control
 *
 * @author glzz
 */
@Table(name = "flow_control")
public class FlowControl {
    @Id
    @Column(name = "flow_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**  数据库字段：flow_id */
    private Integer flowId;

    /**  数据库字段：type */
    private String type;

    /**  数据库字段：name */
    private String name;

    /**  数据库字段：start_datetime */
    private Date startDatetime;

    /**  数据库字段：end_datetime */
    private Date endDatetime;

    /**  数据库字段：flow_control.flow_id */
    public void setFlowId(Integer flowId) {
        this.flowId = flowId;
    }

    /**  数据库字段：flow_control.flow_id */
    public Integer getFlowId() {
        return this.flowId;
    }

    /**  数据库字段：flow_control.type */
    public void setType(String type) {
        this.type = type;
    }

    /**  数据库字段：flow_control.type */
    public String getType() {
        return this.type;
    }

    /**  数据库字段：flow_control.name */
    public void setName(String name) {
        this.name = name;
    }

    /**  数据库字段：flow_control.name */
    public String getName() {
        return this.name;
    }

    /**  数据库字段：flow_control.start_datetime */
    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    /**  数据库字段：flow_control.start_datetime */
    public Date getStartDatetime() {
        return this.startDatetime;
    }

    /**  数据库字段：flow_control.end_datetime */
    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }

    /**  数据库字段：flow_control.end_datetime */
    public Date getEndDatetime() {
        return this.endDatetime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((flowId == null) ? 0 : flowId.hashCode());

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

        FlowControl other = (FlowControl) obj;

        if (flowId == null) {
            if (other.flowId != null) {
                return false;
            }
        } else if (!flowId.equals(other.flowId)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FlowControl [");
        sb.append("flowId=").append(flowId);
        sb.append(", ");
        sb.append("type=").append(type);
        sb.append(", ");
        sb.append("name=").append(name);
        sb.append(", ");
        sb.append("startDatetime=").append(startDatetime);
        sb.append(", ");
        sb.append("endDatetime=").append(endDatetime);
        sb.append("]");

        return sb.toString();
    }
}
