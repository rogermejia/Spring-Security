package com.spring.api.Dao;

import java.util.List;

import com.spring.api.Entities.*;

public interface ISecurityVal {

	public List<Role> role(Users u);
	
	public Users LoggedUser(Users u);
	
	public List<UsersRole> userRoles(Users u);
	
	public List<RoleOptions> roleOptions(Long id);
	
	public List<Optionss> optionss(Long id);
}
