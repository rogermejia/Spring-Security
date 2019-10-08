package com.spring.api.Service;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.api.Dao.IGenericDao;

@Service
public class ServiceGenerImpl implements IGenericService {

	@Autowired
	@Qualifier(value = "daoGen")
	private IGenericDao daoGen;
//	@Autowired
//	public ServiceGenerImpl(IGenericDao daoGen) {
//		this.daoGen = daoGen;
//	}

	public ServiceGenerImpl() {
	}

	@Override
	public Object saveObject(Object obj) {
		return daoGen.saveObject(obj);
	}

	@Override
	public Object updateObject(Object obj) {
		return daoGen.updateObject(obj);
	}

	@Override
	public boolean deleteObject(Object obj) {
		return daoGen.deleteObject(obj);
	}

}
