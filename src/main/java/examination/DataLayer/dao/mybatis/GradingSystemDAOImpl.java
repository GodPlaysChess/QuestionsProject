package examination.DataLayer.dao.mybatis;

import examination.DataLayer.models.GradingRaw;
import examination.DataLayer.models.GradingSystem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * author: a.savanovich
 * Date: 04.10.14
 * Time: 11:15
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class GradingSystemDAOImpl extends BaseCommonDbDAO<GradingRaw> implements GradingSystemDAO {
    @Override
    public void insert(GradingRaw address) {
        insert("insertGrading", address);
    }

    @Override
    public List<GradingRaw> selectList(int offset, int limit) {
        Map params = getParameters(
            OFFSET, offset,
            LIMIT, limit
        );
        return selectList("selectGradingRaws", params);
    }
}
