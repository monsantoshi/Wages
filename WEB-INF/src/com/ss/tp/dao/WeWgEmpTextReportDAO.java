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
public interface WeWgEmpTextReportDAO {
	public List findListPntReport(String ouCode, String userId,
			String orgCodeSeqFrom, String orgCodeSeqTo, String empCodeFrom,
			String empCodeTo);

	public List findListSecPntReport(String ouCode, String userId,
			String orgCodeSeqFrom, String orgCodeSeqTo, String empCodeFrom,
			String empCodeTo);

	public List findListPntReportNew(String ouCode, String userId,
			String orgCodeFrom, String orgCodeTo, String empCodeFrom,
			String empCodeTo);

	public List findListSecPntReportNew(String ouCode, String userId,
			String orgCodeFrom, String orgCodeTo, String empCodeFrom,
			String empCodeTo);

	public String findAreaDesc(String orgCode);

	public String findAreaDescTwo(String areaCode);
}
