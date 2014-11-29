/**
 * 
 */
package com.gffny.ldrbrd.common.dao.nosql.mongo;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gffny.ldrbrd.common.model.nosql.AnalysisScorecard;

/**
 * @author John D. Gaffney | gffny.com
 */
@Transactional
@Repository
public class AnalysisScorecardMongoDaoImpl extends
		GenericNoSqlDaoMongoImpl<AnalysisScorecard> {

}
