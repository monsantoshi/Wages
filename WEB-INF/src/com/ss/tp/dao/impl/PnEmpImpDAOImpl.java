package com.ss.tp.dao.impl;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.PnEmpImpDAO;
import com.ss.tp.dto.PnEmpImpVO;
import com.ss.tp.dto.PnEmpImpEngNameVO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class PnEmpImpDAOImpl extends HibernateDaoSupport implements
		PnEmpImpDAO, Serializable {
	private JdbcTemplate jdbcTemplate;
	{

	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List findByCriteriaReport(Integer workYear, Integer workYearFrom,
			Integer workYearTo, String monthFrom, String monthTo) {
		List resultList = new ArrayList();
		List returnList = new ArrayList();

		String sql = "";
		sql = " select decode(count(n.emp_code),0,'ไม่พบข้อมูล รายงานพนักงาน/ลูกจ้างประจำบรรจุใหม่','รายงานพนักงาน/ลูกจ้างประจำบรรจุใหม่') "
				+ " , n.emp_code,db.prefix_name||' '||n.first_name||' '||n.last_name empname "
				+ " ,p.position_short||' '||decode(to_number(nvl(n.level_code,0)),0,NULL,to_number(n.level_code)) empposition, "
				+ " o.div_short mdivdesc,"
				+ " nvl(o.sec_desc,o.area_desc) msecdesc, "
				+ " n.idate, "
				+ "  o.ORG_DESC sorgdesc, "
				+ " n.ou_code moucode, "
				+ " o.ORG_DESC sorgdesc, "
				+ " n.admin_code "
				+ "FROM   	pn_position p,	"
				+ " pn_organization o,	pn_employee n,db_pre_suff db "
				+ " WHERE    n.ou_code	= '001' "
				+ " AND 	o.inactive 	= 'N' "
				+ " AND        o.ou_code           	= n.ou_code "
				+ " AND	o.code_seq      	= n.code_seq "
				+ " AND 	p.ou_code       	= n.ou_code "
				+ " AND	p.gwork_code    	= n.gwork_code "
				+ " AND 	p.position_code 	= n.position_code "
				+ " AND  n.emp_status = 'B' "
				+ " AND 	db.pre_suff_code(+) = n.pre_name "
				+
				// if(!workYear.equals("")){
				// sql+="\n and to_char(n.idate,'yyyy') ='"+workYear+"'";
				// }

				// if(!monthFrom.equals("") && !monthTo.equals("")){
				// sql+="\n and to_char(n.idate,'mm') between '"+monthFrom+
				// "' and '" + monthTo+"'";
				// }else if(!monthFrom.equals("") && monthTo.equals("")){
				// sql+="\n and to_char(n.idate,'mm') >='"+monthFrom+"'";
				// }

				// // sql+= "\n " +//
				// if(workYear!=null && workYear.intValue()!=0){
				// if(!monthFrom.equals("") && !monthTo.equals("")){
				// sql+="\n and
				// trim(er(to_char(n.idate,'yyyy')||'/'||TRIM(TO_CHAR(n.idate,'mm')))
				// between '"+workYear+"/"+monthFrom+"'"
				// +
				// "\n and '"+workYear+"/"+monthTo+"'" ;
				// }
				// }
				// sql+= "\n" +
				" and to_char(n.idate,'yyyy') ='"
				+ workYearFrom
				+ "'"
				+ " and to_char(n.idate,'mm') between '"
				+ monthFrom
				+ "' and '"
				+ monthTo
				+ "'"
				+ " GROUP BY o.div_seq,o.area_seq,o.sec_seq, n.ou_code, o.dept_seq,o.div_short,nvl(o.sec_desc,o.area_desc), "
				+ " n.idate, o.ORG_DESC,n.emp_code, "
				+ " db.prefix_name||' '||n.first_name||' '||n.last_name, "
				+ " p.position_short||' '||decode(to_number(nvl(n.level_code,0)),0,NULL,to_number(n.level_code)),"
				+ " n.admin_code, nvl(n.level_code, '0') "
				+ " ORDER BY o.div_seq,o.area_seq,o.sec_seq, n.emp_code "
				+ ",n.admin_code, nvl(n.level_code,'0')desc , " + "  n.ou_code";

		resultList = this.jdbcTemplate.queryForList(sql);
		for (int i = 0; i < resultList.size(); i++) {
			ListOrderedMap map = (ListOrderedMap) resultList.get(i);
			String mHeader = (String) map.getValue(0);
			String empCode = (String) map.getValue(1);
			String empName = (String) map.getValue(2);
			String mPosition = (String) map.getValue(3);
			String mDivdesc = (String) map.getValue(4);
			String mSecdesc = (String) map.getValue(5);
			Date iDate = (Date) map.getValue(6);
			// String eduFirst = (String) map.getValue(6);
			// String manReplace = (String) map.getValue(7);
			// String moveSeq = (String) map.getValue(8);
			// String moveOrg = (String) map.getValue(9);
			// Date dateSubmit = (Date)map.getValue(10);
			// String moveReason = (String) map.getValue(11);
			// String moveNote = (String) map.getValue(12);

			PnEmpImpVO vo = new PnEmpImpVO();
			vo.setMHeader(mHeader);
			vo.setEmpCode(empCode);
			vo.setEmpName(empName);
			vo.setMPosition(mPosition);
			vo.setMDivdesc(mDivdesc);
			vo.setMSecdesc(mSecdesc);
			vo.setIDate(iDate);

			// vo.setEduFirst(eduFirst);
			// vo.setManReplace(manReplace);
			// vo.setMoveSeq(moveSeq);
			// vo.setMoveOrg(moveOrg);
			// vo.setDateSubmit(dateSubmit);
			// vo.setMoveReason(moveReason);
			// vo.setMoveNote(moveNote);

			returnList.add(vo);

		}
		return returnList;
	}

	// return resultList;
	public List findByCriteriaReportEngNameNew(Integer workYear, Integer workYearFrom,
			Integer workYearTo, String monthFrom, String monthTo) {
		List resultList = new ArrayList();
		List returnList = new ArrayList();

		String sql = "";
		sql = " select decode(count(n.emp_code),0,'ไม่พบข้อมูล รายงานพนักงาน/ลูกจ้างประจำบรรจุใหม่','รายงานพนักงาน/ลูกจ้างประจำบรรจุใหม่') "
				+ " , n.emp_code,db.prefix_name||' '||n.first_name||' '||n.last_name empname "
				+ " ,p.position_short||' '||decode(to_number(nvl(n.level_code,0)),0,NULL,to_number(n.level_code)) empposition, "
				+ " o.div_short mdivdesc,"
				+ " nvl(o.sec_desc,o.area_desc) msecdesc, "
				+ " n.idate, "
				+ " decode(n.eng_prename,'006','MISS','007','MRS.','008','MR.')||' '||n.eng_name||' '||n.eng_lastname empEngName, "
				+ "  o.ORG_DESC sorgdesc, "
				+ " n.ou_code moucode, "
				+ " o.ORG_DESC sorgdesc, "
				+ " n.admin_code "
				+ "FROM   	pn_position p,	"
				+ " pn_organization o,	pn_employee n,db_pre_suff db "
				+ " WHERE    n.ou_code	= '001' "
				+ " AND 	o.inactive 	= 'N' "
				+ " AND        o.ou_code           	= n.ou_code "
				+ " AND	o.code_seq      	= n.code_seq "
				+ " AND 	p.ou_code       	= n.ou_code "
				+ " AND	p.gwork_code    	= n.gwork_code "
				+ " AND 	p.position_code 	= n.position_code "
				+ " AND  n.emp_status = 'B' "
				+ " AND 	db.pre_suff_code(+) = n.pre_name "
				+
				// if(!workYear.equals("")){
				// sql+="\n and to_char(n.idate,'yyyy') ='"+workYear+"'";
				// }

				// if(!monthFrom.equals("") && !monthTo.equals("")){
				// sql+="\n and to_char(n.idate,'mm') between '"+monthFrom+
				// "' and '" + monthTo+"'";
				// }else if(!monthFrom.equals("") && monthTo.equals("")){
				// sql+="\n and to_char(n.idate,'mm') >='"+monthFrom+"'";
				// }

				// // sql+= "\n " +//
				// if(workYear!=null && workYear.intValue()!=0){
				// if(!monthFrom.equals("") && !monthTo.equals("")){
				// sql+="\n and
				// trim(er(to_char(n.idate,'yyyy')||'/'||TRIM(TO_CHAR(n.idate,'mm')))
				// between '"+workYear+"/"+monthFrom+"'"
				// +
				// "\n and '"+workYear+"/"+monthTo+"'" ;
				// }
				// }
				// sql+= "\n" +
				" and to_char(n.idate,'yyyy') ='"
				+ workYearFrom
				+ "'"
				+ " and to_char(n.idate,'mm') between '"
				+ monthFrom
				+ "' and '"
				+ monthTo
				+ "'"
				+ " GROUP BY o.div_seq,o.area_seq,o.sec_seq, n.ou_code, o.dept_seq,o.div_short,nvl(o.sec_desc,o.area_desc), "
				+ " n.idate,decode(n.eng_prename,'006','MISS','007','MRS.','008','MR.')||' '||n.eng_name||' '||n.eng_lastname, o.ORG_DESC,n.emp_code, "
				+ " db.prefix_name||' '||n.first_name||' '||n.last_name, "
				+ " p.position_short||' '||decode(to_number(nvl(n.level_code,0)),0,NULL,to_number(n.level_code)),"
				+ " n.admin_code, nvl(n.level_code, '0') "
				+ " ORDER BY o.div_seq,o.area_seq,o.sec_seq, n.emp_code "
				+ ",n.admin_code, nvl(n.level_code,'0')desc , " + "  n.ou_code";

		resultList = this.jdbcTemplate.queryForList(sql);
		for (int i = 0; i < resultList.size(); i++) {
			ListOrderedMap map = (ListOrderedMap) resultList.get(i);
			String mHeader = (String) map.getValue(0);
			String empCode = (String) map.getValue(1);
			String empName = (String) map.getValue(2);
			String mPosition = (String) map.getValue(3);
			String mDivdesc = (String) map.getValue(4);
			String mSecdesc = (String) map.getValue(5);
			Date iDate = (Date) map.getValue(6);
			String empEngName = (String) map.getValue(7);
			// String eduFirst = (String) map.getValue(6);
			// String manReplace = (String) map.getValue(7);
			// String moveSeq = (String) map.getValue(8);
			// String moveOrg = (String) map.getValue(9);
			// Date dateSubmit = (Date)map.getValue(10);
			// String moveReason = (String) map.getValue(11);
			// String moveNote = (String) map.getValue(12);

			PnEmpImpEngNameVO vo = new PnEmpImpEngNameVO();
			vo.setMHeader(mHeader);
			vo.setEmpCode(empCode);
			vo.setEmpName(empName);
			vo.setMPosition(mPosition);
			vo.setMDivdesc(mDivdesc);
			vo.setMSecdesc(mSecdesc);
			vo.setIDate(iDate);
			vo.setEngEmpName(empEngName);
			

			// vo.setEduFirst(eduFirst);
			// vo.setManReplace(manReplace);
			// vo.setMoveSeq(moveSeq);
			// vo.setMoveOrg(moveOrg);
			// vo.setDateSubmit(dateSubmit);
			// vo.setMoveReason(moveReason);
			// vo.setMoveNote(moveNote);

			returnList.add(vo);

		}
		return returnList;
	}
	public List findByCriteriaReportEngNameOld(Integer workYear, Integer workYearFrom,
			Integer workYearTo, String monthFrom, String monthTo) {
		List resultList = new ArrayList();
		List returnList = new ArrayList();

		String sql = "";
		sql = " select decode(count(n.emp_code),0,'ไม่พบข้อมูล รายงานพนักงาน/ลูกจ้างประจำพ้นสภาพ','รายงานพนักงาน/ลูกจ้างประจำพ้นสภาพ') "
				+ " , n.emp_code,db.prefix_name||' '||n.first_name||' '||n.last_name empname "
				+ " ,p.position_short||' '||decode(to_number(nvl(n.level_code,0)),0,NULL,to_number(n.level_code)) empposition, "
				+ " o.div_short mdivdesc,"
				+ " nvl(o.sec_desc,o.area_desc) msecdesc, "
				+ " n.last_promote_date, "
				+ " decode(n.eng_prename,'006','MISS','007','MRS.','008','MR.')||' '||n.eng_name||' '||n.eng_lastname empEngName, "
				+ "  o.ORG_DESC sorgdesc, "
				+ " n.ou_code moucode, "
				+ " o.ORG_DESC sorgdesc, "
				+ " n.admin_code "
				+ "FROM   	pn_position p,	"
				+ " pn_organization o,	pn_employee n,db_pre_suff db "
				+ " WHERE    n.ou_code	= '001' "
				+ " AND 	o.inactive 	= 'N' "
				+ " AND        o.ou_code           	= n.ou_code "
				+ " AND	o.code_seq      	= n.code_seq "
				+ " AND 	p.ou_code       	= n.ou_code "
				+ " AND	p.gwork_code    	= n.gwork_code "
				+ " AND 	p.position_code 	= n.position_code "
				+ " AND  n.emp_status = 'R' "
				+ " AND 	db.pre_suff_code(+) = n.pre_name "
				+
				// if(!workYear.equals("")){
				// sql+="\n and to_char(n.idate,'yyyy') ='"+workYear+"'";
				// }

				// if(!monthFrom.equals("") && !monthTo.equals("")){
				// sql+="\n and to_char(n.idate,'mm') between '"+monthFrom+
				// "' and '" + monthTo+"'";
				// }else if(!monthFrom.equals("") && monthTo.equals("")){
				// sql+="\n and to_char(n.idate,'mm') >='"+monthFrom+"'";
				// }

				// // sql+= "\n " +//
				// if(workYear!=null && workYear.intValue()!=0){
				// if(!monthFrom.equals("") && !monthTo.equals("")){
				// sql+="\n and
				// trim(er(to_char(n.idate,'yyyy')||'/'||TRIM(TO_CHAR(n.idate,'mm')))
				// between '"+workYear+"/"+monthFrom+"'"
				// +
				// "\n and '"+workYear+"/"+monthTo+"'" ;
				// }
				// }
				// sql+= "\n" +
				" and to_char(n.last_promote_date,'yyyy') ='"
				+ workYearFrom
				+ "'"
				+ " and to_char(n.last_promote_date,'mm') between '"
				+ monthFrom
				+ "' and '"
				+ monthTo
				+ "'"
				+ " GROUP BY o.div_seq,o.area_seq,o.sec_seq, n.ou_code, o.dept_seq,o.div_short,nvl(o.sec_desc,o.area_desc), "
				+ " n.last_promote_date,decode(n.eng_prename,'006','MISS','007','MRS.','008','MR.')||' '||n.eng_name||' '||n.eng_lastname, o.ORG_DESC,n.emp_code, "
				+ " db.prefix_name||' '||n.first_name||' '||n.last_name, "
				+ " p.position_short||' '||decode(to_number(nvl(n.level_code,0)),0,NULL,to_number(n.level_code)),"
				+ " n.admin_code, nvl(n.level_code, '0') "
				+ " ORDER BY o.div_seq,o.area_seq,o.sec_seq, n.emp_code "
				+ ",n.admin_code, nvl(n.level_code,'0')desc , " + "  n.ou_code";

		resultList = this.jdbcTemplate.queryForList(sql);
		for (int i = 0; i < resultList.size(); i++) {
			ListOrderedMap map = (ListOrderedMap) resultList.get(i);
			String mHeader = (String) map.getValue(0);
			String empCode = (String) map.getValue(1);
			String empName = (String) map.getValue(2);
			String mPosition = (String) map.getValue(3);
			String mDivdesc = (String) map.getValue(4);
			String mSecdesc = (String) map.getValue(5);
			Date iDate = (Date) map.getValue(6);
			String engEmpName = (String) map.getValue(7);
			// String eduFirst = (String) map.getValue(6);
			// String manReplace = (String) map.getValue(7);
			// String moveSeq = (String) map.getValue(8);
			// String moveOrg = (String) map.getValue(9);
			// Date dateSubmit = (Date)map.getValue(10);
			// String moveReason = (String) map.getValue(11);
			// String moveNote = (String) map.getValue(12);

			PnEmpImpEngNameVO vo = new PnEmpImpEngNameVO();
			vo.setMHeader(mHeader);
			vo.setEmpCode(empCode);
			vo.setEmpName(empName);
			vo.setMPosition(mPosition);
			vo.setMDivdesc(mDivdesc);
			vo.setMSecdesc(mSecdesc);
			vo.setIDate(iDate);
			vo.setEngEmpName(engEmpName);

			// vo.setEduFirst(eduFirst);
			// vo.setManReplace(manReplace);
			// vo.setMoveSeq(moveSeq);
			// vo.setMoveOrg(moveOrg);
			// vo.setDateSubmit(dateSubmit);
			// vo.setMoveReason(moveReason);
			// vo.setMoveNote(moveNote);

			returnList.add(vo);

		}
		return returnList;
	}

}