package examination.DataLayer.dao.mybatis;

import org.apache.ibatis.session.LocalCacheScope;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;

import java.io.IOException;

/**
 * Custom sql session factory bean.
 * We need it to override mybatis configuration settings.
 */
public class DefaultSqlSessionFactoryBean extends SqlSessionFactoryBean {

    private boolean cacheEnabled = true;
    private LocalCacheScope localCacheScope = LocalCacheScope.SESSION;

    public void setCacheEnabled(boolean cacheEnabled) {
        this.cacheEnabled = cacheEnabled;
    }

    public void setLocalCacheScope(LocalCacheScope localCacheScope) {
        this.localCacheScope = localCacheScope;
    }

    @Override
    protected SqlSessionFactory buildSqlSessionFactory() throws IOException {
        SqlSessionFactory sqlSessionFactory = super.buildSqlSessionFactory();
        sqlSessionFactory.getConfiguration().setCacheEnabled(cacheEnabled);
        sqlSessionFactory.getConfiguration().setLocalCacheScope(localCacheScope);
        return sqlSessionFactory;
    }
}
