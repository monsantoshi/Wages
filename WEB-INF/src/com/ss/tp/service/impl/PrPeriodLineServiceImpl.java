package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.PrEmployeeDAO;
import com.ss.tp.dao.PrEmployeeTextDAO;
import com.ss.tp.dao.PrPeriodLineDAO;
import com.ss.tp.dto.DefaultYearSectionVO;
import com.ss.tp.model.PrPeriodLine;
import com.ss.tp.service.PrPeriodLineService;

public class PrPeriodLineServiceImpl implements PrPeriodLineService,
		Serializable {

	private PrEmployeeDAO prEmployeeDAO;
	private PrEmployeeTextDAO prEmployeeTextDAO;
	private PrPeriodLineDAO prPeriodLineDAO;

	public PrEmployeeDAO getPrEmployeeDAO() {
		return prEmployeeDAO;
	}

	public void setPrEmployeeDAO(PrEmployeeDAO prEmployeeDAO) {
		this.prEmployeeDAO = prEmployeeDAO;
	}

	public PrEmployeeTextDAO getPrEmployeeTextDAO() {
		return prEmployeeTextDAO;
	}

	public void setPrEmployeeTextDAO(PrEmployeeTextDAO prEmployeeTextDAO) {
		this.prEmployeeTextDAO = prEmployeeTextDAO;
	}

	public PrPeriodLineDAO getPrPeriodLineDAO() {
		return prPeriodLineDAO;
	}

	public void setPrPeriodLineDAO(PrPeriodLineDAO prPeriodLineDAO) {
		this.prPeriodLineDAO = prPeriodLineDAO;
	}

	public DefaultYearSectionVO getDefaultYearAndSection(String ouCode,
			String userId) throws Exception {
		String[] val = prEmployeeDAO.findMaxYearPeriod(ouCode);
		// System.out.println("1234");
		boolean isConfirm = prEmployeeTextDAO.isConfirmFlag(ouCode, val[0],
				val[1], userId);
		// System.out.println("5678");
		PrPeriodLine periodLine = prPeriodLineDAO.findPeriodLine(ouCode,
				val[0], val[1]);
		// System.out.println("11111");

		DefaultYearSectionVO vo = new DefaultYearSectionVO();
		vo.setYear(val[0]);
		vo.setSection(periodLine.getPeriodName());
		vo.setConfirm(isConfirm);

		Double d = new Double(periodLine.getPk().getPeriod());
		int m = (d.intValue()) / 2;
		vo.setPeriod(String.valueOf(d.intValue()));
		vo.setMonth(String.valueOf(m));
		System.out.println("period : " + vo.getPeriod());
		System.out.println("month : " + vo.getMonth());

		return vo;
	}

	public List findYearInPeriodLine(String ouCode) throws Exception {
		return this.prPeriodLineDAO.findYearInPeriodLine(ouCode);
	}

	public List findPeriodInPeriodLine(String ouCode, double year)
			throws Exception {
		return this.prPeriodLineDAO.findPeriodInPeriodLine(ouCode, new Double(
				year));
	}

	public boolean canDeleteData(String ouCode, String year, String period)
			throws Exception {
		return this.prPeriodLineDAO.canDeleteData(ouCode, year, period);
	}

	public boolean isCloseTranClose(String ouCode, String year, String period)
			throws Exception {
		return this.prPeriodLineDAO.isCloseTranClose(ouCode, year, period);
	}
	
	public boolean isCloseMasterClose(String ouCode, String year, String period)
			throws Exception {
		return this.prPeriodLineDAO.isCloseMasterClose(ouCode, year, period);
	}


	public PrPeriodLine findPeriodLine(String ouCode, String year, String period)
			throws Exception {
		return this.prPeriodLineDAO.findPeriodLine(ouCode, year, period);
	}

}
