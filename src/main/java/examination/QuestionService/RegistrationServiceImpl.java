package examination.QuestionService;

import examination.DataLayer.dao.UserDAO;
import examination.DataLayer.dao.UserRoleDAO;
import examination.DataLayer.models.User;
import examination.DataLayer.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * author: a.savanovich
 * Date: 23.09.14
 * Time: 8:53
 * To change this template use File | Settings | File Templates.
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    private UserDAO usersDAO;
    @Autowired
    private UserRoleDAO userRoleDAO;
    @Override
    public void register(String username, String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        Set<UserRole> roles = new HashSet<>(1);
        UserRole role = new UserRole();
        role.setRole("USER_ROLE");
        userRoleDAO.insert(role);
        roles.add(role);
        user.setUserRole(roles);
        usersDAO.insert(user);

    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword) {

        User user = usersDAO.selectByName(username);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
             throw new RuntimeException("wrong password");
        }
        String hashedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(hashedPassword);
        usersDAO.update(user);

    }
}
