package examination.DataLayer.dao.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


/**
 * Base class for all commondb DAO
 *
 * @author ameshkov
 */
public abstract class BaseCommonDbDAO<T> extends BaseDAO<T> {

    public static final String OFFSET = "offset";
    public static final String LIMIT = "limit";
    public static final String PROFILE_IDS = "profileIds";
    public static final String CREDENTIAL_TYPE_CODE = "credentialTypeCode";
    public static final String PROFILE_ID = "profileId";
    public static final String CAMPAIGN_ID = "campaignId";
    public static final String USER_ID = "userId";
    public static final String DATE_FROM = "dateFrom";
    public static final String DATE_TO = "dateTo";
    public static final String CONTRACTOR_ID = "contractorId";
    public static final String CONTRACTOR_INN = "inn";

    @Autowired
    @Qualifier("commondbSqlSessionFactory")
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
}