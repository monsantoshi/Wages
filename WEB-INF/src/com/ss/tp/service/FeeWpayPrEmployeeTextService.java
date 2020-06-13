package com.ss.tp.service;

import java.util.List;
import com.ss.tp.dto.FeeWpayPrEmployeeTextVO;

public interface FeeWpayPrEmployeeTextService {
	public void insertPrEmployeeText(FeeWpayPrEmployeeTextVO feewpaypremployeetextvo)
			throws Exception;

	public void updatePrEmployeeText(FeeWpayPrEmployeeTextVO feewpaypremployeetextvo)
			throws Exception;

	public void deletePrEmployeeText(FeeWpayPrEmployeeTextVO feewpaypremployeetextvo)
			throws Exception;
	
	public void deletePrEmployeeTextError(String userId, String ouCode, String yearPr,
			String periodPr)
			throws Exception;

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception;

	public List updateConfirmIncome(String userId, String ouCode, long yearPr,
			long periodPr, String statusConfirm) throws Exception;

	public List updateConfirmDeduct(String userId, String ouCode, long yearPr,
			long periodPr) throws Exception;

	public List updateConfirmVinai2(String userId, String ouCode, long yearPr,
			long periodPr, String statusConfirm) throws Exception;
	
	public List deleteConfirmIncome(String userId, String ouCode, long yearPr,
			long periodPr) throws Exception;

	public List deleteConfirmDeduct(String userId, String ouCode, long yearPr,
			long periodPr) throws Exception;

	public Integer confirmData(String ouCode, long yearPr, long periodPr,
			String flagStatus, String userId);

	public void updateConfirmData(String ouCode, long yearPr, long periodPr,
			String userId) throws Exception;
	
	public void deleteIncomeError(String userId, String ouCode, String yearPr,
			String periodPr) throws Exception;

	public void deleteDeductError(String userId, String ouCode, String yearPr,
			String periodPr) throws Exception;

	public List findChangeOfMonth(String ouCode, String userId, int year,
			int period);

	public List findMoveChangeOfMonth(String ouCode, String userId, int year,
			int period);
	
	
	public List updateApproveIncome(String userId, String ouCode, long yearPr,
			long periodPr, String statusConfirm) throws Exception;

	public List updateApproveDeduct(String userId, String ouCode, long yearPr,
			long periodPr) throws Exception;
	
	
	public List submitIncomeTransClose(String userId, String ouCode, long yearPr,
			long periodPr, String statusConfirm) throws Exception;
	
	
	public List submitDeductTransClose(String userId, String ouCode, long yearPr,
			long periodPr) throws Exception;
	
	public void updateTransferFlag(String ouCode, long yearPr, long monthPr,String userId) throws Exception;
	
}