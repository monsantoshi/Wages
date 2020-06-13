/*
 * Created on 25 �.�. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ss.tp.dao.PnEmployeeDAO;
import com.ss.tp.dao.TaMonthEmpWorkDAO;
import com.ss.tp.dto.TaMonthEmpWorkVO;
import com.ss.tp.service.TaMonthEmpWorkService;

/**
 * @author airsenaL
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class TaMonthEmpWorkServiceImpl implements TaMonthEmpWorkService,
		Serializable {
	public TaMonthEmpWorkDAO taMonthEmpWorkDAO;
	public PnEmployeeDAO pnEmployeeDAO;

	/**
	 * @return Returns the pnEmployeeDAO.
	 */
	public PnEmployeeDAO getPnEmployeeDAO() {
		return pnEmployeeDAO;
	}

	/**
	 * @param pnEmployeeDAO
	 *            The pnEmployeeDAO to set.
	 */
	public void setPnEmployeeDAO(PnEmployeeDAO pnEmployeeDAO) {
		this.pnEmployeeDAO = pnEmployeeDAO;
	}

	/**
	 * @return Returns the taMonthEmpWorkDao.
	 */
	public TaMonthEmpWorkDAO getTaMonthEmpWorkDAO() {
		return taMonthEmpWorkDAO;
	}

	/**
	 * @param taMonthEmpWorkDao
	 *            The taMonthEmpWorkDao to set.
	 */
	public void setTaMonthEmpWorkDAO(TaMonthEmpWorkDAO taMonthEmpWorkDAO) {
		this.taMonthEmpWorkDAO = taMonthEmpWorkDAO;
	}

	/**
	 * method ����Ѻ Query �����ž�ѡ�ҹ�������¨ҡ Table TA_MONTH_EMP_WORK
	 * �����Ѻ�����ŷ�� query ��ҡ class TaMonthEmpWorkDAOImpl
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
		return this.getTaMonthEmpWorkDAO().listMonthEmpWork(ouCode,
				new Integer(workYear), new Integer(workMonth), areaCode,
				secCode, workCode, userId, new Integer(maxRowPerPage),
				new Integer(pageNo));
	}

	/**
	 * method ����Ѻ Query �����ž�ѡ�ҹ�������¨ҡ Table TA_MONTH_EMP_WORK
	 * �����Ѻ�����ŷ�� query ��ҡ class TaMonthEmpWorkDAOImpl
	 * 
	 * @param workYear
	 * @param workMonth
	 * @param empCode
	 * @return
	 */
	public List findByKey(int workYear, int workMonth, String empCode,
			String ouCode) {
		return this.getTaMonthEmpWorkDAO().findByKey(new Integer(workYear),
				new Integer(workMonth), empCode, ouCode);
	}

	/**
	 * method ����Ѻ Query ���;�ѡ�ҹ�ҡ Table PN_EMPLOYEE �����Ѻ�����ŷ�� query ��ҡ
	 * class TaMonthEmpWorkDAOImpl
	 * 
	 * @param workYear
	 * @param workMonth
	 * @param empCode
	 */
	public TaMonthEmpWorkVO findEmpByKey(int workYear, int workMonth,
			String empCode) {
		return this.getTaMonthEmpWorkDAO().findEmpByKey(new Integer(workYear),
				new Integer(workMonth), empCode);
	}

	/**
	 * method ����Ѻ���b�����ŧ Table TA_MONTH_EMP_WORK
	 * 
	 * @param ouCode
	 * @param year
	 * @param month
	 * @param empCode
	 * @param creby
	 * @param arrDay
	 * @param arrWorkCode
	 * @param arrWorkSeq
	 */
	public void addTaMonthEmpWork(String ouCode, int year, int month,
			String empCode, String creby, List arrDay, List arrWorkCode,
			List arrWorkSeq) {
		this.getTaMonthEmpWorkDAO().addTaMonthEmpWork(ouCode,
				new Integer(year), new Integer(month), empCode, creby, arrDay,
				arrWorkCode, arrWorkSeq);
	}

	/**
	 * method ����Ѻ Update �����ž�ѡ�ҹ�������¨ҡ Table TA_MONTH_EMP_WORK
	 * �����Ѻ���Ѿ���á�� Update �����Ũҡ class TaMonthEmpWorkDAOImpl
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
		this.getTaMonthEmpWorkDAO().updateTaMonthEmpWork(ouCode,
				new Integer(workYear), new Integer(workMonth), empCode, userId,
				arrDay, arrWorkCode, arrWorkSeq);
	}

	/**
	 * method ����Ѻ Delete �����ž�ѡ�ҹ�������¨ҡ Table TA_MONTH_EMP_WORK
	 * �����Ѻ���Ѿ���á�� Delete �����Ũҡ class TaMonthEmpWorkDAOImpl
	 * 
	 * @param ouCode
	 * @param workYear
	 * @param workMonth
	 * @param empCode
	 * @return
	 */
	public String deleteTaMonthEmpWork(String ouCode, int workYear,
			int workMonth, List empCode, String userId) {
		return this.getTaMonthEmpWorkDAO().deleteTaMonthEmpWork(ouCode,
				new Integer(workYear), new Integer(workMonth), empCode, userId);
	}

	/**
	 * method ����Ѻ�֧������ Table TA_STATUS_WORK
	 * 
	 * @param ouCode
	 * @return
	 */
	public List findStatusWork(String ouCode) {
		return this.getTaMonthEmpWorkDAO().findStatusWork(ouCode);
	}

	/**
	 * method ����Ѻ�֧�����Ū��;�ѡ�ҹ
	 * 
	 * @param ouCode
	 * @param empCode
	 * @return
	 */
	public String findEmpName(String ouCode, String empCode) {
		return this.getTaMonthEmpWorkDAO().findEmpName(ouCode, empCode);
	}

	/**
	 * method ����Ѻ Count �ӹǹ��ѡ�ҹ Table TA_MONTH_EMP_WORK
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
		return this.getTaMonthEmpWorkDAO().getCountTaMonthEmpWork(ouCode,
				new Integer(workYear), new Integer(workMonth), areaCode,
				secCode, workCode, userId);
	}

	/**
	 * method ����Ѻ Delete ������ Table TA_MONTH_EMP_WORK �˹�Ҩ� Update Status
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
		this.getTaMonthEmpWorkDAO().deleteEmpDtl(ouCode, new Integer(workYear),
				new Integer(workMonth), empCode, arrWorkSeq);
	}

	/**
	 * method ����Ѻ Count �ӹǹ��ѡ�ҹ Table TA_MONTH_EMP_WORK
	 * 
	 * @param workYear
	 * @param workMonth
	 * @param empCode
	 * @return
	 */
	public int getCountEmp(String workYear, String workMonth, String empCode) {
		return this.getTaMonthEmpWorkDAO().getCountEmp(new Integer(workYear),
				new Integer(workMonth), empCode);
	}

	/**
	 * method ����Ѻ���Ҩӹǹ��ѡ�ҹ Table PN_EMPLOYEE ����ѧ����¶١�ѹ�֡���͹ ��лջѨ�غѹ
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
		return this.getTaMonthEmpWorkDAO().findListEmpIsNotMonth(ouCode,
				new Integer(month), new Integer(year), userId, areaCode,
				secCode, workCode);
	}

	/**
	 * method ����Ѻ�ʴ���áͧ�ӹѡ�ҹ Table PN_EMPLOYEE
	 * 
	 * @param ouCode
	 * @param userId
	 * @param workMonth
	 * @param workYear
	 * @param codeSeq
	 * @return
	 */
	public List findListDivReportEmpMonth(String ouCode, String userId,
			int workMonth, int workYear, String areaCode, String secCode,
			String workCode) {
		return this.getTaMonthEmpWorkDAO().findListDivReportEmpMonth(ouCode,
				userId, new Integer(workMonth), new Integer(workYear),
				areaCode, secCode, workCode);
	}

	/**
	 * method ����Ѻ�ʴ���§ҹ��÷ӧ�û�Ш���͹�ͧ��ѡ�ҹ Table PN_EMPLOYEE
	 * 
	 * @param ouCode
	 * @param userId
	 * @param workMonth
	 * @param workYear
	 * @param codeSeq
	 * @return
	 */
	public List findListReportEmpMonth(String ouCode, String userId,
			int workMonth, int workYear, String areaCode, String secCode,
			String workCode) {
		return this.getTaMonthEmpWorkDAO().findListReportEmpMonth(ouCode,
				userId, new Integer(workMonth), new Integer(workYear),
				areaCode, secCode, workCode);
	}

	/**
	 * method ����Ѻ��÷ӡ�ûԴ�����Ż�Ш���͹�ͧ��ѡ�ҹ �� Insert ������ŧ Table TA_CONTROL
	 * 
	 * @param workYear
	 * @param workMonth
	 * @param ouCode
	 * @param userId
	 * @return
	 */
	public String addCloseMonthEmpWork(int workYear, int workMonth,
			String ouCode, String userId) {
		return this.getTaMonthEmpWorkDAO().addCloseMonthEmpWork(
				new Integer(workYear), new Integer(workMonth), ouCode, userId);
	}

	/**
	 * method ����Ѻ��Ǩ�ͺ��͹�ӡ�� Insert �����ž�ѡ�ҹ �µ�Ǩ�ͺ��Ң����Ż�
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
		return this.getTaMonthEmpWorkDAO().getStatusBeforeAddEmployee(ouCode,
				new Integer(workYear), new Integer(workMonth), areaCode,
				secCode, workCode, userId);
	}

	/**
	 * method ����Ѻ��Ǩ�ͺ��͹�ӡ�� Update �����ž�ѡ�ҹ �µ�Ǩ�ͺ��Ң����Ż�
	 * �Ǵ������ʡͧ�ӹѡ�ҹ����¶١�Դ�������
	 * 
	 * @param ouCode
	 * @param workYear
	 * @param workMonth
	 * @param empCode
	 */
	public String getStatusUpdateEmployee(String ouCode, int workYear,
			int workMonth, String empCode, String userId) {
		return this.getTaMonthEmpWorkDAO().getStatusUpdateEmployee(ouCode,
				new Integer(workYear), new Integer(workMonth), empCode, userId);
	}
	
	public void updateTaMonthEmpWork(String ouCode,String empCode,String userId,String prefixName) {
		this.getTaMonthEmpWorkDAO().updateTaMonthEmpWork(ouCode,empCode,userId,prefixName);
	}
	
}
