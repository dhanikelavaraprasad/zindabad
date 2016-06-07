package com.cyb.tms.entity;
// Generated May 17, 2016 12:38:28 PM by Hibernate Tools 4.3.1.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.cyb.tms.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * TmsLeaveMst generated by hbm2java
 */
@Entity
@Table(name = "tms_leave_mst", catalog = "TaskManagement")
public class TmsLeaveMst extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6667539893351431644L;
	private Long leaveId;
	private TmsSprintMst tmsSprintMst;
	private TmsUsers tmsUsers;
	private Date startDate;
	private Date endDate;
	private String reason;
	private int duration;
	

	public TmsLeaveMst() {
	}

	public TmsLeaveMst(TmsSprintMst tmsSprintMst, TmsUsers tmsUsers, Date startDate, Date endDate,
			String reason, int duration) {
		super();
		
		this.tmsSprintMst = tmsSprintMst;
		this.tmsUsers = tmsUsers;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.duration = duration;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "LEAVE_ID", unique = true, nullable = false)
	public Long getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(Long leaveId) {
		this.leaveId = leaveId;
	}
	
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SPRINTID", nullable = false)
	public TmsSprintMst getTmsSprintMst() {
		return tmsSprintMst;
	}

	public void setTmsSprintMst(TmsSprintMst tmsSprintMst) {
		this.tmsSprintMst = tmsSprintMst;
	}
	
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERID", nullable = false)
	public TmsUsers getTmsUsers() {
		return tmsUsers;
	}

	public void setTmsUsers(TmsUsers tmsUsers) {
		this.tmsUsers = tmsUsers;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "STARTDATE", nullable = false, length = 10)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ENDDATE", nullable = false, length = 10)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Column(name = "REASON", nullable = false, length = 9)
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	@Column(name = "DURATION", nullable = false, length = 9)
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	/*public TmsLeaveMst(TmsSprintMst tmsSprintMst, TmsUsers tmsUsers, Date date, String leavetype) {
		this.tmsSprintMst = tmsSprintMst;
		this.tmsUsers = tmsUsers;
		this.date = date;
		this.leavetype = leavetype;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "LEAVE_ID", unique = true, nullable = false)
	public Long getLeaveId() {
		return this.leaveId;
	}

	public void setLeaveId(Long leaveId) {
		this.leaveId = leaveId;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SPRINTID", nullable = false)
	public TmsSprintMst getTmsSprintMst() {
		return this.tmsSprintMst;
	}

	public void setTmsSprintMst(TmsSprintMst tmsSprintMst) {
		this.tmsSprintMst = tmsSprintMst;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USERID", nullable = false)
	public TmsUsers getTmsUsers() {
		return this.tmsUsers;
	}

	public void setTmsUsers(TmsUsers tmsUsers) {
		this.tmsUsers = tmsUsers;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE", nullable = false, length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "LEAVETYPE", nullable = false, length = 9)
	public String getLeavetype() {
		return this.leavetype;
	}

	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
	}*/
}
