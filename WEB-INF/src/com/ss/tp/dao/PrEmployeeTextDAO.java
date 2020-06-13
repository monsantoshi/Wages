package com.ss.tp.dao;

import java.util.List;
import com.ss.tp.dto.PrEmployeeTextVO;

public interface PrEmployeeTextDAO {
	public void insertPrEmployeeText(PrEmployeeTextVO premployeetextvo)
			throws Exception;

	public void updatePrEmployeeText(PrEmployeeTextVO premployeetextvo)
			throws Exception;

	public void deletePrEmployeeText(PrEmployeeTextVO premployeetextvo)
			throws Exception;
	
	public List deletePrEmployeeTextError(String userId, String ouCode, Long yearPr,
			Long periodPr) throws Exception;
	
	public void deletePrEmployeeTextError(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

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
	
	public void deleteIncomeError(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;
	
	public void deleteDeductError(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

	public Integer confirmData(String ouCode, Long yearPr, Long periodPr,
			String flagStatus, String userId);

	public void updateConfirmData(String ouCode, Long yearPr, Long periodPr,
			String userId) throws Exception;

	public void updateRwModSalIncome(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

	public void updateRwOvertimeIncome(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

	public void updateRwPremiumIncome(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

	public void updateRwHealthIncome(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

	public void updateRwDangerIncome(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

	public void updateRwIncDecOtherIncome(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

	public void deleteRwModSalIncome(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

	public void deleteRwOvertimeIncome(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

	public void deleteRwPremiumIncome(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

	public void deleteRwHealthIncome(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

	public void deleteRwDangerIncome(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

	public void deleteRwIncDecOtherIncome(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

	public void updateRwVinaiDeduct(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

	public void updateRwVinai2Deduct(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

	public void updateRwIncDecOtherDeduct(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

	public List findChangeOfMonth(String ouCode, String userId, Integer year,
			Integer period);

	public List findMoveChangeOfMonth(String ouCode, String userId,
			Integer year, Integer period);

	public void deleteRwVinaiDeductError(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

	public void deleteRwVinai2DeductError(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

	public void deleteRwIncDecOtherDeductError(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;
	
	public void deleteRwModSalIncomeError(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

	public void deleteRwOvertimeIncomeError(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

	public void deleteRwPremiumIncomeError(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

	public void deleteRwHealthIncomeError(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

	public void deleteRwDangerIncomeError(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

	public void deleteRwIncDecOtherIncomeError(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;
	public void deleteRwVinaiDeduct(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

	public void deleteRwVinai2Deduct(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;

	public void deleteRwIncDecOtherDeduct(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;
	

	public void deleteOldPrEmployeeText(PrEmployeeTextVO vo) throws Exception;

}