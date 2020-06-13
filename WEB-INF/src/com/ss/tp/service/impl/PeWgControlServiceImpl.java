/*
 * Created on 28 �.�. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.service.impl;

import java.io.Serializable;
import com.ss.tp.dao.PeWgControlDAO;
import com.ss.tp.dao.SuUserOrganizationDAO;
import com.ss.tp.dto.PeWgControlVO;
import com.ss.tp.service.PeWgControlService;

/**
 * @author Administrator
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class PeWgControlServiceImpl implements PeWgControlService, Serializable {
	private PeWgControlDAO peWgControlDAO;
	private SuUserOrganizationDAO suUserOrganizationDAO;

	public SuUserOrganizationDAO getSuUserOrganizationDAO() {
		return suUserOrganizationDAO;
	}

	public void setSuUserOrganizationDAO(
			SuUserOrganizationDAO suUserOrganizationDAO) {
		this.suUserOrganizationDAO = suUserOrganizationDAO;
	}

	public PeWgControlDAO getPeWGControlDAO() {
		return peWgControlDAO;
	}

	public void setPeWgControlDAO(PeWgControlDAO peWgControlDAO) {
		this.peWgControlDAO = peWgControlDAO;
	}

	public String CheckHaveData(String ouCode, String userId, long evaYear,
			long evaTime, String codeSeq) {
		return this.peWgControlDAO.CheckHaveData(ouCode, userId, new Long(
				evaYear), new Long(evaTime), codeSeq);

	}

	public void insertCloseData(String userId, String ouCode, long evaYear,
			long evaTime) {
		try {
			PeWgControlVO pecontrolvo = new PeWgControlVO();
			boolean insertData = this.peWgControlDAO.CheckDataForInsert(ouCode,
					new Long(evaYear), new Long(evaTime), userId);
			if (insertData) {

				pecontrolvo.setOuCode(ouCode);
				pecontrolvo.setEvaYear(new Long(evaYear));
				pecontrolvo.setEvaTime(new Long(evaTime));
				pecontrolvo.setUserId(userId);
				pecontrolvo.setFlagClose("Y");
				pecontrolvo.setCreBy(userId);
				pecontrolvo.setUpdBy(userId);
				this.peWgControlDAO.insertPeWgControl(pecontrolvo);
			} else {
				pecontrolvo.setOuCode(ouCode);
				pecontrolvo.setEvaYear(new Long(evaYear));
				pecontrolvo.setEvaTime(new Long(evaTime));
				pecontrolvo.setUserId(userId);
				pecontrolvo.setUpdBy(userId);
				this.peWgControlDAO.updatePeWgControl(pecontrolvo);
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
	 * PnOrganizationVO org = (PnOrganizationVO)iter.next(); PeWgControlVO
	 * pewgcontrolvo = new PeWgControlVO(); boolean insertData =
	 * this.peWgControlDAO.CheckDataForInsert(ouCode, new Long(evaYear), new
	 * Long(evaTime), org.getCodeSeq()); if (insertData) {
	 * pewgcontrolvo.setOuCode(ouCode); pewgcontrolvo.setEvaYear(new
	 * Long(evaYear)); pewgcontrolvo.setEvaTime(new Long(evaTime));
	 * pewgcontrolvo.setCodeSeq(org.getCodeSeq());
	 * pewgcontrolvo.setFlagClose("Y"); pewgcontrolvo.setCreBy(userId);
	 * pewgcontrolvo.setUpdBy(userId);
	 * this.peWgControlDAO.insertPeWgControl(pewgcontrolvo); } else{ boolean
	 * updateData = this.peWgControlDAO.CheckDataForUpdate(ouCode, new
	 * Long(evaYear), new Long(evaTime), org.getCodeSeq()); if (updateData) {
	 * pewgcontrolvo.setOuCode(ouCode); pewgcontrolvo.setEvaYear(new
	 * Long(evaYear)); pewgcontrolvo.setEvaTime(new Long(evaTime));
	 * pewgcontrolvo.setCodeSeq(org.getCodeSeq());
	 * pewgcontrolvo.setUpdBy(userId);
	 * this.peWgControlDAO.updatePeWgControl(pewgcontrolvo); } } } } catch
	 * (Exception e) { e.printStackTrace(); } }
	 */
}
