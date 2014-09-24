package examination.QuestionService;

import examination.DataLayer.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {


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

        return new User(domainUser.getUsername(),
            domainUser.getPassword(), enabled, accountNonExpired,
            credentialsNonExpired, accountNonLocked,
            domainUser.getAuthorities());
    }
}