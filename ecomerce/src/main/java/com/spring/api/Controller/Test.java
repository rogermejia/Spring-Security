package com.spring.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.spring.api.Entities.Role;
import com.spring.api.Service.IAllListService;

public class Test {
	
	@Autowired
	@Qualifier(value = "serviceImpl")
	public static IAllListService listS;
public static void Main(String[] args) {
	String init = "hasRole(";
	String roleList ="";
	String closing = ")";
	String or = " or ";
	for(Role r: listS.allRole()) {
		roleList = roleList + init+r.getRolename()+closing;
		
		if(listS.allRole().size() < listS.allRole().size()) {
			roleList = roleList+closing+or;
		}
	}
	
	System.out.println(roleList);
}
}
