/*
 * Created on 26 �.�. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.dao;

import java.util.List;
import java.util.Vector;

/**
 * @author bowpoko
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public interface WpayPrWorkDayReportDAO {
	public List findListTaReport(String ouCode, String userId,
			Integer workYear, String orgCodeSeqFrom, String orgCodeSeqTo,
			String empCodeFrom, String empCodeTo);

	public List findListSecTaReport(String ouCode, String userId,
			Integer workYear, String orgCodeSeqFrom, String orgCodeSeqTo,
			String empCodeFrom, String empCodeTo);

	public List findListTaReportNew(String ouCode, String userId,
			Integer workYear, Integer workYearFrom, Integer workYearTo,
			String monthFrom, String monthTo, String orgCodeFrom,
			String orgCodeTo, String empCodeFrom, String empCodeTo,
			String choice, Vector statusWorkCode, Vector dataStatusFrom,
			Vector dataStatusTo, Integer sumDonKeyFrom, Integer sumDonKeyTo,
			String workCodeFrom, String workCodeTo);

	public List findListSecTaReportNew(String ouCode, String userId,
			Integer workYear, Integer workYearFrom, Integer workYearTo,
			String monthFrom, String monthTo, String orgCodeFrom,
			String orgCodeTo, String empCodeFrom, String empCodeTo,
			String choice, Vector statusWorkCode, Vector dataStatusFrom,
			Vector dataStatusTo, Integer sumDonKeyFrom, Integer sumDonKeyTo,
			String workCodeFrom, String workCodeTo);

	public String findAreaDesc(String orgCode);

	public String findAreaDescTwo(String areaCode);
}
