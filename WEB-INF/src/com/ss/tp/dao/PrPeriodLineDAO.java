package com.ss.tp.dao;

import java.util.List;

import com.ss.tp.model.PrPeriodLine;

public interface PrPeriodLineDAO {
	/**
	 * Find Period Line
	 */
	public PrPeriodLine findPeriodLine(String ouCode, String year, String period)
			throws Exception;

	public List findYearInPeriodLine(String ouCode) throws Exception;

	public List findPeriodInPeriodLine(String ouCode, Double year)
			throws Exception;

	public boolean canDeleteData(String ouCode, String year, String period)
			throws Exception;

	public boolean isCloseTranClose(String ouCode, String year, String period)
			throws Exception;

	public boolean isCloseMasterClose(String ouCode, String year, String period)
			throws Exception;
}