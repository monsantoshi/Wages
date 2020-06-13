package com.ss.tp.dao;

import java.io.Serializable;

import com.ss.tp.dto.VPrEmployeeVO;

public interface VPrEmployeeSecurityDAO extends Serializable {
	public VPrEmployeeVO findByKey(String ouCode, String userId,
			String empCode, String year, String period);
}
