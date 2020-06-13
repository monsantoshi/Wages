/*
 * Created on 18 �.�. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.service.impl;

import java.util.List;

import com.ss.tp.dao.WgPeriodLineDAO;
import com.ss.tp.dao.WgPrWorkDayDAO;
import com.ss.tp.dto.WgPrWorkDayVO;
import com.ss.tp.service.WgPrWorkDayService;

/**
 * @author Administrator
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class WgPrWorkDayServiceImpl implements WgPrWorkDayService {
	private WgPeriodLineDAO wgPeriodLineDAO;
	private WgPrWorkDayDAO wgPrWorkDayDAO;

	public WgPrWorkDayDAO getWgPrWorkDayDAO() {
		return wgPrWorkDayDAO;
	}

	public void setWgPrWorkDayDAO(WgPrWorkDayDAO wgPrWorkDayDAO) {
		this.wgPrWorkDayDAO = wgPrWorkDayDAO;
	}

	public WgPeriodLineDAO getWgPeriodLineDAO() {
		return wgPeriodLineDAO;
	}

	public void setWgPeriodLineDAO(WgPeriodLineDAO wgPeriodLineDAO) {
		this.wgPeriodLineDAO = wgPeriodLineDAO;
	}

	public List addByCriteria(String ouCode, String userId, int year,
			int period, String areaCode, String secCode, String workCode,
			int pageNo, int maxRowPerPage) throws Exception {
		return this.wgPrWorkDayDAO.addByCriteria(ouCode, userId, year, period,
				areaCode, secCode, workCode, pageNo, maxRowPerPage);
	}

	// ============= Create By Kiet ================
	public String findOrgName(String ouCode) {
		return this.wgPrWorkDayDAO.findOrgName(ouCode);
	}

	public List findWgPrWorkDayReport(String ouCode, String userId, int year,
			int period) throws Exception {
		return this.wgPrWorkDayDAO.findWgPrWorkDayReport(ouCode, userId, year,
				period);
	}

	// =============================================

	public int countByCriteria(String ouCode, int year, int period,
			String areaCode, String secCode, String workCode) throws Exception {
		return this.wgPrWorkDayDAO.countByCriteria(ouCode, year, period,
				areaCode, secCode, workCode);
	}

	public void addList(WgPrWorkDayVO wgPrWorkDayVO) {
		this.wgPrWorkDayDAO.addList(wgPrWorkDayVO);
	}

	public void deleteWgPrWorkDay(WgPrWorkDayVO wgPrWorkDayVO) throws Exception {
		try {
			this.wgPrWorkDayDAO.deleteWgPrWorkDay(wgPrWorkDayVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteWgPrWorkDayList() throws Exception {
		this.wgPrWorkDayDAO.deleteWgPrWorkDayList();
	}

	public void clearList() {
		this.wgPrWorkDayDAO.clearList();
	}

	public void addOrUpdateWgPrWorkDay(String ouCode, int year, int period,
			List arrEmpCode, List arrCodeSeq, List arrWorkDay, List arrWorkAmt,
			List arrWageAmt, String creby) {
		this.wgPrWorkDayDAO.addOrUpdateWgPrWorkDay(ouCode, year, period,
				arrEmpCode, arrCodeSeq, arrWorkDay, arrWorkAmt, arrWageAmt,
				creby);
	}

	public Integer countConfirmFlag(String ouCode, String userId, int year,
			int period) {
		Integer confirm = this.wgPrWorkDayDAO.countConfirmFlag(ouCode, userId,
				year, period);
		boolean tranClose = false;
		try {
			tranClose = this.wgPeriodLineDAO.isCloseTranClose(ouCode,
					year + "", period + "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (tranClose) {
			if (confirm.intValue() <= 0) {
				confirm = new Integer(1);
			}
		}
		return confirm;
	}

	public List findWgEmployeeAddOrUpdatePage(String ouCode, String userId,
			int year, int period, String areaCode, String secCode,
			String workCode) {
		return this.wgPrWorkDayDAO.findWgEmployeeAddOrUpdatePage(ouCode,
				userId, year, period, areaCode, secCode, workCode);
	}
}
