package examination.DataLayer.dao.mybatis;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Base object for all DAO
 *
 */
public abstract class BaseDAO<T> {

    private SqlSessionFactory sqlSessionFactory;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * This method does not use transaction scope for selecting result.
     * <b>It is used mostly for working with sequences dao as a workaround for http://code.google.com/p/mybatis/issues/detail?id=126</b>.
     */
    protected T selectOneDirect(String statementName, Object parameter) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            return sqlSession.selectOne(statementName, parameter);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * Shortcut for selecting object (opens session, selects object and commit transaction).
     */
    protected T selectOne(String statementName, Object parameter) {
        TransactionScope transactionScope = TransactionScope.createOrGetCurrent();
        try {
            SqlSession session = transactionScope.getSqlSession(sqlSessionFactory);
            T object = session.selectOne(statementName, parameter);

            transactionScope.commit();
            return object;
        } finally {
            transactionScope.close();
        }
    }

    protected Object selectUnique(String statement, Object param) {
        TransactionScope transactionScope = TransactionScope.createOrGetCurrent();
        try {
            SqlSession session = transactionScope.getSqlSession(sqlSessionFactory);
            Object object = session.selectOne(statement, param);

            transactionScope.commit();
            return object;
        } finally {
            transactionScope.close();
        }
    }

    /**
     * Shortcut for selecting list of objects (opens session, selects list, commits and closes transaction).
     */
    protected List<T> selectList(String statementName, Object parameter) {
        TransactionScope transactionScope = TransactionScope.createOrGetCurrent();
        try {
            SqlSession session = transactionScope.getSqlSession(sqlSessionFactory);
            List<T> list = session.selectList(statementName, parameter);

            transactionScope.commit();
            return list;
        } finally {
            transactionScope.close();
        }
    }

    /**
     * Shortcut for selecting list of objects (opens session, selects list, commits and closes transaction).
     */
    protected List<T> selectList(String statementName) {
        TransactionScope transactionScope = TransactionScope.createOrGetCurrent();
        try {
            SqlSession session = transactionScope.getSqlSession(sqlSessionFactory);
            List<T> list = session.selectList(statementName);

            transactionScope.commit();
            return list;
        } finally {
            transactionScope.close();
        }
    }

    /**
     * Shortcut for selecting list of objects with offset-limit.
     * <b>!!!Be aware: offset and limit logic is implemented using JDBC result set.
     * If you want faster offset-limit -- use database specific SQL queries.</b>
     */
    protected List<T> selectList(String statementName, Object parameter, RowBounds rowBounds) {
        TransactionScope transactionScope = TransactionScope.createOrGetCurrent();
        try {
            SqlSession session = transactionScope.getSqlSession(sqlSessionFactory);
            List<T> list = session.selectList(statementName, parameter, rowBounds);

            transactionScope.commit();
            return list;
        } finally {
            transactionScope.close();
        }
    }

    /**
     * Shortcut for inserting object (opens session, inserts object, commits transaction, closes session).
     */
    protected int insert(String statementName, Object parameter) {
        TransactionScope transactionScope = TransactionScope.createOrGetCurrent();
        try {
            SqlSession session = transactionScope.getSqlSession(sqlSessionFactory);
            int inserted = session.insert(statementName, parameter);

            transactionScope.commit();
            return inserted;
        } finally {
            transactionScope.close();
        }
    }

    /**
     * Shortcut for updating object (opens session, updates object, commits transaction, closes session)
     */
    protected int update(String statementName, Object parameter) {
        TransactionScope transactionScope = TransactionScope.createOrGetCurrent();
        try {
            SqlSession session = transactionScope.getSqlSession(sqlSessionFactory);
            int updated = session.update(statementName, parameter);

            transactionScope.commit();
            return updated;
        } finally {
            transactionScope.close();
        }
    }

    /**
     * Shortcut for deleting object (opens session, deletes object, commits transaction, closes session).
     */
    protected int delete(String statementName, Object parameter) {
        TransactionScope transactionScope = TransactionScope.createOrGetCurrent();
        try {
            SqlSession session = transactionScope.getSqlSession(sqlSessionFactory);
            int deleted = session.delete(statementName, parameter);

            transactionScope.commit();
            return deleted;
        } finally {
            transactionScope.close();
        }
    }

    protected Map<String, Object> getParameters(Map<String, Object> baseParams, Object... params) {
        Map<String, Object> parameters = new HashMap<String, Object>(baseParams);
        for (int i = 0; i < params.length; i += 2) {
            if (i + 1 < params.length) {
                parameters.put((String) params[i], params[i + 1]);
            }
        }
        return parameters;
    }

    protected Map<String, Object> getParameters(Object... params) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        for (int i = 0; i < params.length; i += 2) {
            if (i + 1 < params.length) {
                parameters.put((String) params[i], params[i + 1]);
            }
        }
        return parameters;
    }

    /**
     * Trying to insert in case when update affected no rows.
     */
    protected int updateOrInsert(String updateStatementName, Object updateParameter, String insertStatementName, Object insertParameter) {
        TransactionScope transactionScope = TransactionScope.createOrGetCurrent();
        try {
            SqlSession session = transactionScope.getSqlSession(sqlSessionFactory);
            int affected = session.update(updateStatementName, updateParameter);

            if (affected == 0) {
                affected = session.insert(insertStatementName, insertParameter);
            }

            transactionScope.commit();
            return affected;
        } finally {
            transactionScope.close();
        }
    }
}
