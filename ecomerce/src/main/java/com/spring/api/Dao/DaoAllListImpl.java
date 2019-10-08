package com.spring.api.Dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.spring.api.Config.HibernateUtil;
import com.spring.api.Entities.*;

@Component
public class DaoAllListImpl implements IAllListDao {


	@Override
	public List<Optionss> allOptionss() {
		List<Optionss> list = new ArrayList<Optionss>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			list = session.createQuery("from Optionss", Optionss.class).list();
		return list;
		}
	}

	@Override
	public List<Role> allRole() {
		List<Role> list = new ArrayList<Role>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			list = session.createQuery("from Role", Role.class).list();
		return list;
		}
	}

	@Override
	public List<RoleOptions> allRoleOptions() {
		List<RoleOptions> list = new ArrayList<RoleOptions>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			list = session.createQuery("from RoleOptions", RoleOptions.class).list();
		return list;
		}
	}

	@Override
	public List<Users> allUsers() {
		List<Users> list = new ArrayList<Users>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			list = session.createQuery("from Users", Users.class).list();
		return list;
		}
	}

	@Override
	public List<Products> allProducts() {
		List<Products> list = new ArrayList<Products>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			list = session.createQuery("from Products", Products.class).list();
		return list;
		}
	}

	@Override
	public List<UsersRole> allUsersRole() {
		List<UsersRole> list = new ArrayList<UsersRole>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			list = session.createQuery("from UsersRole", UsersRole.class).list();
		return list;
		}
	}

	@Override
	public List<Category> allCategory() {
		List<Category> list = new ArrayList<Category>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			list = session.createQuery("from Category", Category.class).list();
		return list;
		}
	}

	@Override
	public List<Settings> allSettings() {
		List<Settings> list = new ArrayList<Settings>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			list = session.createQuery("from Settings", Settings.class).list();
		return list;
		}
	}

	@Override
	public List<UsersRole> loginSite(Users u) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			u = session.createQuery("from Users WHERE users = :us AND password = :p", Users.class).setParameter("us", u.getUsers()).setParameter("p", u.getPassword()).getSingleResult();
			return session.createQuery("from UsersRole WHERE idUsers = :us", UsersRole.class).setParameter("us", u.getIdUsers()).getResultList();
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public List<Users> allUsers(String username) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Users WHERE users = :us", Users.class).setParameter("us", username).getResultList();
		}catch(Exception e) {
			return null;
		}
	}
	
	
}
