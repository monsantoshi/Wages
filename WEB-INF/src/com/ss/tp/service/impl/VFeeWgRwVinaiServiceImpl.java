package com.ss.tp.service.impl;

import java.util.List;

import com.ss.tp.dao.VFeeWgRwVinaiDAO;
import com.ss.tp.service.VFeeWgRwVinaiService;

public class VFeeWgRwVinaiServiceImpl implements VFeeWgRwVinaiService {
	private VFeeWgRwVinaiDAO viewFeeWgRwVinaiDAO;

	public VFeeWgRwVinaiDAO getViewRwVinaiDAO() {
		return viewFeeWgRwVinaiDAO;
	}

	public void setViewFeeWgRwVinaiDAO(VFeeWgRwVinaiDAO viewFeeWgRwVinaiDAO) {
		this.viewFeeWgRwVinaiDAO = viewFeeWgRwVinaiDAO;
	}

	public List rwVinaiReport(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaFlag) {
		return viewFeeWgRwVinaiDAO.rwVinaiReport(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), evaFlag);
	}

	
	public List rwVinaiReport(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaFlag,String approveFlag) {
		return viewFeeWgRwVinaiDAO.rwVinaiReport(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), evaFlag,approveFlag);
	}
}
