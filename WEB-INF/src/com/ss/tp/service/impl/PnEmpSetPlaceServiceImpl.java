package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.PnEmpSetPlaceDAO;
import com.ss.tp.dto.PnEmpSetPlaceVO;
import com.ss.tp.service.PnEmpSetPlaceService;

public class PnEmpSetPlaceServiceImpl implements PnEmpSetPlaceService,
		Serializable {
	private PnEmpSetPlaceDAO pnEmpSetPlaceDAO;

	public PnEmpSetPlaceDAO getPnEmpSetPlaceDAO() {
		return pnEmpSetPlaceDAO;
	}

	public void setPnEmpSetPlaceDAO(PnEmpSetPlaceDAO pnEmpSetPlaceDAO) {
		this.pnEmpSetPlaceDAO = pnEmpSetPlaceDAO;
	}

	public List findByCriteriaList(String ouCode, String moveStatus,
			String empCode, String year) {
		return this.pnEmpSetPlaceDAO.findByCriteria(ouCode, new String(
				moveStatus), empCode, year);
	}

	public void insertPnEmpSetPlace(PnEmpSetPlaceVO pnempsetplacevo)
			throws Exception {
		this.pnEmpSetPlaceDAO.insertPnEmpSetPlace(pnempsetplacevo);
	}

	public void updatePnEmpSetPlace(PnEmpSetPlaceVO pnempsetplacevo)
			throws Exception {
		this.pnEmpSetPlaceDAO.updatePnEmpSetPlace(pnempsetplacevo);
	}

	public void deletePnEmpSetPlace(PnEmpSetPlaceVO pnempsetplacevo)
			throws Exception {
		this.pnEmpSetPlaceDAO.deletePnEmpSetPlace(pnempsetplacevo);
	}

	public void addList(PnEmpSetPlaceVO pnempsetplacevo) {
		this.pnEmpSetPlaceDAO.addList(pnempsetplacevo);
	}
}
