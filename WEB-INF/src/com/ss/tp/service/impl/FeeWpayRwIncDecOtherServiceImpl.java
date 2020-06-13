package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;
import com.ss.tp.dao.FeeWpayRwIncDecOtherDAO;
import com.ss.tp.dto.ApTableVO;
import com.ss.tp.dto.RwIncDecOtherVO;
import com.ss.tp.service.FeeWpayRwIncDecOtherService;

public class FeeWpayRwIncDecOtherServiceImpl implements FeeWpayRwIncDecOtherService,
		Serializable {

	private FeeWpayRwIncDecOtherDAO FeeWpayRwIncDecOtherDAO;

	public FeeWpayRwIncDecOtherDAO getFeeWpayRwIncDecOtherDAO() {
		return FeeWpayRwIncDecOtherDAO;
	}

	public void setFeeWpayRwIncDecOtherDAO(FeeWpayRwIncDecOtherDAO FeeWpayRwIncDecOtherDAO) {
		this.FeeWpayRwIncDecOtherDAO = FeeWpayRwIncDecOtherDAO;
	}

	public void insertRwIncDecOther(RwIncDecOtherVO rwincdecothervo)
			throws Exception {
		this.FeeWpayRwIncDecOtherDAO.insertRwIncDecOther(rwincdecothervo);
	}

	public void updateRwIncDecOther(RwIncDecOtherVO rwincdecothervo)
			throws Exception {
		this.FeeWpayRwIncDecOtherDAO.updateRwIncDecOther(rwincdecothervo);
	}

	public void deleteRwIncDecOther(RwIncDecOtherVO rwincdecothervo)
			throws Exception {
		this.FeeWpayRwIncDecOtherDAO.deleteRwIncDecOther(rwincdecothervo);
	}

	public void insertRwIncDecOthers(List rwincdecothervolist) throws Exception {
		this.FeeWpayRwIncDecOtherDAO.insertRwIncDecOthers(rwincdecothervolist);
	}

	public List findByCriteria(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String incDecCode,
			String groupCode) {
		return this.FeeWpayRwIncDecOtherDAO.findByCriteria(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), evaCodeSeqFrom,
				evaCodeSeqTo, evaEmpCodeFrom, evaEmpCodeTo, incDecCode,
				groupCode);
	}

	public void addList(RwIncDecOtherVO rpVo, boolean isSave) {
		this.FeeWpayRwIncDecOtherDAO.addList(rpVo);

		if (isSave) {
			try {
				this.FeeWpayRwIncDecOtherDAO.insertAndUpdateRwIncDecOthers();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void insertAndUpdate() throws Exception {
		this.FeeWpayRwIncDecOtherDAO.insertAndUpdateRwIncDecOthers();
	}

	public boolean isConfirmFlagIncome(String ouCode, String year,
			String period, String userId) throws Exception {
		return this.FeeWpayRwIncDecOtherDAO.isConfirmFlagIncome(ouCode, year, period,
				userId);
	}

	public boolean isConfirmFlagDeduct(String ouCode, String year,
			String period, String userId) throws Exception {
		return this.FeeWpayRwIncDecOtherDAO.isConfirmFlagDeduct(ouCode, year, period,
				userId);
	}

	public List incDecOtherReport(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String incDecCode, String evaFlag) {
		return this.FeeWpayRwIncDecOtherDAO.incDecOtherReport(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), incDecCode, evaFlag);
	}

	
	public List incDecOtherReport(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String incDecCode, String evaFlag,String approveFlag) {
		return this.FeeWpayRwIncDecOtherDAO.incDecOtherReport(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), incDecCode, evaFlag,approveFlag);
	}
	/**
	 * method ????????? query ?????????????????????Sheet
	 */
	public List incDecOtherReportCountSheet(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String incDecCode, String evaFlag) {
		return this.getFeeWpayRwIncDecOtherDAO().incDecOtherReportCountSheet(userId,
				evaOuCode, new Long(evaYear), new Long(evaPeriod), incDecCode,
				evaFlag);
	}

	public List incDecOtherReportCountSheet(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String incDecCode, String evaFlag,String approveFlag) {
		return this.getFeeWpayRwIncDecOtherDAO().incDecOtherReportCountSheet(userId,
				evaOuCode, new Long(evaYear), new Long(evaPeriod), incDecCode,
				evaFlag,approveFlag);
	}

	public List decOtherReport(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String incDecCode, String evaFlag) {
		return this.FeeWpayRwIncDecOtherDAO.decOtherReport(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), incDecCode, evaFlag);
	}

	public List decOtherReportCountSheet(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String incDecCode, String evaFlag) {
		return this.getFeeWpayRwIncDecOtherDAO().decOtherReportCountSheet(userId,
				evaOuCode, new Long(evaYear), new Long(evaPeriod), incDecCode,
				evaFlag);
	}
	
	
	public List decOtherReport(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String incDecCode, String evaFlag,String approveFlag) {
		return this.FeeWpayRwIncDecOtherDAO.decOtherReport(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), incDecCode, evaFlag,approveFlag);
	}

	public List decOtherReportCountSheet(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String incDecCode, String evaFlag,String approveFlag) {
		return this.getFeeWpayRwIncDecOtherDAO().decOtherReportCountSheet(userId,
				evaOuCode, new Long(evaYear), new Long(evaPeriod), incDecCode,
				evaFlag,approveFlag);
	}
	
	public List decOutAmtReport(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String incDecCode) {
		return this.FeeWpayRwIncDecOtherDAO.decOutAmtReport(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), incDecCode);
	}

	public List decOutAmtReportCountSheet(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String incDecCode) {
		return this.getFeeWpayRwIncDecOtherDAO().decOutAmtReportCountSheet(userId,
				evaOuCode, new Long(evaYear), new Long(evaPeriod), incDecCode);
	}

	public boolean canDeleteIncome(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.FeeWpayRwIncDecOtherDAO.canDeleteIncome(ouCode, year, period,
				userId);
	}

	public boolean canDeleteDeduct(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.FeeWpayRwIncDecOtherDAO.canDeleteDeduct(ouCode, year, period,
				userId);
	}

	public void generate(String userId, String ouCode, Long year, Long period) {
		this.FeeWpayRwIncDecOtherDAO.generate(userId, ouCode, year, period);
	}

	public void generate59(String userId, String ouCode, Long year, Long period) {
		this.FeeWpayRwIncDecOtherDAO.generate(userId, ouCode, year, period);
	}
	
	public void addListApprove(RwIncDecOtherVO rpVo, boolean isSave) {
		this.FeeWpayRwIncDecOtherDAO.addListApprove(rpVo);

		if (isSave) {
			try {
				this.FeeWpayRwIncDecOtherDAO.insertAndUpdateApTablesApprove();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public List findByCriteriaListApprove(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String incDecCode,
			String groupCode,int count,int countRecord) {
		return this.FeeWpayRwIncDecOtherDAO.findByCriteriaListApprove(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), evaCodeSeqFrom,
				evaCodeSeqTo, evaEmpCodeFrom, evaEmpCodeTo, incDecCode,
				groupCode,count,countRecord);
	}
	
	public Integer countDataApprove(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String incDecCode,
			String groupCode) {
		return this.FeeWpayRwIncDecOtherDAO.countDataApprove(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), evaCodeSeqFrom,
				evaCodeSeqTo, evaEmpCodeFrom, evaEmpCodeTo, incDecCode,
				groupCode);
	}
	
	public List findByCriteriaList(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String incDecCode,
			String groupCode,int count,int countRecord) {
		return this.FeeWpayRwIncDecOtherDAO.findByCriteriaList(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), evaCodeSeqFrom,
				evaCodeSeqTo, evaEmpCodeFrom, evaEmpCodeTo, incDecCode,
				groupCode,count,countRecord);
	}
	
	public Integer countDataList(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String incDecCode,
			String groupCode) {
		return this.FeeWpayRwIncDecOtherDAO.countDataList(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), evaCodeSeqFrom,
				evaCodeSeqTo, evaEmpCodeFrom, evaEmpCodeTo, incDecCode,
				groupCode);
	}


	
	public void updateApTableApprove(RwIncDecOtherVO rwincdecothervo)
			throws Exception {
		this.FeeWpayRwIncDecOtherDAO.updateApTableApprove(rwincdecothervo);
		
	}
	
	public List incOutAmtReport(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String incDecCode ,String evaFlag) {
		return this.FeeWpayRwIncDecOtherDAO.incOutAmtReport(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), incDecCode ,evaFlag);
	}

	public List incOutAmtReportCountSheet(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String incDecCode ,String evaFlag) {
		return this.getFeeWpayRwIncDecOtherDAO().incOutAmtReportCountSheet(userId,
				evaOuCode, new Long(evaYear), new Long(evaPeriod), incDecCode , evaFlag);
	}

	
	
}
