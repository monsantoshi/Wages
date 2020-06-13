/*
 * Created on 26 �.�. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.service.impl;

import java.io.Serializable;

import java.util.List;
import java.util.Vector;

import com.ss.tp.dao.TaReportDAO;
import com.ss.tp.service.TaReportService;

/**
 * @author bowpoko
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class TaReportServiceImpl implements TaReportService, Serializable {
	private TaReportDAO taReportDAO;

	/**
	 * @return Returns the taReportDAO.
	 */
	public TaReportDAO getTaReportDAO() {
		return taReportDAO;
	}

	/**
	 * @param taReportDAO
	 *            The taReportDAO to set.
	 */
	public void setTaReportDAO(TaReportDAO taReportDAO) {
		this.taReportDAO = taReportDAO;
	}

	/**
	 * method ����Ѻ Query �����š���ҨӢͧ��ѡ�ҹ�ըҡ Table V_TA_MONTH_EMP_WORK
	 * �����Ѻ�����ŷ�� query ��ҡ class taReportDAOImpl
	 * 
	 * @param userId
	 * @param ouCode
	 * @param workYear
	 * @param workMonth
	 * @param orgCodeSeqFrom
	 * @param orgCodeSeqTo
	 * @param empCodeFrom
	 * @param empCodeTo
	 * @return
	 */
	public List findListTaReport(String ouCode, String userId, int workYear,
			String orgCodeSeqFrom, String orgCodeSeqTo, String empCodeFrom,
			String empCodeTo) {

		return this.taReportDAO
				.findListTaReport(ouCode, userId, new Integer(workYear),
						orgCodeSeqFrom, orgCodeSeqTo, empCodeFrom, empCodeTo);
	}

	/**
	 * method ����Ѻ Query �����š���һ�Шӻբͧ��ѡ�ҹ�ҡ Table V_TA_MONTH_EMP_WORK
	 * ������ sheet report �·�����Ѻ�����ŷ�� query ��ҡ class taReportDAOImpl
	 * 
	 * @param userId
	 * @param ouCode
	 * @param workYear
	 * @param workMonth
	 * @param orgCodeSeqFrom
	 * @param orgCodeSeqTo
	 * @param empCodeFrom
	 * @param empCodeTo
	 * @return
	 */
	public List findListSecTaReport(String ouCode, String userId, int workYear,
			String orgCodeSeqFrom, String orgCodeSeqTo, String empCodeFrom,
			String empCodeTo) {
		return this.taReportDAO.findListSecTaReport(ouCode, userId,
				new Integer(workYear), orgCodeSeqFrom, orgCodeSeqTo,
				empCodeFrom, empCodeTo);
	}

	public List findListTaReportNew(String ouCode, String userId, int workYear,
			int workYearFrom, int workYearTo, String monthFrom, String monthTo,
			String orgCodeFrom, String orgCodeTo, String empCodeFrom,
			String empCodeTo, String choice, Vector statusWorkCode,
			Vector dataStatusFrom, Vector dataStatusTo, int sumDonKeyFrom,
			int sumDonKeyTo, String workCodeFrom, String workCodeTo) {
		return this.getTaReportDAO().findListTaReportNew(ouCode, userId,
				new Integer(workYear), new Integer(workYearFrom),
				new Integer(workYearTo), monthFrom, monthTo, orgCodeFrom,
				orgCodeTo, empCodeFrom, empCodeTo, choice, statusWorkCode,
				dataStatusFrom, dataStatusTo, new Integer(sumDonKeyFrom),
				new Integer(sumDonKeyTo), workCodeFrom, workCodeTo);
	}

	public List findListSecTaReportNew(String ouCode, String userId,
			int workYear, int workYearFrom, int workYearTo, String monthFrom,
			String monthTo, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo, String choice,
			Vector statusWorkCode, Vector dataStatusFrom, Vector dataStatusTo,
			int sumDonKeyFrom, int sumDonKeyTo, String workCodeFrom,
			String workCodeTo) {
		return this.getTaReportDAO().findListSecTaReportNew(ouCode, userId,
				new Integer(workYear), new Integer(workYearFrom),
				new Integer(workYearTo), monthFrom, monthTo, orgCodeFrom,
				orgCodeTo, empCodeFrom, empCodeTo, choice, statusWorkCode,
				dataStatusFrom, dataStatusTo, new Integer(sumDonKeyFrom),
				new Integer(sumDonKeyTo), workCodeFrom, workCodeTo);
	}

	public String findAreaDesc(String orgCode) {
		return this.getTaReportDAO().findAreaDesc(orgCode);
	}

	public String findAreaDescTwo(String areaCode) {
		return this.getTaReportDAO().findAreaDescTwo(areaCode);
	}
}
