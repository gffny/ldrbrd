/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import com.gffny.ldrbrd.common.model.CommonUUIDEntity;

/**
 * @author jdgaffney
 * 
 */
@NamedQueries({
	@NamedQuery(name = Club.FIND_CLUB_BY_CLUB_NAME, query = "SELECT c FROM Club c WHERE c.clubName = :clubName")
})
@Entity
@Table(name = "t_club")
public class Club extends CommonUUIDEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4364295284099333222L;

	public static final String FIND_CLUB_BY_CLUB_NAME = "findClubByClubName";

	/**
	 * 
	 */
	private String clubName;

	/**
	 * 
	 */
	private String address;

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
	 * @return
	 */
	public static Club createClub(String clubName) {
		return new Club(clubName);
	}

	/**
	 * 
	 * @param clubName
	 */
	public Club(String clubName) {
		this.clubName = clubName;
	}

	/**
	 * 
	 */
	public Club() {

	}

	/**
	 * 
	 * @return
	 */
	@Column(name = "clb_nm")
	public String getClubName() {
		return this.clubName;
	}

	/**
	 * 
	 * @param clubName
	 */
	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	/**
	 * 
	 * @return
	 */
	@Column(name = "addrss")
	public String getAddress() {
		return this.address;
	}

	/**
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 
	 * @return
	 */
	@Column(name = "mngr_nm")
	public String getManagerName() {
		return this.managerName;
	}

	/**
	 * 
	 * @param managerName
	 */
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	/**
	 * 
	 * @return
	 */
	@Column(name = "drs_cd_plcy")
	public String getDressCodePolicy() {
		return this.dressCodePolicy;
	}

	/**
	 * 
	 * @param dressCodePolicy
	 */
	public void setDressCodePolicy(String dressCodePolicy) {
		this.dressCodePolicy = dressCodePolicy;
	}

	/**
	 * 
	 * @return
	 */
	@Column(name = "grn_kpr_nm")
	public String getGreenKeeperName() {
		return this.greenKeeperName;
	}

	/**
	 * 
	 * @param greenKeeperName
	 */
	public void setGreenKeeperName(String greenKeeperName) {
		this.greenKeeperName = greenKeeperName;
	}

	/**
	 * 
	 * @return
	 */
	@Column(name = "pro_glfr_nm")
	public String getProGolferName() {
		return this.proGolferName;
	}

	/**
	 * 
	 * @param proGolferName
	 */
	public void setProGolferName(String proGolferName) {
		this.proGolferName = proGolferName;
	}
}
