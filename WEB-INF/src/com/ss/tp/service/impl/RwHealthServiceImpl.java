package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;
import com.ss.tp.dao.RwHealthDAO;
import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.RwHealthVO;
import com.ss.tp.service.RwHealthService;

public class RwHealthServiceImpl implements RwHealthService, Serializable {

	private RwHealthDAO RwHealthDAO;

	public RwHealthDAO getRwHealthDAO() {
		return RwHealthDAO;
	}

	public void setRwHealthDAO(RwHealthDAO RwHealthDAO) {
		this.RwHealthDAO = RwHealthDAO;
	}

	public void insertRwHealth(RwHealthVO rwhealthvo) throws Exception {
		this.RwHealthDAO.insertRwHealth(rwhealthvo);
	}

	public void updateRwHealth(RwHealthVO rwhealthvo) throws Exception {
		this.RwHealthDAO.updateRwHealth(rwhealthvo);
	}

	public void deleteRwHealth(RwHealthVO rwhealthvo) throws Exception {
		this.RwHealthDAO.deleteRwHealth(rwhealthvo);
	}

	public void insertRwHealths(List rwhealthvolist) throws Exception {
		this.RwHealthDAO.insertRwHealths(rwhealthvolist);
	}

	public List findByCriteria(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo, int count, int countRecord) {
		return this.RwHealthDAO.findByCriteria(userId, evaOuCode, new Long(
				evaYear), new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo, evaFlagFrom, evaFlagTo, count,
				countRecord);
	}

	public Integer countData(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo) {
		return this.RwHealthDAO.countData(userId, evaOuCode, new Long(evaYear),
				new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo, evaFlagFrom, evaFlagTo);
	}

	public PayRollEmployeeVO findByEmpCodeDetail(String empCode, String ouCode,
			Long year, Long period) {
		return this.RwHealthDAO.findByEmpCodeDetail(empCode, ouCode, year,
				period);
	}

	public List findByEmpCodeList(String userId, String ouCode, long yearPr,
			long periodPr, String empCode) {
		return this.RwHealthDAO.findByEmpCodeList(userId, ouCode, new Long(
				yearPr), new Long(periodPr), empCode);
	}

	/**
	 * method ����Ѻ Query �����Ũҡ Table RW_HEALTH �����ʴ���§ҹ�Թ���ا�آ�Ҿ
	 * 
	 * @param ouCode
	 * @param year
	 * @param period
	 * @param type
	 * @return
	 */
	public List findHealthReport(String ouCode, Integer year, Integer period,
			String type, String userId) {
		return this.getRwHealthDAO().findHealthReport(ouCode, year, period,
				type, userId);
	}

	public void addList(RwHealthVO rpVo, boolean isSave) {
		this.RwHealthDAO.addList(rpVo);

		if (isSave) {
			try {
				this.RwHealthDAO.insertAndUpdateRwHealths();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void insertAndUpdate() throws Exception {
		this.RwHealthDAO.insertAndUpdateRwHealths();
	}

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.RwHealthDAO.isConfirmFlag(ouCode, year, period, userId);
	}

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.RwHealthDAO.canDelete(ouCode, year, period, userId);
	}

	public List findByCriteriaList(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo) {
		return this.RwHealthDAO.findByCriteriaList(userId, evaOuCode, new Long(
				evaYear), new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo);
	}
}
