package com.ss.tp.service.impl;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.ss.tp.dao.WgPrOvertimeDAO;
import com.ss.tp.dto.GwIncdecEmployeeVO;
import com.ss.tp.dto.WgPrOvertimeVO;

import com.ss.tp.model.WgPrOvertime;
import com.ss.tp.service.WgPrOvertimeService;

public class WgPrOvertimeServiceImpl implements WgPrOvertimeService,
		Serializable {

	private WgPrOvertimeDAO wgPrOvertimeDAO;

	public WgPrOvertimeDAO getWgPrOvertimeDAO() {
		return wgPrOvertimeDAO;
	}

	public void setWgPrOvertimeDAO(WgPrOvertimeDAO wgPrOvertimeDAO) {
		this.wgPrOvertimeDAO = wgPrOvertimeDAO;
	}

	public void insertWgPrOvertime(WgPrOvertimeVO rwovertimevo)
			throws Exception {
		this.wgPrOvertimeDAO.insertWgPrOvertime(rwovertimevo);
	}

	public void updateWgPrOvertime(WgPrOvertimeVO rwovertimevo)
			throws Exception {
		this.wgPrOvertimeDAO.updateWgPrOvertime(rwovertimevo);
	}

	public void deleteWgPrOvertime(WgPrOvertimeVO rwovertimevo)
			throws Exception {
		this.wgPrOvertimeDAO.deleteWgPrOvertime(rwovertimevo);
	}

	public void insertWgPrOvertimes(List rwovertimevolist) throws Exception {
		this.wgPrOvertimeDAO.insertWgPrOvertimes(rwovertimevolist);
	}

	public List findOverTimeByCriteria(String userId, String orgCodeFrom,
			String orgCodeTo, String empCodeFrom, String empCodeTo,
			String otTypeFrom, String flagPrFrom, String flagPrTo,
			String refNoFrom, String refNoTo, String ouCode, Long year,
			Long period, int pageRecord, int totalRecord) {
		return this.wgPrOvertimeDAO.findOverTimeByCriteria(userId, orgCodeFrom,
				orgCodeTo, empCodeFrom, empCodeTo, otTypeFrom, flagPrFrom,
				flagPrTo, refNoFrom, refNoTo, ouCode, year, period, pageRecord,
				totalRecord);
	}

	public Integer findCountOverTimeByCriteria(String userId,
			String orgCodeFrom, String orgCodeTo, String empCodeFrom,
			String empCodeTo, String otTypeFrom, String flagPrFrom,
			String flagPrTo, String refNoFrom, String refNoTo, String ouCode,
			Long year, Long period) {
		return this.wgPrOvertimeDAO.findCountOverTimeByCriteria(userId,
				orgCodeFrom, orgCodeTo, empCodeFrom, empCodeTo, otTypeFrom,
				flagPrFrom, flagPrTo, refNoFrom, refNoTo, ouCode, year, period);
	}

	public GwIncdecEmployeeVO findByEmpCodeDetail(String empCode,
			String ouCode, Long year, Long period) {
		return this.wgPrOvertimeDAO.findByEmpCodeDetail(empCode, ouCode, year,
				period);
	}

	public List findByEmpCodeList(String userId, String ouCode, long yearPr,
			long periodPr, String empCode, String refNo, String otType)
			throws UnsupportedEncodingException {
		return this.wgPrOvertimeDAO.findByEmpCodeList(userId, ouCode, new Long(
				yearPr), new Long(periodPr), empCode, refNo, otType);
	}

	public WgPrOvertime loadData(WgPrOvertimeVO rpVo) {
		return this.wgPrOvertimeDAO.loadWgPrOvertime(rpVo);
	}

	public void addList(WgPrOvertimeVO rpVo, boolean isSave) {

		this.wgPrOvertimeDAO.addList(rpVo);

		if (isSave) {
			try {
				this.wgPrOvertimeDAO.insertAndUpdateWgPrOvertimes();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void insertAndUpdate() throws Exception {
		this.wgPrOvertimeDAO.insertAndUpdateWgPrOvertimes();
	}

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.wgPrOvertimeDAO.isConfirmFlag(ouCode, year, period, userId);
	}

	public List findWgPrOvertimeReport(String ouCode, String userId, int year,
			int period, String otType, String otStatus) {
		return this.wgPrOvertimeDAO.findWgPrOvertimeReport(ouCode, userId,
				year, period, otType, otStatus);
	}
}
