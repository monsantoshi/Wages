package com.ss.tp.service;

import java.util.List;
import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.RwHealthVO;

public interface RwHealthService {
	public void insertRwHealth(RwHealthVO rwhealthvo) throws Exception;

	public void updateRwHealth(RwHealthVO rwhealthvo) throws Exception;

	public void deleteRwHealth(RwHealthVO rwhealthvo) throws Exception;

	public void insertRwHealths(List rwhealthvolist) throws Exception;

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

	public List findHealthReport(String ouCode, Integer year, Integer period,
			String type, String userId);

	public void addList(RwHealthVO rpVo, boolean isSave);

	public void insertAndUpdate() throws Exception;

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception;

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception;

	public List findByCriteriaList(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo);
}