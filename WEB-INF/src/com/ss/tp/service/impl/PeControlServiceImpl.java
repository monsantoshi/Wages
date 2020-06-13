package com.ss.tp.service.impl;

import java.io.Serializable;
import com.ss.tp.dao.PeControlDAO;
import com.ss.tp.dao.SuUserOrganizationDAO;
import com.ss.tp.dto.PeControlVO;
import com.ss.tp.service.PeControlService;

public class PeControlServiceImpl implements PeControlService, Serializable {
	private PeControlDAO peControlDAO;
	private SuUserOrganizationDAO suUserOrganizationDAO;

	public SuUserOrganizationDAO getSuUserOrganizationDAO() {
		return suUserOrganizationDAO;
	}

	public void setSuUserOrganizationDAO(
			SuUserOrganizationDAO suUserOrganizationDAO) {
		this.suUserOrganizationDAO = suUserOrganizationDAO;
	}

	public PeControlDAO getPeControlDAO() {
		return peControlDAO;
	}

	public void setPeControlDAO(PeControlDAO peControlDAO) {
		this.peControlDAO = peControlDAO;
	}

	public String CheckHaveData(String ouCode, String userId, long evaYear,
			long evaTime, String codeSeq) {
		return this.peControlDAO.CheckHaveData(ouCode, userId,
				new Long(evaYear), new Long(evaTime), codeSeq);

	}

	public void insertCloseData(String userId, String ouCode, long evaYear,
			long evaTime) {
		try {
			PeControlVO pecontrolvo = new PeControlVO();
			boolean insertData = this.peControlDAO.CheckDataForInsert(ouCode,
					new Long(evaYear), new Long(evaTime), userId);
			if (insertData) {

				pecontrolvo.setOuCode(ouCode);
				pecontrolvo.setEvaYear(new Long(evaYear));
				pecontrolvo.setEvaTime(new Long(evaTime));
				pecontrolvo.setUserId(userId);
				pecontrolvo.setFlagClose("Y");
				pecontrolvo.setCreBy(userId);
				pecontrolvo.setUpdBy(userId);
				this.peControlDAO.insertPeControl(pecontrolvo);
			} else {
				pecontrolvo.setOuCode(ouCode);
				pecontrolvo.setEvaYear(new Long(evaYear));
				pecontrolvo.setEvaTime(new Long(evaTime));
				pecontrolvo.setUserId(userId);
				pecontrolvo.setUpdBy(userId);
				this.peControlDAO.updatePeControl(pecontrolvo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * public void insertCloseData(String userId,String ouCode,long evaYear,long
	 * evaTime){ List orgList; try { orgList =
	 * this.suUserOrganizationDAO.findOrganizationByUserIdAndOuCode(userId,
	 * ouCode); for(Iterator iter = orgList.iterator();iter.hasNext();){
	 * PnOrganizationVO org = (PnOrganizationVO)iter.next(); PeControlVO
	 * pecontrolvo = new PeControlVO(); boolean insertData =
	 * this.peControlDAO.CheckDataForInsert(ouCode, new Long(evaYear), new
	 * Long(evaTime), org.getCodeSeq()); if (insertData) {
	 * pecontrolvo.setOuCode(ouCode); pecontrolvo.setEvaYear(new Long(evaYear));
	 * pecontrolvo.setEvaTime(new Long(evaTime));
	 * pecontrolvo.setCodeSeq(org.getCodeSeq()); pecontrolvo.setFlagClose("Y");
	 * pecontrolvo.setCreBy(userId); pecontrolvo.setUpdBy(userId);
	 * this.peControlDAO.insertPeControl(pecontrolvo); } else{ boolean
	 * updateData = this.peControlDAO.CheckDataForUpdate(ouCode, new
	 * Long(evaYear), new Long(evaTime), org.getCodeSeq()); if (updateData) {
	 * pecontrolvo.setOuCode(ouCode); pecontrolvo.setEvaYear(new Long(evaYear));
	 * pecontrolvo.setEvaTime(new Long(evaTime));
	 * pecontrolvo.setCodeSeq(org.getCodeSeq()); pecontrolvo.setUpdBy(userId);
	 * this.peControlDAO.updatePeControl(pecontrolvo); } } } } catch (Exception
	 * e) { e.printStackTrace(); } }
	 */
}
