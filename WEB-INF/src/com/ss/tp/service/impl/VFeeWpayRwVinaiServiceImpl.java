package com.ss.tp.service.impl;

import java.util.List;

import com.ss.tp.dao.VFeeWpayRwVinaiDAO;
import com.ss.tp.service.VFeeWpayRwVinaiService;

public class VFeeWpayRwVinaiServiceImpl implements VFeeWpayRwVinaiService {
	private VFeeWpayRwVinaiDAO viewFeeWpayRwVinaiDAO;

	public VFeeWpayRwVinaiDAO getViewFeeWpayRwVinaiDAO() {
		return viewFeeWpayRwVinaiDAO;
	}

	public void setViewFeeWpayRwVinaiDAO(VFeeWpayRwVinaiDAO viewFeeWpayRwVinaiDAO) {
		this.viewFeeWpayRwVinaiDAO = viewFeeWpayRwVinaiDAO;
	}

	public List rwVinaiReport(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaFlag) {
		return viewFeeWpayRwVinaiDAO.rwVinaiReport(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), evaFlag);
	}
	
	public List rwVinaiReport(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaFlag,String approveFlag) {
		return viewFeeWpayRwVinaiDAO.rwVinaiReport(userId, evaOuCode,
				new Long(evaYear), new Long(evaPeriod), evaFlag,approveFlag);
	}

}
