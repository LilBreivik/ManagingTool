package resources.database.dao;

import java.io.Serializable;
import java.util.List; 
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import resources.database.dao.GenericDao;
import resources.error.TransactionError;
 
 
public class GenericDaoImpl  <T, PK extends Serializable>
									implements GenericDao<T, PK> {
 
	private Class<T> type;

	@Autowired
	private SessionFactory sessionFactory;
	
	public GenericDaoImpl(Class<T> clazz){
		
		type = clazz;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	} 

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	} 
	
	// Not showing implementations of getSession() and setSessionFactory()
	@Transactional()
	private Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		return session;
	}
 
	@Transactional()
	public PK create(T o) {
		
		try {
			 
			return (PK) getSession().save(o);
		
		}
		catch(Exception e) {
			
			TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
			 
			throw  new TransactionError("Cannot add new Entry in resources.database du to " + e.getMessage());
		}
			
	}

	//@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	@Transactional()
	public void update(T o) {
		 
		getSession().update(o);
	}

	
	@Transactional()
	public void saveOrUpdate(T o) {
	
		getSession().saveOrUpdate(o);
	}
	
	//@Transactional(readOnly = true)
	@Transactional()
	public T read(PK id) {
		return (T) getSession().get(type, id);
	}

	 
	@Transactional()
	public List<T> read() {
			
		  return (List<T>) getSession().createCriteria(type).list();
	}

	//@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	@Transactional()
	public void delete(PK id) {
		T o = (T) getSession().load(type, id);
		getSession().delete(o);
	}

//	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	@Transactional()
	public void delete(T o) {
		getSession().delete(o);
	}
 
}
