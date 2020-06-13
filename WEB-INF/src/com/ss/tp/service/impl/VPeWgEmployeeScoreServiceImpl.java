/*
 * Created on 7 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.VPeWgEmployeeScoreDAO;
import com.ss.tp.service.VPeWgEmployeeScoreService;

/**
 * @author Kiet
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class VPeWgEmployeeScoreServiceImpl implements
		VPeWgEmployeeScoreService, Serializable {
	private VPeWgEmployeeScoreDAO vpeWgEmployeeScoreDAO;

	/**
	 * @return Returns the vpeEmployeeScoreDAO.
	 */
	public VPeWgEmployeeScoreDAO getVpeWgEmployeeScoreDAO() {
		return vpeWgEmployeeScoreDAO;
	}

	/**
	 * @param vpeEmployeeScoreDAO
	 *            The vpeEmployeeScoreDAO to set.
	 */
	public void setVpeWgEmployeeScoreDAO(
			VPeWgEmployeeScoreDAO vpeWgEmployeeScoreDAO) {
		this.vpeWgEmployeeScoreDAO = vpeWgEmployeeScoreDAO;
	}

	public List findVPeWgEmployeeScore(String userId, String ouCode,
			int evaYear, int evaYearTo, String orgFrom, String orgTo,
			String empCodeFrom, String empCodeTo) {
		return this.vpeWgEmployeeScoreDAO.findVPeWgEmployeeScore(userId,
				ouCode, new Integer(evaYear), new Integer(evaYearTo), orgFrom,
				orgTo, empCodeFrom, empCodeTo);
	}

	public List findVPeWgEmpScoreReport(String ouCode, String userId,
			int evaYear, int evaYearTo, String ouCodeFrom, String ouCodeTo,
			String empCodeFrom, String empCodeTo) {
		return this.vpeWgEmployeeScoreDAO.findVPeWgEmpScoreReport(ouCode,
				userId, new Integer(evaYear), new Integer(evaYearTo),
				ouCodeFrom, ouCodeTo, empCodeFrom, empCodeTo);
	}

	public String findOrgName(String ouCode) {
		return this.vpeWgEmployeeScoreDAO.findOrgName(ouCode);
	}

}
