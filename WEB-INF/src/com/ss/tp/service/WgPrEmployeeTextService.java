package com.ss.tp.service;

import java.util.List;
import com.ss.tp.dto.WgPrEmployeeTextVO;

public interface WgPrEmployeeTextService {
	public void insertWgPrEmployeeText(WgPrEmployeeTextVO premployeetextvo)
			throws Exception;

	public void updatePrEmployeeText(WgPrEmployeeTextVO premployeetextvo)
			throws Exception;

	public void deletePrEmployeeText(WgPrEmployeeTextVO premployeetextvo)
			throws Exception;

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception;

	public List updateConfirmIncome(String userId, String ouCode, long yearPr,
			long periodPr) throws Exception;

	public List updateConfirmDeduct(String userId, String ouCode, long yearPr,
			long periodPr) throws Exception;

	public List deleteConfirmIncome(String userId, String ouCode, long yearPr,
			long periodPr) throws Exception;

	public List deleteConfirmDeduct(String userId, String ouCode, long yearPr,
			long periodPr) throws Exception;

	public Integer confirmData(String ouCode, long yearPr, long periodPr,
			String flagStatus, String userId);

	public void updateConfirmData(String ouCode, long yearPr, long periodPr,
			String userId) throws Exception;

	public List findChangeOfMonth(String ouCode, String userId, int year,
			int period);
}