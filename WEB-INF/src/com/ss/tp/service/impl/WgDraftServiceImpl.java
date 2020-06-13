package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.WgDraftDAO;
import com.ss.tp.dto.WgDraftVO;
import com.ss.tp.service.WgDraftService;

public class WgDraftServiceImpl implements WgDraftService, Serializable {
	private WgDraftDAO wgDraftDAO;

	public WgDraftDAO getWgDraftDAO() {
		return wgDraftDAO;
	}

	public void setWgDraftDAO(WgDraftDAO wgDraftDAO) {
		this.wgDraftDAO = wgDraftDAO;
	}

	public void insertWgDraft(WgDraftVO wgDraftVO) throws Exception {
		this.wgDraftDAO.insertWgDraft(wgDraftVO);
	}

	public void updateWgDraft(WgDraftVO wgDraftVO) throws Exception {
		this.wgDraftDAO.updateWgDraft(wgDraftVO);
	}

	public void deleteWgDraft(WgDraftVO wgDraftVO) throws Exception {
		this.wgDraftDAO.deleteWgDraft(wgDraftVO);
	}

	public void confirmWgDraft(String ouCode, String userId, Integer year)
			throws Exception {
		this.wgDraftDAO.confirmWgDraft(ouCode, userId, year);
	}

	public String findMaxYear(String xx) throws Exception {
		return this.wgDraftDAO.findMaxYear();
	}

	public List findEmployee(String year, String ouCode, String userId)
			throws Exception {
		return this.wgDraftDAO.findEmployee(year, ouCode, userId);
	}

	public List findFromEmployee(String year, String ouCode, String userId,
			String empCode) throws Exception {
		return this.wgDraftDAO.findFromEmployee(year, ouCode, userId, empCode);
	}

	public String checkConfirmWgDraft(String ouCode, String userId, Integer year) {
		return this.wgDraftDAO.checkConfirmWgDraft(ouCode, userId, year);
	}

	public List wgDraftReport(String userId, String ouCode, Integer year) {
		return this.wgDraftDAO.wgDraftReport(userId, ouCode, year);
	}

	public List findWgDraftByCriteria(String year, String ouCode,
			String userId, String fCodeSeq, String tCodeSeq, String fEmpCode,
			String tEmpCode, int count, int countRecord) throws Exception {
		return this.wgDraftDAO.findWgDraftByCriteria(year, ouCode, userId,
				fCodeSeq, tCodeSeq, fEmpCode, tEmpCode, count, countRecord);
	}

	public Integer countWgDraftByCriteria(String year, String ouCode,
			String userId, String fCodeSeq, String tCodeSeq, String fEmpCode,
			String tEmpCode) throws Exception {
		return this.wgDraftDAO.countWgDraftByCriteria(year, ouCode, userId,
				fCodeSeq, tCodeSeq, fEmpCode, tEmpCode);
	}

	public WgDraftVO findWgDraft(String ouCode, String year, String empCode)
			throws Exception {
		return this.wgDraftDAO.findWgDraft(ouCode, year, empCode);
	}

	public String findDupHisSeq(String ouCode, String year, String perDraft,
			String hisSeq, String draftStatus, String empCode) throws Exception {
		return this.wgDraftDAO.findDupHisSeq(ouCode, year, perDraft, hisSeq,
				draftStatus, empCode);
	}

	public boolean isConfirmFlag(String ouCode, String year, String userId)
			throws Exception {
		return this.wgDraftDAO.isConfirmFlag(ouCode, year, userId);
	}

	public List findAllYear(String xx) throws Exception {
		return this.wgDraftDAO.findAllYear();
	}

	public Integer findAdjustNotOver(String ouCode, String empCode, String year) {
		return this.wgDraftDAO.findAdjustNotOver(ouCode, empCode, year);
	}
}
