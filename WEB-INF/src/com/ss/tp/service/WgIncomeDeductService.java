package com.ss.tp.service;

import java.util.List;

import com.ss.tp.dto.WgPrIncDecOtherVO;

public interface WgIncomeDeductService {
	public void insertWgIncomeDeduct(WgPrIncDecOtherVO wgincomevo)
			throws Exception;

	public void updateWgIncomeDeduct(WgPrIncDecOtherVO wgincomevo)
			throws Exception;

	public void deleteWgIncomeDeduct(WgPrIncDecOtherVO wgincomevo)
			throws Exception;

	public void insertWgIncomeDeducts(List wgincomevolist) throws Exception;

	public List findIncDecCode(String ouCode, String groupCode)
			throws Exception;

	public String getIncDecName(String ouCode, int groupCode, String incDecCode);

	public List incDecOtherReport(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String incDecCode, String evaFlag);

	public List incDecOtherReportCountSheet(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String incDecCode, String evaFlag);

	public List decDecOtherReport(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String incDecCode, String evaFlag);

	public List decDecOtherReportCountSheet(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String incDecCode, String evaFlag);
}
