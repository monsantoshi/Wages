package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;
import com.ss.tp.dao.RwIncDecOtherDAO;
import com.ss.tp.dto.RwIncDecOtherVO;
import com.ss.tp.service.RwIncDecOtherService;

public class RwIncDecOtherServiceImpl implements RwIncDecOtherService,
		Serializable {

	private RwIncDecOtherDAO RwIncDecOtherDAO;

	public RwIncDecOtherDAO getRwIncDecOtherDAO() {
		return RwIncDecOtherDAO;
	}

	public void setRwIncDecOtherDAO(RwIncDecOtherDAO RwIncDecOtherDAO) {
		this.RwIncDecOtherDAO = RwIncDecOtherDAO;
	}

	public void insertRwIncDecOther(RwIncDecOtherVO rwincdecothervo)
			throws Exception {
		this.RwIncDecOtherDAO.insertRwIncDecOther(rwincdecothervo);
	}

	public void updateRwIncDecOther(RwIncDecOtherVO rwincdecothervo)
			throws Exception {
		this.RwIncDecOtherDAO.updateRwIncDecOther(rwincdecothervo);
	}

	public void deleteRwIncDecOther(RwIncDecOtherVO rwincdecothervo)
			throws Exception {
		this.RwIncDecOtherDAO.deleteRwIncDecOther(rwincdecothervo);
	}

	public void insertRwIncDecOthers(List rwincdecothervolist) throws Exception {
		this.RwIncDecOtherDAO.insertRwIncDecOthers(rwincdecothervolist);
	}

	public List findByCriteria(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String incDecCode,
			String groupCode) {
		return this.RwIncDecOtherDAO.findByCriteria(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), evaCodeSeqFrom,
				evaCodeSeqTo, evaEmpCodeFrom, evaEmpCodeTo, incDecCode,
				groupCode);
	}

	public void addList(RwIncDecOtherVO rpVo, boolean isSave) {
		this.RwIncDecOtherDAO.addList(rpVo);

		if (isSave) {
			try {
				this.RwIncDecOtherDAO.insertAndUpdateRwIncDecOthers();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void insertAndUpdate() throws Exception {
		this.RwIncDecOtherDAO.insertAndUpdateRwIncDecOthers();
	}

	public boolean isConfirmFlagIncome(String ouCode, String year,
			String period, String userId) throws Exception {
		return this.RwIncDecOtherDAO.isConfirmFlagIncome(ouCode, year, period,
				userId);
	}

	public boolean isConfirmFlagDeduct(String ouCode, String year,
			String period, String userId) throws Exception {
		return this.RwIncDecOtherDAO.isConfirmFlagDeduct(ouCode, year, period,
				userId);
	}

	public List incDecOtherReport(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String incDecCode, String evaFlag) {
		return this.RwIncDecOtherDAO.incDecOtherReport(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), incDecCode, evaFlag);
	}

	/**
	 * method ????????? query ?????????????????????Sheet
	 */
	public List incDecOtherReportCountSheet(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String incDecCode, String evaFlag) {
		return this.getRwIncDecOtherDAO().incDecOtherReportCountSheet(userId,
				evaOuCode, new Long(evaYear), new Long(evaPeriod), incDecCode,
				evaFlag);
	}

	public List decOtherReport(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String incDecCode, String evaFlag) {
		return this.RwIncDecOtherDAO.decOtherReport(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), incDecCode, evaFlag);
	}

	public List decOtherReportCountSheet(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String incDecCode, String evaFlag) {
		return this.getRwIncDecOtherDAO().decOtherReportCountSheet(userId,
				evaOuCode, new Long(evaYear), new Long(evaPeriod), incDecCode,
				evaFlag);
	}
	
	public List decOutAmtReport(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String incDecCode) {
		return this.RwIncDecOtherDAO.decOutAmtReport(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), incDecCode);
	}

	public List decOutAmtReportCountSheet(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String incDecCode) {
		return this.getRwIncDecOtherDAO().decOutAmtReportCountSheet(userId,
				evaOuCode, new Long(evaYear), new Long(evaPeriod), incDecCode);
	}

	public boolean canDeleteIncome(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.RwIncDecOtherDAO.canDeleteIncome(ouCode, year, period,
				userId);
	}

	public boolean canDeleteDeduct(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.RwIncDecOtherDAO.canDeleteDeduct(ouCode, year, period,
				userId);
	}

	public void generate(String userId, String ouCode, Long year, Long period) {
		this.RwIncDecOtherDAO.generate(userId, ouCode, year, period);
	}

	public void generate59(String userId, String ouCode, Long year, Long period) {
		this.RwIncDecOtherDAO.generate(userId, ouCode, year, period);
	}
}
