/*
 * Created on 27 ?.?. 2549
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
public interface PeEmpScoreReportService {
	public List findPeEmpScoreReport(String ouCode, String userId, int evaYear,
			int evaTime, String areaCode, String secCode, String workCode);

	public String findOrgName(String ouCode);
}
