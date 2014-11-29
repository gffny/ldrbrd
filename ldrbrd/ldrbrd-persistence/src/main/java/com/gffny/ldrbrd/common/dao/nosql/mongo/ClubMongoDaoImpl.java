/**
 * 
 */
package com.gffny.ldrbrd.common.dao.nosql.mongo;

import org.springframework.stereotype.Repository;

import com.gffny.ldrbrd.common.model.nosql.Club;

/**
 * @author John D. Gaffney | gffny.com
 */
// @Transactional
@Repository(value = "clubMongoDaoImpl")
public class ClubMongoDaoImpl extends GenericNoSqlDaoMongoImpl<Club> {

}
