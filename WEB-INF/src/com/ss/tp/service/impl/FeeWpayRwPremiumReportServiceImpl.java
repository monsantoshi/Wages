package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.FeeWpayRwPremiumReportDAO;
import com.ss.tp.dto.PayRollEmployeeReportVO;
import com.ss.tp.dto.RwPremiumReportVO;
import com.ss.tp.service.FeeWpayRwPremiumReportService;

public class FeeWpayRwPremiumReportServiceImpl implements FeeWpayRwPremiumReportService,
		Serializable {

	private FeeWpayRwPremiumReportDAO rwPremiumReportDAO;

	public FeeWpayRwPremiumReportDAO getFeeWpayRwPremiumReportDAO() {
		return rwPremiumReportDAO;
	}

	public void setFeeWpayRwPremiumReportDAO(FeeWpayRwPremiumReportDAO rwPremiumReportDAO) {
		this.rwPremiumReportDAO = rwPremiumReportDAO;
	}

	public void insertRwPremium(RwPremiumReportVO rwpremiumreportvo)
			throws Exception {
		this.rwPremiumReportDAO.insertRwPremium(rwpremiumreportvo);
	}

	public void updateRwPremium(RwPremiumReportVO rwpremiumreportvo)
			throws Exception {
		this.rwPremiumReportDAO.updateRwPremium(rwpremiumreportvo);
	}

	public void deleteRwPremium(RwPremiumReportVO rwpremiumreportvo)
			throws Exception {
		this.rwPremiumReportDAO.deleteRwPremium(rwpremiumreportvo);
	}

	public void insertRwPremiums(List rwpremiumvolist) throws Exception {
		this.rwPremiumReportDAO.insertRwPremiums(rwpremiumvolist);
	}

	public List findByCriteria(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo, int count, int countRecord) {
		return this.rwPremiumReportDAO.findByCriteria(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), evaCodeSeqFrom,
				evaCodeSeqTo, evaEmpCodeFrom, evaEmpCodeTo, evaFlagFrom,
				evaFlagTo, count, countRecord);
	}

	public Integer countData(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo) {
		return this.rwPremiumReportDAO.countData(userId, evaOuCode, new Long(
				evaYear), new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo, evaFlagFrom, evaFlagTo);
	}

	public PayRollEmployeeReportVO findByEmpCodeDetail(String empCode,
			String ouCode, Long year, Long period) {
		return this.rwPremiumReportDAO.findByEmpCodeDetail(empCode, ouCode,
				year, period);
	}

	public List findByEmpCodeList(String userId, String ouCode, long yearPr,
			long periodPr, String empCode) {
		return this.rwPremiumReportDAO.findByEmpCodeList(userId, ouCode,
				new Long(yearPr), new Long(periodPr), empCode);
	}

	public List rwPremiumReportByOrgReport(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaFlag) {
		return this.rwPremiumReportDAO.rwPremiumReportByOrgReport(userId,
				evaOuCode, new Long(evaYear), new Long(evaPeriod), evaFlag);
	}

	public void addList(RwPremiumReportVO rpVo, boolean isSave) {
		this.rwPremiumReportDAO.addList(rpVo);

		if (isSave) {
			try {
				this.rwPremiumReportDAO.insertAndUpdateRwPremiums();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void insertAndUpdate() throws Exception {
		this.rwPremiumReportDAO.insertAndUpdateRwPremiums();
	}

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.rwPremiumReportDAO.isConfirmFlag(ouCode, year, period,
				userId);
	}

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.rwPremiumReportDAO.canDelete(ouCode, year, period, userId);
	}

}
