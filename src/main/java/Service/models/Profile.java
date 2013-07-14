package Service.models;

/**
 * author: a.savanovich
 * Date: 14.07.13
 * Time: 15:03
 */
public class Profile implements BaseModel {
    private long id;
    private String name;
    private String lastName;
    private Role role;

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
