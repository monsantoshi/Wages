package com.ss.tp.dao;

import java.util.List;

import com.ss.tp.dto.FeeWpayRwConfirmDataVO;
import com.ss.tp.model.FeeWpayPrPeriodLine;
import com.ss.tp.model.VSuMasterUser;

public interface FeeWpayRwConfirmDataDAO {
	public void insertRwConfirmData(FeeWpayRwConfirmDataVO vo) throws Exception;
	public void insertRwConfirmData01(FeeWpayRwConfirmDataVO vo) throws Exception;

	public boolean isConfirmMasterData(String ouCode, String year,
			String period, String userId) throws Exception;

	public boolean isConfirmRwData(String ouCode, String year, String period,
			String userId) throws Exception;
	
	public boolean isConfirmVinai2(String ouCode, String year, String period,
			String userId) throws Exception;
	
	
	public boolean isConfirmMasterData(String ouCode, String year,
			String period) throws Exception;

	public boolean isConfirmRwData(String ouCode, String year, String period) throws Exception;
	
	public boolean isConfirmVinai2(String ouCode, String year, String period) throws Exception;
	
	public void confirmMaster(String userId, String evaOuCode, String evaYear,String evaPeriod);
	public void transferTrans(String userId, String evaOuCode, String evaYear,String evaPeriod);
	public void confirmTrans(String userId, String evaOuCode, String evaYear,String evaPeriod);
	public void closeTrans(String userId, String evaOuCode, String evaYear,String evaPeriod);
	
	public List addFeeWpayDailyToGl(String userId, String evaOuCode, String evaYear,
			String evaMonth,String accDate);
	
	public void insertRwConfirmDataApMt012(FeeWpayRwConfirmDataVO vo) throws Exception;
	public void insertRwConfirmDataPcMt001(FeeWpayRwConfirmDataVO vo) throws Exception;
	
	public List findByCriteriaUserApprove(String userId, Long evaYear,
			Long evaPeriod, String evaUserFrom, String evaUserTo, String evaFlagFrom,
			 int count, int countRecord);

	public Integer countDataUserApprove(String userId, Long evaYear,
			Long evaPeriod,	String evaUserFrom, String evaUserTo, String evaFlagFrom);

	
	public List findByCriteriaUserConfirm(String userId, Long evaYear,
			Long evaPeriod, String evaUserFrom, String evaUserTo, String evaZipFrom, String evaZipTo, String evaFlag2,
			String evaFlag3, int count, int countRecord);

	public Integer countDataUserConfirm(String userId, Long evaYear,
			Long evaPeriod,	String evaUserFrom, String evaUserTo,String evaZipFrom, String evaZipTo, String evaFlag2,
			String evaFlag3);

	public List findByCriteriaUserByMaster(String userId, String evaYear,
			String evaPeriod)throws Exception;
	
	public List findByCriteriaZipCodeByMaster(String userId, String evaYear,
			String evaPeriod)throws Exception;
	
	public List findByCriteriaFlagByUser(String userId, String evaYear,
			String evaPeriod)throws Exception;
	
	public VSuMasterUser findConfirmMaster(String ouCode, String year, String period,String userId)	throws Exception;
	
	public List updateConfirmMaster(String userId, String ouCode, String yearPr,
			String periodPr) throws Exception;
	
	
	public void updateMasterFlag(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;
	
	public void deleteFlagConfirm(String ouCode, String year, String period,
			String userId) throws Exception ;
	
	public void deleteFlagApprove(String ouCode, String year, String period,
			String userId) throws Exception ;
	
	
	public void updateConfirmFlag(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;
	
	
	
	public List updateConfirmDetail(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;
	
	
	public void updateConfirmRwData(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;
	
	public void deletePrDailyApprove(String ouCode, String year, String period,
			String userId) throws Exception;

	
	public void updateTranFlag(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;
	
	
	public List updateConfirmApprove(String ouCode, String yearPr,
			String periodPr, String userId) throws Exception;
	
	
	public void convertApproveByUser(String userId, String ouCode, String yearPr,
			String periodPr) throws Exception;
	
	public void convertConfirmRwDataByUser(String userId, String ouCode, String yearPr,
			String periodPr) throws Exception;
	
	
	public void convertConfirmPrTxtByUser(String userId, String ouCode, String yearPr,
			String periodPr) throws Exception;
	
	
	public void insertEmpToPrBreakPay(String userId,String empId, String ouCode, String yearPr,
			String periodPr) throws Exception;

	public boolean isConfirmInComeOutCome(String ouCode, String year, String period,
			String userId) throws Exception;
	
	public boolean isConfirmApprove(String ouCode, String year, String period,
			String userId) throws Exception;
	public boolean isAddWorkDays(String ouCode, String year, String period,
			String userId) throws Exception;
}