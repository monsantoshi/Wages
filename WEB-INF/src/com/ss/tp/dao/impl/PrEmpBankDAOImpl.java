package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.PrEmpBankDAO;
import com.ss.tp.dto.PrEmpBankVO;
import com.ss.tp.dto.PrEmpKTBVO;

//import com.ss.tp.dto.RwPremiumEmployeeVO;
//import com.ss.tp.dto.RwPremiumReportByOrgVO;
//import com.ss.tp.model.RwPremium;

public class PrEmpBankDAOImpl extends HibernateDaoSupport implements
		PrEmpBankDAO, Serializable {
	List rwList = new ArrayList();

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public PrEmpBankDAOImpl() {
		Locale.setDefault(Locale.US);
	}

	public List prEmpBankReport(String userId, String evaOuCode,
			Integer evaYear, Integer evaPeriod) {
		List resultList = new ArrayList();
		List returnList = new ArrayList();

		String sql = "";
		sql = " SELECT  A.EMP_CODE EMP_CODE,TRIM(TO_CHAR(A.BANK_ID)) BANK_ID,DB.PREFIX_NAME||' '||PN.FIRST_NAME||' '||PN.LAST_NAME NAME,"
				+ " TO_CHAR( Pr_Desc.GET_NET_INCOME('001','"
				+ evaYear
				+ "','"
				+ evaPeriod
				+ "', A.EMP_CODE)) AMOUNT "
				+ "   FROM      PR_EMPLOYEE A,PN_EMPLOYEE PN,DB_PRE_SUFF DB ,PR_DOCUMENT PD "
				+ "    WHERE  A.OU_CODE    ='001'"
				+ "  AND       A.YEAR     ="
				+ evaYear
				+ "                              AND    A.PERIOD    ="
				+ evaPeriod
				+ "                              AND    A.PAY_STATUS = '1'"
				+ "                              AND       A.BANK_ID IS NOT NULL"
				+ "                              AND    NVL(A.FLAG_PR,'0')= '0'"
				+
				// " AND A.OU_CODE = F.OU_CODE"
				// +
				// " AND A.YEAR = F.YEAR"
				// +
				// " AND A.PERIOD = F.PERIOD"
				// +
				"                              AND PN.OU_CODE = A.OU_CODE"
				+ "                              AND PN.EMP_CODE = A.EMP_CODE"
				+ "                              AND PN.EMP_STATUS ='B'"
				+
				// " AND PN.OU_CODE = DB.OU_CODE "
				// +
				"                              AND PN.PRE_NAME = DB.PRE_SUFF_CODE   "
				+ "                              AND A.YEAR   = PD.YEAR"
				+ "                              AND A.PERIOD = PD.PERIOD"
				+ "                              AND PD.DOC_TYPE = 'SL' "
				+ "                              AND A.EMP_CODE = PD.EMP_CODE"
				+ "                              AND Pr_Desc.GET_NET_INCOME('001','"
				+ evaYear
				+ "','"
				+ evaPeriod
				+ "', A.EMP_CODE) > 0"
				+ "                              ORDER BY PD.DOC_NO ";

		resultList = this.jdbcTemplate.queryForList(sql);
		for (int i = 0; i < resultList.size(); i++) {
			ListOrderedMap map = (ListOrderedMap) resultList.get(i);
			String empCode = (String) map.getValue(0);
			String bankId = (String) map.getValue(1);
			String empName = (String) map.getValue(2);
			String amount = (String) map.getValue(3);
			// Double amount =(Double)map.getValue(3);

			// String eduFirst = (String) map.getValue(6);
			// String manReplace = (String) map.getValue(7);
			// String moveSeq = (String) map.getValue(8);
			// String moveOrg = (String) map.getValue(9);
			// Date dateSubmit = (Date)map.getValue(10);
			// String moveReason = (String) map.getValue(11);
			// String moveNote = (String) map.getValue(12);

			PrEmpBankVO vo = new PrEmpBankVO();

			vo.setEmpCode(empCode);
			vo.setBankId(bankId);
			vo.setEmpName(empName);
			vo.setAmount(amount);

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
	
	public List prEmpKTBReport(String userId, String evaOuCode,
			Integer evaYear, Integer evaPeriod) {
		List resultList = new ArrayList();
		List returnList = new ArrayList();

		String sql = "";
		sql = " SELECT  A.EMP_CODE,pr_desc.get_emp_name(A.OU_CODE,A.EMP_CODE) emp_name,A.REF_NO,TO_CHAR(A.TOT_AMT) TOT_AMT," 
				+ " TO_CHAR( Pr_Desc.GET_NET_KTB01('001','"
				+ evaYear
				+ "','"
				+ evaPeriod
				+ "', A.EMP_CODE)) AMOUNT, "
				+ " A.ERR_REMARK "
				+ "   FROM      PR_DAILY A "
				+ "    WHERE  A.OU_CODE    ='001'"
				+ "  AND       A.YEAR_PR     ="
				+ evaYear
				+ "                              AND    A.PERIOD_PR    ="
				+ evaPeriod
				+ "                              AND    A.INC_DEC_CODE = '60'"
				+ "                              ORDER BY A.EMP_CODE ";

		resultList = this.jdbcTemplate.queryForList(sql);
		for (int i = 0; i < resultList.size(); i++) {
			ListOrderedMap map = (ListOrderedMap) resultList.get(i);
			String empCode = (String) map.getValue(0);
                        String empName = (String) map.getValue(1);
			String memberNo = (String) map.getValue(2);
			String totAmt = (String) map.getValue(3);
			String amount = (String) map.getValue(4);
			String errRemark = (String) map.getValue(5);
			// Double amount =(Double)map.getValue(3);

			// String eduFirst = (String) map.getValue(6);
			// String manReplace = (String) map.getValue(7);
			// String moveSeq = (String) map.getValue(8);
			// String moveOrg = (String) map.getValue(9);
			// Date dateSubmit = (Date)map.getValue(10);
			// String moveReason = (String) map.getValue(11);
			// String moveNote = (String) map.getValue(12);

			PrEmpKTBVO vo = new PrEmpKTBVO();

			vo.setEmpCode(empCode);
                        vo.setEmpName(empName);
			vo.setMemberNo(memberNo);
			vo.setTotAmt(totAmt);
			vo.setAmount(amount);
			vo.setErrRemark(errRemark);

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
	
	public List prEmpKTBReport1(String userId, String evaOuCode,
			Integer evaYear, Integer evaPeriod) {
		List resultList = new ArrayList();
		List returnList = new ArrayList();

		String sql = "";
		sql = " SELECT  A.EMP_CODE EMP_CODE,TRIM(TO_CHAR(A.REF_NO)) MEMBER_NO, "
				+ " TO_CHAR(A.TOT_AMT) AMOUNT, "
				+ " TO_CHAR( Pr_Desc.GET_NET_KTB01('001','"
				+ evaYear
				+ "','"
				+ evaPeriod
				+ "', A.EMP_CODE)) TOT_AMT, "
				+" A.ERR_REMARK "
				+ "   FROM      PR_DAILY A "
				+ "    WHERE  A.OU_CODE    ='001'"
				+ "  AND       A.YEAR_PR     ="
				+ evaYear
				+ "                              AND    A.PERIOD_PR    ="
				+ evaPeriod
				+ "                              AND    A.INC_DEC_CODE  = '46'"
			    + "                              ORDER BY A.MEMBER_NO ";


		resultList = this.jdbcTemplate.queryForList(sql);
		for (int i = 0; i < resultList.size(); i++) {
			ListOrderedMap map = (ListOrderedMap) resultList.get(i);
			String empCode = (String) map.getValue(0);
			String memberNo = (String) map.getValue(1);
			String amount = (String) map.getValue(2);
			String totAmt = (String) map.getValue(3);
			String errRemark = (String) map.getValue(4);
			
					
			// Double amount =(Double)map.getValue(3);

			// String eduFirst = (String) map.getValue(6);
			// String manReplace = (String) map.getValue(7);
			// String moveSeq = (String) map.getValue(8);
			// String moveOrg = (String) map.getValue(9);
			// Date dateSubmit = (Date)map.getValue(10);
			// String moveReason = (String) map.getValue(11);
			// String moveNote = (String) map.getValue(12);

			PrEmpKTBVO vo = new PrEmpKTBVO();

			vo.setEmpCode(empCode);
			vo.setMemberNo(memberNo);
			vo.setAmount(amount);
			vo.setTotAmt(totAmt);
			vo.setErrRemark(errRemark);

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