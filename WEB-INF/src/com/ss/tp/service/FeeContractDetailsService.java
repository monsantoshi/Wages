package com.ss.tp.service;

import java.util.List;
import com.ss.tp.dto.FeeWpayPayRollEmployeeVO;

import com.ss.tp.dto.FeeContractDetailsVO;

public interface FeeContractDetailsService {
	public void insertFeeContractDetails(FeeContractDetailsVO feeContractDetailsvo) throws Exception;

	public void updateFeeContractDetails(FeeContractDetailsVO feeContractDetailsvo) throws Exception;

	public void deleteFeeContractDetails(FeeContractDetailsVO feeContractDetailsvo) throws Exception;

	public void insertFeeContractDetailss(List feeContractDetailsvolist) throws Exception;

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

	public List feeContractDetailsReportByOrg(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaFlag);

	public void addList(FeeContractDetailsVO rpVo, boolean isSave);

	public void insertAndUpdate() throws Exception;

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception;

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception;

	public void generate(String userId, String ouCode, Long year, Long period);

	public List findByCriteriaList(String userId, String evaOuCode,String evaContractNo,
			String evaYear, String evaCodeSeq, int count, int countRecord);
	
	public List findByCriteriaListApprove(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo, int count, int countRecord);
	public Integer countDataApprove(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo);
	
	public void addListApprove(FeeContractDetailsVO rpVo, boolean isSave);
	
	public void updateApTableApprove(FeeContractDetailsVO feeContractDetailsvo)
			throws Exception;
	
	
	public Integer countDataList(String userId, String evaOuCode,String evaContractNo,
			String evaYear, String evaCodeSeq);

	
}