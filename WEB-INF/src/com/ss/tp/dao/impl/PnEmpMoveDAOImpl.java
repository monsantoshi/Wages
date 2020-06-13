package com.ss.tp.dao.impl;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.PnEmpMoveDAO;
import com.ss.tp.dto.PnEmpMoveVO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class PnEmpMoveDAOImpl extends HibernateDaoSupport implements
		PnEmpMoveDAO, Serializable {
	private JdbcTemplate jdbcTemplate;
	{

	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List findByCriteriaReport1(String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo, String choiceStatus,
			String choiceGroup) {
		List resultList = new ArrayList();
		List returnList = new ArrayList();
		String sql = "";
		sql = " select decode(count(m.emp_code),0,'��辺������ ��§ҹ���������͡��u��ѧ�Ѵ��������','��§ҹ���������͡��u��ѧ�Ѵ��������') "
				+ " , m.emp_code,db.prefix_name||' '||n.first_name||' '||n.last_name empname "
				+ " ,p.position_short||' '||decode(to_number(nvl(n.level_code,0)),0,NULL,to_number(n.level_code)) empposition, "
				+ " h.div_short mdivdesc,"
				+ " nvl(h.sec_desc,h.area_desc) msecdesc, "
				+
				/*
				 * " decode(n.last_adjust_edu, null,
				 * pn_desc.get_edu_desc(n.ou_code,
				 * n.first_edu_code),'*'||pn_desc.get_edu_desc(n.ou_code,
				 * n.last_adjust_edu)) edu_desc " +8
				 */
				"	 decode (pn_desc.get_emp_edu_apply_desc('001',n.emp_code,1),null,null,'1. '||trim(pn_desc.get_emp_edu_apply_desc('001',n.EMP_CODE,1))) edu_desc, "
				+ "    decode (pn_desc.get_emp_edu_apply_desc('001',n.emp_code,2),null,null,'2. '||trim(pn_desc.get_emp_edu_apply_desc('001',n.EMP_CODE,2))) sec_edu, "
				+ "     decode (pn_desc.get_emp_edu_apply_desc('001',n.emp_code,3),null,null,'3. '||trim(pn_desc.get_emp_edu_apply_desc('001',n.EMP_CODE,3))) third_edu, "
				+ "      decode (pn_desc.get_emp_edu_apply_desc('001',n.emp_code,4),null,null,'4. '||trim(pn_desc.get_emp_edu_apply_desc('001',n.EMP_CODE,4))) forth_edu, "
				+ "  decode (pn_desc.get_emp_edu_apply_desc('001',n.emp_code,5),null,null,'5. '||trim(pn_desc.get_emp_edu_apply_desc('001',n.EMP_CODE,5))) fifth_edu "
				+ " ,decode(m.kind_man_replace,'0','�����ѵ�ҷ�᷹','1','��ѡ�ҹ/�١��ҧ','2','�١��ҧ','3','��ѡ�ҹ') man, "
				+ " to_char( s.move_seq), "
				+ "  o.ORG_DESC sorgdesc, "
				+ "  s.DATE_SUBMIT, "
				+ "  decode(s.reason,'1','8.1','2','8.2','3','8.3','4','8.4','5','8.5') sreason, "
				+ "  m.note, "
				+ " m.group_t,s.MOVE_STATUS smovestatus "
				+ ",m.move_no mmoveno,m.ou_code moucode "
				+ ",s.ORG_CODE sorgcode,o.ORG_DESC sorgdesc,s.move_seq, "
				+ " n.admin_code,r.reason_detail "
				+ "FROM   	pn_emp_set_place s,	pn_position p,pn_employee  n,	"
				+ "pn_organization o,	pn_emp_move m,db_pre_suff db,pn_organization h,pn_move_reason r "
				+ " WHERE  s.ou_code 	= '001' "
				+ " AND 	m.ou_code          	= '001' "
				+ " AND	    n.ou_code	= '001' "
				+ " AND 	o.inactive 	= 'N' "
				+
				// "AND s.year = :YEAR" +
				" and m.move_status = s.move_status "
				+
				// " AND s.move_status = '"+ choiceStatus +"' " +
				" AND        o.ou_code           	= m.ou_code "
				+ " AND	m.ou_code	= s.ou_code "
				+ " AND	m.year		= s.year "
				+ " AND	m.emp_code	= s.emp_code "
				+ " and  m.move_no = s.move_no "
				+ " AND	n.ou_code 	= m.ou_code"
				+ " AND	n.emp_code 	= m.emp_code "
				+ " AND	o.ou_code       	= n.ou_code "
				+ " AND	h.code_seq      	= n.code_seq "
				+ " and s.CODE_SEQ = o.CODE_SEQ "
				+ " and s.OU_CODE = o.OU_CODE "
				+ " AND 	p.ou_code       	= n.ou_code "
				+ " AND	p.gwork_code    	= n.gwork_code "
				+ " AND 	p.position_code 	= n.position_code "
				+ " AND 	db.pre_suff_code(+) = n.pre_name "
				+ " and  s.ou_code = r.ou_code "
				+ " and  s.reason = r.reason_no ";
		if (!choiceStatus.equals("")) {
			sql += "\n and  m.move_status ='" + choiceStatus + "'";
		}
		if (!choiceGroup.equals("")) {
			sql += "\n and  m.group_t  = '" + choiceGroup + "'";
		}

		if (!orgCodeFrom.equals("") && !orgCodeTo.equals("")) {
			sql += "\n and  h.org_code between '" + orgCodeFrom + "' and  '"
					+ orgCodeTo + "'";
		} else if (!orgCodeFrom.equals("") && orgCodeTo.equals("")) {
			sql += "\n and  h.org_code  >='" + orgCodeFrom + "'";
		}
		if (!empCodeFrom.equals("") && !empCodeTo.equals("")) {
			sql += "\n and m.emp_code between '" + empCodeFrom + "' and  '"
					+ empCodeTo + "'";
		} else if (!empCodeFrom.equals("") && empCodeTo.equals("")) {
			sql += " and m.emp_code >='" + empCodeFrom + "'";
		}
		sql += "\n "
				+ " GROUP BY  m.group_t,s.DATE_SUBMIT,decode(s.reason,'1','8.1','2','8.2','3','8.3','4','8.4','5','8.5'),s.MOVE_STATUS,m.move_no, "
				+ " m.ou_code, h.dept_seq,h.div_short,nvl(h.sec_desc,h.area_desc),s.ORG_CODE, "
				+ " o.ORG_DESC,s.MOVE_STATUS,m.emp_code, s.move_seq, "
				+ " db.prefix_name||' '||n.first_name||' '||n.last_name, "
				+ "p.position_short||' '||decode(to_number(nvl(n.level_code,0)),0,NULL,to_number(n.level_code)),"
				+ " n.admin_code, nvl(n.level_code, '0'), decode (pn_desc.get_emp_edu_apply_desc('001',n.emp_code,1),null,null,'1. '||trim(pn_desc.get_emp_edu_apply_desc('001',n.EMP_CODE,1))), "
				+ "    decode (pn_desc.get_emp_edu_apply_desc('001',n.emp_code,2),null,null,'2. '||trim(pn_desc.get_emp_edu_apply_desc('001',n.EMP_CODE,2))), "
				+ "     decode (pn_desc.get_emp_edu_apply_desc('001',n.emp_code,3),null,null,'3. '||trim(pn_desc.get_emp_edu_apply_desc('001',n.EMP_CODE,3))), "
				+ "      decode (pn_desc.get_emp_edu_apply_desc('001',n.emp_code,4),null,null,'4. '||trim(pn_desc.get_emp_edu_apply_desc('001',n.EMP_CODE,4))), "
				+ "  decode (pn_desc.get_emp_edu_apply_desc('001',n.emp_code,5),null,null,'5. '||trim(pn_desc.get_emp_edu_apply_desc('001',n.EMP_CODE,5))) ,r.reason_detail,decode(m.kind_man_replace,'0','���ͷ�᷹','1','��ѡ�ҹ/�١��ҧ','2','�١��ҧ','3','��ѡ�ҹ'),m.note"
				+ " ORDER BY m.group_t desc,"
				+ " decode(s.reason,'1','8.1','2','8.2','3','8.3','4','8.4','5','8.5'),s.DATE_SUBMIT,m.emp_code,s.move_seq,s.MOVE_STATUS "
				+ ",n.admin_code, nvl(n.level_code,'0')desc , "
				+ "  m.ou_code,"
				+ " decode(m.kind_man_replace,'0','�����ѵ�ҷ�᷹','1','��ѡ�ҹ/�١��ҧ','2','�١��ҧ','3','��ѡ�ҹ') ";

		resultList = this.jdbcTemplate.queryForList(sql);
		for (int i = 0; i < resultList.size(); i++) {
			ListOrderedMap map = (ListOrderedMap) resultList.get(i);
			String mHeader = (String) map.getValue(0);
			String empCode = (String) map.getValue(1);
			String empName = (String) map.getValue(2);
			String mPosition = (String) map.getValue(3);
			String mDivdesc = (String) map.getValue(4);
			String mSecdesc = (String) map.getValue(5);
			String eduFirst = (String) map.getValue(6);
			String secEdu = (String) map.getValue(7);
			String thirdEdu = (String) map.getValue(8);
			String forthEdu = (String) map.getValue(9);
			String fifthEdu = (String) map.getValue(10);
			String manReplace = (String) map.getValue(11);
			String moveSeq = (String) map.getValue(12);
			String moveOrg = (String) map.getValue(13);
			Date dateSubmit = (Date) map.getValue(14);
			String moveReason = (String) map.getValue(15);
			String moveNote = (String) map.getValue(16);

			PnEmpMoveVO vo = new PnEmpMoveVO();
			vo.setHeader(mHeader);
			vo.setEmpCode(empCode);
			vo.setEmpName(empName);
			vo.setMPosition(mPosition);
			vo.setMDivdesc(mDivdesc);
			vo.setMSecdesc(mSecdesc);
			vo.setEduFirst(eduFirst);
			vo.setSecEdu(secEdu);
			vo.setThirdEdu(thirdEdu);
			vo.setForthEdu(forthEdu);
			vo.setFifthEdu(fifthEdu);
			vo.setManReplace(manReplace);
			vo.setMoveSeq(moveSeq);
			vo.setMoveOrg(moveOrg);
			vo.setDateSubmit(dateSubmit);
			vo.setMoveReason(moveReason);
			vo.setMoveNote(moveNote);

			returnList.add(vo);

		}
		return returnList;
	}

	// return resultList;
	public List findByCriteriaReport2(String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo, String choiceStatus,
			String choiceGroup) {
		List resultList = new ArrayList();
		List returnList = new ArrayList();
		String sql = "";
		sql = " select decode(count(m.emp_code),0,'��辺������ ��§ҹ���������͡����ѧ�Ѵ�������','��§ҹ���������͡����ѧ�Ѵ�������') "
				+ " , m.emp_code,db.prefix_name||' '||n.first_name||' '||n.last_name empname "
				+ " ,p.position_short||' '||decode(to_number(nvl(n.level_code,0)),0,NULL,to_number(n.level_code)) empposition, "
				+ " h.div_short mdivdesc,"
				+ " nvl(h.sec_desc,h.area_desc) msecdesc, "
				+
				/*
				 * " decode(n.last_adjust_edu, null,
				 * pn_desc.get_edu_desc(n.ou_code,
				 * n.first_edu_code),'*'||pn_desc.get_edu_desc(n.ou_code,
				 * n.last_adjust_edu)) edu_desc " +8
				 */
				"	 decode (pn_desc.get_emp_edu_apply_desc('001',n.emp_code,1),null,null,'1. '||trim(pn_desc.get_emp_edu_apply_desc('001',n.EMP_CODE,1))) edu_desc, "
				+ "    decode (pn_desc.get_emp_edu_apply_desc('001',n.emp_code,2),null,null,'2. '||trim(pn_desc.get_emp_edu_apply_desc('001',n.EMP_CODE,2))) sec_edu, "
				+ "     decode (pn_desc.get_emp_edu_apply_desc('001',n.emp_code,3),null,null,'3. '||trim(pn_desc.get_emp_edu_apply_desc('001',n.EMP_CODE,3))) third_edu, "
				+ "      decode (pn_desc.get_emp_edu_apply_desc('001',n.emp_code,4),null,null,'4. '||trim(pn_desc.get_emp_edu_apply_desc('001',n.EMP_CODE,4))) forth_edu, "
				+ "  decode (pn_desc.get_emp_edu_apply_desc('001',n.emp_code,5),null,null,'5. '||trim(pn_desc.get_emp_edu_apply_desc('001',n.EMP_CODE,5))) fifth_edu  "
				+ " ,decode(m.kind_man_replace,'0','���ͷ�᷹','1','��ѡ�ҹ/�١��ҧ','2','�١��ҧ','3','��ѡ�ҹ') man, "
				+ " to_char( s.move_seq), "
				+ "  o.ORG_DESC sorgdesc, "
				+ "  s.DATE_SUBMIT, "
				+ "  decode(s.reason,'1','8.1','2','8.2','3','8.3','4','8.4','5','8.5') sreason, "
				+ "  m.note, "
				+ " m.group_t,s.MOVE_STATUS smovestatus "
				+ ",m.move_no mmoveno,m.ou_code moucode "
				+ ",s.ORG_CODE sorgcode,o.ORG_DESC sorgdesc,s.move_seq, "
				+ " n.admin_code,r.reason_detail "
				+ "FROM   	pn_emp_set_place s,	pn_position p,pn_employee  n,	"
				+ "pn_organization o,	pn_emp_move m,db_pre_suff db,pn_organization h,pn_move_reason r "
				+ " WHERE  s.ou_code 	= '001' "
				+ " AND 	m.ou_code          	= '001' "
				+ " AND	    n.ou_code	= '001' "
				+ " AND 	o.inactive 	= 'N' "
				+
				// "AND s.year = :YEAR" +
				" and m.move_status = s.move_status "
				+
				// " AND s.move_status = '"+ choiceStatus +"' " +
				" AND        o.ou_code           	= m.ou_code "
				+ " AND	m.ou_code	= s.ou_code "
				+ " AND	m.year		= s.year "
				+ " AND	m.emp_code	= s.emp_code "
				+ " and  m.move_no = s.move_no "
				+ " AND	n.ou_code 	= m.ou_code"
				+ " AND	n.emp_code 	= m.emp_code "
				+ " AND	o.ou_code       	= n.ou_code "
				+ " AND	h.code_seq      	= n.code_seq "
				+ " and s.CODE_SEQ = o.CODE_SEQ "
				+ " and s.OU_CODE = o.OU_CODE "
				+ " AND 	p.ou_code       	= n.ou_code "
				+ " AND	p.gwork_code    	= n.gwork_code "
				+ " AND 	p.position_code 	= n.position_code "
				+ " AND 	db.pre_suff_code(+) = n.pre_name "
				+ " and  s.ou_code = r.ou_code "
				+ " and  s.reason = r.reason_no "
				+
				/*
				 * if(!choiceStatus.equals("")){ sql+="\n and m.move_status
				 * ='"+choiceStatus+"'"; } if(!choiceGroup.equals("")){ sql+="\n
				 * and m.group_t = '"+choiceGroup+"'"; }
				 * 
				 * if(!orgCodeFrom.equals("") && !orgCodeTo.equals("")){
				 * sql+="\n and o.org_code between '"+orgCodeFrom+ "' and '" +
				 * orgCodeTo+"'"; }else if(!orgCodeFrom.equals("") &&
				 * orgCodeTo.equals("")){ sql+="\n and o.org_code
				 * >='"+orgCodeFrom+"'"; } if(!empCodeFrom.equals("") &&
				 * !empCodeTo.equals("")){ sql+="\n and m.emp_code between
				 * '"+empCodeFrom+ "' and '" + empCodeTo+"'"; }else
				 * if(!empCodeFrom.equals("") && empCodeTo.equals("")){ sql+="
				 * and m.emp_code >='"+empCodeFrom+"'"; } sql+= "\n " +
				 */
				" GROUP BY  m.group_t,s.DATE_SUBMIT,decode(s.reason,'1','8.1','2','8.2','3','8.3','4','8.4','5','8.5'),s.MOVE_STATUS,m.move_no, "
				+ " m.ou_code, h.dept_seq,h.div_short,nvl(h.sec_desc,h.area_desc),s.ORG_CODE, "
				+ " o.ORG_DESC,s.MOVE_STATUS,m.emp_code, s.move_seq, "
				+ " db.prefix_name||' '||n.first_name||' '||n.last_name, "
				+ "p.position_short||' '||decode(to_number(nvl(n.level_code,0)),0,NULL,to_number(n.level_code)),"
				+ " n.admin_code, nvl(n.level_code, '0'),"
				+ "	 decode (pn_desc.get_emp_edu_apply_desc('001',n.emp_code,1),null,null,'1. '||trim(pn_desc.get_emp_edu_apply_desc('001',n.EMP_CODE,1))) , "
				+ "    decode (pn_desc.get_emp_edu_apply_desc('001',n.emp_code,2),null,null,'2. '||trim(pn_desc.get_emp_edu_apply_desc('001',n.EMP_CODE,2))), "
				+ "     decode (pn_desc.get_emp_edu_apply_desc('001',n.emp_code,3),null,null,'3. '||trim(pn_desc.get_emp_edu_apply_desc('001',n.EMP_CODE,3))), "
				+ "      decode (pn_desc.get_emp_edu_apply_desc('001',n.emp_code,4),null,null,'4. '||trim(pn_desc.get_emp_edu_apply_desc('001',n.EMP_CODE,4))) , "
				+ "  decode (pn_desc.get_emp_edu_apply_desc('001',n.emp_code,5),null,null,'5. '||trim(pn_desc.get_emp_edu_apply_desc('001',n.EMP_CODE,5))),  "
				+ "  r.reason_detail,decode(m.kind_man_replace,'0','���ͷ�᷹','1','��ѡ�ҹ/�١��ҧ','2','�١��ҧ','3','��ѡ�ҹ'),m.note"
				+ " ORDER BY m.group_t desc,"
				+ " decode(s.reason,'1','8.1','2','8.2','3','8.3','4','8.4','5','8.5'),s.DATE_SUBMIT,m.emp_code,s.move_seq,s.MOVE_STATUS "
				+ ",n.admin_code, nvl(n.level_code,'0')desc , "
				+ "  m.ou_code,"
				+ " decode(m.kind_man_replace,'0','���ͷ�᷹','1','��ѡ�ҹ/�١��ҧ','2','�١��ҧ','3','��ѡ�ҹ') ";

		resultList = this.jdbcTemplate.queryForList(sql);
		for (int i = 0; i < resultList.size(); i++) {
			ListOrderedMap map = (ListOrderedMap) resultList.get(i);
			String mHeader = (String) map.getValue(0);
			String empCode = (String) map.getValue(1);
			String empName = (String) map.getValue(2);
			String mPosition = (String) map.getValue(3);
			String mDivdesc = (String) map.getValue(4);
			String mSecdesc = (String) map.getValue(5);
			String eduFirst = (String) map.getValue(6);
			String secEdu = (String) map.getValue(7);
			String thirdEdu = (String) map.getValue(8);
			String forthEdu = (String) map.getValue(9);
			String fifthEdu = (String) map.getValue(10);
			String manReplace = (String) map.getValue(11);
			String moveSeq = (String) map.getValue(12);
			String moveOrg = (String) map.getValue(13);
			Date dateSubmit = (Date) map.getValue(14);
			String moveReason = (String) map.getValue(15);
			String moveNote = (String) map.getValue(16);

			PnEmpMoveVO vo = new PnEmpMoveVO();
			vo.setHeader(mHeader);
			vo.setEmpCode(empCode);
			vo.setEmpName(empName);
			vo.setMPosition(mPosition);
			vo.setMDivdesc(mDivdesc);
			vo.setMSecdesc(mSecdesc);
			vo.setEduFirst(eduFirst);
			vo.setSecEdu(secEdu);
			vo.setThirdEdu(thirdEdu);
			vo.setForthEdu(forthEdu);
			vo.setFifthEdu(fifthEdu);
			vo.setManReplace(manReplace);
			vo.setMoveSeq(moveSeq);
			vo.setMoveOrg(moveOrg);
			vo.setDateSubmit(dateSubmit);
			vo.setMoveReason(moveReason);
			vo.setMoveNote(moveNote);

			returnList.add(vo);

		}
		return returnList;
	}
	// return resultList;
}