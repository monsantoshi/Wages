package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;
import com.ss.tp.dao.PrMonthlyLineDAO;
import com.ss.tp.service.PrMonthlyLineService;

public class PrMonthlyLineServiceImpl implements PrMonthlyLineService,
		Serializable {

	private PrMonthlyLineDAO prMonthlyLineDAO;

	public PrMonthlyLineDAO getPrMonthlyLineDAO() {
		return prMonthlyLineDAO;
	}

	public void setPrMonthlyLineDAO(PrMonthlyLineDAO prMonthlyLineDAO) {
		this.prMonthlyLineDAO = prMonthlyLineDAO;
	}

	public List findPaySlip(String ouCode, String year, String period,
			String empCode, String groupCode) throws Exception {
		return this.prMonthlyLineDAO.findPaySlip(ouCode, year, period, empCode,
				groupCode);
	}

	public List findIncomeAndTaxPrAccumulate(String ouCode, String year,
			String period, String empCode) throws Exception {
		return this.prMonthlyLineDAO.findIncomeAndTaxPrAccumulate(ouCode, year,
				period, empCode);
	}
}
