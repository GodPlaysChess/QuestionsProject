package Service.models;

import java.util.Map;

/**
 * author: a.savanovich
 * Date: 15.07.13
 * Time: 19:43
 * To change this template use File | Settings | File Templates.
 */
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
