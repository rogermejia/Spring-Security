package com.spring.api.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.api.Dao.*;
import com.spring.api.Entities.*;

@Service
public class ServiceAllListImpl implements IAllListService {

	
	public ServiceAllListImpl() {
	}
	
	@Autowired
	@Qualifier(value = "listDao")
	private IAllListDao allList;
	
//	@Autowired
//	public ServiceAllListImpl(IAllListDao allList) {
//		this.allList = allList;
//	}
	
	@Override
	public List<Optionss> allOptionss() {
		return allList.allOptionss();
	}

	@Override
	public List<Role> allRole() {
		return allList.allRole();
	}

	@Override
	public List<RoleOptions> allRoleOptions() {
		return allList.allRoleOptions();
	}

	@Override
	public List<Users> allUsers() {
		return allList.allUsers();
	}

	@Override
	public List<Products> allProducts() {
		return allList.allProducts();
	}

	@Override
	public List<UsersRole> allUsersRole() {
		return allList.allUsersRole();
	}

	@Override
	public List<Category> allCategory() {
		return allList.allCategory();
	}

	@Override
	public List<Settings> allSettings() {
		return allList.allSettings();
	}

	@Override
	public List<UsersRole> loginSite(Users u) {
		return allList.loginSite(u);
	}

	@Override
	public List<Users> allUsers(String name) {
		return allList.allUsers(name);
	}
	
	
}
