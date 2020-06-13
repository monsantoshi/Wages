package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.RwPremiumDAO;
import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.RwPremiumVO;
import com.ss.tp.service.RwPremiumService;

public class RwPremiumServiceImpl implements RwPremiumService, Serializable {

	private RwPremiumDAO RwPremiumDAO;

	public RwPremiumDAO getRwPremiumDAO() {
		return RwPremiumDAO;
	}

	public void setRwPremiumDAO(RwPremiumDAO RwPremiumDAO) {
		this.RwPremiumDAO = RwPremiumDAO;
	}

	public void insertRwPremium(RwPremiumVO rwpremiumvo) throws Exception {
		this.RwPremiumDAO.insertRwPremium(rwpremiumvo);
	}

	public void updateRwPremium(RwPremiumVO rwpremiumvo) throws Exception {
		this.RwPremiumDAO.updateRwPremium(rwpremiumvo);
	}

	public void deleteRwPremium(RwPremiumVO rwpremiumvo) throws Exception {
		this.RwPremiumDAO.deleteRwPremium(rwpremiumvo);
	}

	public void insertRwPremiums(List rwpremiumvolist) throws Exception {
		this.RwPremiumDAO.insertRwPremiums(rwpremiumvolist);
	}

	public List findByCriteria(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo, int count, int countRecord) {
		return this.RwPremiumDAO.findByCriteria(userId, evaOuCode, new Long(
				evaYear), new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo, evaFlagFrom, evaFlagTo, count,
				countRecord);
	}

	public Integer countData(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo) {
		return this.RwPremiumDAO.countData(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), evaCodeSeqFrom,
				evaCodeSeqTo, evaEmpCodeFrom, evaEmpCodeTo, evaFlagFrom,
				evaFlagTo);
	}

	public PayRollEmployeeVO findByEmpCodeDetail(String empCode, String ouCode,
			Long year, Long period) {
		return this.RwPremiumDAO.findByEmpCodeDetail(empCode, ouCode, year,
				period);
	}

	public List findByEmpCodeList(String userId, String ouCode, long yearPr,
			long periodPr, String empCode) {
		return this.RwPremiumDAO.findByEmpCodeList(userId, ouCode, new Long(
				yearPr), new Long(periodPr), empCode);
	}

	public List rwPremiumReportByOrg(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaFlag) {
		return this.RwPremiumDAO.rwPremiumReportByOrg(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), evaFlag);
	}

	public void addList(RwPremiumVO rpVo, boolean isSave) {
		this.RwPremiumDAO.addList(rpVo);

		if (isSave) {
			try {
				this.RwPremiumDAO.insertAndUpdateRwPremiums();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void insertAndUpdate() throws Exception {
		this.RwPremiumDAO.insertAndUpdateRwPremiums();
	}

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.RwPremiumDAO.isConfirmFlag(ouCode, year, period, userId);
	}

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.RwPremiumDAO.canDelete(ouCode, year, period, userId);
	}

	public List findByCriteriaList(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo) {
		return this.RwPremiumDAO.findByCriteriaList(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), evaCodeSeqFrom,
				evaCodeSeqTo, evaEmpCodeFrom, evaEmpCodeTo);
	}

	public void generate(String userId, String ouCode, Long year, Long period) {
		this.RwPremiumDAO.generate(userId, ouCode, year, period);
	}

}
