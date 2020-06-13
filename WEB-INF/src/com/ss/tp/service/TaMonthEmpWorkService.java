/*
 * Created on 25 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.service;

import java.util.Date;
import java.util.List;

import com.ss.tp.dto.TaMonthEmpWorkVO;

/**
 * @author airsenaL
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public interface TaMonthEmpWorkService {
	public List listMonthEmpWork(String ouCode, int workYear, int workMonth,
			String areaCode, String secCode, String workCode, String userId,
			int maxRowPerPage, int pageNo);

	public List findByKey(int workYear, int workMonth, String empCode,
			String ouCode);

	public void addTaMonthEmpWork(String ouCode, int year, int month,
			String empCode, String creby, List arrDay, List arrWorkCode,
			List arrWorkSeq);

	public void updateTaMonthEmpWork(String ouCode, int workYear,
			int workMonth, String empCode, String userId, List arrDay,
			List arrWorkCode, List arrWorkSeq);

	public String deleteTaMonthEmpWork(String ouCode, int workYear,
			int workMonth, List empCode, String userId);

	public TaMonthEmpWorkVO findEmpByKey(int workYear, int workMonth,
			String empCode);

	public List findStatusWork(String ouCode);

	public String findEmpName(String ouCode, String empCode);

	public int getCountTaMonthEmpWork(String ouCode, int workYear,
			int workMonth, String areaCode, String secCode, String workCode,
			String userId);

	public void deleteEmpDtl(String ouCode, int workYear, int workMonth,
			String empCode, List arrWorkSeq);

	public int getCountEmp(String workYear, String workMonth, String empCode);

	public List findListEmpIsNotMonth(String ouCode, int month, int year,
			String userId, String areaCode, String secCode, String workCode);

	public List findListDivReportEmpMonth(String ouCode, String userId,
			int workMonth, int workYear, String areaCode, String secCode,
			String workCode);

	public List findListReportEmpMonth(String ouCode, String userId,
			int workMonth, int workYear, String areaCode, String secCode,
			String workCode);

	public String addCloseMonthEmpWork(int workYear, int workMonth,
			String ouCode, String userId);

	public String getStatusBeforeAddEmployee(String ouCode, int workYear,
			int workMonth, String areaCode, String secCode, String workCode,
			String userId);

	public String getStatusUpdateEmployee(String ouCode, int workYear,
			int workMonth, String empCode, String userId);
	
	public void updateTaMonthEmpWork(String ouCode,String empCode,String userId,String prefixName);

	
}
