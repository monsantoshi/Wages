package com.ss.tp.dao;

import java.util.List;

public interface PrMonthlyLineDAO {
	public List findPaySlip(String ouCode, String year, String period,
			String empCode, String groupCode) throws Exception;

	public List findIncomeAndTaxPrAccumulate(String ouCode, String year,
			String period, String empCode) throws Exception;
}