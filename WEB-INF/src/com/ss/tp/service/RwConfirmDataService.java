package com.ss.tp.service;

import com.ss.tp.dto.RwConfirmDataVO;

public interface RwConfirmDataService {
	public void insertRwConfirmData(RwConfirmDataVO vo) throws Exception;

	public boolean isConfirmMasterData(String ouCode, String year,
			String period, String userId) throws Exception;

	public boolean isConfirmRwData(String ouCode, String year, String period,
			String userId) throws Exception;
}