/**
 * 
 */
package com.gffny.ldrbrd.common.persistence;

import org.springframework.stereotype.Repository;

import com.gffny.ldrbrd.common.model.impl.GolferProfile;

/**
 * @author jdgaffney
 * 
 */
@Repository("golferDao")
public class GolferDao extends GenericDaoJpaImpl<GolferProfile> {

}
