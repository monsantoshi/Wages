/*
 * Created on 7 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.dao;

import java.util.List;

/**
 * @author Kiet
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public interface VPeWgEmployeeScoreDAO {
	public List findVPeWgEmployeeScore(String userId, String ouCode,
			Integer evaYear, Integer evaYearTo, String orgFrom, String orgTo,
			String empCodeFrom, String empCodeTo);

	public List findVPeWgEmpScoreReport(String ouCode, String userId,
			Integer evaYear, Integer evaYearTo, String orgCodeFrom,
			String orgCodeTo, String empCodeFrom, String empCodeTo);

	public String findOrgName(String ouCode);
}
