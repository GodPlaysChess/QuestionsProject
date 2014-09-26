package examination.QuestionService;

import examination.DataLayer.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

    public static class CustomUser extends User {
        private long userId;
        public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, long userId) {
            super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
            this.userId = userId;
        }

        public long getUserId() {
            return userId;
        }
    };

    @Autowired
    UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String userName)
        throws UsernameNotFoundException {

        examination.DataLayer.models.User domainUser = userDAO.selectByName(userName);
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return new CustomUser(domainUser.getUsername(),
            domainUser.getPassword(), enabled, accountNonExpired,
            credentialsNonExpired, accountNonLocked,
            domainUser.getAuthorities(), domainUser.getId());
    }
}