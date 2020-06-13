package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.PnEmployeeDAO;

import com.ss.tp.dto.ApEmployeeVO;
import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.PnEmployeeDetailVO;
import com.ss.tp.dto.WeEmployeeVO;

import com.ss.tp.service.PnEmployeeService;

public class PnEmployeeServiceImpl implements PnEmployeeService, Serializable {
	private PnEmployeeDAO pnEmployeeDAO;

	public PnEmployeeDAO getPnEmployeeDAO() {
		return pnEmployeeDAO;
	}

	public void setPnEmployeeDAO(PnEmployeeDAO pnEmployeeDAO) {
		this.pnEmployeeDAO = pnEmployeeDAO;
	}

	public List findByCriteria(String ouCode) {
		return this.pnEmployeeDAO.findByCriteria(ouCode);
	}

	public List findPrEmpBySecurity(String userId, String ouCode, String year,
			String period) throws Exception {
		return this.pnEmployeeDAO.findPrEmpBySecurity(userId, ouCode, year,
				period);
	}

	public List findPrToEmpBySecurity(String userId, String ouCode,
			String fromEmpCode, String year, String period) throws Exception {
		return this.pnEmployeeDAO.findPrToEmpBySecurity(userId, ouCode,
				fromEmpCode, year, period);
	}

	public List findPnEmpBySecurity(String userId, String ouCode)
			throws Exception {
		return this.pnEmployeeDAO.findPnEmpBySecurity(userId, ouCode);
	}

	public List findWeEmpBySecurity(String userId, String ouCode)
			throws Exception {
		return this.pnEmployeeDAO.findWeEmpBySecurity(userId, ouCode);
	}

	public PnEmployeeDetailVO findPnEmpDetailNotInSecue(String userId,
			String ouCode, String empCode, String year, String period)
			throws Exception {
		return this.pnEmployeeDAO.findByCriteriaNotInSecue(userId, ouCode,
				empCode, year, period);
	}

	public PnEmployeeDetailVO findPnEmpDetailInSecue(String userId,
			String ouCode, String empCode, String year, String period)
			throws Exception {
		return this.pnEmployeeDAO.findByCriteriaInSecue(userId, ouCode,
				empCode, year, period);
	}

	public List findToEmpBySecurity(String userId, String ouCode,
			String fromEmpCode) throws Exception {
		return this.pnEmployeeDAO.findToEmpBySecurity(userId, ouCode,
				fromEmpCode);
	}

	public PayRollEmployeeVO findByEmpCodeDetail(String userId, String empCode,
			String ouCode, Long year, Long period) {
		return this.pnEmployeeDAO.findByEmpCodeDetail(userId, empCode, ouCode,
				year, period);
	}

	public WeEmployeeVO findByWeEmpCodeDetail(String empCode, String ouCode) {
		return this.pnEmployeeDAO.findByWeEmpCodeDetail(empCode, ouCode);
	}
	public ApEmployeeVO findByApEmpCodeDetail(String empCode) {
		return this.pnEmployeeDAO.findByApEmpCodeDetail(empCode);
	}

	public WeEmployeeVO findByWeOrgCodeDetail(String orgCode, String ouCode) {
		return this.pnEmployeeDAO.findByWeOrgCodeDetail(orgCode, ouCode);
	}

	public List findEmpByNotInSecurity(String userId, String ouCode)
			throws Exception {
		return this.pnEmployeeDAO.findEmpByNotInSecurity(userId, ouCode);
	}

	public PayRollEmployeeVO findByEmpCodeDetailNotSecurity(String userId,
			String empCode, String ouCode, Long year, Long period) {
		return this.pnEmployeeDAO.findByEmpCodeDetailNotSecurity(userId,
				empCode, ouCode, year, period);
	}

	public PayRollEmployeeVO findByEmpCodeDetailNotSecurityCTRW03(
			String userId, String empCode, String ouCode, Long year, Long period) {
		return this.pnEmployeeDAO.findByEmpCodeDetailNotSecurityCTRW03(userId,
				empCode, ouCode, year, period);
	}

	public PnEmployeeDetailVO findPnEmployeeNotUseSecurity(String userId,
			String ouCode, String empCode, String year, String period)
			throws Exception {
		return this.pnEmployeeDAO.findPnEmployeeNotUseSecurity(userId, ouCode,
				empCode, year, period);
	}

	public List findPrEmpMT001(String userId, String ouCode, String year,
			String period) throws Exception {
		return this.pnEmployeeDAO.findPrEmpMT001(userId, ouCode, year, period);
	}

	public List findToPrEmpMT001(String userId, String ouCode,
			String fromEmpCode, String year, String period) throws Exception {
		return this.pnEmployeeDAO.findToPrEmpMT001(userId, ouCode, year,
				period, fromEmpCode);
	}

	public PnEmployeeDetailVO findByCriteriaInSecueEmpText(String userId,
			String ouCode, String empCode, String year, String period)
			throws Exception {
		return this.pnEmployeeDAO.findByCriteriaInSecueEmpText(userId, ouCode,
				empCode, year, period);
	}
}
