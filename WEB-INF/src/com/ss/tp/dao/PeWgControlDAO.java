/*
 * Created on 28 �.�. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.dao;

import com.ss.tp.dto.PeWgControlVO;

/**
 * @author Administrator
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public interface PeWgControlDAO {
	public void insertPeWgControl(PeWgControlVO pecontrolvo) throws Exception;

	public void updatePeWgControl(PeWgControlVO pecontrolvo) throws Exception;

	public String CheckHaveData(String ouCode, String userId, Long evaYear,
			Long evaTime, String codeSeq);

	public boolean CheckDataForInsert(String ouCode, Long evaYear,
			Long evaTime, String userId);

	public boolean CheckDataForUpdate(String ouCode, Long evaYear,
			Long evaTime, Long codeSeq);
}
