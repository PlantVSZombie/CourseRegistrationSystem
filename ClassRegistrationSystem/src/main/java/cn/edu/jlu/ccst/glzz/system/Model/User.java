package cn.edu.jlu.ccst.glzz.system.Model;

public class User {
    UserType userType;
    Object person;

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Object getPerson() {
        return person;
    }

    public void setPerson(Object person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "User{" +
                "userType=" + userType +
                ", person=" + person +
                '}';
    }
}
