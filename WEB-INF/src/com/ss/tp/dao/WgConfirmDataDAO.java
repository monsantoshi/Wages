package com.ss.tp.dao;

import com.ss.tp.dto.WgConfirmDataVO;

public interface WgConfirmDataDAO {
	public void insertWgConfirmData(WgConfirmDataVO vo) throws Exception;

	public boolean isConfirmMasterData(String ouCode, String year,
			String period, String userId) throws Exception;

	public boolean isConfirmWgData(String ouCode, String year, String period,
			String userId) throws Exception;
}