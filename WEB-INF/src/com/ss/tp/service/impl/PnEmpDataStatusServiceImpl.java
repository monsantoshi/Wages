package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ss.tp.dao.PnEmpDataStatusDAO;
import com.ss.tp.service.PnEmpDataStatusService;

public class PnEmpDataStatusServiceImpl implements PnEmpDataStatusService,
		Serializable {
	PnEmpDataStatusDAO pnEmpDataStatusDAO;

	public PnEmpDataStatusDAO getPnEmpDataStatusDAO() {
		return pnEmpDataStatusDAO;
	}

	public void setPnEmpDataStatusDAO(PnEmpDataStatusDAO pnEmpDataStatusDAO) {
		this.pnEmpDataStatusDAO = pnEmpDataStatusDAO;
	}

	public List findByCriteria(String ouCode, String empCode, Date statusDate) {
		return this.getPnEmpDataStatusDAO().findByCriteria(ouCode, empCode,
				statusDate);
	}

}
