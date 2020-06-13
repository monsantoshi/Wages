package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;
import com.ss.tp.dao.RwDangerDAO;
import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.RwDangerVO;
import com.ss.tp.service.RwDangerService;

public class RwDangerServiceImpl implements RwDangerService, Serializable {

	private RwDangerDAO RwDangerDAO;

	public RwDangerDAO getRwDangerDAO() {
		return RwDangerDAO;
	}

	public void setRwDangerDAO(RwDangerDAO RwDangerDAO) {
		this.RwDangerDAO = RwDangerDAO;
	}

	public void insertRwDanger(RwDangerVO rwdangervo) throws Exception {
		this.RwDangerDAO.insertRwDanger(rwdangervo);
	}

	public void updateRwDanger(RwDangerVO rwdangervo) throws Exception {
		this.RwDangerDAO.updateRwDanger(rwdangervo);
	}

	public void deleteRwDanger(RwDangerVO rwdangervo) throws Exception {
		this.RwDangerDAO.deleteRwDanger(rwdangervo);
	}

	public void insertRwDangers(List rwdangervolist) throws Exception {
		this.RwDangerDAO.insertRwDangers(rwdangervolist);
	}

	public List findByCriteria(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo, int count, int countRecord) {
		return this.RwDangerDAO.findByCriteria(userId, evaOuCode, new Long(
				evaYear), new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo, evaFlagFrom, evaFlagTo, count,
				countRecord);
	}

	public Integer countData(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo) {
		return this.RwDangerDAO.countData(userId, evaOuCode, new Long(evaYear),
				new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo, evaFlagFrom, evaFlagTo);
	}

	public PayRollEmployeeVO findByEmpCodeDetail(String empCode, String ouCode,
			Long year, Long period) {
		return this.RwDangerDAO.findByEmpCodeDetail(empCode, ouCode, year,
				period);
	}

	public List findByEmpCodeList(String userId, String ouCode, long yearPr,
			long periodPr, String empCode) {
		return this.RwDangerDAO.findByEmpCodeList(userId, ouCode, new Long(
				yearPr), new Long(periodPr), empCode);
	}

	public void addList(RwDangerVO rpVo, boolean isSave) {
		this.RwDangerDAO.addList(rpVo);

		if (isSave) {
			try {
				this.RwDangerDAO.insertAndUpdateRwDangers();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void insertAndUpdate() throws Exception {
		this.RwDangerDAO.insertAndUpdateRwDangers();
	}

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.RwDangerDAO.isConfirmFlag(ouCode, year, period, userId);
	}

	// ==============Created by Kiet==============
	public List findReportByCriteria(String ouCode, String userId, Long yearPr,
			Long periodPr, String flagPr) {
		return this.RwDangerDAO.findReportByCriteria(ouCode, userId, yearPr,
				periodPr, flagPr);
	}

	public String findOrgName(String ouCode) {
		return this.RwDangerDAO.findOrgName(ouCode);
	}

	// ===============================================

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.RwDangerDAO.canDelete(ouCode, year, period, userId);
	}

}
