package com.ss.tp.service.impl;

import java.util.List;

import com.ss.tp.dao.PrEmployeeDAO;
import com.ss.tp.dto.PrEmployeeTextVO;
import com.ss.tp.dto.PrEmployeeVO;
import com.ss.tp.service.PrEmployeeService;






public class PrEmployeeServiceImpl implements PrEmployeeService {

	private PrEmployeeDAO prEmployeeDAO;

	public PrEmployeeDAO getPrEmployeeDAO() {
		return prEmployeeDAO;
	}

	public void setPrEmployeeDAO(PrEmployeeDAO prEmployeeDAO) {
		this.prEmployeeDAO = prEmployeeDAO;
	}

	public String[] findMaxYearPeriod(String ouCode) {
		return this.prEmployeeDAO.findMaxYearPeriod(ouCode);
	}

	public List findByCriteria(String ouCode, String year, String period,
			String userId, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo, int page, int recPerPage)
			throws Exception {
		return this.prEmployeeDAO.findByCriteria(ouCode, year, period, userId,
				orgCodeFrom, orgCodeTo, empCodeFrom, empCodeTo, page,
				recPerPage);
	}

	public Integer getCountByCriteria(String ouCode, String year,
			String period, String userId, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo) throws Exception {
		return this.prEmployeeDAO.getCountByCriteria(ouCode, year, period,
				userId, orgCodeFrom, orgCodeTo, empCodeFrom, empCodeTo);
	}

	public PrEmployeeVO findPrEmployee(String ouCode, String year,
			String period, String empCode, String userId) {
		return this.prEmployeeDAO.findByCriteria(ouCode, year, period, empCode,
				userId);
	}

	public String[] findMaxPeriodName(String ouCode, String year, String period) {
		return this.prEmployeeDAO.findMaxPeriodName(ouCode, year, period);
	}

	public List findByCriteriaRWQY001(String ouCode, String year,
			String period, String userId, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo, int page, int recPerPage)
			throws Exception {
		return this.prEmployeeDAO.findByCriteriaRWQY001(ouCode, year, period,
				userId, orgCodeFrom, orgCodeTo, empCodeFrom, empCodeTo, page,
				recPerPage);
	}

	public Integer getCountByCriteriaRWQY001(String ouCode, String year,
			String period, String userId, String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo) throws Exception {
		return this.prEmployeeDAO.getCountByCriteriaRWQY001(ouCode, year,
				period, userId, orgCodeFrom, orgCodeTo, empCodeFrom, empCodeTo);
	}

	public List findDetailPaySlip(String ouCode, String userId, String year,
			String period) {
		return this.prEmployeeDAO.findDetailPaySlip(ouCode, userId, year,
				period);
	}

	public void updatePrEmpByPrEmpText(PrEmployeeTextVO prEmpTextVo)
			throws Exception {
		this.prEmployeeDAO.updatePrEmpByPrEmpText(prEmpTextVo);
	}

	public List findDetailPaySlipOverTime(String ouCode, String userId,
			String year, String period, String divCode, String areaCode,
			String secCode) throws Exception {
		return this.prEmployeeDAO.findDetailPaySlipOverTime(ouCode, userId,
				year, period, divCode, areaCode, secCode);
	}

	public List findDetailPaySlipPremium(String ouCode, String userId,
			String year, String period, String divCode, String areaCode,
			String secCode) throws Exception {
		return this.prEmployeeDAO.findDetailPaySlipPremium(ouCode, userId,
				year, period, divCode, areaCode, secCode);
	}

	public List findDetailPaySlipHealth(String ouCode, String userId,
			String year, String period, String divCode, String areaCode,
			String secCode) throws Exception {
		return this.prEmployeeDAO.findDetailPaySlipHealth(ouCode, userId, year,
				period, divCode, areaCode, secCode);
	}

	public List findDetailPaySlipDanger(String ouCode, String userId,
			String year, String period, String divCode, String areaCode,
			String secCode) throws Exception {
		return this.prEmployeeDAO.findDetailPaySlipDanger(ouCode, userId, year,
				period, divCode, areaCode, secCode);
	}

	public List findDetailPaySlipOverTime(String ouCode, String userId,
			String year, String period, String divCode) throws Exception {
		return this.prEmployeeDAO.findDetailPaySlipOverTime(ouCode, userId,
				year, period, divCode);
	}

	public List findDetailPaySlipPremium(String ouCode, String userId,
			String year, String period, String divCode) throws Exception {
		return this.prEmployeeDAO.findDetailPaySlipPremium(ouCode, userId,
				year, period, divCode);
	}

	public List findDetailPaySlipHealth(String ouCode, String userId,
			String year, String period, String divCode) throws Exception {
		return this.prEmployeeDAO.findDetailPaySlipHealth(ouCode, userId, year,
				period, divCode);
	}

	public List findDetailPaySlipDanger(String ouCode, String userId,
			String year, String period, String divCode) throws Exception {
		return this.prEmployeeDAO.findDetailPaySlipDanger(ouCode, userId, year,
				period, divCode);
	}
	
	
	public List findByCriteriaTax(String ouCode,int evaYear, int evaPeriod)
			throws Exception {
		return this.prEmployeeDAO.findByCriteriaTax(ouCode, new Integer(evaYear),new Integer(evaPeriod));
	}
	
	public List findPrDailyRandom(String ouCode,int evaYear, int evaPeriod)
			throws Exception {
		return this.prEmployeeDAO.findPrDailyRandom(ouCode, new Integer(evaYear),new Integer(evaPeriod));
	}
}
