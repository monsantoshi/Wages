package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.PrEmpBankDAO;
import com.ss.tp.service.PrEmpBankService;

public class PrEmpBankServiceImpl implements PrEmpBankService, Serializable {

	private PrEmpBankDAO PrEmpBankDAO;

	public PrEmpBankDAO getPrEmpBankDAO() {
		return PrEmpBankDAO;
	}

	public void setPrEmpBankDAO(PrEmpBankDAO PrEmpBankDAO) {
		this.PrEmpBankDAO = PrEmpBankDAO;
	}

	public List prEmpBankReport(String userId, String evaOuCode, int evaYear,
			int evaPeriod) {
		return this.PrEmpBankDAO.prEmpBankReport(userId, evaOuCode,
				new Integer(evaYear), new Integer(evaPeriod));
	}
	

    public List prEmpKTBReport(String userId, String evaOuCode, int evaYear,
			int evaPeriod) {
		// TODO Auto-generated method stub
		return this.PrEmpBankDAO.prEmpKTBReport(userId, evaOuCode,
				new Integer(evaYear), new Integer(evaPeriod));
	}

}
