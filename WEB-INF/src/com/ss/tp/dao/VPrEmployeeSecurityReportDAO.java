package com.ss.tp.dao;

import java.io.Serializable;

import com.ss.tp.dto.VPrEmployeeReportVO;

public interface VPrEmployeeSecurityReportDAO extends Serializable {
	public VPrEmployeeReportVO findByKey(String ouCode, String userId,
			String empCode, String year, String period);
}
