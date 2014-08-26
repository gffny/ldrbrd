/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl.mongo;

import java.util.List;

import org.mongodb.morphia.annotations.Entity;

import com.gffny.ldrbrd.common.model.CommonUUIDEntity;

/**
 * @author John D. Gaffney | gffny.com
 */
@Entity
public class Club extends CommonUUIDEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4364295284099333222L;

	/**
	 * 
	 */
	public static final String FIND_CLUB_BY_CLUB_NAME = "findClubByClubName";

	/**
	 * 
	 */
	private String address;

	/**
	 * 
	 */
	private String city;

	/**
	 * 
	 */
	private String managerName;

	/**
	 * 
	 */
	private String dressCodePolicy;

	/**
	 * 
	 */
	private String greenKeeperName;

	/**
	 * 
	 */
	private String proGolferName;

	/**
	 * 
	 */
	private List<Course> courseList;

	/**
	 * @return
	 */
	public static Club createClub(String clubName) {
		return new Club(clubName);
	}

	/**
	 * @param clubName
	 */
	public Club(String clubName) {
		this.setName(clubName);
	}

	/**
	 * 
	 */
	public Club() {

	}

	/**
	 * @return
	 */
	public String getClubName() {
		return this.getName();
	}

	/**
	 * @param clubName
	 */
	public void setClubName(String clubName) {
		this.setName(clubName);
	}

	/**
	 * @return
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return
	 */
	public String getManagerName() {
		return this.managerName;
	}

	/**
	 * @param managerName
	 */
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	/**
	 * @return
	 */
	public String getDressCodePolicy() {
		return this.dressCodePolicy;
	}

	/**
	 * @param dressCodePolicy
	 */
	public void setDressCodePolicy(String dressCodePolicy) {
		this.dressCodePolicy = dressCodePolicy;
	}

	/**
	 * @return
	 */
	public String getGreenKeeperName() {
		return this.greenKeeperName;
	}

	/**
	 * @param greenKeeperName
	 */
	public void setGreenKeeperName(String greenKeeperName) {
		this.greenKeeperName = greenKeeperName;
	}

	/**
	 * @return
	 */
	public String getProGolferName() {
		return this.proGolferName;
	}

	/**
	 * @param proGolferName
	 */
	public void setProGolferName(String proGolferName) {
		this.proGolferName = proGolferName;
	}

	/**
	 * @return
	 */
	public List<Course> getCourseList() {
		return this.courseList;
	}

	/**
	 * @param courseList
	 */
	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}
}
