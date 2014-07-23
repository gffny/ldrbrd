/**
 * 
 */
package com.gffny.ldrbrd.common.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.joda.time.DateTime;

/**
 * Base entity for persisted classes
 * 
 * @author John D. Gaffney | gffny.com
 */
@SuppressWarnings("restriction")
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@XmlAccessorType(XmlAccessType.NONE)
public abstract class CommonEntity implements Serializable {

	/** */
	private static final long serialVersionUID = -3333711223303385379L;

	/** */
	@JsonIgnore
	private int version;

	/** */
	@JsonIgnore
	private String createdBy;

	/** */
	@JsonIgnore
	private String updatedBy;

	/** */
	@JsonIgnore
	private DateTime createdDate;

	/** */
	@JsonIgnore
	private DateTime updatedDate;

	/** */
	@JsonIgnore
	private boolean skipCreatedDate = false;

	/** */
	@JsonIgnore
	private int syncVersionId;

	/** */
	@JsonIgnore
	private boolean isNew = false;

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

	/**
	 * 
	 * @return
	 */
	// @Version
	// @JsonIgnore
	// //@Column(name = "vrsn")
	@Transient
	public int getVersion() {
		return version;
	}

	/**
	 * 
	 * @param version
	 */
	public void setVersion(final int version) {
		this.version = version;
	}

	/**
	 * 
	 * @return
	 */
	// @JsonIgnore
	// //@Column(name = "crtdby")
	@Transient
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * 
	 * @param createdBy
	 */
	public void setCreatedBy(final String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * 
	 * @return
	 */
	@JsonIgnore
	// @Column(name = "updtdby")
	@Transient
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * 
	 * @param updatedBy
	 */
	public void setUpdatedBy(final String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * 
	 * @return
	 */
	@JsonIgnore
	// @Column(name = "crtddt")
	@Transient
	public Date getCreatedDate() {
		if (getCreatedDateDT() != null) {
			return this.getCreatedDateDT().toDate();
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	@JsonIgnore
	@Transient
	public DateTime getCreatedDateDT() {
		return this.createdDate;
	}

	/**
	 * 
	 * @param createdDate
	 */
	public void setCreatedDate(final Date createdDate) {
		this.setCreatedDateDT(new DateTime(createdDate));
	}

	/**
	 * 
	 * @param createdDate
	 */
	@JsonIgnore
	@Transient
	public void setCreatedDateDT(final DateTime createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * 
	 * @return
	 */
	@JsonIgnore
	// @Column(name = "updtddt")
	@Transient
	public Date getUpdatedDate() {
		if (getUpdatedDateDT() != null) {
			return getUpdatedDateDT().toDate();
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	@JsonIgnore
	@Transient
	public DateTime getUpdatedDateDT() {
		return this.updatedDate;
	}

	/**
	 * 
	 * @param updatedDate
	 */
	public void setUpdatedDate(final Date updatedDate) {
		this.setUpdatedDateDT(new DateTime(updatedDate));
	}

	/**
	 * 
	 * @param updateDatedDate
	 */
	@JsonIgnore
	@Transient
	public void setUpdatedDateDT(final DateTime updateDatedDate) {
		this.updatedDate = updateDatedDate;
	}

	/**
	 * 
	 * @return
	 */
	@JsonIgnore
	// @Column(name = "obslt", columnDefinition = "BIT", length = 1)
	@Transient
	public Boolean isObsolete() {
		return isObsolete;
	}

	/**
	 * 
	 * @param isObsolete
	 */
	public void setObsolete(final Boolean isObsolete) {
		this.isObsolete = isObsolete;
	}

	/**
	 * 
	 * @return
	 */
	@JsonIgnore
	// @Column(name = "archv", columnDefinition = "BIT", length = 1)
	@Transient
	public Boolean isArchive() {
		return isArchive;
	}

	/**
	 * 
	 * @param isArchive
	 */
	public void setArchive(final Boolean isArchive) {
		this.isArchive = isArchive;
	}

	/**
	 * 
	 * @return
	 */
	@JsonIgnore
	// @Column(name = "dlt", columnDefinition = "BIT", length = 1)
	@Transient
	public Boolean isDelete() {
		return isDelete;
	}

	/**
	 * 
	 * @param isDelete
	 */
	public void setDelete(final Boolean isDelete) {
		this.isDelete = isDelete;
	}

	/**
	 * 
	 */
	@PrePersist
	public void prePersist() {

		final DateTime date = new DateTime();
		if (!skipCreatedDate || createdDate == null) {
			setCreatedDateDT(date);
		}
	}

	/**
	 * 
	 */
	@PreUpdate
	public void preUpdate() {

		if (syncVersionId != 0) {
			syncVersionId++;
		}
	}

	/**
	 * 
	 */
	@JsonIgnore
	// @Column(name = "syncvrsnid")
	@Transient
	public int getSyncVersionId() {
		return syncVersionId;
	}

	/**
	 * 
	 * @param syncVersionId
	 */
	public void setSyncVersionId(final int syncVersionId) {
		this.syncVersionId = syncVersionId;
	}

	/**
	 * 
	 * @return
	 */
	@JsonIgnore
	@Transient
	// @Column(name = "skpcrtddt", columnDefinition = "BIT", length = 1)
	public boolean isSkipCreatedDate() {
		return skipCreatedDate;
	}

	/**
	 * 
	 * @param skipCreatedDate
	 */
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
