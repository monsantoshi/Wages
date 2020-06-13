package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.WgIncomeDeductDAO;
import com.ss.tp.dto.WgPrIncDecOtherVO;
import com.ss.tp.service.WgIncomeDeductService;

public class WgIncomeDeductServiceImpl implements WgIncomeDeductService,
		Serializable {

	private WgIncomeDeductDAO wgIncomeDeductDAO;

	public WgIncomeDeductDAO getWgIncomeDeductDAO() {
		return wgIncomeDeductDAO;
	}

	public void setWgIncomeDeductDAO(WgIncomeDeductDAO wgIncomeDeductDAO) {
		this.wgIncomeDeductDAO = wgIncomeDeductDAO;
	}

	public void insertWgIncomeDeduct(WgPrIncDecOtherVO wgincomevo)
			throws Exception {
		this.wgIncomeDeductDAO.insertWgIncomeDeduct(wgincomevo);

	}

	public void updateWgIncomeDeduct(WgPrIncDecOtherVO wgincomevo)
			throws Exception {
		this.wgIncomeDeductDAO.updateWgIncomeDeduct(wgincomevo);

	}

	public void deleteWgIncomeDeduct(WgPrIncDecOtherVO wgincomevo)
			throws Exception {
		this.wgIncomeDeductDAO.deleteWgIncomeDeduct(wgincomevo);

	}

	public void insertWgIncomeDeducts(List wgincomevolist) throws Exception {
		this.wgIncomeDeductDAO.insertWgIncomeDeducts(wgincomevolist);

	}

	public List findIncDecCode(String ouCode, String groupCode)
			throws Exception {
		return this.wgIncomeDeductDAO.findIncDecCode(ouCode, groupCode);
	}

	public String getIncDecName(String ouCode, int groupCode, String incDecCode) {
		return this.wgIncomeDeductDAO.getIncDecName(ouCode, groupCode,
				incDecCode);
	}

	public List incDecOtherReport(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String incDecCode, String evaFlag) {
		return this.wgIncomeDeductDAO.incDecOtherReport(userId, evaOuCode,
				evaYear, evaPeriod, incDecCode, evaFlag);
	}

	public List incDecOtherReportCountSheet(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String incDecCode, String evaFlag) {
		return this.wgIncomeDeductDAO.incDecOtherReportCountSheet(userId,
				evaOuCode, evaYear, evaPeriod, incDecCode, evaFlag);
	}

	public List decDecOtherReport(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String incDecCode, String evaFlag) {
		return this.wgIncomeDeductDAO.decDecOtherReport(userId, evaOuCode,
				evaYear, evaPeriod, incDecCode, evaFlag);
	}

	public List decDecOtherReportCountSheet(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String incDecCode, String evaFlag) {
		return this.wgIncomeDeductDAO.decDecOtherReportCountSheet(userId,
				evaOuCode, evaYear, evaPeriod, incDecCode, evaFlag);
	}
}
