package examination.DataLayer.models;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private Set<String> userRoleString = new HashSet<String>(0);

    public Set<String> getUserRoleString() {
        return userRoleString;
    }

    public void setUserRoleString(Set<String> userRoleString) {
        this.userRoleString = userRoleString;
    }

    List<SimpleGrantedAuthority> newAuthorities = new ArrayList<SimpleGrantedAuthority>();

    public List<SimpleGrantedAuthority> getAuthorities() {
        return newAuthorities;
    }

    public void setAuthorities() {
        for (String role : userRoleString) {
            newAuthorities.add(new SimpleGrantedAuthority(role));
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
}
