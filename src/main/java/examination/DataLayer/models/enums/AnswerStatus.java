package examination.DataLayer.models.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum AnswerStatus {
    AUTOSAVE(0), APPROVED(1);
    private int code;

    private AnswerStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    private static final Map<Integer, AnswerStatus> lookup = new HashMap<Integer, AnswerStatus>();

    static {
        for (AnswerStatus s : EnumSet.allOf(AnswerStatus.class)) {
            lookup.put(s.getCode(), s);
        }
    }

    public static AnswerStatus getByValue(int code) {
        return lookup.get(code);
    }
}
