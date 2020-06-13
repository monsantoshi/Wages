package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.WgPrIncDecOtherDAO;
import com.ss.tp.dto.WgPrIncDecOtherVO;
import com.ss.tp.service.WgPrIncDecOtherService;

public class WgPrIncDecOtherServiceImpl implements WgPrIncDecOtherService,
		Serializable {

	private WgPrIncDecOtherDAO WgPrIncDecOtherDAO;

	public WgPrIncDecOtherDAO getWgPrIncDecOtherDAO() {
		return WgPrIncDecOtherDAO;
	}

	public void setWgPrIncDecOtherDAO(WgPrIncDecOtherDAO wgPrIncDecOtherDAO) {
		WgPrIncDecOtherDAO = wgPrIncDecOtherDAO;
	}

	public void insertWgPrIncDecOther(WgPrIncDecOtherVO rwincdecothervo)
			throws Exception {
		this.WgPrIncDecOtherDAO.insertWgPrIncDecOther(rwincdecothervo);
	}

	public void updateWgPrIncDecOther(WgPrIncDecOtherVO rwincdecothervo)
			throws Exception {
		this.WgPrIncDecOtherDAO.updateWgPrIncDecOther(rwincdecothervo);
	}

	public void deleteWgPrIncDecOther(WgPrIncDecOtherVO rwincdecothervo)
			throws Exception {
		this.WgPrIncDecOtherDAO.deleteWgPrIncDecOther(rwincdecothervo);
	}

	public void insertWgPrIncDecOthers(List rwincdecothervolist)
			throws Exception {
		this.WgPrIncDecOtherDAO.insertWgPrIncDecOthers(rwincdecothervolist);
	}

	public List findByCriteria(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String incDecCode,
			String groupCode) {
		return this.WgPrIncDecOtherDAO.findByCriteria(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), evaCodeSeqFrom,
				evaCodeSeqTo, evaEmpCodeFrom, evaEmpCodeTo, incDecCode,
				groupCode);
	}

	public void addList(WgPrIncDecOtherVO rpVo, boolean isSave) {
		this.WgPrIncDecOtherDAO.addList(rpVo);

		if (isSave) {
			try {
				this.WgPrIncDecOtherDAO.insertAndUpdateWgPrIncDecOthers();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void insertAndUpdate() throws Exception {
		this.WgPrIncDecOtherDAO.insertAndUpdateWgPrIncDecOthers();
	}

	public boolean isConfirmFlagIncome(String ouCode, String year,
			String period, String userId) throws Exception {
		return this.WgPrIncDecOtherDAO.isConfirmFlagIncome(ouCode, year,
				period, userId);
	}

	public boolean isConfirmFlagDeduct(String ouCode, String year,
			String period, String userId) throws Exception {
		return this.WgPrIncDecOtherDAO.isConfirmFlagDeduct(ouCode, year,
				period, userId);
	}

	public List incDecOtherReport(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String incDecCode, String evaFlag) {
		return this.WgPrIncDecOtherDAO.incDecOtherReport(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), incDecCode, evaFlag);
	}

	public List decOtherReport(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String incDecCode, String evaFlag) {
		return this.WgPrIncDecOtherDAO.decOtherReport(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), incDecCode, evaFlag);
	}
}
