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
import com.ss.tp.dao.WePnEmpTextReportDAO; // import
// com.ss.tp.service.TaReportService;
import com.ss.tp.service.WePnEmpTextReportService;

/**
 * @author bowpoko
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class WePnEmpTextReportServiceImpl implements WePnEmpTextReportService,
		Serializable {

	// private WePnEmpTextReport
	private WePnEmpTextReportDAO wePnEmpTextReportDAO;

	/**
	 * @return Returns the taReportDAO.
	 */
	public WePnEmpTextReportDAO getWePnEmpTextReportDAO() {
		return wePnEmpTextReportDAO;
	}

	/**
	 * @param taReportDAO
	 *            The taReportDAO to set.
	 */
	public void setWePnEmpTextReportDAO(
			WePnEmpTextReportDAO wePnEmpTextReportDAO) {
		this.wePnEmpTextReportDAO = wePnEmpTextReportDAO;
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

		return this.wePnEmpTextReportDAO.findListPntReport(ouCode, userId,
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
		return this.wePnEmpTextReportDAO.findListSecPntReport(ouCode, userId,
				orgCodeSeqFrom, orgCodeSeqTo, empCodeFrom, empCodeTo);
	}

	public List findListPntReportNew(String ouCode, String userId,
			String orgCodeFrom, String orgCodeTo, String empCodeFrom,
			String empCodeTo) {
		return this.getWePnEmpTextReportDAO().findListPntReportNew(ouCode,
				userId, orgCodeFrom, orgCodeTo, empCodeFrom, empCodeTo);
	}

	public List findListSecPntReportNew(String ouCode, String userId,
			String orgCodeFrom, String orgCodeTo, String empCodeFrom,
			String empCodeTo) {
		return this.getWePnEmpTextReportDAO().findListSecPntReportNew(ouCode,
				userId, orgCodeFrom, orgCodeTo, empCodeFrom, empCodeTo);
	}

	public String findAreaDesc(String orgCode) {
		return this.getWePnEmpTextReportDAO().findAreaDesc(orgCode);
	}

	public String findAreaDescTwo(String areaCode) {
		return this.getWePnEmpTextReportDAO().findAreaDescTwo(areaCode);
	}
}
