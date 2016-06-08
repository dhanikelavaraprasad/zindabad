package com.cyb.tms.dto;

import java.util.Date;

import com.cyb.tms.dto.base.BaseDTO;
import com.cyb.tms.entity.TmsSprintMst;
import com.cyb.tms.entity.TmsUsers;

public class TmsLeaveDTO extends BaseDTO {
	
	private Long leaveId;
	private Long projectId;
	private Long id;
	private Date startDate;
	private Date endDate;
	private String reason;
	private int duration;
	
	
	public TmsLeaveDTO() {
		super();
	}


	public TmsLeaveDTO(Long leaveId, Long projectId, Long id, Date startDate, Date endDate, String reason,
			int duration) {
		super();
		this.leaveId = leaveId;
		this.projectId = projectId;
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.duration = duration;
	}


	public Long getLeaveId() {
		return leaveId;
	}


	public void setLeaveId(Long leaveId) {
		this.leaveId = leaveId;
	}


	public Long getProjectId() {
		return projectId;
	}


	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	
	

}