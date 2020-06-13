/*
 * Created on 15 �.�. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.dao;

import java.util.List;

import com.ss.tp.model.WgPeriodLine;

/**
 * @author Administrator
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public interface WgPeriodLineDAO {
	public WgPeriodLine findPeriodLine(String ouCode, String year, String period)
			throws Exception;

	public List findYearInPeriodLine(String ouCode) throws Exception;

	public List findPeriodInPeriodLine(String ouCode, Integer year)
			throws Exception;

	public boolean isCloseTranClose(String ouCode, String year, String period)
			throws Exception;

	public String findMaxPeriodOfYear(String ouCode, String year);
}
