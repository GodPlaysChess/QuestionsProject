package examination;

import examination.DataLayer.models.BaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * author: a.savanovich
 * Date: 15.07.13
 * Time: 16:46
 * To change this template use File | Settings | File Templates.
 */
public class BaseModelUtils {
    public static List<Long> createIdsList(List<?extends BaseModel> in) {
        if (in == null) return null;
        List<Long> out = new ArrayList<Long>(in.size());
        for (BaseModel model : in) {
            out.add(model.getId());
        }
        return out;
    }
}
