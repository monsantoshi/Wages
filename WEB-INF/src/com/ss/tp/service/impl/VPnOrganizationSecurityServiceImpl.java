package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.VPnOrganizationSecurityDAO;
import com.ss.tp.dto.VPnOrganizationVO;
import com.ss.tp.service.VPnOrganizationSecurityService;

public class VPnOrganizationSecurityServiceImpl implements
		VPnOrganizationSecurityService, Serializable {
	private VPnOrganizationSecurityDAO pnOrgSec;

	public VPnOrganizationVO findVPnOrganizationSecurity(String ouCode,
			String userId, String codeSeq) {
		return this.pnOrgSec.findByKey(ouCode, userId, codeSeq);
	}

	public List findByKeyPK(String ouCode, String userId, String orgCode) {
		return this.pnOrgSec.findByKeyPK(ouCode, userId, orgCode);
	}
	
	public List findByCodeSeqPK(String ouCode, String userId, Double codeSeq) {
		return this.pnOrgSec.findByCodeSeqPK(ouCode, userId, codeSeq);
	}

	public VPnOrganizationSecurityDAO getPnOrgSec() {
		return pnOrgSec;
	}

	public void setPnOrgSec(VPnOrganizationSecurityDAO pnOrgSec) {
		this.pnOrgSec = pnOrgSec;
	}

}
