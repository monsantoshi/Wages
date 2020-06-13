/*
 * Created on 19 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author airsenal
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class WgEmployee implements Serializable {
	private WgEmployeePK wgEmployeePK;
	private Integer codeSeq;
	private Integer codeSeqAct;
	private String preName;
	private String firstName;
	private String lastName;
	private String account;
	private Integer salary;
	private String salaryStep;
	private String payFlag;
	private Integer warfree;
	private String sex;
	private String maritalStatus;
	private String clss;
	private String empStatus;
	private Date birthDate;
	private String race;
	private String nationality;
	private String religion;
	private Date idate;
	private String gworkCode;
	private String dutyCode;
	private Integer dutySeq;
	private String firstEduCode;
	private Integer lastEduCode;
	private String lastAdjustEdu;
	private String levelCode;
	private Date adminDate;
	private String fundStatus;
	private String ordain;
	private String idcardNo;
	private Date idcardExpire;
	private String idcardIssue;
	private String idcardProvince;
	private String taxId;
	private String bloodGroup;
	private Integer weight;
	private Integer height;
	private Integer typeTha;
	private Integer typeEng;
	private String militatyStatus;
	private String telHome;
	private String telMobile;
	private String pager;
	private String fax;
	private String email;
	private String houseAddr;
	private String houseSoi;
	private String houseRoad;
	private String houseDistrict;
	private String houseAmphor;
	private String houseProvince;
	private String houseZipCode;
	private String houseStatus;
	private String censusStatus;
	private String censusAddr;
	private String censusSoi;
	private String censusRoad;
	private String censusDistrict;
	private String censusAmphor;
	private String censusProvince;
	private String censusZipCode;
	private String spouseAddress;
	private String spouseStatus;
	private String spouseCareer;
	private String spouseOffice;
	private String spouseTel;
	private String fatherName;
	private String fatherStatus;
	private String fatherCareer;
	private String fatherOffice;
	private String fatherAddress;
	private String motherName;
	private String motherStatus;
	private String motherCareer;
	private String motherOffice;
	private String motherAddress;
	private String refName;
	private String refRelation;
	private String refCareer;
	private String refOffice;
	private String refTel;
	private String refTelOff;
	private String refAddress;
	private String retireFlag;
	private Integer hisSeqRet;
	private Date lastPromoteDate;
	private String lastInstructNo;
	private Date lastInstructDate;
	private String note;
	private String creBy;
	private Date creDate;
	private String updBy;
	private Date updDate;
	private String house;
	private String retire;
	private Date dutyDate;
	private String instructNo;
	private Date instructDate;
	private Date scontactDate;
	private Date econtactDate;
	private PnOrganization refPnOrganization;
	private PnOrganization refPnOrganizationAct;
	private DbPreSuff refDbPreSuff;
	private WgDuty refWgDuty;

	public WgDuty getRefWgDuty() {
		return refWgDuty;
	}

	public void setRefWgDuty(WgDuty refWgDuty) {
		this.refWgDuty = refWgDuty;
	}

	/**
	 * @return Returns the refDbPreSuff.
	 */
	public DbPreSuff getRefDbPreSuff() {
		return refDbPreSuff;
	}

	/**
	 * @param refDbPreSuff
	 *            The refDbPreSuff to set.
	 */
	public void setRefDbPreSuff(DbPreSuff refDbPreSuff) {
		this.refDbPreSuff = refDbPreSuff;
	}

	/**
	 * @return Returns the refPnOrganization.
	 */
	public PnOrganization getRefPnOrganization() {
		return refPnOrganization;
	}

	/**
	 * @param refPnOrganization
	 *            The refPnOrganization to set.
	 */
	public void setRefPnOrganization(PnOrganization refPnOrganization) {
		this.refPnOrganization = refPnOrganization;
	}

	public String getSpouseOffice() {
		return spouseOffice;
	}

	public void setSpouseOffice(String spouseOffice) {
		this.spouseOffice = spouseOffice;
	}

	public String getSpouseStatus() {
		return spouseStatus;
	}

	public void setSpouseStatus(String spouseStatus) {
		this.spouseStatus = spouseStatus;
	}

	public String getHouseAmphor() {
		return houseAmphor;
	}

	public void setHouseAmphor(String houseAmphor) {
		this.houseAmphor = houseAmphor;
	}

	public String getHouseProvince() {
		return houseProvince;
	}

	public void setHouseProvince(String houseProvince) {
		this.houseProvince = houseProvince;
	}

	public String getHouseZipCode() {
		return houseZipCode;
	}

	public void setHouseZipCode(String houseZipCode) {
		this.houseZipCode = houseZipCode;
	}

	public String getIdcardIssue() {
		return idcardIssue;
	}

	public void setIdcardIssue(String idcardIssue) {
		this.idcardIssue = idcardIssue;
	}

	public String getOrdain() {
		return ordain;
	}

	public void setOrdain(String ordain) {
		this.ordain = ordain;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Date getAdminDate() {
		return adminDate;
	}

	public void setAdminDate(Date adminDate) {
		this.adminDate = adminDate;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getCensusAddr() {
		return censusAddr;
	}

	public void setCensusAddr(String censusAddr) {
		this.censusAddr = censusAddr;
	}

	public String getCensusAmphor() {
		return censusAmphor;
	}

	public void setCensusAmphor(String censusAmphor) {
		this.censusAmphor = censusAmphor;
	}

	public String getCensusDistrict() {
		return censusDistrict;
	}

	public void setCensusDistrict(String censusDistrict) {
		this.censusDistrict = censusDistrict;
	}

	public String getCensusProvince() {
		return censusProvince;
	}

	public void setCensusProvince(String censusProvince) {
		this.censusProvince = censusProvince;
	}

	public String getCensusRoad() {
		return censusRoad;
	}

	public void setCensusRoad(String censusRoad) {
		this.censusRoad = censusRoad;
	}

	public String getCensusSoi() {
		return censusSoi;
	}

	public void setCensusSoi(String censusSoi) {
		this.censusSoi = censusSoi;
	}

	public String getCensusStatus() {
		return censusStatus;
	}

	public void setCensusStatus(String censusStatus) {
		this.censusStatus = censusStatus;
	}

	public String getCensusZipCode() {
		return censusZipCode;
	}

	public void setCensusZipCode(String censusZipCode) {
		this.censusZipCode = censusZipCode;
	}

	public String getClss() {
		return clss;
	}

	public void setClss(String clss) {
		this.clss = clss;
	}

	public Integer getCodeSeq() {
		return codeSeq;
	}

	public void setCodeSeq(Integer codeSeq) {
		this.codeSeq = codeSeq;
	}

	public Integer getCodeSeqAct() {
		return codeSeqAct;
	}

	public void setCodeSeqAct(Integer codeSeqAct) {
		this.codeSeqAct = codeSeqAct;
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

	public String getDutyCode() {
		return dutyCode;
	}

	public void setDutyCode(String dutyCode) {
		this.dutyCode = dutyCode;
	}

	public Date getDutyDate() {
		return dutyDate;
	}

	public void setDutyDate(Date dutyDate) {
		this.dutyDate = dutyDate;
	}

	public Integer getDutySeq() {
		return dutySeq;
	}

	public void setDutySeq(Integer dutySeq) {
		this.dutySeq = dutySeq;
	}

	public Date getEcontactDate() {
		return econtactDate;
	}

	public void setEcontactDate(Date econtactDate) {
		this.econtactDate = econtactDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}

	public String getFatherAddress() {
		return fatherAddress;
	}

	public void setFatherAddress(String fatherAddress) {
		this.fatherAddress = fatherAddress;
	}

	public String getFatherCareer() {
		return fatherCareer;
	}

	public void setFatherCareer(String fatherCareer) {
		this.fatherCareer = fatherCareer;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getFatherOffice() {
		return fatherOffice;
	}

	public void setFatherOffice(String fatherOffice) {
		this.fatherOffice = fatherOffice;
	}

	public String getFatherStatus() {
		return fatherStatus;
	}

	public void setFatherStatus(String fatherStatus) {
		this.fatherStatus = fatherStatus;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFirstEduCode() {
		return firstEduCode;
	}

	public void setFirstEduCode(String firstEduCode) {
		this.firstEduCode = firstEduCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFundStatus() {
		return fundStatus;
	}

	public void setFundStatus(String fundStatus) {
		this.fundStatus = fundStatus;
	}

	public String getGworkCode() {
		return gworkCode;
	}

	public void setGworkCode(String gworkCode) {
		this.gworkCode = gworkCode;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getHisSeqRet() {
		return hisSeqRet;
	}

	public void setHisSeqRet(Integer hisSeqRet) {
		this.hisSeqRet = hisSeqRet;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getHouseAddr() {
		return houseAddr;
	}

	public void setHouseAddr(String houseAddr) {
		this.houseAddr = houseAddr;
	}

	public String getHouseDistrict() {
		return houseDistrict;
	}

	public void setHouseDistrict(String houseDistrict) {
		this.houseDistrict = houseDistrict;
	}

	public String getHouseRoad() {
		return houseRoad;
	}

	public void setHouseRoad(String houseRoad) {
		this.houseRoad = houseRoad;
	}

	public String getHouseSoi() {
		return houseSoi;
	}

	public void setHouseSoi(String houseSoi) {
		this.houseSoi = houseSoi;
	}

	public String getHouseStatus() {
		return houseStatus;
	}

	public void setHouseStatus(String houseStatus) {
		this.houseStatus = houseStatus;
	}

	public Date getIdate() {
		return idate;
	}

	public void setIdate(Date idate) {
		this.idate = idate;
	}

	public Date getIdcardExpire() {
		return idcardExpire;
	}

	public void setIdcardExpire(Date idcardExpire) {
		this.idcardExpire = idcardExpire;
	}

	public String getIdcardNo() {
		return idcardNo;
	}

	public void setIdcardNo(String idcardNo) {
		this.idcardNo = idcardNo;
	}

	public String getIdcardProvince() {
		return idcardProvince;
	}

	public void setIdcardProvince(String idcardProvince) {
		this.idcardProvince = idcardProvince;
	}

	public Date getInstructDate() {
		return instructDate;
	}

	public void setInstructDate(Date instructDate) {
		this.instructDate = instructDate;
	}

	public String getInstructNo() {
		return instructNo;
	}

	public void setInstructNo(String instructNo) {
		this.instructNo = instructNo;
	}

	public String getLastAdjustEdu() {
		return lastAdjustEdu;
	}

	public void setLastAdjustEdu(String lastAdjustEdu) {
		this.lastAdjustEdu = lastAdjustEdu;
	}

	public Integer getLastEduCode() {
		return lastEduCode;
	}

	public void setLastEduCode(Integer lastEduCode) {
		this.lastEduCode = lastEduCode;
	}

	public Date getLastInstructDate() {
		return lastInstructDate;
	}

	public void setLastInstructDate(Date lastInstructDate) {
		this.lastInstructDate = lastInstructDate;
	}

	public String getLastInstructNo() {
		return lastInstructNo;
	}

	public void setLastInstructNo(String lastInstructNo) {
		this.lastInstructNo = lastInstructNo;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getLastPromoteDate() {
		return lastPromoteDate;
	}

	public void setLastPromoteDate(Date lastPromoteDate) {
		this.lastPromoteDate = lastPromoteDate;
	}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getMilitatyStatus() {
		return militatyStatus;
	}

	public void setMilitatyStatus(String militatyStatus) {
		this.militatyStatus = militatyStatus;
	}

	public String getMotherAddress() {
		return motherAddress;
	}

	public void setMotherAddress(String motherAddress) {
		this.motherAddress = motherAddress;
	}

	public String getMotherCareer() {
		return motherCareer;
	}

	public void setMotherCareer(String motherCareer) {
		this.motherCareer = motherCareer;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getMotherOffice() {
		return motherOffice;
	}

	public void setMotherOffice(String motherOffice) {
		this.motherOffice = motherOffice;
	}

	public String getMotherStatus() {
		return motherStatus;
	}

	public void setMotherStatus(String motherStatus) {
		this.motherStatus = motherStatus;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPager() {
		return pager;
	}

	public void setPager(String pager) {
		this.pager = pager;
	}

	public String getPayFlag() {
		return payFlag;
	}

	public void setPayFlag(String payFlag) {
		this.payFlag = payFlag;
	}

	public String getPreName() {
		return preName;
	}

	public void setPreName(String preName) {
		this.preName = preName;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getRefAddress() {
		return refAddress;
	}

	public void setRefAddress(String refAddress) {
		this.refAddress = refAddress;
	}

	public String getRefCareer() {
		return refCareer;
	}

	public void setRefCareer(String refCareer) {
		this.refCareer = refCareer;
	}

	public String getRefName() {
		return refName;
	}

	public void setRefName(String refName) {
		this.refName = refName;
	}

	public String getRefOffice() {
		return refOffice;
	}

	public void setRefOffice(String refOffice) {
		this.refOffice = refOffice;
	}

	public String getRefRelation() {
		return refRelation;
	}

	public void setRefRelation(String refRelation) {
		this.refRelation = refRelation;
	}

	public String getRefTel() {
		return refTel;
	}

	public void setRefTel(String refTel) {
		this.refTel = refTel;
	}

	public String getRefTelOff() {
		return refTelOff;
	}

	public void setRefTelOff(String refTelOff) {
		this.refTelOff = refTelOff;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getRetire() {
		return retire;
	}

	public void setRetire(String retire) {
		this.retire = retire;
	}

	public String getRetireFlag() {
		return retireFlag;
	}

	public void setRetireFlag(String retireFlag) {
		this.retireFlag = retireFlag;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public String getSalaryStep() {
		return salaryStep;
	}

	public void setSalaryStep(String salaryStep) {
		this.salaryStep = salaryStep;
	}

	public Date getScontactDate() {
		return scontactDate;
	}

	public void setScontactDate(Date scontactDate) {
		this.scontactDate = scontactDate;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSpouseAddress() {
		return spouseAddress;
	}

	public void setSpouseAddress(String spouseAddress) {
		this.spouseAddress = spouseAddress;
	}

	public String getSpouseCareer() {
		return spouseCareer;
	}

	public void setSpouseCareer(String spouseCareer) {
		this.spouseCareer = spouseCareer;
	}

	public String getSpouseTel() {
		return spouseTel;
	}

	public void setSpouseTel(String spouseTel) {
		this.spouseTel = spouseTel;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getTelHome() {
		return telHome;
	}

	public void setTelHome(String telHome) {
		this.telHome = telHome;
	}

	public String getTelMobile() {
		return telMobile;
	}

	public void setTelMobile(String telMobile) {
		this.telMobile = telMobile;
	}

	public Integer getTypeEng() {
		return typeEng;
	}

	public void setTypeEng(Integer typeEng) {
		this.typeEng = typeEng;
	}

	public Integer getTypeTha() {
		return typeTha;
	}

	public void setTypeTha(Integer typeTha) {
		this.typeTha = typeTha;
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

	public Integer getWarfree() {
		return warfree;
	}

	public void setWarfree(Integer warfree) {
		this.warfree = warfree;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public WgEmployeePK getWgEmployeePK() {
		return wgEmployeePK;
	}

	public void setWgEmployeePK(WgEmployeePK wgEmployeePK) {
		this.wgEmployeePK = wgEmployeePK;
	}

	public PnOrganization getRefPnOrganizationAct() {
		return refPnOrganizationAct;
	}

	public void setRefPnOrganizationAct(PnOrganization refPnOrganizationAct) {
		this.refPnOrganizationAct = refPnOrganizationAct;
	}



	
}
