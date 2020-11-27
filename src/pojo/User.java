package pojo;
/**
 * 和数据库一一对应的java对象，实体对象
 *
 */
public class User {
    private String id;
    private String userName;
    private String userPassword;
    private String userRealName;
    private String userSex;
    private String userAge;
    private String userTelephone;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public String getUserRealName() {
        return userRealName;
    }
    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }
    public String getUserSex() {
        return userSex;
    }
    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }
    public String getUserAge() {
        return userAge;
    }
    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userRealName='" + userRealName + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userAge='" + userAge + '\'' +
                ", userTelephone='" + userTelephone + '\'' +
                '}';
    }
}
