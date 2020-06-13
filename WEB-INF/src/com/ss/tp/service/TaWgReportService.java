/*
 * Created on 28 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.service;

import java.util.List;
import java.util.Vector;

/**
 * @author bowpoko
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public interface TaWgReportService {
	public List findListTaReport(String ouCode, String userId, int workYear,
			String orgCodeSeqFrom, String orgCodeSeqTo, String empCodeFrom,
			String empCodeTo);

	public List findListSecTaReport(String ouCode, String userId, int workYear,
			String orgCodeSeqFrom, String orgCodeSeqTo, String empCodeFrom,
			String empCodeTo);

	public List findListTaWgReportNew(String ouCode, String userId, int workYear,
			int workYearFrom, int workYearTo, String monthFrom, String monthTo,
			String orgCodeFrom, String orgCodeTo, String empCodeFrom,
			String empCodeTo, String choice, Vector statusWorkCode,
			Vector dataStatusFrom, Vector dataStatusTo, int sumDonKeyFrom,
			int sumDonKeyTo, String workCodeFrom, String workCodeTo);
	
	public List findListSecTaWgReportNew(String ouCode, String userId,
			int workYear, int workYearFrom, int workYearTo, String monthFrom,
			String monthTo, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo, String choice,
			Vector statusWorkCode, Vector dataStatusFrom, Vector dataStatusTo,
			int sumDonKeyFrom, int sumDonKeyTo, String workCodeFrom,
			String workCodeTo);
	public String findAreaDesc(String orgCode);

	public String findAreaDescTwo(String areaCode);
}
