package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;
import com.ss.tp.dao.PrIncomeDeductDAO;
import com.ss.tp.dto.PrIncomeDeductVO;
import com.ss.tp.service.PrIncomeDeductService;

public class PrIncomeDeductServiceImpl implements PrIncomeDeductService,
		Serializable {

	private PrIncomeDeductDAO PrIncomeDeductDAO;

	public PrIncomeDeductDAO getPrIncomeDeductDAO() {
		return PrIncomeDeductDAO;
	}

	public void setPrIncomeDeductDAO(PrIncomeDeductDAO PrIncomeDeductDAO) {
		this.PrIncomeDeductDAO = PrIncomeDeductDAO;
	}

	public void insertPrIncomeDeduct(PrIncomeDeductVO princomevo)
			throws Exception {
		this.PrIncomeDeductDAO.insertPrIncomeDeduct(princomevo);
	}

	public void updatePrIncomeDeduct(PrIncomeDeductVO princomevo)
			throws Exception {
		this.PrIncomeDeductDAO.updatePrIncomeDeduct(princomevo);
	}

	public void deletePrIncomeDeduct(PrIncomeDeductVO princomevo)
			throws Exception {
		this.PrIncomeDeductDAO.deletePrIncomeDeduct(princomevo);
	}

	public void insertPrIncomeDeducts(List princomevolist) throws Exception {
		this.PrIncomeDeductDAO.insertPrIncomeDeducts(princomevolist);
	}

	public List findIncDecCode(String ouCode, String groupCode)
			throws Exception {
		return this.PrIncomeDeductDAO.findIncDecCode(ouCode, groupCode);
	}

	public List findIncDecCodeRep(String ouCode, String groupCode)
			throws Exception {
		return this.PrIncomeDeductDAO.findIncDecCodeRep(ouCode, groupCode);
	}
	
	public List findIncDecCodeOutAmtRep(String ouCode, String groupCode)
			throws Exception {
		return this.PrIncomeDeductDAO.findIncDecCodeOutAmtRep(ouCode, groupCode);
	}

	public List findIncDecCode59(String ouCode, String groupCode)
			throws Exception {
		return this.PrIncomeDeductDAO.findIncDecCode59(ouCode, groupCode);
	}

	public String getIncDecName(String ouCode, int groupCode, String incDecCode) {
		return this.PrIncomeDeductDAO.getIncDecName(ouCode, groupCode,
				incDecCode);
	}

	public Double findMaxIncDecCode(String ouCode, String groupCode,
			String incDecCode) throws Exception {
		return this.PrIncomeDeductDAO.findMaxIncDecCode(ouCode, groupCode,
				incDecCode);
	}
}
