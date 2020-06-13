package com.ss.tp.service;

import java.util.List;
import com.ss.tp.dto.PayRollEmployeeReportVO;
import com.ss.tp.dto.RwPremiumReportVO;

public interface FeeWpayRwPremiumReportService {
	public void insertRwPremium(RwPremiumReportVO rwpremiumreportvo)
			throws Exception;

	public void updateRwPremium(RwPremiumReportVO rwpremiumreportvo)
			throws Exception;

	public void deleteRwPremium(RwPremiumReportVO rwpremiumreportvo)
			throws Exception;

	public void insertRwPremiums(List rwpremiumreportvolist) throws Exception;

	public List findByCriteria(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo, int count, int countRecord);

	public Integer countData(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo);

	public PayRollEmployeeReportVO findByEmpCodeDetail(String empCode,
			String ouCode, Long year, Long period);

	public List findByEmpCodeList(String userId, String ouCode, long yearPr,
			long periodPr, String empCode);

	public List rwPremiumReportByOrgReport(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaFlag);

	public void addList(RwPremiumReportVO rpVo, boolean isSave);

	public void insertAndUpdate() throws Exception;

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception;

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception;
}