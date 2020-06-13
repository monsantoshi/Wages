package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.WeWgEmployeeTextDAO;
import com.ss.tp.dto.WeEmployeeVO;
import com.ss.tp.dto.WeWgEmployeeTextVO;
import com.ss.tp.service.WeWgEmployeeTextService;

public class WeWgEmployeeTextServiceImpl implements WeWgEmployeeTextService,
		Serializable {

	private WeWgEmployeeTextDAO WeWgEmployeeTextDAO;

	public WeWgEmployeeTextDAO getWeWgEmployeeTextDAO() {
		return WeWgEmployeeTextDAO;
	}

	public void setWeWgEmployeeTextDAO(WeWgEmployeeTextDAO WeWgEmployeeTextDAO) {
		this.WeWgEmployeeTextDAO = WeWgEmployeeTextDAO;
	}

	public void insertWeWgEmployeeText(WeWgEmployeeTextVO wepnemployeetextvo)
			throws Exception {
		this.WeWgEmployeeTextDAO.insertWeWgEmployeeText(wepnemployeetextvo);
	}

	public void updateWeWgEmployeeText(WeWgEmployeeTextVO wepnemployeetextvo)
			throws Exception {
		this.WeWgEmployeeTextDAO.updateWeWgEmployeeText(wepnemployeetextvo);
	}

	public void deleteWeWgEmployeeText(WeWgEmployeeTextVO wepnemployeetextvo)
			throws Exception {
		this.WeWgEmployeeTextDAO.deleteWeWgEmployeeText(wepnemployeetextvo);
	}

	public void insertWeWgEmployeeTexts(List rwpremiumvolist) throws Exception {
		this.WeWgEmployeeTextDAO.insertWeWgEmployeeTexts(rwpremiumvolist);
	}

	public List findByCriteria(String userId, String evaOuCode,
			String evaCodeSeqFrom, String evaCodeSeqTo, String evaEmpCodeFrom,
			String evaEmpCodeTo, int count, int countRecord) {
		return this.WeWgEmployeeTextDAO.findByCriteria(userId, evaOuCode,
				evaCodeSeqFrom, evaCodeSeqTo, evaEmpCodeFrom, evaEmpCodeTo,
				count, countRecord);
	}

	public Integer countData(String userId, String evaOuCode,
			String evaCodeSeqFrom, String evaCodeSeqTo, String evaEmpCodeFrom,
			String evaEmpCodeTo) {
		return this.WeWgEmployeeTextDAO.countData(userId, evaOuCode,
				evaCodeSeqFrom, evaCodeSeqTo, evaEmpCodeFrom, evaEmpCodeTo);
	}

	public WeEmployeeVO findByEmpCodeDetail(String empCode, String ouCode) {
		return this.WeWgEmployeeTextDAO.findByEmpCodeDetail(empCode, ouCode);
	}

	public List findByEmpCodeList(String userId, String ouCode, String empCode) {
		return this.WeWgEmployeeTextDAO.findByEmpCodeList(userId, ouCode,
				empCode);
	}

	public void addList(WeWgEmployeeTextVO rpVo, boolean isSave) {
		this.WeWgEmployeeTextDAO.addList(rpVo);

		if (isSave) {
			try {
				this.WeWgEmployeeTextDAO.insertAndUpdateWeWgEmployeeTexts();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void insertAndUpdate() throws Exception {
		this.WeWgEmployeeTextDAO.insertAndUpdateWeWgEmployeeTexts();
	}

	public boolean canDelete(String ouCode, String userId) throws Exception {
		return this.WeWgEmployeeTextDAO.canDelete(ouCode, userId);
	}

	public List findByCriteriaList(String userId, String evaOuCode,
			String evaCodeSeqFrom, String evaCodeSeqTo, String evaEmpCodeFrom,
			String evaEmpCodeTo, int count, int countRecord) {
		return this.WeWgEmployeeTextDAO.findByCriteriaList(userId, evaOuCode,
				evaCodeSeqFrom, evaCodeSeqTo, evaEmpCodeFrom, evaEmpCodeTo,
				count, countRecord);
	}

}
