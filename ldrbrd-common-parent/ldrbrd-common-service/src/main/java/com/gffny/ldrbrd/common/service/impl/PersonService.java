/**
 * 
 */
package com.gffny.ldrbrd.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gffny.ldrbrd.common.exception.DataAccessException;
import com.gffny.ldrbrd.common.model.impl.UserProfile;
import com.gffny.ldrbrd.common.persistence.GenericDao;

@Component
public class PersonService {

	@Autowired
	private GenericDao<UserProfile> personDao;

	public void addPerson(UserProfile person) throws DataAccessException {
		personDao.persist(person);
	}

	public List<UserProfile> fetchAllPersons() throws DataAccessException {
		return personDao.findAll();
	}
}
