package com.ss.tp.dao;

import java.util.List;

public interface PnEmpImpDAO {

	public List findByCriteriaReport(Integer workYear, Integer workYearFrom,
			Integer workYearTo, String monthFrom, String monthTo);
	public List findByCriteriaReportEngNameNew(Integer workYear, Integer workYearFrom,
			Integer workYearTo, String monthFrom, String monthTo);
	public List findByCriteriaReportEngNameOld(Integer workYear, Integer workYearFrom,
			Integer workYearTo, String monthFrom, String monthTo);

}
