package com.ss.tp.service;

import java.util.List;

/**
 * @param args
 */
public interface PnEmpImpService {

	public List findByCriteriaReport(int workYear, int workYearFrom,
			int workYearTo, String monthFrom, String monthTo);
	public List findByCriteriaReportEngNameNew(int workYear, int workYearFrom,
			int workYearTo, String monthFrom, String monthTo);
	public List findByCriteriaReportEngNameOld(int workYear, int workYearFrom,
			int workYearTo, String monthFrom, String monthTo);
	// public List findByCriteriaReport2(String orgCodeFrom,String orgCodeTo,
	// String empCodeFrom,String empCodeTo,String choiceStatus,String
	// choiceGroup);
	// public String findFormCode(String ouCode,long evaYear,long evaTime,String
	// empCode);
	// public Double findFormScore(String ouCode,String formCode);

}
