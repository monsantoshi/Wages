/*
 * Created on 30 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.FeeWpayConfirmDataDAO;
import com.ss.tp.dto.FeeWpayConfirmDataVO;
import com.ss.tp.service.FeeWpayConfirmDataService;

/**
 * @author Administrator
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class FeeWpayConfirmDataServiceImpl implements FeeWpayConfirmDataService,
		Serializable {
	private FeeWpayConfirmDataDAO feeWpayConfirmDataDAO;

	public FeeWpayConfirmDataDAO getFeeWpayConfirmDataDAO() {
		return feeWpayConfirmDataDAO;
	}

	public void setFeeWpayConfirmDataDAO(FeeWpayConfirmDataDAO feeWpayConfirmDataDAO) {
		this.feeWpayConfirmDataDAO = feeWpayConfirmDataDAO;
	}

	public void insertWgConfirmData(FeeWpayConfirmDataVO vo) throws Exception {
		this.feeWpayConfirmDataDAO.insertWgConfirmData(vo);
	}

	public boolean isConfirmMasterData(String ouCode, String year,
			String period, String userId) throws Exception {
		return this.feeWpayConfirmDataDAO.isConfirmMasterData(ouCode, year, period,
				userId);
	}

	public boolean isConfirmWgData(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.feeWpayConfirmDataDAO.isConfirmWgData(ouCode, year, period,
				userId);
	}
	
	public boolean isCreateWgPrData(String ouCode, String year,String userId) throws Exception {
		return this.feeWpayConfirmDataDAO.isCreateWgPrData(ouCode, year, userId);
	}
	
	public List addPeriodInYear(String userId, String evaOuCode, String evaYear) {
		// TODO Auto-generated method stub
		return this.feeWpayConfirmDataDAO.addPeriodInYear(userId, evaOuCode,evaYear);
	}
	
	public List addPnToPr(String userId, String evaOuCode, String evaYear,String evaMonth) {
		// TODO Auto-generated method stub
		return this.feeWpayConfirmDataDAO.addPnToPr(userId, evaOuCode,evaYear,evaMonth);
	}

	//@Override
	public boolean isInsertWgPnToPr(String ouCode, String year, String month,
			String userId) throws Exception {
		return this.feeWpayConfirmDataDAO.isInsertWgPnToPr(ouCode, year,month, userId);
	}
	
	public void confirmMaster(String userId, String evaOuCode, String evaYear,String evaPeriod) {
		// TODO Auto-generated method stub
		this.feeWpayConfirmDataDAO.confirmMaster(userId, evaOuCode,evaYear,evaPeriod);
	}
	
	public void transferTrans(String userId, String evaOuCode, String evaYear,String evaPeriod) {
		// TODO Auto-generated method stub
		this.feeWpayConfirmDataDAO.transferTrans(userId, evaOuCode,evaYear,evaPeriod);
	}
	
	public void confirmTrans(String userId, String evaOuCode, String evaYear,String evaPeriod) {
		// TODO Auto-generated method stub
		this.feeWpayConfirmDataDAO.confirmTrans(userId, evaOuCode,evaYear,evaPeriod);
	}
	
	public void closeTrans(String userId, String evaOuCode, String evaYear,String evaPeriod) {
		// TODO Auto-generated method stub
		this.feeWpayConfirmDataDAO.closeTrans(userId, evaOuCode,evaYear,evaPeriod);
	}
	
	public List addFeeWpayDailyToGl(String userId, String evaOuCode, String evaYear,
			String evaMonth,String accDate) {
		// TODO Auto-generated method stub
		return this.feeWpayConfirmDataDAO.addFeeWpayDailyToGl(userId, evaOuCode,evaYear,evaMonth, accDate);
	}
	
}
