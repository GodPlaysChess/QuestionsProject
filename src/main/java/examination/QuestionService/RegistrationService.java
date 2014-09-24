package examination.QuestionService;

/**
 * author: a.savanovich
 * Date: 23.09.14
 * Time: 8:51
 * To change this template use File | Settings | File Templates.
 */
public interface RegistrationService {
    void register(String username, String password);

    void changePassword(String username, String password);
}
