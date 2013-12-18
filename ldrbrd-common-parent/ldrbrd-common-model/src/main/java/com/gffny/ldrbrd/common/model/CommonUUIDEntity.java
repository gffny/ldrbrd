/**
 * 
 */
package com.gffny.ldrbrd.common.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author jdgaffney
 * 
 */
@MappedSuperclass
public abstract class CommonUUIDEntity extends CommonEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public static final String ID_FIELD = "id";

	/**
	 * 
	 */
	//@Id
	@XmlElement
	private String id;

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (id == null ? 0 : id.hashCode());
		return result;
	}

	/**
	 * equals method
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CommonUUIDEntity other = (CommonUUIDEntity) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 */
	public CommonUUIDEntity() {
		if (getId() == null) {
			id = UUID.randomUUID().toString();
		}
	}

	/**
	 * 
	 * @return
	 */
	@Column(name = ID_FIELD, nullable = false, length = 36)
	@Id
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(final String id) {
		this.id = id;
	}

}
