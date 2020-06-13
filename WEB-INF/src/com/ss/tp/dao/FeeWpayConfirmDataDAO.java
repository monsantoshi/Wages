package com.ss.tp.dao;

import java.util.List;

import com.ss.tp.dto.FeeWpayConfirmDataVO;

public interface FeeWpayConfirmDataDAO {
	public void insertWgConfirmData(FeeWpayConfirmDataVO vo) throws Exception;

	public boolean isConfirmMasterData(String ouCode, String year,
			String period, String userId) throws Exception;

	public boolean isConfirmWgData(String ouCode, String year, String period,
			String userId) throws Exception;
	
	public boolean isCreateWgPrData(String ouCode, String year,String userId) throws Exception;
	
	public boolean isInsertWgPnToPr(String ouCode, String year,String month,String userId) throws Exception;

	
	public List addPeriodInYear(String userId, String evaOuCode, String evaYear);
	
	public List addPnToPr(String userId, String evaOuCode, String evaYear,String evaMonth);
	
	public void confirmMaster(String userId, String evaOuCode, String evaYear,String evaPeriod);
	public void transferTrans(String userId, String evaOuCode, String evaYear,String evaPeriod);
	public void confirmTrans(String userId, String evaOuCode, String evaYear,String evaPeriod);
	public void closeTrans(String userId, String evaOuCode, String evaYear,String evaPeriod);
	
	public List addFeeWpayDailyToGl(String userId, String evaOuCode, String evaYear,
			String evaMonth,String accDate);



	
	
}