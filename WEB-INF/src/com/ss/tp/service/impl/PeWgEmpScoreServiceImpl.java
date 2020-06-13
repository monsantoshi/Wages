package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.PeWgEmpScoreDAO;
import com.ss.tp.dao.PeWgEmpScoreDetailDAO;
import com.ss.tp.dao.PeEvaluationFormTitleDAO;
import com.ss.tp.dao.WgEmployeeDAO;
import com.ss.tp.dto.DefaultYearSectionVO;
import com.ss.tp.dto.PeWgEmpScoreVO;
import com.ss.tp.service.PeWgEmpScoreService;

public class PeWgEmpScoreServiceImpl implements PeWgEmpScoreService,
		Serializable {
	private PeWgEmpScoreDAO peWgEmpScoreDAO;
	private PeWgEmpScoreDetailDAO peWgEmpScoreDetailDAO;
	private WgEmployeeDAO wgEmployeeDAO;
	private PeEvaluationFormTitleDAO peEvaluationFormTitleDAO;

	public PeEvaluationFormTitleDAO getPeEvaluationFormTitleDAO() {
		return peEvaluationFormTitleDAO;
	}

	public void setPeEvaluationFormTitleDAO(
			PeEvaluationFormTitleDAO peEvaluationFormTitleDAO) {
		this.peEvaluationFormTitleDAO = peEvaluationFormTitleDAO;
	}

	public PeWgEmpScoreDetailDAO getPeWgEmpScoreDetailDAO() {
		return peWgEmpScoreDetailDAO;
	}

	public void setPeWgEmpScoreDetailDAO(
			PeWgEmpScoreDetailDAO PeWgEmpScoreDetailDAO) {
		this.peWgEmpScoreDetailDAO = PeWgEmpScoreDetailDAO;
	}

	public WgEmployeeDAO getWgEmployeeDAO() {
		return wgEmployeeDAO;
	}

	public void setWgEmployeeDAO(WgEmployeeDAO wgEmployeeDAO) {
		this.wgEmployeeDAO = wgEmployeeDAO;
	}

	public PeWgEmpScoreDAO getPeWgEmpScoreDAO() {
		return peWgEmpScoreDAO;
	}

	public void setPeWgEmpScoreDAO(PeWgEmpScoreDAO PeWgEmpScoreDAO) {
		this.peWgEmpScoreDAO = PeWgEmpScoreDAO;
	}

	public PeWgEmpScoreVO findByKey(String ouCode, long evaYear, long evaTime,
			String empCode) {
		return this.peWgEmpScoreDAO.findByKey(ouCode, new Long(evaYear),
				new Long(evaTime), empCode);
	}

	public List findByCriteria(String ouCode, long evaYear, long evaTime,
			String areaCode, String secCode, String workCode, String userId,
			int count, int countRecord) {
		return this.peWgEmpScoreDAO.findByCriteria(ouCode, new Long(evaYear),
				new Long(evaTime), areaCode, secCode, workCode, userId, count,
				countRecord);
	}

	public void insertPeWgEmpScore(PeWgEmpScoreVO empscorevo) throws Exception {
		this.peWgEmpScoreDAO.insertPeWgEmpScore(empscorevo);
	}

	public void updatePeWgEmpScore(PeWgEmpScoreVO empscorevo) throws Exception {
		this.peWgEmpScoreDAO.updatePeWgEmpScore(empscorevo);
	}

	public void deletePeWgEmpScore(PeWgEmpScoreVO empscorevo) throws Exception {
		this.peWgEmpScoreDAO.deletePeWgEmpScore(empscorevo);
	}

	public String findEmpName(String ouCode, String empCode) {
		return this.wgEmployeeDAO.findEmpName(ouCode, empCode);
	}

	public String findFormName(String ouCode, String formCode) {
		return this.peEvaluationFormTitleDAO.findFormName(ouCode, formCode);
	}

	public List findTitle(String ouCode, String formCode) {
		return this.peEvaluationFormTitleDAO.findTitle(ouCode, formCode);
	}

	public Integer countData(String ouCode, Long evaYear, Long evaTime,
			String areaCode, String secCode, String workCode, String userId) {
		return this.peWgEmpScoreDAO.countData(ouCode, evaYear, evaTime,
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
		return this.peWgEmpScoreDAO.findEmpForInsertUpdate(userId, ouCode,
				new Long(evaYear), new Long(evaTime), areaCode, secCode,
				workCode);
	}

	public void addList(PeWgEmpScoreVO PeWgEmpScoreVO) {
		this.peWgEmpScoreDAO.addList(PeWgEmpScoreVO);
	}

	public void insertPeWgEmpScoreList() {
		try {
			this.peWgEmpScoreDAO.updatePeWgEmpScoreList();
			this.peWgEmpScoreDetailDAO.insertPeWgEmpScoreDetailList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updatePeWgEmpScoreList() {
		try {
			this.peWgEmpScoreDAO.updatePeWgEmpScoreList();
			this.peWgEmpScoreDetailDAO.updatePeWgEmpScoreDetailList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deletePeWgEmpScoreList() {
		try {
			this.peWgEmpScoreDAO.deletePeWgEmpScoreList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DefaultYearSectionVO findMaxYearPeriod(String ouCode) {
		return this.peWgEmpScoreDAO.findMaxYearPeriod(ouCode);
	}

}
