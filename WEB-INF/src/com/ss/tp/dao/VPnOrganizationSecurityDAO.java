package com.ss.tp.dao;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dto.VPnOrganizationVO;

public interface VPnOrganizationSecurityDAO extends Serializable {
	public VPnOrganizationVO findByKey(String ouCode, String userId,
			String codeSeq);

	public List findByKeyPK(String ouCode, String userId, String orgCode);
	
	public List findByCodeSeqPK(String ouCode, String userId, Double codeSeq);
}
