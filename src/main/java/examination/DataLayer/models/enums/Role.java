package examination.DataLayer.models.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Role {
    STUDENT(10), TEACHER(20), ADMIN(30);
    private int code;

    private Role(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
    private static final Map<Integer, Role> lookup = new HashMap<Integer, Role>();

    static {
        for (Role s : EnumSet.allOf(Role.class)) {
            lookup.put(s.getCode(), s);
        }
    }

    public static Role getByValue(int code) {
        return lookup.get(code);
    }

}
