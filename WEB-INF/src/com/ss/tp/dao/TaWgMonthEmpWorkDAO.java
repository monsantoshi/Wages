/*
 * Created on 27 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.dao;

import java.util.List;

import com.ss.tp.dto.TaWgMonthEmpWorkVO;

/**
 * @author airsenal
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public interface TaWgMonthEmpWorkDAO {
	public List listMonthEmpWork(String ouCode, Integer workYear,
			Integer workMonth, String areaCode, String secCode,
			String workCode, String userId, Integer maxRowPerPage,
			Integer pageNo);

	public int getCountTaMonthEmpWork(String ouCode, Integer workYear,
			Integer workMonth, String areaCode, String secCode,
			String workCode, String userId);

	public int getCheckCloseThisMonth(String ouCode, Integer workYear,
			Integer workMonth);

	public String findEmpName(String ouCode, String empCode);

	public TaWgMonthEmpWorkVO findEmpByKey(Integer workYear, Integer workMonth,
			String empCode);

	public List findStatusWork(String ouCode);

	public void addTaMonthEmpWork(String ouCode, Integer year, Integer month,
			String empCode, String creby, List arrDay, List arrWorkCode,
			List arrWorkSeq);

	public List findListEmpIsNotMonth(String ouCode, Integer month,
			Integer year, String userId, String areaCode, String secCode,
			String workCode);

	public String deleteTaMonthEmpWork(String ouCode, Integer workYear,
			Integer workMonth, List empCode, String userId);

	public List findByKey(Integer workYear, Integer workMonth, String empCode,
			String ouCode);

	public void updateTaMonthEmpWork(String ouCode, Integer workYear,
			Integer workMonth, String empCode, String userId, List arrDay,
			List arrWorkCode, List arrWorkSeq);

	public void deleteEmpDtl(String ouCode, Integer workYear,
			Integer workMonth, String empCode, List arrWorkSeq);

	public String addCloseMonthEmpWork(Integer workYear, Integer workMonth,
			String ouCode, String userId);

	public List findListDivReportEmpMonth(String ouCode, String userId,
			Integer workMonth, Integer workYear, String areaCode,
			String secCode, String workCode);

	public List findListReportEmpMonth(String ouCode, String userId,
			Integer workMonth, Integer workYear, String areaCode,
			String secCode, String workCode);

	public String getStatusBeforeAddEmployee(String ouCode, Integer workYear,
			Integer workMonth, String areaCode, String secCode,
			String workCode, String userId);

	public String getStatusUpdateEmployee(String ouCode, Integer workYear,
			Integer workMonth, String empCode, String userId);
}
