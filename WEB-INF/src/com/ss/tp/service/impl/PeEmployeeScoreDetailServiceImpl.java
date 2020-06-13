package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.PeEmployeeScoreDetailDAO;
import com.ss.tp.dto.PeEmployeeScoreDetailVO;
import com.ss.tp.service.PeEmployeeScoreDetailService;

public class PeEmployeeScoreDetailServiceImpl implements
		PeEmployeeScoreDetailService, Serializable {
	private PeEmployeeScoreDetailDAO peEmployeeScoreDetailDAO;

	public PeEmployeeScoreDetailDAO getPeEmployeeScoreDetailDAO() {
		return peEmployeeScoreDetailDAO;
	}

	public void setPeEmployeeScoreDetailDAO(
			PeEmployeeScoreDetailDAO peEmployeeScoreDetailDAO) {
		this.peEmployeeScoreDetailDAO = peEmployeeScoreDetailDAO;
	}

	public List findByCriteriaList(String ouCode, long evaYear, long evaTime,
			String empCode) {
		return this.peEmployeeScoreDetailDAO.findByCriteria(ouCode, new Long(
				evaYear), new Long(evaTime), empCode);
	}

	public void insertPeEmployeeScoreDetail(PeEmployeeScoreDetailVO empscorevo)
			throws Exception {
		this.peEmployeeScoreDetailDAO.insertPeEmployeeScoreDetail(empscorevo);
	}

	public void updatePeEmployeeScoreDetail(PeEmployeeScoreDetailVO empscorevo)
			throws Exception {
		this.peEmployeeScoreDetailDAO.updatePeEmployeeScoreDetail(empscorevo);
	}

	public void deletePeEmployeeScoreDetail(PeEmployeeScoreDetailVO empscorevo)
			throws Exception {
		this.peEmployeeScoreDetailDAO.deletePeEmployeeScoreDetail(empscorevo);
	}

	public void addList(PeEmployeeScoreDetailVO empscorevo) {
		this.peEmployeeScoreDetailDAO.addList(empscorevo);
	}
}
