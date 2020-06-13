package com.ss.tp.service;

import java.util.List;

import com.ss.tp.dto.PrEmployeeTextVO;
import com.ss.tp.dto.PrEmployeeVO;



public interface PrEmployeeService {
	/**
	 * Find Max year and Max section of OU_CODE
	 */
	public String[] findMaxYearPeriod(String ouCode);

	public PrEmployeeVO findPrEmployee(String ouCode, String year,
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
			String period, String userId, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo, int page, int recPerPage)
			throws Exception;
	
	public Integer getCountByCriteriaRWQY001(String ouCode, String year,
			String period, String userId, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo) throws Exception;

	public List findDetailPaySlip(String ouCode, String userId, String year,
			String period);

	public void updatePrEmpByPrEmpText(PrEmployeeTextVO prEmpTextVo)
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
}
