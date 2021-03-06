package com.cyb.tms.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.cyb.tms.dto.TmsEffortsDTO;
import com.cyb.tms.entity.TmsEfforts;

public interface TmsEffortsService {
	
	public long createEffort(TmsEffortsDTO tmseffortDTO);
	public TmsEfforts updateEffort(TmsEfforts effort);
	public List<TmsEfforts> getAllEfforts();
	public TmsEfforts getEffort(long id);
	public List<LinkedHashMap<String, Object>> getCurrentUserEffortsBySprint(Long userId,
			Long projectId);


}
