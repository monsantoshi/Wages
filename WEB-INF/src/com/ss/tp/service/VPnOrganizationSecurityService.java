package com.ss.tp.service;

import java.util.List;

import com.ss.tp.dto.VPnOrganizationVO;

public interface VPnOrganizationSecurityService {
	public VPnOrganizationVO findVPnOrganizationSecurity(String ouCode,
			String userId, String codeSeq);

	public List findByKeyPK(String ouCode, String userId, String orgCode);
	public List findByCodeSeqPK(String ouCode, String userId, Double codeSeq);
}
