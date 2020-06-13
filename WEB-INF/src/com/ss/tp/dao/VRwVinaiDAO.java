package com.ss.tp.dao;

import java.util.List;

public interface VRwVinaiDAO {
	public List rwVinaiReport(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaFlag);
}
