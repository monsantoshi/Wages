/*
 * Created on 27 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.PeWgEmpScoreReportDAO;
import com.ss.tp.service.PeWgEmpScoreReportService;

/**
 * @author Kiet
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class PeWgEmpScoreReportServiceImpl implements
		PeWgEmpScoreReportService, Serializable {
	private PeWgEmpScoreReportDAO peWgEmpScoreReportDAO;

	/**
	 * @return Returns the peWgEmpScoreReportDAO.
	 */
	public PeWgEmpScoreReportDAO getPeWgEmpScoreReportDAO() {
		return peWgEmpScoreReportDAO;
	}

	/**
	 * @param peEmpScoreReportDAO
	 *            The peWgEmpScoreReportDAO to set.
	 */
	public void setPeWgEmpScoreReportDAO(
			PeWgEmpScoreReportDAO peWgEmpScoreReportDAO) {
		this.peWgEmpScoreReportDAO = peWgEmpScoreReportDAO;
	}

	public List findPeWgEmpScoreReport(String ouCode, String userId,
			int evaYear, int evaTime, String areaCode, String secCode,
			String workCode) {
		return this.peWgEmpScoreReportDAO.findPeWgEmpScoreReport(ouCode,
				userId, new Integer(evaYear), new Integer(evaTime), areaCode,
				secCode, workCode);
	}

	public String findOrgName(String ouCode) {
		return this.peWgEmpScoreReportDAO.findOrgName(ouCode);
	}
}
