package examination.DataLayer.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

/**
 * author: a.savanovich
 * Date: 23.09.14
 * Time: 8:57
 * To change this template use File | Settings | File Templates.
 */
public class User implements BaseModel {
    private String username;
    private String password;
    private long id;

    private Set<UserRole> userRole = new HashSet<UserRole>(0);

    List<SimpleGrantedAuthority> newAuthorities = new ArrayList<SimpleGrantedAuthority>();

    public List<SimpleGrantedAuthority> getAuthorities() {
        return newAuthorities;
    }

    public void setAuthorities() {
        for (UserRole role : userRole) {
            newAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<UserRole> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }
}
