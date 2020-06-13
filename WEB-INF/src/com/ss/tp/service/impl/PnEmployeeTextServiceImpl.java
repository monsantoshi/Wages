package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.PnEmployeeTextDAO;
import com.ss.tp.dao.PnEmployeeTextDetailDAO;
import com.ss.tp.dao.PeEvaluationFormTitleDAO;
import com.ss.tp.dao.PnEmployeeDAO;
import com.ss.tp.dto.DefaultYearSectionPnTextVO;
import com.ss.tp.dto.PnEmployeeTextVO;
import com.ss.tp.service.PnEmployeeTextService;

public class PnEmployeeTextServiceImpl implements PnEmployeeTextService,
		Serializable {
	private PnEmployeeTextDAO pnEmployeeTextDAO;
	private PnEmployeeTextDetailDAO pnEmployeeTextDetailDAO;
	private PnEmployeeDAO pnEmployeeDAO;
	private PeEvaluationFormTitleDAO peEvaluationFormTitleDAO;

	public PnEmployeeTextDetailDAO getPnEmployeeTextDetailDAO() {
		return pnEmployeeTextDetailDAO;
	}

	public void setPnEmployeeTextDetailDAO(
			PnEmployeeTextDetailDAO pnEmployeeTextDetailDAO) {
		this.pnEmployeeTextDetailDAO = pnEmployeeTextDetailDAO;
	}

	public PnEmployeeTextDAO getPnEmployeeTextDAO() {
		return pnEmployeeTextDAO;
	}

	public void setPnEmployeeTextDAO(PnEmployeeTextDAO pnEmployeeTextDAO) {
		this.pnEmployeeTextDAO = pnEmployeeTextDAO;
	}

	public PnEmployeeDAO getPnEmployeeDAO() {
		return pnEmployeeDAO;
	}

	public void setPnEmployeeDAO(PnEmployeeDAO pnEmployeeDAO) {
		this.pnEmployeeDAO = pnEmployeeDAO;
	}

	public String findEmpName(String ouCode, String empCode) {
		return this.pnEmployeeDAO.findEmpName(ouCode, empCode);
	}

	public String findFormName(String ouCode, String formCode) {
		return this.peEvaluationFormTitleDAO.findFormName(ouCode, formCode);
	}

	public PnEmployeeTextVO findByKey(String ouCode, long evaYear,
			long evaTime, String empCode) {
		// TODO Auto-generated method stub
		return this.pnEmployeeTextDAO.findByKey(ouCode, new Long(evaYear),
				new Long(evaTime), empCode);
	}

	public List findByCriteria(String ouCode, long evaYear, long evaTime,
			String areaCode, String secCode, String workCode, String userId,
			int count, int countRecord) {
		// TODO Auto-generated method stub
		return this.pnEmployeeTextDAO.findByCriteria(ouCode, new Long(evaYear),
				new Long(evaTime), areaCode, secCode, workCode, userId, count,
				countRecord);
	}

	public void insertPnEmployeeText(PnEmployeeTextVO emptextvo)
			throws Exception {
		// TODO Auto-generated method stub
		this.pnEmployeeTextDAO.insertPnEmployeeText(emptextvo);
	}

	public void updatePnEmployeeText(PnEmployeeTextVO emptextvo)
			throws Exception {
		// TODO Auto-generated method stub
		this.pnEmployeeTextDAO.updatePnEmployeeText(emptextvo);
	}

	public void deletePnEmployeeText(PnEmployeeTextVO emptextvo)
			throws Exception {
		// TODO Auto-generated method stub
		this.pnEmployeeTextDAO.deletePnEmployeeText(emptextvo);
	}

	public Integer countData(String ouCode, Long evaYear, Long evaTime,
			String areaCode, String secCode, String workCode, String userId) {
		// TODO Auto-generated method stub
		return this.pnEmployeeTextDAO.countData(ouCode, evaYear, evaTime,
				areaCode, secCode, workCode, userId);
	}

	public List findEmpForInsertUpdate(String userId, String ouCode,
			long evaYear, long evaTime, String areaCode, String secCode,
			String workCode) {
		// TODO Auto-generated method stub
		return this.pnEmployeeTextDAO.findEmpForInsertUpdate(userId, ouCode,
				new Long(evaYear), new Long(evaTime), areaCode, secCode,
				workCode);
	}

	public void addList(PnEmployeeTextVO pnEmployeeTextVO) {
		// TODO Auto-generated method stub
		this.pnEmployeeTextDAO.addList(pnEmployeeTextVO);
	}

	public void insertPnEmployeeTextList() {
		// TODO Auto-generated method stub
		try {
			this.pnEmployeeTextDAO.updatePnEmployeeTextList();
			this.pnEmployeeTextDetailDAO.insertPnEmployeeTextDetailList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updatePnEmployeeTextList() {
		// TODO Auto-generated method stub
		try {
			this.pnEmployeeTextDAO.updatePnEmployeeTextList();
			this.pnEmployeeTextDetailDAO.updatePnEmployeeTextDetailList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deletePnEmployeeTextList() {
		// TODO Auto-generated method stub
		try {
			this.pnEmployeeTextDAO.deletePnEmployeeTextList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String findFormCode(String ouCode, long evaYear, long evaTime,
			String empCode) {
		return this.pnEmployeeTextDAO.findFormCode(ouCode, new Long(evaYear),
				new Long(evaTime), empCode);
	}

	public DefaultYearSectionPnTextVO findMaxYearPeriod(String ouCode) {
		// TODO Auto-generated method stub
		return this.pnEmployeeTextDAO.findMaxYearPeriod(ouCode);
	}

}
