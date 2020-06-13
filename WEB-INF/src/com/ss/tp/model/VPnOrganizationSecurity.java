package com.ss.tp.model;

import java.io.Serializable;

public class VPnOrganizationSecurity implements Serializable {
	private VPnOrganizationSecurityPK pk;
	private String orgCode;

	public VPnOrganizationSecurity() {

	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public VPnOrganizationSecurityPK getPk() {
		return pk;
	}

	public void setPk(VPnOrganizationSecurityPK pk) {
		this.pk = pk;
	}

	public VPnOrganizationSecurity(String code, VPnOrganizationSecurityPK pk) {
		orgCode = code;
		this.pk = pk;
	}

}
