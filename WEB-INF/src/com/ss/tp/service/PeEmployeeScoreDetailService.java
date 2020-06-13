package com.ss.tp.service;

import java.util.List;

import com.ss.tp.dto.PeEmployeeScoreDetailVO;

public interface PeEmployeeScoreDetailService {
	public List findByCriteriaList(String ouCode, long evaYear, long evaTime,
			String empCode);

	public void insertPeEmployeeScoreDetail(PeEmployeeScoreDetailVO empscorevo)
			throws Exception;

	public void updatePeEmployeeScoreDetail(PeEmployeeScoreDetailVO empscorevo)
			throws Exception;

	public void deletePeEmployeeScoreDetail(PeEmployeeScoreDetailVO empscorevo)
			throws Exception;

	public void addList(PeEmployeeScoreDetailVO empscorevo);
}
