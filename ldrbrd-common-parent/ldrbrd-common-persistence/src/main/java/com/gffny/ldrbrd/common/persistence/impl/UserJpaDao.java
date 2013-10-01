/**
 * 
 */
package com.gffny.ldrbrd.common.persistence.impl;

import org.springframework.stereotype.Repository;

import com.gffny.ldrbrd.common.model.UserProfile;
import com.gffny.ldrbrd.common.persistence.GenericDaoJpaImpl;

/**
 * @author jdgaffney
 *
 */
@Repository
public class UserJpaDao  extends GenericDaoJpaImpl<UserProfile> {

	public UserJpaDao() {
		super(UserProfile.class);
	}
	
	public void save(UserProfile userProfile) {
		System.out.println("User Profile isEnabled? "+userProfile.isEnabled());
	}

}
