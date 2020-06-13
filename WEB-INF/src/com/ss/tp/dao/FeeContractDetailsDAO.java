package com.ss.tp.dao;

import java.util.List;
import com.ss.tp.dto.FeeWpayPayRollEmployeeVO;

import com.ss.tp.dto.FeeContractDetailsVO;
import com.ss.tp.model.FeeContractDetails;

public interface FeeContractDetailsDAO {
	public void insertFeeContractDetails(FeeContractDetailsVO feeContractDetailsvo) throws Exception;

	public void updateFeeContractDetails(FeeContractDetailsVO feeContractDetailsvo) throws Exception;

	public void deleteFeeContractDetails(FeeContractDetailsVO feeContractDetailsvo) throws Exception;

	public void insertFeeContractDetailss(List feeContractDetailsvolist) throws Exception;

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

	public FeeContractDetails loadFeeContractDetails(FeeContractDetailsVO rpVo);

	public List feeContractDetailsReportByOrg(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String evaFlag);

	public void addList(FeeContractDetailsVO feeContractDetailsvo);

	public void insertAndUpdateFeeContractDetails() throws Exception;

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception;

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception;

	public void generate(String userId, String ouCode, Long year, Long period);

	public List findByCriteriaList(String userId, String evaOuCode,String evaContractNo,
			String evaYear, String evaCodeSeq, int count, int countRecord);
	
	
	public Integer countDataApprove(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo);
	
	public void addListApprove(FeeContractDetailsVO feeContractDetailsvo);
	
	public void insertAndUpdateApTablesApprove() throws Exception;
	
	public List findByCriteriaListApprove(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo, int count, int countRecord);
	
	public void updateApTableApprove(FeeContractDetailsVO feeContractDetailsvo)
			throws Exception;
	
	
	public Integer countDataList(String userId, String evaOuCode,String evaContractNo,
			String evaYear, String evaCodeSeq);
	
}