package com.cyb.tms.entity;
// Generated May 17, 2016 12:38:28 PM by Hibernate Tools 4.3.1.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.cyb.tms.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * TmsSprintMst generated by hbm2java
 */
@Entity
@Table(name = "tms_sprint_mst", catalog = "TaskManagement")
public class TmsSprintMst extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9012000666173512555L;
	private Long sprintId;
	private Date sprintEndDate;
	private int sprintHours;
	private String sprintName;
	private Date sprintStartDate;
	private String sprintStatus;
	private int sprintVelocity;
	private Set<TmsLeaveMst> tmsLeaveMsts = new HashSet<TmsLeaveMst>(0);
	private Set<TmsEfforts> tmsEffortses = new HashSet<TmsEfforts>(0);
	private Set<UserStoryStaus> userStoryStauses = new HashSet<UserStoryStaus>(0);

	public TmsSprintMst() {
	}

	public TmsSprintMst(Date sprintEndDate, int sprintHours, String sprintName, Date sprintStartDate,
			String sprintStatus, int sprintVelocity) {
		this.sprintEndDate = sprintEndDate;
		this.sprintHours = sprintHours;
		this.sprintName = sprintName;
		this.sprintStartDate = sprintStartDate;
		this.sprintStatus = sprintStatus;
		this.sprintVelocity = sprintVelocity;
	}

	public TmsSprintMst(Date sprintEndDate, int sprintHours, String sprintName, Date sprintStartDate,
			String sprintStatus, int sprintVelocity, Set<TmsLeaveMst> tmsLeaveMsts, Set<TmsEfforts> tmsEffortses,
			Set<UserStoryStaus> userStoryStauses) {
		this.sprintEndDate = sprintEndDate;
		this.sprintHours = sprintHours;
		this.sprintName = sprintName;
		this.sprintStartDate = sprintStartDate;
		this.sprintStatus = sprintStatus;
		this.sprintVelocity = sprintVelocity;
		this.tmsLeaveMsts = tmsLeaveMsts;
		this.tmsEffortses = tmsEffortses;
		this.userStoryStauses = userStoryStauses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "SPRINT_ID", unique = true, nullable = false)
	public Long getSprintId() {
		return this.sprintId;
	}

	public void setSprintId(Long sprintId) {
		this.sprintId = sprintId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SPRINT_END_DATE", nullable = false, length = 10)
	public Date getSprintEndDate() {
		return this.sprintEndDate;
	}

	public void setSprintEndDate(Date sprintEndDate) {
		this.sprintEndDate = sprintEndDate;
	}

	@Column(name = "SPRINT_HOURS", nullable = false)
	public int getSprintHours() {
		return this.sprintHours;
	}

	public void setSprintHours(int sprintHours) {
		this.sprintHours = sprintHours;
	}

	@Column(name = "SPRINT_NAME", nullable = false, length = 45)
	public String getSprintName() {
		return this.sprintName;
	}

	public void setSprintName(String sprintName) {
		this.sprintName = sprintName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SPRINT_START_DATE", nullable = false, length = 10)
	public Date getSprintStartDate() {
		return this.sprintStartDate;
	}

	public void setSprintStartDate(Date sprintStartDate) {
		this.sprintStartDate = sprintStartDate;
	}

	@Column(name = "SPRINT_STATUS", nullable = false, length = 6)
	public String getSprintStatus() {
		return this.sprintStatus;
	}

	public void setSprintStatus(String sprintStatus) {
		this.sprintStatus = sprintStatus;
	}

	@Column(name = "SPRINT_VELOCITY", nullable = false)
	public int getSprintVelocity() {
		return this.sprintVelocity;
	}

	public void setSprintVelocity(int sprintVelocity) {
		this.sprintVelocity = sprintVelocity;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tmsSprintMst")
	public Set<TmsLeaveMst> getTmsLeaveMsts() {
		return this.tmsLeaveMsts;
	}

	public void setTmsLeaveMsts(Set<TmsLeaveMst> tmsLeaveMsts) {
		this.tmsLeaveMsts = tmsLeaveMsts;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tmsSprintMst")
	public Set<TmsEfforts> getTmsEffortses() {
		return this.tmsEffortses;
	}

	public void setTmsEffortses(Set<TmsEfforts> tmsEffortses) {
		this.tmsEffortses = tmsEffortses;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tmsSprintMst")
	public Set<UserStoryStaus> getUserStoryStauses() {
		return this.userStoryStauses;
	}

	public void setUserStoryStauses(Set<UserStoryStaus> userStoryStauses) {
		this.userStoryStauses = userStoryStauses;
	}
}
