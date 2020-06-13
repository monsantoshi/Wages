package com.ss.tp.service;

import java.util.List;

import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.RwDangerVO;

public interface RwDangerService {
	public void insertRwDanger(RwDangerVO rwdangervo) throws Exception;

	public void updateRwDanger(RwDangerVO rwdangervo) throws Exception;

	public void deleteRwDanger(RwDangerVO rwdangervo) throws Exception;

	public void insertRwDangers(List rwdangervolist) throws Exception;

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

	public void addList(RwDangerVO rpVo, boolean isSave);

	public void insertAndUpdate() throws Exception;

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception;

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception;

	// =================Create By Kiet=======================
	public List findReportByCriteria(String ouCode, String userId, Long yearPr,
			Long periodPr, String flagPr);

	public String findOrgName(String ouCode);
	// ======================================================
}