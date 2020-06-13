/*
 * Created on 28 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import com.ss.tp.dao.TaWgReportDAO;
import com.ss.tp.service.TaWgReportService;

/**
 * @author bowpoko
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
/**
 * @author bowpoko
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class TaWgReportServiceImpl implements TaWgReportService, Serializable {
	private TaWgReportDAO taWgReportDAO;

	/**
	 * @return Returns the taWgReportDAO.
	 */
	public TaWgReportDAO getTaWgReportDAO() {
		return taWgReportDAO;
	}

	/**
	 * @param taWgReportDAO
	 *            The taWgReportDAO to set.
	 */
	public void setTaWgReportDAO(TaWgReportDAO taWgReportDAO) {
		this.taWgReportDAO = taWgReportDAO;
	}

	/**
	 * method ����Ѻ Query �����š���һ�Шӻբͧ�١��ҧ�ҡ Table V_TA_WG_MONTH_EMP_WORK
	 * �����Ѻ�����ŷ�� query ��ҡ class taWgReportDAOImpl
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

		return this.getTaWgReportDAO().findListTaReport(ouCode, userId,
				new Integer(workYear), orgCodeSeqFrom, orgCodeSeqTo,
				empCodeFrom, empCodeTo);
	}

	/**
	 * method ����Ѻ Query �����š���һ�Шӻբͧ�١��ҧ�ҡ Table V_TA_WG_MONTH_EMP_WORK
	 * ������ sheet report �·�����Ѻ�����ŷ�� query ��ҡ class taWgReportDAOImpl
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
		return this.getTaWgReportDAO().findListSecTaReport(ouCode, userId,
				new Integer(workYear), orgCodeSeqFrom, orgCodeSeqTo,
				empCodeFrom, empCodeTo);
	}

	public List findListTaWgReportNew(String ouCode, String userId, int workYear,
			int workYearFrom, int workYearTo, String monthFrom, String monthTo,
			String orgCodeFrom, String orgCodeTo, String empCodeFrom,
			String empCodeTo, String choice, Vector statusWorkCode,
			Vector dataStatusFrom, Vector dataStatusTo, int sumDonKeyFrom,
			int sumDonKeyTo, String workCodeFrom, String workCodeTo) {
		return this.getTaWgReportDAO().findListTaWgReportNew(ouCode, userId,
				new Integer(workYear), new Integer(workYearFrom),
				new Integer(workYearTo), monthFrom, monthTo, orgCodeFrom,
				orgCodeTo, empCodeFrom, empCodeTo, choice, statusWorkCode,
				dataStatusFrom, dataStatusTo, new Integer(sumDonKeyFrom),
				new Integer(sumDonKeyTo), workCodeFrom, workCodeTo);
	}

	public List findListSecTaWgReportNew(String ouCode, String userId,
			int workYear, int workYearFrom, int workYearTo, String monthFrom,
			String monthTo, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo, String choice,
			Vector statusWorkCode, Vector dataStatusFrom, Vector dataStatusTo,
			int sumDonKeyFrom, int sumDonKeyTo, String workCodeFrom,
			String workCodeTo) {
		return this.getTaWgReportDAO().findListSecTaWgReportNew(ouCode, userId,
				new Integer(workYear), new Integer(workYearFrom),
				new Integer(workYearTo), monthFrom, monthTo, orgCodeFrom,
				orgCodeTo, empCodeFrom, empCodeTo, choice, statusWorkCode,
				dataStatusFrom, dataStatusTo, new Integer(sumDonKeyFrom),
				new Integer(sumDonKeyTo), workCodeFrom, workCodeTo);
	}
	public String findAreaDesc(String orgCode) {
		return this.getTaWgReportDAO().findAreaDesc(orgCode);
	}

	public String findAreaDescTwo(String areaCode) {
		return this.getTaWgReportDAO().findAreaDescTwo(areaCode);
	}
}
