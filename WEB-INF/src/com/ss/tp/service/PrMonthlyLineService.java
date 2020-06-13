package com.ss.tp.service;

import java.util.List;

public interface PrMonthlyLineService {
	public List findPaySlip(String ouCode, String year, String period,
			String empCode, String groupCode) throws Exception;

	public List findIncomeAndTaxPrAccumulate(String ouCode, String year,
			String period, String empCode) throws Exception;
}
