package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.WePnEmployeeTextDAO;
import com.ss.tp.dto.WeEmployeeVO;
import com.ss.tp.dto.WePnEmployeeTextVO;
import com.ss.tp.service.WePnEmployeeTextService;

public class WePnEmployeeTextServiceImpl implements WePnEmployeeTextService,
		Serializable {

	private WePnEmployeeTextDAO WePnEmployeeTextDAO;

	public WePnEmployeeTextDAO getWePnEmployeeTextDAO() {
		return WePnEmployeeTextDAO;
	}

	public void setWePnEmployeeTextDAO(WePnEmployeeTextDAO WePnEmployeeTextDAO) {
		this.WePnEmployeeTextDAO = WePnEmployeeTextDAO;
	}

	public void insertWePnEmployeeText(WePnEmployeeTextVO wepnemployeetextvo)
			throws Exception {
		this.WePnEmployeeTextDAO.insertWePnEmployeeText(wepnemployeetextvo);
	}

	public void updateWePnEmployeeText(WePnEmployeeTextVO wepnemployeetextvo)
			throws Exception {
		this.WePnEmployeeTextDAO.updateWePnEmployeeText(wepnemployeetextvo);
	}

	public void deleteWePnEmployeeText(WePnEmployeeTextVO wepnemployeetextvo)
			throws Exception {
		this.WePnEmployeeTextDAO.deleteWePnEmployeeText(wepnemployeetextvo);
	}

	public void insertWePnEmployeeTexts(List rwpremiumvolist) throws Exception {
		this.WePnEmployeeTextDAO.insertWePnEmployeeTexts(rwpremiumvolist);
	}

	public List findByCriteria(String userId, String evaOuCode,
			String evaCodeSeqFrom, String evaCodeSeqTo, String evaEmpCodeFrom,
			String evaEmpCodeTo, int count, int countRecord) {
		return this.WePnEmployeeTextDAO.findByCriteria(userId, evaOuCode,
				evaCodeSeqFrom, evaCodeSeqTo, evaEmpCodeFrom, evaEmpCodeTo,
				count, countRecord);
	}

	public Integer countData(String userId, String evaOuCode,
			String evaCodeSeqFrom, String evaCodeSeqTo, String evaEmpCodeFrom,
			String evaEmpCodeTo) {
		return this.WePnEmployeeTextDAO.countData(userId, evaOuCode,
				evaCodeSeqFrom, evaCodeSeqTo, evaEmpCodeFrom, evaEmpCodeTo);
	}

	public WeEmployeeVO findByEmpCodeDetail(String empCode, String ouCode) {
		return this.WePnEmployeeTextDAO.findByEmpCodeDetail(empCode, ouCode);
	}

	public List findByEmpCodeList(String userId, String ouCode, String empCode) {
		return this.WePnEmployeeTextDAO.findByEmpCodeList(userId, ouCode,
				empCode);
	}

	public void addList(WePnEmployeeTextVO rpVo, boolean isSave) {
		this.WePnEmployeeTextDAO.addList(rpVo);

		if (isSave) {
			try {
				this.WePnEmployeeTextDAO.insertAndUpdateWePnEmployeeTexts();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void insertAndUpdate() throws Exception {
		this.WePnEmployeeTextDAO.insertAndUpdateWePnEmployeeTexts();
	}

	public boolean canDelete(String ouCode, String userId) throws Exception {
		return this.WePnEmployeeTextDAO.canDelete(ouCode, userId);
	}

	public List findByCriteriaList(String userId, String evaOuCode,
			String evaCodeSeqFrom, String evaCodeSeqTo, String evaEmpCodeFrom,
			String evaEmpCodeTo, int count, int countRecord) {
		return this.WePnEmployeeTextDAO.findByCriteriaList(userId, evaOuCode,
				evaCodeSeqFrom, evaCodeSeqTo, evaEmpCodeFrom, evaEmpCodeTo,
				count, countRecord);
	}

}
