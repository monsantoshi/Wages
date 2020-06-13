package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import com.ss.tp.dao.FeeWpayPrWorkDayDAO;
import com.ss.tp.dto.FeeWpayMonthEmpWorkVO;
import com.ss.tp.dto.FeeWpayPayRollEmployeeVO;
import com.ss.tp.dto.FeeWpayPrWorkDayVO;
import com.ss.tp.service.FeeWpayPrWorkDayService;

public class FeeWpayPrWorkDayServiceImpl implements FeeWpayPrWorkDayService, Serializable {

	private FeeWpayPrWorkDayDAO feeWpayPrWorkDayDAO;

	public FeeWpayPrWorkDayDAO getFeeWpayPrWorkDayDAO() {
		return feeWpayPrWorkDayDAO;
	}

	public void setFeeWpayPrWorkDayDAO(FeeWpayPrWorkDayDAO feeWpayPrWorkDayDAO) {
		this.feeWpayPrWorkDayDAO = feeWpayPrWorkDayDAO;
	}

	public void insertPrWorkDay(FeeWpayPrWorkDayVO rwhealthvo) throws Exception {
		this.feeWpayPrWorkDayDAO.insertPrWorkDay(rwhealthvo);
	}

	public void updatePrWorkDay(FeeWpayPrWorkDayVO rwhealthvo) throws Exception {
		this.feeWpayPrWorkDayDAO.updatePrWorkDay(rwhealthvo);
	}

	public void deletePrWorkDay(FeeWpayPrWorkDayVO rwhealthvo) throws Exception {
		this.feeWpayPrWorkDayDAO.deletePrWorkDay(rwhealthvo);
	}

	public void insertPrWorkDays(List rwhealthvolist) throws Exception {
		this.feeWpayPrWorkDayDAO.insertPrWorkDays(rwhealthvolist);
	}

	public List findByCriteria(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo, int count, int countRecord) {
		return this.feeWpayPrWorkDayDAO.findByCriteria(userId, evaOuCode, new Long(
				evaYear), new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo, evaFlagFrom, evaFlagTo, count,
				countRecord);
	}

	public Integer countData(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo) {
		return this.feeWpayPrWorkDayDAO.countData(userId, evaOuCode, new Long(evaYear),
				new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo, evaFlagFrom, evaFlagTo);
	}

	public FeeWpayPayRollEmployeeVO findByEmpCodeDetail(String empCode, String ouCode,
			Long year, Long period) {
		return this.feeWpayPrWorkDayDAO.findByEmpCodeDetail(empCode, ouCode, year,
				period);
	}

	public List findByEmpCodeList(String userId, String ouCode, long yearPr,
			long periodPr, String empCode) {
		return this.feeWpayPrWorkDayDAO.findByEmpCodeList(userId, ouCode, new Long(
				yearPr), new Long(periodPr), empCode);
	}

	
	public List findPrWorkDayReport(String ouCode, Integer year, Integer period,
			String type, String userId) {
		return this.getFeeWpayPrWorkDayDAO().findPrWorkDayReport(ouCode, year, period,
				type, userId);
	}

