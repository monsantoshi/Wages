package com.ss.tp.dao;

import java.util.List;
import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.RwModSalVO;

public interface RwModSalDAO {
	public void insertRwModSal(RwModSalVO rwmodsalvo) throws Exception;

	public void updateRwModSal(RwModSalVO rwmodsalvo) throws Exception;

	public void deleteRwModSal(RwModSalVO rwmodsalvo) throws Exception;

	public void insertRwModSals(List rwmodsalvolist) throws Exception;

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

	public void addList(RwModSalVO rwmodsalvo);

	public void insertAndUpdateRwModSals() throws Exception;

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception;

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception;

	public List findSalaryUpdateRpt(String ouCode, String userId, Integer year,
			Integer period, String flag) throws Exception;
}