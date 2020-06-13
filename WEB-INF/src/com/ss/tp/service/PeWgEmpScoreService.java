package com.ss.tp.service;

import java.util.List;

import com.ss.tp.dto.DefaultYearSectionVO;
import com.ss.tp.dto.PeWgEmpScoreVO;

public interface PeWgEmpScoreService {
	public PeWgEmpScoreVO findByKey(String ouCode, long evaYear, long evaTime,
			String empCode);

	public List findByCriteria(String ouCode, long evaYear, long evaTime,
			String areaCode, String secCode, String workCode, String userId,
			int count, int countRecord);

	public void insertPeWgEmpScore(PeWgEmpScoreVO empscorevo) throws Exception;

	public void updatePeWgEmpScore(PeWgEmpScoreVO empscorevo) throws Exception;

	public void deletePeWgEmpScore(PeWgEmpScoreVO empscorevo) throws Exception;

	public String findEmpName(String ouCode, String empCode);

	public String findFormName(String ouCode, String formCode);

	public List findForm(String ouCode, String formType);

	public List findFormDefault(String ouCode, String formType);

	public List findTitle(String ouCode, String formCode);

	public Integer countData(String ouCode, Long evaYear, Long evaTime,
			String areaCode, String secCode, String workCode, String userId);

	public List findEmpForInsertUpdate(String userId, String ouCode,
			long evaYear, long evaTime, String areaCode, String secCode,
			String workCode);

	public void addList(PeWgEmpScoreVO PeWgEmpScoreVO);

	public void insertPeWgEmpScoreList();

	public void updatePeWgEmpScoreList();

	public void deletePeWgEmpScoreList();

	public DefaultYearSectionVO findMaxYearPeriod(String ouCode);

}
