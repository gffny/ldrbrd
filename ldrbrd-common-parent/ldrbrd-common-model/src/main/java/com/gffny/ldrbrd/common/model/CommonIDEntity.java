/**
 * 
 */
package com.gffny.ldrbrd.common.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author John D. Gaffney | gffny.com
 * 
 */
@MappedSuperclass
public class CommonIDEntity extends CommonEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9038508401519929600L;

	/** */
	private int id;

	/**
	 * 
	 * @return
	 */
	@Id
	@Column(name = Constant.DB_ID_FIELD)
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
}
