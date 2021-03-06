package com.cyb.tms.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.cyb.tms.dao.TmsEffortsDAO;
import com.cyb.tms.dao.TmsSprintDAO;
import com.cyb.tms.dto.TmsEffortsDTO;
import com.cyb.tms.entity.TmsEfforts;
import com.cyb.tms.entity.TmsSprintMst;
import com.cyb.tms.entity.TmsSubtask;
import com.cyb.tms.entity.UserStoryStaus;
import com.cyb.tms.util.HibernateUtil;

@Repository
public class TmsEffortsDAOImpl implements TmsEffortsDAO {
	
	
	@Value("${tms.status.backlog}")
	private String backlog;
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	private TmsSprintDAO tmsSprintDAO;

	@Override
	public long createEffort(TmsEffortsDTO tmseffortDTO) {
		return (Long)hibernateUtil.create(setDtoToDo(tmseffortDTO));
	}

	@Override
	public TmsEfforts updateEffort(TmsEfforts effort) {
		return hibernateUtil.update(effort);
	}

	@Override
	public List<TmsEfforts> getAllEfforts() {
		return hibernateUtil.fetchAll(TmsEfforts.class);
	}

	@Override
	public TmsEfforts getEffort(long id) {
		return hibernateUtil.fetchById(id, TmsEfforts.class);
	}

	@Override
	public Double getTotalHoursLoggedByUser(Long userId, Long projectId) {
		List<TmsSubtask> subTasks = getCurrentUserSubtasksBySprint(userId, projectId);
		return (subTasks != null && subTasks.size() > 0) ? getTotalHoursLoggedByUserInSprint(projectId, getSubTaskIds(subTasks)) : 0.0 ;
	}
	
	private Double getTotalHoursLoggedByUserInSprint(Long projectId, List<Long> subTaskIds) {
		TmsSprintMst sprint = tmsSprintDAO.getActiveSprint(projectId);
		Double efforts = 0.0;
		if(sprint != null) {
			efforts = (Double) hibernateUtil.getCurrentSession().createCriteria(TmsEfforts.class, "ef")
					.createAlias("tmsSprintMst", "sprint")
					.createAlias("tmsSubtask", "sub")
					.setProjection( Projections.sum("ef.loggedHours"))
					.add(Restrictions.in("sub.subtaskId", subTaskIds))
					.add(Restrictions.eq("sprint.sprintId", sprint.getSprintId())).uniqueResult();
		}
		return (efforts == null) ? 0.0 : efforts;
	}

	private List<Long> getSubTaskIds(List<TmsSubtask> subTasks) {
		List<Long> ids = new ArrayList<Long>();
		for (TmsSubtask tmsSubtask : subTasks) {
			ids.add(tmsSubtask.getSubtaskId());
		}
		return ids;
	}

	@Override
	public List<LinkedHashMap<String, Object>> getCurrentUserEffortsBySprint(Long userId,
			Long projectId) {
		List<TmsSubtask> subTasks = getCurrentUserSubtasksBySprint(userId, projectId);
		return parseSubtasks(projectId, subTasks);
	}

	@SuppressWarnings("unchecked")
	private Double getTotalEffortsBySubtask(Long projectId, Long subtaskId) {
		
		TmsSprintMst sprint = tmsSprintDAO.getActiveSprint(projectId);
		Double efforts = 0.0;
		if(sprint != null) {
			efforts = (Double) hibernateUtil.getCurrentSession().createCriteria(TmsEfforts.class, "ef")
					.createAlias("tmsSprintMst", "sprint")
					.createAlias("tmsSubtask", "sub")
					.setProjection( Projections.sum("ef.loggedHours"))
					.add(Restrictions.eq("sub.subtaskId", subtaskId))
					.add(Restrictions.eq("sprint.sprintId", sprint.getSprintId())).uniqueResult();
		}
		return (efforts == null) ? 0.0 : efforts;
	}

	@SuppressWarnings("unchecked")
	private List<TmsSubtask> getCurrentUserSubtasksBySprint(Long userId, Long projectId) {
		TmsSprintMst sprint = tmsSprintDAO.getActiveSprint(projectId);
		List<TmsSubtask> tmsSubtask = null;
		if(sprint != null) {
			List<Long> backLogIds = getBackLogSubtaskIds();
			tmsSubtask = hibernateUtil.getCurrentSession().createCriteria(UserStoryStaus.class, "uss")
					.createAlias("tmsSprintMst", "sprint")
					.createAlias("tmsSubtask", "sub")
					.createAlias("tmsUsersByAssignedTo", "users")
					.setProjection( Projections.distinct(Projections.property("tmsSubtask")))
					.add(backLogIds.size() > 0 ? Restrictions.not(Restrictions.in("sub.subtaskId", backLogIds)): Restrictions.sqlRestriction("(1=1)"))
					.add(Restrictions.eq("users.id", userId))
					.add(Restrictions.eq("sprint.sprintId", sprint.getSprintId())).list();
		}
		return tmsSubtask;
	}
	
	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Long> getBackLogSubtaskIds() {
		List<Long> subtaskIds = hibernateUtil.getCurrentSession().createCriteria(UserStoryStaus.class, "uss")
				.createAlias("tmsSubtask", "sub")
				.createAlias("tmsStatusMst", "tsm")
				.add(Subqueries.propertyIn("uss.id",  DetachedCriteria.forClass(UserStoryStaus.class, "status")
						.add(Restrictions.eqProperty("uss.tmsSubtask", "status.tmsSubtask"))
						.setProjection(Projections.max("status.id"))))
						.add(Restrictions.eq("tsm.status", "BACKLOG"))
				.setProjection( Projections.distinct(Projections.property("sub.subtaskId"))).list();
		return subtaskIds;
	}
	
	private List<LinkedHashMap<String, Object>> parseSubtasks(Long projectId, List<TmsSubtask> subtasks) {
		List<LinkedHashMap<String, Object>> userSubtasks = new ArrayList<LinkedHashMap<String, Object>>();
		for (TmsSubtask tmsSubtask : subtasks) {
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("subtaskId", tmsSubtask.getSubtaskId());
			map.put("jiraId", tmsSubtask.getJiraId());
			map.put("scope", tmsSubtask.getScope());
			map.put("type", tmsSubtask.getType());
			Double loggedHours = getTotalEffortsBySubtask(projectId, tmsSubtask.getSubtaskId());
			map.put("remaingEfforts", (tmsSubtask.getEfforts() - loggedHours));
			userSubtasks.add(map);
		}
		return userSubtasks;
	}
	
	private TmsEfforts setDtoToDo(TmsEffortsDTO tmseffortDTO) {
		TmsSubtask subtask=hibernateUtil.fetchById( tmseffortDTO.getSubtaskId(), TmsSubtask.class);
		TmsSprintMst sprint = tmsSprintDAO.getActiveSprint(tmseffortDTO.getProjectId());
		TmsEfforts efforts =new TmsEfforts();
		efforts.setTmsSprintMst(sprint);
		efforts.setTmsSubtask(subtask);
		efforts.setLoggedDate(new Date());
		efforts.setLoggedHours(tmseffortDTO.getLoggedHours());
		return efforts;
	}
}
