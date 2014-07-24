/**
 * 
 */
package com.gffny.ldrbrd.common.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gffny.ldrbrd.common.dao.GenericDao;
import com.gffny.ldrbrd.common.exception.PersistenceException;
import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.enums.ClubType;
import com.gffny.ldrbrd.common.model.enums.Manufacturer;
import com.gffny.ldrbrd.common.model.impl.GolfClub;
import com.gffny.ldrbrd.common.service.IGolfClubService;

/**
 * @author John D. Gaffney | gffny.com
 * 
 */
@Service
public class GolfClubService implements IGolfClubService {

	/** The Constant log. */
	private static final Logger LOG = LoggerFactory
			.getLogger(GolfClubService.class);

	@Autowired
	private GenericDao<GolfClub> golfClubDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.service.IGolfClubService#addGolfClub(com.gffny
	 * .ldrbrd.common.model.impl.GolfClub)
	 */
	public void addGolfClub(GolfClub golfClub) throws ServiceException {
		// TODO implement addGolfClub
		throw new ServiceException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.service.IGolfClubService#addGolfClubList(java
	 * .util.List)
	 */
	public void addGolfClubList(List<GolfClub> golfClub)
			throws ServiceException {
		// TODO implement addGolfClubList
		throw new ServiceException();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.service.IGolfClubService#getAllClubsByManufacturer
	 * (com.gffny.ldrbrd.common.model.enums.Manufacturer)
	 */
	public List<GolfClub> getAllClubsByManufacturer(Manufacturer manufacturer)
			throws ServiceException {
		// TODO implement getAllClubsByManufacturer
		throw new ServiceException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.service.IGolfClubService#getAllClubsByType(com
	 * .gffny.ldrbrd.common.model.enums.ClubType)
	 */
	public List<GolfClub> getAllClubsByType(ClubType type)
			throws ServiceException {
		// TODO implement getAllClubsByType
		throw new ServiceException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.service.IGolfClubService#getAllClubsByCategory
	 * (com.gffny.ldrbrd.common.model.enums.ClubType)
	 */
	public List<GolfClub> getAllClubsByCategory(ClubType category)
			throws ServiceException {
		// TODO implement getAllClubsByCategory
		throw new ServiceException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gffny.ldrbrd.common.service.IGolfClubService#getDefaultGolfClubList()
	 */
	public List<GolfClub> getDefaultGolfClubList() throws ServiceException {
		try {
			return golfClubDao.findByNamedQuery(
					GolfClub.DEFAULT_CLUB_LIST_QUERY, null, 100);
		} catch (PersistenceException daEx) {
			LOG.error("error getting the list of default golf clubs : "
					+ daEx.getMessage());
			throw new ServiceException(daEx.getMessage());
		}
	}
}
