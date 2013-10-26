package examination.DataLayer.dao;


import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.annotation.PostConstruct;


public abstract class BaseDAOImpl {

    protected SessionFactory factory;
    protected static final Logger log = Logger.getLogger(QuestionDAOImpl.class);

    @PostConstruct
    private void createFactory() {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            log.error("Failed to create session factory: ", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

}
