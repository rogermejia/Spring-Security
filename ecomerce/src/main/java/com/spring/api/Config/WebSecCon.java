package com.spring.api.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.spring.api.Dao.IByIdDao;
import com.spring.api.Entities.Users;
import com.spring.api.Entities.UsersRole;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WebSecCon implements UserDetailsService {

	@Autowired
	@Qualifier(value = "byIdDao")
	IByIdDao byIdDao;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = byIdDao.loginUsers(username);
		UserBuilder builder = null;
		if (user != null) {

			builder = org.springframework.security.core.userdetails.User.withUsername(username);
//	      builder.disabled(!user.isEnabled());
			builder.password(user.getPassword());
			UsersRole ur = new UsersRole();
			String[] authorities = ur.getRoleobj().getListUsersRole().stream()
					.map(a -> a.getRoleobj().getListUsersRole()).toArray(String[]::new);

			builder.authorities(authorities);
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
		return builder.build();
	}
}
