package com.ss.tp.service;

import java.util.List;
import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.RwModSalVO;

public interface RwModSalService {
	public void insertRwModSal(RwModSalVO rwmodsalvo) throws Exception;

	public void updateRwModSal(RwModSalVO rwmodsalvo) throws Exception;

	public void deleteRwModSal(RwModSalVO rwmodsalvo) throws Exception;

	public void insertRwModSals(List rwmodsalvolist) throws Exception;

	public List findByCriteria(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo, int count, int countRecord);

	public Integer countData(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo);

	public PayRollEmployeeVO findByEmpCodeDetail(String empCode, String ouCode,
			Long year, Long period);

	public List findByEmpCodeList(String userId, String ouCode, long yearPr,
			long periodPr, String empCode);

	public void addList(RwModSalVO rpVo, boolean isSave);

	public void insertAndUpdate() throws Exception;

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception;

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception;

	public List findSalaryUpdateRpt(String ouCode, String userId, int year,
			int period, String flag) throws Exception;
}