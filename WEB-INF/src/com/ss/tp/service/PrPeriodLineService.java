package com.ss.tp.service;

import java.util.List;

import com.ss.tp.dto.DefaultYearSectionVO;
import com.ss.tp.model.PrPeriodLine;

public interface PrPeriodLineService {
	public DefaultYearSectionVO getDefaultYearAndSection(String ouCode,
			String userId) throws Exception;

	public List findYearInPeriodLine(String ouCode) throws Exception;

	public List findPeriodInPeriodLine(String ouCode, double year)
			throws Exception;

	public boolean canDeleteData(String ouCode, String year, String period)
			throws Exception;

	public boolean isCloseTranClose(String ouCode, String year, String period)
			throws Exception;
	
	public boolean isCloseMasterClose(String ouCode, String year, String period)
			throws Exception;

	public PrPeriodLine findPeriodLine(String ouCode, String year, String period)
			throws Exception;
}
