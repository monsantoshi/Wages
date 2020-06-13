package com.ss.tp.service.impl;

import java.util.List;

import com.ss.tp.dao.VRwVinaiDAO;
import com.ss.tp.service.VRwVinaiService;

public class VRwVinaiServiceImpl implements VRwVinaiService {
	private VRwVinaiDAO viewRwVinaiDAO;

	public VRwVinaiDAO getViewRwVinaiDAO() {
		return viewRwVinaiDAO;
	}

	public void setViewRwVinaiDAO(VRwVinaiDAO viewRwVinaiDAO) {
		this.viewRwVinaiDAO = viewRwVinaiDAO;
	}

	public List rwVinaiReport(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaFlag) {
		return viewRwVinaiDAO.rwVinaiReport(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), evaFlag);
	}

}
