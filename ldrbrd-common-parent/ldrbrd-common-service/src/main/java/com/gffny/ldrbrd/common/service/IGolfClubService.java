/**
 * 
 */
package com.gffny.ldrbrd.common.service;

import java.util.List;

import com.gffny.ldrbrd.common.exception.ServiceException;
import com.gffny.ldrbrd.common.model.enums.ClubType;
import com.gffny.ldrbrd.common.model.enums.Manufacturer;
import com.gffny.ldrbrd.common.model.impl.GolfClub;

/**
 * @author jdgaffney
 * 
 */
public interface IGolfClubService {

	/**
	 * 
	 * @param golfClub
	 */
	public void addGolfClub(GolfClub golfClub) throws ServiceException;

	/**
	 * 
	 * @param golfClub
	 */
	public void addGolfClubList(List<GolfClub> golfClub)
			throws ServiceException;

	/**
	 * 
	 * @param manufacturer
	 * @return
	 */
	public List<GolfClub> getAllClubsByManufacturer(Manufacturer manufacturer)
			throws ServiceException;

	/**
	 * 
	 * @param type
	 * @return
	 */
	public List<GolfClub> getAllClubsByType(ClubType type)
			throws ServiceException;

	/**
	 * 
	 * @param category
	 * @return
	 */
	public List<GolfClub> getAllClubsByCategory(ClubType category)
			throws ServiceException;

	/**
	 * 
	 * @return
	 */
	public abstract List<GolfClub> getDefaultGolfClubList()
			throws ServiceException;
}
