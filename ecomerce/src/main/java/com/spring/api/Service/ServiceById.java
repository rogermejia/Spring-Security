package com.spring.api.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.api.Dao.*;
import com.spring.api.Entities.*;

@Service
public class ServiceById implements IByIdService {

	@Autowired
	@Qualifier(value = "byIdDao")
	IByIdDao byIdDao;
	
//	@Autowired
//	public ServiceById(IByIdDao byIdDao) {
//		this.byIdDao = byIdDao;
//	}

	public ServiceById() {
	}

	@Override
	public Optionss getOptionssById(Long id) {
		return byIdDao.getOptionssById(id);
	}

	@Override
	public Role getRoleById(Long id) {
		return byIdDao.getRoleById(id);
	}

	@Override
	public RoleOptions getRoleOptionsById(Long id) {
		return byIdDao.getRoleOptionsById(id);
	}

	@Override
	public Users getUsersById(Long id) {
		return byIdDao.getUsersById(id);
	}

	@Override
	public UsersRole getUsersRoleById(Long id) {
		return byIdDao.getUsersRoleById(id);
	}

	@Override
	public Products getProductsById(Long id) {
		return byIdDao.getProductsById(id);
	}

	@Override
	public Category getCategoryById(Long id) {
		return byIdDao.getCategoryById(id);
	}

	@Override
	public Settings getSettingsById(Long id) {
		return byIdDao.getSettingsById(id);
	}

	@Override
	public Users loginUsers(Users u) {
		return byIdDao.loginUsers(u);
	}


	

}
