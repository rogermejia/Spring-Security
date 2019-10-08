package com.spring.api.Dao;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.spring.api.Entities.Category;
import com.spring.api.Entities.Optionss;
import com.spring.api.Entities.Products;
import com.spring.api.Entities.Role;
import com.spring.api.Entities.RoleOptions;
import com.spring.api.Entities.Settings;
import com.spring.api.Entities.Users;
import com.spring.api.Entities.UsersRole;

import com.spring.api.Config.HibernateUtil;

@Component
public class DaoByIdImpl implements IByIdDao {

	@Override
	public Optionss getOptionssById(Long id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Optionss.class, new Long(id));
		}
	}

	@Override
	public Role getRoleById(Long id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Role.class, new Long(id));
		}
	}

	@Override
	public RoleOptions getRoleOptionsById(Long id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(RoleOptions.class, new Long(id));
		}
	}

	@Override
	public Users getUsersById(Long id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Users.class, new Long(id));
		}
	}

	@Override
	public UsersRole getUsersRoleById(Long id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(UsersRole.class, new Long(id));
		}
	}

	@Override
	public Products getProductsById(Long id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Products.class, new Long(id));
		}
	}

	@Override
	public Category getCategoryById(Long id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Category.class, new Long(id));
		}
	}

	@Override
	public Settings getSettingsById(Long id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(Settings.class, new Long(id));
		}
	}

//	@Override
//	public Users loginUsers(Users u) {
//		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//			return session.createQuery("from Users WHERE users = :us AND password = :p", Users.class).setParameter("us", u.getUsers()).setParameter("p", u.getPassword()).getSingleResult();
//		}
//	}

	@Override
	public Users loginUsers(Users u) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Users WHERE users = :us AND password = :p", Users.class).setParameter("us", u.getUsers()).setParameter("p", u.getPassword()).getSingleResult();
		}catch(Exception e) {
			return null;
		}
	}

@Override
public Users loginSite(Users u) {
	try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		return session.createQuery("from Users WHERE users = :us AND password = :p", Users.class).setParameter("us", u.getUsers()).setParameter("p", u.getPassword()).getSingleResult();
		//return session.createQuery("from Users WHERE idUsers = :us", Users.class).setParameter("us", u.getIdUsers()).getResultList();
	}catch(Exception e) {
		return null;
	}
}

@Override
public Users loginUsers(String username) {
	try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		return session.createQuery("from Users WHERE users = :us", Users.class).setParameter("us", username).getSingleResult();
	}catch(Exception e) {
		return null;
	}
}
	
	
}
