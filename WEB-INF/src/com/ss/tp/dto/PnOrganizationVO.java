package com.ss.tp.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class PnOrganizationVO implements Serializable {
	private String ouCode;
	private Long codeSeq;
	private String orgCode;
	private String orgShort;
	private String orgDesc;
	private String levelCode;
	private String masterCode;
	private Long seq;
	private Long weight;
	private String id;
	private String deptCode;
	private String deptDesc;
	private Long deptSeq;
	private String partCode;
	private String partDesc;
	private Long partSeq;
	private String divCode;
	private String divDesc;
	private String divShort;
	private Long divSeq;
	private String secCode;
	private String secDesc;
	private Long secSeq;
	private String workCode;
	private String workDesc;
	private Long workSeq;
	private String addition1Code;
	private String addition1Desc;
	private Long addition1Seq;
	private String addition2Code;
	private String addition2Desc;
	private Long addition2Seq;
	private String addition3Code;
	private String addition3Desc;
	private Long addition3Seq;
	private Date lastActionDate;
	private String lastDocNo;
	private Date lastDocDate;
	private String inactive;
	private String provinceCode;
	private String zipCode;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;
	private String bankFlag;
	private String b27Flag;
	private Set pnEmployees;
	private String areaCode;
	private String areaDesc;
	private String orgShowDesc;

	public PnOrganizationVO() {
	}

	public String getAddition1Code() {
		return addition1Code;
	}

	public void setAddition1Code(String addition1Code) {
		this.addition1Code = addition1Code;
	}

	public String getAddition1Desc() {
		return addition1Desc;
	}

	public void setAddition1Desc(String addition1Desc) {
		this.addition1Desc = addition1Desc;
	}

	public Long getAddition1Seq() {
		return addition1Seq;
	}

	public void setAddition1Seq(Long addition1Seq) {
		this.addition1Seq = addition1Seq;
	}

	public String getAddition2Code() {
		return addition2Code;
	}

	public void setAddition2Code(String addition2Code) {
		this.addition2Code = addition2Code;
	}

	public String getAddition2Desc() {
		return addition2Desc;
	}

	public void setAddition2Desc(String addition2Desc) {
		this.addition2Desc = addition2Desc;
	}

	public Long getAddition2Seq() {
		return addition2Seq;
	}

	public void setAddition2Seq(Long addition2Seq) {
		this.addition2Seq = addition2Seq;
	}

	public String getAddition3Code() {
		return addition3Code;
	}

	public void setAddition3Code(String addition3Code) {
		this.addition3Code = addition3Code;
	}

	public String getAddition3Desc() {
		return addition3Desc;
	}

	public void setAddition3Desc(String addition3Desc) {
		this.addition3Desc = addition3Desc;
	}

	public Long getAddition3Seq() {
		return addition3Seq;
	}

	public void setAddition3Seq(Long addition3Seq) {
		this.addition3Seq = addition3Seq;
	}

	public String getB27Flag() {
		return b27Flag;
	}

	public void setB27Flag(String flag) {
		b27Flag = flag;
	}

	public String getBankFlag() {
		return bankFlag;
	}

	public void setBankFlag(String bankFlag) {
		this.bankFlag = bankFlag;
	}

	public Long getCodeSeq() {
		return codeSeq;
	}

	public void setCodeSeq(Long codeSeq) {
		this.codeSeq = codeSeq;
	}

	public String getCreBy() {
		return creBy;
	}

	public void setCreBy(String creBy) {
		this.creBy = creBy;
	}

	public Date getCreDate() {
		return creDate;
	}

	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptDesc() {
		return deptDesc;
	}

	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}

	public Long getDeptSeq() {
		return deptSeq;
	}

	public void setDeptSeq(Long deptSeq) {
		this.deptSeq = deptSeq;
	}

	public String getDivCode() {
		return divCode;
	}

	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}

	public String getDivDesc() {
		return divDesc;
	}

	public void setDivDesc(String divDesc) {
		this.divDesc = divDesc;
	}

	public Long getDivSeq() {
		return divSeq;
	}

	public void setDivSeq(Long divSeq) {
		this.divSeq = divSeq;
	}

	public String getDivShort() {
		return divShort;
	}

	public void setDivShort(String divShort) {
		this.divShort = divShort;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInactive() {
		return inactive;
	}

	public void setInactive(String inactive) {
		this.inactive = inactive;
	}

	public Date getLastActionDate() {
		return lastActionDate;
	}

	public void setLastActionDate(Date lastActionDate) {
		this.lastActionDate = lastActionDate;
	}

	public Date getLastDocDate() {
		return lastDocDate;
	}

	public void setLastDocDate(Date lastDocDate) {
		this.lastDocDate = lastDocDate;
	}

	public String getLastDocNo() {
		return lastDocNo;
	}

	public void setLastDocNo(String lastDocNo) {
		this.lastDocNo = lastDocNo;
	}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public String getMasterCode() {
		return masterCode;
	}

	public void setMasterCode(String masterCode) {
		this.masterCode = masterCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgDesc() {
		return orgDesc;
	}

	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}

	public String getOrgShort() {
		return orgShort;
	}

	public void setOrgShort(String orgShort) {
		this.orgShort = orgShort;
	}

	public String getOuCode() {
		return ouCode;
	}

	public void setOuCode(String ouCode) {
		this.ouCode = ouCode;
	}

	public String getPartCode() {
		return partCode;
	}

	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}

	public String getPartDesc() {
		return partDesc;
	}

	public void setPartDesc(String partDesc) {
		this.partDesc = partDesc;
	}

	public Long getPartSeq() {
		return partSeq;
	}

	public void setPartSeq(Long partSeq) {
		this.partSeq = partSeq;
	}

	public Set getPnEmployees() {
		return pnEmployees;
	}

	public void setPnEmployees(Set pnEmployees) {
		this.pnEmployees = pnEmployees;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getSecCode() {
		return secCode;
	}

	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}

	public String getSecDesc() {
		return secDesc;
	}

	public void setSecDesc(String secDesc) {
		this.secDesc = secDesc;
	}

	public Long getSecSeq() {
		return secSeq;
	}

	public void setSecSeq(Long secSeq) {
		this.secSeq = secSeq;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getUpdBy() {
		return updBy;
	}

	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}

	public Date getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public Long getWeight() {
		return weight;
	}

	public void setWeight(Long weight) {
		this.weight = weight;
	}

	public String getWorkCode() {
		return workCode;
	}

	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}

	public String getWorkDesc() {
		return workDesc;
	}

	public void setWorkDesc(String workDesc) {
		this.workDesc = workDesc;
	}

	public Long getWorkSeq() {
		return workSeq;
	}

	public void setWorkSeq(Long workSeq) {
		this.workSeq = workSeq;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaDesc() {
		return areaDesc;
	}

	public void setAreaDesc(String areaDesc) {
		this.areaDesc = areaDesc;
	}

	public String getOrgShowDesc() {
		return orgShowDesc;
	}

	public void setOrgShowDesc(String orgShowDesc) {
		this.orgShowDesc = orgShowDesc;
	}

	public PnOrganizationVO(String code, String desc, Long seq, String code2,
			String desc2, Long seq2, String code3, String desc3, Long seq3,
			String flag, String flag2, Long seq4, String by, Date date,
			String code4, String desc4, Long seq5, String code5, String desc5,
			Long seq6, String short1, String id, String inactive, Date date2,
			Date date3, String no, String code6, String code7, String code8,
			String desc6, String short2, String code9, String code10,
			String desc7, Long seq7, Set employees, String code11,
			String code12, String desc8, Long seq8, Long seq9, String by2,
			Date date4, Long weight, String code13, String desc9, Long seq10,
			String code14) {
		super();
		// TODO Auto-generated constructor stub
		addition1Code = code;
		addition1Desc = desc;
		addition1Seq = seq;
		addition2Code = code2;
		addition2Desc = desc2;
		addition2Seq = seq2;
		addition3Code = code3;
		addition3Desc = desc3;
		addition3Seq = seq3;
		b27Flag = flag;
		bankFlag = flag2;
		codeSeq = seq4;
		creBy = by;
		creDate = date;
		deptCode = code4;
		deptDesc = desc4;
		deptSeq = seq5;
		divCode = code5;
		divDesc = desc5;
		divSeq = seq6;
		divShort = short1;
		this.id = id;
		this.inactive = inactive;
		lastActionDate = date2;
		lastDocDate = date3;
		lastDocNo = no;
		levelCode = code6;
		masterCode = code7;
		orgCode = code8;
		orgDesc = desc6;
		orgShort = short2;
		ouCode = code9;
		partCode = code10;
		partDesc = desc7;
		partSeq = seq7;
		pnEmployees = employees;
		provinceCode = code11;
		secCode = code12;
		secDesc = desc8;
		secSeq = seq8;
		seq = seq9;
		updBy = by2;
		updDate = date4;
		this.weight = weight;
		workCode = code13;
		workDesc = desc9;
		workSeq = seq10;
		zipCode = code14;
	}

}
