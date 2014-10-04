package examination.DataLayer.dao.mybatis;

import examination.DataLayer.models.GradingRaw;

import java.util.List;

/**
 * author: a.savanovich
 * Date: 04.10.14
 * Time: 11:15
 * To change this template use File | Settings | File Templates.
 */
public interface GradingSystemDAO {
    void insert(GradingRaw address);
    List<GradingRaw> selectList(int offset, int limit);
}
