package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.PnSalaryDAO;
import com.ss.tp.service.PnSalaryService;

;

public class PnSalaryServiceImpl implements PnSalaryService, Serializable {

	private PnSalaryDAO pnSalaryDAO;

	public PnSalaryDAO getPnSalaryDAO() {
		return pnSalaryDAO;
	}

	public void setPnSalaryDAO(PnSalaryDAO pnSalaryDAO) {
		this.pnSalaryDAO = pnSalaryDAO;
	}

	public List findSalary(String ouCode) {
		return this.pnSalaryDAO.findSalary(ouCode);
	}

}
