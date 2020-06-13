/*
 * Created on 15 �.�. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.dao;

import java.util.List;

import com.ss.tp.dto.PnEmployeeDetailVO;
import com.ss.tp.dto.WgEmployeeDetailVO;
import com.ss.tp.dto.WgPrEmployeeVO;

/**
 * @author Administrator
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public interface WgPrEmployeeDAO {
	public String[] findMaxYearPeriod(String ouCode);

	public List findWgPrEmpBySecurity(String userId, String ouCode,
			String year, String period) throws Exception;

	public List findWgPrToEmpBySecurity(String userId, String ouCode,
			String fromEmpCode, String year, String period) throws Exception;

	public List findByCriteria(String ouCode, String year, String period,
			String userId, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo, int page, int recPerPage)
			throws Exception;

	public Integer getCountByCriteria(String ouCode, String year,
			String period, String userId, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo) throws Exception;

	public PnEmployeeDetailVO findWgPrEmpNotInSecue(String userId,
			String ouCode, String empCode, String year, String period)
			throws Exception;

	public WgEmployeeDetailVO findWgPrEmpInSecue(String userId, String ouCode,
			String empCode, String year, String period) throws Exception;

	public WgPrEmployeeVO findWgPrEmp(String ouCode, String year,
			String period, String yearPrev, String periodPrev, String empCode,
			String userId);

	public List findDetailPaySlip(String ouCode, String userId, String year,
			String period);

}
