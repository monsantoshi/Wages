package com.ss.tp.dao;

import java.util.List;
import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.RwVinai2VO;
import com.ss.tp.dto.RwVinaiVO;

public interface FeeWpayRwVinaiDAO {
	public void insertRwVinai(RwVinaiVO rwvinaivo) throws Exception;

	public void updateRwVinai(RwVinaiVO rwvinaivo) throws Exception;

	public void deleteRwVinai(RwVinaiVO rwvinaivo) throws Exception;

	public void insertRwVinais(List rwvinaivolist) throws Exception;

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

	public void addList(RwVinaiVO rwvinaivo);

	public void insertAndUpdateRwVinais() throws Exception;

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception;

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception;

	public List rwVinaiReport(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaFlag);
	
	
	public Integer countDataApprove(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo);

	
	public void addListApprove(RwVinaiVO rwvinaivo);
	
	public void insertAndUpdateApTablesApprove() throws Exception;
	
	public List findByCriteriaListApprove(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, int count, int countRecord);
	
	public void updateApTableApprove(RwVinaiVO rwvinaivo)
			throws Exception;
}