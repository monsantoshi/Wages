package com.ss.tp.service.impl;

import java.util.List;

import com.ss.tp.dao.SuUserOrganizationDAO;
import com.ss.tp.dto.PnOrganizationVO;
import com.ss.tp.service.SuUserOrganizationService;

public class SuUserOrganizationServiceImpl implements SuUserOrganizationService {

	private SuUserOrganizationDAO suUserOrganizationDaoImpl;

	public SuUserOrganizationDAO getSuUserOrganizationDaoImpl() {
		return suUserOrganizationDaoImpl;
	}

	public void setSuUserOrganizationDaoImpl(
			SuUserOrganizationDAO suUserOrganizationDaoImpl) {
		this.suUserOrganizationDaoImpl = suUserOrganizationDaoImpl;
	}

	public PnOrganizationVO findOrganizationByCriteria(String userId,
			String ouCode, String orgCode) throws Exception {
		return this.suUserOrganizationDaoImpl.findOrganizationByCriteria(
				userId, ouCode, orgCode);
	}

	public List findOrganizationByUserIdAndOuCode(String userId, String ouCode)
			throws Exception {
		return suUserOrganizationDaoImpl.findOrganizationByUserIdAndOuCode(
				userId, ouCode);
	}

	public List findOrganizationByUserIdAndOuCodeToOrgCode(String userId,
			String ouCode, String orgCode) throws Exception {
		return suUserOrganizationDaoImpl
				.findOrganizationByUserIdAndOuCodeToOrgCode(userId, ouCode,
						orgCode);
	}

	public PnOrganizationVO findPrOrganizationByCriteria(String userId,
			String ouCode, String orgCode, String year, String period)
			throws Exception {
		return suUserOrganizationDaoImpl.findPrOrganizationByCriteria(userId,
				ouCode, orgCode, year, period);
	}

	public List findSecByDiv(String ouCode, String divCode, String userId)
			throws Exception {
		return suUserOrganizationDaoImpl.findSecByDiv(ouCode, divCode, userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ss.tp.service.SuUserOrganizationService#
	 * findOrganizationByUserIdAndOuCodeLevel31(java.lang.String,
	 * java.lang.String)
	 */
	public List findOrganizationByUserIdAndOuCodeLevel31(String userId,
			String ouCode) throws Exception {
		return suUserOrganizationDaoImpl
				.findOrganizationByUserIdAndOuCodeLevel31(userId, ouCode);
	}
	
	public List findOrganizationByUserIdAndOuCodeLevel3(String userId,
			String ouCode) throws Exception {
		return suUserOrganizationDaoImpl
				.findOrganizationByUserIdAndOuCodeLevel3(userId, ouCode);
	}

	public List findWorkCodeByAreaAndSecCode(String ouCode, String areaCode,
			String secCode, String userId) throws Exception {
		return suUserOrganizationDaoImpl.findWorkCodeByAreaAndSecCode(ouCode,
				areaCode, secCode, userId);
	}
}
