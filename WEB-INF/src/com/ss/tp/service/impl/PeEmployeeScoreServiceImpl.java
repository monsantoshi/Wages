package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.PeEmployeeScoreDAO;
import com.ss.tp.dao.PeEmployeeScoreDetailDAO;
import com.ss.tp.dao.PeEvaluationFormTitleDAO;
import com.ss.tp.dao.PnEmployeeDAO;
import com.ss.tp.dto.DefaultYearSectionVO;
import com.ss.tp.dto.PeEmployeeScoreVO;
import com.ss.tp.service.PeEmployeeScoreService;

public class PeEmployeeScoreServiceImpl implements PeEmployeeScoreService,
		Serializable {
	private PeEmployeeScoreDAO peEmployeeScoreDAO;
	private PeEmployeeScoreDetailDAO peEmployeeScoreDetailDAO;
	private PnEmployeeDAO pnEmployeeDAO;
	private PeEvaluationFormTitleDAO peEvaluationFormTitleDAO;

	public PeEvaluationFormTitleDAO getPeEvaluationFormTitleDAO() {
		return peEvaluationFormTitleDAO;
	}

	public void setPeEvaluationFormTitleDAO(
			PeEvaluationFormTitleDAO peEvaluationFormTitleDAO) {
		this.peEvaluationFormTitleDAO = peEvaluationFormTitleDAO;
	}

	public PeEmployeeScoreDetailDAO getPeEmployeeScoreDetailDAO() {
		return peEmployeeScoreDetailDAO;
	}

	public void setPeEmployeeScoreDetailDAO(
			PeEmployeeScoreDetailDAO peEmployeeScoreDetailDAO) {
		this.peEmployeeScoreDetailDAO = peEmployeeScoreDetailDAO;
	}

	public PnEmployeeDAO getPnEmployeeDAO() {
		return pnEmployeeDAO;
	}

	public void setPnEmployeeDAO(PnEmployeeDAO pnEmployeeDAO) {
		this.pnEmployeeDAO = pnEmployeeDAO;
	}

	public PeEmployeeScoreDAO getPeEmployeeScoreDAO() {
		return peEmployeeScoreDAO;
	}

	public void setPeEmployeeScoreDAO(PeEmployeeScoreDAO peEmployeeScoreDAO) {
		this.peEmployeeScoreDAO = peEmployeeScoreDAO;
	}

	public PeEmployeeScoreVO findByKey(String ouCode, long evaYear,
			long evaTime, String empCode) {
		return this.peEmployeeScoreDAO.findByKey(ouCode, new Long(evaYear),
				new Long(evaTime), empCode);
	}

	public List findByCriteria(String ouCode, long evaYear, long evaTime,
			String areaCode, String secCode, String workCode, String userId,
			int count, int countRecord) {
		return this.peEmployeeScoreDAO.findByCriteria(ouCode,
				new Long(evaYear), new Long(evaTime), areaCode, secCode,
				workCode, userId, count, countRecord);
	}

	public void insertPeEmployeeScore(PeEmployeeScoreVO empscorevo)
			throws Exception {
		this.peEmployeeScoreDAO.insertPeEmployeeScore(empscorevo);
	}

	public void updatePeEmployeeScore(PeEmployeeScoreVO empscorevo)
			throws Exception {
		this.peEmployeeScoreDAO.updatePeEmployeeScore(empscorevo);
	}

	public void deletePeEmployeeScore(PeEmployeeScoreVO empscorevo)
			throws Exception {
		this.peEmployeeScoreDAO.deletePeEmployeeScore(empscorevo);
	}

	public String findEmpName(String ouCode, String empCode) {
		return this.pnEmployeeDAO.findEmpName(ouCode, empCode);
	}

	public String findFormName(String ouCode, String formCode) {
		return this.peEvaluationFormTitleDAO.findFormName(ouCode, formCode);
	}

	public List findTitle(String ouCode, String formCode) {
		return this.peEvaluationFormTitleDAO.findTitle(ouCode, formCode);
	}

	public Integer countData(String ouCode, Long evaYear, Long evaTime,
			String areaCode, String secCode, String workCode, String userId) {
		return this.peEmployeeScoreDAO.countData(ouCode, evaYear, evaTime,
				areaCode, secCode, workCode, userId);
	}

	public List findForm(String ouCode, String formType) {
		return this.peEvaluationFormTitleDAO.findForm(ouCode, formType);
	}

	public List findFormDefault(String ouCode, String formType) {
		return this.peEvaluationFormTitleDAO.findFormDefault(ouCode, formType);
	}

	public List findEmpForInsertUpdate(String userId, String ouCode,
			long evaYear, long evaTime, String areaCode, String secCode,
			String workCode) {
		return this.peEmployeeScoreDAO.findEmpForInsertUpdate(userId, ouCode,
				new Long(evaYear), new Long(evaTime), areaCode, secCode,
				workCode);
	}

	public void addList(PeEmployeeScoreVO peEmployeeScoreVO) {
		this.peEmployeeScoreDAO.addList(peEmployeeScoreVO);
	}

	public void insertPeEmployeeScoreList() {
		try {
			this.peEmployeeScoreDAO.updatePeEmployeeScoreList();
			this.peEmployeeScoreDetailDAO.insertPeEmployeeScoreDetailList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updatePeEmployeeScoreList() {
		try {
			this.peEmployeeScoreDAO.updatePeEmployeeScoreList();
			this.peEmployeeScoreDetailDAO.updatePeEmployeeScoreDetailList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deletePeEmployeeScoreList() {
		try {
			this.peEmployeeScoreDAO.deletePeEmployeeScoreList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String findFormCode(String ouCode, long evaYear, long evaTime,
			String empCode) {
		return this.peEmployeeScoreDAO.findFormCode(ouCode, new Long(evaYear),
				new Long(evaTime), empCode);
	}

	public Double findFormScore(String ouCode, String formCode) {
		return this.peEvaluationFormTitleDAO.findFormScore(ouCode, formCode);
	}

	public DefaultYearSectionVO findMaxYearPeriod(String ouCode) {
		return this.peEmployeeScoreDAO.findMaxYearPeriod(ouCode);
	}
}
