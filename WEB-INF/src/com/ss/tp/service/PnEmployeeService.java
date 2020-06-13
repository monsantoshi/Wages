package com.ss.tp.service;

import java.util.List;

import com.ss.tp.dto.ApEmployeeVO;
import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.PnEmployeeDetailVO;
import com.ss.tp.dto.WeEmployeeVO;

public interface PnEmployeeService {
	public List findByCriteria(String ouCode);

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

	public PnEmployeeDetailVO findPnEmpDetailNotInSecue(String userId,
			String ouCode, String empCode, String year, String period)
			throws Exception;

	public PnEmployeeDetailVO findPnEmpDetailInSecue(String userId,
			String ouCode, String empCode, String year, String period)
			throws Exception;

	public List findEmpByNotInSecurity(String userId, String ouCode)
			throws Exception;

	public List findWeEmpBySecurity(String userId, String ouCode)
			throws Exception;

	public PnEmployeeDetailVO findByCriteriaInSecueEmpText(String userId,
			String ouCode, String empCode, String year, String period)
			throws Exception;

	public PnEmployeeDetailVO findPnEmployeeNotUseSecurity(String userId,
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

	public PayRollEmployeeVO findByEmpCodeDetail(String userId, String empCode,
			String ouCode, Long year, Long period);

	public PayRollEmployeeVO findByEmpCodeDetailNotSecurity(String userId,
			String empCode, String ouCode, Long year, Long period);

	public PayRollEmployeeVO findByEmpCodeDetailNotSecurityCTRW03(
			String userId, String empCode, String ouCode, Long year, Long period);
}
