/**
 * 
 */
package com.gffny.ldrbrd.web.model;

import com.gffny.ldrbrd.web.model.cache.Cache;
import com.gffny.ldrbrd.common.model.impl.GolferProfile;
import com.gffny.ldrbrd.common.service.IAuthorisationService;
import com.gffny.ldrbrd.common.service.IUserProfileService;
//import com.gffny.leaderboard.service.IAuthorisationService;
import com.gffny.ldrbrd.common.service.ICompetitionService;
import com.gffny.ldrbrd.common.service.ICourseClubService;
import com.gffny.ldrbrd.common.service.IScorecardService;
import com.gffny.ldrbrd.utils.Locale;

/**
 * @author John Gaffney (john@gffny.com) Dec 24, 2012
 * 
 */
public class RequestContext {

	/**
	 * 
	 */
	private static ThreadLocal<RequestContext> currentInstance = new ThreadLocal<RequestContext>() {

		/**
		 * 
		 */
		@Override
		protected RequestContext initialValue() {
			return new RequestContext();
		}
	};

	/**
	 * 
	 */
	private GolferProfile user;

	/**
	 * 
	 */
	private ServletData servletData;

	/**
	 * 
	 */
	private IUserProfileService userService;

	/**
	 * 
	 */
	private ICompetitionService competitionService;

	/**
	 * 
	 */
	private ICourseClubService golfCourseService;

	/**
	 * 
	 */
	private IScorecardService scorecardService;

	/**
	 * 
	 */
	private IAuthorisationService authorisationSerivce;

	/**
	 * 
	 */
	private Cache cache;

	/**
	 * 
	 * @return
	 */
	public static RequestContext get() {
		return currentInstance.get();
	}

	/**
	 * 
	 * @return
	 */
	public GolferProfile getUser() {
		return user;
	}

	/**
	 * 
	 * @param user
	 */
	public void setUser(GolferProfile user) {
		this.user = user;
	}

	/**
	 * 
	 * @return
	 */
	public ServletData getServletData() {
		return servletData;
	}

	/**
	 * 
	 * @param servletData
	 */
	public void setServletData(ServletData servletData) {
		this.servletData = servletData;
	}

	/*
	 * public Locale userLocaleFromDb() { try { return
	 * getUser().getLanguagePreference(); } catch (Throwable ex) { return null;
	 * }
	 * 
	 * }
	 */
	/**
	 * @return the competitionService
	 */
	public ICompetitionService getCompetitionService() {
		return competitionService;
	}

	/**
	 * @param competitionService
	 *            the competitionService to set
	 */
	public void setCompetitionService(ICompetitionService competitionService) {
		this.competitionService = competitionService;
	}

	/**
	 * @return the golfCourseService
	 */
	public ICourseClubService getGolfCourseService() {
		return golfCourseService;
	}

	/**
	 * @param golfCourseService
	 *            the golfCourseService to set
	 */
	public void setGolfCourseService(ICourseClubService golfCourseService) {
		this.golfCourseService = golfCourseService;
	}

	/**
	 * @return the scorecardService
	 */
	public IScorecardService getScorecardService() {
		return scorecardService;
	}

	/**
	 * @param scorecardService
	 *            the scorecardService to set
	 */
	public void setScorecardService(IScorecardService scorecardService) {
		this.scorecardService = scorecardService;
	}

	/**
	 * @return the authorisationSerivce
	 */
	public IAuthorisationService getAuthorisationSerivce() {
		return authorisationSerivce;
	}

	/**
	 * @param authorisationSerivce
	 *            the authorisationSerivce to set
	 */
	public void setAuthorisationSerivce(
			IAuthorisationService authorisationSerivce) {
		this.authorisationSerivce = authorisationSerivce;
	}

	/**
	 * 
	 * @return
	 */
	public IUserProfileService getUserService() {
		return userService;
	}

	/**
	 * @param userService
	 *            the userService to set
	 */
	public void setUserService(IUserProfileService userService) {
		this.userService = userService;
	}

	/**
	 * @param cache
	 *            the cache to set
	 */
	public void setCache(Cache cache) {
		this.cache = cache;
	}

	/**
	 * 
	 * @return
	 */
	public Cache getCache() {
		if (cache == null) {
			cache = new Cache();
		}

		return cache;
	}

	/**
	 * 
	 */
	public void release() {
		user = null;
		servletData = null;
		userService = null;
		cache = null;
	}

	public Locale userLocaleFromDb() {
		// TODO Auto-generated method stub
		return null;
	}
}
