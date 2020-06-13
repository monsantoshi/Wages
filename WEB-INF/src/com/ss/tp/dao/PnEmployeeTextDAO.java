package com.ss.tp.dao;

import java.util.List;

import com.ss.tp.dto.DefaultYearSectionPnTextVO;
import com.ss.tp.dto.PnEmployeeTextVO;

public interface PnEmployeeTextDAO {
	public PnEmployeeTextVO findByKey(String ouCode, Long evaYear,
			Long evaTime, String empCode);

	public List findByCriteria(String ouCode, Long evaYear, Long evaTime,
			String areaCode, String secCode, String workCode, String userId,
			int count, int countRecord);

	public void insertPnEmployeeText(PnEmployeeTextVO emptextvo)
			throws Exception;

	public void updatePnEmployeeText(PnEmployeeTextVO emptextvo)
			throws Exception;

	public void deletePnEmployeeText(PnEmployeeTextVO emptextvo)
			throws Exception;

	public Integer countData(String ouCode, Long evaYear, Long evaTime,
			String areaCode, String secCode, String workCode, String userId);

	public List findEmpForInsertUpdate(String userId, String ouCode,
			Long evaYear, Long evaTime, String areaCode, String secCode,
			String workCode);

	public void addList(PnEmployeeTextVO pnEmployeeTextVO);

	public void clearList();

	public void insertPnEmployeeTextList() throws Exception;

	public void updatePnEmployeeTextList() throws Exception;

	public void deletePnEmployeeTextList() throws Exception;

	public String findFormCode(String ouCode, Long evaYear, Long evaTime,
			String empCode);

	public DefaultYearSectionPnTextVO findMaxYearPeriod(String ouCode);
}
