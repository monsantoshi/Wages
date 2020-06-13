package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;
import com.ss.tp.dao.FeeWpayRwHealthDAO;
import com.ss.tp.dto.FeeWpayPayRollEmployeeVO;
import com.ss.tp.dto.FeeWpayRwHealthVO;
import com.ss.tp.service.FeeWpayRwHealthService;

public class FeeWpayRwHealthServiceImpl implements FeeWpayRwHealthService, Serializable {

	private FeeWpayRwHealthDAO FeeWpayRwHealthDAO;

	public FeeWpayRwHealthDAO getFeeWpayRwHealthDAO() {
		return FeeWpayRwHealthDAO;
	}

	public void setFeeWpayRwHealthDAO(FeeWpayRwHealthDAO FeeWpayRwHealthDAO) {
		this.FeeWpayRwHealthDAO = FeeWpayRwHealthDAO;
	}

	public void insertRwHealth(FeeWpayRwHealthVO rwhealthvo) throws Exception {
		this.FeeWpayRwHealthDAO.insertRwHealth(rwhealthvo);
	}

	public void updateRwHealth(FeeWpayRwHealthVO rwhealthvo) throws Exception {
		this.FeeWpayRwHealthDAO.updateRwHealth(rwhealthvo);
	}

	public void deleteRwHealth(FeeWpayRwHealthVO rwhealthvo) throws Exception {
		this.FeeWpayRwHealthDAO.deleteRwHealth(rwhealthvo);
	}

	public void insertRwHealths(List rwhealthvolist) throws Exception {
		this.FeeWpayRwHealthDAO.insertRwHealths(rwhealthvolist);
	}

	public List findByCriteria(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo, int count, int countRecord) {
		return this.FeeWpayRwHealthDAO.findByCriteria(userId, evaOuCode, new Long(
				evaYear), new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo, evaFlagFrom, evaFlagTo, count,
				countRecord);
	}

	public Integer countData(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo) {
		return this.FeeWpayRwHealthDAO.countData(userId, evaOuCode, new Long(evaYear),
				new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo, evaFlagFrom, evaFlagTo);
	}

	public FeeWpayPayRollEmployeeVO findByEmpCodeDetail(String empCode, String ouCode,
			Long year, Long period) {
		return this.FeeWpayRwHealthDAO.findByEmpCodeDetail(empCode, ouCode, year,
				period);
	}

	public List findByEmpCodeList(String userId, String ouCode, long yearPr,
			long periodPr, String empCode) {
		return this.FeeWpayRwHealthDAO.findByEmpCodeList(userId, ouCode, new Long(
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
		return this.getFeeWpayRwHealthDAO().findHealthReport(ouCode, year, period,
				type, userId);
	}

	
	public List findHealthReport(String ouCode, Integer year, Integer period,
			String type, String userId,String approveFlag) {
		return this.getFeeWpayRwHealthDAO().findHealthReport(ouCode, year, period,
				type, userId,approveFlag);
	}
	
	public void addList(FeeWpayRwHealthVO rpVo, boolean isSave) {
		this.FeeWpayRwHealthDAO.addList(rpVo);

		if (isSave) {
			try {
				this.FeeWpayRwHealthDAO.insertAndUpdateRwHealths();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void insertAndUpdate() throws Exception {
		this.FeeWpayRwHealthDAO.insertAndUpdateRwHealths();
	}

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.FeeWpayRwHealthDAO.isConfirmFlag(ouCode, year, period, userId);
	}

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.FeeWpayRwHealthDAO.canDelete(ouCode, year, period, userId);
	}

	public List findByCriteriaList(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo, int count, int countRecord) {
		return this.FeeWpayRwHealthDAO.findByCriteriaList(userId, evaOuCode, new Long(
				evaYear), new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo, count,countRecord);
	}
	public Integer countDataList(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo) {
		return this.FeeWpayRwHealthDAO.countDataList(userId, evaOuCode, new Long(
				evaYear), new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo);
	}

	
	public List findByCriteriaListApprove(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo, int count, int countRecord) {
		return this.FeeWpayRwHealthDAO.findByCriteriaListApprove(userId, evaOuCode, new Long(
				evaYear), new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo, count,countRecord);
	}

	//@Override
	public Integer countDataApprove(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo) {
		return this.FeeWpayRwHealthDAO.countDataApprove(userId, evaOuCode, new Long(
				evaYear), new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo);
	}

	//@Override
	public void addListApprove(FeeWpayRwHealthVO feewpayrwhealthvo, boolean isSave) {
		this.FeeWpayRwHealthDAO.addListApprove(feewpayrwhealthvo);

		if (isSave) {
			try {
				this.FeeWpayRwHealthDAO.insertAndUpdateApTablesApprove();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	//@Override
	public void updateApTableApprove(FeeWpayRwHealthVO feewpayrwhealthvo)
			throws Exception {
		this.FeeWpayRwHealthDAO.updateApTableApprove(feewpayrwhealthvo);
		
	}
}
