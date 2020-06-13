/*
 * Created on 7 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.VPeEmployeeScoreDAO;
import com.ss.tp.service.VPeEmployeeScoreService;

/**
 * @author Administrator
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class VPeEmployeeScoreServiceImpl implements VPeEmployeeScoreService,
		Serializable {
	private VPeEmployeeScoreDAO vpeEmployeeScoreDAO;

	/**
	 * @return Returns the vpeEmployeeScoreDAO.
	 */
	public VPeEmployeeScoreDAO getVpeEmployeeScoreDAO() {
		return vpeEmployeeScoreDAO;
	}

	/**
	 * @param vpeEmployeeScoreDAO
	 *            The vpeEmployeeScoreDAO to set.
	 */
	public void setVpeEmployeeScoreDAO(VPeEmployeeScoreDAO vpeEmployeeScoreDAO) {
		this.vpeEmployeeScoreDAO = vpeEmployeeScoreDAO;
	}

	public List findVPeEmployeeScore(String userId, String ouCode, int evaYear,
			int evaYearTo, String orgFrom, String orgTo, String empCodeFrom,
			String empCodeTo) {
		return this.vpeEmployeeScoreDAO.findVPeEmployeeScore(userId, ouCode,
				new Integer(evaYear), new Integer(evaYearTo), orgFrom, orgTo,
				empCodeFrom, empCodeTo);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ss.tp.service.VPeEmployeeScoreService#findVPeEmployeeScore(java.lang
	 * .String, java.lang.String)
	 */

}
