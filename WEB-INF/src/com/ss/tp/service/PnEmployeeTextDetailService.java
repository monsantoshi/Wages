package com.ss.tp.service;

import java.util.List;

import com.ss.tp.dto.PnEmployeeTextDetailVO;

public interface PnEmployeeTextDetailService {
	public List findByCriteriaList(String ouCode, long evaYear, long evaTime,
			String empCode);

	public void insertPnEmployeeTextDetail(PnEmployeeTextDetailVO emptextvo)
			throws Exception;

	public void updatePnEmployeeTextDetail(PnEmployeeTextDetailVO emptextvo)
			throws Exception;

	public void deletePnEmployeeTextDetail(PnEmployeeTextDetailVO emptextvo)
			throws Exception;

	public void addList(PnEmployeeTextDetailVO emptextvo);
}
