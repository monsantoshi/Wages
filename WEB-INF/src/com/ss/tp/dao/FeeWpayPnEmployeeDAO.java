package com.ss.tp.dao;

import java.util.List;

import com.ss.tp.dto.ApEmployeeVO;
import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.FeeWpayPayRollEmployeeVO;


import com.ss.tp.dto.PnEmployeeDetailVO;
import com.ss.tp.dto.FeeWpayPnEmployeeDetailVO;
import com.ss.tp.dto.WeEmployeeVO;

public interface FeeWpayPnEmployeeDAO {
	public String findEmpName(String ouCode, String empCode);

	public List findByCriteria(String ouCode);

	public FeeWpayPnEmployeeDetailVO findByCriteriaNotInSecue(String userId,
			String ouCode, String empCode, String year, String period)
			throws Exception;

	public FeeWpayPnEmployeeDetailVO findByCriteriaInSecue(String userId,
			String ouCode, String empCode, String year, String period)
			throws Exception;

	/**
	 * Find Employee by Security ( Code_seq )
	 * 
	 * @param userId
	 * @param ouCode
	 * @return
	 * @throws Exception
	 */
	public List findPrEmpMT001(String userId, String ouCode, String year,
			String period) throws Exception;

	public List findToPrEmpMT001(String userId, String ouCode, String year,
			String period, String fromEmpCode) throws Exception;

	public List findPrEmpBySecurity(String userId, String ouCode, String year,
			String period) throws Exception;

	public List findPrToEmpBySecurity(String userId, String ouCode,
			String fromEmpCode, String year, String period) throws Exception;

	public List findPnEmpBySecurity(String userId, String ouCode)
			throws Exception;

	public List findEmpByNotInSecurity(String userId, String ouCode)
			throws Exception;

	public List findWeEmpBySecurity(String userId, String ouCode)
			throws Exception;

	public FeeWpayPnEmployeeDetailVO findByCriteriaInSecueEmpText(String userId,
			String ouCode, String empCode, String year, String period)
			throws Exception;

	public FeeWpayPnEmployeeDetailVO findPnEmployeeNotUseSecurity(String userId,
			String ouCode, String empCode, String year, String period)
			throws Exception;

	/**
	 * Find Employee by Security depend on empCode ( Code_seq )
	 * 
	 * @param userId
	 * @param ouCode
	 * @param fromEmpCode
	 * @return
	 * @throws Exception
	 */
	public List findToEmpBySecurity(String userId, String ouCode,
			String fromEmpCode) throws Exception;

	public WeEmployeeVO findByWeEmpCodeDetail(String empCode, String ouCode);

	
	public ApEmployeeVO findByApEmpCodeDetail(String empCode);

	public WeEmployeeVO findByWeOrgCodeDetail(String orgCode, String ouCode);

	public FeeWpayPayRollEmployeeVO findByEmpCodeDetail(String userId, String empCode,
			String ouCode, Long year, Long period);

	public FeeWpayPayRollEmployeeVO findByEmpCodeDetailNotSecurity(String userId,
			String empCode, String ouCode, Long year, Long period);

	public FeeWpayPayRollEmployeeVO findByEmpCodeDetailNotSecurityCTRW03(
			String userId, String empCode, String ouCode, Long year, Long period);
	
	public List findFeeWgEmpBySecurity(String userId, String ouCode) throws Exception;
	
	public List findFeeWgContractBySecurity(String userId, String ouCode) throws Exception;
	
	public List findFeeWgYearBySecurity(String userId, String ouCode) throws Exception;
	
	
	public List findContractNoBySecurity(String userId, String ouCode) throws Exception;
	
	public List findYearConBySecurity(String userId, String ouCode) throws Exception;
	

}
