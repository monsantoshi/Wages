/*
 * Created on 27 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.TaWgMonthEmpWorkDAO;
import com.ss.tp.dto.TaWgMonthEmpWorkVO;
import com.ss.tp.service.TaWgMonthEmpWorkService;

/**
 * @author airsenal
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class TaWgMonthEmpWorkServiceImpl implements TaWgMonthEmpWorkService,
		Serializable {
	public TaWgMonthEmpWorkDAO taWgMonthEmpWorkDAO;

	/**
	 * @return
	 */
	public TaWgMonthEmpWorkDAO getTaWgMonthEmpWorkDAO() {
		return taWgMonthEmpWorkDAO;
	}

	/**
	 * @param taWgMonthEmpWorkDAO
	 */
	public void setTaWgMonthEmpWorkDAO(TaWgMonthEmpWorkDAO taWgMonthEmpWorkDAO) {
		this.taWgMonthEmpWorkDAO = taWgMonthEmpWorkDAO;
	}

	/**
	 * method ����Ѻ Query �����ž�ѡ�ҹ�������¨ҡ Table TA_WG_MONTH_EMP_WORK
	 * �����Ѻ�����ŷ�� query ��ҡ class TaWgMonthEmpWorkDAOImpl
	 * 
	 * @param workYear
	 * @param workMonth
	 * @param codeSeq
	 * @param userId
	 * @param maxRowPerPage
	 * @param pageNo
	 * @return
	 */
	public List listMonthEmpWork(String ouCode, int workYear, int workMonth,
			String areaCode, String secCode, String workCode, String userId,
			int maxRowPerPage, int pageNo) {
		return this.getTaWgMonthEmpWorkDAO().listMonthEmpWork(ouCode,
				new Integer(workYear), new Integer(workMonth), areaCode,
				secCode, workCode, userId, new Integer(maxRowPerPage),
				new Integer(pageNo));
	}

	/**
	 * method ����Ѻ Count �ӹǹ��ѡ�ҹ Table TA_WG_MONTH_EMP_WORK
	 * 
	 * @param workYear
	 * @param workMonth
	 * @param codeSeq
	 * @param userId
	 * @return
	 */
	public int getCountTaMonthEmpWork(String ouCode, int workYear,
			int workMonth, String areaCode, String secCode, String workCode,
			String userId) {
		return this.getTaWgMonthEmpWorkDAO().getCountTaMonthEmpWork(ouCode,
				new Integer(workYear), new Integer(workMonth), areaCode,
				secCode, workCode, userId);
	}

	/**
	 * method ����Ѻ check �����͹��лչ���¶١�Դ�����������
	 * 
	 * @param ouCode
	 * @param workYear
	 * @param workMonth
	 * @return
	 */
	public int getCheckCloseThisMonth(String ouCode, int workYear, int workMonth) {
		return this.getTaWgMonthEmpWorkDAO().getCheckCloseThisMonth(ouCode,
				new Integer(workYear), new Integer(workMonth));
	}

	/**
	 * method ����Ѻ�֧�����Ū��;�ѡ�ҹ
	 * 
	 * @param ouCode
	 * @param empCode
	 * @return
	 */
	public String findEmpName(String ouCode, String empCode) {
		return this.getTaWgMonthEmpWorkDAO().findEmpName(ouCode, empCode);
	}

	/**
	 * method ����Ѻ Query ���;�ѡ�ҹ�ҡ Table WG_EMPLOYEE �����Ѻ�����ŷ�� query ��ҡ
	 * class TaWgMonthEmpWorkDAOImpl
	 * 
	 * @param workYear
	 * @param workMonth
	 * @param empCode
	 * @return
	 */
	public TaWgMonthEmpWorkVO findEmpByKey(int workYear, int workMonth,
			String empCode) {
		return this.getTaWgMonthEmpWorkDAO().findEmpByKey(
				new Integer(workYear), new Integer(workMonth), empCode);
	}

	/**
	 * method ����Ѻ�֧������ Table TA_STATUS_WORK
	 * 
	 * @param ouCode
	 * @return
	 */
	public List findStatusWork(String ouCode) {
		return this.getTaWgMonthEmpWorkDAO().findStatusWork(ouCode);
	}

	/**
	 * method ����Ѻ���b�����ŧ Table TA_WG_MONTH_EMP_WORK
	 * 
	 * @param ouCode
	 * @param year
	 * @param month
	 * @param creby
	 * @param arrDay
	 * @param arrTime
	 * @param arrWorkCode
	 * @param arrWorkSeq
	 */
	public void addTaMonthEmpWork(String ouCode, int year, int month,
			String empCode, String creby, List arrDay, List arrWorkCode,
			List arrWorkSeq) {
		this.getTaWgMonthEmpWorkDAO().addTaMonthEmpWork(ouCode,
				new Integer(year), new Integer(month), empCode, creby, arrDay,
				arrWorkCode, arrWorkSeq);
	}

	/**
	 * method ����Ѻ���Ҩӹǹ��ѡ�ҹ Table WG_EMPLOYEE ����ѧ����¶١�ѹ�֡���͹ ��лջѨ�غѹ
	 * 
	 * @param ouCode
	 * @param month
	 * @param year
	 * @param userId
	 * @param codeSeq
	 * @return
	 */
	public List findListEmpIsNotMonth(String ouCode, int month, int year,
			String userId, String areaCode, String secCode, String workCode) {
		return this.getTaWgMonthEmpWorkDAO().findListEmpIsNotMonth(ouCode,
				new Integer(month), new Integer(year), userId, areaCode,
				secCode, workCode);
	}

	/**
	 * method ����Ѻ Delete �����ž�ѡ�ҹ�������¨ҡ Table TA_WG_MONTH_EMP_WORK
	 * �����Ѻ���Ѿ���á�� Delete �����Ũҡ class TaWgMonthEmpWorkDAOImpl
	 * 
	 * @param ouCode
	 * @param workYear
	 * @param workMonth
	 * @param empCode
	 * @return
	 */
	public String deleteTaMonthEmpWork(String ouCode, int workYear,
			int workMonth, List empCode, String userId) {
		return this.getTaWgMonthEmpWorkDAO().deleteTaMonthEmpWork(ouCode,
				new Integer(workYear), new Integer(workMonth), empCode, userId);
	}

	/**
	 * method ����Ѻ Query �����ž�ѡ�ҹ�������¨ҡ Table TA_WG_MONTH_EMP_WORK
	 * �����Ѻ�����ŷ�� query ��ҡ class TaWgMonthEmpWorkDAOImpl
	 * 
	 * @param workYear
	 * @param workMonth
	 * @param empCode
	 * @return
	 */
	public List findByKey(int workYear, int workMonth, String empCode,
			String ouCode) {
		return this.getTaWgMonthEmpWorkDAO().findByKey(new Integer(workYear),
				new Integer(workMonth), empCode, ouCode);
	}

	/**
	 * method ����Ѻ Update �����ž�ѡ�ҹ�������¨ҡ Table TA_WG_MONTH_EMP_WORK
	 * �����Ѻ���Ѿ���á�� Update �����Ũҡ class TaWgMonthEmpWorkDAOImpl
	 * 
	 * @param ouCode
	 * @param workYear
	 * @param workMonth
	 * @param empCode
	 * @param userId
	 * @param arrDay
	 * @param arrTime
	 * @param arrWorkCode
	 * @param arrWorkSeq
	 */
	public void updateTaMonthEmpWork(String ouCode, int workYear,
			int workMonth, String empCode, String userId, List arrDay,
			List arrWorkCode, List arrWorkSeq) {
		this.getTaWgMonthEmpWorkDAO().updateTaMonthEmpWork(ouCode,
				new Integer(workYear), new Integer(workMonth), empCode, userId,
				arrDay, arrWorkCode, arrWorkSeq);
	}

	/**
	 * method ����Ѻ Delete ������ Table TA_WG_MONTH_EMP_WORK �˹�Ҩ� Update Status
	 * Work �ͧ��ѡ�ҹ
	 * 
	 * @param ouCode
	 * @param workYear
	 * @param workMonth
	 * @param empCode
	 * @param arrWorkSeq
	 * @return
	 */
	public void deleteEmpDtl(String ouCode, int workYear, int workMonth,
			String empCode, List arrWorkSeq) {
		this.getTaWgMonthEmpWorkDAO().deleteEmpDtl(ouCode,
				new Integer(workYear), new Integer(workMonth), empCode,
				arrWorkSeq);
	}

	/**
	 * method ����Ѻ��÷ӡ�ûԴ�����Ż�Ш���͹�ͧ��ѡ�ҹ �� Insert ������ŧ Table
	 * TA_WG_CONTROL
	 * 
	 * @param workYear
	 * @param workMonth
	 * @param ouCode
	 * @param userId
	 * @return
	 */
	public String addCloseMonthEmpWork(int workYear, int workMonth,
			String ouCode, String userId) {
		return this.getTaWgMonthEmpWorkDAO().addCloseMonthEmpWork(
				new Integer(workYear), new Integer(workMonth), ouCode, userId);
	}

	/**
	 * 
	 * @param ouCode
	 * @param month
	 * @param year
	 * @param codeSeq
	 * @return
	 */
	public List findListDivReportEmpMonth(String ouCode, String userId,
			int workMonth, int workYear, String areaCode, String secCode,
			String workCode) {
		return this.getTaWgMonthEmpWorkDAO().findListDivReportEmpMonth(ouCode,
				userId, new Integer(workMonth), new Integer(workYear),
				areaCode, secCode, workCode);
	}

	/**
	 * 
	 * @param ouCode
	 * @param month
	 * @param year
	 * @param codeSeq
	 * @return
	 */
	public List findListReportEmpMonth(String ouCode, String userId,
			int workMonth, int workYear, String areaCode, String secCode,
			String workCode) {
		return this.getTaWgMonthEmpWorkDAO().findListReportEmpMonth(ouCode,
				userId, new Integer(workMonth), new Integer(workYear),
				areaCode, secCode, workCode);
	}

	/**
	 * method ����Ѻ��Ǩ�ͺ��͹�ӡ�� Insert �������١��ҧ �µ�Ǩ�ͺ��Ң����Ż�
	 * �Ǵ������ʡͧ�ӹѡ�ҹ����¶١�Դ�������
	 * 
	 * @param ouCode
	 * @param workYear
	 * @param workMonth
	 * @param codeSeq
	 * @param userId
	 */
	public String getStatusBeforeAddEmployee(String ouCode, int workYear,
			int workMonth, String areaCode, String secCode, String workCode,
			String userId) {
		return this.getTaWgMonthEmpWorkDAO().getStatusBeforeAddEmployee(ouCode,
				new Integer(workYear), new Integer(workMonth), areaCode,
				secCode, workCode, userId);
	}

	/**
	 * method ����Ѻ��Ǩ�ͺ��͹�ӡ�� Update �������١��ҧ �µ�Ǩ�ͺ��Ң����Ż�
	 * �Ǵ������ʡͧ�ӹѡ�ҹ����¶١�Դ�������
	 * 
	 * @param ouCode
	 * @param workYear
	 * @param workMonth
	 * @param empCode
	 * @param userId
	 */
	public String getStatusUpdateEmployee(String ouCode, int workYear,
			int workMonth, String empCode, String userId) {
		return this.getTaWgMonthEmpWorkDAO().getStatusUpdateEmployee(ouCode,
				new Integer(workYear), new Integer(workMonth), empCode, userId);
	}
}
