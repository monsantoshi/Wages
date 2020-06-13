package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;
import com.ss.tp.dao.FeeWpayPrIncomeDeductDAO;
import com.ss.tp.dto.FeeWpayPrIncomeDeductVO;
import com.ss.tp.service.FeeWpayPrIncomeDeductService;

public class FeeWpayPrIncomeDeductServiceImpl implements FeeWpayPrIncomeDeductService,
		Serializable {

	private FeeWpayPrIncomeDeductDAO feeWpayPrIncomeDeductDAO;

	public FeeWpayPrIncomeDeductDAO getFeeWpayPrIncomeDeductDAO() {
		return feeWpayPrIncomeDeductDAO;
	}

	public void setFeeWpayPrIncomeDeductDAO(FeeWpayPrIncomeDeductDAO feeWpayPrIncomeDeductDAO) {
		this.feeWpayPrIncomeDeductDAO = feeWpayPrIncomeDeductDAO;
	}

	public void insertPrIncomeDeduct(FeeWpayPrIncomeDeductVO princomevo)
			throws Exception {
		this.feeWpayPrIncomeDeductDAO.insertPrIncomeDeduct(princomevo);
	}

	public void updatePrIncomeDeduct(FeeWpayPrIncomeDeductVO princomevo)
			throws Exception {
		this.feeWpayPrIncomeDeductDAO.updatePrIncomeDeduct(princomevo);
	}

	public void deletePrIncomeDeduct(FeeWpayPrIncomeDeductVO princomevo)
			throws Exception {
		this.feeWpayPrIncomeDeductDAO.deletePrIncomeDeduct(princomevo);
	}

	public void insertPrIncomeDeducts(List princomevolist) throws Exception {
		this.feeWpayPrIncomeDeductDAO.insertPrIncomeDeducts(princomevolist);
	}

	public List findIncDecCode(String ouCode, String groupCode)
			throws Exception {
		return this.feeWpayPrIncomeDeductDAO.findIncDecCode(ouCode, groupCode);
	}

	public List findIncDecCodeRep(String ouCode, String groupCode)
			throws Exception {
		return this.feeWpayPrIncomeDeductDAO.findIncDecCodeRep(ouCode, groupCode);
	}
	
	public List findIncDecCodeOutAmtRep(String ouCode, String groupCode)
			throws Exception {
		return this.feeWpayPrIncomeDeductDAO.findIncDecCodeOutAmtRep(ouCode, groupCode);
	}

	public List findIncDecCode59(String ouCode, String groupCode)
			throws Exception {
		return this.feeWpayPrIncomeDeductDAO.findIncDecCode59(ouCode, groupCode);
	}

	public String getIncDecName(String ouCode, int groupCode, String incDecCode) {
		return this.feeWpayPrIncomeDeductDAO.getIncDecName(ouCode, groupCode,
				incDecCode);
	}

	public Double findMaxIncDecCode(String ouCode, String groupCode,
			String incDecCode) throws Exception {
		return this.feeWpayPrIncomeDeductDAO.findMaxIncDecCode(ouCode, groupCode,
				incDecCode);
	}
}
