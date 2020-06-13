/*
 * Created on 27 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.PeEmpScoreReportDAO;
import com.ss.tp.service.PeEmpScoreReportService;

/**
 * @author Kiet
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class PeEmpScoreReportServiceImpl implements PeEmpScoreReportService,
		Serializable {
	private PeEmpScoreReportDAO peEmpScoreReportDAO;

	/**
	 * @return Returns the peEmpScoreReportDAO.
	 */
	public PeEmpScoreReportDAO getPeEmpScoreReportDAO() {
		return peEmpScoreReportDAO;
	}

	/**
	 * @param peEmpScoreReportDAO
	 *            The peEmpScoreReportDAO to set.
	 */
	public void setPeEmpScoreReportDAO(PeEmpScoreReportDAO peEmpScoreReportDAO) {
		this.peEmpScoreReportDAO = peEmpScoreReportDAO;
	}

	public List findPeEmpScoreReport(String ouCode, String userId, int evaYear,
			int evaTime, String areaCode, String secCode, String workCode) {
		return this.peEmpScoreReportDAO.findPeEmpScoreReport(ouCode, userId,
				new Integer(evaYear), new Integer(evaTime), areaCode, secCode,
				workCode);
	}

	public String findOrgName(String ouCode) {
		return this.peEmpScoreReportDAO.findOrgName(ouCode);
	}
}
