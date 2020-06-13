package com.ss.tp.dao;

import java.util.List;
import com.ss.tp.dto.PayRollEmployeeReportVO;
import com.ss.tp.dto.RwPremiumReportVO;
import com.ss.tp.model.RwPremiumReport;

public interface FeeWpayRwPremiumReportDAO {
	public void insertRwPremium(RwPremiumReportVO rwpremiumreportvo)
			throws Exception;

	public void updateRwPremium(RwPremiumReportVO rwpremiumreportvo)
			throws Exception;

	public void deleteRwPremium(RwPremiumReportVO rwpremiumreportvo)
			throws Exception;

	public void insertRwPremiums(List rwpremiumvolist) throws Exception;

	public List findByCriteria(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo, int count, int countRecord);

	public Integer countData(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo);

	public PayRollEmployeeReportVO findByEmpCodeDetail(String empCode,
			String ouCode, Long year, Long period);

	public List findByEmpCodeList(String userId, String ouCode, Long yearPr,
			Long periodPr, String empCode);

	public RwPremiumReport loadRwPremium(RwPremiumReportVO rpVo);

	public List rwPremiumReportByOrgReport(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String evaFlag);

	public void addList(RwPremiumReportVO rwpremiumreportvo);

	public void insertAndUpdateRwPremiums() throws Exception;

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception;

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception;
}