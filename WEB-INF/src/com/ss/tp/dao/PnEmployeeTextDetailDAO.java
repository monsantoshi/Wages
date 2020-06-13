package com.ss.tp.dao;

import com.ss.tp.dto.PnEmployeeTextDetailVO;

import java.util.List;

public interface PnEmployeeTextDetailDAO {
	public List findByCriteria(String ouCode, Long evaYear, Long evaTime,
			String empCode);

	public void insertPnEmployeeTextDetail(PnEmployeeTextDetailVO emptextvo)
			throws Exception;

	public void updatePnEmployeeTextDetail(PnEmployeeTextDetailVO emptextvo)
			throws Exception;

	public void deletePnEmployeeTextDetail(PnEmployeeTextDetailVO emptextvo)
			throws Exception;

	public void addList(PnEmployeeTextDetailVO emptextvo);

	public void clearList();

	public void insertPnEmployeeTextDetailList() throws Exception;

	public void updatePnEmployeeTextDetailList() throws Exception;
}
