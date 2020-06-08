package cn.edu.jlu.ccst.glzz.system.Model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 表名：classroom
 *
 * @author glzz
 */
@Table(name = "classroom")
public class Classroom {
    @Id
    @Column(name = "building")
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**  数据库字段：building */
    private String building;
    @Id
    @Column(name = "room_number")
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**  数据库字段：room_number */
    private String roomNumber;

    /**  数据库字段：capacity */
    private BigDecimal capacity;

    /**  数据库字段：classroom.building */
    public void setBuilding(String building) {
        this.building = building;
    }

    /**  数据库字段：classroom.building */
    public String getBuilding() {
        return this.building;
    }

    /**  数据库字段：classroom.room_number */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**  数据库字段：classroom.room_number */
    public String getRoomNumber() {
        return this.roomNumber;
    }

    /**  数据库字段：classroom.capacity */
    public void setCapacity(BigDecimal capacity) {
        this.capacity = capacity;
    }

    /**  数据库字段：classroom.capacity */
    public BigDecimal getCapacity() {
        return this.capacity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) +
            ((building == null) ? 0 : building.hashCode());

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

        Classroom other = (Classroom) obj;

        if (building == null) {
            if (other.building != null) {
                return false;
            }
        } else if (!building.equals(other.building)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Classroom [");
        sb.append("building=").append(building);
        sb.append(", ");
        sb.append("roomNumber=").append(roomNumber);
        sb.append(", ");
        sb.append("capacity=").append(capacity);
        sb.append("]");

        return sb.toString();
    }
}
