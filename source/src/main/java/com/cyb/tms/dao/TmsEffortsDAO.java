package com.cyb.tms.dao;

import java.util.LinkedHashMap;
import java.util.List;

import com.cyb.tms.dto.TmsEffortsDTO;
import com.cyb.tms.entity.TmsEfforts;
import com.cyb.tms.entity.TmsSubtask;

public interface TmsEffortsDAO {
	
	public long createEffort(TmsEffortsDTO tmseffortDTO);
	public TmsEfforts updateEffort(TmsEfforts effort);
	public List<TmsEfforts> getAllEfforts();
	public TmsEfforts getEffort(long id);
	public List<LinkedHashMap<String, Object>> getCurrentUserEffortsBySprint(Long userId,
			Long projectId);
	public Double getTotalHoursLoggedByUser(Long userId, Long projectId);
}
