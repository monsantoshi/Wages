package com.ss.tp.service.impl;

import java.util.List;

import com.ss.tp.dao.FeeWpayPrEmployeeDAO;
import com.ss.tp.dto.FeeWpayPrEmployeeTextVO;
import com.ss.tp.dto.FeeWpayPrEmployeeVO;
import com.ss.tp.service.FeeWpayPrEmployeeService;






public class FeeWpayPrEmployeeServiceImpl implements FeeWpayPrEmployeeService {

	private FeeWpayPrEmployeeDAO feeWpayPrEmployeeDAO;

	public FeeWpayPrEmployeeDAO getFeeWpayPrEmployeeDAO() {
		return feeWpayPrEmployeeDAO;
	}

	public void setFeeWpayPrEmployeeDAO(FeeWpayPrEmployeeDAO feeWpayPrEmployeeDAO) {
		this.feeWpayPrEmployeeDAO = feeWpayPrEmployeeDAO;
	}

	public String[] findMaxYearPeriod(String ouCode,String userId) {
		return this.feeWpayPrEmployeeDAO.findMaxYearPeriod(ouCode,userId);
	}
	
	
	public String[] findMaxYearPeriodTransClose(String ouCode,String userId) {
		return this.feeWpayPrEmployeeDAO.findMaxYearPeriodTransClose(ouCode,userId);
	}
	
	public String[] findMinYearPeriodLine(String ouCode,String userId) {
		return this.feeWpayPrEmployeeDAO.findMinYearPeriodLine(ouCode,userId);
	}
	public String[] findMinPeriodLine(String ouCode,String year,String userId) {
		return this.feeWpayPrEmployeeDAO.findMinPeriodLine(ouCode,year,userId);
	}
	public List findByCriteria(String ouCode, String year, String period,
			String userId, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo, int page, int recPerPage)
			throws Exception {
		return this.feeWpayPrEmployeeDAO.findByCriteria(ouCode, year, period, userId,
				orgCodeFrom, orgCodeTo, empCodeFrom, empCodeTo, page,
				recPerPage);
	}

	public Integer getCountByCriteria(String ouCode, String year,
			String period, String userId, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo) throws Exception {
		return this.feeWpayPrEmployeeDAO.getCountByCriteria(ouCode, year, period,
				userId, orgCodeFrom, orgCodeTo, empCodeFrom, empCodeTo);
	}

	public FeeWpayPrEmployeeVO findPrEmployee(String ouCode, String year,
			String period, String empCode, String userId) {
		return this.feeWpayPrEmployeeDAO.findByCriteria(ouCode, year, period, empCode,userId);
	}

	public String[] findMaxPeriodName(String ouCode, String year, String period) {
		return this.feeWpayPrEmployeeDAO.findMaxPeriodName(ouCode, year, period);
	}

	public List findByCriteriaRWQY001(String ouCode, String year,
			String period, String userId, String orgCodeFrom, String orgCodeTo, String bankIdFrom, String bankIdTo,
			String empCodeFrom, String empCodeTo, int page, int recPerPage)
			throws Exception {
		return this.feeWpayPrEmployeeDAO.findByCriteriaRWQY001(ouCode, year, period,
				userId, orgCodeFrom, orgCodeTo,bankIdFrom,bankIdTo, empCodeFrom, empCodeTo, page,
				recPerPage);
	}

	public Integer getCountByCriteriaRWQY001(String ouCode, String year,
			String period, String userId, String orgCodeFrom, String orgCodeTo, String bankIdFrom, String bankIdTo,
			String empCodeFrom, String empCodeTo) throws Exception {
		return this.feeWpayPrEmployeeDAO.getCountByCriteriaRWQY001(ouCode, year,
				period, userId, orgCodeFrom, orgCodeTo,bankIdFrom,bankIdTo, empCodeFrom, empCodeTo);
	}

	public List findDetailPaySlip(String ouCode, String userId, String year,
			String period) {
		return this.feeWpayPrEmployeeDAO.findDetailPaySlip(ouCode, userId, year,
				period);
	}
	
	public List findDetailPaySlip(String ouCode, String userId, String year,
			String period,String approveFlag) {
		return this.feeWpayPrEmployeeDAO.findDetailPaySlip(ouCode, userId, year,
				period,approveFlag);
	}

	public void updatePrEmpByPrEmpText(FeeWpayPrEmployeeTextVO prEmpTextVo)
			throws Exception {
		this.feeWpayPrEmployeeDAO.updatePrEmpByPrEmpText(prEmpTextVo);
	}

	public List findDetailPaySlipOverTime(String ouCode, String userId,
			String year, String period, String divCode, String areaCode,
			String secCode) throws Exception {
		return this.feeWpayPrEmployeeDAO.findDetailPaySlipOverTime(ouCode, userId,
				year, period, divCode, areaCode, secCode);
	}

	public List findDetailPaySlipPremium(String ouCode, String userId,
			String year, String period, String divCode, String areaCode,
			String secCode) throws Exception {
		return this.feeWpayPrEmployeeDAO.findDetailPaySlipPremium(ouCode, userId,
				year, period, divCode, areaCode, secCode);
	}

