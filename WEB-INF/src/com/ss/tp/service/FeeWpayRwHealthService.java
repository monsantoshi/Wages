package com.ss.tp.service;

import java.util.List;
import com.ss.tp.dto.FeeWpayPayRollEmployeeVO;
import com.ss.tp.dto.FeeWpayRwHealthVO;
import com.ss.tp.dto.RwIncDecOtherVO;

public interface FeeWpayRwHealthService {
	public void insertRwHealth(FeeWpayRwHealthVO rwhealthvo) throws Exception;

	public void updateRwHealth(FeeWpayRwHealthVO rwhealthvo) throws Exception;

	public void deleteRwHealth(FeeWpayRwHealthVO rwhealthvo) throws Exception;

	public void insertRwHealths(List rwhealthvolist) throws Exception;

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

	public List findHealthReport(String ouCode, Integer year, Integer period,
			String type, String userId);
	
	public List findHealthReport(String ouCode, Integer year, Integer period,
			String type, String userId,String approveFlag);
	public void addList(FeeWpayRwHealthVO rpVo, boolean isSave);

	public void insertAndUpdate() throws Exception;

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception;

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception;

	public List findByCriteriaList(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo ,int count, int countRecord);
	
	public Integer countDataList(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo);
	
	
	
	
	public List findByCriteriaListApprove(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, int count, int countRecord);
	public Integer countDataApprove(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo);
	
	public void addListApprove(FeeWpayRwHealthVO rpVo, boolean isSave);
	
	public void updateApTableApprove(FeeWpayRwHealthVO feewpayrwhealthvo)
			throws Exception;
	
	
}