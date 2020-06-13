package com.ss.tp.dao;

import java.util.List;

//import com.ss.tp.model.PrPeriodLine;
import com.ss.tp.model.FeeWpayPrPeriodLine;

public interface FeeWpayPrPeriodLineDAO {
	/**
	 * Find Period Line
	 */
	public FeeWpayPrPeriodLine findPeriodLine(String ouCode, String year, String period,String userId)
			throws Exception;

	public List findYearInPeriodLine(String ouCode) throws Exception;

	public List findPeriodInPeriodLine(String ouCode, Double year,String userId)
			throws Exception;

	public boolean canDeleteData(String ouCode, String year, String period,String userId)
			throws Exception;

	public boolean isCloseTranClose(String ouCode, String year, String period,String userId)
			throws Exception;

	public boolean isCloseMasterClose(String ouCode, String year, String period,String userId)
			throws Exception;
}