/**
 * 
 */
package com.gffny.ldrbrd.common.model;

import java.io.Serializable;

import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.joda.time.DateTime;

import com.gffny.ldrbrd.common.model.impl.UserProfile;

/**
 * Base entity for persisted classes
 * 
 * @author jdgaffney
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@XmlAccessorType(XmlAccessType.NONE)
public abstract class CommonEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3333711223303385379L;

	@JsonIgnore
	private int version;

	@JsonIgnore
	private UserProfile createdBy;

	@JsonIgnore
	private UserProfile updatedBy;

	@JsonIgnore
	private DateTime created;

	@JsonIgnore
	private DateTime updated;

	@JsonIgnore
	private boolean skipCreatedDate = false;

	@JsonIgnore
	private long syncVersionId;

	/**
	 * User action, used as user's archive. In active, not in archive DB
	 */
	@JsonIgnore
	private Boolean isObsolete = false;

	/**
	 * System action due to time expiration or other system rules. In archive,
	 * not in Active
	 */
	@JsonIgnore
	private Boolean isArchive = false;

	/**
	 * Due to user action. In archive, not in active
	 */
	@JsonIgnore
	private Boolean isDelete = false;

	@Version
	@JsonIgnore
	public int getVersion() {
		return version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", updatable = false)
	public UserProfile getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(final UserProfile createdBy) {
		this.createdBy = createdBy;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by")
	public UserProfile getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(final UserProfile updatedBy) {
		this.updatedBy = updatedBy;
	}

	public DateTime getCreated() {
		return created;
	}

	public void setCreated(final DateTime created) {
		this.created = created;
	}

	public DateTime getUpdated() {
		return updated;
	}

	public void setUpdated(final DateTime updated) {
		this.updated = updated;
	}

	public Boolean getIsObsolete() {
		return isObsolete;
	}

	public void setIsObsolete(final Boolean isObsolete) {
		this.isObsolete = isObsolete;
	}

	public Boolean getIsArchive() {
		return isArchive;
	}

	public void setIsArchive(final Boolean isArchive) {
		this.isArchive = isArchive;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(final Boolean isDelete) {
		this.isDelete = isDelete;
	}

	@PrePersist
	public void prePersist() {

		final DateTime date = new DateTime();
		if (!skipCreatedDate || created == null) {
			setCreated(date);
		}
	}

	@PreUpdate
	public void preUpdate() {

		if (syncVersionId != 0) {
			syncVersionId++;
		}
	}

	public long getSyncVersionId() {
		return syncVersionId;
	}

	public void setSyncVersionId(final long syncVersionId) {
		this.syncVersionId = syncVersionId;
	}

	@Transient
	public boolean isSkipCreatedDate() {
		return skipCreatedDate;
	}

	public void setSkipCreatedDate(final boolean skipCreatedDate) {
		this.skipCreatedDate = skipCreatedDate;
	}
}
