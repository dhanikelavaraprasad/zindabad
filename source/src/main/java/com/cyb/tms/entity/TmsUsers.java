package com.cyb.tms.entity;
// Generated May 19, 2016 12:17:39 PM by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cyb.tms.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * TmsUsers generated by hbm2java
 */
@Entity
@Table(name = "tms_users", catalog = "TaskManagement", uniqueConstraints = {
		@UniqueConstraint(columnNames = "USER_NAME"), @UniqueConstraint(columnNames = "EMAIL") })
public class TmsUsers extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9198550957284072520L;
	private Long id;
	private TmsProject tmsProject;
	private String email;
	private String isActive;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private String userName;
	private String userRole;
	private Set<TmsLeaveMst> tmsLeaveMsts = new HashSet<TmsLeaveMst>(0);
	private Set<TmsCodeReview> tmsCodeReviewsForDeveloper = new HashSet<TmsCodeReview>(0);
	private Set<TmsSubtask> tmsSubtasks = new HashSet<TmsSubtask>(0);
	private Set<TmsCodeReview> tmsCodeReviewsForFixedBy = new HashSet<TmsCodeReview>(0);
	private Set<TmsUserModuleSprint> tmsUserModuleSprints = new HashSet<TmsUserModuleSprint>(0);
	private Set<TmsStoryMst> tmsStoryMsts = new HashSet<TmsStoryMst>(0);
	private Set<TmsSprintUser> tmsSprintUsers = new HashSet<TmsSprintUser>(0);
	private Set<UserStoryStaus> userStoryStauses = new HashSet<UserStoryStaus>(0);

	public TmsUsers() {
	}

	public TmsUsers(String email, String isActive, String password, String userName, String userRole) {
		this.email = email;
		this.isActive = isActive;
		this.password = password;
		this.userName = userName;
		this.userRole = userRole;
	}

	public TmsUsers(TmsProject tmsProject, String email, String isActive, String password, String userName,
			String userRole, Set<TmsLeaveMst> tmsLeaveMsts, Set<TmsCodeReview> tmsCodeReviewsForDeveloper,
			Set<TmsSubtask> tmsSubtasks, Set<TmsCodeReview> tmsCodeReviewsForFixedBy,
			Set<TmsUserModuleSprint> tmsUserModuleSprints, Set<TmsStoryMst> tmsStoryMsts,
			Set<TmsSprintUser> tmsSprintUsers, Set<UserStoryStaus> userStoryStauses) {
		this.tmsProject = tmsProject;
		this.email = email;
		this.isActive = isActive;
		this.password = password;
		this.userName = userName;
		this.userRole = userRole;
		this.tmsLeaveMsts = tmsLeaveMsts;
		this.tmsCodeReviewsForDeveloper = tmsCodeReviewsForDeveloper;
		this.tmsSubtasks = tmsSubtasks;
		this.tmsCodeReviewsForFixedBy = tmsCodeReviewsForFixedBy;
		this.tmsUserModuleSprints = tmsUserModuleSprints;
		this.tmsStoryMsts = tmsStoryMsts;
		this.tmsSprintUsers = tmsSprintUsers;
		this.userStoryStauses = userStoryStauses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECT_ID")
	public TmsProject getTmsProject() {
		return this.tmsProject;
	}

	public void setTmsProject(TmsProject tmsProject) {
		this.tmsProject = tmsProject;
	}

	@Column(name = "EMAIL", unique = true, nullable = false, length = 45)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "IS_ACTIVE", nullable = false, length = 9)
	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@JsonIgnore
	@Column(name = "PASSWORD", nullable = false, length = 80)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "USER_NAME", unique = true, nullable = false, length = 45)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "USER_ROLE", nullable = false, length = 9)
	public String getUserRole() {
		return this.userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tmsUsers")
	public Set<TmsLeaveMst> getTmsLeaveMsts() {
		return this.tmsLeaveMsts;
	}

	public void setTmsLeaveMsts(Set<TmsLeaveMst> tmsLeaveMsts) {
		this.tmsLeaveMsts = tmsLeaveMsts;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tmsUsersByDeveloper")
	public Set<TmsCodeReview> getTmsCodeReviewsForDeveloper() {
		return this.tmsCodeReviewsForDeveloper;
	}

	public void setTmsCodeReviewsForDeveloper(Set<TmsCodeReview> tmsCodeReviewsForDeveloper) {
		this.tmsCodeReviewsForDeveloper = tmsCodeReviewsForDeveloper;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tmsUsers")
	public Set<TmsSubtask> getTmsSubtasks() {
		return this.tmsSubtasks;
	}

	public void setTmsSubtasks(Set<TmsSubtask> tmsSubtasks) {
		this.tmsSubtasks = tmsSubtasks;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tmsUsersByFixedBy")
	public Set<TmsCodeReview> getTmsCodeReviewsForFixedBy() {
		return this.tmsCodeReviewsForFixedBy;
	}

	public void setTmsCodeReviewsForFixedBy(Set<TmsCodeReview> tmsCodeReviewsForFixedBy) {
		this.tmsCodeReviewsForFixedBy = tmsCodeReviewsForFixedBy;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tmsUsers")
	public Set<TmsUserModuleSprint> getTmsUserModuleSprints() {
		return this.tmsUserModuleSprints;
	}

	public void setTmsUserModuleSprints(Set<TmsUserModuleSprint> tmsUserModuleSprints) {
		this.tmsUserModuleSprints = tmsUserModuleSprints;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tmsUsers")
	public Set<TmsStoryMst> getTmsStoryMsts() {
		return this.tmsStoryMsts;
	}

	public void setTmsStoryMsts(Set<TmsStoryMst> tmsStoryMsts) {
		this.tmsStoryMsts = tmsStoryMsts;
	}
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tmsUsers")
	public Set<TmsSprintUser> getTmsSprintUsers() {
		return this.tmsSprintUsers;
	}

	public void setTmsSprintUsers(Set<TmsSprintUser> tmsSprintUsers) {
		this.tmsSprintUsers = tmsSprintUsers;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tmsUsers")
	public Set<UserStoryStaus> getUserStoryStauses() {
		return this.userStoryStauses;
	}

	public void setUserStoryStauses(Set<UserStoryStaus> userStoryStauses) {
		this.userStoryStauses = userStoryStauses;
	}

}
