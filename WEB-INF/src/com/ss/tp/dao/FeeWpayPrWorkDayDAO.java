package com.ss.tp.dao;

import java.util.List;
import java.util.Vector;

import com.ss.tp.dto.FeeMonthEmpWorkVO;
import com.ss.tp.dto.FeeWpayMonthEmpWorkVO;
import com.ss.tp.dto.FeeWpayPayRollEmployeeVO;
import com.ss.tp.dto.FeeWpayPrWorkDayVO;
import com.ss.tp.dto.TaWgMonthEmpWorkVO;

public interface FeeWpayPrWorkDayDAO {
	public void insertPrWorkDay(FeeWpayPrWorkDayVO prworkdayvo) throws Exception;

	public void updatePrWorkDay(FeeWpayPrWorkDayVO prworkdayvo) throws Exception;

	public void deletePrWorkDay(FeeWpayPrWorkDayVO prworkdayvo) throws Exception;

	public void insertPrWorkDays(List prworkdayvolist) throws Exception;

	public List findByCriteria(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo, int count, int countRecord);

	public Integer countData(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo);

	public FeeWpayPayRollEmployeeVO findByEmpCodeDetail(String empCode, String ouCode,
			Long year, Long period);

	public List findByEmpCodeList(String userId, String ouCode, Long yearPr,
			Long periodPr, String empCode);

	public List findPrWorkDayReport(String ouCode, Integer year, Integer period,
			String type, String userId);

	public void addList(FeeWpayPrWorkDayVO prworkdayvo);

	public void insertAndUpdatePrWorkDays() throws Exception;

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception;

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception;

	public List findByCriteriaList(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo);
	
	public void generate(String userId, String ouCode, Long year, Long period);
	
	
	
	
	
	public String findEmpName(String ouCode, String empCode);

	public FeeWpayMonthEmpWorkVO findEmpByKey(Integer workYear, Integer workMonth,
			String empCode);
	
	public List findWorkMonth(String ouCode);
	
	public List findStatusWork(String ouCode,Integer workMonth);

	public void addPrWorkDay(String ouCode, Integer year, Integer month,
			String empCode, String creby, List arrDay, List arrWorkCode,
			List arrWorkSeq, List arrNote);
	
	public List listMonthEmpWork(String ouCode, Integer workYear,
			Integer workMonth, String areaCode, String secCode,
			String workCode, String userId, Integer maxRowPerPage,
			Integer pageNo);

	public int getCountTaMonthEmpWork(String ouCode, Integer workYear,
			Integer workMonth, String areaCode, String secCode,
			String workCode, String userId);

	public int getCheckCloseThisMonth(String ouCode, Integer workYear,
			Integer workMonth);

	public List findListEmpIsNotMonth(String ouCode, Integer month,
			Integer year, String userId, String areaCode, String secCode,
			String workCode);

	public String deleteTaMonthEmpWork(String ouCode, Integer workYear,
			Integer workMonth, List empCode, String userId);

	public List findByKey(Integer workYear, Integer workMonth, String empCode,
			String ouCode);

	public void updateTaMonthEmpWork(String ouCode, Integer workYear,
			Integer workMonth, String empCode, String userId, List arrDay,
			List arrWorkCode, List arrWorkSeq, List arrNote);

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
	
	public String findAreaDescTwo(String areaCode);
	
	public List findListTaReportNew(String ouCode, String userId,
			Integer workYear, Integer workYearFrom, Integer workYearTo,
			String monthFrom, String monthTo, String orgCodeFrom,
			String orgCodeTo, String empCodeFrom, String empCodeTo,
			String choice);

	public List findListSecTaReportNew(String ouCode, String userId,
			Integer workYear, Integer workYearFrom, Integer workYearTo,
			String monthFrom, String monthTo, String orgCodeFrom,
			String orgCodeTo, String empCodeFrom, String empCodeTo,
			String choice);

	public String findAreaDesc(String orgCode);
	
}
