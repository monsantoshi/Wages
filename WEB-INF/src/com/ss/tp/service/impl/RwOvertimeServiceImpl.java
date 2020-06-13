package com.ss.tp.service.impl;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.ss.tp.dao.RwOvertimeDAO; //import com.ss.tp.dao.RwPremiumDAO;
import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.RwOvertimeVO; //import com.ss.tp.dto.RwPremiumVO;
import com.ss.tp.model.RwOvertime;
import com.ss.tp.service.RwOvertimeService;

//import com.ss.tp.service.RwPremiumService;

public class RwOvertimeServiceImpl implements RwOvertimeService, Serializable {

	private RwOvertimeDAO rwOvertimeDAO;

	public RwOvertimeDAO getRwOvertimeDAO() {
		return rwOvertimeDAO;
	}

	public void setRwOvertimeDAO(RwOvertimeDAO rwOvertimeDAO) {
		this.rwOvertimeDAO = rwOvertimeDAO;
	}

	public void insertRwOvertime(RwOvertimeVO rwovertimevo) throws Exception {
		this.rwOvertimeDAO.insertRwOvertime(rwovertimevo);
	}

	public void updateRwOvertime(RwOvertimeVO rwovertimevo) throws Exception {
		this.rwOvertimeDAO.updateRwOvertime(rwovertimevo);
	}

	public void deleteRwOvertime(RwOvertimeVO rwovertimevo) throws Exception {
		this.rwOvertimeDAO.deleteRwOvertime(rwovertimevo);
	}

	public void insertRwOvertimes(List rwovertimevolist) throws Exception {
		this.rwOvertimeDAO.insertRwOvertimes(rwovertimevolist);
	}

	public List findOverTimeByCriteria(String userId, String orgCodeFrom,
			String orgCodeTo, String empCodeFrom, String empCodeTo,
			String otTypeFrom, String flagPrFrom, String flagPrTo,
			String refNoFrom, String refNoTo, String ouCode, Long year,
			Long period, int pageRecord, int totalRecord) {
		return this.rwOvertimeDAO.findOverTimeByCriteria(userId, orgCodeFrom,
				orgCodeTo, empCodeFrom, empCodeTo, otTypeFrom, flagPrFrom,
				flagPrTo, refNoFrom, refNoTo, ouCode, year, period, pageRecord,
				totalRecord);
	}

	public Integer findCountOverTimeByCriteria(String userId,
			String orgCodeFrom, String orgCodeTo, String empCodeFrom,
			String empCodeTo, String otTypeFrom, String flagPrFrom,
			String flagPrTo, String refNoFrom, String refNoTo, String ouCode,
			Long year, Long period) {
		return this.rwOvertimeDAO.findCountOverTimeByCriteria(userId,
				orgCodeFrom, orgCodeTo, empCodeFrom, empCodeTo, otTypeFrom,
				flagPrFrom, flagPrTo, refNoFrom, refNoTo, ouCode, year, period);

	}

	public PayRollEmployeeVO findByEmpCodeDetail(String empCode, String ouCode,
			Long year, Long period) {
		return this.rwOvertimeDAO.findByEmpCodeDetail(empCode, ouCode, year,
				period);
	}

	public List findByEmpCodeList(String userId, String ouCode, long yearPr,
			long periodPr, String empCode, String refNo, String otType)
			throws UnsupportedEncodingException {
		return this.rwOvertimeDAO.findByEmpCodeList(userId, ouCode, new Long(
				yearPr), new Long(periodPr), empCode, refNo, otType);
	}

	public List findRwOvertimeReport(String ouCode, String userId, int year,
			int period, String otType, String otStatus) {
		return this.rwOvertimeDAO.findRwOvertimeReport(ouCode, userId, year,
				period, otType, otStatus);
	}

	public RwOvertime loadData(RwOvertimeVO rpVo) {
		return this.rwOvertimeDAO.loadRwOvertime(rpVo);
	}

	public void addList(RwOvertimeVO rpVo, boolean isSave) {

		this.rwOvertimeDAO.addList(rpVo);

		if (isSave) {
			try {
				this.rwOvertimeDAO.insertAndUpdateRwOvertimes();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void insertAndUpdate() throws Exception {
		this.rwOvertimeDAO.insertAndUpdateRwOvertimes();
	}

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.rwOvertimeDAO.isConfirmFlag(ouCode, year, period, userId);
	}

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.rwOvertimeDAO.canDelete(ouCode, year, period, userId);
	}
}
