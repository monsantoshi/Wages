package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.PnEmpImpDAO;

import com.ss.tp.service.PnEmpImpService;

public class PnEmpImpServiceImpl implements PnEmpImpService, Serializable {
	PnEmpImpDAO pnEmpImpDAO;

	public List findByCriteriaReport(int workYear, int workYearFrom,
			int workYearTo, String monthFrom, String monthTo) {
		return this.pnEmpImpDAO.findByCriteriaReport(new Integer(workYear),
				new Integer(workYearFrom), new Integer(workYearTo), monthFrom,
				monthTo);

	}

	public PnEmpImpDAO getPnEmpImpDAO() {
		return pnEmpImpDAO;
	}

	public void setPnEmpImpDAO(PnEmpImpDAO pnEmpImpDAO) {
		this.pnEmpImpDAO = pnEmpImpDAO;
	}
	public List findByCriteriaReportEngNameNew(int workYear, int workYearFrom,
			int workYearTo, String monthFrom, String monthTo) {
		return this.pnEmpImpDAO.findByCriteriaReportEngNameNew(new Integer(workYear),
				new Integer(workYearFrom), new Integer(workYearTo), monthFrom,
				monthTo);

	}
	public List findByCriteriaReportEngNameOld(int workYear, int workYearFrom,
			int workYearTo, String monthFrom, String monthTo) {
		return this.pnEmpImpDAO.findByCriteriaReportEngNameOld(new Integer(workYear),
				new Integer(workYearFrom), new Integer(workYearTo), monthFrom,
				monthTo);

	}
}
