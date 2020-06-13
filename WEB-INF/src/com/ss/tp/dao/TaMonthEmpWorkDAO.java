/*
 * Created on 25 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.dao;

import java.util.Date;
import java.util.List;

import com.ss.tp.dto.TaMonthEmpWorkVO;

/**
 * @author airsenaL
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public interface TaMonthEmpWorkDAO {
	public List listMonthEmpWork(String ouCode, Integer workYear,
			Integer workMonth, String areaCode, String secCode,
			String workCode, String userId, Integer maxRowPerPage,
			Integer pageNo);

	public List findByKey(Integer workYear, Integer workMonth, String empCode,
			String ouCode);

	public void addTaMonthEmpWork(String ouCode, Integer year, Integer month,
			String empCode, String creby, List arrDay, List arrWorkCode,
			List arrWorkSeq);

	public void updateTaMonthEmpWork(String ouCode, Integer workYear,
			Integer workMonth, String empCode, String userId, List arrDay,
			List arrWorkCode, List arrWorkSeq);

	public String deleteTaMonthEmpWork(String ouCode, Integer workYear,
			Integer workMonth, List empCode, String userId);

	public TaMonthEmpWorkVO findEmpByKey(Integer workYear, Integer workMonth,
			String empCode);

	public List findStatusWork(String ouCode);

	public int getCountTaMonthEmpWork(String ouCode, Integer workYear,
			Integer workMonth, String areaCode, String secCode,
			String workCode, String userId);

	public void deleteEmpDtl(String ouCode, Integer workYear,
			Integer workMonth, String empCode, List arrWorkSeq);

	public String findEmpName(String ouCode, String empCode);

	public int getCountEmp(Integer workYear, Integer workMonth, String empCode);

	public List findListEmpIsNotMonth(String ouCode, Integer month,
			Integer year, String userId, String areaCode, String secCode,
			String workCode);

	public List findListDivReportEmpMonth(String ouCode, String userId,
			Integer workMonth, Integer workYear, String areaCode,
			String secCode, String workCode);

	public List findListReportEmpMonth(String ouCode, String userId,
			Integer workMonth, Integer workYear, String areaCode,
			String secCode, String workCode);

	public String addCloseMonthEmpWork(Integer workYear, Integer workMonth,
			String ouCode, String userId);

	public String getStatusBeforeAddEmployee(String ouCode, Integer workYear,
			Integer workMonth, String areaCode, String secCode,
			String workCode, String userId);

	public String getStatusUpdateEmployee(String ouCode, Integer workYear,
			Integer workMonth, String empCode, String userId);
	
	public void updateTaMonthEmpWork(String ouCode,String empCode,String userId,String prefixName);

	
}
