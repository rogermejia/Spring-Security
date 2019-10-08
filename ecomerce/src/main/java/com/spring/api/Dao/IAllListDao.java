package com.spring.api.Dao;

import java.util.List;

import com.spring.api.Entities.*;

public interface IAllListDao {
	
	public List<Optionss> allOptionss();     // Optionss  General List
	public List<Role> allRole();               // Role  General List
	public List<RoleOptions> allRoleOptions();               // RoleOptions  General List
	public List<Users> allUsers();         // Users  General List
	public List<Users> allUsers(String name);         // Users  General List username
	public List<Products> allProducts();       // Products  General List
	
	public List<UsersRole> allUsersRole();       // UsersRole  General List
	public List<Category> allCategory();       // Category  General List
	public List<Settings> allSettings();       // Category  Settings List

	public List<UsersRole> loginSite(Users u);      // RETRIEVE SINGLE UsersRole
	

}