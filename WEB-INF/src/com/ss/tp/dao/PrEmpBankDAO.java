package com.ss.tp.dao;

import java.util.List;

//import com.ss.tp.model.PrEmpBank;

public interface PrEmpBankDAO {

	public List prEmpBankReport(String userId, String evaOuCode,
			Integer evaYear, Integer evaPeriod);
	
	public List prEmpKTBReport(String userId, String evaOuCode,
			Integer evaYear, Integer evaPeriod);

}