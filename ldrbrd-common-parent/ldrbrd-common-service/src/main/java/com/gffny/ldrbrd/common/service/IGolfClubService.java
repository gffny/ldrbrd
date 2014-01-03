/**
 * 
 */
package com.gffny.ldrbrd.common.service;

import java.util.List;

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
	public void addGolfClub(GolfClub golfClub);

	/**
	 * 
	 * @param golfClub
	 */
	public void addGolfClubList(List<GolfClub> golfClub);

	/**
	 * 
	 * @param manufacturer
	 * @return
	 */
	public List<GolfClub> getAllClubsByManufacturer(Manufacturer manufacturer);

	/**
	 * 
	 * @param type
	 * @return
	 */
	public List<GolfClub> getAllClubsByType(ClubType type);

	/**
	 * 
	 * @param category
	 * @return
	 */
	public List<GolfClub> getAllClubsByCategory(ClubType category);

	/**
	 * 
	 * @return
	 */
	public abstract List<GolfClub> getDefaultGolfClubList();
}
