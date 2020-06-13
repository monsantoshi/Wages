/*
 * Created on 8 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.service.impl;

import java.io.Serializable;

import java.util.List;

import com.ss.tp.dao.VPeEmpScoreReportDAO;
import com.ss.tp.service.VPeEmpScoreReportService;

/**
 * @author Kiet
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class VPeEmpScoreReportServiceImpl implements VPeEmpScoreReportService,
		Serializable {
	private VPeEmpScoreReportDAO vpeEmpScoreReportDAO;

	/**
	 * @return Returns the vpeEmpScoreReportDAO.
	 */
	public VPeEmpScoreReportDAO getVpeEmpScoreReportDAO() {
		return vpeEmpScoreReportDAO;
	}

	/**
	 * @param vpeEmpScoreReportDAO
	 *            The vpeEmpScoreReportDAO to set.
	 */
	public void setVpeEmpScoreReportDAO(
			VPeEmpScoreReportDAO vpeEmpScoreReportDAO) {
		this.vpeEmpScoreReportDAO = vpeEmpScoreReportDAO;
	}

	public List findVPeEmpScoreReport(String ouCode, String userId,
			int evaYear, int evaYearTo, String ouCodeFrom, String ouCodeTo,
			String empCodeFrom, String empCodeTo) {
		return this.vpeEmpScoreReportDAO.findVPeEmpScoreReport(ouCode, userId,
				new Integer(evaYear), new Integer(evaYearTo), ouCodeFrom,
				ouCodeTo, empCodeFrom, empCodeTo);
	}

	public String findOrgName(String ouCode) {
		return this.vpeEmpScoreReportDAO.findOrgName(ouCode);
	}

}
