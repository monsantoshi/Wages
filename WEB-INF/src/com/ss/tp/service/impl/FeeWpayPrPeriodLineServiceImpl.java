package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.FeeWpayPrEmployeeDAO;
import com.ss.tp.dao.FeeWpayPrEmployeeTextDAO;
//import com.ss.tp.dao.PrPeriodLineDAO;
import com.ss.tp.dao.FeeWpayPrPeriodLineDAO;
import com.ss.tp.dto.DefaultYearSectionVO;
//import com.ss.tp.model.PrPeriodLine;
import com.ss.tp.model.FeeWpayPrPeriodLine;
//import com.ss.tp.service.PrPeriodLineService;
import com.ss.tp.service.FeeWpayPrPeriodLineService;

public class FeeWpayPrPeriodLineServiceImpl implements FeeWpayPrPeriodLineService,
		Serializable {

	private FeeWpayPrEmployeeDAO feeWpayPrEmployeeDAO;
	private FeeWpayPrEmployeeTextDAO feeWpayPrEmployeeTextDAO;
	//private PrPeriodLineDAO prPeriodLineDAO;
	private FeeWpayPrPeriodLineDAO feeWpayPrPeriodLineDAO;

	public FeeWpayPrEmployeeDAO getFeeWpayPrEmployeeDAO() {
		return feeWpayPrEmployeeDAO;
	}

	public void setFeeWpayPrEmployeeDAO(FeeWpayPrEmployeeDAO feeWpayPrEmployeeDAO) {
		this.feeWpayPrEmployeeDAO = feeWpayPrEmployeeDAO;
	}

	public FeeWpayPrEmployeeTextDAO getFeeWpayPrEmployeeTextDAO() {
		return feeWpayPrEmployeeTextDAO;
	}

	public void setFeeWpayPrEmployeeTextDAO(FeeWpayPrEmployeeTextDAO feeWpayPrEmployeeTextDAO) {
		this.feeWpayPrEmployeeTextDAO = feeWpayPrEmployeeTextDAO;
	}

	
	
	public FeeWpayPrPeriodLineDAO getFeeWpayPrPeriodLineDAO() {
		return feeWpayPrPeriodLineDAO;
	}

	public void setFeeWpayPrPeriodLineDAO(FeeWpayPrPeriodLineDAO feeWpayPrPeriodLineDAO) {
		this.feeWpayPrPeriodLineDAO = feeWpayPrPeriodLineDAO;
	}

	public DefaultYearSectionVO getDefaultYearAndSection(String ouCode,String userId) throws Exception {
		String[] val = feeWpayPrEmployeeDAO.findMaxYearPeriod(ouCode,userId);
		// System.out.println("1234");
		boolean isConfirm = feeWpayPrEmployeeTextDAO.isConfirmFlag(ouCode, val[0],
				val[1], userId);
		// System.out.println("5678");
		FeeWpayPrPeriodLine periodLine = feeWpayPrPeriodLineDAO.findPeriodLine(ouCode,
				val[0], val[1],userId);
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

	public DefaultYearSectionVO getDefaultYearAndSectionOne(String ouCode,String userId) throws Exception {
		String[] val = feeWpayPrEmployeeDAO.findMinYearPeriodLine(ouCode,userId);
		String[] ral = feeWpayPrEmployeeDAO.findMinPeriodLine(ouCode, val[0], userId);
		FeeWpayPrPeriodLine periodLine = feeWpayPrPeriodLineDAO.findPeriodLine(ouCode,val[0], ral[1],userId);
		// System.out.println("11111");
        boolean confirmed = true;
        if (periodLine.getMainClose()=="N") {
        	confirmed = false;
        }else{ 
        	confirmed = true;
        }
		DefaultYearSectionVO vo = new DefaultYearSectionVO();
		vo.setYear(val[0]);
		vo.setSection(periodLine.getPeriodName());
		vo.setConfirm(confirmed);

		Double d = new Double(periodLine.getPk().getPeriod());
		int m = (d.intValue()) / 2;
	
		vo.setPeriod(String.valueOf(d.intValue()));
		vo.setMonth(String.valueOf(m));
		System.out.println("period : " + vo.getPeriod());
		System.out.println("month : " + vo.getMonth());
		System.out.println("section : " + vo.getSection());

		return vo;
	}
	
	public DefaultYearSectionVO getDefaultYearAndSectionApprove(String ouCode,String userId) throws Exception {
		String[] val = feeWpayPrEmployeeDAO.findMinYearPeriodLine(ouCode,userId);
		// System.out.println("1234");
		boolean isConfirm = feeWpayPrEmployeeTextDAO.isConfirmFlag(ouCode, val[0],
				val[1], userId);
		// System.out.println("5678");
		FeeWpayPrPeriodLine periodLine = feeWpayPrPeriodLineDAO.findPeriodLine(ouCode,
				val[0], val[1],userId);
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
	
	
	public DefaultYearSectionVO getDefaultYearAndSectionTranClose(String ouCode,String userId) throws Exception {
		String[] val = feeWpayPrEmployeeDAO.findMaxYearPeriodTransClose(ouCode,userId);
		// System.out.println("1234");
		boolean isConfirm = feeWpayPrEmployeeTextDAO.isConfirmFlag(ouCode, val[0],
				val[1], userId);
		// System.out.println("5678");
		FeeWpayPrPeriodLine periodLine = feeWpayPrPeriodLineDAO.findPeriodLine(ouCode,
				val[0], val[1],userId);
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
		return this.feeWpayPrPeriodLineDAO.findYearInPeriodLine(ouCode);
	}

	public List findPeriodInPeriodLine(String ouCode, double year,String userId)
			throws Exception {
		return this.feeWpayPrPeriodLineDAO.findPeriodInPeriodLine(ouCode, new Double(year),userId);
	}

	public boolean canDeleteData(String ouCode, String year, String period,String userId)
			throws Exception {
		return this.feeWpayPrPeriodLineDAO.canDeleteData(ouCode, year, period,userId);
	}

	public boolean isCloseTranClose(String ouCode, String year, String period,String userId)
			throws Exception {
		return this.feeWpayPrPeriodLineDAO.isCloseTranClose(ouCode, year, period,userId);
	}
	
	public boolean isCloseMasterClose(String ouCode, String year, String period,String userId)
			throws Exception {
		return this.feeWpayPrPeriodLineDAO.isCloseMasterClose(ouCode, year, period,userId);
	}


	public FeeWpayPrPeriodLine findPeriodLine(String ouCode, String year, String period,String userId)
			throws Exception {
		return this.feeWpayPrPeriodLineDAO.findPeriodLine(ouCode, year, period,userId);
	}

}
