package com.ss.tp.dao;

import java.util.List;
import com.ss.tp.dto.FeeWpayPayRollEmployeeVO;
import com.ss.tp.dto.FeeWpayRwOvertimeVO;
import com.ss.tp.dto.FeeWpayRwPremiumVO;
import com.ss.tp.model.FeeWpayRwPremium;

public interface FeeWpayRwPremiumDAO {
	public void insertRwPremium(FeeWpayRwPremiumVO rwpremiumvo) throws Exception;

	public void updateRwPremium(FeeWpayRwPremiumVO rwpremiumvo) throws Exception;

	public void deleteRwPremium(FeeWpayRwPremiumVO rwpremiumvo) throws Exception;

	public void insertRwPremiums(List rwpremiumvolist) throws Exception;

	public List findByCriteria(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo, int count, int countRecord);

	public Integer countData(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo);

	public FeeWpayPayRollEmployeeVO findByEmpCodeDetail(String empCode, String ouCode,
			Long year, Long period);

	public List findByEmpCodeList(String userId, String ouCode, Long yearPr,
			Long periodPr, String empCode);

	public FeeWpayRwPremium loadRwPremium(FeeWpayRwPremiumVO rpVo);

	public List rwPremiumReportByOrg(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String evaFlag);
	
	public List rwPremiumReportByOrg(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String evaFlag,String evaAppFlag);

	public void addList(FeeWpayRwPremiumVO rwpremiumvo);

	public void insertAndUpdateRwPremiums() throws Exception;

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception;

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception;

	public void generate(String userId, String ouCode, Long year, Long period);
	
	public List findByCriteriaListM(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo);


	public List findByCriteriaList(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo, int count, int countRecord);
	
	public Integer countDataList(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo);
	
	public Integer countDataApprove(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo);
	
	public void addListApprove(FeeWpayRwPremiumVO rwpremiumvo);
	
	public void insertAndUpdateApTablesApprove() throws Exception;
	
	public List findByCriteriaListApprove(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, int count, int countRecord);
	
	public void updateApTableApprove(FeeWpayRwPremiumVO rwpremiumvo)
			throws Exception;
}