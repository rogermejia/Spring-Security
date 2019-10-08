package com.spring.api.Service;

import com.spring.api.Entities.*;

public interface IByIdService {
	
	public Optionss getOptionssById(Long id);  // RETRIEVE SINGLE Optionss
	public Role getRoleById(Long id);            // RETRIEVE SINGLE Role
	public RoleOptions getRoleOptionsById(Long id);            // RETRIEVE SINGLE RoleOptions
	public Users getUsersById(Long id);      // RETRIEVE SINGLE Users
	public UsersRole getUsersRoleById(Long id);    // RETRIEVE SINGLE UsersRole
	
	public Products getProductsById(Long id);    // RETRIEVE SINGLE Products
	public Category getCategoryById(Long id);    // RETRIEVE SINGLE Category
	public Settings getSettingsById(Long id);    // RETRIEVE SINGLE Settings
	
	public Users loginUsers(Users u);      // RETRIEVE SINGLE Users

}
