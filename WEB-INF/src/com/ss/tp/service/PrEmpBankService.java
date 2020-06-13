package com.ss.tp.service;

import java.util.List;

public interface PrEmpBankService {

	public List prEmpBankReport(String userId, String evaOuCode, int evaYear,
			int evaPeriod);
	
	public List prEmpKTBReport(String userId, String evaOuCode, int evaYear,
			int evaPeriod);

}