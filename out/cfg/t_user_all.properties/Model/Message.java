package cn.edu.jlu.ccst.glzz.system.generated.Model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 表名：message
 *
 * @author glzz
 */
@Table(name = "message")
public class Message {
    @Id
    @Column(name = "message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**  数据库字段：message_id */
    private Integer messageId;

    /**  数据库字段：user_id */
    private String userId;

    /**  数据库字段：type */
    private String type;

    /**  数据库字段：context */
    private String context;

    /**  数据库字段：hasread */
    private Integer hasread;

    /**  数据库字段：message.message_id */
    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    /**  数据库字段：message.message_id */
    public Integer getMessageId() {
        return this.messageId;
    }

    /**  数据库字段：message.user_id */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**  数据库字段：message.user_id */
    public String getUserId() {
        return this.userId;
    }

    /**  数据库字段：message.type */
    public void setType(String type) {
        this.type = type;
    }

    /**  数据库字段：message.type */
    public String getType() {
        return this.type;
    }

    /**  数据库字段：message.context */
    public void setContext(String context) {
        this.context = context;
    }

    /**  数据库字段：message.context */
    public String getContext() {
        return this.context;
    }

    /**  数据库字段：message.hasread */
    public void setHasread(Integer hasread) {
        this.hasread = hasread;
    }

    /**  数据库字段：message.hasread */
    public Integer getHasread() {
        return this.hasread;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) +
            ((messageId == null) ? 0 : messageId.hashCode());

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

        Message other = (Message) obj;

        if (messageId == null) {
            if (other.messageId != null) {
                return false;
            }
        } else if (!messageId.equals(other.messageId)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Message [");
        sb.append("messageId=").append(messageId);
        sb.append(", ");
        sb.append("userId=").append(userId);
        sb.append(", ");
        sb.append("type=").append(type);
        sb.append(", ");
        sb.append("context=").append(context);
        sb.append(", ");
        sb.append("hasread=").append(hasread);
        sb.append("]");

        return sb.toString();
    }
}
