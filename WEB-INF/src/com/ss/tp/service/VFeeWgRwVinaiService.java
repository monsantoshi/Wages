/*
 * Created on 27 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.service;

import java.util.List;

/**
 * @author Kiet
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public interface VFeeWgRwVinaiService {
	public List rwVinaiReport(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaFlag);
	
	public List rwVinaiReport(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaFlag,String approveFlag);
}
