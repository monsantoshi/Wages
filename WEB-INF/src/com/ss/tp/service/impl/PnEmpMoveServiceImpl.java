package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.PnEmpMoveDAO;

import com.ss.tp.service.PnEmpMoveService;

public class PnEmpMoveServiceImpl implements PnEmpMoveService, Serializable {
	PnEmpMoveDAO pnEmpMoveDAO;

	public List findByCriteriaReport1(String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo, String choiceStatus,
			String choiceGroup) {
		return this.pnEmpMoveDAO.findByCriteriaReport1(orgCodeFrom, orgCodeTo,
				empCodeFrom, empCodeTo, choiceStatus, choiceGroup);
	}

	public List findByCriteriaReport2(String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo, String choiceStatus,
			String choiceGroup) {
		return this.pnEmpMoveDAO.findByCriteriaReport2(orgCodeFrom, orgCodeTo,
				empCodeFrom, empCodeTo, choiceStatus, choiceGroup);
	}

	public PnEmpMoveDAO getPnEmpMoveDAO() {
		return pnEmpMoveDAO;
	}

	public void setPnEmpMoveDAO(PnEmpMoveDAO pnEmpMoveDAO) {
		this.pnEmpMoveDAO = pnEmpMoveDAO;
	}
}
