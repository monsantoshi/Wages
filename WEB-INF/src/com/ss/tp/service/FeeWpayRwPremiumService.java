package com.ss.tp.service;

import java.util.List;
import com.ss.tp.dto.FeeWpayPayRollEmployeeVO;
import com.ss.tp.dto.FeeWpayRwOvertimeVO;
import com.ss.tp.dto.FeeWpayRwPremiumVO;

public interface FeeWpayRwPremiumService {
	public void insertRwPremium(FeeWpayRwPremiumVO rwpremiumvo) throws Exception;

	public void updateRwPremium(FeeWpayRwPremiumVO rwpremiumvo) throws Exception;

	public void deleteRwPremium(FeeWpayRwPremiumVO rwpremiumvo) throws Exception;

	public void insertRwPremiums(List rwpremiumvolist) throws Exception;

	public List findByCriteria(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo, int count, int countRecord);

	public Integer countData(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo);

	public FeeWpayPayRollEmployeeVO findByEmpCodeDetail(String empCode, String ouCode,
			Long year, Long period);

	public List findByEmpCodeList(String userId, String ouCode, long yearPr,
			long periodPr, String empCode);

	public List rwPremiumReportByOrg(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaFlag);
	
	public List rwPremiumReportByOrg(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaFlag,String evaAppFlag);


	public void addList(FeeWpayRwPremiumVO rpVo, boolean isSave);

	public void insertAndUpdate() throws Exception;

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception;

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception;

	public void generate(String userId, String ouCode, Long year, Long period);

	public List findByCriteriaList(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo ,int count, int countRecord);
	
	public List findByCriteriaListM(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo);
	
	public Integer countDataList(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo);
	
	public List findByCriteriaListApprove(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, int count, int countRecord);
	public Integer countDataApprove(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo);
	
	public void addListApprove(FeeWpayRwPremiumVO rpVo, boolean isSave);
	
	public void updateApTableApprove(FeeWpayRwPremiumVO feewpayrwpremiumvo)
			throws Exception;
	
	
}