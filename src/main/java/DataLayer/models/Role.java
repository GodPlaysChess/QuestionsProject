package DataLayer.models;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * author: a.savanovich
 * Date: 14.07.13
 * Time: 14:59
 * To change this template use File | Settings | File Templates.
 */
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
