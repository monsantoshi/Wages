package com.ss.tp.dao;

import java.util.List;

import com.ss.tp.dto.DefaultYearSectionVO;
import com.ss.tp.dto.PeEmployeeScoreVO;

public interface PeEmployeeScoreDAO {
	public PeEmployeeScoreVO findByKey(String ouCode, Long evaYear,
			Long evaTime, String empCode);

	public List findByCriteria(String ouCode, Long evaYear, Long evaTime,
			String areaCode, String secCode, String workCode, String userId,
			int count, int countRecord);

	public void insertPeEmployeeScore(PeEmployeeScoreVO empscorevo)
			throws Exception;

	public void updatePeEmployeeScore(PeEmployeeScoreVO empscorevo)
			throws Exception;

	public void deletePeEmployeeScore(PeEmployeeScoreVO empscorevo)
			throws Exception;

	public Integer countData(String ouCode, Long evaYear, Long evaTime,
			String areaCode, String secCode, String workCode, String userId);

	public List findEmpForInsertUpdate(String userId, String ouCode,
			Long evaYear, Long evaTime, String areaCode, String secCode,
			String workCode);

	public void addList(PeEmployeeScoreVO peEmployeeScoreVO);

	public void clearList();

	public void insertPeEmployeeScoreList() throws Exception;

	public void updatePeEmployeeScoreList() throws Exception;

	public void deletePeEmployeeScoreList() throws Exception;

	public String findFormCode(String ouCode, Long evaYear, Long evaTime,
			String empCode);

	public DefaultYearSectionVO findMaxYearPeriod(String ouCode);
}
