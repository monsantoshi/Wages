/*
 * Created on 8 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.service;

import java.util.List;

/**
 * @author Kiet
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public interface VPeEmpScoreReportService {
	public List findVPeEmpScoreReport(String ouCode, String userId,
			int evaYear, int evaYearTo, String ouCodeFrom, String ouCodeTo,
			String empCodeFrom, String empCodeTo);

	public String findOrgName(String ouCode);
}
