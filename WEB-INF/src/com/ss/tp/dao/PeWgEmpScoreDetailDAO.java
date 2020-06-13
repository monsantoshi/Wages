package com.ss.tp.dao;

import java.util.List;

import com.ss.tp.dto.PeWgEmpScoreDetailVO;

public interface PeWgEmpScoreDetailDAO {
	public List findByCriteria(String ouCode, Long evaYear, Long evaTime,
			String empCode);

	public void insertPeWgEmpScoreDetail(PeWgEmpScoreDetailVO empscorevo)
			throws Exception;

	public void updatePeWgEmpScoreDetail(PeWgEmpScoreDetailVO empscorevo)
			throws Exception;

	public void deletePeWgEmpScoreDetail(PeWgEmpScoreDetailVO empscorevo)
			throws Exception;

	public void addList(PeWgEmpScoreDetailVO empscorevo);

	public void clearList();

	public void insertPeWgEmpScoreDetailList() throws Exception;

	public void updatePeWgEmpScoreDetailList() throws Exception;
}
