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

// import com.ss.tp.dao.TaReportDAO;
import com.ss.tp.dao.WeWgEmpTextReportDAO; // import
// com.ss.tp.service.TaReportService;
import com.ss.tp.service.WeWgEmpTextReportService;

/**
 * @author bowpoko
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class WeWgEmpTextReportServiceImpl implements WeWgEmpTextReportService,
		Serializable {

	// private WePnEmpTextReport
	private WeWgEmpTextReportDAO weWgEmpTextReportDAO;

	/**
	 * @return Returns the taReportDAO.
	 */
	public WeWgEmpTextReportDAO getWeWgEmpTextReportDAO() {
		return weWgEmpTextReportDAO;
	}

	/**
	 * @param taReportDAO
	 *            The taReportDAO to set.
	 */
	public void setWeWgEmpTextReportDAO(
			WeWgEmpTextReportDAO weWgEmpTextReportDAO) {
		this.weWgEmpTextReportDAO = weWgEmpTextReportDAO;
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
	public List findListPntReport(String ouCode, String userId,
			String orgCodeSeqFrom, String orgCodeSeqTo, String empCodeFrom,
			String empCodeTo) {

		return this.weWgEmpTextReportDAO.findListPntReport(ouCode, userId,
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
	public List findListSecPntReport(String ouCode, String userId,
			String orgCodeSeqFrom, String orgCodeSeqTo, String empCodeFrom,
			String empCodeTo) {
		return this.weWgEmpTextReportDAO.findListSecPntReport(ouCode, userId,
				orgCodeSeqFrom, orgCodeSeqTo, empCodeFrom, empCodeTo);
	}

	public List findListPntReportNew(String ouCode, String userId,
			String orgCodeFrom, String orgCodeTo, String empCodeFrom,
			String empCodeTo) {
		return this.getWeWgEmpTextReportDAO().findListPntReportNew(ouCode,
				userId, orgCodeFrom, orgCodeTo, empCodeFrom, empCodeTo);
	}

	public List findListSecPntReportNew(String ouCode, String userId,
			String orgCodeFrom, String orgCodeTo, String empCodeFrom,
			String empCodeTo) {
		return this.getWeWgEmpTextReportDAO().findListSecPntReportNew(ouCode,
				userId, orgCodeFrom, orgCodeTo, empCodeFrom, empCodeTo);
	}

	public String findAreaDesc(String orgCode) {
		return this.getWeWgEmpTextReportDAO().findAreaDesc(orgCode);
	}

	public String findAreaDescTwo(String areaCode) {
		return this.getWeWgEmpTextReportDAO().findAreaDescTwo(areaCode);
	}
}
