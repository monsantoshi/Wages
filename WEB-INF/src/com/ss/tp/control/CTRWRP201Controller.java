/* BALL
 * Created on 31 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.control;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Blank;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;


import com.ss.tp.dto.CTRWRP201VO;
import com.ss.tp.dto.RwDangerVO;
import com.ss.tp.dto.RwHealthVO;
import com.ss.tp.dto.RwOvertimeVO;
import com.ss.tp.dto.RwPremiumVO;
import com.ss.tp.security.UserInfo;
import com.ss.tp.service.PrEmployeeService;
import com.ss.tp.service.SuOrganizeService;

/**
 * @author BALL
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class CTRWRP201Controller extends MultiActionController {

	/**
	 * @return Returns the taReportService.
	 */
	public PrEmployeeService getPrEmployeeService() {
		return (PrEmployeeService) this.getApplicationContext().getBean(
				"prEmployeeService");
	}

	public SuOrganizeService getSuOrganizeService() {
		return (SuOrganizeService) this.getApplicationContext().getBean(
				"suOrganizeService");
	}

	/**
	 * method ����Ѻ�ʴ���§ҹ�������¹�ŧ��������ѡ�ͧ�١��ҧ��Ш�
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @return
	 */
	public ModelAndView doPrintReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String ouCode = "";
		String userId = "";
		String year = "";
		String period = "";
		String periodName = "";

		DecimalFormat df = new DecimalFormat("###,##0.00");
		DecimalFormat dfInt = new DecimalFormat("###,##0");

		String key = null;
		String keyAll = null;
		CTRWRP201VO value = new CTRWRP201VO();
		CTRWRP201VO valueAll = new CTRWRP201VO();

		Hashtable hash = new Hashtable();
		Hashtable hashAll = new Hashtable();

		if (request.getParameter("ouCode") != null) {
			ouCode = request.getParameter("ouCode");
		}
		if (request.getParameter("userId") != null) {
			userId = request.getParameter("userId");
		}

		if (request.getParameter("year") != null
				&& !"".equals(request.getParameter("year"))) {
			year = String.valueOf(request.getParameter("year"));
		}
		if (request.getParameter("period") != null) {
			period = String.valueOf(request.getParameter("period"));
		}
		if (request.getParameter("section") != null) {
			periodName = request.getParameter("section");
		}

		List ctrwrp201 = this.getPrEmployeeService().findDetailPaySlip(ouCode,
				userId, year, period);

		// ---------------------------------Generate Report
		// Detail--------------------------------------------------

		WritableCellFormat headL4 = new WritableCellFormat();
		headL4.setBorder(Border.ALL, BorderLineStyle.THIN);
		WritableFont font2 = new WritableFont(WritableFont.ARIAL);
		font2.setBoldStyle(WritableFont.BOLD);
		font2.setPointSize(9);

		WritableFont font = new WritableFont(WritableFont.ARIAL);
		font.setPointSize(9);

		Alignment data2 = Alignment.getAlignment(1);
		headL4.setAlignment(data2.LEFT);
		headL4.setFont(font2);

		// RwOvertimeVO otvo=null;
		int startRow = 6;
		int i = 0;
		int j = 0;
		int s = 1; // Sheet's name
		String ouDesc = this.getSuOrganizeService().findOrganizeName(ouCode);

		String divCodeChk = "";
		String areaCodeChk = "";
		String secCodeChk = "";
		String incDecCodeChk = "";

		String cntEmp = "";
		String empA = "";
		String empI = "";
		String empD = "";

		String costLife = "";
		String gundan = "";
		String costChild = "";
		String sumCostChild = "";

		int vDouble = 0;

		List rpList = ctrwrp201;

		CTRWRP201VO vo = null;
		CTRWRP201VO vo2 = null;

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition",
				"attachment; filename=./page/report/CTRWRP201.xls");
		InputStream in = this.getServletContext().getResourceAsStream(
				"/page/report/CTRWRP201.xls");

		Workbook wb = Workbook.getWorkbook(in);
		wb.isProtected();
		WritableWorkbook ww = Workbook.createWorkbook(
				response.getOutputStream(), wb);
		WritableSheet sheet1 = ww.getSheet(0);
		ww.setProtected(true);

		// SheetSettings sst = new SheetSettings();
		// sst = wb.getSheet(0).getSettings();

		// ++++++++++Set Cell Format++++++++++
		CellFormat header = sheet1.getWritableCell(0, 0).getCellFormat();
		CellFormat headerRight = sheet1.getWritableCell(8, 2).getCellFormat();
		CellFormat headerLeft = sheet1.getWritableCell(0, 1).getCellFormat();
		// CellFormat normal = sheet1.getWritableCell(11,6).getCellFormat();
		CellFormat tbcell = sheet1.getWritableCell(9, 5).getCellFormat();

		WritableCellFormat subHead = new WritableCellFormat();
		subHead.setBorder(Border.LEFT, BorderLineStyle.THIN);
		subHead.setAlignment(Alignment.LEFT);
		subHead.setFont(font2);

		WritableFont fontBold = new WritableFont(WritableFont.ARIAL);
		fontBold.setBoldStyle(WritableFont.BOLD);
		fontBold.setPointSize(9);
		// ------------------//
		WritableCellFormat borderNumber2 = new WritableCellFormat();
		borderNumber2.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		borderNumber2.setBorder(Border.LEFT, BorderLineStyle.THIN);
		borderNumber2.setAlignment(Alignment.RIGHT);
		borderNumber2.setFont(font);

		WritableCellFormat borderString = new WritableCellFormat();
		borderString.setBorder(Border.LEFT, BorderLineStyle.THIN);
		borderString.setAlignment(Alignment.LEFT);
		borderString.setFont(font);

		WritableCellFormat HeadFormat = new WritableCellFormat();
		HeadFormat.setAlignment(Alignment.LEFT);
		HeadFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		HeadFormat.setFont(fontBold);

		WritableCellFormat normalCenter = new WritableCellFormat();
		normalCenter.setAlignment(Alignment.CENTRE);
		normalCenter.setVerticalAlignment(VerticalAlignment.CENTRE);
		normalCenter.setFont(font);

		WritableCellFormat normalLeft = new WritableCellFormat();
		normalLeft.setAlignment(Alignment.LEFT);
		normalLeft.setVerticalAlignment(VerticalAlignment.CENTRE);
		normalLeft.setFont(font);

		WritableCellFormat normalRight = new WritableCellFormat();
		normalRight.setAlignment(Alignment.RIGHT);
		normalRight.setVerticalAlignment(VerticalAlignment.CENTRE);
		normalRight.setFont(font);
		// ------------------//

		WritableCellFormat mergeCenterBoldBGGrayFormat = new WritableCellFormat();
		mergeCenterBoldBGGrayFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		mergeCenterBoldBGGrayFormat.setFont(fontBold);
		mergeCenterBoldBGGrayFormat.setBackground(Colour.GRAY_50);
		mergeCenterBoldBGGrayFormat.setAlignment(Alignment.CENTRE);
		mergeCenterBoldBGGrayFormat
				.setVerticalAlignment(VerticalAlignment.CENTRE);
		mergeCenterBoldBGGrayFormat.setWrap(true);

		GregorianCalendar gd = new GregorianCalendar();
		SimpleDateFormat sdfPrint = new SimpleDateFormat("dd/MM/yyyy HH:mm",
				new java.util.Locale("th", "TH"));
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		WritableCellFormat borderEnd = new WritableCellFormat();
		borderEnd.setBorder(Border.TOP, BorderLineStyle.THIN);

		WritableCellFormat noData = new WritableCellFormat();
		noData.setBorder(Border.ALL, BorderLineStyle.THIN);
		noData.setAlignment(Alignment.CENTRE);
		if (rpList.size() > 0) {
			try {

				divCodeChk = "";
				areaCodeChk = "";
				secCodeChk = "";

				sheet1.addCell(new Label(12, 5, "", tbcell));
				for (int k = 0; k < rpList.size(); k++) {
					vo = (CTRWRP201VO) rpList.get(k);
					if (k + 1 < rpList.size())
						vo2 = (CTRWRP201VO) rpList.get(k + 1);

					// loop set Hash
					if (!divCodeChk.equals(vo.getDivCode())
							|| !incDecCodeChk.equals(vo.getIncDecCode())
							|| !secCodeChk.equals(vo.getSecCode())) {
						key = vo.getDivCode().trim()
								+ vo.getIncDecCode().trim();
						CTRWRP201VO voTotal = new CTRWRP201VO();
						BeanUtils.copyProperties(voTotal, vo);
						value = (CTRWRP201VO) hash.get(key);
						if (value != null) {
							// sum value
							value.setCntEmp(new Long(Integer.parseInt(value
									.getCntEmp().toString())
									+ Integer.parseInt(vo.getCntEmp()
											.toString())));
							value.setEmpA(new Long(Integer.parseInt(value
									.getEmpA().toString())
									+ Integer.parseInt(vo.getEmpA().toString())));
							value.setEmpI(new Long(Integer.parseInt(value
									.getEmpI().toString())
									+ Integer.parseInt(vo.getEmpI().toString())));
							value.setEmpD(new Long(Integer.parseInt(value
									.getEmpD().toString())
									+ Integer.parseInt(vo.getEmpD().toString())));

							value.setCntRec(new Long(Integer.parseInt(value
									.getCntRec().toString())
									+ Integer.parseInt(vo.getCntRec()
											.toString())));
							value.setAmtRec(Double.valueOf(String.valueOf(value
									.getAmtRec().doubleValue()
									+ vo.getAmtRec().doubleValue())));
							value.setCntN(new Long(Integer.parseInt(value
									.getCntN().toString())
									+ Integer.parseInt(vo.getCntN().toString())));
							value.setAmtN(Double.valueOf(String.valueOf(value
									.getAmtN().doubleValue()
									+ vo.getAmtN().doubleValue())));
							value.setCntA(new Long(Integer.parseInt(value
									.getCntA().toString())
									+ Integer.parseInt(vo.getCntA().toString())));
							value.setAmtA(Double.valueOf(String.valueOf(value
									.getAmtA().doubleValue()
									+ vo.getAmtA().doubleValue())));
							value.setCntR(new Long(Integer.parseInt(value
									.getCntR().toString())
									+ Integer.parseInt(vo.getCntR().toString())));
							value.setAmtR(Double.valueOf(String.valueOf(value
									.getAmtR().doubleValue()
									+ vo.getAmtR().doubleValue())));
							value.setCntB(new Long(Integer.parseInt(value
									.getCntB().toString())
									+ Integer.parseInt(vo.getCntB().toString())));
							value.setAmtB(Double.valueOf(String.valueOf(value
									.getAmtB().doubleValue()
									+ vo.getAmtB().doubleValue())));
							value.setCntS(new Long(Integer.parseInt(value
									.getCntS().toString())
									+ Integer.parseInt(vo.getCntS().toString())));
							value.setAmtS(Double.valueOf(String.valueOf(value
									.getAmtS().doubleValue()
									+ vo.getAmtS().doubleValue())));

							value.setCostLife(new Long(Integer.parseInt(value
									.getCostLife().toString())
									+ Integer.parseInt(vo.getCostLife()
											.toString())));
							value.setGundan(new Long(Integer.parseInt(value
									.getGundan().toString())
									+ Integer.parseInt(vo.getGundan()
											.toString())));
							value.setCostChild(new Long(Integer.parseInt(value
									.getCostChild().toString())
									+ Integer.parseInt(vo.getCostChild()
											.toString())));
							value.setSumCostChild(new Long(Integer
									.parseInt(value.getSumCostChild()
											.toString())
									+ Integer.parseInt(vo.getSumCostChild()
											.toString())));
						} else {
							hash.put(key, voTotal);
						}

					}

					// Total All
					if (!divCodeChk.equals(vo.getDivCode())
							|| !incDecCodeChk.equals(vo.getIncDecCode())
							|| !secCodeChk.equals(vo.getSecCode())) {
						keyAll = "ALL" + vo.getIncDecCode().trim();
						CTRWRP201VO voTotalAll = new CTRWRP201VO();
						BeanUtils.copyProperties(voTotalAll, vo);
						valueAll = (CTRWRP201VO) hashAll.get(keyAll);
						if (valueAll != null) {
							// sum value
							// System.out.println(key+"
							// "+valueAll.getCntEmp().toString()+"
							// "+Integer.parseInt(vo.getCntEmp().toString()));
							valueAll.setCntEmp(new Long(Integer
									.parseInt(valueAll.getCntEmp().toString())
									+ Integer.parseInt(vo.getCntEmp()
											.toString())));
							valueAll.setEmpA(new Long(Integer.parseInt(valueAll
									.getEmpA().toString())
									+ Integer.parseInt(vo.getEmpA().toString())));
							valueAll.setEmpI(new Long(Integer.parseInt(valueAll
									.getEmpI().toString())
									+ Integer.parseInt(vo.getEmpI().toString())));
							valueAll.setEmpD(new Long(Integer.parseInt(valueAll
									.getEmpD().toString())
									+ Integer.parseInt(vo.getEmpD().toString())));

							valueAll.setCntRec(new Long(Integer
									.parseInt(valueAll.getCntRec().toString())
									+ Integer.parseInt(vo.getCntRec()
											.toString())));
							valueAll.setAmtRec(Double.valueOf(String
									.valueOf(valueAll.getAmtRec().doubleValue()
											+ vo.getAmtRec().doubleValue())));
							valueAll.setCntN(new Long(Integer.parseInt(valueAll
									.getCntN().toString())
									+ Integer.parseInt(vo.getCntN().toString())));
							valueAll.setAmtN(Double.valueOf(String
									.valueOf(valueAll.getAmtN().doubleValue()
											+ vo.getAmtN().doubleValue())));
							valueAll.setCntA(new Long(Integer.parseInt(valueAll
									.getCntA().toString())
									+ Integer.parseInt(vo.getCntA().toString())));
							valueAll.setAmtA(Double.valueOf(String
									.valueOf(valueAll.getAmtA().doubleValue()
											+ vo.getAmtA().doubleValue())));
							valueAll.setCntR(new Long(Integer.parseInt(valueAll
									.getCntR().toString())
									+ Integer.parseInt(vo.getCntR().toString())));
							valueAll.setAmtR(Double.valueOf(String
									.valueOf(valueAll.getAmtR().doubleValue()
											+ vo.getAmtR().doubleValue())));
							valueAll.setCntB(new Long(Integer.parseInt(valueAll
									.getCntB().toString())
									+ Integer.parseInt(vo.getCntB().toString())));
							valueAll.setAmtB(Double.valueOf(String
									.valueOf(valueAll.getAmtB().doubleValue()
											+ vo.getAmtB().doubleValue())));
							valueAll.setCntS(new Long(Integer.parseInt(valueAll
									.getCntS().toString())
									+ Integer.parseInt(vo.getCntS().toString())));
							valueAll.setAmtS(Double.valueOf(String
									.valueOf(valueAll.getAmtS().doubleValue()
											+ vo.getAmtS().doubleValue())));

							valueAll.setCostLife(new Long(
									Integer.parseInt(valueAll.getCostLife()
											.toString())
											+ Integer.parseInt(vo.getCostLife()
													.toString())));
							valueAll.setGundan(new Long(Integer
									.parseInt(valueAll.getGundan().toString())
									+ Integer.parseInt(vo.getGundan()
											.toString())));
							valueAll.setCostChild(new Long(Integer
									.parseInt(valueAll.getCostChild()
											.toString())
									+ Integer.parseInt(vo.getCostChild()
											.toString())));
							valueAll.setSumCostChild(new Long(Integer
									.parseInt(valueAll.getSumCostChild()
											.toString())
									+ Integer.parseInt(vo.getSumCostChild()
											.toString())));
						} else {
							hashAll.put(keyAll, voTotalAll);
						}

					}

					if (!divCodeChk.equals(vo.getDivCode())
							|| !areaCodeChk.equals(vo.getAreaCode())
							|| (vo.getSecCode() != null && secCodeChk == null)
							|| (vo.getSecCode() != null && !secCodeChk.equals(vo.getSecCode()))) {
						divCodeChk = vo.getDivCode();
						areaCodeChk = vo.getAreaCode();
						secCodeChk = vo.getSecCode();
						if (i == 0) {
							ww.copySheet("Sheet1","��ǹ_���ӡ��", ww.getNumberOfSheets());
							s++;
							i = 0;
							j = 1;
							if (j > 0) {
								sheet1.getSettings().setPassword("123");
								sheet1.getSettings().setProtected(true);
							}
							sheet1 = ww.getSheet(j);
							// sheet1.setName("��ǹ/���ӡ��");
							// String
							// otDesc="����� CTRWRP201 ��§ҹ�ʹ��ػ㹡�èѴ��
							// Pay Slip";
							 String otDesc="����� CTRWRP201";
                             String content = "��§ҹ�ʹ��ػ㹡�èѴ�� Pay Slip";

							sheet1.addCell(new Label(0, 0, ChgNullToEmpty(
									ouDesc, ""), header));
							// sheet1.addCell(new Label(0,
							// 0,ChgNullToEmpty(otDesc,""),HeadFormat));
							sheet1.addCell(new Label(0, 1, ChgNullToEmpty(
									otDesc, ""), headerLeft));
							sheet1.addCell(new Label(1, 1, ChgNullToEmpty(
									content, ""), header));
							// sheet1.addCell(new Label(0, 2,"��ШӧǴ��� "+
							// periodName + " �.�." +
							// year+" �ѹ����~� :
							// "+sdfPrint.format(gd.getTime()),headerRight));
							 sheet1.addCell(new Label(0,2, "�������  "+userInfo.getUserName(), headerLeft));
                             sheet1.addCell(new Label(1, 2,"��ШӧǴ��� "+ periodName + " �.�." + year,header));
                             sheet1.addCell(new Label(8, 2,"�ѹ������� : "+sdfPrint.format(gd.getTime()),headerRight));
								
							if (vo.getAreaCode() != null
									&& !vo.getAreaCode().equals("")
									&& vo.getAreaName() != null
									&& !vo.getAreaName().equals("")
									&& vo.getSecCode() != null
									&& !vo.getSecName().equals("")) {
								if(vo.getDivCode().equals(vo.getAreaCode())){
									sheet1.addCell(new Label(0, 3,"����/ʹ�.   "+ vo.getDivCode()+" : "+vo.getDivName() + "    ��ǹ/���ӡ��   "+vo.getSecCode()+" : "+vo.getSecName(),header));
								}else{
									sheet1.addCell(new Label(0, 3,"����/ʹ�.   "+ vo.getDivCode()+" : "+vo.getDivName() + "    ʾ/��.   " + vo.getAreaCode()+" : "+vo.getAreaName()+"      ��ǹ/���ӡ��   "+vo.getSecCode()+" : "+vo.getSecName(),header));
								}
							}else if(vo.getAreaCode() != null && !vo.getAreaCode().equals("") && vo.getAreaName() != null && !vo.getAreaName().equals("")){
								if(vo.getDivCode().equals(vo.getAreaCode())){
									sheet1.addCell(new Label(0, 3,"����/ʹ�.   "+ vo.getDivCode()+" : "+vo.getDivName(),header));
								}else{
									sheet1.addCell(new Label(0, 3,"����/ʹ�.   "+ vo.getDivCode()+" : "+vo.getDivName() + "    ʾ/��.   " + vo.getAreaCode()+" : "+vo.getAreaName(),header));
								}
							}else{
								sheet1.addCell(new Label(0, 3,"����/ʹ�.   "+ vo.getDivCode()+" : "+vo.getDivName(),header));
							}
							
						//	vDouble = vo.getCntEmp().intValue() + vo.getEmpI().intValue() - vo.getEmpD().intValue();
							vDouble = vo.getCntEmp().intValue();
							sheet1.addCell(new Label(0, 4,"�ӹǹ��ѡ�ҹ/�١��ҧ��Шӷ�����  "+ String.valueOf(vDouble) + " ��       ���  " + vo.getEmpA()+" ��¡��    ����  "+vo.getEmpI()+" ��¡��    ź  "+vo.getEmpD()+" ��¡��",header));
							i++;
							sheet1.addCell(new Label(0, 5,"�ӹǹ��������Ѻ�Թ�ѧ�վ  "+ vo.getCostLife() + " ��      �ӹǹ��������Ѻ���¡ѹ���  " + vo.getGundan()+" ��    �ӹǹ��������Ѻ��Ҫ�������ͺص�  "+vo.getCostChild()+" ��    �ӹǹ�ص÷�����Ѻ��Ҫ��������  "+vo.getSumCostChild()+" ��",header));
							i++;
							cntEmp = String.valueOf(vDouble);
							empA = String.valueOf(vo.getEmpA());
							empI = String.valueOf(vo.getEmpI());
							empD = String.valueOf(vo.getEmpD());
							costLife = String.valueOf(vo.getCostLife());
							gundan = String.valueOf(vo.getGundan());
							costChild = String.valueOf(vo.getCostChild());
							sumCostChild = String.valueOf(vo.getSumCostChild());

						} else {

							int test;
							if ((i + 5) / 25 == 0) {
								test = 25 - (i + 5);
							} else {
								test = ((i + 5) - (((i + 5) / 25) * 25));
							}

							if (test > 7) {
								sheet1.mergeCells(0, startRow + i, 12, startRow
										+ i);
								sheet1.addCell(new Label(0, startRow + i, "",
										borderEnd));

								i++;
								sheet1.mergeCells(0, startRow + i, 12, startRow
										+ i);
								if(vo.getAreaCode() != null && !vo.getAreaCode().equals("") && vo.getAreaName() != null && !vo.getAreaName().equals("") && vo.getSecCode() != null && !vo.getSecName().equals("")){
									if(vo.getDivCode().equals(vo.getAreaCode())){
										sheet1.addCell(new Label(0, startRow+i,"����/ʹ�.   "+ vo.getDivCode()+" : "+vo.getDivName() + "    ��ǹ/���ӡ��   "+vo.getSecCode()+" : "+vo.getSecName(),header));
									}else{
										sheet1.addCell(new Label(0, startRow+i,"����/ʹ�.   "+ vo.getDivCode()+" : "+vo.getDivName() + "    ʾ/��.   " + vo.getAreaCode()+" : "+vo.getAreaName()+"      ��ǹ/���ӡ��   "+vo.getSecCode()+" : "+vo.getSecName(),header));
									}
								}else if(vo.getAreaCode() != null && !vo.getAreaCode().equals("") && vo.getAreaName() != null && !vo.getAreaName().equals("")){
									if(vo.getDivCode().equals(vo.getAreaCode())){
										sheet1.addCell(new Label(0, startRow+i,"����/ʹ�.   "+ vo.getDivCode()+" : "+vo.getDivName(),header));
									}else{
										sheet1.addCell(new Label(0, startRow+i,"����/ʹ�.   "+ vo.getDivCode()+" : "+vo.getDivName() + "    ʾ/��.   " + vo.getAreaCode()+" : "+vo.getAreaName(),header));
									}
								}else{
									sheet1.addCell(new Label(0, startRow+i,"����/ʹ�.   "+ vo.getDivCode()+" : "+vo.getDivName(),header));
								}
								i++;
								// vDouble = vo.getCntEmp().intValue() +
								// vo.getEmpI().intValue() -
								// vo.getEmpD().intValue();
								vDouble = vo.getCntEmp().intValue();
								sheet1.mergeCells(0, startRow + i, 12, startRow
										+ i);
								sheet1.addCell(new Label(0, startRow+i,"�ӹǹ��ѡ�ҹ/�١��ҧ��Шӷ�����  "+ String.valueOf(vDouble) + " ��       ���  " + vo.getEmpA()+" ��¡��    ����  "+vo.getEmpI()+" ��¡��    ź  "+vo.getEmpD()+" ��¡��",header));
								
								i++;
								sheet1.mergeCells(0, startRow + i, 12, startRow
										+ i);
								sheet1.addCell(new Label(0, startRow+i,"�ӹǹ��������Ѻ�Թ�ѧ�վ  "+ vo.getCostLife() + " ��      �ӹǹ��������Ѻ���¡ѹ���  " + vo.getGundan()+" ��    �ӹǹ��������Ѻ��Ҫ�������ͺص�  "+vo.getCostChild()+" ��    �ӹǹ�ص÷�����Ѻ��Ҫ��������  "+vo.getSumCostChild()+" ��",header));
								i++;
								
								cntEmp = String.valueOf(vDouble);
								empA = String.valueOf(vo.getEmpA());
								empI = String.valueOf(vo.getEmpI());
								empD = String.valueOf(vo.getEmpD());
								costLife = String.valueOf(vo.getCostLife());
								gundan = String.valueOf(vo.getGundan());
								costChild = String.valueOf(vo.getCostChild());
								sumCostChild = String.valueOf(vo.getSumCostChild());

							} else {

								sheet1.mergeCells(0, startRow + i, 12, startRow
										+ i);
								sheet1.addCell(new Label(0, startRow + i, "",
										borderEnd));
								i = i + (7 - test);

								sheet1.mergeCells(0, startRow + i, 12, startRow
										+ i);
								if(vo.getAreaCode() != null && !vo.getAreaCode().equals("") && vo.getAreaName() != null && !vo.getAreaName().equals("") && vo.getSecCode() != null && !vo.getSecName().equals("")){
									if(vo.getDivCode().equals(vo.getAreaCode())){
										sheet1.addCell(new Label(0, startRow+i,"����/ʹ�.   "+ vo.getDivCode()+" : "+vo.getDivName() + "    ��ǹ/���ӡ��   "+vo.getSecCode()+" : "+vo.getSecName(),header));
									}else{
										sheet1.addCell(new Label(0, startRow+i,"����/ʹ�.   "+ vo.getDivCode()+" : "+vo.getDivName() + "    ʾ/��.   " + vo.getAreaCode()+" : "+vo.getAreaName()+"      ��ǹ/���ӡ��   "+vo.getSecCode()+" : "+vo.getSecName(),header));
									}
								}else if(vo.getAreaCode() != null && !vo.getAreaCode().equals("") && vo.getAreaName() != null && !vo.getAreaName().equals("")){
									if(vo.getDivCode().equals(vo.getAreaCode())){
										sheet1.addCell(new Label(0, startRow+i,"����/ʹ�.   "+ vo.getDivCode()+" : "+vo.getDivName(),header));
									}else{
										sheet1.addCell(new Label(0, startRow+i,"����/ʹ�.   "+ vo.getDivCode()+" : "+vo.getDivName() + "    ʾ/��.   " + vo.getAreaCode()+" : "+vo.getAreaName(),header));
									}
								}else{
									sheet1.addCell(new Label(0, startRow+i,"����/ʹ�.   "+ vo.getDivCode()+" : "+vo.getDivName(),header));
								}
								i++;
								vDouble = vo.getCntEmp().intValue();
								sheet1.mergeCells(0, startRow + i, 12, startRow
										+ i);
								sheet1.addCell(new Label(0, startRow+i,"�ӹǹ��ѡ�ҹ/�١��ҧ��Шӷ�����  "+ String.valueOf(vDouble) + " ��       ���  " + vo.getEmpA()+" ��¡��    ����  "+vo.getEmpI()+" ��¡��    ź  "+vo.getEmpD()+" ��¡��",header));
								
								i++;
								sheet1.mergeCells(0,startRow+i, 12,startRow+i);
								sheet1.addCell(new Label(0, startRow+i,"�ӹǹ��������Ѻ�Թ�ѧ�վ  "+ vo.getCostLife() + " ��      �ӹǹ��������Ѻ���¡ѹ���  " + vo.getGundan()+" ��    �ӹǹ��������Ѻ��Ҫ�������ͺص�  "+vo.getCostChild()+" ��    �ӹǹ�ص÷�����Ѻ��Ҫ��������  "+vo.getSumCostChild()+" ��",header));
								i++;
								

								cntEmp = String.valueOf(vDouble);
								empA = String.valueOf(vo.getEmpA());
								empI = String.valueOf(vo.getEmpI());
								empD = String.valueOf(vo.getEmpD());
								costLife = String.valueOf(vo.getCostLife());
								gundan = String.valueOf(vo.getGundan());
								costChild = String.valueOf(vo.getCostChild());
								sumCostChild = String.valueOf(vo
										.getSumCostChild());
							}

							// sheet1.setRowView(startRow+i, 550);
							sheet1.mergeCells(0,startRow+i, 0,startRow+i+1);
							sheet1.addCell(new Label(0, startRow+i, "��¡��", mergeCenterBoldBGGrayFormat));
							sheet1.mergeCells(1,startRow+i, 2,startRow+i);
							sheet1.addCell(new Label(1, startRow+i, "�����ŷ�����", mergeCenterBoldBGGrayFormat));
							sheet1.mergeCells(3,startRow+i, 4,startRow+i);
							sheet1.addCell(new Label(3, startRow+i, "����", mergeCenterBoldBGGrayFormat));
							sheet1.mergeCells(5,startRow+i, 6,startRow+i);
							sheet1.addCell(new Label(5, startRow+i, "��Ѻ��ا", mergeCenterBoldBGGrayFormat));
							sheet1.mergeCells(7,startRow+i, 8,startRow+i);
							sheet1.addCell(new Label(7, startRow+i, "���¡�׹", mergeCenterBoldBGGrayFormat));
							sheet1.mergeCells(9,startRow+i, 10,startRow+i);
							sheet1.addCell(new Label(9, startRow+i, "���ԡ��Ѻ��ا", mergeCenterBoldBGGrayFormat));
							sheet1.mergeCells(11,startRow+i, 12,startRow+i);
							sheet1.addCell(new Label(11, startRow+i, "���ԡ���¡�׹", mergeCenterBoldBGGrayFormat));
							i++;

							sheet1.addCell(new Label(0, startRow+i, "", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(1, startRow+i, "��¡��", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(2, startRow+i, "�ҷ", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(3, startRow+i, "��¡��", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(4, startRow+i, "�ҷ", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(5, startRow+i, "��¡��", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(6, startRow+i, "�ҷ", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(7, startRow+i, "��¡��", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(8, startRow+i, "�ҷ", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(9, startRow+i, "��¡��", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(10, startRow+i, "�ҷ", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(11, startRow+i, "��¡��", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(12, startRow+i, "�ҷ", mergeCenterBoldBGGrayFormat));
							i++;
							// end new table

						}
					}

					if (!vo.getIncDecCode().equals("00")) {

						sheet1.addCell(new Label(0, startRow + i, vo
								.getIncDecCode() + "  " + vo.getIncDecName(),
								borderString));
						sheet1.addCell(new Label(1, startRow + i, String
								.valueOf(vo.getCntRec()), borderNumber2));
						sheet1.addCell(new Label(2, startRow + i,
								convertFormatNumber(vo.getAmtRec()),
								borderNumber2));
						sheet1.addCell(new Label(3, startRow + i, String
								.valueOf(vo.getCntN()), borderNumber2));
						sheet1.addCell(new Label(4, startRow + i,
								convertFormatNumber(vo.getAmtN()),
								borderNumber2));
						sheet1.addCell(new Label(5, startRow + i, String
								.valueOf(vo.getCntA()), borderNumber2));
						sheet1.addCell(new Label(6, startRow + i,
								convertFormatNumber(vo.getAmtA()),
								borderNumber2));
						sheet1.addCell(new Label(7, startRow + i, String
								.valueOf(vo.getCntR()), borderNumber2));
						sheet1.addCell(new Label(8, startRow + i,
								convertFormatNumber(vo.getAmtR()),
								borderNumber2));
						sheet1.addCell(new Label(9, startRow + i, String
								.valueOf(vo.getCntB()), borderNumber2));
						sheet1.addCell(new Label(10, startRow + i,
								convertFormatNumber(vo.getAmtB()),
								borderNumber2));
						sheet1.addCell(new Label(11, startRow + i, String
								.valueOf(vo.getCntS()), borderNumber2));
						sheet1.addCell(new Label(12, startRow + i,
								convertFormatNumber(vo.getAmtS()),
								borderNumber2));
						i++;

						// Detail Overtime
						if (vo.getIncDecCode().equals("11")) {

							sheet1.addCell(new Label(0, startRow+i," ",normalCenter));
							sheet1.mergeCells(1,startRow+i,2,startRow+i);
							sheet1.addCell(new Label(1, startRow+i,"1���/*1���",normalCenter));
							sheet1.mergeCells(3,startRow+i,4,startRow+i);
							sheet1.addCell(new Label(3, startRow+i,"1.5���/*1.5���",normalCenter));
							sheet1.mergeCells(5,startRow+i,6,startRow+i);
							sheet1.addCell(new Label(5, startRow+i,"3���/*3���",normalCenter));
							sheet1.addCell(new Label(7, startRow+i," ",normalCenter));
							sheet1.addCell(new Label(8, startRow+i,"*�ѹ�ӧҹ/�ѹ��ش�ѡ�ѵġ��",normalLeft));
							i++;
							List rwOverTime = this.getPrEmployeeService()
									.findDetailPaySlipOverTime(ouCode, userId,
											year, period, vo.getDivCode(),
											vo.getAreaCode(), vo.getSecCode());
							RwOvertimeVO voOt = new RwOvertimeVO();
							String flagPrDesc = "";
							String flagPrDescTemp = "";
							String flagStatus = "";
							String flagWorkDesc = "";
							String otTypeDesc = "";
							String otTypeDescTemp = "";
							for (int l = 0; l < rwOverTime.size(); l++) {
								voOt = (RwOvertimeVO) rwOverTime.get(l);
								 if (voOt.getOtType().equals("1"))otTypeDesc = "�����ǧ����";
								 if (voOt.getOtType().equals("2"))otTypeDesc = "��ҷӧҹ��ѹ��ش";
								 
								 if (voOt.getFlagPr().equals("N"))flagPrDesc = "����";
								 if (voOt.getFlagPr().equals("A"))flagPrDesc = "��Ѻ��ا";
								 if (voOt.getFlagPr().equals("R"))flagPrDesc = "���¡�׹";
								 if (voOt.getFlagPr().equals("B"))flagPrDesc = "���ԡ��Ѻ��ا";
								 if (voOt.getFlagPr().equals("S"))flagPrDesc = "���ԡ���¡�׹";
								 
								 if (voOt.getFlagWork().equals("Y"))flagWorkDesc = "    ��ҧ�ͧ";
								 if (voOt.getFlagWork().equals("N"))flagWorkDesc = "���㹡ͧ";
								if (!otTypeDesc.equals(otTypeDescTemp)) {
									flagStatus = otTypeDesc + "          ";
									otTypeDescTemp = otTypeDesc;
								} else {
									flagStatus = "";
								}
								if (!flagPrDesc.equals(flagPrDescTemp)
										|| !flagStatus.equals("")) {
									flagStatus = flagStatus + flagPrDesc
											+ "          ";
									flagPrDescTemp = flagPrDesc;
								} else {
									flagStatus = "";
								}
								flagStatus = flagStatus + flagWorkDesc;
								sheet1.addCell(new Label(0, startRow + i,
										flagStatus, normalRight));
								sheet1.mergeCells(1, startRow + i, 2, startRow
										+ i);
								sheet1.addCell(new Label(1, startRow + i, voOt
										.getTotDay1_R(), normalCenter));
								sheet1.mergeCells(3, startRow + i, 4, startRow
										+ i);
								sheet1.addCell(new Label(3, startRow + i, voOt
										.getTotDay15_R(), normalCenter));
								sheet1.mergeCells(5, startRow + i, 6, startRow
										+ i);
								sheet1.addCell(new Label(5, startRow + i, voOt
										.getTotDay3_R(), normalCenter));
								sheet1.addCell(new Label(7, startRow+i,"�������",normalRight));
								sheet1.addCell(new Label(8, startRow + i, " ",
										normalCenter));
								i++;
							}
						}

						// Detail Premiun
						if (vo.getIncDecCode().equals("12")) {
							sheet1.addCell(new Label(0, startRow + i, " ",
									normalCenter));
							sheet1.mergeCells(1,startRow+i,2,startRow+i);
							sheet1.addCell(new Label(1, startRow+i,"�����",normalCenter));
							sheet1.mergeCells(3,startRow+i,4,startRow+i);
							sheet1.addCell(new Label(3, startRow+i,"�к���",normalCenter));
							sheet1.mergeCells(5,startRow+i,6,startRow+i);
							sheet1.addCell(new Label(5, startRow+i,"�С�ҧ�׹",normalCenter));
							sheet1.addCell(new Label(7, startRow+i," ",normalCenter));
							sheet1.addCell(new Label(8, startRow+i,"(����/��������)",normalLeft));
							i++;

							List rwPremium = this.getPrEmployeeService()
									.findDetailPaySlipPremium(ouCode, userId,
											year, period, vo.getDivCode(),
											vo.getAreaCode(), vo.getSecCode());
							RwPremiumVO voPremium = new RwPremiumVO();
							String flagPrDesc = "";
							String flagPrDescTemp = "";
							String flagStatus = "";
							for (int l = 0; l < rwPremium.size(); l++) {
								voPremium = (RwPremiumVO) rwPremium.get(l);
								 if (voPremium.getFlagPr().equals("N"))flagPrDesc = "����";
								 if (voPremium.getFlagPr().equals("A"))flagPrDesc = "��Ѻ��ا";
								 if (voPremium.getFlagPr().equals("R"))flagPrDesc = "���¡�׹";
								 if (voPremium.getFlagPr().equals("B"))flagPrDesc = "���ԡ��Ѻ��ا";
								 if (voPremium.getFlagPr().equals("S"))flagPrDesc = "���ԡ���¡�׹";
								 
								if (!flagPrDesc.equals(flagPrDescTemp)) {
									flagStatus = flagPrDesc;
									flagPrDescTemp = flagPrDesc;
								} else {
									flagStatus = "";
								}
								sheet1.addCell(new Label(0, startRow + i,
										flagStatus, normalRight));
								sheet1.mergeCells(1, startRow + i, 2, startRow
										+ i);
								sheet1.addCell(new Label(1, startRow + i, dfInt
										.format(voPremium.getMorDay())
										+ "/"
										+ dfInt.format(voPremium.getMorHour()),
										normalCenter));
								sheet1.mergeCells(3, startRow + i, 4, startRow
										+ i);
								sheet1.addCell(new Label(3, startRow + i, dfInt
										.format(voPremium.getAftDay())
										+ "/"
										+ dfInt.format(voPremium.getAftHour()),
										normalCenter));
								sheet1.mergeCells(5, startRow + i, 6, startRow
										+ i);
								sheet1.addCell(new Label(5, startRow + i, dfInt
										.format(voPremium.getEvnDay())
										+ "/"
										+ dfInt.format(voPremium.getEvnHour()),
										normalCenter));

								i++;
							}
						}

						// Detail Health
						if (vo.getIncDecCode().equals("13")) {

							List rwHealth = this.getPrEmployeeService()
									.findDetailPaySlipHealth(ouCode, userId,
											year, period, vo.getDivCode(),
											vo.getAreaCode(), vo.getSecCode());
							RwHealthVO voHealth = new RwHealthVO();
							String flagPrDesc = "";
							String flagPrDescTemp = "";
							String flagStatus = "";
							for (int l = 0; l < rwHealth.size(); l++) {
								voHealth = (RwHealthVO) rwHealth.get(l);
								 if (voHealth.getFlagPr().equals("N"))flagPrDesc = "����";
								 if (voHealth.getFlagPr().equals("A"))flagPrDesc = "��Ѻ��ا";
								 if (voHealth.getFlagPr().equals("R"))flagPrDesc = "���¡�׹";
								 if (voHealth.getFlagPr().equals("B"))flagPrDesc = "���ԡ��Ѻ��ا";
								 if (voHealth.getFlagPr().equals("S"))flagPrDesc = "���ԡ���¡�׹";
								 
								if (!flagPrDesc.equals(flagPrDescTemp)) {
									flagStatus = flagPrDesc;
									flagPrDescTemp = flagPrDesc;
								} else {
									flagStatus = "";
								}
								sheet1.addCell(new Label(0, startRow + i,
										flagStatus, normalRight));
								sheet1.addCell(new Label(1, startRow + i, dfInt
										.format(voHealth.getTotalHour()),
										normalRight));
								sheet1.addCell(new Label(2, startRow+i,"�������",normalRight));

								i++;
							}
						}

						// Detail Health
						if (vo.getIncDecCode().equals("14")) {

							List rwDager = this.getPrEmployeeService()
									.findDetailPaySlipDanger(ouCode, userId,
											year, period, vo.getDivCode(),
											vo.getAreaCode(), vo.getSecCode());
							RwDangerVO voDanger = new RwDangerVO();
							String flagPrDesc = "";
							String flagPrDescTemp = "";
							String flagStatus = "";
							for (int l = 0; l < rwDager.size(); l++) {
								voDanger = (RwDangerVO) rwDager.get(l);
								 if (voDanger.getFlagPr().equals("N"))flagPrDesc = "����";
								 if (voDanger.getFlagPr().equals("A"))flagPrDesc = "��Ѻ��ا";
								 if (voDanger.getFlagPr().equals("R"))flagPrDesc = "���¡�׹";
								 if (voDanger.getFlagPr().equals("B"))flagPrDesc = "���ԡ��Ѻ��ا";
								 if (voDanger.getFlagPr().equals("S"))flagPrDesc = "���ԡ���¡�׹";
								 
								if (!flagPrDesc.equals(flagPrDescTemp)) {
									flagStatus = flagPrDesc;
									flagPrDescTemp = flagPrDesc;
								} else {
									flagStatus = "";
								}
								sheet1.addCell(new Label(0, startRow + i,
										flagStatus, normalRight));
								sheet1.addCell(new Label(1, startRow + i, dfInt
										.format(voDanger.getFullDay())
										+ "/"
										+ dfInt.format(voDanger.getHalfDay()),
										normalCenter));
								 sheet1.addCell(new Label(2, startRow+i,"(����ѹ/�����ѹ)",normalLeft));

								i++;
							}
						}

						if (i % 25 == 0) {
							// ��鹻Դ
							// sheet1.mergeCells(0,startRow+i, 12,startRow+i);
							sheet1.addCell(new Label(0, startRow + i, "",
									borderEnd));
							sheet1.addCell(new Label(1, startRow + i, "",
									borderEnd));
							sheet1.addCell(new Label(2, startRow + i, "",
									borderEnd));
							sheet1.addCell(new Label(3, startRow + i, "",
									borderEnd));
							sheet1.addCell(new Label(4, startRow + i, "",
									borderEnd));
							sheet1.addCell(new Label(5, startRow + i, "",
									borderEnd));
							sheet1.addCell(new Label(6, startRow + i, "",
									borderEnd));
							sheet1.addCell(new Label(7, startRow + i, "",
									borderEnd));
							sheet1.addCell(new Label(8, startRow + i, "",
									borderEnd));
							sheet1.addCell(new Label(9, startRow + i, "",
									borderEnd));
							sheet1.addCell(new Label(10, startRow + i, "",
									borderEnd));
							sheet1.addCell(new Label(11, startRow + i, "",
									borderEnd));
							sheet1.addCell(new Label(12, startRow + i, "",
									borderEnd));
							// ��鹻Դ
							// set new line next page draw new head
							i++;

							sheet1.mergeCells(0, startRow + i, 12, startRow + i);
							if (vo.getAreaCode() != null
									&& !vo.getAreaCode().equals("")
									&& vo.getAreaName() != null
									&& !vo.getAreaName().equals("")
									&& vo.getSecCode() != null
									&& !vo.getSecName().equals("")) {
								if(vo.getDivCode().equals(vo.getAreaCode())){
									sheet1.addCell(new Label(0, startRow+i,"����/ʹ�.   "+ vo.getDivCode()+" : "+vo.getDivName() + "    ��ǹ/���ӡ��   "+vo.getSecCode()+" : "+vo.getSecName(),header));
								}else{
									sheet1.addCell(new Label(0, startRow+i,"����/ʹ�.   "+ vo.getDivCode()+" : "+vo.getDivName() + "    ʾ/��.   " + vo.getAreaCode()+" : "+vo.getAreaName()+"      ��ǹ/���ӡ��   "+vo.getSecCode()+" : "+vo.getSecName(),header));
								}
							}else if(vo.getAreaCode() != null && !vo.getAreaCode().equals("") && vo.getAreaName() != null && !vo.getAreaName().equals("")){
								if(vo.getDivCode().equals(vo.getAreaCode())){
									sheet1.addCell(new Label(0, startRow+i,"����/ʹ�.   "+ vo.getDivCode()+" : "+vo.getDivName(),header));
								}else{
									sheet1.addCell(new Label(0, startRow+i,"����/ʹ�.   "+ vo.getDivCode()+" : "+vo.getDivName() + "    ʾ/��.   " + vo.getAreaCode()+" : "+vo.getAreaName(),header));
								}
							}else{
								sheet1.addCell(new Label(0, startRow+i,"����/ʹ�.   "+ vo.getDivCode()+" : "+vo.getDivName(),header));
							}
							i++;
							// vDouble = vo.getCntEmp().intValue() +
							// vo.getEmpI().intValue() -
							// vo.getEmpD().intValue();
							vDouble = vo.getCntEmp().intValue();
							sheet1.mergeCells(0, startRow + i, 12, startRow + i);
							sheet1.addCell(new Label(0, startRow+i,"�ӹǹ��ѡ�ҹ/�١��ҧ��Шӷ�����  "+ cntEmp + " ��       ���  " + empA +" ��¡��    ����  "+empI+" ��¡��    ź  "+ empD +" ��¡��",header));
							
							i++;
							sheet1.mergeCells(0,startRow+i, 12,startRow+i);
							sheet1.addCell(new Label(0, startRow+i,"�ӹǹ��������Ѻ�Թ�ѧ�վ  "+ costLife + " ��      �ӹǹ��������Ѻ���¡ѹ���  " + gundan +" ��    �ӹǹ��������Ѻ��Ҫ�������ͺص�  "+costChild+" ��    �ӹǹ�ص÷�����Ѻ��Ҫ��������  "+sumCostChild+" ��",header));
							i++;
							/*sheet1.mergeCells(0, startRow + i, 12, startRow + i);
							sheet1.addCell(new Label(
									0,
									startRow + i,
									"�ӹǹ��������Ѻ�Թ�ѧ�վ  "
											+ costLife
											+ " ��      �ӹǹ��������Ѻ���¡ѹ���  "
											+ gundan
											+ " ��    �ӹǹ��������Ѻ��Ҫ�������ͺص�  "
											+ costChild
											+ " ��    �ӹǹ�ص÷�����Ѻ��Ҫ��������  "
											+ sumCostChild + " ��", header));
							i++;*/

							// sheet1.setRowView(startRow+i, 550);
							sheet1.mergeCells(0,startRow+i, 0,startRow+i+1);
							sheet1.addCell(new Label(0, startRow+i, "��¡��", mergeCenterBoldBGGrayFormat));
							sheet1.mergeCells(1,startRow+i, 2,startRow+i);
							sheet1.addCell(new Label(1, startRow+i, "�����ŷ�����", mergeCenterBoldBGGrayFormat));
							sheet1.mergeCells(3,startRow+i, 4,startRow+i);
							sheet1.addCell(new Label(3, startRow+i, "����", mergeCenterBoldBGGrayFormat));
							sheet1.mergeCells(5,startRow+i, 6,startRow+i);
							sheet1.addCell(new Label(5, startRow+i, "��Ѻ��ا", mergeCenterBoldBGGrayFormat));
							sheet1.mergeCells(7,startRow+i, 8,startRow+i);
							sheet1.addCell(new Label(7, startRow+i, "���¡�׹", mergeCenterBoldBGGrayFormat));
							sheet1.mergeCells(9,startRow+i, 10,startRow+i);
							sheet1.addCell(new Label(9, startRow+i, "���ԡ��Ѻ��ا", mergeCenterBoldBGGrayFormat));
							sheet1.mergeCells(11,startRow+i, 12,startRow+i);
							sheet1.addCell(new Label(11, startRow+i, "���ԡ���¡�׹", mergeCenterBoldBGGrayFormat));
							
							i++;
							
							sheet1.addCell(new Label(0, startRow+i, "", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(1, startRow+i, "��¡��", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(2, startRow+i, "�ҷ", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(3, startRow+i, "��¡��", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(4, startRow+i, "�ҷ", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(5, startRow+i, "��¡��", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(6, startRow+i, "�ҷ", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(7, startRow+i, "��¡��", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(8, startRow+i, "�ҷ", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(9, startRow+i, "��¡��", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(10, startRow+i, "�ҷ", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(11, startRow+i, "��¡��", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(12, startRow+i, "�ҷ", mergeCenterBoldBGGrayFormat));
							i++;
							
							
						}

					}
				}

				// ��鹻Դ
				// sheet1.mergeCells(0,startRow+i, 12,startRow+i);
				sheet1.addCell(new Label(0, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(1, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(2, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(3, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(4, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(5, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(6, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(7, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(8, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(9, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(10, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(11, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(12, startRow + i, "", borderEnd));
				// ��鹻Դ
				// ˹���ʴ������
				System.out.println("------------ENDING-------------" + startRow
						+ " :: " + i + "  :: " + startRow + i);

				divCodeChk = "";
				areaCodeChk = "";
				incDecCodeChk = "";
				i = 0;

				for (Iterator it = new TreeSet(hash.keySet()).iterator(); it
						.hasNext();) {
					key = (String) it.next();
					value = (CTRWRP201VO) hash.get(key);

					if (!divCodeChk.equals(value.getDivCode())
							|| (value.getIncDecCode().equals("00"))) {
						divCodeChk = value.getDivCode();
						if (i == 0) {
							ww.copySheet("Sheet1", "����_�ӹѡ�ҹ", ww.getNumberOfSheets());

							// j++;
							j = 2;
							if (j > 0) {
								sheet1.getSettings().setPassword("123");
								sheet1.getSettings().setProtected(true);
							}

							sheet1 = ww.getSheet(j);
							// sheet1.setName("����/�ӹѡ�ҹ");
							String otDesc="����� CTRWRP201                                                                                         ��§ҹ�ʹ��ػ㹡�èѴ�� Pay Slip";

							sheet1.addCell(new Label(0, 0, ChgNullToEmpty(
									ouDesc, ""), header));
							sheet1.addCell(new Label(0, 1, ChgNullToEmpty(
									otDesc, ""), HeadFormat));
							// sheet1.addCell(new Label(0, 2,"��ШӧǴ��� "+
							// periodName + " �.�." +
							// year+" �ѹ����~� :
							// "+sdfPrint.format(gd.getTime()),headerRight));
							sheet1.addCell(new Label(0,2, "�������  "+userInfo.getUserName(), headerLeft));
							sheet1.addCell(new Label(1, 2,"��ШӧǴ��� "+ periodName + " �.�." + year,header));
                            sheet1.addCell(new Label(8, 2,"�ѹ������� : "+sdfPrint.format(gd.getTime()),headerRight));
							sheet1.addCell(new Label(0, 3,"����/ʹ�.   "+ value.getDivCode()+" : "+value.getDivName(),header));
							sheet1.addCell(new Label(0, 4,"�ӹǹ��ѡ�ҹ/�١��ҧ��Шӷ�����  "+ value.getCntEmp() + " ��       ���  " + value.getEmpA()+" ��¡��    ����  "+value.getEmpI()+" ��¡��    ź  "+value.getEmpD()+" ��¡��",header));
							sheet1.addCell(new Label(0, 5,"�ӹǹ��������Ѻ�Թ�ѧ�վ  "+ value.getCostLife() + " ��      �ӹǹ��������Ѻ���¡ѹ���  " + value.getGundan() +" ��    �ӹǹ��������Ѻ��Ҫ�������ͺص�  "+value.getCostChild()+" ��    �ӹǹ�ص÷�����Ѻ��Ҫ��������  "+value.getSumCostChild()+" ��",header));
							
						
							i++;
							i++;
						} else {
							// begin new table
							// sheet1 = ww.getSheet(j);
							sheet1.mergeCells(0, startRow + i, 12, startRow + i);
							sheet1.addCell(new Label(0, startRow + i, "",
									borderEnd));

							i++;
							sheet1.mergeCells(0,startRow+i, 12,startRow+i);
							sheet1.addCell(new Label(0, startRow+i,"����/ʹ�.   "+ value.getDivCode()+" : "+value.getDivName(),header));
							i++;
							sheet1.mergeCells(0,startRow+i, 12,startRow+i);
							sheet1.addCell(new Label(0, startRow+i,"�ӹǹ��ѡ�ҹ/�١��ҧ��Шӷ�����  "+ value.getCntEmp() + " ��       ���  " + value.getEmpA()+" ��¡��    ����  "+value.getEmpI()+" ��¡��    ź  "+value.getEmpD()+" ��¡��",header));
							i++;
							sheet1.mergeCells(0,startRow+i, 12,startRow+i);
							sheet1.addCell(new Label(0, startRow+i,"�ӹǹ��������Ѻ�Թ�ѧ�վ  "+ value.getCostLife() + " ��      �ӹǹ��������Ѻ���¡ѹ���  " + value.getGundan() +" ��    �ӹǹ��������Ѻ��Ҫ�������ͺص�  "+value.getCostChild()+" ��    �ӹǹ�ص÷�����Ѻ��Ҫ��������  "+value.getSumCostChild()+" ��",header));
							
							i++;
							
							//sheet1.setRowView(startRow+i, 550);
							sheet1.mergeCells(0,startRow+i, 0,startRow+i+1);
							sheet1.addCell(new Label(0, startRow+i, "��¡��", mergeCenterBoldBGGrayFormat));
							sheet1.mergeCells(1,startRow+i, 2,startRow+i);
							sheet1.addCell(new Label(1, startRow+i, "�����ŷ�����", mergeCenterBoldBGGrayFormat));
							sheet1.mergeCells(3,startRow+i, 4,startRow+i);
							sheet1.addCell(new Label(3, startRow+i, "����", mergeCenterBoldBGGrayFormat));
							sheet1.mergeCells(5,startRow+i, 6,startRow+i);
							sheet1.addCell(new Label(5, startRow+i, "��Ѻ��ا", mergeCenterBoldBGGrayFormat));
							sheet1.mergeCells(7,startRow+i, 8,startRow+i);
							sheet1.addCell(new Label(7, startRow+i, "���¡�׹", mergeCenterBoldBGGrayFormat));
							sheet1.mergeCells(9,startRow+i, 10,startRow+i);
							sheet1.addCell(new Label(9, startRow+i, "���ԡ��Ѻ��ا", mergeCenterBoldBGGrayFormat));
							sheet1.mergeCells(11,startRow+i, 12,startRow+i);
							sheet1.addCell(new Label(11, startRow+i, "���ԡ���¡�׹", mergeCenterBoldBGGrayFormat));
							
							i++;
							
							sheet1.addCell(new Label(0, startRow+i, "", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(1, startRow+i, "��¡��", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(2, startRow+i, "�ҷ", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(3, startRow+i, "��¡��", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(4, startRow+i, "�ҷ", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(5, startRow+i, "��¡��", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(6, startRow+i, "�ҷ", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(7, startRow+i, "��¡��", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(8, startRow+i, "�ҷ", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(9, startRow+i, "��¡��", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(10, startRow+i, "�ҷ", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(11, startRow+i, "��¡��", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(12, startRow+i, "�ҷ", mergeCenterBoldBGGrayFormat));
							i++;
					//	 end new table
							
						}
						
					}
					if (!value.getIncDecCode().equals("00")) {
						sheet1.addCell(new Label(0, startRow + i,
								value.getIncDecCode() + "  "
										+ value.getIncDecName(), borderString));
						sheet1.addCell(new Label(1, startRow + i, String
								.valueOf(value.getCntRec()), borderNumber2));
						sheet1.addCell(new Label(2, startRow + i,
								convertFormatNumber(value.getAmtRec()),
								borderNumber2));
						sheet1.addCell(new Label(3, startRow + i, String
								.valueOf(value.getCntN()), borderNumber2));
						sheet1.addCell(new Label(4, startRow + i,
								convertFormatNumber(value.getAmtN()),
								borderNumber2));
						sheet1.addCell(new Label(5, startRow + i, String
								.valueOf(value.getCntA()), borderNumber2));
						sheet1.addCell(new Label(6, startRow + i,
								convertFormatNumber(value.getAmtA()),
								borderNumber2));
						sheet1.addCell(new Label(7, startRow + i, String
								.valueOf(value.getCntR()), borderNumber2));
						sheet1.addCell(new Label(8, startRow + i,
								convertFormatNumber(value.getAmtR()),
								borderNumber2));
						sheet1.addCell(new Label(9, startRow + i, String
								.valueOf(value.getCntB()), borderNumber2));
						sheet1.addCell(new Label(10, startRow + i,
								convertFormatNumber(value.getAmtB()),
								borderNumber2));
						sheet1.addCell(new Label(11, startRow + i, String
								.valueOf(value.getCntS()), borderNumber2));
						sheet1.addCell(new Label(12, startRow + i,
								convertFormatNumber(value.getAmtS()),
								borderNumber2));
						i++;

						// Detail Overtime
						if (value.getIncDecCode().equals("11")) {

							sheet1.addCell(new Label(0, startRow+i," ",normalCenter));
							sheet1.mergeCells(1,startRow+i,2,startRow+i);
							sheet1.addCell(new Label(1, startRow+i,"1���/*1���",normalCenter));
							sheet1.mergeCells(3,startRow+i,4,startRow+i);
							sheet1.addCell(new Label(3, startRow+i,"1.5���/*1.5���",normalCenter));
							sheet1.mergeCells(5,startRow+i,6,startRow+i);
							sheet1.addCell(new Label(5, startRow+i,"3���/*3���",normalCenter));
							sheet1.addCell(new Label(7, startRow+i," ",normalCenter));
							sheet1.addCell(new Label(8, startRow+i,"*�ѹ�ӧҹ/�ѹ��ش�ѡ�ѵġ��",normalLeft));
							i++;
							List rwOverTime = this.getPrEmployeeService()
									.findDetailPaySlipOverTime(ouCode, userId,
											year, period, value.getDivCode());
							RwOvertimeVO voOt = new RwOvertimeVO();
							String flagPrDesc = "";
							String flagPrDescTemp = "";
							String flagStatus = "";
							String flagWorkDesc = "";
							String otTypeDesc = "";
							String otTypeDescTemp = "";
							for (int l = 0; l < rwOverTime.size(); l++) {
								voOt = (RwOvertimeVO) rwOverTime.get(l);
								 if (voOt.getOtType().equals("1"))otTypeDesc = "�����ǧ����";
								 if (voOt.getOtType().equals("2"))otTypeDesc = "��ҷӧҹ��ѹ��ش";
								 
								 if (voOt.getFlagPr().equals("N"))flagPrDesc = "����";
								 if (voOt.getFlagPr().equals("A"))flagPrDesc = "��Ѻ��ا";
								 if (voOt.getFlagPr().equals("R"))flagPrDesc = "���¡�׹";
								 if (voOt.getFlagPr().equals("B"))flagPrDesc = "���ԡ��Ѻ��ا";
								 if (voOt.getFlagPr().equals("S"))flagPrDesc = "���ԡ���¡�׹";
								 
								 if (voOt.getFlagWork().equals("Y"))flagWorkDesc = "    ��ҧ�ͧ";
								 if (voOt.getFlagWork().equals("N"))flagWorkDesc = "���㹡ͧ";
								 
								if (!otTypeDesc.equals(otTypeDescTemp)) {
									flagStatus = otTypeDesc + "          ";
									otTypeDescTemp = otTypeDesc;
								} else {
									flagStatus = "";
								}
								if (!flagPrDesc.equals(flagPrDescTemp)
										|| !flagStatus.equals("")) {
									flagStatus = flagStatus + flagPrDesc
											+ "          ";
									flagPrDescTemp = flagPrDesc;
								} else {
									flagStatus = "";
								}
								flagStatus = flagStatus + flagWorkDesc;
								sheet1.addCell(new Label(0, startRow + i,
										flagStatus, normalRight));
								sheet1.mergeCells(1, startRow + i, 2, startRow
										+ i);
								sheet1.addCell(new Label(1, startRow + i, voOt
										.getTotDay1_R(), normalCenter));
								sheet1.mergeCells(3, startRow + i, 4, startRow
										+ i);
								sheet1.addCell(new Label(3, startRow + i, voOt
										.getTotDay15_R(), normalCenter));
								sheet1.mergeCells(5, startRow + i, 6, startRow
										+ i);
								sheet1.addCell(new Label(5, startRow + i, voOt
										.getTotDay3_R(), normalCenter));
								sheet1.addCell(new Label(7, startRow+i,"�������",normalRight));
								sheet1.addCell(new Label(8, startRow + i, " ",
										normalCenter));
								i++;
							}
						}

						// Detail Premiun
						if (value.getIncDecCode().equals("12")) {
							sheet1.addCell(new Label(0, startRow+i," ",normalCenter));
							sheet1.mergeCells(1,startRow+i,2,startRow+i);
							sheet1.addCell(new Label(1, startRow+i,"�����",normalCenter));
							sheet1.mergeCells(3,startRow+i,4,startRow+i);
							sheet1.addCell(new Label(3, startRow+i,"�к���",normalCenter));
							sheet1.mergeCells(5,startRow+i,6,startRow+i);
							sheet1.addCell(new Label(5, startRow+i,"�С�ҧ�׹",normalCenter));
							sheet1.addCell(new Label(7, startRow+i," ",normalCenter));
							sheet1.addCell(new Label(8, startRow+i,"(����/��������)",normalLeft));
							i++;
							List rwPremium = this.getPrEmployeeService()
									.findDetailPaySlipPremium(ouCode, userId,
											year, period, value.getDivCode());
							RwPremiumVO voPremium = new RwPremiumVO();
							String flagPrDesc = "";
							String flagPrDescTemp = "";
							String flagStatus = "";
							for (int l = 0; l < rwPremium.size(); l++) {
								voPremium = (RwPremiumVO) rwPremium.get(l);
								 if (voPremium.getFlagPr().equals("N"))flagPrDesc = "����";
								 if (voPremium.getFlagPr().equals("A"))flagPrDesc = "��Ѻ��ا";
								 if (voPremium.getFlagPr().equals("R"))flagPrDesc = "���¡�׹";
								 if (voPremium.getFlagPr().equals("B"))flagPrDesc = "���ԡ��Ѻ��ا";
								 if (voPremium.getFlagPr().equals("S"))flagPrDesc = "���ԡ���¡�׹";
								 
								if (!flagPrDesc.equals(flagPrDescTemp)) {
									flagStatus = flagPrDesc;
									flagPrDescTemp = flagPrDesc;
								} else {
									flagStatus = "";
								}
								sheet1.addCell(new Label(0, startRow + i,
										flagStatus, normalRight));
								sheet1.mergeCells(1, startRow + i, 2, startRow
										+ i);
								sheet1.addCell(new Label(1, startRow + i, dfInt
										.format(voPremium.getMorDay())
										+ "/"
										+ dfInt.format(voPremium.getMorHour()),
										normalCenter));
								sheet1.mergeCells(3, startRow + i, 4, startRow
										+ i);
								sheet1.addCell(new Label(3, startRow + i, dfInt
										.format(voPremium.getAftDay())
										+ "/"
										+ dfInt.format(voPremium.getAftHour()),
										normalCenter));
								sheet1.mergeCells(5, startRow + i, 6, startRow
										+ i);
								sheet1.addCell(new Label(5, startRow + i, dfInt
										.format(voPremium.getEvnDay())
										+ "/"
										+ dfInt.format(voPremium.getEvnHour()),
										normalCenter));

								i++;
							}
						}

						// Detail Health
						if (value.getIncDecCode().equals("13")) {

							List rwHealth = this.getPrEmployeeService()
									.findDetailPaySlipHealth(ouCode, userId,
											year, period, value.getDivCode());
							RwHealthVO voHealth = new RwHealthVO();
							String flagPrDesc = "";
							String flagPrDescTemp = "";
							String flagStatus = "";
							for (int l = 0; l < rwHealth.size(); l++) {
								voHealth = (RwHealthVO) rwHealth.get(l);
								 if (voHealth.getFlagPr().equals("N"))flagPrDesc = "����";
								 if (voHealth.getFlagPr().equals("A"))flagPrDesc = "��Ѻ��ا";
								 if (voHealth.getFlagPr().equals("R"))flagPrDesc = "���¡�׹";
								 if (voHealth.getFlagPr().equals("B"))flagPrDesc = "���ԡ��Ѻ��ا";
								 if (voHealth.getFlagPr().equals("S"))flagPrDesc = "���ԡ���¡�׹";
								 
								if (!flagPrDesc.equals(flagPrDescTemp)) {
									flagStatus = flagPrDesc;
									flagPrDescTemp = flagPrDesc;
								} else {
									flagStatus = "";
								}
								sheet1.addCell(new Label(0, startRow + i,
										flagStatus, normalRight));
								sheet1.addCell(new Label(1, startRow + i, dfInt
										.format(voHealth.getTotalHour()),
										normalRight));
								sheet1.addCell(new Label(2, startRow+i,"�������",normalRight));

								i++;
							}
						}

						// Detail Health
						if (value.getIncDecCode().equals("14")) {

							List rwDager = this.getPrEmployeeService()
									.findDetailPaySlipDanger(ouCode, userId,
											year, period, value.getDivCode());
							RwDangerVO voDanger = new RwDangerVO();
							String flagPrDesc = "";
							String flagPrDescTemp = "";
							String flagStatus = "";
							for (int l = 0; l < rwDager.size(); l++) {
								voDanger = (RwDangerVO) rwDager.get(l);
								 if (voDanger.getFlagPr().equals("N"))flagPrDesc = "����";
								 if (voDanger.getFlagPr().equals("A"))flagPrDesc = "��Ѻ��ا";
								 if (voDanger.getFlagPr().equals("R"))flagPrDesc = "���¡�׹";
								 if (voDanger.getFlagPr().equals("B"))flagPrDesc = "���ԡ��Ѻ��ا";
								 if (voDanger.getFlagPr().equals("S"))flagPrDesc = "���ԡ���¡�׹";
								 
								if (!flagPrDesc.equals(flagPrDescTemp)) {
									flagStatus = flagPrDesc;
									flagPrDescTemp = flagPrDesc;
								} else {
									flagStatus = "";
								}
								sheet1.addCell(new Label(0, startRow + i,
										flagStatus, normalRight));
								sheet1.addCell(new Label(1, startRow + i, dfInt
										.format(voDanger.getFullDay())
										+ "/"
										+ dfInt.format(voDanger.getHalfDay()),
										normalCenter));
								sheet1.addCell(new Label(2, startRow+i,"(����ѹ/�����ѹ)",normalLeft));

								i++;
							}
						}
					}

				}
				// ��鹻Դ
				// sheet1.mergeCells(0,startRow+i, 12,startRow+i);
				sheet1.addCell(new Label(0, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(1, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(2, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(3, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(4, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(5, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(6, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(7, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(8, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(9, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(10, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(11, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(12, startRow + i, "", borderEnd));
				// ��鹻Դ
				// ˹���ʴ������

				i = 0;
				for (Iterator it = new TreeSet(hashAll.keySet()).iterator(); it
						.hasNext();) {
					key = (String) it.next();
					value = (CTRWRP201VO) hashAll.get(key);

					if (value.getIncDecCode().equals("00")) {
						if (i == 0) {
							ww.copySheet("Sheet1", "���������", ww.getNumberOfSheets());

							// j++;
							j = 3;
							if (j > 0) {
								sheet1.getSettings().setPassword("123");
								sheet1.getSettings().setProtected(true);
							}

							sheet1 = ww.getSheet(j);
							sheet1.setName("���������");
							String otDesc="����� CTRWRP201                                                                                         ��§ҹ�ʹ��ػ㹡�èѴ�� Pay Slip";
							
							
							sheet1.addCell(new Label(0, 0, ChgNullToEmpty(
									ouDesc, ""), header));
							sheet1.addCell(new Label(0, 1, ChgNullToEmpty(
									otDesc, ""), HeadFormat));
							// sheet1.addCell(new Label(0, 2,"��ШӧǴ��� "+
							// periodName + " �.�." +
							// year+" �ѹ����~� :
							// "+sdfPrint.format(gd.getTime()),headerRight));
							sheet1.addCell(new Label(0,2, "�������  "+userInfo.getUserName(), headerLeft));
							sheet1.addCell(new Label(1, 2,"��ШӧǴ��� "+ periodName + " �.�." + year,header));
                            sheet1.addCell(new Label(8, 2,"�ѹ������� : "+sdfPrint.format(gd.getTime()),headerRight));
							sheet1.addCell(new Label(0, 3,"���������",header));
							sheet1.addCell(new Label(0, 4,"�ӹǹ��ѡ�ҹ/�١��ҧ��Шӷ�����  "+ value.getCntEmp() + " ��       ���  " + value.getEmpA()+" ��¡��    ����  "+value.getEmpI()+" ��¡��    ź  "+value.getEmpD()+" ��¡��",header));
							sheet1.addCell(new Label(0, 5,"�ӹǹ��������Ѻ�Թ�ѧ�վ  "+ value.getCostLife() + " ��      �ӹǹ��������Ѻ���¡ѹ���  " + value.getGundan() +" ��    �ӹǹ��������Ѻ��Ҫ�������ͺص�  "+value.getCostChild()+" ��    �ӹǹ�ص÷�����Ѻ��Ҫ��������  "+value.getSumCostChild()+" ��",header));
							
							i++;
							i++;
						} else {
							// begin new table
							// sheet1 = ww.getSheet(j);
							sheet1.mergeCells(0, startRow + i, 12, startRow + i);
							sheet1.addCell(new Label(0, startRow + i, "",
									borderEnd));

							i++;
							sheet1.mergeCells(0, startRow + i, 12, startRow + i);
							sheet1.addCell(new Label(0, startRow+i,"����/ʹ�.   "+ value.getDivCode()+" : "+value.getDivName(),header));
							i++;
							sheet1.mergeCells(0,startRow+i, 12,startRow+i);
							sheet1.addCell(new Label(0, startRow+i,"�ӹǹ��ѡ�ҹ/�١��ҧ��Шӷ�����  "+ value.getCntEmp() + " ��       ���  " + value.getEmpA()+" ��¡��    ����  "+value.getEmpI()+" ��¡��    ź  "+value.getEmpD()+" ��¡��",header));
							i++;
							sheet1.mergeCells(0,startRow+i, 12,startRow+i);
							sheet1.addCell(new Label(0, startRow+i,"�ӹǹ��������Ѻ�Թ�ѧ�վ  "+ value.getCostLife() + " ��      �ӹǹ��������Ѻ���¡ѹ���  " + value.getGundan() +" ��    �ӹǹ��������Ѻ��Ҫ�������ͺص�  "+value.getCostChild()+" ��    �ӹǹ�ص÷�����Ѻ��Ҫ��������  "+value.getSumCostChild()+" ��",header));
							
							i++;
							
							//sheet1.setRowView(startRow+i, 550);
							sheet1.mergeCells(0,startRow+i, 0,startRow+i+1);
							sheet1.addCell(new Label(0, startRow+i, "��¡��", mergeCenterBoldBGGrayFormat));
							sheet1.mergeCells(1,startRow+i, 2,startRow+i);
							sheet1.addCell(new Label(1, startRow+i, "�����ŷ�����", mergeCenterBoldBGGrayFormat));
							sheet1.mergeCells(3,startRow+i, 4,startRow+i);
							sheet1.addCell(new Label(3, startRow+i, "����", mergeCenterBoldBGGrayFormat));
							sheet1.mergeCells(5,startRow+i, 6,startRow+i);
							sheet1.addCell(new Label(5, startRow+i, "��Ѻ��ا", mergeCenterBoldBGGrayFormat));
							sheet1.mergeCells(7,startRow+i, 8,startRow+i);
							sheet1.addCell(new Label(7, startRow+i, "���¡�׹", mergeCenterBoldBGGrayFormat));
							sheet1.mergeCells(9,startRow+i, 10,startRow+i);
							sheet1.addCell(new Label(9, startRow+i, "���ԡ��Ѻ��ا", mergeCenterBoldBGGrayFormat));
							sheet1.mergeCells(11,startRow+i, 12,startRow+i);
							sheet1.addCell(new Label(11, startRow+i, "���ԡ���¡�׹", mergeCenterBoldBGGrayFormat));
							
							i++;
							
							sheet1.addCell(new Label(0, startRow+i, "", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(1, startRow+i, "��¡��", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(2, startRow+i, "�ҷ", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(3, startRow+i, "��¡��", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(4, startRow+i, "�ҷ", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(5, startRow+i, "��¡��", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(6, startRow+i, "�ҷ", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(7, startRow+i, "��¡��", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(8, startRow+i, "�ҷ", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(9, startRow+i, "��¡��", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(10, startRow+i, "�ҷ", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(11, startRow+i, "��¡��", mergeCenterBoldBGGrayFormat));
							sheet1.addCell(new Label(12, startRow+i, "�ҷ", mergeCenterBoldBGGrayFormat));
							i++;
					//	 end new table
							
						}
						
					}
					
					if (!value.getIncDecCode().equals("00")) {
						sheet1.addCell(new Label(0, startRow + i,
								value.getIncDecCode() + "  "
										+ value.getIncDecName(), borderString));
						sheet1.addCell(new Label(1, startRow + i, String
								.valueOf(value.getCntRec()), borderNumber2));
						sheet1.addCell(new Label(2, startRow + i,
								convertFormatNumber(value.getAmtRec()),
								borderNumber2));
						sheet1.addCell(new Label(3, startRow + i, String
								.valueOf(value.getCntN()), borderNumber2));
						sheet1.addCell(new Label(4, startRow + i,
								convertFormatNumber(value.getAmtN()),
								borderNumber2));
						sheet1.addCell(new Label(5, startRow + i, String
								.valueOf(value.getCntA()), borderNumber2));
						sheet1.addCell(new Label(6, startRow + i,
								convertFormatNumber(value.getAmtA()),
								borderNumber2));
						sheet1.addCell(new Label(7, startRow + i, String
								.valueOf(value.getCntR()), borderNumber2));
						sheet1.addCell(new Label(8, startRow + i,
								convertFormatNumber(value.getAmtR()),
								borderNumber2));
						sheet1.addCell(new Label(9, startRow + i, String
								.valueOf(value.getCntB()), borderNumber2));
						sheet1.addCell(new Label(10, startRow + i,
								convertFormatNumber(value.getAmtB()),
								borderNumber2));
						sheet1.addCell(new Label(11, startRow + i, String
								.valueOf(value.getCntS()), borderNumber2));
						sheet1.addCell(new Label(12, startRow + i,
								convertFormatNumber(value.getAmtS()),
								borderNumber2));
						i++;

						// Detail Overtime
						if (value.getIncDecCode().equals("11")) {

							sheet1.addCell(new Label(0, startRow + i, " ",
									normalCenter));
							sheet1.mergeCells(1, startRow + i, 2, startRow + i);
							sheet1.addCell(new Label(1, startRow+i,"1���/*1���",normalCenter));
							sheet1.mergeCells(3,startRow+i,4,startRow+i);
							sheet1.addCell(new Label(3, startRow+i,"1.5���/*1.5���",normalCenter));
							sheet1.mergeCells(5,startRow+i,6,startRow+i);
							sheet1.addCell(new Label(5, startRow+i,"3���/*3���",normalCenter));
							sheet1.addCell(new Label(7, startRow+i," ",normalCenter));
							sheet1.addCell(new Label(8, startRow+i,"*�ѹ�ӧҹ/�ѹ��ش�ѡ�ѵġ��",normalLeft));
							i++;
							List rwOverTime = this.getPrEmployeeService()
									.findDetailPaySlipOverTime(ouCode, userId,
											year, period, null);
							RwOvertimeVO voOt = new RwOvertimeVO();
							String flagPrDesc = "";
							String flagPrDescTemp = "";
							String flagStatus = "";
							String flagWorkDesc = "";
							String otTypeDesc = "";
							String otTypeDescTemp = "";
							for (int l = 0; l < rwOverTime.size(); l++) {
								voOt = (RwOvertimeVO) rwOverTime.get(l);
								 if (voOt.getOtType().equals("1"))otTypeDesc = "�����ǧ����";
								 if (voOt.getOtType().equals("2"))otTypeDesc = "��ҷӧҹ��ѹ��ش";
								 
								 if (voOt.getFlagPr().equals("N"))flagPrDesc = "����";
								 if (voOt.getFlagPr().equals("A"))flagPrDesc = "��Ѻ��ا";
								 if (voOt.getFlagPr().equals("R"))flagPrDesc = "���¡�׹";
								 if (voOt.getFlagPr().equals("B"))flagPrDesc = "���ԡ��Ѻ��ا";
								 if (voOt.getFlagPr().equals("S"))flagPrDesc = "���ԡ���¡�׹";
								 
								 if (voOt.getFlagWork().equals("Y"))flagWorkDesc = "    ��ҧ�ͧ";
								 if (voOt.getFlagWork().equals("N"))flagWorkDesc = "���㹡ͧ";
								 
								if (!otTypeDesc.equals(otTypeDescTemp)) {
									flagStatus = otTypeDesc + "          ";
									otTypeDescTemp = otTypeDesc;
								} else {
									flagStatus = "";
								}
								if (!flagPrDesc.equals(flagPrDescTemp)
										|| !flagStatus.equals("")) {
									flagStatus = flagStatus + flagPrDesc
											+ "          ";
									flagPrDescTemp = flagPrDesc;
								} else {
									flagStatus = "";
								}
								flagStatus = flagStatus + flagWorkDesc;
								sheet1.addCell(new Label(0, startRow + i,
										flagStatus, normalRight));
								sheet1.mergeCells(1, startRow + i, 2, startRow
										+ i);
								sheet1.addCell(new Label(1, startRow + i, voOt
										.getTotDay1_R(), normalCenter));
								sheet1.mergeCells(3, startRow + i, 4, startRow
										+ i);
								sheet1.addCell(new Label(3, startRow + i, voOt
										.getTotDay15_R(), normalCenter));
								sheet1.mergeCells(5, startRow + i, 6, startRow
										+ i);
								sheet1.addCell(new Label(5, startRow + i, voOt
										.getTotDay3_R(), normalCenter));
								sheet1.addCell(new Label(7, startRow+i,"�������",normalRight));
								sheet1.addCell(new Label(8, startRow + i, " ",
										normalCenter));
								i++;
							}
						}

						// Detail Premiun
						if (value.getIncDecCode().equals("12")) {
							sheet1.addCell(new Label(0, startRow + i, " ",
									normalCenter));
							sheet1.mergeCells(1, startRow + i, 2, startRow + i);
							sheet1.addCell(new Label(1, startRow+i,"�����",normalCenter));
							sheet1.mergeCells(3,startRow+i,4,startRow+i);
							sheet1.addCell(new Label(3, startRow+i,"�к���",normalCenter));
							sheet1.mergeCells(5,startRow+i,6,startRow+i);
							sheet1.addCell(new Label(5, startRow+i,"�С�ҧ�׹",normalCenter));
							sheet1.addCell(new Label(7, startRow+i," ",normalCenter));
							sheet1.addCell(new Label(8, startRow+i,"(����/��������)",normalLeft));
							i++;
					

							List rwPremium = this.getPrEmployeeService()
									.findDetailPaySlipPremium(ouCode, userId,
											year, period, null);
							RwPremiumVO voPremium = new RwPremiumVO();
							String flagPrDesc = "";
							String flagPrDescTemp = "";
							String flagStatus = "";
							for (int l = 0; l < rwPremium.size(); l++) {
								voPremium = (RwPremiumVO) rwPremium.get(l);
								 if (voPremium.getFlagPr().equals("N"))flagPrDesc = "����";
								 if (voPremium.getFlagPr().equals("A"))flagPrDesc = "��Ѻ��ا";
								 if (voPremium.getFlagPr().equals("R"))flagPrDesc = "���¡�׹";
								 if (voPremium.getFlagPr().equals("B"))flagPrDesc = "���ԡ��Ѻ��ا";
								 if (voPremium.getFlagPr().equals("S"))flagPrDesc = "���ԡ���¡�׹";
								 
								if (!flagPrDesc.equals(flagPrDescTemp)) {
									flagStatus = flagPrDesc;
									flagPrDescTemp = flagPrDesc;
								} else {
									flagStatus = "";
								}
								sheet1.addCell(new Label(0, startRow + i,
										flagStatus, normalRight));
								sheet1.mergeCells(1, startRow + i, 2, startRow
										+ i);
								sheet1.addCell(new Label(1, startRow + i, dfInt
										.format(voPremium.getMorDay())
										+ "/"
										+ dfInt.format(voPremium.getMorHour()),
										normalCenter));
								sheet1.mergeCells(3, startRow + i, 4, startRow
										+ i);
								sheet1.addCell(new Label(3, startRow + i, dfInt
										.format(voPremium.getAftDay())
										+ "/"
										+ dfInt.format(voPremium.getAftHour()),
										normalCenter));
								sheet1.mergeCells(5, startRow + i, 6, startRow
										+ i);
								sheet1.addCell(new Label(5, startRow + i, dfInt
										.format(voPremium.getEvnDay())
										+ "/"
										+ dfInt.format(voPremium.getEvnHour()),
										normalCenter));

								i++;
							}
						}

						// Detail Health
						if (value.getIncDecCode().equals("13")) {

							List rwHealth = this.getPrEmployeeService()
									.findDetailPaySlipHealth(ouCode, userId,
											year, period, null);
							RwHealthVO voHealth = new RwHealthVO();
							String flagPrDesc = "";
							String flagPrDescTemp = "";
							String flagStatus = "";
							for (int l = 0; l < rwHealth.size(); l++) {
								voHealth = (RwHealthVO) rwHealth.get(l);
								 if (voHealth.getFlagPr().equals("N"))flagPrDesc = "����";
								 if (voHealth.getFlagPr().equals("A"))flagPrDesc = "��Ѻ��ا";
								 if (voHealth.getFlagPr().equals("R"))flagPrDesc = "���¡�׹";
								 if (voHealth.getFlagPr().equals("B"))flagPrDesc = "���ԡ��Ѻ��ا";
								 if (voHealth.getFlagPr().equals("S"))flagPrDesc = "���ԡ���¡�׹";
								 
								if (!flagPrDesc.equals(flagPrDescTemp)) {
									flagStatus = flagPrDesc;
									flagPrDescTemp = flagPrDesc;
								} else {
									flagStatus = "";
								}
								sheet1.addCell(new Label(0, startRow + i,
										flagStatus, normalRight));
								sheet1.addCell(new Label(1, startRow + i, dfInt
										.format(voHealth.getTotalHour()),
										normalRight));
								sheet1.addCell(new Label(2, startRow+i,"�������",normalRight));
								i++;
							}
						}

						// Detail Health
						if (value.getIncDecCode().equals("14")) {

							List rwDager = this.getPrEmployeeService()
									.findDetailPaySlipDanger(ouCode, userId,
											year, period, null);
							RwDangerVO voDanger = new RwDangerVO();
							String flagPrDesc = "";
							String flagPrDescTemp = "";
							String flagStatus = "";
							for (int l = 0; l < rwDager.size(); l++) {
								voDanger = (RwDangerVO) rwDager.get(l);
								 if (voDanger.getFlagPr().equals("N"))flagPrDesc = "����";
								 if (voDanger.getFlagPr().equals("A"))flagPrDesc = "��Ѻ��ا";
								 if (voDanger.getFlagPr().equals("R"))flagPrDesc = "���¡�׹";
								 if (voDanger.getFlagPr().equals("B"))flagPrDesc = "���ԡ��Ѻ��ا";
								 if (voDanger.getFlagPr().equals("S"))flagPrDesc = "���ԡ���¡�׹";
								 
								if (!flagPrDesc.equals(flagPrDescTemp)) {
									flagStatus = flagPrDesc;
									flagPrDescTemp = flagPrDesc;
								} else {
									flagStatus = "";
								}
								sheet1.addCell(new Label(0, startRow + i,
										flagStatus, normalRight));
								sheet1.addCell(new Label(1, startRow + i, dfInt
										.format(voDanger.getFullDay())
										+ "/"
										+ dfInt.format(voDanger.getHalfDay()),
										normalCenter));
								sheet1.addCell(new Label(2, startRow+i,"(����ѹ/�����ѹ)",normalLeft));

								i++;
							}
						}
						i++;
					}

				}
				i--;
				// ��鹻Դ
				// sheet1.mergeCells(0,startRow+i, 12,startRow+i);
				sheet1.addCell(new Label(0, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(1, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(2, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(3, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(4, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(5, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(6, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(7, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(8, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(9, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(10, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(11, startRow + i, "", borderEnd));
				sheet1.addCell(new Label(12, startRow + i, "", borderEnd));
				// ��鹻Դ

				sheet1.getSettings().setPassword("123");
				sheet1.getSettings().setProtected(true);

				if (ww.getNumberOfSheets() > 1) {
					ww.removeSheet(0);
				}

				// sheet1 = ww.getSheet(0);
				// sheet1.getSettings().setHidden(true);
				ww.setProtected(true);
				ww.write();
				ww.close();
				// sheet1.getSettings().setHidden(false);

				wb.close();
				in.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		} else {
			ww.copySheet("Sheet1", "��ǹ_���ӡ��" , ww.getNumberOfSheets());	
			sheet1.setName("��ǹ/���ӡ��");
			String otDesc="����� CTRWRP201                                                                                         ��§ҹ�ʹ��ػ㹡�èѴ�� Pay Slip";
			sheet1 = ww.getSheet(1);

			sheet1.addCell(new Label(0, 0, ChgNullToEmpty(ouDesc, ""), header));
			sheet1.addCell(new Label(0, 1, ChgNullToEmpty(otDesc, ""),
					HeadFormat));
			sheet1.addCell(new Label(0,2, "�������  "+userInfo.getUserName(), headerLeft));
			sheet1.addCell(new Label(1, 2,"��ШӧǴ��� "+ periodName + " �.�." + year+"                                                �ѹ������� : "+sdfPrint.format(gd.getTime()),headerRight));
			sheet1.addCell(new Label(0, 3,"����/ʹ�.   .... : ..........    ʾ/��.   ........ : ............."));
			sheet1.addCell(new Label(0, 4,"�ӹǹ��ѡ�ҹ/�١��ҧ��Шӷ�����  0 ��       ���  0 ��¡��    ����  0 ��¡��    ź  0 ��¡��",header));
	
//			sheet1.addCell(new Blank(5, 8, normal));
			sheet1.mergeCells(0,startRow+i,12,startRow+i);
			sheet1.addCell(new Label(0, startRow+i,"��辺������", noData));
			sheet1.addCell(new Blank(1, startRow + i, noData));
			sheet1.addCell(new Blank(2, startRow + i, noData));
			sheet1.addCell(new Blank(3, startRow + i, noData));
			sheet1.addCell(new Blank(4, startRow + i, noData));
			sheet1.addCell(new Blank(5, startRow + i, noData));
			sheet1.addCell(new Blank(6, startRow + i, noData));
			sheet1.addCell(new Blank(7, startRow + i, noData));
			sheet1.addCell(new Blank(8, startRow + i, noData));
			sheet1.addCell(new Blank(9, startRow + i, noData));
			sheet1.addCell(new Blank(10, startRow + i, noData));
			sheet1.addCell(new Blank(11, startRow + i, noData));
			sheet1.addCell(new Blank(12, startRow + i, noData));
			sheet1.getSettings().setPassword("123");
			sheet1.getSettings().setProtected(true);
			ww.removeSheet(0);
			ww.setProtected(true);
			ww.write();
			ww.close();
			wb.close();
			in.close();
			return null;
		}

	}

	private String ChgNullToEmpty(String str1, String str2) {
		if (str1 == null) {
			str1 = str2;
		}
		return str1;
	}

	/**
	 * method ����Ѻ�ŧ Time Format
	 * 
	 * @param hour
	 * @return
	 */
	private String convertFormatNumber(Double hour) {
		String rlst = "";
		DecimalFormat dec = new DecimalFormat("###,##0.00");
		rlst = dec.format(hour != null ? hour.doubleValue() : 0);
		return rlst;
	}
}