	public void addList(FeeWpayPrWorkDayVO rpVo, boolean isSave) {
		this.feeWpayPrWorkDayDAO.addList(rpVo);

		if (isSave) {
			try {
				this.feeWpayPrWorkDayDAO.insertAndUpdatePrWorkDays();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void insertAndUpdate() throws Exception {
		this.feeWpayPrWorkDayDAO.insertAndUpdatePrWorkDays();
	}

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.feeWpayPrWorkDayDAO.isConfirmFlag(ouCode, year, period, userId);
	}

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.feeWpayPrWorkDayDAO.canDelete(ouCode, year, period, userId);
	}

	public List findByCriteriaList(String userId, String evaOuCode,
			long evaYear, long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo) {
		return this.feeWpayPrWorkDayDAO.findByCriteriaList(userId, evaOuCode, new Long(
				evaYear), new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo);
	}
	
	public void generate(String userId, String ouCode, Long year, Long period) {
		this.feeWpayPrWorkDayDAO.generate(userId, ouCode, year, period);
	}
	
	
	
	
	
	

	public String findEmpName(String ouCode, String empCode) {
		return this.getFeeWpayPrWorkDayDAO().findEmpName(ouCode, empCode);
	}

	public FeeWpayMonthEmpWorkVO findEmpByKey(int workYear, int workMonth,
			String empCode) {
		return this.getFeeWpayPrWorkDayDAO().findEmpByKey(
				new Integer(workYear), new Integer(workMonth), empCode);
	}
	
	public List findWorkMonth(String ouCode) {
		return this.getFeeWpayPrWorkDayDAO().findWorkMonth(ouCode );
	}


	public List findStatusWork(String ouCode,int workMonth) {
		return this.getFeeWpayPrWorkDayDAO().findStatusWork(ouCode ,new Integer(workMonth));
	}

	public void addPrWorkDay(String ouCode, int year, int month,
			String empCode, String creby, List arrDay, List arrWorkCode,
			List arrWorkSeq,List arrNote) {
		this.getFeeWpayPrWorkDayDAO().addPrWorkDay(ouCode,
				new Integer(year), new Integer(month), empCode, creby, arrDay,
				arrWorkCode, arrWorkSeq,arrNote);
		
	}
	
	public List listMonthEmpWork(String ouCode, int workYear, int workMonth,
			String areaCode, String secCode, String workCode, String userId,
			int maxRowPerPage, int pageNo) {
		return this.getFeeWpayPrWorkDayDAO().listMonthEmpWork(ouCode,
				new Integer(workYear), new Integer(workMonth), areaCode,
				secCode, workCode, userId, new Integer(maxRowPerPage),
				new Integer(pageNo));
	}

	public int getCountTaMonthEmpWork(String ouCode, int workYear,
			int workMonth, String areaCode, String secCode, String workCode,
			String userId) {
		return this.getFeeWpayPrWorkDayDAO().getCountTaMonthEmpWork(ouCode,
				new Integer(workYear), new Integer(workMonth), areaCode,
				secCode, workCode, userId);
	}

	
	public int getCheckCloseThisMonth(String ouCode, int workYear, int workMonth) {
		return this.getFeeWpayPrWorkDayDAO().getCheckCloseThisMonth(ouCode,
				new Integer(workYear), new Integer(workMonth));
	}
	
	public List findListEmpIsNotMonth(String ouCode, int month, int year,
			String userId, String areaCode, String secCode, String workCode) {
		return this.getFeeWpayPrWorkDayDAO().findListEmpIsNotMonth(ouCode,
				new Integer(month), new Integer(year), userId, areaCode,
				secCode, workCode);
	}



	public String deleteTaMonthEmpWork(String ouCode, int workYear,
			int workMonth, List empCode, String userId) {
		return this.getFeeWpayPrWorkDayDAO().deleteTaMonthEmpWork(ouCode,
				new Integer(workYear), new Integer(workMonth), empCode, userId);
	}



	public List findByKey(int workYear, int workMonth, String empCode,
			String ouCode) {
		return this.getFeeWpayPrWorkDayDAO().findByKey(new Integer(workYear),
				new Integer(workMonth), empCode, ouCode);
	}



	public void updateTaMonthEmpWork(String ouCode, int workYear,
			int workMonth, String empCode, String userId, List arrDay,
			List arrWorkCode, List arrWorkSeq, List arrNote) {
		this.getFeeWpayPrWorkDayDAO().updateTaMonthEmpWork(ouCode,
				new Integer(workYear), new Integer(workMonth), empCode, userId,
				arrDay, arrWorkCode, arrWorkSeq, arrNote);
	}



	public void deleteEmpDtl(String ouCode, int workYear, int workMonth,
			String empCode, List arrWorkSeq) {
		this.getFeeWpayPrWorkDayDAO().deleteEmpDtl(ouCode,
				new Integer(workYear), new Integer(workMonth), empCode,
				arrWorkSeq);
	}




	public String addCloseMonthEmpWork(int workYear, int workMonth,
			String ouCode, String userId) {
		return this.getFeeWpayPrWorkDayDAO().addCloseMonthEmpWork(
				new Integer(workYear), new Integer(workMonth), ouCode, userId);
	}



	public List findListDivReportEmpMonth(String ouCode, String userId,
			int workMonth, int workYear, String areaCode, String secCode,
			String workCode) {
		return this.getFeeWpayPrWorkDayDAO().findListDivReportEmpMonth(ouCode,
				userId, new Integer(workMonth), new Integer(workYear),
				areaCode, secCode, workCode);
	}


	public List findListReportEmpMonth(String ouCode, String userId,
			int workMonth, int workYear, String areaCode, String secCode,
			String workCode) {
		return this.getFeeWpayPrWorkDayDAO().findListReportEmpMonth(ouCode,
				userId, new Integer(workMonth), new Integer(workYear),
				areaCode, secCode, workCode);
	}

	
	public String getStatusBeforeAddEmployee(String ouCode, int workYear,
			int workMonth, String areaCode, String secCode, String workCode,
			String userId) {
		return this.getFeeWpayPrWorkDayDAO().getStatusBeforeAddEmployee(ouCode,
				new Integer(workYear), new Integer(workMonth), areaCode,
				secCode, workCode, userId);
	}

	
	public String getStatusUpdateEmployee(String ouCode, int workYear,
			int workMonth, String empCode, String userId) {
		return this.getFeeWpayPrWorkDayDAO().getStatusUpdateEmployee(ouCode,
				new Integer(workYear), new Integer(workMonth), empCode, userId);
	}

	//@Override
	public String findAreaDescTwo(String areaCode) {
		return this.getFeeWpayPrWorkDayDAO().findAreaDescTwo(areaCode);
	}
	public List findListTaReportNew(String ouCode, String userId, int workYear,
			int workYearFrom, int workYearTo, String monthFrom, String monthTo,
			String orgCodeFrom, String orgCodeTo, String empCodeFrom,
			String empCodeTo, String choice) {
		return this.getFeeWpayPrWorkDayDAO().findListTaReportNew(ouCode, userId,
				new Integer(workYear), new Integer(workYearFrom),
				new Integer(workYearTo), monthFrom, monthTo, orgCodeFrom,
				orgCodeTo, empCodeFrom, empCodeTo, choice);
	}

	public List findListSecTaReportNew(String ouCode, String userId,
			int workYear, int workYearFrom, int workYearTo, String monthFrom,
			String monthTo, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo, String choice) {
		return this.getFeeWpayPrWorkDayDAO().findListSecTaReportNew(ouCode, userId,
				new Integer(workYear), new Integer(workYearFrom),
				new Integer(workYearTo), monthFrom, monthTo, orgCodeFrom,
				orgCodeTo, empCodeFrom, empCodeTo, choice);
	}

	public String findAreaDesc(String orgCode) {
		return this.getFeeWpayPrWorkDayDAO().findAreaDesc(orgCode);
	}
}
