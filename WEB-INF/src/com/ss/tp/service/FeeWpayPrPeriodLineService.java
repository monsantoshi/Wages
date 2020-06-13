package com.ss.tp.service;

import java.util.List;

import com.ss.tp.dto.DefaultYearSectionVO;
//import com.ss.tp.model.PrPeriodLine;
import com.ss.tp.model.FeeWpayPrPeriodLine;

public interface FeeWpayPrPeriodLineService {
	public DefaultYearSectionVO getDefaultYearAndSection(String ouCode,
			String userId) throws Exception;
	
	public DefaultYearSectionVO getDefaultYearAndSectionOne(String ouCode,
			String userId) throws Exception;

	public DefaultYearSectionVO getDefaultYearAndSectionApprove(String ouCode,
			String userId) throws Exception;
	
	public DefaultYearSectionVO getDefaultYearAndSectionTranClose(String ouCode,
			String userId) throws Exception;

	
	public List findYearInPeriodLine(String ouCode) throws Exception;

	public List findPeriodInPeriodLine(String ouCode, double year,String useId)
			throws Exception;

	public boolean canDeleteData(String ouCode, String year, String period,String userId)
			throws Exception;

	public boolean isCloseTranClose(String ouCode, String year, String period,String userId)
			throws Exception;
	
	public boolean isCloseMasterClose(String ouCode, String year, String period,String userId)
			throws Exception;

	public FeeWpayPrPeriodLine findPeriodLine(String ouCode, String year, String period,String userId)
			throws Exception;
}
