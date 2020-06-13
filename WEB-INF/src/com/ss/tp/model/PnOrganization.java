package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class PnOrganization implements Serializable {
	private PnOrganizationPK pk;
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
	private Long areaSeq;
	private String areaCode;
	private String areaDesc;
	private String orgShowDesc;

	/**
	 * @return Returns the areaCode.
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * @param areaCode
	 *            The areaCode to set.
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * @return Returns the areaDesc.
	 */
	public String getAreaDesc() {
		return areaDesc;
	}

	/**
	 * @param areaDesc
	 *            The areaDesc to set.
	 */
	public void setAreaDesc(String areaDesc) {
		this.areaDesc = areaDesc;
	}

	/**
	 * 
	 */
	public PnOrganization() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param pk
	 * @param orgCode
	 * @param orgShort
	 * @param orgDesc
	 * @param levelCode
	 * @param masterCode
	 * @param seq
	 * @param weight
	 * @param id
	 * @param deptCode
	 * @param deptDesc
	 * @param deptSeq
	 * @param partCode
	 * @param partDesc
	 * @param partSeq
	 * @param divCode
	 * @param divDesc
	 * @param divShort
	 * @param divSeq
	 * @param secCode
	 * @param secDesc
	 * @param secSeq
	 * @param workCode
	 * @param workDesc
	 * @param workSeq
	 * @param addition1Code
	 * @param addition1Desc
	 * @param addition1Seq
	 * @param addition2Code
	 * @param addition2Desc
	 * @param addition2Seq
	 * @param addition3Code
	 * @param addition3Desc
	 * @param addition3Seq
	 * @param lastActionDate
	 * @param lastDocNo
	 * @param lastDocDate
	 * @param inactive
	 * @param provinceCode
	 * @param zipCode
	 * @param creBy
	 * @param creDate
	 * @param updBy
	 * @param updDate
	 * @param bankFlag
	 * @param flag
	 * @param pnEmployees
	 */
	public PnOrganization(PnOrganizationPK pk, String orgCode, String orgShort,
			String orgDesc, String levelCode, String masterCode, Long seq,
			Long weight, String id, String deptCode, String deptDesc,
			Long deptSeq, String partCode, String partDesc, Long partSeq,
			String divCode, String divDesc, String divShort, Long divSeq,
			String secCode, String secDesc, Long secSeq, String workCode,
			String workDesc, Long workSeq, String addition1Code,
			String addition1Desc, Long addition1Seq, String addition2Code,
			String addition2Desc, Long addition2Seq, String addition3Code,
			String addition3Desc, Long addition3Seq, Date lastActionDate,
			String lastDocNo, Date lastDocDate, String inactive,
			String provinceCode, String zipCode, String creBy, Date creDate,
			String updBy, Date updDate, String bankFlag, String b27flag,
			Set pnEmployees) {

		this.pk = pk;
		this.orgCode = orgCode;
		this.orgShort = orgShort;
		this.orgDesc = orgDesc;
		this.levelCode = levelCode;
		this.masterCode = masterCode;
		this.seq = seq;
		this.weight = weight;
		this.id = id;
		this.deptCode = deptCode;
		this.deptDesc = deptDesc;
		this.deptSeq = deptSeq;
		this.partCode = partCode;
		this.partDesc = partDesc;
		this.partSeq = partSeq;
		this.divCode = divCode;
		this.divDesc = divDesc;
		this.divShort = divShort;
		this.divSeq = divSeq;
		this.secCode = secCode;
		this.secDesc = secDesc;
		this.secSeq = secSeq;
		this.workCode = workCode;
		this.workDesc = workDesc;
		this.workSeq = workSeq;
		this.addition1Code = addition1Code;
		this.addition1Desc = addition1Desc;
		this.addition1Seq = addition1Seq;
		this.addition2Code = addition2Code;
		this.addition2Desc = addition2Desc;
		this.addition2Seq = addition2Seq;
		this.addition3Code = addition3Code;
		this.addition3Desc = addition3Desc;
		this.addition3Seq = addition3Seq;
		this.lastActionDate = lastActionDate;
		this.lastDocNo = lastDocNo;
		this.lastDocDate = lastDocDate;
		this.inactive = inactive;
		this.provinceCode = provinceCode;
		this.zipCode = zipCode;
		this.creBy = creBy;
		this.creDate = creDate;
		this.updBy = updBy;
		this.updDate = updDate;
		this.bankFlag = bankFlag;
		this.b27Flag = b27flag;
		this.pnEmployees = pnEmployees;
	}

	/**
	 * @return Returns the addition1Code.
	 */
	public String getAddition1Code() {
		return addition1Code;
	}

	/**
	 * @param addition1Code
	 *            The addition1Code to set.
	 */
	public void setAddition1Code(String addition1Code) {
		this.addition1Code = addition1Code;
	}

	/**
	 * @return Returns the addition1Desc.
	 */
	public String getAddition1Desc() {
		return addition1Desc;
	}

	/**
	 * @param addition1Desc
	 *            The addition1Desc to set.
	 */
	public void setAddition1Desc(String addition1Desc) {
		this.addition1Desc = addition1Desc;
	}

	/**
	 * @return Returns the addition1Seq.
	 */
	public Long getAddition1Seq() {
		return addition1Seq;
	}

	/**
	 * @param addition1Seq
	 *            The addition1Seq to set.
	 */
	public void setAddition1Seq(Long addition1Seq) {
		this.addition1Seq = addition1Seq;
	}

	/**
	 * @return Returns the addition2Code.
	 */
	public String getAddition2Code() {
		return addition2Code;
	}

	/**
	 * @param addition2Code
	 *            The addition2Code to set.
	 */
	public void setAddition2Code(String addition2Code) {
		this.addition2Code = addition2Code;
	}

	/**
	 * @return Returns the addition2Desc.
	 */
	public String getAddition2Desc() {
		return addition2Desc;
	}

	/**
	 * @param addition2Desc
	 *            The addition2Desc to set.
	 */
	public void setAddition2Desc(String addition2Desc) {
		this.addition2Desc = addition2Desc;
	}

	/**
	 * @return Returns the addition2Seq.
	 */
	public Long getAddition2Seq() {
		return addition2Seq;
	}

	/**
	 * @param addition2Seq
	 *            The addition2Seq to set.
	 */
	public void setAddition2Seq(Long addition2Seq) {
		this.addition2Seq = addition2Seq;
	}

	/**
	 * @return Returns the addition3Code.
	 */
	public String getAddition3Code() {
		return addition3Code;
	}

	/**
	 * @param addition3Code
	 *            The addition3Code to set.
	 */
	public void setAddition3Code(String addition3Code) {
		this.addition3Code = addition3Code;
	}

	/**
	 * @return Returns the addition3Desc.
	 */
	public String getAddition3Desc() {
		return addition3Desc;
	}

	/**
	 * @param addition3Desc
	 *            The addition3Desc to set.
	 */
	public void setAddition3Desc(String addition3Desc) {
		this.addition3Desc = addition3Desc;
	}

	/**
	 * @return Returns the addition3Seq.
	 */
	public Long getAddition3Seq() {
		return addition3Seq;
	}

	/**
	 * @param addition3Seq
	 *            The addition3Seq to set.
	 */
	public void setAddition3Seq(Long addition3Seq) {
		this.addition3Seq = addition3Seq;
	}

	/**
	 * @return Returns the b27Flag.
	 */
	public String getB27Flag() {
		return b27Flag;
	}

	/**
	 * @param flag
	 *            The b27Flag to set.
	 */
	public void setB27Flag(String flag) {
		b27Flag = flag;
	}

	/**
	 * @return Returns the bankFlag.
	 */
	public String getBankFlag() {
		return bankFlag;
	}

	/**
	 * @param bankFlag
	 *            The bankFlag to set.
	 */
	public void setBankFlag(String bankFlag) {
		this.bankFlag = bankFlag;
	}

	/**
	 * @return Returns the creBy.
	 */
	public String getCreBy() {
		return creBy;
	}

	/**
	 * @param creBy
	 *            The creBy to set.
	 */
	public void setCreBy(String creBy) {
		this.creBy = creBy;
	}

	/**
	 * @return Returns the creDate.
	 */
	public Date getCreDate() {
		return creDate;
	}

	/**
	 * @param creDate
	 *            The creDate to set.
	 */
	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}

	/**
	 * @return Returns the deptCode.
	 */
	public String getDeptCode() {
		return deptCode;
	}

	/**
	 * @param deptCode
	 *            The deptCode to set.
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	/**
	 * @return Returns the deptDesc.
	 */
	public String getDeptDesc() {
		return deptDesc;
	}

	/**
	 * @param deptDesc
	 *            The deptDesc to set.
	 */
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}

	/**
	 * @return Returns the deptSeq.
	 */
	public Long getDeptSeq() {
		return deptSeq;
	}

	/**
	 * @param deptSeq
	 *            The deptSeq to set.
	 */
	public void setDeptSeq(Long deptSeq) {
		this.deptSeq = deptSeq;
	}

	/**
	 * @return Returns the divCode.
	 */
	public String getDivCode() {
		return divCode;
	}

	/**
	 * @param divCode
	 *            The divCode to set.
	 */
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}

	/**
	 * @return Returns the divDesc.
	 */
	public String getDivDesc() {
		return divDesc;
	}

	/**
	 * @param divDesc
	 *            The divDesc to set.
	 */
	public void setDivDesc(String divDesc) {
		this.divDesc = divDesc;
	}

	/**
	 * @return Returns the divSeq.
	 */
	public Long getDivSeq() {
		return divSeq;
	}

	/**
	 * @param divSeq
	 *            The divSeq to set.
	 */
	public void setDivSeq(Long divSeq) {
		this.divSeq = divSeq;
	}

	/**
	 * @return Returns the divShort.
	 */
	public String getDivShort() {
		return divShort;
	}

	/**
	 * @param divShort
	 *            The divShort to set.
	 */
	public void setDivShort(String divShort) {
		this.divShort = divShort;
	}

	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return Returns the inactive.
	 */
	public String getInactive() {
		return inactive;
	}

	/**
	 * @param inactive
	 *            The inactive to set.
	 */
	public void setInactive(String inactive) {
		this.inactive = inactive;
	}

	/**
	 * @return Returns the lastActionDate.
	 */
	public Date getLastActionDate() {
		return lastActionDate;
	}

	/**
	 * @param lastActionDate
	 *            The lastActionDate to set.
	 */
	public void setLastActionDate(Date lastActionDate) {
		this.lastActionDate = lastActionDate;
	}

	/**
	 * @return Returns the lastDocDate.
	 */
	public Date getLastDocDate() {
		return lastDocDate;
	}

	/**
	 * @param lastDocDate
	 *            The lastDocDate to set.
	 */
	public void setLastDocDate(Date lastDocDate) {
		this.lastDocDate = lastDocDate;
	}

	/**
	 * @return Returns the lastDocNo.
	 */
	public String getLastDocNo() {
		return lastDocNo;
	}

	/**
	 * @param lastDocNo
	 *            The lastDocNo to set.
	 */
	public void setLastDocNo(String lastDocNo) {
		this.lastDocNo = lastDocNo;
	}

	/**
	 * @return Returns the levelCode.
	 */
	public String getLevelCode() {
		return levelCode;
	}

	/**
	 * @param levelCode
	 *            The levelCode to set.
	 */
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	/**
	 * @return Returns the masterCode.
	 */
	public String getMasterCode() {
		return masterCode;
	}

	/**
	 * @param masterCode
	 *            The masterCode to set.
	 */
	public void setMasterCode(String masterCode) {
		this.masterCode = masterCode;
	}

	/**
	 * @return Returns the orgCode.
	 */
	public String getOrgCode() {
		return orgCode;
	}

	/**
	 * @param orgCode
	 *            The orgCode to set.
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	/**
	 * @return Returns the orgDesc.
	 */
	public String getOrgDesc() {
		return orgDesc;
	}

	/**
	 * @param orgDesc
	 *            The orgDesc to set.
	 */
	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}

	/**
	 * @return Returns the orgShort.
	 */
	public String getOrgShort() {
		return orgShort;
	}

	/**
	 * @param orgShort
	 *            The orgShort to set.
	 */
	public void setOrgShort(String orgShort) {
		this.orgShort = orgShort;
	}

	/**
	 * @return Returns the partCode.
	 */
	public String getPartCode() {
		return partCode;
	}

	/**
	 * @param partCode
	 *            The partCode to set.
	 */
	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}

	/**
	 * @return Returns the partDesc.
	 */
	public String getPartDesc() {
		return partDesc;
	}

	/**
	 * @param partDesc
	 *            The partDesc to set.
	 */
	public void setPartDesc(String partDesc) {
		this.partDesc = partDesc;
	}

	/**
	 * @return Returns the partSeq.
	 */
	public Long getPartSeq() {
		return partSeq;
	}

	/**
	 * @param partSeq
	 *            The partSeq to set.
	 */
	public void setPartSeq(Long partSeq) {
		this.partSeq = partSeq;
	}

	/**
	 * @return Returns the pk.
	 */
	public PnOrganizationPK getPk() {
		return pk;
	}

	/**
	 * @param pk
	 *            The pk to set.
	 */
	public void setPk(PnOrganizationPK pk) {
		this.pk = pk;
	}

	/**
	 * @return Returns the pnEmployees.
	 */
	public Set getPnEmployees() {
		return pnEmployees;
	}

	/**
	 * @param pnEmployees
	 *            The pnEmployees to set.
	 */
	public void setPnEmployees(Set pnEmployees) {
		this.pnEmployees = pnEmployees;
	}

	/**
	 * @return Returns the provinceCode.
	 */
	public String getProvinceCode() {
		return provinceCode;
	}

	/**
	 * @param provinceCode
	 *            The provinceCode to set.
	 */
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	/**
	 * @return Returns the secCode.
	 */

	public String getSecCode() {
		return secCode;
	}

	/**
	 * @param secCode
	 *            The secCode to set.
	 */
	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}

	/**
	 * @return Returns the secDesc.
	 */
	public String getSecDesc() {
		return secDesc;
	}

	/**
	 * @param secDesc
	 *            The secDesc to set.
	 */
	public void setSecDesc(String secDesc) {
		this.secDesc = secDesc;
	}

	/**
	 * @return Returns the secSeq.
	 */
	public Long getSecSeq() {
		return secSeq;
	}

	/**
	 * @param secSeq
	 *            The secSeq to set.
	 */
	public void setSecSeq(Long secSeq) {
		this.secSeq = secSeq;
	}

	/**
	 * @return Returns the seq.
	 */
	public Long getSeq() {
		return seq;
	}

	/**
	 * @param seq
	 *            The seq to set.
	 */
	public void setSeq(Long seq) {
		this.seq = seq;
	}

	/**
	 * @return Returns the updBy.
	 */
	public String getUpdBy() {
		return updBy;
	}

	/**
	 * @param updBy
	 *            The updBy to set.
	 */
	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}

	/**
	 * @return Returns the updDate.
	 */
	public Date getUpdDate() {
		return updDate;
	}

	/**
	 * @param updDate
	 *            The updDate to set.
	 */
	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	/**
	 * @return Returns the weight.
	 */
	public Long getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            The weight to set.
	 */
	public void setWeight(Long weight) {
		this.weight = weight;
	}

	/**
	 * @return Returns the workCode.
	 */
	public String getWorkCode() {
		return workCode;
	}

	/**
	 * @param workCode
	 *            The workCode to set.
	 */
	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}

	/**
	 * @return Returns the workDesc.
	 */
	public String getWorkDesc() {
		return workDesc;
	}

	/**
	 * @param workDesc
	 *            The workDesc to set.
	 */
	public void setWorkDesc(String workDesc) {
		this.workDesc = workDesc;
	}

	/**
	 * @return Returns the workSeq.
	 */
	public Long getWorkSeq() {
		return workSeq;
	}

	/**
	 * @param workSeq
	 *            The workSeq to set.
	 */
	public void setWorkSeq(Long workSeq) {
		this.workSeq = workSeq;
	}

	/**
	 * @return Returns the zipCode.
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode
	 *            The zipCode to set.
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getOrgShowDesc() {
		String divShortCheck = this.divShort == null ? "" : this.divShort;
		String areaCheck = " " + (this.areaDesc == null ? "" : this.areaDesc);
		String divCodeCheck = this.divCode == null ? "" : this.divCode;
		String areaCodeCheck = this.areaCode == null ? "" : this.areaCode;
		if (divCodeCheck.equals(areaCodeCheck)) {
			areaCheck = "";
		}
		this.orgShowDesc = divShortCheck + areaCheck + " "
				+ (this.secDesc == null ? "" : this.secDesc) + " "
				+ (this.workDesc == null ? "" : this.workDesc);
		return this.orgShowDesc.trim();
	}

	public void setOrgShowDesc(String orgShowDesc) {
		this.orgShowDesc = orgShowDesc;
	}

	public Long getAreaSeq() {
		return areaSeq;
	}

	public void setAreaSeq(Long areaSeq) {
		this.areaSeq = areaSeq;
	}

}
