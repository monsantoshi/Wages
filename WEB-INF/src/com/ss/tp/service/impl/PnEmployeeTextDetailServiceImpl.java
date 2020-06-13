package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.PnEmployeeTextDetailDAO;
import com.ss.tp.dto.PnEmployeeTextDetailVO;
import com.ss.tp.service.PnEmployeeTextDetailService;

public class PnEmployeeTextDetailServiceImpl implements
		PnEmployeeTextDetailService, Serializable {
	private PnEmployeeTextDetailDAO pnEmployeeTextDetailDAO;

	public PnEmployeeTextDetailDAO getPnEmployeeTextDetailDAO() {
		return pnEmployeeTextDetailDAO;
	}

	public void setPnEmployeeTextDetailDAO(
			PnEmployeeTextDetailDAO pnEmployeeTextDetailDAO) {
		this.pnEmployeeTextDetailDAO = pnEmployeeTextDetailDAO;
	}

	public List findByCriteriaList(String ouCode, long evaYear, long evaTime,
			String empCode) {
		// TODO Auto-generated method stub
		return this.pnEmployeeTextDetailDAO.findByCriteria(ouCode, new Long(
				evaYear), new Long(evaTime), empCode);
	}

	public void insertPnEmployeeTextDetail(PnEmployeeTextDetailVO emptextvo)
			throws Exception {
		// TODO Auto-generated method stub
		this.pnEmployeeTextDetailDAO.insertPnEmployeeTextDetail(emptextvo);
	}

	public void updatePnEmployeeTextDetail(PnEmployeeTextDetailVO emptextvo)
			throws Exception {
		// TODO Auto-generated method stub
		this.pnEmployeeTextDetailDAO.updatePnEmployeeTextDetail(emptextvo);
	}

	public void deletePnEmployeeTextDetail(PnEmployeeTextDetailVO emptextvo)
			throws Exception {
		// TODO Auto-generated method stub
		this.pnEmployeeTextDetailDAO.deletePnEmployeeTextDetail(emptextvo);
	}

	public void addList(PnEmployeeTextDetailVO emptextvo) {
		// TODO Auto-generated method stub
		this.pnEmployeeTextDetailDAO.addList(emptextvo);

	}

}
