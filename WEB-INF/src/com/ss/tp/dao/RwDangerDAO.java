package com.ss.tp.dao;

import java.util.List;
import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.RwDangerVO;

public interface RwDangerDAO {
	public void insertRwDanger(RwDangerVO rwdangervo) throws Exception;

	public void updateRwDanger(RwDangerVO rwdangervo) throws Exception;

	public void deleteRwDanger(RwDangerVO rwdangervo) throws Exception;

	public void insertRwDangers(List rwdangervolist) throws Exception;

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

	public void addList(RwDangerVO rwdangervo);

	public void insertAndUpdateRwDangers() throws Exception;

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception;

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception;

	// ==============Created by Kiet==============
	public List findReportByCriteria(String ouCode, String userId, Long yearPr,
			Long periodPR, String flagPR);

	public String findOrgName(String ouCode);
	// ===========================================
}