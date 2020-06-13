package com.ss.tp.service;

import java.util.List;

import com.ss.tp.dto.WgDraftVO;

public interface WgDraftService {
	public void insertWgDraft(WgDraftVO wgDraftVO) throws Exception;

	public void updateWgDraft(WgDraftVO wgDraftVO) throws Exception;

	public void deleteWgDraft(WgDraftVO wgDraftVO) throws Exception;

	public void confirmWgDraft(String ouCode, String userId, Integer year)
			throws Exception;

	public String findMaxYear(String xx) throws Exception;

	public List findAllYear(String xx) throws Exception;

	public List findEmployee(String year, String ouCode, String userId)
			throws Exception;

	public List findFromEmployee(String year, String ouCode, String userId,
			String empCode) throws Exception;

	public String checkConfirmWgDraft(String ouCode, String userId, Integer year);

	public List findWgDraftByCriteria(String year, String ouCode,
			String userId, String fCodeSeq, String tCodeSeq, String fEmpCode,
			String tEmpCode, int count, int countRecord) throws Exception;

	public Integer countWgDraftByCriteria(String year, String ouCode,
			String userId, String fCodeSeq, String tCodeSeq, String fEmpCode,
			String tEmpCode) throws Exception;

	public List wgDraftReport(String userId, String ouCode, Integer year);

	public WgDraftVO findWgDraft(String ouCode, String year, String empCode)
			throws Exception;

	public String findDupHisSeq(String ouCode, String year, String perDraft,
			String hisSeq, String draftStatus, String empCode) throws Exception;

	public boolean isConfirmFlag(String ouCode, String year, String userId)
			throws Exception;

	public Integer findAdjustNotOver(String ouCode, String empCode, String year);
}
