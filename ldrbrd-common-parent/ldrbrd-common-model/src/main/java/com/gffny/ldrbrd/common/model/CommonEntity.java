/**
 * 
 */
package com.gffny.ldrbrd.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

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
	private String createdBy;
	// private UserProfile createdBy;

	@JsonIgnore
	private String updatedBy;
	// private UserProfile updatedBy;

	@JsonIgnore
	private DateTime createdDate;

	@JsonIgnore
	private DateTime updatedDate;

	@JsonIgnore
	private boolean skipCreatedDate = false;

	@JsonIgnore
	private int syncVersionId;

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
	@Column(name = "vrsn")
	public int getVersion() {
		return version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	// // @ManyToOne(fetch = FetchType.LAZY)
	// // @JoinColumn(name = "crtdby", updatable = false)
	// @Column(name = "crtdby")
	// public UserProfile getCreatedBy() {
	// return createdBy;
	// }
	//
	// public void setCreatedBy(final UserProfile createdBy) {
	// this.createdBy = createdBy;
	// }
	//
	// // @ManyToOne(fetch = FetchType.LAZY)
	// // @JoinColumn(name = "updtdby", updatable = false)
	// @Column(name = "updtdby")
	// public UserProfile getUpdatedBy() {
	// return updatedBy;
	// }
	//
	// public void setUpdatedBy(final UserProfile updatedBy) {
	// this.updatedBy = updatedBy;
	// }

	@Column(name = "crtdby")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(final String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "updtdby")
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(final String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	@Column(name = "crtddt")
	public DateTime getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(final DateTime createdDate) {
		this.createdDate = createdDate;
	}

	@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
	@Column(name = "updtddt")
	public DateTime getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(final DateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(name = "obslt", columnDefinition = "BIT", length = 1)
	public Boolean getIsObsolete() {
		return isObsolete;
	}

	public void setIsObsolete(final Boolean isObsolete) {
		this.isObsolete = isObsolete;
	}

	@Column(name = "archv", columnDefinition = "BIT", length = 1)
	public Boolean getIsArchive() {
		return isArchive;
	}

	public void setIsArchive(final Boolean isArchive) {
		this.isArchive = isArchive;
	}

	@Column(name = "dlt", columnDefinition = "BIT", length = 1)
	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(final Boolean isDelete) {
		this.isDelete = isDelete;
	}

	@PrePersist
	public void prePersist() {

		final DateTime date = new DateTime();
		if (!skipCreatedDate || createdDate == null) {
			setCreatedDate(date);
		}
	}

	@PreUpdate
	public void preUpdate() {

		if (syncVersionId != 0) {
			syncVersionId++;
		}
	}

	@Column(name = "syncvrsnid")
	public int getSyncVersionId() {
		return syncVersionId;
	}

	public void setSyncVersionId(final int syncVersionId) {
		this.syncVersionId = syncVersionId;
	}

	@Transient
	@Column(name = "skpcrtddt", columnDefinition = "BIT", length = 1)
	public boolean isSkipCreatedDate() {
		return skipCreatedDate;
	}

	public void setSkipCreatedDate(final boolean skipCreatedDate) {
		this.skipCreatedDate = skipCreatedDate;
	}

	/**
	 * @param manufacturer
	 * @return
	 */
	protected <T> T getDefaultNotNullValue(T value, T defaultValue) {
		return null != value ? value : defaultValue;
	}
}