	public List findDetailPaySlipHealth(String ouCode, String userId,
			String year, String period, String divCode, String areaCode,
			String secCode) throws Exception {
		return this.feeWpayPrEmployeeDAO.findDetailPaySlipHealth(ouCode, userId, year,
				period, divCode, areaCode, secCode);
	}

	public List findDetailPaySlipDanger(String ouCode, String userId,
			String year, String period, String divCode, String areaCode,
			String secCode) throws Exception {
		return this.feeWpayPrEmployeeDAO.findDetailPaySlipDanger(ouCode, userId, year,
				period, divCode, areaCode, secCode);
	}

	public List findDetailPaySlipOverTime(String ouCode, String userId,
			String year, String period, String divCode) throws Exception {
		return this.feeWpayPrEmployeeDAO.findDetailPaySlipOverTime(ouCode, userId,
				year, period, divCode);
	}

	public List findDetailPaySlipPremium(String ouCode, String userId,
			String year, String period, String divCode) throws Exception {
		return this.feeWpayPrEmployeeDAO.findDetailPaySlipPremium(ouCode, userId,
				year, period, divCode);
	}

	public List findDetailPaySlipHealth(String ouCode, String userId,
			String year, String period, String divCode) throws Exception {
		return this.feeWpayPrEmployeeDAO.findDetailPaySlipHealth(ouCode, userId, year,
				period, divCode);
	}

	public List findDetailPaySlipDanger(String ouCode, String userId,
			String year, String period, String divCode) throws Exception {
		return this.feeWpayPrEmployeeDAO.findDetailPaySlipDanger(ouCode, userId, year,
				period, divCode);
	}
	
	
	public List findByCriteriaTax(String ouCode,int evaYear, int evaPeriod)
			throws Exception {
		return this.feeWpayPrEmployeeDAO.findByCriteriaTax(ouCode, new Integer(evaYear),new Integer(evaPeriod));
	}
	
	public List findPrDailyRandom(String ouCode,int evaYear, int evaPeriod)
			throws Exception {
		return this.feeWpayPrEmployeeDAO.findPrDailyRandom(ouCode, new Integer(evaYear),new Integer(evaPeriod));
	}
	public List findByCriteriaFeeEmpMT001(String ouCode, 
			String userId, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo, int page, int recPerPage)
			throws Exception {
		return this.feeWpayPrEmployeeDAO.findByCriteriaFeeEmpMT001(ouCode,  userId,	orgCodeFrom, orgCodeTo, empCodeFrom, empCodeTo, page,
				recPerPage);
	}

	public Integer getCountByCriteriaFeeEmpMT001(String ouCode, String userId, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo) throws Exception {
		return this.feeWpayPrEmployeeDAO.getCountByCriteriaFeeEmpMT001(ouCode,userId, orgCodeFrom, orgCodeTo, empCodeFrom, empCodeTo);
	}
	
	public List findByCriteriaWGCONMT001(String ouCode, String userId, String orgCodeFrom, String orgCodeTo,String yearCodeFrom, String yearCodeTo,
			String empCodeFrom, String empCodeTo,String dateFrom,String yearFrom,String yearTo,String monthFrom,String monthTo, int page, int recPerPage)
			throws Exception {
		return this.feeWpayPrEmployeeDAO.findByCriteriaWGCONMT001(ouCode,userId,orgCodeFrom, orgCodeTo,yearCodeFrom, yearCodeTo, empCodeFrom, empCodeTo,dateFrom,
				yearFrom,yearTo,monthFrom,monthTo,page,	recPerPage);
	}
	
/*	public List findByCriteriaWGCONMT001(String ouCode, String userId, String orgCodeFrom, String orgCodeTo,String yearCodeFrom, String yearCodeTo,
			String empCodeFrom, String empCodeTo,String dateFrom, int page, int recPerPage)
			throws Exception {
		return this.feeWpayPrEmployeeDAO.findByCriteriaWGCONMT001(ouCode,userId,orgCodeFrom, orgCodeTo,yearCodeFrom, yearCodeTo, empCodeFrom, empCodeTo,dateFrom, page,
				recPerPage);
	}*/

	public Integer getCountByCriteriaWGCONMT001(String ouCode, String userId, String orgCodeFrom, String orgCodeTo,String yearCodeFrom, String yearCodeTo,
			String empCodeFrom, String empCodeTo,String dateFrom,String yearFrom,String yearTo,String monthFrom,String monthTo) throws Exception {
		return this.feeWpayPrEmployeeDAO.getCountByCriteriaWGCONMT001(ouCode,userId, orgCodeFrom, orgCodeTo,yearCodeFrom, yearCodeTo, empCodeFrom, empCodeTo,dateFrom, yearFrom, yearTo, monthFrom, monthTo);
	}
	public boolean isCreatedWgPrEmployeeData(String ouCode, String year, String period,String userId) throws Exception {
		return this.feeWpayPrEmployeeDAO.isCreatedWgPrEmployeeData(ouCode, year, period,userId);
	}

	

