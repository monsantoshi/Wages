package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.PeWgEmpScoreDetailDAO;
import com.ss.tp.dto.PeWgEmpScoreDetailVO;
import com.ss.tp.service.PeWgEmpScoreDetailService;

public class PeWgEmpScoreDetailServiceImpl implements
		PeWgEmpScoreDetailService, Serializable {
	private PeWgEmpScoreDetailDAO peWgEmpScoreDetailDAO;

	public PeWgEmpScoreDetailDAO getPeWgEmpScoreDetailDAO() {
		return peWgEmpScoreDetailDAO;
	}

	public void setPeWgEmpScoreDetailDAO(
			PeWgEmpScoreDetailDAO peWgEmpScoreDetailDAO) {
		this.peWgEmpScoreDetailDAO = peWgEmpScoreDetailDAO;
	}

	public List findByCriteriaList(String ouCode, long evaYear, long evaTime,
			String empCode) {
		return this.peWgEmpScoreDetailDAO.findByCriteria(ouCode, new Long(
				evaYear), new Long(evaTime), empCode);
	}

	public void insertPeWgEmpScoreDetail(PeWgEmpScoreDetailVO empscorevo)
			throws Exception {
		this.peWgEmpScoreDetailDAO.insertPeWgEmpScoreDetail(empscorevo);
	}

	public void updatePeWgEmpScoreDetail(PeWgEmpScoreDetailVO empscorevo)
			throws Exception {
		this.peWgEmpScoreDetailDAO.updatePeWgEmpScoreDetail(empscorevo);
	}

	public void deletePeWgEmpScoreDetail(PeWgEmpScoreDetailVO empscorevo)
			throws Exception {
		this.peWgEmpScoreDetailDAO.deletePeWgEmpScoreDetail(empscorevo);
	}

	public void addList(PeWgEmpScoreDetailVO empscorevo) {
		this.peWgEmpScoreDetailDAO.addList(empscorevo);
	}
}
