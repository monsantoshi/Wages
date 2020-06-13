package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;


import com.ss.tp.dao.FeeWpayPnEmployeeDAO;

import com.ss.tp.dto.ApEmployeeVO;
import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.FeeWpayPayRollEmployeeVO;
import com.ss.tp.dto.PnEmployeeDetailVO;
import com.ss.tp.dto.FeeWpayPnEmployeeDetailVO;
import com.ss.tp.dto.WeEmployeeVO;


import com.ss.tp.service.FeeWpayPnEmployeeService;

public class FeeWpayPnEmployeeServiceImpl implements FeeWpayPnEmployeeService, Serializable {

	private FeeWpayPnEmployeeDAO feeWpayPnEmployeeDAO;
	public FeeWpayPnEmployeeDAO getFeeWpayPnEmployeeDAO() {
		return feeWpayPnEmployeeDAO;
	}

	public void setFeeWpayPnEmployeeDAO(FeeWpayPnEmployeeDAO feeWpayPnEmployeeDAO) {
		this.feeWpayPnEmployeeDAO = feeWpayPnEmployeeDAO;
	}
	public List findByCriteria(String ouCode) {
		return this.feeWpayPnEmployeeDAO.findByCriteria(ouCode);
	}

	public List findPrEmpBySecurity(String userId, String ouCode, String year,
			String period) throws Exception {
		return this.feeWpayPnEmployeeDAO.findPrEmpBySecurity(userId, ouCode, year,
				period);
	}

	public List findPrToEmpBySecurity(String userId, String ouCode,
			String fromEmpCode, String year, String period) throws Exception {
		return this.feeWpayPnEmployeeDAO.findPrToEmpBySecurity(userId, ouCode,
				fromEmpCode, year, period);
	}

	public List findPnEmpBySecurity(String userId, String ouCode)
			throws Exception {
		return this.feeWpayPnEmployeeDAO.findPnEmpBySecurity(userId, ouCode);
	}

	public List findWeEmpBySecurity(String userId, String ouCode)
			throws Exception {
		return this.feeWpayPnEmployeeDAO.findWeEmpBySecurity(userId, ouCode);
	}

	public FeeWpayPnEmployeeDetailVO findPnEmpDetailNotInSecue(String userId,
			String ouCode, String empCode, String year, String period)
			throws Exception {
		return this.feeWpayPnEmployeeDAO.findByCriteriaNotInSecue(userId, ouCode,
				empCode, year, period);
	}

	public FeeWpayPnEmployeeDetailVO findPnEmpDetailInSecue(String userId,
			String ouCode, String empCode, String year, String period)
			throws Exception {
		return this.feeWpayPnEmployeeDAO.findByCriteriaInSecue(userId, ouCode,
				empCode, year, period);
	}

	public List findToEmpBySecurity(String userId, String ouCode,
			String fromEmpCode) throws Exception {
		return this.feeWpayPnEmployeeDAO.findToEmpBySecurity(userId, ouCode,
				fromEmpCode);
	}

	public FeeWpayPayRollEmployeeVO findByEmpCodeDetail(String userId, String empCode,
			String ouCode, Long year, Long period) {
		return this.feeWpayPnEmployeeDAO.findByEmpCodeDetail(userId, empCode, ouCode,
				year, period);
	}

	public WeEmployeeVO findByWeEmpCodeDetail(String empCode, String ouCode) {
		return this.feeWpayPnEmployeeDAO.findByWeEmpCodeDetail(empCode, ouCode);
	}
	public ApEmployeeVO findByApEmpCodeDetail(String empCode) {
		return this.feeWpayPnEmployeeDAO.findByApEmpCodeDetail(empCode);
	}

	public WeEmployeeVO findByWeOrgCodeDetail(String orgCode, String ouCode) {
		return this.feeWpayPnEmployeeDAO.findByWeOrgCodeDetail(orgCode, ouCode);
	}

	public List findEmpByNotInSecurity(String userId, String ouCode)
			throws Exception {
		return this.feeWpayPnEmployeeDAO.findEmpByNotInSecurity(userId, ouCode);
	}

	public FeeWpayPayRollEmployeeVO findByEmpCodeDetailNotSecurity(String userId,
			String empCode, String ouCode, Long year, Long period) {
		return this.feeWpayPnEmployeeDAO.findByEmpCodeDetailNotSecurity(userId,
				empCode, ouCode, year, period);
	}

	public FeeWpayPayRollEmployeeVO findByEmpCodeDetailNotSecurityCTRW03(
			String userId, String empCode, String ouCode, Long year, Long period) {
		return this.feeWpayPnEmployeeDAO.findByEmpCodeDetailNotSecurityCTRW03(userId,
				empCode, ouCode, year, period);
	}

	public FeeWpayPnEmployeeDetailVO findPnEmployeeNotUseSecurity(String userId,
			String ouCode, String empCode, String year, String period)
			throws Exception {
		return this.feeWpayPnEmployeeDAO.findPnEmployeeNotUseSecurity(userId, ouCode,
				empCode, year, period);
	}

	public List findPrEmpMT001(String userId, String ouCode, String year,
			String period) throws Exception {
		return this.feeWpayPnEmployeeDAO.findPrEmpMT001(userId, ouCode, year, period);
	}

	public List findToPrEmpMT001(String userId, String ouCode,
			String fromEmpCode, String year, String period) throws Exception {
		return this.feeWpayPnEmployeeDAO.findToPrEmpMT001(userId, ouCode, year,
				period, fromEmpCode);
	}

	public FeeWpayPnEmployeeDetailVO findByCriteriaInSecueEmpText(String userId,
			String ouCode, String empCode, String year, String period)
			throws Exception {
		return this.feeWpayPnEmployeeDAO.findByCriteriaInSecueEmpText(userId, ouCode,
				empCode, year, period);
	}

	
	public List findFeeWgEmpBySecurity(String userId, String ouCode)
			throws Exception {
		return this.feeWpayPnEmployeeDAO.findFeeWgEmpBySecurity(userId, ouCode);
	}
	
	public List findFeeWgContractBySecurity(String userId, String ouCode)
			throws Exception {
		return this.feeWpayPnEmployeeDAO.findFeeWgContractBySecurity(userId, ouCode);
	}
	
	public List findFeeWgYearBySecurity(String userId, String ouCode)
			throws Exception {
		return this.feeWpayPnEmployeeDAO.findFeeWgYearBySecurity(userId, ouCode);
	}
	public List findContractNoBySecurity(String userId, String ouCode)
			throws Exception {
		return this.feeWpayPnEmployeeDAO.findContractNoBySecurity(userId, ouCode);
	}
	
	public List findYearConBySecurity(String userId, String ouCode)
			throws Exception {
		return this.feeWpayPnEmployeeDAO.findYearConBySecurity(userId, ouCode);
	}
	
	
	
}
