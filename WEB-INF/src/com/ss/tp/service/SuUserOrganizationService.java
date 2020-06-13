package com.ss.tp.service;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dto.PnOrganizationVO;

public interface SuUserOrganizationService extends Serializable {

	public PnOrganizationVO findOrganizationByCriteria(String userId,
			String ouCode, String orgCode) throws Exception;

	public PnOrganizationVO findPrOrganizationByCriteria(String userId,
			String ouCode, String orgCode, String year, String period)
			throws Exception;

	public List findOrganizationByUserIdAndOuCode(String userId, String ouCode)
			throws Exception;

	/**
	 * Find Organization depend on orgCode ( >= orgCode )
	 * 
	 * @param userId
	 * @param ouCode
	 * @param orgCode
	 * @return
	 * @throws Exception
	 */
	public List findOrganizationByUserIdAndOuCodeToOrgCode(String userId,
			String ouCode, String orgCode) throws Exception;

	public List findSecByDiv(String ouCode, String divCode, String userId)
			throws Exception;

	public List findOrganizationByUserIdAndOuCodeLevel31(String userId,
			String ouCode) throws Exception;
	
	public List findOrganizationByUserIdAndOuCodeLevel3(String userId,
			String ouCode) throws Exception;

	public List findWorkCodeByAreaAndSecCode(String ouCode, String areaCode,
			String secCode, String userId) throws Exception;
}
