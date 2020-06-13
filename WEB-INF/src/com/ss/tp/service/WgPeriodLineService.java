/*
 * Created on 15 �.�. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.service;

import java.util.List;

import com.ss.tp.dto.DefaultYearSectionVO;

/**
 * @author Administrator
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public interface WgPeriodLineService {
	public List findYearInPeriodLine(String ouCode) throws Exception;

	public List findPeriodInPeriodLine(String ouCode, int year)
			throws Exception;

	public DefaultYearSectionVO getDefaultYearAndSection(String ouCode,
			String userId) throws Exception;

	/*
	 * start airsenal 01/11/2006 add new method for decrease one month of
	 * defalut year
	 */
	public DefaultYearSectionVO getDefaultYearAndSectionMinusOne(String ouCode,
			String userId) throws Exception;

	/*
	 * end airsenal 01/11/2006 add new method for decrease one month of defalut
	 * year
	 */
	public boolean isCloseTranClose(String ouCode, String year, String period)
			throws Exception;

	public String findMaxPeriodOfYear(String ouCode, String year)
			throws Exception;

}
