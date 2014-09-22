/**
 * 
 */
package com.gffny.ldrbrd.common.model;

import javax.persistence.Transient;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Version;

/**
 * @author John D. Gaffney | gffny.com
 */
public abstract class CommonUUIDEntity extends CommonEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6631341524076524375L;

	/**
	 * 
	 */
	@Id
	@Property("id")
	private ObjectId objectId;

	/**
	 * 
	 */
	@Version
	@Property("version")
	private Long version;

	/**
	 * 
	 */
	private String name;

	/**
	 * 
	 */
	public CommonUUIDEntity() {

	}

	/**
	 * @return
	 */
	public ObjectId getId() {
		return this.objectId;
	}

	/**
	 * @return
	 */
	@Transient
	public String getIdString() {
		if (this.objectId != null) {
			return this.objectId.toString();
		}
		return null;
	}

	/**
	 * @param id
	 */
	public void setId(final ObjectId id) {
		this.objectId = id;
	}

	/**
	 * @return
	 */
	public Long getVersion() {
		return this.version;
	}

	/**
	 * @param version
	 */
	public void setVersion(Long version) {
		this.version = version;
	}

	/**
	 * @return
	 */
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
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (objectId == null ? 0 : objectId.hashCode());
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
		if (objectId == null) {
			if (other.objectId != null) {
				return false;
			}
		} else if (!objectId.equals(other.objectId)) {
			return false;
		}
		return true;
	}
}
