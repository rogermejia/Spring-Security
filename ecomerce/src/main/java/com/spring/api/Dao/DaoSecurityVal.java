package com.spring.api.Dao;

import java.util.List;

import org.hibernate.Session;

import com.spring.api.Config.HibernateUtil;
import com.spring.api.Entities.Optionss;
import com.spring.api.Entities.Role;
import com.spring.api.Entities.RoleOptions;
import com.spring.api.Entities.Users;
import com.spring.api.Entities.UsersRole;

public class DaoSecurityVal implements ISecurityVal{

	@Override
	public List<Role> role(Users u) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			u = session.createQuery("from Users WHERE users = :us AND password = :p", Users.class).setParameter("us", u.getUsers()).setParameter("p", u.getPassword()).getSingleResult();
			return session.createQuery("from Role WHERE idUsers = :us", Role.class).setParameter("us", u.getIdUsers()).getResultList();
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public Users LoggedUser(Users u) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Users WHERE users = :us AND password = :p", Users.class).setParameter("us", u.getUsers()).setParameter("p", u.getPassword()).getSingleResult();
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public List<UsersRole> userRoles(Users u) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			u = session.createQuery("from Users WHERE users = :us AND password = :p", Users.class).setParameter("us", u.getUsers()).setParameter("p", u.getPassword()).getSingleResult();
			return session.createQuery("from UsersRole WHERE idUsers = :us", UsersRole.class).setParameter("us", u.getIdUsers()).getResultList();
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public List<RoleOptions> roleOptions(Long id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from RoleOptions WHERE idRoleOptions = :us", RoleOptions.class).setParameter("us", id).getResultList();
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public List<Optionss> optionss(Long id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Optionss WHERE idOptions = :us", Optionss.class).setParameter("us", id).getResultList();
		}catch(Exception e) {
			return null;
		}
	}

}
