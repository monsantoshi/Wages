package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.WgEmployeeDAO;

import com.ss.tp.dto.GwIncdecEmployeeVO;
import com.ss.tp.service.WgEmployeeService;

public class WgEmployeeServiceImpl implements WgEmployeeService, Serializable {
	private WgEmployeeDAO wgEmployeeDAO;

	public WgEmployeeDAO getWgEmployeeDAO() {
		return wgEmployeeDAO;
	}

	public void setWgEmployeeDAO(WgEmployeeDAO wgEmployeeDAO) {
		this.wgEmployeeDAO = wgEmployeeDAO;
	}

	public List findWgEmpBySecurity(String userId, String ouCode)
			throws Exception {
		return this.wgEmployeeDAO.findWgEmpBySecurity(userId, ouCode);
	}

	public List findToEmpBySecurity(String userId, String ouCode,
			String fromEmpCode) throws Exception {
		return this.wgEmployeeDAO.findToEmpBySecurity(userId, ouCode,
				fromEmpCode);
	}

	public List findWgPrToEmpBySecurity(String userId, String ouCode,
			String fromEmpCode, String year, String period) throws Exception {
		return this.wgEmployeeDAO.findWgPrToEmpBySecurity(userId, ouCode,
				fromEmpCode, year, period);
	}

	public GwIncdecEmployeeVO findByEmpCodeDetail(String userId,
			String empCode, String ouCode, Long year, Long period) {
		return this.wgEmployeeDAO.findByEmpCodeDetail(userId, empCode, ouCode,
				year, period);
	}

	public GwIncdecEmployeeVO findByEmpCodeDetailNotSecurity(String userId,
			String empCode, String ouCode, Long year, Long period) {
		return this.wgEmployeeDAO.findByEmpCodeDetailNotSecurity(userId,
				empCode, ouCode, year, period);
	}

	public GwIncdecEmployeeVO findByEmpCodeDetailInSecurity(String userId,
			String empCode, String ouCode, Long year, Long period) {
		return this.wgEmployeeDAO.findByEmpCodeDetailInSecurity(userId,
				empCode, ouCode, year, period);
	}

}
