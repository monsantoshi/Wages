package com.ss.tp.dao;

import com.ss.tp.dto.PeEmployeeScoreDetailVO;

import java.util.List;

public interface PeEmployeeScoreDetailDAO {
	public List findByCriteria(String ouCode, Long evaYear, Long evaTime,
			String empCode);

	public void insertPeEmployeeScoreDetail(PeEmployeeScoreDetailVO empscorevo)
			throws Exception;

	public void updatePeEmployeeScoreDetail(PeEmployeeScoreDetailVO empscorevo)
			throws Exception;

	public void deletePeEmployeeScoreDetail(PeEmployeeScoreDetailVO empscorevo)
			throws Exception;

	public void addList(PeEmployeeScoreDetailVO empscorevo);

	public void clearList();

	public void insertPeEmployeeScoreDetailList() throws Exception;

	public void updatePeEmployeeScoreDetailList() throws Exception;
}
