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
public class FeeWgEmployeeText implements Serializable {
	private FeeWgEmployeeTextPK pk;
	private Double codeSeq;
	private Double codeSeqAct;
	private String flagPn;
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
	private PnOrganization refPnOrganizationWork;
	private DbPreSuff refDbPreSuff;
	private WgDuty refWgDuty;
	public FeeWgEmployeeText() {
		//super();
		// TODO Auto-generated constructor stub
	}
	public FeeWgEmployeeText(FeeWgEmployeeTextPK pk, Double codeSeq,
			Double codeSeqAct, String flagPn, String preName, String firstName,
			String lastName, String account, Integer salary, String salaryStep,
			String payFlag, Integer warfree, String sex, String maritalStatus,
			String clss, String empStatus, Date birthDate, String race,
			String nationality, String religion, Date idate, String gworkCode,
			String dutyCode, Integer dutySeq, String firstEduCode,
			Integer lastEduCode, String lastAdjustEdu, String levelCode,
			Date adminDate, String fundStatus, String ordain, String idcardNo,
			Date idcardExpire, String idcardIssue, String idcardProvince,
			String taxId, String bloodGroup, Integer weight, Integer height,
			Integer typeTha, Integer typeEng, String militatyStatus,
			String telHome, String telMobile, String pager, String fax,
			String email, String houseAddr, String houseSoi, String houseRoad,
			String houseDistrict, String houseAmphor, String houseProvince,
			String houseZipCode, String houseStatus, String censusStatus,
			String censusAddr, String censusSoi, String censusRoad,
			String censusDistrict, String censusAmphor, String censusProvince,
			String censusZipCode, String spouseAddress, String spouseStatus,
			String spouseCareer, String spouseOffice, String spouseTel,
			String fatherName, String fatherStatus, String fatherCareer,
			String fatherOffice, String fatherAddress, String motherName,
			String motherStatus, String motherCareer, String motherOffice,
			String motherAddress, String refName, String refRelation,
			String refCareer, String refOffice, String refTel,
			String refTelOff, String refAddress, String retireFlag,
			Integer hisSeqRet, Date lastPromoteDate, String lastInstructNo,
			Date lastInstructDate, String note, String creBy, Date creDate,
			String updBy, Date updDate, String house, String retire,
			Date dutyDate, String instructNo, Date instructDate,
			Date scontactDate, Date econtactDate,
			PnOrganization refPnOrganization,
			PnOrganization refPnOrganizationWork, DbPreSuff refDbPreSuff,
			WgDuty refWgDuty) {
		//super();
		this.pk = pk;
		this.codeSeq = codeSeq;
		this.codeSeqAct = codeSeqAct;
		this.flagPn = flagPn;
		this.preName = preName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.account = account;
		this.salary = salary;
		this.salaryStep = salaryStep;
		this.payFlag = payFlag;
		this.warfree = warfree;
		this.sex = sex;
		this.maritalStatus = maritalStatus;
		this.clss = clss;
		this.empStatus = empStatus;
		this.birthDate = birthDate;
		this.race = race;
		this.nationality = nationality;
		this.religion = religion;
		this.idate = idate;
		this.gworkCode = gworkCode;
		this.dutyCode = dutyCode;
		this.dutySeq = dutySeq;
		this.firstEduCode = firstEduCode;
		this.lastEduCode = lastEduCode;
		this.lastAdjustEdu = lastAdjustEdu;
		this.levelCode = levelCode;
		this.adminDate = adminDate;
		this.fundStatus = fundStatus;
		this.ordain = ordain;
		this.idcardNo = idcardNo;
		this.idcardExpire = idcardExpire;
		this.idcardIssue = idcardIssue;
		this.idcardProvince = idcardProvince;
		this.taxId = taxId;
		this.bloodGroup = bloodGroup;
		this.weight = weight;
		this.height = height;
		this.typeTha = typeTha;
		this.typeEng = typeEng;
		this.militatyStatus = militatyStatus;
		this.telHome = telHome;
		this.telMobile = telMobile;
		this.pager = pager;
		this.fax = fax;
		this.email = email;
		this.houseAddr = houseAddr;
		this.houseSoi = houseSoi;
		this.houseRoad = houseRoad;
		this.houseDistrict = houseDistrict;
		this.houseAmphor = houseAmphor;
		this.houseProvince = houseProvince;
		this.houseZipCode = houseZipCode;
		this.houseStatus = houseStatus;
		this.censusStatus = censusStatus;
		this.censusAddr = censusAddr;
		this.censusSoi = censusSoi;
		this.censusRoad = censusRoad;
		this.censusDistrict = censusDistrict;
		this.censusAmphor = censusAmphor;
		this.censusProvince = censusProvince;
		this.censusZipCode = censusZipCode;
		this.spouseAddress = spouseAddress;
		this.spouseStatus = spouseStatus;
		this.spouseCareer = spouseCareer;
		this.spouseOffice = spouseOffice;
		this.spouseTel = spouseTel;
		this.fatherName = fatherName;
		this.fatherStatus = fatherStatus;
		this.fatherCareer = fatherCareer;
		this.fatherOffice = fatherOffice;
		this.fatherAddress = fatherAddress;
		this.motherName = motherName;
		this.motherStatus = motherStatus;
		this.motherCareer = motherCareer;
		this.motherOffice = motherOffice;
		this.motherAddress = motherAddress;
		this.refName = refName;
		this.refRelation = refRelation;
		this.refCareer = refCareer;
		this.refOffice = refOffice;
		this.refTel = refTel;
		this.refTelOff = refTelOff;
		this.refAddress = refAddress;
		this.retireFlag = retireFlag;
		this.hisSeqRet = hisSeqRet;
		this.lastPromoteDate = lastPromoteDate;
		this.lastInstructNo = lastInstructNo;
		this.lastInstructDate = lastInstructDate;
		this.note = note;
		this.creBy = creBy;
		this.creDate = creDate;
		this.updBy = updBy;
		this.updDate = updDate;
		this.house = house;
		this.retire = retire;
		this.dutyDate = dutyDate;
		this.instructNo = instructNo;
		this.instructDate = instructDate;
		this.scontactDate = scontactDate;
		this.econtactDate = econtactDate;
		this.refPnOrganization = refPnOrganization;
		this.refPnOrganizationWork = refPnOrganizationWork;
		this.refDbPreSuff = refDbPreSuff;
		this.refWgDuty = refWgDuty;
	}
	public FeeWgEmployeeTextPK getPk() {
		return pk;
	}
	public void setPk(FeeWgEmployeeTextPK pk) {
		this.pk = pk;
	}
	public Double getCodeSeq() {
		return codeSeq;
	}
	public void setCodeSeq(Double codeSeq) {
		this.codeSeq = codeSeq;
	}
	public Double getCodeSeqAct() {
		return codeSeqAct;
	}
	public void setCodeSeqAct(Double codeSeqAct) {
		this.codeSeqAct = codeSeqAct;
	}
	public String getFlagPn() {
		return flagPn;
	}
	public void setFlagPn(String flagPn) {
		this.flagPn = flagPn;
	}
	public String getPreName() {
		return preName;
	}
	public void setPreName(String preName) {
		this.preName = preName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
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
	public String getPayFlag() {
		return payFlag;
	}
	public void setPayFlag(String payFlag) {
		this.payFlag = payFlag;
	}
	public Integer getWarfree() {
		return warfree;
	}
	public void setWarfree(Integer warfree) {
		this.warfree = warfree;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getClss() {
		return clss;
	}
	public void setClss(String clss) {
		this.clss = clss;
	}
	public String getEmpStatus() {
		return empStatus;
	}
	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public Date getIdate() {
		return idate;
	}
	public void setIdate(Date idate) {
		this.idate = idate;
	}
	public String getGworkCode() {
		return gworkCode;
	}
	public void setGworkCode(String gworkCode) {
		this.gworkCode = gworkCode;
	}
	public String getDutyCode() {
		return dutyCode;
	}
	public void setDutyCode(String dutyCode) {
		this.dutyCode = dutyCode;
	}
	public Integer getDutySeq() {
		return dutySeq;
	}
	public void setDutySeq(Integer dutySeq) {
		this.dutySeq = dutySeq;
	}
	public String getFirstEduCode() {
		return firstEduCode;
	}
	public void setFirstEduCode(String firstEduCode) {
		this.firstEduCode = firstEduCode;
	}
	public Integer getLastEduCode() {
		return lastEduCode;
	}
	public void setLastEduCode(Integer lastEduCode) {
		this.lastEduCode = lastEduCode;
	}
	public String getLastAdjustEdu() {
		return lastAdjustEdu;
	}
	public void setLastAdjustEdu(String lastAdjustEdu) {
		this.lastAdjustEdu = lastAdjustEdu;
	}
	public String getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
	public Date getAdminDate() {
		return adminDate;
	}
	public void setAdminDate(Date adminDate) {
		this.adminDate = adminDate;
	}
	public String getFundStatus() {
		return fundStatus;
	}
	public void setFundStatus(String fundStatus) {
		this.fundStatus = fundStatus;
	}
	public String getOrdain() {
		return ordain;
	}
	public void setOrdain(String ordain) {
		this.ordain = ordain;
	}
	public String getIdcardNo() {
		return idcardNo;
	}
	public void setIdcardNo(String idcardNo) {
		this.idcardNo = idcardNo;
	}
	public Date getIdcardExpire() {
		return idcardExpire;
	}
	public void setIdcardExpire(Date idcardExpire) {
		this.idcardExpire = idcardExpire;
	}
	public String getIdcardIssue() {
		return idcardIssue;
	}
	public void setIdcardIssue(String idcardIssue) {
		this.idcardIssue = idcardIssue;
	}
	public String getIdcardProvince() {
		return idcardProvince;
	}
	public void setIdcardProvince(String idcardProvince) {
		this.idcardProvince = idcardProvince;
	}
	public String getTaxId() {
		return taxId;
	}
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getTypeTha() {
		return typeTha;
	}
	public void setTypeTha(Integer typeTha) {
		this.typeTha = typeTha;
	}
	public Integer getTypeEng() {
		return typeEng;
	}
	public void setTypeEng(Integer typeEng) {
		this.typeEng = typeEng;
	}
	public String getMilitatyStatus() {
		return militatyStatus;
	}
	public void setMilitatyStatus(String militatyStatus) {
		this.militatyStatus = militatyStatus;
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
	public String getPager() {
		return pager;
	}
	public void setPager(String pager) {
		this.pager = pager;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHouseAddr() {
		return houseAddr;
	}
	public void setHouseAddr(String houseAddr) {
		this.houseAddr = houseAddr;
	}
	public String getHouseSoi() {
		return houseSoi;
	}
	public void setHouseSoi(String houseSoi) {
		this.houseSoi = houseSoi;
	}
	public String getHouseRoad() {
		return houseRoad;
	}
	public void setHouseRoad(String houseRoad) {
		this.houseRoad = houseRoad;
	}
	public String getHouseDistrict() {
		return houseDistrict;
	}
	public void setHouseDistrict(String houseDistrict) {
		this.houseDistrict = houseDistrict;
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
	public String getHouseStatus() {
		return houseStatus;
	}
	public void setHouseStatus(String houseStatus) {
		this.houseStatus = houseStatus;
	}
	public String getCensusStatus() {
		return censusStatus;
	}
	public void setCensusStatus(String censusStatus) {
		this.censusStatus = censusStatus;
	}
	public String getCensusAddr() {
		return censusAddr;
	}
	public void setCensusAddr(String censusAddr) {
		this.censusAddr = censusAddr;
	}
	public String getCensusSoi() {
		return censusSoi;
	}
	public void setCensusSoi(String censusSoi) {
		this.censusSoi = censusSoi;
	}
	public String getCensusRoad() {
		return censusRoad;
	}
	public void setCensusRoad(String censusRoad) {
		this.censusRoad = censusRoad;
	}
	public String getCensusDistrict() {
		return censusDistrict;
	}
	public void setCensusDistrict(String censusDistrict) {
		this.censusDistrict = censusDistrict;
	}
	public String getCensusAmphor() {
		return censusAmphor;
	}
	public void setCensusAmphor(String censusAmphor) {
		this.censusAmphor = censusAmphor;
	}
	public String getCensusProvince() {
		return censusProvince;
	}
	public void setCensusProvince(String censusProvince) {
		this.censusProvince = censusProvince;
	}
	public String getCensusZipCode() {
		return censusZipCode;
	}
	public void setCensusZipCode(String censusZipCode) {
		this.censusZipCode = censusZipCode;
	}
	public String getSpouseAddress() {
		return spouseAddress;
	}
	public void setSpouseAddress(String spouseAddress) {
		this.spouseAddress = spouseAddress;
	}
	public String getSpouseStatus() {
		return spouseStatus;
	}
	public void setSpouseStatus(String spouseStatus) {
		this.spouseStatus = spouseStatus;
	}
	public String getSpouseCareer() {
		return spouseCareer;
	}
	public void setSpouseCareer(String spouseCareer) {
		this.spouseCareer = spouseCareer;
	}
	public String getSpouseOffice() {
		return spouseOffice;
	}
	public void setSpouseOffice(String spouseOffice) {
		this.spouseOffice = spouseOffice;
	}
	public String getSpouseTel() {
		return spouseTel;
	}
	public void setSpouseTel(String spouseTel) {
		this.spouseTel = spouseTel;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getFatherStatus() {
		return fatherStatus;
	}
	public void setFatherStatus(String fatherStatus) {
		this.fatherStatus = fatherStatus;
	}
	public String getFatherCareer() {
		return fatherCareer;
	}
	public void setFatherCareer(String fatherCareer) {
		this.fatherCareer = fatherCareer;
	}
	public String getFatherOffice() {
		return fatherOffice;
	}
	public void setFatherOffice(String fatherOffice) {
		this.fatherOffice = fatherOffice;
	}
	public String getFatherAddress() {
		return fatherAddress;
	}
	public void setFatherAddress(String fatherAddress) {
		this.fatherAddress = fatherAddress;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getMotherStatus() {
		return motherStatus;
	}
	public void setMotherStatus(String motherStatus) {
		this.motherStatus = motherStatus;
	}
	public String getMotherCareer() {
		return motherCareer;
	}
	public void setMotherCareer(String motherCareer) {
		this.motherCareer = motherCareer;
	}
	public String getMotherOffice() {
		return motherOffice;
	}
	public void setMotherOffice(String motherOffice) {
		this.motherOffice = motherOffice;
	}
	public String getMotherAddress() {
		return motherAddress;
	}
	public void setMotherAddress(String motherAddress) {
		this.motherAddress = motherAddress;
	}
	public String getRefName() {
		return refName;
	}
	public void setRefName(String refName) {
		this.refName = refName;
	}
	public String getRefRelation() {
		return refRelation;
	}
	public void setRefRelation(String refRelation) {
		this.refRelation = refRelation;
	}
	public String getRefCareer() {
		return refCareer;
	}
	public void setRefCareer(String refCareer) {
		this.refCareer = refCareer;
	}
	public String getRefOffice() {
		return refOffice;
	}
	public void setRefOffice(String refOffice) {
		this.refOffice = refOffice;
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
	public String getRefAddress() {
		return refAddress;
	}
	public void setRefAddress(String refAddress) {
		this.refAddress = refAddress;
	}
	public String getRetireFlag() {
		return retireFlag;
	}
	public void setRetireFlag(String retireFlag) {
		this.retireFlag = retireFlag;
	}
	public Integer getHisSeqRet() {
		return hisSeqRet;
	}
	public void setHisSeqRet(Integer hisSeqRet) {
		this.hisSeqRet = hisSeqRet;
	}
	public Date getLastPromoteDate() {
		return lastPromoteDate;
	}
	public void setLastPromoteDate(Date lastPromoteDate) {
		this.lastPromoteDate = lastPromoteDate;
	}
	public String getLastInstructNo() {
		return lastInstructNo;
	}
	public void setLastInstructNo(String lastInstructNo) {
		this.lastInstructNo = lastInstructNo;
	}
	public Date getLastInstructDate() {
		return lastInstructDate;
	}
	public void setLastInstructDate(Date lastInstructDate) {
		this.lastInstructDate = lastInstructDate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
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
	public String getHouse() {
		return house;
	}
	public void setHouse(String house) {
		this.house = house;
	}
	public String getRetire() {
		return retire;
	}
	public void setRetire(String retire) {
		this.retire = retire;
	}
	public Date getDutyDate() {
		return dutyDate;
	}
	public void setDutyDate(Date dutyDate) {
		this.dutyDate = dutyDate;
	}
	public String getInstructNo() {
		return instructNo;
	}
	public void setInstructNo(String instructNo) {
		this.instructNo = instructNo;
	}
	public Date getInstructDate() {
		return instructDate;
	}
	public void setInstructDate(Date instructDate) {
		this.instructDate = instructDate;
	}
	public Date getScontactDate() {
		return scontactDate;
	}
	public void setScontactDate(Date scontactDate) {
		this.scontactDate = scontactDate;
	}
	public Date getEcontactDate() {
		return econtactDate;
	}
	public void setEcontactDate(Date econtactDate) {
		this.econtactDate = econtactDate;
	}
	public PnOrganization getRefPnOrganization() {
		return refPnOrganization;
	}
	public void setRefPnOrganization(PnOrganization refPnOrganization) {
		this.refPnOrganization = refPnOrganization;
	}
	public PnOrganization getRefPnOrganizationWork() {
		return refPnOrganizationWork;
	}
	public void setRefPnOrganizationWork(PnOrganization refPnOrganizationWork) {
		this.refPnOrganizationWork = refPnOrganizationWork;
	}
	public DbPreSuff getRefDbPreSuff() {
		return refDbPreSuff;
	}
	public void setRefDbPreSuff(DbPreSuff refDbPreSuff) {
		this.refDbPreSuff = refDbPreSuff;
	}
	public WgDuty getRefWgDuty() {
		return refWgDuty;
	}
	public void setRefWgDuty(WgDuty refWgDuty) {
		this.refWgDuty = refWgDuty;
	}
	


	
}
