package examination.DataLayer.models.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


public enum Mark {
    TRUE(1), FAlSE(2), UNDEFINED(0);
    private int code;

    private Mark(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
    private static final Map<Integer, Mark> lookup = new HashMap<Integer, Mark>();

    static {
        for (Mark s : EnumSet.allOf(Mark.class)) {
            lookup.put(s.getCode(), s);
        }
    }

    public static Mark getByValue(int code) {
        return lookup.get(code);
    }
}
