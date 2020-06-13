package com.ss.tp.dao;

import java.util.List;

import com.ss.tp.dto.DefaultYearSectionVO;
import com.ss.tp.dto.PeWgEmpScoreVO;

public interface PeWgEmpScoreDAO {
	public PeWgEmpScoreVO findByKey(String ouCode, Long evaYear, Long evaTime,
			String empCode);

	public List findByCriteria(String ouCode, Long evaYear, Long evaTime,
			String areaCode, String secCode, String workCode, String userId,
			int count, int countRecord);

	public void insertPeWgEmpScore(PeWgEmpScoreVO empscorevo) throws Exception;

	public void updatePeWgEmpScore(PeWgEmpScoreVO empscorevo) throws Exception;

	public void deletePeWgEmpScore(PeWgEmpScoreVO empscorevo) throws Exception;

	public Integer countData(String ouCode, Long evaYear, Long evaTime,
			String areaCode, String secCode, String workCode, String userId);

	public List findEmpForInsertUpdate(String userId, String ouCode,
			Long evaYear, Long evaTime, String areaCode, String secCode,
			String workCode);

	public void addList(PeWgEmpScoreVO peWgEmpScoreVO);

	public void clearList();

	public void insertPeWgEmpScoreList() throws Exception;

	public void updatePeWgEmpScoreList() throws Exception;

	public void deletePeWgEmpScoreList() throws Exception;

	public DefaultYearSectionVO findMaxYearPeriod(String ouCode);
}
