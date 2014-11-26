/**
 * 
 */
package com.gffny.ldrbrd.common.dao.nosql;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gffny.ldrbrd.common.model.impl.mongo.HoleScore;

/**
 * @author John D. Gaffney | gffny.com
 */
@Transactional
@Repository
public class HoleScoreMongoDaoImpl extends GenericNoSqlDaoMongoImpl<HoleScore> {

}
