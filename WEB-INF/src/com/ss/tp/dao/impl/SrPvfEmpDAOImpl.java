package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.ss.tp.dao.SrPvfEmpDAO;

import com.ss.tp.dto.PnEmpMoveVO;
import com.ss.tp.dto.SrPvfEmpVO;

public class SrPvfEmpDAOImpl extends HibernateDaoSupport implements
		SrPvfEmpDAO, Serializable {
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	
	public List findMDate(String ouCode)
			throws Exception {
	
		StringBuffer hql = new StringBuffer(0);
		hql.append("select to_char(a.mDate,'dd/mm/yyyy') from VPvfMDate a ");
		//hql.append("group by to_char(a.mDate,'dd/mm/yyyy') ");
		hql.append(" order by a.mDate desc ");

		
		List ls = this.getHibernateTemplate().find(hql.toString());
	    System.out.println(ls);
		return ls;
	}
	
	
	public List findChgRateDate(String ouCode)
			throws Exception {
		
		StringBuffer hql = new StringBuffer(0);
		hql.append("select to_char(a.rDate,'dd/mm/yyyy') from VPvfChgRateDate a ");
		hql.append("order by a.rDate desc ");

		
		List ls = this.getHibernateTemplate().find(hql.toString());
	    System.out.println(ls);
		return ls;
	}
	
	public List findEmpStatusDate(String ouCode)
			throws Exception {
		
		StringBuffer hql = new StringBuffer(0);
		hql.append("select to_char(a.rDate,'dd/mm/yyyy') from VPvfEmpStatusDate a ");
		hql.append("order by a.rDate desc ");

		
		List ls = this.getHibernateTemplate().find(hql.toString());
	    System.out.println(ls);
		return ls;
	}
	
	
	
	public List findChgMasterDate(String ouCode)
			throws Exception {
		
		StringBuffer hql = new StringBuffer(0);
		hql.append("select to_char(a.rDate,'dd/mm/yyyy') from VPvfChgMasterDate a ");
		//hql.append("group by to_char(a.rDate,'dd/mm/yyyy') ");
		hql.append("order by a.rDate desc ");

		

		List ls = this.getHibernateTemplate().find(hql.toString());
	    System.out.println(ls);
		return ls;
	}
	
	public List CTPFRP207(String ouCode,String ageYear,
			String dDate) {
		List resultList = new ArrayList();
		List returnList = new ArrayList();
		String sql = "";
		sql = " SELECT A.OU_CODE,C.DIV_DESC "
				+ " , nvl(c.sec_desc,c.area_desc) "
				+ " , C.WORK_DESC "
				+ " , C.ZIP_CODE "
				+ " , E.EMP_CODE||'/'||E.GWORK_CODE "
				+ " ,HRPVF.PN_GET_NAME(A.EMP_CODE,A.OU_CODE) "
				+ " ,Pn_Desc.get_emp_position_short(A.OU_CODE,A.EMP_CODE) ||' '||TO_NUMBER(E.LEVEL_CODE) "
				+ " ,A.CAT_DATE " + ",DECODE( '"
				+ ageYear
				+ "','10', ADD_MONTHS (A.CAT_DATE, 10 * 12),'20',ADD_MONTHS(A.CAT_DATE,20*12) ) "
				+ " ,to_char(A.COLLECTR) "
				+ " ,to_char(A.COLLECTR+1)   "
				+ " ,to_char(A.JOINR) "
				+ " ,DECODE('"
				+ ageYear
				+ "','10','10','20','11')  "
				+ " ,A.UPD_DATE "
				+ " FROM    HRPVF.PF_MEMBER A , PN_EMPLOYEE  E "
				+ " ,PN_ORGANIZATION C "
				+ " WHERE C.OU_CODE = '"
				+ ouCode
				+ "' "
				+ " AND C.INACTIVE = 'N' "
				+ " AND E.OU_CODE = C.OU_CODE "
				+ " AND E.CODE_SEQ_ACT = C.CODE_SEQ "
				+ " AND A.OU_CODE = E.OU_CODE "
				+ " AND A.EMP_CODE = E.EMP_CODE "
				+ " AND E.EMP_STATUS ='B' "
				+ " AND (  (TO_NUMBER('"
				+ ageYear
				+ "' ) = 10  AND  A.JOINR = 9          AND (TRUNC(MONTHS_BETWEEN(TO_DATE('"
				+ dDate
				+ "','DD/MM/YYYY'),A.CAT_DATE)/12)) >= 10 "
				+ " AND (TRUNC(MONTHS_BETWEEN(TO_DATE('"
				+ dDate
				+ "','DD/MM/YYYY'),A.CAT_DATE)/12)) <   20 )  OR "
				+ "(TO_NUMBER('"
				+ ageYear
				+ "' ) = 20  AND  A.JOINR IN(9,10)  AND (TRUNC(MONTHS_BETWEEN(TO_DATE('"
				+ dDate
				+ "','DD/MM/YYYY'),A.CAT_DATE)/12)) >= 20)  ) "
				+ " AND A.MEMBER_STATUS = 'B' "
				+ " group by  A.OU_CODE,C.DIV_DESC "
				+ " ,NVL(C.AREA_SEQ,0) "
				+ " ,NVL(C.SEC_DESC,C.AREA_DESC)"
				+ " ,C.SEC_CODE "
				+ " ,C.SEC_DESC "
				+ " ,NVL(C.SEC_SEQ,0) "
				+ " ,NVL(C.WORK_SEQ,0) "
				+ " ,C.WORK_DESC "
				+ " ,C.ZIP_CODE "
				+ " ,E.EMP_CODE||'/'||E.GWORK_CODE "
				+ " ,HRPVF.PN_GET_NAME(A.EMP_CODE,A.OU_CODE ) "
				+ " ,A.EMP_CODE,E.LEVEL_CODE ,"
				+ " E.EMP_STATUS , "
				+ " A.CAT_DATE, "
				+ " DECODE('"
				+ ageYear
				+ "','10', ADD_MONTHS (A.CAT_DATE, 10 * 12),ADD_MONTHS(A.CAT_DATE,20*12) ) ,"
				+ " to_char(A.COLLECTR), "
				+ " to_char(A.JOINR), "
				+ " A.UPD_DATE, "
				+ " DECODE('"
				+ ageYear
				+ "','10','10','20','11') ,"
				+ " to_char(A.COLLECTR+1) , "
				+ " Pn_Desc.get_emp_position_short('"
				+ ouCode
				+ "' ,A.EMP_CODE) ||'/'||TO_NUMBER(E.LEVEL_CODE) "
				+ " ORDER BY nvl(AREA_SEQ,0),nvl( SEC_SEQ,0),nvl(WORK_SEQ,0),A.EMP_CODE ";
		System.out.println(sql);
		resultList = this.jdbcTemplate.queryForList(sql);
		for (int i = 0; i < resultList.size(); i++) {
			ListOrderedMap map = (ListOrderedMap) resultList.get(i);
			String ouId = (String) map.getValue(0);
			String divDesc = (String) map.getValue(1);
			String secDesc = (String) map.getValue(2);
			String workDesc = (String) map.getValue(3);
			String zipCode = (String) map.getValue(4);
			String empCode = (String) map.getValue(5);
			String fullName = (String) map.getValue(6);
			String position = (String) map.getValue(7);
			Date iDate = (Date) map.getValue(8);
			Date duDate = (Date) map.getValue(9);
			String oldC = (String) map.getValue(10);
			String newC = (String) map.getValue(11);
			String oldJ = (String) map.getValue(12);
			String newJ = (String) map.getValue(13);

			SrPvfEmpVO vo = new SrPvfEmpVO();
			vo.setOuCode(ouId);
			vo.setDivDesc(divDesc);
			vo.setSecDesc(secDesc);
			vo.setWorkDesc(workDesc);
			vo.setZipCode(zipCode);
			vo.setEmpCode(empCode);
			vo.setFullName(fullName);
			vo.setPosition(position);
			vo.setiDate(iDate);
			vo.setdDate(duDate);
			vo.setOldC(oldC);
			vo.setNewC(newC);
			vo.setOldJ(oldJ);
			vo.setNewJ(newJ);

			returnList.add(vo);

		}
		return returnList;

	}

	public List CTPFRP070(String dDate) {
		// TODO Auto-generated method stub
		List resultList = new ArrayList();
		List returnList = new ArrayList();
		String sql = "";
		sql = " SELECT  HIS.EMP_CODE , "
				+ " HRPVF.GET_PRE_NAME(EMP.PRE_NAME)||' '||EMP.FIRST_NAME||' '||EMP.LAST_NAME , "
				+ " ORG.DIV_SHORT ,ORG.ZIP_CODE , "
				+ " M.CAT_DATE IDATE, "
				+ " to_char(HIS.COLLECTR) OLDC, "
				+ " to_char(HIS.NCOLLECTR) NEWC, "
				+ " to_char(HIS.JOINR) OLDJ, "
				+ " to_char(HIS.NJOINR) NEWJ, "
				+ " HIS.RESULT_DATE DDATE, "
				+ " HIS.UPD_DATE "
				+ " FROM     HRPVF.PF_CHANGE_RATE_HISTORY HIS,PN_EMPLOYEE EMP,PN_ORGANIZATION ORG,HRPVF.PF_MEMBER M "
				+ " WHERE  HIS.EMP_CODE               =             EMP.EMP_CODE "
				+ " AND        HIS.OU_CODE                 =             EMP.OU_CODE "
				+ " AND        HIS.EMP_CODE               =             M.EMP_CODE "
				+ " AND        HIS.OU_CODE                  =            M.OU_CODE "
				+ " AND        EMP.CODE_SEQ_ACT     =             ORG.CODE_SEQ "
				+ " AND        EMP.OU_CODE                =             ORG.OU_CODE "
				+ " AND        HIS.CONDITION               =             2"
				+ " AND        HIS.RESULT_DATE         =   TO_DATE('" + dDate + "','DD/MM/YYYY') "
				+ " AND        M.MEMBER_STATUS      =             'B'"
				+ " ORDER BY M.EMP_CODE ";
		System.out.println(sql);
		resultList = this.jdbcTemplate.queryForList(sql);
		for (int i = 0; i < resultList.size(); i++) {
			ListOrderedMap map = (ListOrderedMap) resultList.get(i);
			String empCode = (String) map.getValue(0);
			String fullName = (String) map.getValue(1);
			String divDesc = (String) map.getValue(2);
			String zipCode = (String) map.getValue(3);
			Date iDate = (Date) map.getValue(4);
			String oldC = (String) map.getValue(5);
			String newC = (String) map.getValue(6);
			String oldJ = (String) map.getValue(7);
			String newJ = (String) map.getValue(8);
			Date duDate = (Date) map.getValue(9);
			Date uDate = (Date) map.getValue(10);
			System.out.println(empCode);
			SrPvfEmpVO vo = new SrPvfEmpVO();
			vo.setEmpCode(empCode);
			vo.setFullName(fullName);
			vo.setDivDesc(divDesc);
			vo.setZipCode(zipCode);
			vo.setiDate(iDate);
			vo.setOldC(oldC);
		    vo.setNewC(newC);
			vo.setOldJ(oldJ);
			vo.setNewJ(newJ);
			vo.setdDate(duDate);
			vo.setUpdDate(uDate);
            
			returnList.add(vo);

		}
		return returnList;
	}

	public List CTPFRP029(String ouCode,String dDate) {
		// TODO Auto-generated method stub
		List resultList = new ArrayList();
		List returnList = new ArrayList();
		String sql = "";
		sql = " SELECT PN_EMPLOYEE.EMP_CODE, PN_DESC.GET_EMP_NAME(PN_EMPLOYEE.OU_CODE, PN_EMPLOYEE.EMP_CODE) FULLNAME, "
				+ " PN_ORGANIZATION.DIV_DESC,NVL(PN_ORGANIZATION.SEC_DESC,PN_ORGANIZATION.AREA_DESC) SEC_T,PN_ORGANIZATION.WORK_DESC,PN_ORGANIZATION.ZIP_CODE "
				+ " FROM         PN_EMPLOYEE, PN_ORGANIZATION "
				+ " WHERE     PN_EMPLOYEE.OU_CODE           = '"
				+ ouCode
				+ "' "
				+ " AND           PN_EMPLOYEE.FUND_STATUS IS NULL "
				+ " AND           PN_EMPLOYEE.EMP_STATUS = 'B' "
				+ " AND            PN_EMPLOYEE.EMP_CODE IN (SELECT E.EMP_CODE FROM PN_EMP_DATA_STATUS E WHERE E.STATUS_DATE =  TO_DATE('" + dDate + "','DD/MM/YYYY') group by E.EMP_CODE  ) "
				+ " AND            PN_EMPLOYEE.EMP_CODE NOT IN (SELECT PVF.EMP_CODE FROM HRPVF.PF_MEMBER PVF WHERE PVF.MEMBER_STATUS IS NOT NULL ) "
				+ " AND           PN_ORGANIZATION.OU_CODE   = PN_EMPLOYEE.OU_CODE "
				+ " AND           PN_ORGANIZATION.CODE_SEQ = PN_EMPLOYEE.CODE_SEQ "
				+ " AND           PN_ORGANIZATION.INACTIVE   = 'N' "
				+ " ORDER BY    pn_organization.div_seq, NVL(PN_ORGANIZATION.AREA_SEQ,0) , "
				+ "         NVL(PN_ORGANIZATION.SEC_SEQ,0),NVL(PN_ORGANIZATION.WORK_SEQ,0) ,PN_EMPLOYEE.EMP_CODE ";
		System.out.println(sql);
		resultList = this.jdbcTemplate.queryForList(sql);
		for (int i = 0; i < resultList.size(); i++) {
			ListOrderedMap map = (ListOrderedMap) resultList.get(i);
			String empCode = (String) map.getValue(0);
			String fullName = (String) map.getValue(1);
			String divDesc = (String) map.getValue(2);
			String secDesc = (String) map.getValue(3);
			String workDesc = (String) map.getValue(4);
			String zipCode = (String) map.getValue(5);

			SrPvfEmpVO vo = new SrPvfEmpVO();
			vo.setEmpCode(empCode);
			vo.setFullName(fullName);
			vo.setDivDesc(divDesc);
			vo.setSecDesc(secDesc);
			vo.setWorkDesc(workDesc);
			vo.setZipCode(zipCode);

			returnList.add(vo);

		}
		return returnList;
	}

	public List CTPFRP019(String dDate) {
		// TODO Auto-generated method stub
		List resultList = new ArrayList();
		List returnList = new ArrayList();
		String sql = "";
		sql = "	SELECT  REQ.EMP_CODE, "
				+ " HRPVF.PN_GET_NAME(REQ.EMP_CODE,REQ.OU_CODE) FULL_NAME, "
				+ "  ORG.DIV_DESC, "
				+ " NVL(ORG.SEC_DESC,ORG.AREA_DESC)  SEC_DESC, "
				+ " ORG.WORK_DESC ,ORG.ZIP_CODE , "
				+ " M.CAT_DATE IDATE, "
				+ " decode(REQ.MASTER,'1','ตราสารหนี้ 100%','3','ผสมลงทุนในตราสารทุน 20%','4','ผสมลงทุนในตราสารทุน 30%') OLDP, "
				+ " decode(REQ.NMASTER,'1','ตราสารหนี้ 100%','3','ผสมลงทุนในตราสารทุน 20%','4','ผสมลงทุนในตราสารทุน 30%') NEWP, "
				+ " REQ.RESULT_DATE    DDATE "
				+ " FROM     HRPVF.PF_REQUEST_CHANGE_MASTER REQ,PN_EMPLOYEE EMP,PN_ORGANIZATION ORG,HRPVF.PF_MEMBER M "
				+ " WHERE  REQ.EMP_CODE                =             EMP.EMP_CODE "
				+ " AND        REQ.OU_CODE                  =             EMP.OU_CODE "
				+ " AND        REQ.EMP_CODE                =             M.EMP_CODE "
				+ " AND        REQ.OU_CODE                  =             M.OU_CODE "
				+ " AND        EMP.CODE_SEQ_ACT       =             ORG.CODE_SEQ "
				+ " AND        EMP.OU_CODE                  =             ORG.OU_CODE "
				+ " AND        REQ.RESULT_DATE =             TO_DATE('"	+ dDate	+ "','DD/MM/YYYY')  "
				+ " ORDER BY " 
				+ "ORG.DIV_SEQ, "
				+ "       NVL(ORG.AREA_SEQ,0) , "
				+ "        NVL(ORG.SEC_SEQ,0) , "
				+ "         NVL(ORG.WORK_SEQ,0) , "
				+ " REQ.RESULT_DATE,REQ.EMP_CODE ";
		System.out.println(sql);
		resultList = this.jdbcTemplate.queryForList(sql);
		for (int i = 0; i < resultList.size(); i++) {
			ListOrderedMap map = (ListOrderedMap) resultList.get(i);
			String empCode = (String) map.getValue(0);
			String fullName = (String) map.getValue(1);
			String divDesc = (String) map.getValue(2);
			String secDesc = (String) map.getValue(3);
			String workDesc = (String) map.getValue(4);
			String zipCode = (String) map.getValue(5);
			Date iDate = (Date) map.getValue(6);
			String oldP = (String) map.getValue(7);
			String newP = (String) map.getValue(8);
			Date duDate = (Date) map.getValue(9);

			SrPvfEmpVO vo = new SrPvfEmpVO();
			vo.setEmpCode(empCode);
			vo.setFullName(fullName);
			vo.setDivDesc(divDesc);
			vo.setSecDesc(secDesc);
			vo.setWorkDesc(workDesc);
			vo.setZipCode(zipCode);
			vo.setiDate(iDate);
			vo.setOldP(oldP);
			vo.setNewP(newP);
			vo.setdDate(duDate);

			returnList.add(vo);

		}
		return returnList;
	}

	public List CTPFRP034(String dDate) {
		// TODO Auto-generated method stub
		List resultList = new ArrayList();
		List returnList = new ArrayList();
		String sql = "";
		sql = "	SELECT PF.EMP_CODE, "
				+ " HRPVF.PN_GET_NAME(PF.EMP_CODE, PF.OU_CODE) FULLNAME, "
				+ " PN_ORGANIZATION.DIV_CODE, PN_ORGANIZATION.DIV_SHORT, PN_ORGANIZATION.SEC_DESC, "
				+ " decode(PF.MASTER,'1','ตราสารหนี้','3','ผสม 20%','4','ผสม 30%') MASTER,pn_organization.zip_code,PF.MDATE "
				+ " FROM           HRPVF.PF_MEMBER PF,  PN_EMPLOYEE, PN_ORGANIZATION "
				+ " WHERE       MDATE = TO_DATE('"+ dDate + "','DD/MM/YYYY')  "
				+ " AND              PF.OU_CODE =  PN_EMPLOYEE.OU_CODE "
				+ " AND              PF.EMP_CODE = PN_EMPLOYEE.EMP_CODE "
				+ " AND              PN_ORGANIZATION.OU_CODE =  PN_EMPLOYEE.OU_CODE "
				+ " AND              PN_EMPLOYEE.CODE_SEQ_ACT = PN_ORGANIZATION.CODE_SEQ "
				+ " ORDER BY   PF.MEMBER_STATUS, PF.EMP_CODE ";
		System.out.println(sql);
		resultList = this.jdbcTemplate.queryForList(sql);
		for (int i = 0; i < resultList.size(); i++) {
			ListOrderedMap map = (ListOrderedMap) resultList.get(i);
			String empCode = (String) map.getValue(0);
			String fullName = (String) map.getValue(1);
			String divCode = (String) map.getValue(2);
			String divDesc = (String) map.getValue(3);
			String secDesc = (String) map.getValue(4);
			String master = (String) map.getValue(5);
			String zipCode = (String) map.getValue(6);
			Date mDate = (Date) map.getValue(7);

			SrPvfEmpVO vo = new SrPvfEmpVO();
			vo.setEmpCode(empCode);
			vo.setFullName(fullName);
			vo.setDivCode(divCode);
			vo.setDivDesc(divDesc);
			vo.setSecDesc(secDesc);
			vo.setMaster(master);
			vo.setZipCode(zipCode);
			vo.setdDate(mDate);

			returnList.add(vo);
		}
		return returnList;
	}
	
	public List CTPFRP001(String ouCode, String status) {
		// TODO Auto-generated method stub
		List resultList = new ArrayList();
		List returnList = new ArrayList();
		String sql = "";
		sql = "	SELECT PF.EMP_CODE, "
				+ " HRPVF.PN_GET_NAME(PF.EMP_CODE, PF.OU_CODE) FULLNAME, "
				+ " PN_ORGANIZATION.DIV_CODE, PN_ORGANIZATION.DIV_SHORT, PN_ORGANIZATION.SEC_DESC, "
				+ " decode(PF.MASTER,'1','ตราสารหนี้','3','ผสม 20%','4','ผสม 30%') MASTER,pn_organization.zip_code, "
				+ " Pn_Desc.get_emp_position_short(PF.OU_CODE,PF.EMP_CODE) ||' '||TO_NUMBER(PN_EMPLOYEE.LEVEL_CODE), "
				+ " to_char(PF.COLLECTR) "
				+ " ,to_char(PF.JOINR) "
				+ " ,PF.CAT_DATE "
				+ " ,PF.MDATE"
				+ " FROM           HRPVF.PF_MEMBER PF,  PN_EMPLOYEE, PN_ORGANIZATION "
				+ " WHERE   PF.OU_CODE = '"+ ouCode + "' "
				+ " AND              PN_EMPLOYEE.OU_CODE =  '"+ ouCode + "' "
				+ " AND              PF.EMP_CODE = PN_EMPLOYEE.EMP_CODE "
				+ " AND              PF.OU_CODE = PN_EMPLOYEE.OU_CODE "
				+ " AND              PN_ORGANIZATION.OU_CODE =  '"+ ouCode + "' "
				+ " AND              PF.MEMBER_STATUS =  '"+ status + "' "
				+ " AND              PN_EMPLOYEE.OU_CODE = PN_ORGANIZATION.OU_CODE "
				+ " AND              PN_EMPLOYEE.CODE_SEQ_ACT = PN_ORGANIZATION.CODE_SEQ "
				+ " ORDER BY   PF.MEMBER_STATUS, PF.EMP_CODE ";
		System.out.println(sql);
		resultList = this.jdbcTemplate.queryForList(sql);
		for (int i = 0; i < resultList.size(); i++) {
			ListOrderedMap map = (ListOrderedMap) resultList.get(i);
			String empCode = (String) map.getValue(0);
			String fullName = (String) map.getValue(1);
			String divCode = (String) map.getValue(2);
			String divDesc = (String) map.getValue(3);
			String secDesc = (String) map.getValue(4);
			String master = (String) map.getValue(5);
			String zipCode = (String) map.getValue(6);
			String position = (String) map.getValue(7);
			String newC = (String) map.getValue(8);
			String newJ = (String) map.getValue(9);
			Date iDate = (Date) map.getValue(10);
			Date dDate = (Date) map.getValue(11);

			SrPvfEmpVO vo = new SrPvfEmpVO();
			vo.setEmpCode(empCode);
			vo.setFullName(fullName);
			vo.setDivCode(divCode);
			vo.setDivDesc(divDesc);
			vo.setSecDesc(secDesc);
			vo.setMaster(master);
			vo.setZipCode(zipCode);
			vo.setPosition(position);
			vo.setNewC(newC);
			vo.setNewJ(newJ);
			vo.setiDate(iDate);
			vo.setdDate(dDate);
			
			

			returnList.add(vo);
		}
		return returnList;
	}

	public List findLastDate(String ouCode)
			throws Exception {
		
		StringBuffer hql = new StringBuffer(0);
		hql.append("select to_char(a.lDate,'dd/mm/yyyy') from VPvfLastDate a ");
		hql.append("order by a.lDate desc ");

		
		List ls = this.getHibernateTemplate().find(hql.toString());
	    System.out.println(ls);
		return ls;
	}
	
}
