package com.ss.tp.dao;

import java.util.List;

import com.ss.tp.dto.FeeWpayRwPremiumVO;
import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.RwVinai2VO;

public interface FeeWpayRwVinai2DAO {
	public void insertRwVinai2(RwVinai2VO rwvinai2vo) throws Exception;

	public void updateRwVinai2(RwVinai2VO rwvinai2vo) throws Exception;

	public void deleteRwVinai2(RwVinai2VO rwvinai2vo) throws Exception;

	public void insertRwVinai2s(List rwvinai2volist) throws Exception;

	public List findByCriteria(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo, int count, int countRecord);

	public Integer countData(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo);

	public PayRollEmployeeVO findByEmpCodeDetail(String empCode, String ouCode,
			Long year, Long period);

	public List findByEmpCodeList(String userId, String ouCode, Long yearPr,
			Long periodPr, String empCode);

	public void addList(RwVinai2VO rwvinai2vo);

	public void insertAndUpdateRwVinai2s() throws Exception;

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception;

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception;
	
	
	
	public Integer countDataApprove(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo);
	
	public void addListApprove(RwVinai2VO rwvinai2vo);
	
	public void insertAndUpdateApTablesApprove() throws Exception;
	
	public List findByCriteriaListApprove(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo,int count, int countRecord);
	
	public void updateApTableApprove(RwVinai2VO rwvinai2vo)
			throws Exception;
	
	public List rwVinai2Report(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaFlag);
	
	
	
}