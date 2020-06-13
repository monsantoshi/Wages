package com.ss.tp.service;

public interface PnTextControlService {
	public String CheckHaveData(String ouCode, String userId, long evaYear,
			long evaTime, String codeSeq);

	public void insertCloseData(String userId, String ouCode, long evaYear,
			long evaTime);
}
