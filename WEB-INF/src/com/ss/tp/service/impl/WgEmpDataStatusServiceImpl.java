package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ss.tp.dao.WgEmpDataStatusDAO;
import com.ss.tp.service.WgEmpDataStatusService;

public class WgEmpDataStatusServiceImpl implements WgEmpDataStatusService,
		Serializable {
	WgEmpDataStatusDAO wgEmpDataStatusDAO;

	public WgEmpDataStatusDAO getWgEmpDataStatusDAO() {
		return wgEmpDataStatusDAO;
	}

	public void setWgEmpDataStatusDAO(WgEmpDataStatusDAO wgEmpDataStatusDAO) {
		this.wgEmpDataStatusDAO = wgEmpDataStatusDAO;
	}

	public List findByCriteria(String ouCode, String empCode, Date statusDate) {
		return this.getWgEmpDataStatusDAO().findByCriteria(ouCode, empCode,
				statusDate);
	}

}
