package com.ss.tp.service;

import java.util.List;

import com.ss.tp.dto.FeeWpayPrEmployeeTextVO;
import com.ss.tp.dto.FeeWpayPrEmployeeVO;



public interface FeeWpayPrEmployeeService {
	/**
	 * Find Max year and Max section of OU_CODE
	 */
	public String[] findMaxYearPeriod(String ouCode,String userId);
	public String[] findMaxYearPeriodTransClose(String ouCode,String userId);
	public String[] findMinYearPeriodLine(String ouCode,String userId);
	public String[] findMinPeriodLine(String ouCode,String year,String userId);

	public FeeWpayPrEmployeeVO findPrEmployee(String ouCode, String year,
			String period, String empCode, String userId);

	public List findByCriteria(String ouCode, String year, String period,
			String userId, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo, int page, int recPerPage)
			throws Exception;

	public Integer getCountByCriteria(String ouCode, String year,
			String period, String userId, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo) throws Exception;

	public String[] findMaxPeriodName(String ouCode, String year, String period);

	public List findByCriteriaRWQY001(String ouCode, String year,
			String period, String userId, String orgCodeFrom, String orgCodeTo,String bankIdFrom,String bankIdTo,
			String empCodeFrom, String empCodeTo, int page, int recPerPage)
			throws Exception;
	
	public Integer getCountByCriteriaRWQY001(String ouCode, String year,
			String period, String userId, String orgCodeFrom, String orgCodeTo,String bankIdFrom,String bankIdTo,
			String empCodeFrom, String empCodeTo) throws Exception;

	public List findDetailPaySlip(String ouCode, String userId, String year,
			String period);
	
	public List findDetailPaySlip(String ouCode, String userId, String year,
			String period,String approveFlag);


	public void updatePrEmpByPrEmpText(FeeWpayPrEmployeeTextVO feeWpayPrEmployeeTextVO)
			throws Exception;

	public List findDetailPaySlipOverTime(String ouCode, String userId,
			String year, String period, String divCode, String areaCode,
			String secCode) throws Exception;

	public List findDetailPaySlipPremium(String ouCode, String userId,
			String year, String period, String divCode, String areaCode,
			String secCode) throws Exception;

	public List findDetailPaySlipHealth(String ouCode, String userId,
			String year, String period, String divCode, String areaCode,
			String secCode) throws Exception;

	public List findDetailPaySlipDanger(String ouCode, String userId,
			String year, String period, String divCode, String areaCode,
			String secCode) throws Exception;

	public List findDetailPaySlipOverTime(String ouCode, String userId,
			String year, String period, String divCode) throws Exception;

	public List findDetailPaySlipPremium(String ouCode, String userId,
			String year, String period, String divCode) throws Exception;

	public List findDetailPaySlipHealth(String ouCode, String userId,
			String year, String period, String divCode) throws Exception;

	public List findDetailPaySlipDanger(String ouCode, String userId,
			String year, String period, String divCode) throws Exception;
	
	public List findByCriteriaTax(String ouCode, int evaYear,
			int evaPeriod)
			throws Exception;
	
	public List findPrDailyRandom(String ouCode, int evaYear,
			int evaPeriod)
			throws Exception;
	
	public List findByCriteriaFeeEmpMT001(String ouCode,String userId, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo, int page, int recPerPage)
			throws Exception;

	public Integer getCountByCriteriaFeeEmpMT001(String ouCode, String userId, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo) throws Exception;
	
	public List findByCriteriaWGCONMT001(String ouCode,	String userId, String orgCodeFrom, String orgCodeTo,String yearCodeFrom, String yearCodeTo,
			String empCodeFrom, String empCodeTo,String dateFrom,String yearFrom,String yearTo,String monthFrom,String monthTo, int page, int recPerPage)
			throws Exception;
	
	/*public List findByCriteriaWGCONMT001(String ouCode,	String userId, String orgCodeFrom, String orgCodeTo,String yearCodeFrom, String yearCodeTo,
			String empCodeFrom, String empCodeTo,String dateFrom, int page, int recPerPage)
			throws Exception;
*/

	public Integer getCountByCriteriaWGCONMT001(String ouCode, String userId, String orgCodeFrom, String orgCodeTo,String yearCodeFrom, String yearCodeTo,
			String empCodeFrom, String empCodeTo,String dateFrom,String yearFrom,String yearTo,String monthFrom,String monthTo) throws Exception;
	
	public boolean isCreatedWpayPrEmployeeData(String ouCode, String year, String period,
			String userId) throws Exception;
	
	public List wpayKtbBankReport(String userId, String evaOuCode, int evaYear,
			int evaMonth);
	
	
	public List findBankIdBySecurity(String userId, String ouCode, String year,
			String period) throws Exception;
	
	public List feeWpayPrDailyRepByOrg(String userId, String evaOuCode,
			long evaYear, long evaPeriod);
	
	public List prDailyRepByOrg(String userId, String evaOuCode,
			long evaYear, long evaPeriod);
	
	public List feeWpay401Report(String evaOuCode,long evaYear, long evaPeriod,String flag);
	
	public List feeWpay401KTBReport(String evaOuCode,long evaYear, long evaPeriod,String flag);
	
	public List feeWpay401SCBReport(String evaOuCode,long evaYear, long evaPeriod,String flag);
	
	public List feeWpayGlTransferReport(String evaYear, String evaMonth);
	
	public List feeWpayPrMonthlyRepByOrg(String userId, String evaOuCode,
			long evaYear, long evaPeriod);
	
	public List findByCriteriaIntBrk001(String ouCode, String year,
			String period, String userId,String bankIdFrom,String bankIdTo,
			String empCodeFrom, String empCodeTo, int page, int recPerPage)
			throws Exception;
	
	public Integer getCountByCriteriaIntBrk001(String ouCode, String year,
			String period, String userId,String bankIdFrom,String bankIdTo,
			String empCodeFrom, String empCodeTo) throws Exception;
	
	public List feeWpayPrBreakPayReport(String userId, String evaOuCode, long evaYear,
			long evaPeriod);
	
	public List feeWpayPrBreakPayScbReport(String userId, String evaOuCode, long evaYear,
			long evaPeriod);
	
	
	public List feeWpay401UserReport(String evaOuCode,long evaYear, long evaPeriod,String flag,String userId);
	
	public List feeWpay401UserKTBReport(String evaOuCode,long evaYear, long evaPeriod,String flag,String userId);
	
	public List feeWpay401UserSCBReport(String evaOuCode,long evaYear, long evaPeriod,String flag,String userId);
	

}
