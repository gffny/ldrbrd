/**
 * 
 */
package com.gffny.ldrbrd.common.model.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.gffny.ldrbrd.common.model.CommonIDEntity;
import com.gffny.ldrbrd.common.model.Constant;
import com.gffny.ldrbrd.common.utils.CollectionUtils;

/**
 * @author John D. Gaffney | gffny.com
 */
@NamedQueries(@NamedQuery(name = Society.FIND_BY_SOCIETY_NAME, query = "select s from Society s where s.name = :societyName"))
@Entity
@Table(name = Constant.DB_TABLE_SOCIETY)
public class Society extends CommonIDEntity {

	/** */
	private static final long serialVersionUID = -412837881642668059L;

	/** */
	public static final String FIND_BY_SOCIETY_NAME = "SOCIETY_FIND_BY_NAME";

	/** */
	@JsonIgnore
	private String name;

	/** */
	@JsonIgnore
	private Golfer president;

	/** */
	@JsonIgnore
	private List<Golfer> membershipList;

	/**
	 * @param societyName
	 * @return
	 */
	public static Society createNewSociety(String societyName) {
		return new Society(societyName);
	}

	/**
	 * 
	 */
	public Society() {
		// hibernate required non-private zero-argument constructor
	}

	/**
	 * @param name
	 */
	private Society(String name) {
		this.name = name;
	}

	/**
	 * @return
	 */
	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the president
	 */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "president_id", nullable = false)
	public Golfer getPresident() {
		return president;
	}

	/**
	 * @param president
	 *            the president to set
	 */
	public void setPresident(Golfer president) {
		this.president = president;
	}

	/**
	 * @return the membershipList
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = Constant.DB_TABLE_SOCIETY_MEMBERSHIP, joinColumns = @JoinColumn(name = "society_id"), inverseJoinColumns = @JoinColumn(name = "golfer_id"))
	public List<Golfer> getMembershipList() {
		// check if the membership list is null and return an empty array if so
		return membershipList != null ? membershipList
				: new ArrayList<Golfer>();
	}

	/**
	 * @param membershipList
	 *            the membershipList to set
	 */
	public void setMembershipList(List<Golfer> membershipList) {
		this.membershipList = membershipList;
	}

	/**
	 * 
	 * @param newMember
	 */
	@Transient
	public void addMember(Golfer newMember) {
		CollectionUtils.safeAdd(this.membershipList, newMember);
	}

	/**
	 * 
	 * @param newMemberList
	 */
	@Transient
	public void addMember(List<Golfer> newMemberList) {
		CollectionUtils.safeAddAll(this.membershipList, newMemberList);
	}

}
