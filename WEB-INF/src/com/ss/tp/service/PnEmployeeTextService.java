package com.ss.tp.service;

import java.util.List;

import com.ss.tp.dto.DefaultYearSectionPnTextVO;
import com.ss.tp.dto.PnEmployeeTextVO;

public interface PnEmployeeTextService {
	public PnEmployeeTextVO findByKey(String ouCode, long evaYear,
			long evaTime, String empCode);

	public List findByCriteria(String ouCode, long evaYear, long evaTime,
			String areaCode, String secCode, String workCode, String userId,
			int count, int countRecord);

	public void insertPnEmployeeText(PnEmployeeTextVO emptextvo)
			throws Exception;

	public void updatePnEmployeeText(PnEmployeeTextVO emptextvo)
			throws Exception;

	public void deletePnEmployeeText(PnEmployeeTextVO emptextvo)
			throws Exception;

	public String findEmpName(String ouCode, String empCode);

	public String findFormName(String ouCode, String formCode);

	public Integer countData(String ouCode, Long evaYear, Long evaTime,
			String areaCode, String secCode, String workCode, String userId);

	public List findEmpForInsertUpdate(String userId, String ouCode,
			long evaYear, long evaTime, String areaCode, String secCode,
			String workCode);

	public void addList(PnEmployeeTextVO pnEmployeeTextVO);

	public void insertPnEmployeeTextList();

	public void updatePnEmployeeTextList();

	public void deletePnEmployeeTextList();

	public String findFormCode(String ouCode, long evaYear, long evaTime,
			String empCode);

	public DefaultYearSectionPnTextVO findMaxYearPeriod(String ouCode);

}
