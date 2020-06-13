package com.ss.tp.dao;

import com.ss.tp.dto.PeControlVO;

public interface PeControlDAO {
	public void insertPeControl(PeControlVO pecontrolvo) throws Exception;

	public void updatePeControl(PeControlVO pecontrolvo) throws Exception;

	public String CheckHaveData(String ouCode, String userId, Long evaYear,
			Long evaTime, String codeSeq);

	public boolean CheckDataForInsert(String ouCode, Long evaYear,
			Long evaTime, String userId);

	public boolean CheckDataForUpdate(String ouCode, Long evaYear,
			Long evaTime, Long codeSeq);
}
