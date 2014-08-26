/**
 * 
 */
package com.gffny.ldrbrd.common.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author John D. Gaffney | gffny.com
 */
@MappedSuperclass
public class CommonIDEntity extends CommonEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9038508401519929600L;

	/** */
	private int id;

	/** */
	@JsonIgnore
	private int version;

	/**
	 * @return
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = Constant.DB_ID_FIELD)
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return
	 */
	@Transient
	public int getVersion() {
		return version;
	}

	/**
	 * @param version
	 */
	public void setVersion(final int version) {
		this.version = version;
	}
}
