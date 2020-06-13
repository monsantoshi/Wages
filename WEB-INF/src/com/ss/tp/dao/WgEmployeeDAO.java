package com.ss.tp.dao;

import java.util.List;

import com.ss.tp.dto.GwIncdecEmployeeVO;

public interface WgEmployeeDAO {
	public String findEmpName(String ouCode, String empCode);

	public List findWgEmpBySecurity(String userId, String ouCode)
			throws Exception;

	public List findToEmpBySecurity(String userId, String ouCode,
			String fromEmpCode) throws Exception;

	public List findWgPrToEmpBySecurity(String userId, String ouCode,
			String fromEmpCode, String year, String period) throws Exception;

	public GwIncdecEmployeeVO findByEmpCodeDetail(String userId,
			String empCode, String ouCode, Long year, Long period);

	public GwIncdecEmployeeVO findByEmpCodeDetailNotSecurity(String userId,
			String empCode, String ouCode, Long year, Long period);

	public GwIncdecEmployeeVO findByEmpCodeDetailInSecurity(String userId,
			String empCode, String ouCode, Long year, Long period);

}
