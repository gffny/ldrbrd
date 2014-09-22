/**
 * 
 */
package com.gffny.ldrbrd.common.dao.mongo;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gffny.ldrbrd.common.dao.GenericNoSqlDaoMongoImpl;
import com.gffny.ldrbrd.common.model.impl.mongo.Club;

/**
 * @author John D. Gaffney | gffny.com
 */
@Transactional
@Repository
public class AnalysisScorecardMongoDaoImpl extends GenericNoSqlDaoMongoImpl<Club> {

	public AnalysisScorecardMongoDaoImpl() {
		super();
	}

}
