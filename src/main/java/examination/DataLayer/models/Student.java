package examination.DataLayer.models;

import examination.DataLayer.models.enums.Role;

public class Student extends Profile {
    @Override
    public Role getRole() {
        return Role.STUDENT;
    }
}
