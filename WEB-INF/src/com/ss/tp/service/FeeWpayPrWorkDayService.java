package com.ss.tp.service;

import java.util.List;
import java.util.Vector;

import com.ss.tp.dto.FeeMonthEmpWorkVO;
import com.ss.tp.dto.FeeWpayMonthEmpWorkVO;
import com.ss.tp.dto.FeeWpayPayRollEmployeeVO;
import com.ss.tp.dto.FeeWpayPrWorkDayVO;

public interface FeeWpayPrWorkDayService {
	public void insertPrWorkDay(FeeWpayPrWorkDayVO feewpayprworkdayvo)
			throws Exception;

	public void updatePrWorkDay(FeeWpayPrWorkDayVO feewpayprworkdayvo)
			throws Exception;

	public void deletePrWorkDay(FeeWpayPrWorkDayVO feewpayprworkdayvo)
			throws Exception;

	public void insertPrWorkDays(List prworkdayvolist) throws Exception;

	public List findByCriteria(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo, int count, int countRecord);

	public Integer countData(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo);

	public FeeWpayPayRollEmployeeVO findByEmpCodeDetail(String empCode,
			String ouCode, Long year, Long period);

	public List findByEmpCodeList(String userId, String ouCode, long yearPr,
			long periodPr, String empCode);

	public List findPrWorkDayReport(String ouCode, Integer year,
			Integer period, String type, String userId);

	public void addList(FeeWpayPrWorkDayVO rpVo, boolean isSave);

	public void insertAndUpdate() throws Exception;

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception;

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception;

	public List findByCriteriaList(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo);

	public void generate(String userId, String ouCode, Long year, Long period);
	
	
	
	

	public String findEmpName(String ouCode, String empCode);

	public FeeWpayMonthEmpWorkVO findEmpByKey(int workYear, int workMonth,
			String empCode);

	public List findWorkMonth(String ouCode);
	public List findStatusWork(String ouCode,int workMonth);

	public void addPrWorkDay(String ouCode, int year, int month,
			String empCode, String creby, List arrDay, List arrWorkCode,
			List arrWorkSeq,List arrNote);

	public List listMonthEmpWork(String ouCode, int workYear, int workMonth,
			String areaCode, String secCode, String workCode, String userId,
			int maxRowPerPage, int pageNo);

	public int getCountTaMonthEmpWork(String ouCode, int workYear,
			int workMonth, String areaCode, String secCode, String workCode,
			String userId);

	public int getCheckCloseThisMonth(String ouCode, int workYear, int workMonth);
	
	public List findListEmpIsNotMonth(String ouCode, int month, int year,
			String userId, String areaCode, String secCode, String workCode);

	public String deleteTaMonthEmpWork(String ouCode, int workYear,
			int workMonth, List empCode, String userId);

	public List findByKey(int workYear, int workMonth, String empCode,
			String ouCode);

	public void updateTaMonthEmpWork(String ouCode, int workYear,
			int workMonth, String empCode, String userId, List arrDay,
			List arrWorkCode, List arrWorkSeq,List arrNote);

	public void deleteEmpDtl(String ouCode, int workYear, int workMonth,
			String empCode, List arrWorkSeq);

	public String addCloseMonthEmpWork(int workYear, int workMonth,
			String ouCode, String userId);

	public List findListDivReportEmpMonth(String ouCode, String userId,
			int workMonth, int workYear, String areaCode, String secCode,
			String workCode);
	
	public List findListReportEmpMonth(String ouCode, String userId,
			int workMonth, int workYear, String areaCode, String secCode,
			String workCode);
	
	public String getStatusBeforeAddEmployee(String ouCode, int workYear,
			int workMonth, String areaCode, String secCode, String workCode,
			String userId);

	public String getStatusUpdateEmployee(String ouCode, int workYear,
			int workMonth, String empCode, String userId);
	
	public String findAreaDescTwo(String areaCode);
	
	public List findListTaReportNew(String ouCode, String userId, int workYear,
			int workYearFrom, int workYearTo, String monthFrom, String monthTo,
			String orgCodeFrom, String orgCodeTo, String empCodeFrom,
			String empCodeTo, String choice);

	public List findListSecTaReportNew(String ouCode, String userId,
			int workYear, int workYearFrom, int workYearTo, String monthFrom,
			String monthTo, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo, String choice);

	public String findAreaDesc(String orgCode);

}