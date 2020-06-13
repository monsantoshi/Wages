package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.WgPrEmployeeDAO;
import com.ss.tp.dto.PnEmployeeDetailVO;
import com.ss.tp.dto.WgEmployeeDetailVO;
import com.ss.tp.dto.WgPrEmployeeVO;
import com.ss.tp.service.WgPrEmployeeService;

public class WgPrEmployeeServiceImpl implements WgPrEmployeeService,
		Serializable {
	private WgPrEmployeeDAO wgPrEmployeeDAO;

	public WgPrEmployeeDAO getWgPrEmployeeDAO() {
		return wgPrEmployeeDAO;
	}

	public void setWgPrEmployeeDAO(WgPrEmployeeDAO wgPrEmployeeDAO) {
		this.wgPrEmployeeDAO = wgPrEmployeeDAO;
	}

	public List findWgPrEmpBySecurity(String userId, String ouCode,
			String year, String period) throws Exception {
		return this.wgPrEmployeeDAO.findWgPrEmpBySecurity(userId, ouCode, year,
				period);
	}

	public List findWgPrToEmpBySecurity(String userId, String ouCode,
			String fromEmpCode, String year, String period) throws Exception {
		return this.wgPrEmployeeDAO.findWgPrToEmpBySecurity(userId, ouCode,
				fromEmpCode, year, period);
	}

	public List findByCriteria(String ouCode, String year, String period,
			String userId, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo, int page, int recPerPage)
			throws Exception {
		return this.wgPrEmployeeDAO.findByCriteria(ouCode, year, period,
				userId, orgCodeFrom, orgCodeTo, empCodeFrom, empCodeTo, page,
				recPerPage);
	}

	public Integer getCountByCriteria(String ouCode, String year,
			String period, String userId, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo) throws Exception {
		return this.wgPrEmployeeDAO.getCountByCriteria(ouCode, year, period,
				userId, orgCodeFrom, orgCodeTo, empCodeFrom, empCodeTo);
	}

	public PnEmployeeDetailVO findWgPrEmpNotInSecue(String userId,
			String ouCode, String empCode, String year, String period)
			throws Exception {
		return this.wgPrEmployeeDAO.findWgPrEmpNotInSecue(userId, ouCode,
				empCode, year, period);
	}

	public WgEmployeeDetailVO findWgPrEmpInSecue(String userId, String ouCode,
			String empCode, String year, String period) throws Exception {
		return this.wgPrEmployeeDAO.findWgPrEmpInSecue(userId, ouCode, empCode,
				year, period);
	}

	public WgPrEmployeeVO findWgPrEmp(String ouCode, String year,
			String period, String yearPrev, String periodPrev, String empCode,
			String userId) {
		return this.wgPrEmployeeDAO.findWgPrEmp(ouCode, year, period, yearPrev,
				periodPrev, empCode, userId);
	}

	public List findDetailPaySlip(String ouCode, String userId, String year,
			String period) {
		return this.wgPrEmployeeDAO.findDetailPaySlip(ouCode, userId, year,
				period);
	}
}
