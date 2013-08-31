package examination.DataLayer.dao;

import examination.DataLayer.models.BaseModel;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * author: a.savanovich
 * Date: 01.09.13
 * Time: 0:37
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring/applicationContext.xml"})
public class AbstractTest <T extends BaseModel> {

    protected void baseCheck(T model, BaseDAO <T> dao) {
        boolean inserted = dao.insert(model);
        assertTrue(model.getId() > 0);
        assertTrue(inserted);

        long id = model.getId();

        /* get exam */
        model = dao.selectById(id);
        assertNotNull(model);

        /* delete exam */
        dao.delete(id);
        assertNull(dao.selectById(id));
    }
}
