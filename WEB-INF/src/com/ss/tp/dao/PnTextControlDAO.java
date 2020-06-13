package com.ss.tp.dao;

import com.ss.tp.dto.PnTextControlVO;

public interface PnTextControlDAO {
	public void insertPnTextControl(PnTextControlVO pntextcontrolvo)
			throws Exception;

	public void updatePnTextControl(PnTextControlVO pntextcontrolvo)
			throws Exception;

	public String CheckHaveData(String ouCode, String userId, Long evaYear,
			Long evaTime, String codeSeq);

	public boolean CheckDataForInsert(String ouCode, Long evaYear,
			Long evaTime, String userId);

	public boolean CheckDataForUpdate(String ouCode, Long evaYear,
			Long evaTime, Long codeSeq);
}
