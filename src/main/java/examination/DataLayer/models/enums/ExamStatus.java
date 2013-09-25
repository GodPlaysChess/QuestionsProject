package examination.DataLayer.models.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ExamStatus {

    NOT_CHECKED(0), CHECKED(1);
    private int code;

    private ExamStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    private static final Map<Integer, ExamStatus> lookup = new HashMap<Integer, ExamStatus>();

    static {
        for (ExamStatus e : EnumSet.allOf(ExamStatus.class)) {
            lookup.put(e.getCode(), e);
        }
    }

    public static ExamStatus getByValue(int code) {
        return lookup.get(code);
    }
}
