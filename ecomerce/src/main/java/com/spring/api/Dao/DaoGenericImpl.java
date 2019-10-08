package com.spring.api.Dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.spring.api.Config.HibernateUtil;

@Component
public class DaoGenericImpl implements IGenericDao {

	@Override
	public Object saveObject(Object obj) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(obj);
			transaction.commit();
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}


	@Override
	public Object updateObject(Object obj) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.update(obj);
			transaction.commit();
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteObject(Object obj) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.delete(obj);
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	
}
