/*
 * Created on 15 �.�. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.WgPeriodLineDAO;
import com.ss.tp.dao.WgPrEmployeeDAO;
import com.ss.tp.dao.WgPrEmployeeTextDAO;
import com.ss.tp.dto.DefaultYearSectionVO;

import com.ss.tp.model.WgPeriodLine;
import com.ss.tp.service.WgPeriodLineService;

/**
 * @author Administrator
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class WgPeriodLineServiceImpl implements WgPeriodLineService,
		Serializable {
	private WgPeriodLineDAO wgPeriodLineDAO;
	private WgPrEmployeeDAO wgPrEmployeeDAO;
	private WgPrEmployeeTextDAO wgPrEmployeeTextDAO;

	public WgPeriodLineDAO getWgPeriodLineDAO() {
		return wgPeriodLineDAO;
	}

	public void setWgPeriodLineDAO(WgPeriodLineDAO wgPeriodLineDAO) {
		this.wgPeriodLineDAO = wgPeriodLineDAO;
	}

	public WgPrEmployeeDAO getWgPrEmployeeDAO() {
		return wgPrEmployeeDAO;
	}

	public void setWgPrEmployeeDAO(WgPrEmployeeDAO wgPrEmployeeDAO) {
		this.wgPrEmployeeDAO = wgPrEmployeeDAO;
	}

	public WgPrEmployeeTextDAO getWgPrEmployeeTextDAO() {
		return wgPrEmployeeTextDAO;
	}

	public void setWgPrEmployeeTextDAO(WgPrEmployeeTextDAO wgPrEmployeeTextDAO) {
		this.wgPrEmployeeTextDAO = wgPrEmployeeTextDAO;
	}

	public DefaultYearSectionVO getDefaultYearAndSection(String ouCode,
			String userId) throws Exception {
		String[] val = this.getWgPrEmployeeDAO().findMaxYearPeriod(ouCode);
		boolean isConfirm = this.getWgPrEmployeeTextDAO().isConfirmFlag(ouCode,
				val[0], val[1], userId);
		WgPeriodLine periodLine = this.getWgPeriodLineDAO().findPeriodLine(
				ouCode, val[0], val[1]);

		DefaultYearSectionVO vo = new DefaultYearSectionVO();
		vo.setYear(val[0]);
		vo.setSection(periodLine.getPeriodName());
		vo.setConfirm(isConfirm);

		Integer d = periodLine.getWgPeriodLinePK().getPeriod();

		vo.setPeriod(String.valueOf(d.intValue()));

		return vo;
	}

	/*
	 * start airsenal 01/11/2006 add new method for decrease one month of
	 * defalut year
	 */
	public DefaultYearSectionVO getDefaultYearAndSectionMinusOne(String ouCode,
			String userId) throws Exception {
		String[] val = this.getWgPrEmployeeDAO().findMaxYearPeriod(ouCode);
		boolean isConfirm = this.getWgPrEmployeeTextDAO().isConfirmFlag(ouCode,
				val[0], val[1], userId);
		DefaultYearSectionVO vo = new DefaultYearSectionVO();
		int year = Integer.parseInt(val[0]);
		int period = Integer.parseInt(val[1]);
		if (period == 1) {
			period = 12;
			year--;
		} else {
			period--;
		}
		WgPeriodLine periodLine = this.getWgPeriodLineDAO().findPeriodLine(
				ouCode, String.valueOf(year), String.valueOf(period));
		vo.setYear(String.valueOf(year));
		vo.setSection(periodLine.getPeriodName());
		vo.setConfirm(isConfirm);
		vo.setPeriod(String.valueOf(period));
		return vo;
	}

	/*
	 * end airsenal 01/11/2006 add new method for decrease one month of defalut
	 * year
	 */
	public List findYearInPeriodLine(String ouCode) throws Exception {
		return this.wgPeriodLineDAO.findYearInPeriodLine(ouCode);
	}

	public List findPeriodInPeriodLine(String ouCode, int year)
			throws Exception {
		return this.wgPeriodLineDAO.findPeriodInPeriodLine(ouCode, new Integer(
				year));
	}

	public boolean isCloseTranClose(String ouCode, String year, String period)
			throws Exception {
		return this.wgPeriodLineDAO.isCloseTranClose(ouCode, year, period);
	}

	public String findMaxPeriodOfYear(String ouCode, String year)
			throws Exception {
		return this.wgPeriodLineDAO.findMaxPeriodOfYear(ouCode, year);
	}

}
