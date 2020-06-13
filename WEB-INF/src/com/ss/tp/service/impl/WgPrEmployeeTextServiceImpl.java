package com.ss.tp.service.impl;

import java.util.List;

import com.ss.tp.dao.WgPrEmployeeTextDAO;
import com.ss.tp.dto.WgPrEmployeeTextVO;
import com.ss.tp.service.WgPrEmployeeTextService;

public class WgPrEmployeeTextServiceImpl implements WgPrEmployeeTextService {

	private WgPrEmployeeTextDAO wgPrEmployeeTextDAO;

	public WgPrEmployeeTextDAO getWgPrEmployeeTextDAO() {
		return wgPrEmployeeTextDAO;
	}

	public void setWgPrEmployeeTextDAO(WgPrEmployeeTextDAO wgPrEmployeeTextDAO) {
		this.wgPrEmployeeTextDAO = wgPrEmployeeTextDAO;
	}

	public void insertWgPrEmployeeText(WgPrEmployeeTextVO premployeetextvo)
			throws Exception {
		this.wgPrEmployeeTextDAO.insertWgPrEmployeeText(premployeetextvo);
	}

	public void updatePrEmployeeText(WgPrEmployeeTextVO premployeetextvo)
			throws Exception {
		this.wgPrEmployeeTextDAO.updatePrEmployeeText(premployeetextvo);
	}

	public void deletePrEmployeeText(WgPrEmployeeTextVO premployeetextvo)
			throws Exception {
		this.wgPrEmployeeTextDAO.deletePrEmployeeText(premployeetextvo);
	}

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.wgPrEmployeeTextDAO.isConfirmFlag(ouCode, year, period,
				userId);
	}

	public List updateConfirmIncome(String userId, String ouCode, long yearPr,
			long periodPr) throws Exception {
		// System.out.println("into updateconfirmIncome");

		try {
			List ls = this.wgPrEmployeeTextDAO.updateConfirmIncome(userId,
					ouCode, new Long(yearPr), new Long(periodPr));

			this.wgPrEmployeeTextDAO.updateWgPrIncDecOtherIncome(ouCode,
					String.valueOf(yearPr), String.valueOf(periodPr), userId);

			this.wgPrEmployeeTextDAO.updateWgPrOvertimeIncome(ouCode,
					String.valueOf(yearPr), String.valueOf(periodPr), userId);

			this.wgPrEmployeeTextDAO.updateWgPrWorkDayIncome(ouCode,
					String.valueOf(yearPr), String.valueOf(periodPr), userId);

			this.wgPrEmployeeTextDAO.updateWgPrIncDecOtherDeduct(ouCode,
					String.valueOf(yearPr), String.valueOf(periodPr), userId);

			return ls;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List deleteConfirmIncome(String userId, String ouCode, long yearPr,
			long periodPr) throws Exception {
		// System.out.println("into updateconfirmIncome");

		try {
			List ls = this.wgPrEmployeeTextDAO.deleteConfirmIncome(userId,
					ouCode, new Long(yearPr), new Long(periodPr));

			this.wgPrEmployeeTextDAO.deleteWgPrIncDecOtherIncome(ouCode,
					String.valueOf(yearPr), String.valueOf(periodPr), userId);

			this.wgPrEmployeeTextDAO.deleteWgPrOvertimeIncome(ouCode,
					String.valueOf(yearPr), String.valueOf(periodPr), userId);

			this.wgPrEmployeeTextDAO.deleteWgPrWorkDayIncome(ouCode,
					String.valueOf(yearPr), String.valueOf(periodPr), userId);

			return ls;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List updateConfirmDeduct(String userId, String ouCode, long yearPr,
			long periodPr) throws Exception {
		try {
			List ls = this.wgPrEmployeeTextDAO.updateConfirmDeduct(userId,
					ouCode, new Long(yearPr), new Long(periodPr));

			this.wgPrEmployeeTextDAO.updateWgPrIncDecOtherDeduct(ouCode,
					String.valueOf(yearPr), String.valueOf(periodPr), userId);

			return ls;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List deleteConfirmDeduct(String userId, String ouCode, long yearPr,
			long periodPr) throws Exception {
		try {
			List ls = this.wgPrEmployeeTextDAO.deleteConfirmDeduct(userId,
					ouCode, new Long(yearPr), new Long(periodPr));

			this.wgPrEmployeeTextDAO.deleteWgPrIncDecOtherDeduct(ouCode,
					String.valueOf(yearPr), String.valueOf(periodPr), userId);

			return ls;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public Integer confirmData(String ouCode, long yearPr, long periodPr,
			String flagStatus, String userId) {
		return this.wgPrEmployeeTextDAO.confirmData(ouCode, new Long(yearPr),
				new Long(periodPr), flagStatus, userId);
	}

	public void updateConfirmData(String ouCode, long yearPr, long periodPr,
			String userId) throws Exception {
		this.wgPrEmployeeTextDAO.updateConfirmData(ouCode, new Long(yearPr),
				new Long(periodPr), userId);
	}

	/**
	 * method ����Ѻ Query �����Ţ����š������¹�ŧ��������ѡ�١��ҧ��Ш� �ҡ Table
	 * VWgPrEmpTextRep V_WG_PR_EMP_WORK_TEXT_REP �����Ѻ�����ŷ�� query ��ҡ class
	 * wgPrEmployeeTextDAOImpl
	 * 
	 * @param userId
	 * @param ouCode
	 * @param year
	 * @param period
	 * @return
	 */
	public List findChangeOfMonth(String ouCode, String userId, int year,
			int period) {
		return this.wgPrEmployeeTextDAO.findChangeOfMonth(ouCode, userId,
				new Integer(year), new Integer(period));
	}
}
