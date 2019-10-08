package com.spring.api.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.api.Entities.*;
import com.spring.api.Service.*;

@RestController
@RequestMapping(value = "/api")
public class UsersController {
	
	public UsersController() {
	}
//	@Autowired
//	private IByIdService byIdS;
//
//	@Autowired
//	private IGenericService genS;
	

	
	@Autowired
	@Qualifier(value = "serviceImpl")
	public IAllListService listS;
	
	@Autowired
	@Qualifier(value = "byIdImpl")
	public IByIdService byIdS;
	
	@Autowired
	@Qualifier(value = "genericImpl")
	public IGenericService genS;
	
	HttpHeaders head = new HttpHeaders();

	// ******************************************************************************************
	@RequestMapping(value = "/productos", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<?> checker(@RequestHeader(name = "Authorization") String header) {

		Settings objetoDB = byIdS.getSettingsById(new Long(1));
		head.add("Authorization", header);

		if (header.equalsIgnoreCase(objetoDB.getToken())) {
			return new ResponseEntity<>(listS.allProducts(), head, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Go fish", HttpStatus.FORBIDDEN);
		}
	}

	// http://localhost:8090/ecomerce/api/userlog

	/*
	 { 
	 "users": "admin", 
	 "password": "12345" 
	 }
	 */

	@GetMapping(value = "/userlog", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<?> userlog(@RequestBody Users user) {

		List<UsersRole> obj = listS.loginSite(user);

		if (obj != null) {
			head.add("Authorization", "Token_Generated_Simulation");
			return new ResponseEntity<>(obj, head, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Wrong password/username", HttpStatus.FORBIDDEN);
		}
	}

	// RETRIEVE SINGLE
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<?> byIdRole(@RequestBody Users user) {

		Users obj = byIdS.loginUsers(user);
		
		obj = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (obj != null) {
			return new ResponseEntity<>(obj, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Wrong password/username", HttpStatus.FORBIDDEN);
		}

	}
	
//	// RETRIEVE SINGLE
//		@RequestMapping(value = "/users", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
//		@ResponseBody
//		public ResponseEntity<?> byIdRole(@RequestBody Users user) {
//
//			Users obj = byIdS.loginUsers(user);
//			if (obj != null) {
//				return new ResponseEntity<>(obj, HttpStatus.OK);
//			} else {
//				return new ResponseEntity<>("Wrong password/username", HttpStatus.FORBIDDEN);
//			}
//
//		}
	
	@RequestMapping(value = "/usersAuth", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
    public ResponseEntity<UserProfile> profile(@RequestBody User user)
    {
        //Build some dummy data to return for testing
        user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = user.getUsername() + "@howtodoinjava.com";
 
        UserProfile profile = new UserProfile();
        profile.setName(user.getUsername());
        profile.setEmail(email);
 
        return ResponseEntity.ok(profile);
    }

	// ******************************************************************************************

	// SHOW COMPLETE LIST
	@ResponseStatus(code = HttpStatus.FOUND)
	@RequestMapping(value = "/role", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<Role> findAllRole() {
		List<Role> list = listS.allRole();
		return list;
	}

	// RETRIEVE SINGLE
	@RequestMapping(value = "/role/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<?> byIdRole(@PathVariable("id") Long id) {

		Role obj = byIdS.getRoleById(id);
		if (obj != null) {
			return new ResponseEntity<>(obj, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// SAVE NEW SINGLE Role
	@RequestMapping(value = "/role", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<?> saveRole(@RequestBody Role obj) {
		if (obj.getIdRole() == null || obj.getIdRole() == 0) {
			return new ResponseEntity<>(genS.saveObject(obj), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	// UPDATE SINGLE ENTRY Role
	@RequestMapping(value = "/role/{id}", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<?> updateRole(@PathVariable("id") Long id, @RequestBody Role obj) {

		if (obj.getIdRole() == id) {

			Role r = (Role) genS.updateObject(obj);
			if (r != null && obj.getIdRole() != null) {
				return new ResponseEntity<>(r, HttpStatus.OK);
			} else if (r == null && obj.getIdRole() != null) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			} else if (r == null && obj.getIdRole() == null) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}

		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// DELETE SINGLE ENTRY USERS
	@RequestMapping(value = "/role/{id}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<?> deleteRole(@PathVariable("id") Long idobj) {
		Role obj = new Role();
		obj.setIdRole(idobj);

		boolean msj = genS.deleteObject(obj);

		if (msj) {
			return new ResponseEntity<>("File deleted successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Sorry, there was a problem deleting the file... try again!",
					HttpStatus.NO_CONTENT);
		}
	}

	// *************************************************Optionss*******************************************************

	// SHOW COMPLETE LIST
	@ResponseStatus(code = HttpStatus.FOUND)
	@RequestMapping(value = "/optionss", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<Optionss> findAllOptionss() {
		List<Optionss> list = listS.allOptionss();
		return list;
	}

	// RETRIEVE SINGLE
	@RequestMapping(value = "/optionss/{id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<?> byIdOptionss(@PathVariable("id") Long id) {
		Optionss obj = byIdS.getOptionssById(id);
		if (obj != null) {
			return new ResponseEntity<>(obj, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// SAVE NEW SINGLE Role
	@RequestMapping(value = "/optionss", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<?> saveOptionss(@RequestBody Optionss obj) {
		if (obj.getIdOptionss() == null || obj.getIdOptionss() == 0) {
			return new ResponseEntity<>(genS.saveObject(obj), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// UPDATE SINGLE ENTRY Role
	@RequestMapping(value = "/optionss/{id}", method = RequestMethod.PUT, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<?> updateOptionss(@PathVariable("id") Long id, @RequestBody Optionss obj) {

		if (obj.getIdOptionss() == id) {

			Optionss r = (Optionss) genS.updateObject(obj);
			if (r != null && obj.getIdOptionss() != null) {
				return new ResponseEntity<>(r, HttpStatus.OK);
			} else if (r == null && obj.getIdOptionss() != null) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			} else if (r == null && obj.getIdOptionss() == null) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}

		} else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// DELETE SINGLE ENTRY USERS
	@RequestMapping(value = "/optionss/{id}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<?> deleteOptionss(@PathVariable("id") Long idobj) {
		Optionss obj = new Optionss();
		obj.setIdOptionss(idobj);

		boolean msj = genS.deleteObject(obj);

		if (msj) {
			return new ResponseEntity<>("File deleted successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Sorry, there was a problem deleting the file... try again!",
					HttpStatus.NO_CONTENT);
		}
	}

	// *************************************************Products*******************************************************
	// SHOW COMPLETE LIST
	@ResponseStatus(code = HttpStatus.FOUND)
	@RequestMapping(value = "/products", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<Products> findAllProducts() {
		List<Products> list = listS.allProducts();
		return list;
	}

	// RETRIEVE SINGLE
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<?> byIdProducts(@PathVariable("id") Long id) {
		Products obj = byIdS.getProductsById(id);
		if (obj != null) {
			return new ResponseEntity<>(obj, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// *************************************************Category*******************************************************
	// SHOW COMPLETE LIST
	@ResponseStatus(code = HttpStatus.FOUND)
	@RequestMapping(value = "/category", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<Category> findAllCategory() {
		List<Category> list = listS.allCategory();
		return list;
	}

	// RETRIEVE SINGLE
	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<?> byIdCategory(@PathVariable("id") Long id) {
		Category obj = byIdS.getCategoryById(id);
		if (obj != null) {
			return new ResponseEntity<>(obj, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// *************************************************Settings*******************************************************

	// RETRIEVE SINGLE
	@RequestMapping(value = "/settings/{id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<?> byIdSettings(@PathVariable("id") Long id) {
		Settings obj = byIdS.getSettingsById(id);
		if (obj != null) {
			return new ResponseEntity<>(obj, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}
