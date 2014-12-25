/**
 * 
 */
package com.gffny.ldrbrd.common.service.impl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gffny.ldrbrd.common.dao.GenericDao;
import com.gffny.ldrbrd.common.exception.AuthorisationException;
import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.impl.CompetitionRound;
import com.gffny.ldrbrd.common.model.impl.Golfer;
import com.gffny.ldrbrd.common.model.impl.Scorecard;
import com.gffny.ldrbrd.common.model.web.GolferDigestResponse;
import com.gffny.ldrbrd.common.service.ICompetitionService;
import com.gffny.ldrbrd.common.service.IScorecardService;
import com.gffny.ldrbrd.common.service.IUserProfileService;
import com.gffny.ldrbrd.common.utils.QueryUtils;

/**
 * @author John D. Gaffney | gffny.com
 */
@Service
public class ProfileService extends AbstractService implements
		IUserProfileService {

	/** The Constant log. */
	private static final Logger LOG = LoggerFactory
			.getLogger(ProfileService.class);

	/** */
	@Autowired
	@Qualifier(value = "genericDaoJpaImpl")
	private GenericDao<Golfer> personDao;

	/** */
	@Autowired
	private ICompetitionService competitionService;

	/** */
	@Autowired
	private IScorecardService scorecardService;

	/** */
	@Autowired
	private CourseClubService courseClubService;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IUserProfileService#getGolferByHandle
	 *      (java.lang.String)
	 */
	@Override
	public Golfer getGolferByHandle(String golferHandle)
			throws ServiceException, AuthorisationException {

		if (golferHandle != null) {
			authorisationService.authorise(golferHandle);
			return namedQuerySingleResultOrNull(personDao,
					Golfer.FIND_BY_HANDLE,
					QueryUtils.paramMap("profileHandle", golferHandle));
		}
		LOG.error("error with parameters for method");
		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IUserProfileService#getGolferByEmail(java.lang.String)
	 */
	@Override
	public Golfer getGolferByEmail(String golferEmail) throws ServiceException,
			AuthorisationException {

		if (golferEmail != null) {
			authorisationService.authorise(golferEmail);
			return namedQuerySingleResultOrNull(personDao,
					Golfer.FIND_BY_EMAIL,
					QueryUtils.paramMap("emailAddress", golferEmail));
		}
		LOG.error("error with parameters for method");
		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @throws AuthorisationException
	 * @see com.gffny.ldrbrd.common.service.IUserProfileService#getGolferById(int)
	 */
	@Override
	public Golfer getGolferById(String id) throws ServiceException,
			AuthorisationException {

		try {
			// authorize the user
			id = authorisationService.authorise(id);
			return personDao.findById(Golfer.class, Integer.parseInt(id));
		} catch (PersistenceException | NumberFormatException e) {
			LOG.error(e.getMessage());
			throw new ServiceException(e);
		}

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IUserProfileService#getDigestById(int)
	 */
	@Override
	public GolferDigestResponse getDigestById(String id)
			throws AuthorisationException, ServiceException {

		GolferDigestResponse digest = new GolferDigestResponse(
				getGolferById(id));
		digest.setFavouriteCourseList(courseClubService.testList());
		digest.setLastXScorecardList(new ArrayList<Scorecard>());
		digest.setUpcomingCompetitionRoundList(competitionService
				.listCompetitionRoundForGolfer(id));
		digest.setUpcomingNonCompetitionRoundList(new ArrayList<CompetitionRound>());
		if (scorecardService.hasActiveScorecard(id)) {
			digest.setActiveScorecard(scorecardService
					.getActiveScorecard(digest.getGolfer().getIdString()));
		}
		return digest;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.gffny.ldrbrd.common.service.IUserProfileService#getLoggedInGolferDigest()
	 */
	@Override
	public GolferDigestResponse getLoggedInGolferDigest()
			throws AuthorisationException, ServiceException {
		return getDigestById(authorisationService.getLoggedInUser()
				.getIdString());
	}
}