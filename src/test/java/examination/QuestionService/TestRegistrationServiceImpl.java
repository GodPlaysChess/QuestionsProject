package examination.QuestionService;

import examination.DataLayer.dao.AbstractTest;
import examination.DataLayer.dao.UserDAO;
import examination.DataLayer.models.User;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class TestRegistrationServiceImpl extends AbstractTest<User> {

    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private UserDAO userDAO;
    @Ignore
    @Test
    public void testRegister() throws Exception {
        try {
            registrationService.register("sas4", "123");
        }catch (Exception ex) {

        }
        User user = userDAO.selectByName("sas4");
        userDAO.delete(user.getId());
        assertEquals(1, user.getUserRole().size());

    }

    public void testChangePassword() throws Exception {

    }
}