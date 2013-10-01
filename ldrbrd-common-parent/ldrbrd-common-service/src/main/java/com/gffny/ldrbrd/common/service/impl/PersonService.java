/**
 * 
 */
package com.gffny.ldrbrd.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gffny.ldrbrd.common.model.UserProfile;
import com.gffny.ldrbrd.common.persistence.impl.PersonDao;
 
@Component
public class PersonService {
 
    private PersonDao personDao;
 
    public PersonDao getPersonDao() {
        return personDao;
    }

    @Autowired
    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }
 
    public void addPerson(UserProfile person) {
        getPersonDao().insert(person);
    }
 
    public List<UserProfile> fetchAllPersons() {
        return getPersonDao().selectAll();
    }
}

