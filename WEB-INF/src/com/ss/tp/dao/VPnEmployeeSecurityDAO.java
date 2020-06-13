package com.ss.tp.dao;

import java.io.Serializable;

import com.ss.tp.dto.VPnEmployeeVO;

public interface VPnEmployeeSecurityDAO extends Serializable {
	public VPnEmployeeVO findByKey(String ouCode, String userId, String empCode);
}
