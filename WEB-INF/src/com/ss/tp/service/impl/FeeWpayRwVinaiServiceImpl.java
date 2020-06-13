package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;
import com.ss.tp.dao.FeeWpayRwVinaiDAO;
import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.RwVinaiVO;
import com.ss.tp.service.FeeWpayRwVinaiService;

public class FeeWpayRwVinaiServiceImpl implements FeeWpayRwVinaiService, Serializable {

	private FeeWpayRwVinaiDAO FeeWpayRwVinaiDAO;

	public FeeWpayRwVinaiDAO getFeeWpayRwVinaiDAO() {
		return FeeWpayRwVinaiDAO;
	}

	public void setFeeWpayRwVinaiDAO(FeeWpayRwVinaiDAO FeeWpayRwVinaiDAO) {
		this.FeeWpayRwVinaiDAO = FeeWpayRwVinaiDAO;
	}

	public void insertRwVinai(RwVinaiVO rwvinaivo) throws Exception {
		this.FeeWpayRwVinaiDAO.insertRwVinai(rwvinaivo);
	}

	public void updateRwVinai(RwVinaiVO rwvinaivo) throws Exception {
		this.FeeWpayRwVinaiDAO.updateRwVinai(rwvinaivo);
	}

	public void deleteRwVinai(RwVinaiVO rwvinaivo) throws Exception {
		this.FeeWpayRwVinaiDAO.deleteRwVinai(rwvinaivo);
	}

	public void insertRwVinais(List rwvinaivolist) throws Exception {
		this.FeeWpayRwVinaiDAO.insertRwVinais(rwvinaivolist);
	}

	public List findByCriteria(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo, int count, int countRecord) {
		return this.FeeWpayRwVinaiDAO.findByCriteria(userId, evaOuCode, new Long(
				evaYear), new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo, evaFlagFrom, evaFlagTo, count,
				countRecord);
	}

	public Integer countData(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo) {
		return this.FeeWpayRwVinaiDAO.countData(userId, evaOuCode, new Long(evaYear),
				new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo, evaFlagFrom, evaFlagTo);
	}

	public PayRollEmployeeVO findByEmpCodeDetail(String empCode, String ouCode,
			Long year, Long period) {
		return this.FeeWpayRwVinaiDAO.findByEmpCodeDetail(empCode, ouCode, year,
				period);
	}

	public List findByEmpCodeList(String userId, String ouCode, long yearPr,
			long periodPr, String empCode) {
		return this.FeeWpayRwVinaiDAO.findByEmpCodeList(userId, ouCode, new Long(
				yearPr), new Long(periodPr), empCode);
	}

	public void addList(RwVinaiVO rpVo, boolean isSave) {
		this.FeeWpayRwVinaiDAO.addList(rpVo);

		if (isSave) {
			try {
				this.FeeWpayRwVinaiDAO.insertAndUpdateRwVinais();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void insertAndUpdate() throws Exception {
		this.FeeWpayRwVinaiDAO.insertAndUpdateRwVinais();
	}

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.FeeWpayRwVinaiDAO.isConfirmFlag(ouCode, year, period, userId);
	}

	public List rwVinaiReport(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaFlag) {
		return this.FeeWpayRwVinaiDAO.rwVinaiReport(userId, evaOuCode, new Long(
				evaYear), new Long(evaPeriod), evaFlag);
	}

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.FeeWpayRwVinaiDAO.canDelete(ouCode, year, period, userId);
	}

	//@Override
	public List findByCriteriaListApprove(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo,
			int count, int countRecord) {
		return this.FeeWpayRwVinaiDAO.findByCriteriaListApprove(userId, evaOuCode, new Long(
				evaYear), new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo,count,countRecord);
	}

	//@Override
	public Integer countDataApprove(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo) {
		return this.FeeWpayRwVinaiDAO.countDataApprove(userId, evaOuCode, new Long(
				evaYear), new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo);
	}
	//@Override
	public void addListApprove(RwVinaiVO feewpayrwvinaivo, boolean isSave) {
		this.FeeWpayRwVinaiDAO.addListApprove(feewpayrwvinaivo);

		if (isSave) {
			try {
				this.FeeWpayRwVinaiDAO.insertAndUpdateApTablesApprove();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	//@Override
	public void updateApTableApprove(RwVinaiVO feewpayrwvinaivo)
			throws Exception {
		this.FeeWpayRwVinaiDAO.updateApTableApprove(feewpayrwvinaivo);
		
	}
}
