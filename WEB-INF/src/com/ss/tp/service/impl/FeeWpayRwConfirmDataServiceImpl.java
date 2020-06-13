/*
 * Created on 30 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

//import com.ss.tp.dao.RwConfirmDataDAO;
//import com.ss.tp.dto.RwConfirmDataVO;
import com.ss.tp.dto.FeeWpayRwConfirmDataVO;
//import com.ss.tp.service.RwConfirmDataService;
import com.ss.tp.dao.FeeWpayRwConfirmDataDAO;
import com.ss.tp.model.FeeWpayPrPeriodLine;
import com.ss.tp.model.VSuMasterUser;
import com.ss.tp.service.FeeWpayRwConfirmDataService;

/**
 * @author Administrator
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class FeeWpayRwConfirmDataServiceImpl implements FeeWpayRwConfirmDataService,
		Serializable {
	//private RwConfirmDataDAO rwConfirmData;
	private FeeWpayRwConfirmDataDAO feeWpayRwConfirmDataDAO;

	public void insertRwConfirmData(FeeWpayRwConfirmDataVO vo) throws Exception {
		this.feeWpayRwConfirmDataDAO.insertRwConfirmData(vo);
	}

	public FeeWpayRwConfirmDataDAO getRwConfirmData() {
		return feeWpayRwConfirmDataDAO;
	}
	
	public void insertRwConfirmData01(FeeWpayRwConfirmDataVO vo) throws Exception {
		this.feeWpayRwConfirmDataDAO.insertRwConfirmData01(vo);
	}

	

	public void setRwConfirmData(FeeWpayRwConfirmDataDAO feeWpayRwConfirmDataDAO) {
		this.feeWpayRwConfirmDataDAO = feeWpayRwConfirmDataDAO;
	}
	
	public FeeWpayRwConfirmDataDAO getFeeWpayRwConfirmDataDAO() {
		return feeWpayRwConfirmDataDAO;
	}

	public void setFeeWpayRwConfirmDataDAO(FeeWpayRwConfirmDataDAO feeWpayRwConfirmDataDAO) {
		this.feeWpayRwConfirmDataDAO = feeWpayRwConfirmDataDAO;
	}

	public boolean isConfirmMasterData(String ouCode, String year,
			String period, String userId) throws Exception {
		return this.feeWpayRwConfirmDataDAO.isConfirmMasterData(ouCode, year, period,
				userId);
	}

	public boolean isConfirmRwData(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.feeWpayRwConfirmDataDAO.isConfirmRwData(ouCode, year, period, userId);
	}
	
	public boolean isConfirmVinai2(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.feeWpayRwConfirmDataDAO.isConfirmVinai2(ouCode, year, period, userId);
	}
	
	
	public boolean isConfirmMasterData(String ouCode, String year,
			String period) throws Exception {
		return this.feeWpayRwConfirmDataDAO.isConfirmMasterData(ouCode, year, period);
	}

	public boolean isConfirmRwData(String ouCode, String year, String period) throws Exception {
		return this.feeWpayRwConfirmDataDAO.isConfirmRwData(ouCode, year, period);
	}
	
	public boolean isConfirmVinai2(String ouCode, String year, String period) throws Exception {
		return this.feeWpayRwConfirmDataDAO.isConfirmVinai2(ouCode, year, period);
	}
	
	
	public void confirmMaster(String userId, String evaOuCode, String evaYear,String evaPeriod) {
		// TODO Auto-generated method stub
		this.feeWpayRwConfirmDataDAO.confirmMaster(userId, evaOuCode,evaYear,evaPeriod);
	}
	
	public void transferTrans(String userId, String evaOuCode, String evaYear,String evaPeriod) {
		// TODO Auto-generated method stub
		this.feeWpayRwConfirmDataDAO.transferTrans(userId, evaOuCode,evaYear,evaPeriod);
	}
	
	public void confirmTrans(String userId, String evaOuCode, String evaYear,String evaPeriod) {
		// TODO Auto-generated method stub
		this.feeWpayRwConfirmDataDAO.confirmTrans(userId, evaOuCode,evaYear,evaPeriod);
	}
	
	public void closeTrans(String userId, String evaOuCode, String evaYear,String evaPeriod) {
		// TODO Auto-generated method stub
		this.feeWpayRwConfirmDataDAO.closeTrans(userId, evaOuCode,evaYear,evaPeriod);
	}
	
	public List addFeeWpayDailyToGl(String userId, String evaOuCode, String evaYear,
			String evaMonth,String accDate) {
		// TODO Auto-generated method stub
		return this.feeWpayRwConfirmDataDAO.addFeeWpayDailyToGl(userId, evaOuCode,evaYear,evaMonth, accDate);
	}
	
	public void insertRwConfirmDataApMt012(FeeWpayRwConfirmDataVO vo) throws Exception {
		this.feeWpayRwConfirmDataDAO.insertRwConfirmDataApMt012(vo);
	}
	
	public void insertRwConfirmDataPcMt001(FeeWpayRwConfirmDataVO vo) throws Exception {
		this.feeWpayRwConfirmDataDAO.insertRwConfirmDataPcMt001(vo);
	}

	
	public List findByCriteriaUserApprove(String userId,  long evaYear,
			long evaPeriod,
			String evaUserFrom, String evaUserTo, String evaFlagFrom, int count, int countRecord) {
		return this.feeWpayRwConfirmDataDAO.findByCriteriaUserApprove(userId,  new Long(
				evaYear), new Long(evaPeriod),evaUserFrom, evaUserTo, evaFlagFrom, count,
				countRecord);
	}

	public Integer countDataUserApprove(String userId, long evaYear,
			long evaPeriod,	String evaUserFrom, String evaUserTo, String evaFlagFrom) {
		return this.feeWpayRwConfirmDataDAO.countDataUserApprove(userId, new Long(evaYear),
				new Long(evaPeriod),evaUserFrom, evaUserTo, evaFlagFrom);
	}
	
	
	public List findByCriteriaUserConfirm(String userId,  long evaYear,
			long evaPeriod,
			String evaUserFrom, String evaUserTo,String evaZipFrom, String evaZipTo, String evaFlag2,
			String evaFlag3, int count, int countRecord) {
		return this.feeWpayRwConfirmDataDAO.findByCriteriaUserConfirm(userId,  new Long(
				evaYear), new Long(evaPeriod),evaUserFrom, evaUserTo,evaZipFrom, evaZipTo, evaFlag2, evaFlag3, count,
				countRecord);
	}

	public Integer countDataUserConfirm(String userId, long evaYear,
			long evaPeriod,	String evaUserFrom, String evaUserTo,String evaZipFrom, String evaZipTo, String evaFlag2,
			String evaFlag3) {
		return this.feeWpayRwConfirmDataDAO.countDataUserConfirm(userId, new Long(evaYear),
				new Long(evaPeriod),evaUserFrom, evaUserTo,evaZipFrom, evaZipTo ,evaFlag2, evaFlag3);
	}

	public List findByCriteriaUserByMaster(String userId,  String evaYear,
			String evaPeriod) throws Exception {
		return this.feeWpayRwConfirmDataDAO.findByCriteriaUserByMaster(userId,  evaYear,evaPeriod);
	}
	
	public List findByCriteriaZipCodeByMaster(String userId,  String evaYear,
			String evaPeriod) throws Exception {
		return this.feeWpayRwConfirmDataDAO.findByCriteriaZipCodeByMaster(userId,  evaYear,evaPeriod);
	}
	
	public List findByCriteriaFlagByUser(String userId,  String evaYear,
			String evaPeriod) throws Exception {
		return this.feeWpayRwConfirmDataDAO.findByCriteriaFlagByUser(userId,  evaYear,evaPeriod);
	}

	public VSuMasterUser findConfirmMaster(String ouCode, String year, String period,String userId)	throws Exception {
			return this.feeWpayRwConfirmDataDAO.findConfirmMaster(ouCode, year, period,userId);
	}
	
	public List updateConfirmMaster(String userId, String ouCode, String yearPr,
			String periodPr, String statusConfirm) throws Exception {
	

		try {
			List ls = this.feeWpayRwConfirmDataDAO.updateConfirmMaster(userId,
					ouCode, yearPr, periodPr);

			System.out.println("after cancen");
			if (statusConfirm.equals("Y")) {
			

				this.feeWpayRwConfirmDataDAO.updateMasterFlag(ouCode, yearPr, periodPr,userId); 
				this.feeWpayRwConfirmDataDAO.deleteFlagConfirm(ouCode, yearPr, periodPr,userId); 
				this.feeWpayRwConfirmDataDAO.updateConfirmFlag(ouCode, yearPr, periodPr,userId); 

				

				
			}

			return ls;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public List updateConfirmDetail(String userId, String ouCode, String yearPr,
			String periodPr, String statusConfirm) throws Exception {
	

		try {
			List ls = this.feeWpayRwConfirmDataDAO.updateConfirmDetail(userId,
					ouCode, yearPr, periodPr);

			System.out.println("after cancen");
			if (statusConfirm.equals("Y")) {
	
				this.feeWpayRwConfirmDataDAO.updateMasterFlag(ouCode, yearPr, periodPr,userId); 
				this.feeWpayRwConfirmDataDAO.deleteFlagApprove(ouCode, yearPr, periodPr,userId); 
				this.feeWpayRwConfirmDataDAO.updateConfirmRwData(ouCode, yearPr, periodPr,userId); 
				
			}

			return ls;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public List updateConfirmApprove(String userId, String ouCode, String yearPr,
			String periodPr, String statusConfirm) throws Exception {
	

		try {
			List ls = this.feeWpayRwConfirmDataDAO.updateConfirmApprove(userId,
					ouCode, yearPr, periodPr);

			System.out.println("after cancen");
			if (statusConfirm.equals("Y")) {
	
				this.feeWpayRwConfirmDataDAO.updateTranFlag(ouCode, yearPr, periodPr,userId); 
				this.feeWpayRwConfirmDataDAO.deletePrDailyApprove(ouCode, yearPr, periodPr, userId);
				this.feeWpayRwConfirmDataDAO.deleteFlagApprove(ouCode, yearPr, periodPr,userId); 
				
				
			}

			return ls;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void convertApproveByUser(String userId, String ouCode, String yearPr,
			String periodPr) throws Exception {
		this.feeWpayRwConfirmDataDAO.convertApproveByUser(userId,ouCode,yearPr,periodPr);
	}
	
	public void convertConfirmRwDataByUser(String userId, String ouCode, String yearPr,
			String periodPr) throws Exception {
		this.feeWpayRwConfirmDataDAO.convertConfirmRwDataByUser(userId,ouCode,yearPr,periodPr);
	}
	
	
	public void convertConfirmPrTxtByUser(String userId, String ouCode, String yearPr,
			String periodPr) throws Exception {
		this.feeWpayRwConfirmDataDAO.convertConfirmPrTxtByUser(userId,ouCode,yearPr,periodPr);
	}

	public void insertEmpToPrBreakPay(String userId,String empId, String ouCode, String yearPr,
			String periodPr) throws Exception {
		this.feeWpayRwConfirmDataDAO.insertEmpToPrBreakPay(userId,empId,ouCode,yearPr,periodPr);
	}
	
	public boolean isConfirmInComeOutCome(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.feeWpayRwConfirmDataDAO.isConfirmInComeOutCome(ouCode, year, period, userId);
	}
	
	public boolean isConfirmApprove(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.feeWpayRwConfirmDataDAO.isConfirmApprove(ouCode, year, period, userId);
	}

	public boolean isAddWorkDays(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.feeWpayRwConfirmDataDAO.isAddWorkDays(ouCode, year, period, userId);
	}
	
	
}