	//@Override
	public List findByCriteriaWGCONMT001(String ouCode, String year,
			String period, String userId, String orgCodeFrom, String orgCodeTo,
			String yearCodeFrom, String yearCodeTo, String empCodeFrom,
			String empCodeTo, int page, int recPerPage) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	public Integer getCountByCriteriaWGCONMT001(String ouCode, String year,
			String period, String userId, String orgCodeFrom, String orgCodeTo,
			String yearCodeFrom, String yearCodeTo, String empCodeFrom,
			String empCodeTo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	public boolean isCreatedWpayPrEmployeeData(String ouCode, String year,
			String period, String userId) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	public List wpayKtbBankReport(String userId, String evaOuCode, int evaYear,
			int evaMonth) {
		return this.feeWpayPrEmployeeDAO.wpayKtbBankReport(userId, evaOuCode,
				new Integer(evaYear), new Integer(evaMonth));
	}
	public List findBankIdBySecurity(String userId, String ouCode, String year,
			String period) throws Exception {
		return this.feeWpayPrEmployeeDAO.findBankIdBySecurity(userId, ouCode, year,
				period);
	}
	public List feeWpayPrDailyRepByOrg(String userId, String evaOuCode,
			long evaYear, long evaPeriod) {
		return this.feeWpayPrEmployeeDAO.feeWpayPrDailyRepByOrg(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod));
	}
	
	
	public List prDailyRepByOrg(String userId, String evaOuCode,
			long evaYear, long evaPeriod) {
		return this.feeWpayPrEmployeeDAO.prDailyRepByOrg(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod));
	}
	public List feeWpay401Report( String evaOuCode,
			long evaYear, long evaPeriod, String flag) {
		return this.feeWpayPrEmployeeDAO.feeWpay401Report( evaOuCode,
				new Long(evaYear), new Long(evaPeriod), flag);
	}
	
	public List feeWpay401KTBReport( String evaOuCode,
			long evaYear, long evaPeriod, String flag) {
		return this.feeWpayPrEmployeeDAO.feeWpay401KTBReport( evaOuCode,
				new Long(evaYear), new Long(evaPeriod), flag);
	}
	
	public List feeWpay401SCBReport( String evaOuCode,
			long evaYear, long evaPeriod, String flag) {
		return this.feeWpayPrEmployeeDAO.feeWpay401SCBReport( evaOuCode,
				new Long(evaYear), new Long(evaPeriod), flag);
	}
	public List feeWpayGlTransferReport( String evaYear, String evaMonth) {
		return this.feeWpayPrEmployeeDAO.feeWpayGlTransferReport(evaYear, evaMonth);
	}
	
	
	public List feeWpayPrMonthlyRepByOrg(String userId, String evaOuCode,
			long evaYear, long evaPeriod) {
		return this.feeWpayPrEmployeeDAO.feeWpayPrMonthlyRepByOrg(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod));
	}
	
	
	public List findByCriteriaIntBrk001(String ouCode, String year,
			String period, String userId,  String bankIdFrom, String bankIdTo,
			String empCodeFrom, String empCodeTo, int page, int recPerPage)
			throws Exception {
		return this.feeWpayPrEmployeeDAO.findByCriteriaIntBrk001(ouCode, year, period,
				userId, bankIdFrom,bankIdTo, empCodeFrom, empCodeTo, page,
				recPerPage);
	}

	public Integer getCountByCriteriaIntBrk001(String ouCode, String year,
			String period, String userId,  String bankIdFrom, String bankIdTo,
			String empCodeFrom, String empCodeTo) throws Exception {
		return this.feeWpayPrEmployeeDAO.getCountByCriteriaIntBrk001(ouCode, year,
				period, userId,bankIdFrom,bankIdTo, empCodeFrom, empCodeTo);
	}
	
	public List feeWpayPrBreakPayReport(String userId, String evaOuCode, long evaYear,
			long evaPeriod) {
		return this.feeWpayPrEmployeeDAO.feeWpayPrBreakPayReport(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod));
	}
	
	public List feeWpayPrBreakPayScbReport(String userId, String evaOuCode, long evaYear,
			long evaPeriod) {
		return this.feeWpayPrEmployeeDAO.feeWpayPrBreakPayScbReport(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod));
	}
	public List feeWpay401UserReport( String evaOuCode,
			long evaYear, long evaPeriod, String flag, String userId) {
		return this.feeWpayPrEmployeeDAO.feeWpay401UserReport( evaOuCode,
				new Long(evaYear), new Long(evaPeriod), flag,userId);
	}
	
	public List feeWpay401UserKTBReport( String evaOuCode,
			long evaYear, long evaPeriod, String flag, String userId) {
		return this.feeWpayPrEmployeeDAO.feeWpay401UserKTBReport( evaOuCode,
				new Long(evaYear), new Long(evaPeriod), flag,userId);
	}
	
	public List feeWpay401UserSCBReport( String evaOuCode,
			long evaYear, long evaPeriod, String flag, String userId) {
		return this.feeWpayPrEmployeeDAO.feeWpay401UserSCBReport( evaOuCode,
				new Long(evaYear), new Long(evaPeriod), flag,userId);
	}
	
}
