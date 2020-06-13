package com.ss.tp.dao;

import java.util.List;
import com.ss.tp.dto.WgPrEmployeeTextVO;

public interface WgPrEmployeeTextDAO {
	public void insertWgPrEmployeeText(WgPrEmployeeTextVO premployeetextvo)
			throws Exception;

	public void updatePrEmployeeText(WgPrEmployeeTextVO premployeetextvo)
			throws Exception;

	public void deletePrEmployeeText(WgPrEmployeeTextVO premployeetextvo)
			throws Exception;

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception;

	public List updateConfirmIncome(String userId, String ouCode, Long yearPr,
			Long periodPr) throws Exception;

	public List updateConfirmDeduct(String userId, String ouCode, Long yearPr,
			Long periodPr) throws Exception;

	public List deleteConfirmIncome(String userId, String ouCode, Long yearPr,
			Long periodPr) throws Exception;

	public List deleteConfirmDeduct(String userId, String ouCode, Long yearPr,
			Long periodPr) throws Exception;

	public Integer confirmData(String ouCode, Long yearPr, Long periodPr,
			String flagStatus, String userId);

	public void updateConfirmData(String ouCode, Long yearPr, Long periodPr,
			String userId) throws Exception;

	public void updateWgPrOvertimeIncome(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

	public void updateWgPrWorkDayIncome(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

	public void updateWgPrIncDecOtherIncome(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

	public void deleteWgPrOvertimeIncome(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

	public void deleteWgPrWorkDayIncome(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

	public void deleteWgPrIncDecOtherIncome(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

	public void updateWgPrIncDecOtherDeduct(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

	public List findChangeOfMonth(String ouCode, String userId, Integer year,
			Integer period);

	public void deleteWgPrIncDecOtherDeduct(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

}