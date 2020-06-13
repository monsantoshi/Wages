package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;
import com.ss.tp.dao.FeeWpayPrMonthlyLineDAO;
import com.ss.tp.service.FeeWpayPrMonthlyLineService;

public class FeeWpayPrMonthlyLineServiceImpl implements FeeWpayPrMonthlyLineService,
		Serializable {

	private FeeWpayPrMonthlyLineDAO feeWpayPrMonthlyLineDAO;

	public FeeWpayPrMonthlyLineDAO getFeeWpayPrMonthlyLineDAO() {
		return feeWpayPrMonthlyLineDAO;
	}

	public void setFeeWpayPrMonthlyLineDAO(FeeWpayPrMonthlyLineDAO feeWpayPrMonthlyLineDAO) {
		this.feeWpayPrMonthlyLineDAO = feeWpayPrMonthlyLineDAO;
	}

	public List findPaySlip(String ouCode, String year, String period,
			String empCode, String groupCode) throws Exception {
		return this.feeWpayPrMonthlyLineDAO.findPaySlip(ouCode, year, period, empCode,
				groupCode);
	}

	public List findIncomeAndTaxPrAccumulate(String ouCode, String year,
			String period, String empCode) throws Exception {
		return this.feeWpayPrMonthlyLineDAO.findIncomeAndTaxPrAccumulate(ouCode, year,
				period, empCode);
	}
	
	public List findPaySlipNew(String ouCode, String year, String period,
			String empCode, String groupCode) throws Exception {
		return this.feeWpayPrMonthlyLineDAO.findPaySlipNew(ouCode, year, period, empCode,
				groupCode);
	}
	
}
