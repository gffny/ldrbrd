/**
 * 
 */
package com.gffny.ldrbrd.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author jdgaffney
 *
 */
@Entity
@Table(name="t_user")
public class UserProfile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5309751265778916250L;
	
	@Id
	@Column(name="usr_id")
	private Integer id;
	
	@Column(name="is_nbld")
	private boolean isEnabled;

	/* CONSTRUCTORS */
	
	public UserProfile(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	/* ACCESSOR/MUTATOR METHODS */
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the isEnabled
	 */
	public boolean isEnabled() {
		return isEnabled;
	}

	/**
	 * @param isEnabled the isEnabled to set
	 */
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

}
