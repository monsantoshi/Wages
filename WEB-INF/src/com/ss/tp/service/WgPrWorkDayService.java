/*
 * Created on 18 �.�. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.service;

import java.util.List;

import com.ss.tp.dto.WgPrWorkDayVO;

/**
 * @author Administrator
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public interface WgPrWorkDayService {
	public List addByCriteria(String ouCode, String userId, int year,
			int period, String areaCode, String secCode, String workCode,
			int pageNo, int maxRowPerPage) throws Exception;

	// ============= Create By Kiet ================
	public String findOrgName(String ouCode);

	public List findWgPrWorkDayReport(String ouCode, String userId, int year,
			int period) throws Exception;

	// =============================================

	public int countByCriteria(String ouCode, int year, int period,
			String areaCode, String secCode, String workCode) throws Exception;

	public void addList(WgPrWorkDayVO wgPrWorkDayVO);

	public void deleteWgPrWorkDay(WgPrWorkDayVO wgPrWorkDayVO) throws Exception;

	public void deleteWgPrWorkDayList() throws Exception;

	public void clearList();

	public void addOrUpdateWgPrWorkDay(String ouCode, int year, int period,
			List arrEmpCode, List arrCodeSeq, List arrWorkDay, List arrWorkAmt,
			List arrWageAmt, String creby);

	public Integer countConfirmFlag(String ouCode, String userId, int year,
			int period);

	public List findWgEmployeeAddOrUpdatePage(String ouCode, String userId,
			int year, int period, String areaCode, String secCode,
			String workCode);
}
