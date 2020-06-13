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

import com.ss.tp.dao.WpayPrWorkDayReportDAO;
import com.ss.tp.service.WpayPrWorkDayReportService;

/**
 * @author bowpoko
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class WpayPrWorkDayReportServiceImpl implements WpayPrWorkDayReportService, Serializable {
	private WpayPrWorkDayReportDAO wPayPrWorkDayReportDAO;

	/**
	 * @return Returns the wPayPrWorkDayReportDAO.
	 */
	public WpayPrWorkDayReportDAO getWpayPrWorkDayReportDAO() {
		return wPayPrWorkDayReportDAO;
	}

	/**
	 * @param wPayPrWorkDayReportDAO
	 *            The wPayPrWorkDayReportDAO to set.
	 */
	public void setWpayPrWorkDayReportDAO(WpayPrWorkDayReportDAO wPayPrWorkDayReportDAO) {
		this.wPayPrWorkDayReportDAO = wPayPrWorkDayReportDAO;
	}

	/**
	 * method ����Ѻ Query �����š���ҨӢͧ��ѡ�ҹ�ըҡ Table V_TA_MONTH_EMP_WORK
	 * �����Ѻ�����ŷ�� query ��ҡ class wPayPrWorkDayReportDAOImpl
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

		return this.wPayPrWorkDayReportDAO.findListTaReport(ouCode, userId, new Integer(workYear),
						orgCodeSeqFrom, orgCodeSeqTo, empCodeFrom, empCodeTo);
	}

	/**
	 * method ����Ѻ Query �����š���һ�Шӻբͧ��ѡ�ҹ�ҡ Table V_TA_MONTH_EMP_WORK
	 * ������ sheet report �·�����Ѻ�����ŷ�� query ��ҡ class wPayPrWorkDayReportDAOImpl
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
		return this.wPayPrWorkDayReportDAO.findListSecTaReport(ouCode, userId,
				new Integer(workYear), orgCodeSeqFrom, orgCodeSeqTo,
				empCodeFrom, empCodeTo);
	}

	public List findListTaReportNew(String ouCode, String userId, int workYear,
			int workYearFrom, int workYearTo, String monthFrom, String monthTo,
			String orgCodeFrom, String orgCodeTo, String empCodeFrom,
			String empCodeTo, String choice, Vector statusWorkCode,
			Vector dataStatusFrom, Vector dataStatusTo, int sumDonKeyFrom,
			int sumDonKeyTo, String workCodeFrom, String workCodeTo) {
		return this.getWpayPrWorkDayReportDAO().findListTaReportNew(ouCode, userId,
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
		return this.getWpayPrWorkDayReportDAO().findListSecTaReportNew(ouCode, userId,
				new Integer(workYear), new Integer(workYearFrom),
				new Integer(workYearTo), monthFrom, monthTo, orgCodeFrom,
				orgCodeTo, empCodeFrom, empCodeTo, choice, statusWorkCode,
				dataStatusFrom, dataStatusTo, new Integer(sumDonKeyFrom),
				new Integer(sumDonKeyTo), workCodeFrom, workCodeTo);
	}

	public String findAreaDesc(String orgCode) {
		return this.getWpayPrWorkDayReportDAO().findAreaDesc(orgCode);
	}

	public String findAreaDescTwo(String areaCode) {
		return this.getWpayPrWorkDayReportDAO().findAreaDescTwo(areaCode);
	}
}
