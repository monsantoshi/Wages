package com.ss.tp.model;

import java.io.Serializable;

public class PnAdmin implements Serializable {
	private PnAdminPK pk;
	private String seq;
	private String adminDesc;
	private String stdBy;
	private String inActive;

	public String getAdminDesc() {
		return adminDesc;
	}

	public void setAdminDesc(String adminDesc) {
		this.adminDesc = adminDesc;
	}

	public String getInActive() {
		return inActive;
	}

	public void setInActive(String inActive) {
		this.inActive = inActive;
	}

	public PnAdminPK getPk() {
		return pk;
	}

	public void setPk(PnAdminPK pk) {
		this.pk = pk;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getStdBy() {
		return stdBy;
	}

	public void setStdBy(String stdBy) {
		this.stdBy = stdBy;
	}

	public PnAdmin(String desc, String active, PnAdminPK pk, String seq,
			String by) {
		super();
		// TODO Auto-generated constructor stub
		adminDesc = desc;
		inActive = active;
		this.pk = pk;
		this.seq = seq;
		stdBy = by;
	}

	public PnAdmin() {
	}

}
