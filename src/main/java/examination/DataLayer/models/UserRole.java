package examination.DataLayer.models;

/**
 * author: a.savanovich
 * Date: 23.09.14
 * Time: 10:33
 * To change this template use File | Settings | File Templates.
 */
public class UserRole implements BaseModel {

    private Integer userRoleId;
    private String role;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public long getId() {
        return userRoleId;
    }

    //getter and setter methods
}