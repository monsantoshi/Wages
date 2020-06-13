package com.ss.tp.service.impl;

import java.io.Serializable; //import com.ss.tp.dao.PeControlDAO;
import com.ss.tp.dao.PnTextControlDAO;
import com.ss.tp.dao.SuUserOrganizationDAO; /*import com.ss.tp.dto.PeControlVO;
import com.ss.tp.service.PeControlService;*/
import com.ss.tp.dto.PnTextControlVO;
import com.ss.tp.service.PnTextControlService;

public class PnTextControlServiceImpl implements PnTextControlService,
		Serializable {
	private PnTextControlDAO pnTextControlDAO;
	private SuUserOrganizationDAO suUserOrganizationDAO;

	public SuUserOrganizationDAO getSuUserOrganizationDAO() {
		return suUserOrganizationDAO;
	}

	public void setSuUserOrganizationDAO(
			SuUserOrganizationDAO suUserOrganizationDAO) {
		this.suUserOrganizationDAO = suUserOrganizationDAO;
	}

	public PnTextControlDAO getPnTextControlDAO() {
		return pnTextControlDAO;
	}

	public void setPnTextControlDAO(PnTextControlDAO pnTextControlDAO) {
		this.pnTextControlDAO = pnTextControlDAO;
	}

	public String CheckHaveData(String ouCode, String userId, long evaYear,
			long evaTime, String codeSeq) {
		return this.pnTextControlDAO.CheckHaveData(ouCode, userId, new Long(
				evaYear), new Long(evaTime), codeSeq);

	}

	public void insertCloseData(String userId, String ouCode, long evaYear,
			long evaTime) {
		try {
			PnTextControlVO pntextcontrolvo = new PnTextControlVO();
			boolean insertData = this.pnTextControlDAO.CheckDataForInsert(
					ouCode, new Long(evaYear), new Long(evaTime), userId);
			if (insertData) {

				pntextcontrolvo.setOuCode(ouCode);
				pntextcontrolvo.setEvaYear(new Long(evaYear));
				pntextcontrolvo.setEvaTime(new Long(evaTime));
				pntextcontrolvo.setUserId(userId);
				pntextcontrolvo.setFlagClose("Y");
				pntextcontrolvo.setCreBy(userId);
				pntextcontrolvo.setUpdBy(userId);
				this.pnTextControlDAO.insertPnTextControl(pntextcontrolvo);
			} else {
				pntextcontrolvo.setOuCode(ouCode);
				pntextcontrolvo.setEvaYear(new Long(evaYear));
				pntextcontrolvo.setEvaTime(new Long(evaTime));
				pntextcontrolvo.setUserId(userId);
				pntextcontrolvo.setUpdBy(userId);
				this.pnTextControlDAO.updatePnTextControl(pntextcontrolvo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
