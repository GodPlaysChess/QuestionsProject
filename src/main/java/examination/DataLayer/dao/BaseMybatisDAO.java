package examination.DataLayer.dao;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: a.savanovich
 * Date: 25.09.14
 * Time: 10:49
 * To change this template use File | Settings | File Templates.
 */
public  abstract class BaseMybatisDAO <T>  {
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



    /**
     * Shortcut for selecting list of objects (opens session, selects list, commits and closes transaction).
     */
    protected List<T> selectList(String statementName, Object parameter, RowBounds rowBounds) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            List<T> list = sqlSession.selectList(statementName, parameter, rowBounds);
            return list;
        } finally {
            sqlSession.close();
        }
    }

    /**
     * Shortcut for inserting object (opens session, inserts object, commits transaction, closes session).
     */
    protected int insert(String statementName, Object parameter) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            int inserted = session.insert(statementName, parameter);
            return inserted;
        } finally {
            session.close();
        }
    }

    /**
     * Shortcut for updating object (opens session, updates object, commits transaction, closes session)
     */
    protected int update(String statementName, Object parameter) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            int inserted = session.update(statementName, parameter);
            return inserted;
        } finally {
            session.close();
        }
    }

    /**
     * Shortcut for deleting object (opens session, deletes object, commits transaction, closes session).
     */
    protected int delete(String statementName, Object parameter) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            int inserted = session.delete(statementName, parameter);
            return inserted;
        } finally {
            session.close();
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
}
