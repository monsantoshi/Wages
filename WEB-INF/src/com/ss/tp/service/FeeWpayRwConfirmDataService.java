package com.ss.tp.service;

import java.util.List;

import com.ss.tp.dto.FeeWpayRwConfirmDataVO;
import com.ss.tp.model.FeeWpayPrPeriodLine;
import com.ss.tp.model.VSuMasterUser;


public interface FeeWpayRwConfirmDataService {
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
	
	public List findByCriteriaUserApprove(String userId,  long evaYear,
			long evaPeriod,	String evaUserFrom, String evaUserTo, String evaFlagFrom, int count, int countRecord);

	public Integer countDataUserApprove(String userId, long evaYear,
			long evaPeriod,String evaUserFrom, String evaUserTo, String evaFlagFrom);
	
	
	public List findByCriteriaUserConfirm(String userId,  long evaYear,
			long evaPeriod,	String evaUserFrom, String evaUserTo,String evaZipFrom, String evaZipTo, String evaFlag2,
			String evaFlag3, int count, int countRecord);

	public Integer countDataUserConfirm(String userId, long evaYear,
			long evaPeriod,String evaUserFrom,String evaZipFrom, String evaZipTo,  String evaUserTo, String evaFlag2,
			String evaFlag3);
	
	public List findByCriteriaUserByMaster(String userId,  String evaYear,
			String evaPeriod)throws Exception;
	
	public List findByCriteriaZipCodeByMaster(String userId,  String evaYear,
			String evaPeriod)throws Exception;
	
	
	public List findByCriteriaFlagByUser(String userId,  String evaYear,
			String evaPeriod)throws Exception;
	public VSuMasterUser findConfirmMaster(String ouCode, String year, String period,String userId)
	throws Exception;
	
	public List updateConfirmMaster(String userId, String ouCode, String yearPr,
			String periodPr, String statusConfirm) throws Exception;
	
	
	public List updateConfirmDetail(String userId, String ouCode, String yearPr,
			String periodPr, String statusConfirm) throws Exception;
	
	
	public void convertApproveByUser(String userId, String evaOuCode, String evaYear,String evaPeriod) throws Exception;
	
	
	public void convertConfirmRwDataByUser(String userId, String evaOuCode, String evaYear,String evaPeriod) throws Exception;
	
	
	public void convertConfirmPrTxtByUser(String userId, String evaOuCode, String evaYear,String evaPeriod) throws Exception;
	

	public void insertEmpToPrBreakPay(String userId, String empId, String evaOuCode, String evaYear,String evaPeriod) throws Exception;
	
	
	public boolean isConfirmInComeOutCome(String ouCode, String year, String period,
			String userId) throws Exception;
	
	
	public boolean isConfirmApprove(String ouCode, String year, String period,
			String userId) throws Exception;
	
	public boolean isAddWorkDays(String ouCode, String year, String period,
			String userId) throws Exception;
	
	
}