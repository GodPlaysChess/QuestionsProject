package examination.DataLayer.dao;

import examination.DataLayer.models.BaseModel;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


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

        List<T> models = dao.selectList(0, 1);
        assertNotNull(models);
        model = models.get(0);
        assertNotNull(model);


        /* delete exam */
        dao.delete(id);
        assertNull(dao.selectById(id));
    }
}
