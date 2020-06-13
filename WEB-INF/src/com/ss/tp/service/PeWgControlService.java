/*
 * Created on 28 �.�. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.service;

/**
 * @author Administrator
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public interface PeWgControlService {
	public String CheckHaveData(String ouCode, String userId, long evaYear,
			long evaTime, String codeSeq);

	public void insertCloseData(String userId, String ouCode, long evaYear,
			long evaTime);
}
