package examination.DataLayer.dao.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Transaction scope. Current transaction
 *
 * @author ameshkov
 */
public class TransactionScope {
    //https://planeta.atlassian.net/browse/PLANETA-11285
    private static final int MAX_ACTIVE_TRANSACTIONS_PER_APPLICATION = 100;
    private static final Semaphore semaphore = new Semaphore(MAX_ACTIVE_TRANSACTIONS_PER_APPLICATION, true);
	private static final ThreadLocal<TransactionScope> current = new ThreadLocal<TransactionScope>();

	/**
	 * Returns current transaction scope and increments nesting level.
	 * If no current transaction - creates new.
	 */
	public static TransactionScope createOrGetCurrent() {

		TransactionScope transactionScope = current.get();

		if (transactionScope == null) {

            try {
                semaphore.tryAcquire(1, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            transactionScope = new TransactionScope();
			current.set(transactionScope);
		} else {
			transactionScope.nestingLevel++;
		}

		return transactionScope;
	}
	private int nestingLevel = 0;
	private Map<SqlSessionFactory, SqlSession> currentSessions = new HashMap<SqlSessionFactory, SqlSession>();

	private TransactionScope() {
	}

	/**
	 * Gets current sql session (that is used inside this scope).
	 * If no sessions yet - opens new session.
	 */
	public SqlSession getSqlSession(SqlSessionFactory sqlSessionFactory) {
		SqlSession session = currentSessions.get(sqlSessionFactory);

		if (session == null) {
			session = sqlSessionFactory.openSession();
			currentSessions.put(sqlSessionFactory, session);
		}

		return session;
	}

	public void commit() {
		if (nestingLevel == 0) {
			for (SqlSession session : currentSessions.values()) {
				session.commit();
			}
		}
	}

	/**
	 * Closes all current sessions.
	 */
	public void close() {
        if (nestingLevel == 0) {
            try {
                for (SqlSession session : currentSessions.values()) {
                    session.close();
                }
                current.set(null);
                currentSessions.clear();
            } finally {
                semaphore.release();
            }
        } else {
            nestingLevel--;
        }
    }
}
