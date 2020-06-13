package com.ss.tp.service;

import com.ss.tp.dto.PeWgEmpScoreDetailVO;

import java.util.List;

public interface PeWgEmpScoreDetailService {
	public List findByCriteriaList(String ouCode, long evaYear, long evaTime,
			String empCode);

	public void insertPeWgEmpScoreDetail(PeWgEmpScoreDetailVO empscorevo)
			throws Exception;

	public void updatePeWgEmpScoreDetail(PeWgEmpScoreDetailVO empscorevo)
			throws Exception;

	public void deletePeWgEmpScoreDetail(PeWgEmpScoreDetailVO empscorevo)
			throws Exception;

	public void addList(PeWgEmpScoreDetailVO empscorevo);
}
