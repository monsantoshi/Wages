/*
 * Created on 4 �.�. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.TaViewDAO;
import com.ss.tp.service.TaViewService;

/**
 * @author bowpoko
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class TaViewServiceImpl implements TaViewService, Serializable {
	private TaViewDAO taViewDAO;

	/**
	 * @return Returns the taViewDAO.
	 */
	public TaViewDAO getTaViewDAO() {
		return taViewDAO;
	}

	/**
	 * @param taViewDAO
	 *            The taViewDAO to set.
	 */
	public void setTaViewDAO(TaViewDAO taViewDAO) {
		this.taViewDAO = taViewDAO;
	}

	/**
	 * method ����Ѻ Query ����١���һ�лբͧ��ѡ�ҹ�ҡ Table V_TA_MONTH_EMP_WORK �����Ѻ
	 * query �� class taViewDAOImpl
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
	public List findListEmpDetail(int workYear, String ouCode, String userId,
			String codeSeq, String empCodeFrom, String empCodeTo) {
		return this.taViewDAO.findListEmpDetail(new Integer(workYear), ouCode,
				userId, codeSeq, empCodeFrom, empCodeTo);
	}

	/**
	 * method ����Ѻ Query ����١���һ�лբͧ�١��ҧ�ҡ Table V_TA_WG_MONTH_EMP_WORK
	 * �����Ѻ query �� class taWgViewDAOImpl
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
	public List findListWgEmpDetail(Integer workYear, String ouCode,
			String userId, String codeSeq, String empCodeFrom, String empCodeTo) {
		return this.taViewDAO.findListWgEmpDetail(workYear, ouCode, userId,
				codeSeq, empCodeFrom, empCodeTo);
	}

	public List findListEmpDetailNew(String ouCode, String userId,
			int workYear, int workYearFrom, int workYearTo, String monthFrom,
			String monthTo, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo, String choice) {
		return this.getTaViewDAO().findListEmpDetailNew(ouCode, userId,
				new Integer(workYear), new Integer(workYearFrom),
				new Integer(workYearTo), monthFrom, monthTo, orgCodeFrom,
				orgCodeTo, empCodeFrom, empCodeTo, choice);
	}

	public List findListWgEmpDetailNew(String ouCode, String userId,
			int workYear, int workYearFrom, int workYearTo, String monthFrom,
			String monthTo, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo, String choice) {
		return this.getTaViewDAO().findListWgEmpDetailNew(ouCode, userId,
				new Integer(workYear), new Integer(workYearFrom),
				new Integer(workYearTo), monthFrom, monthTo, orgCodeFrom,
				orgCodeTo, empCodeFrom, empCodeTo, choice);
	}
}
