package Service.models;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Enum QuestionType
 * @author a.savanovich
 */
public enum QuestionType {

    SIMPLE(0), RADIOBUTTON(1), CHECKBOX(2);
    private int code;

    private QuestionType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
    private static final Map<Integer, QuestionType> lookup = new HashMap<Integer, QuestionType>();

    static {
        for (QuestionType s : EnumSet.allOf(QuestionType.class)) {
            lookup.put(s.getCode(), s);
        }
    }

    public static QuestionType getByValue(int code) {
        return lookup.get(code);
    }
}
