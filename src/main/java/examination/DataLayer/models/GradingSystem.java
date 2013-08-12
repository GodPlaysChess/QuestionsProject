package examination.DataLayer.models;

import java.util.Map;

public class GradingSystem {
    private Map<Integer, Grade> map;

    public void setMap(Map<Integer, Grade> map) {
        this.map = map;
    }

    public Grade getGrade(int grade) {
        int key = 0;
        for(int currKey : map.keySet()) {
            if (currKey > grade) break;
            key = currKey;
        }
        return map.get(key);
    }
}
