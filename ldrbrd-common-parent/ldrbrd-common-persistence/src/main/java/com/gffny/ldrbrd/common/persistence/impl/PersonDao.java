package com.gffny.ldrbrd.common.persistence.impl;

import java.util.List;
 


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
 


import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gffny.ldrbrd.common.model.UserProfile;
 
@Repository
public class PersonDao {
 
    private static final String SELECT_QUERY = "select p from UserProfile p";
 
    @PersistenceContext
    private EntityManager entityManager;
 
    public EntityManager getEntityManager() {
        return entityManager;
    }
 
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
 
    @Transactional
    public void insert(UserProfile person) {
        entityManager.persist(person);
    }
 
    public List<UserProfile> selectAll() {
        Query query = entityManager.createQuery(SELECT_QUERY);
        List<UserProfile> persons = (List<UserProfile>) query.getResultList();
        return persons;
    }
 
}