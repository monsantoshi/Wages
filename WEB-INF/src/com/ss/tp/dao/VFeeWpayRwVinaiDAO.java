package com.ss.tp.dao;

import java.util.List;

public interface VFeeWpayRwVinaiDAO {
	public List rwVinaiReport(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaFlag);
	
	public List rwVinaiReport(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaFlag,String approveFlag);
	
}
