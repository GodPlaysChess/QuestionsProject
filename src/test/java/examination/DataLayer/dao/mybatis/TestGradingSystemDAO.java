package examination.DataLayer.dao.mybatis;

import examination.DataLayer.dao.AbstractTest;
import examination.DataLayer.models.GradingRaw;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestGradingSystemDAO extends AbstractTest<GradingRaw> {

    @Autowired
    private GradingSystemDAO gradingSystemDAO;
    @Test
    public void testInsert() throws Exception {
        GradingRaw gradingRaw = new GradingRaw();
        gradingRaw.setSystemId(1);
        gradingRaw.setPercent(20);
        gradingRaw.setMarkCode(4);
        gradingSystemDAO.insert(gradingRaw);

    }
}