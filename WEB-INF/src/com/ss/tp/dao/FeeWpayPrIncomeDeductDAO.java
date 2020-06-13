package com.ss.tp.dao;

import java.util.List;

import com.ss.tp.dto.FeeWpayPrIncomeDeductVO;

public interface FeeWpayPrIncomeDeductDAO {
	public void insertPrIncomeDeduct(FeeWpayPrIncomeDeductVO princomevo)
			throws Exception;

	public void updatePrIncomeDeduct(FeeWpayPrIncomeDeductVO princomevo)
			throws Exception;

	public void deletePrIncomeDeduct(FeeWpayPrIncomeDeductVO princomevo)
			throws Exception;

	public void insertPrIncomeDeducts(List princomevolist) throws Exception;

	public List findIncDecCode(String ouCode, String groupCode)
			throws Exception;

	public List findIncDecCodeRep(String ouCode, String groupCode)
			throws Exception;
	
	public List findIncDecCodeOutAmtRep(String ouCode, String groupCode)
			throws Exception;

	public List findIncDecCode59(String ouCode, String groupCode)
			throws Exception;

	public Double findMaxIncDecCode(String ouCode, String groupCode,
			String incDecCode) throws Exception;

	public String getIncDecName(String ouCode, int groupCode, String incDecCode);
}