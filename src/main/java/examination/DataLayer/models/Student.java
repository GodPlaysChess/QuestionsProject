package examination.DataLayer.models;

/**
 * author: a.savanovich
 * Date: 14.07.13
 * Time: 15:06
 */
public class Student extends Profile {
    @Override
    public Role getRole() {
        return Role.STUDENT;
    }
}
