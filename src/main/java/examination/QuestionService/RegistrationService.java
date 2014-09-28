package examination.QuestionService;

/**
 * author: a.savanovich
 * Date: 23.09.14
 * Time: 8:51
 */
public interface RegistrationService {
    void register(String username, String password);

    void changePassword(String username, String oldPassword, String newPassword);

}
