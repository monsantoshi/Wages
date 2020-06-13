/*
 * Created on 31 �.�. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.control;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.format.VerticalAlignment;
import jxl.write.Blank;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;


import com.ss.tp.dto.FeeWpayPrDailyNetAmtRepVO;
import com.ss.tp.security.UserInfo;

import com.ss.tp.service.FeeWpayPrEmployeeService;
import com.ss.tp.service.SuOrganizeService;

/**
 * @author airsenal
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class CTWPAYAPRP013Controller extends MultiActionController {

	
	
	public FeeWpayPrEmployeeService getFeeWpayPrEmployeeService() {
		return (FeeWpayPrEmployeeService) this.getApplicationContext().getBean("feeWpayPrEmployeeService");
	}

	public SuOrganizeService getSuOrganizeService() {
		return (SuOrganizeService) this.getApplicationContext().getBean(
				"suOrganizeService");
	}

	/**
	 * method ����Ѻ���ҧ��§ҹ�Թ���ا�آ�Ҿ
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView doPrintReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int year = 0;
		int period = 0;
		//String type = request.getParameter("type");
		//String ouCode = request.getParameter("ouCode");
		//String userId = request.getParameter("userId");
		String ouCode = "";
		String userId = "";
		String periodName = request.getParameter("periodName");
		DecimalFormat df = new DecimalFormat("###,##0.00");
		DecimalFormat dfInt = new DecimalFormat("###,##0");
		DecimalFormat dfYear = new DecimalFormat("#####0");
		if (request.getParameter("ouCode") != null) {
			ouCode = request.getParameter("ouCode");
		}

		if (request.getParameter("userId") != null) {
			userId = request.getParameter("userId");
		}

		if (request.getParameter("year") != null
				&& !"".equals(request.getParameter("year"))) {
			year = Integer.parseInt(request.getParameter("year"));
		}

		if (request.getParameter("period") != null) {
			period = Integer.parseInt(request.getParameter("period"));
		}
		try {
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition",
					"attachment; filename=./page/report/CTWPAYAPRP013.xls");
			InputStream in = this.getServletContext().getResourceAsStream(
					"/page/report/CTWPAYAPRP013.xls");

			Workbook wb = Workbook.getWorkbook(in);
			WritableWorkbook ww = Workbook.createWorkbook(
					response.getOutputStream(), wb);
			WritableSheet sheet = ww.getSheet(0);

			WritableFont font = new WritableFont(WritableFont.ARIAL);
			font.setPointSize(9);

			Alignment dataAlignLeft = Alignment.LEFT;
			Alignment dataAlignRight = Alignment.RIGHT;
			Alignment dataAlignCenter = Alignment.CENTRE;

			WritableFont fontBold = new WritableFont(WritableFont.ARIAL);
			fontBold.setBoldStyle(WritableFont.BOLD);
			fontBold.setPointSize(9);

			Alignment alg = Alignment.getAlignment(1);

			WritableCellFormat boldCenterFormat = new WritableCellFormat();
			boldCenterFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
			boldCenterFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			boldCenterFormat.setAlignment(dataAlignCenter);
			boldCenterFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			boldCenterFormat.setFont(font);

			WritableCellFormat normalCenterFormat = new WritableCellFormat();
			normalCenterFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
			normalCenterFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			normalCenterFormat.setAlignment(dataAlignCenter);
			normalCenterFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			normalCenterFormat.setFont(font);

			WritableCellFormat borderNumber = new WritableCellFormat();
			borderNumber.setBorder(Border.LEFT, BorderLineStyle.THIN);
			borderNumber.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			borderNumber.setAlignment(Alignment.RIGHT);
			borderNumber.setFont(font);

			WritableCellFormat borderNumber2 = new WritableCellFormat();
			borderNumber2.setBorder(Border.ALL, BorderLineStyle.THIN);
			borderNumber2.setAlignment(Alignment.RIGHT);
			borderNumber2.setFont(fontBold);

			WritableCellFormat HeadFormat = new WritableCellFormat();
			HeadFormat.setAlignment(Alignment.LEFT);
			HeadFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			HeadFormat.setFont(fontBold);

			WritableCellFormat borderEndLine = new WritableCellFormat();
			borderEndLine.setBorder(Border.TOP, BorderLineStyle.THIN);
			borderEndLine.setAlignment(Alignment.LEFT);

			CellFormat title = sheet.getWritableCell(3, 0).getCellFormat();
			// CellFormat headRight = sheet.getWritableCell(0,
			// 2).getCellFormat();
			CellFormat headerRpt = sheet.getWritableCell(4, 0).getCellFormat();
			CellFormat group = sheet.getWritableCell(0, 5).getCellFormat();
			// CellFormat borderLR = sheet.getWritableCell(3,
			// 5).getCellFormat();

			WritableCellFormat borderLR = new WritableCellFormat();
			borderLR.setBorder(Border.LEFT, BorderLineStyle.THIN);
			borderLR.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			borderLR.setAlignment(dataAlignLeft);
			borderLR.setVerticalAlignment(VerticalAlignment.CENTRE);
			borderLR.setFont(font);

			WritableCellFormat headRight = new WritableCellFormat();
			headRight.setAlignment(dataAlignRight);
			headRight.setVerticalAlignment(VerticalAlignment.CENTRE);
			headRight.setFont(fontBold);

			WritableCellFormat headLeft = new WritableCellFormat();
			headLeft.setAlignment(dataAlignLeft);
			headLeft.setVerticalAlignment(VerticalAlignment.CENTRE);
			headLeft.setFont(fontBold);
			// CellFormat borderLRId = sheet.getWritableCell(2,
			// 5).getCellFormat();

			GregorianCalendar gd = new GregorianCalendar();
			SimpleDateFormat sdfPrint = new SimpleDateFormat(
					"dd/MM/yyyy HH:mm", new java.util.Locale("th", "TH"));
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
					"UserLogin");
			/* start title */
			// sheet.mergeCells(0, 1, 3, 1);
			// sheet.addCell(new Label(0, 1,
			// "����� CTRWRP004 ��§ҹ�Թ���z��ا�آ�Ҿ",
			// HeadFormat));
			// sheet.addCell(new Label(0, 0,
			// this.getSuOrganizeService().findOrganizeName(ouCode), title));
			sheet.addCell(new Label(0,2, "�������  "+userInfo.getUserName(), headLeft));
			sheet.addCell(new Label(10, 2, "��ШӧǴ��� " + periodName + "  �.�. " + year, headRight));
			sheet.addCell(new Label(23, 2, "�ѹ������� : "+sdfPrint.format(gd.getTime()), headRight));
			/* end title */

			FeeWpayPrDailyNetAmtRepVO helVo = null;
			List helRpt = this.getFeeWpayPrEmployeeService().feeWpayPrDailyRepByOrg(userId, ouCode,year, period);
			int startRow = 4;
			String tmpCode = "";
			int checkLV = 0;
			double sumTotalNewSalary = 0.0;
			double sumAllTotalNewSalary = 0.0;
			double sumTotalSalary = 0.0;
			double sumAllTotalSalary = 0.0;
			double sumTotalSalary02 = 0.0;
			double sumAllTotalSalary02 = 0.0;
			double sumTotalSalary03 = 0.0;
			double sumAllTotalSalary03 = 0.0;
			double sumTotalKlongchev = 0.0;
			double sumAllTotalKlongchev = 0.0;
			double sumTotalChilds = 0.0;
			double sumAllTotalChilds = 0.0;
			
			double sumTotalNetIncome = 0.0;
			double sumAllTotalNetIncome = 0.0;
			double sumTotalVinai1Pct = 0.0;
			double sumAllTotalVinai1Pct = 0.0;
			double sumTotalVinai1Lev = 0.0;
			double sumAllTotalVinai1Lev = 0.0;
			double sumTotalVinai2 = 0.0;
			double sumAllTotalVinai2 = 0.0;
			double sumTotalRefund = 0.0;
			double sumAllTotalRefund = 0.0;
			double sumTotalCar = 0.0;
			double sumAllTotalCar = 0.0;
			double sumTotalMotor = 0.0;
			double sumAllTotalMotor = 0.0;
			double sumTotalRick = 0.0;
			double sumAllTotalRick = 0.0;
			double sumTotalStudy = 0.0;
			double sumAllTotalStudy = 0.0;
			double sumTotalSsr = 0.0;
			double sumAllTotalSsr = 0.0;
			
			double sumTotalKys = 0.0;
			double sumAllTotalKys = 0.0;
			
			double sumTotalKro = 0.0;
			double sumAllTotalKro = 0.0;
			
			double sumTotalNetDec = 0.0;
			double sumAllTotalNetDec = 0.0;
			double sumTotalNet = 0.0;
			double sumAllTotalNet = 0.0;
			
			double sumTotalOomsin = 0.0;
			double sumAllTotalOomsin = 0.0;
			
			double sumTotalLawLoan = 0.0;
			double sumAllTotalLawLoan = 0.0;
			
			double sumTotalPangLoan = 0.0;
			double sumAllTotalPangLoan = 0.0;
			
			
			String flagTotalHour = "";
			int seq = 0;
			if (helRpt.size() != 0) {
				for (int i = 0; i < helRpt.size(); i++) {
					helVo = (FeeWpayPrDailyNetAmtRepVO) helRpt.get(i);
					checkLV = checkLV(helVo.getAreaCode(), helVo.getSecCode());
					if (checkLV == 3) {
						if (!tmpCode.equals(helVo.getAreaCode())) {
							if (!flagTotalHour.equals("")) {
								sheet.mergeCells(0, startRow, 2, startRow);
								sheet.addCell(new Label(0, startRow, "���", borderNumber2));
								sheet.addCell(new Label(3, startRow, String.valueOf(df.format(sumTotalNewSalary)),borderNumber2));
								sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalSalary)),borderNumber2));
								sheet.addCell(new Label(5, startRow, String.valueOf(df.format(sumTotalSalary02)),borderNumber2));
								sheet.addCell(new Label(6, startRow, String.valueOf(df.format(sumTotalSalary03)),borderNumber2));
								sheet.addCell(new Label(7, startRow, String.valueOf(df.format(sumTotalKlongchev)),borderNumber2));
								sheet.addCell(new Label(8, startRow, String.valueOf(df.format(sumTotalChilds)),borderNumber2));
								sheet.addCell(new Label(9, startRow, String.valueOf(df.format(sumTotalNetIncome)),borderNumber2));
								sheet.addCell(new Label(10, startRow, String.valueOf(df.format(sumTotalVinai2)),borderNumber2));
								sheet.addCell(new Label(11, startRow, String.valueOf(df.format(sumTotalVinai1Pct)),borderNumber2));
								sheet.addCell(new Label(12, startRow, String.valueOf(df.format(sumTotalVinai1Lev)),borderNumber2));
								
								sheet.addCell(new Label(13, startRow, String.valueOf(df.format(sumTotalRefund)),borderNumber2));
								sheet.addCell(new Label(14, startRow, String.valueOf(df.format(sumTotalMotor)),borderNumber2));
								sheet.addCell(new Label(15, startRow, String.valueOf(df.format(sumTotalRick)),borderNumber2));
								sheet.addCell(new Label(16, startRow, String.valueOf(df.format(sumTotalStudy)),borderNumber2));
								
								sheet.addCell(new Label(17, startRow, String.valueOf(df.format(sumTotalLawLoan)),borderNumber2));
								sheet.addCell(new Label(18, startRow, String.valueOf(df.format(sumTotalPangLoan)),borderNumber2));
								sheet.addCell(new Label(19, startRow, String.valueOf(df.format(sumTotalSsr)),borderNumber2));
								sheet.addCell(new Label(20, startRow, String.valueOf(df.format(sumTotalKys)),borderNumber2));
								sheet.addCell(new Label(21, startRow, String.valueOf(df.format(sumTotalKro)),borderNumber2));
								
								sheet.addCell(new Label(22, startRow, String.valueOf(df.format(sumTotalNetDec)),borderNumber2));
								sheet.addCell(new Label(23, startRow, String.valueOf(df.format(sumTotalNet)),borderNumber2));
								
								
								sumTotalNewSalary = 0.0;
								sumTotalSalary02 = 0.0;
								sumTotalSalary03 = 0.0;
								sumTotalSalary = 0.0;
								sumTotalKlongchev = 0.0;
								sumTotalChilds = 0.0;
								
								sumTotalNetIncome = 0.0;
								sumTotalVinai1Pct = 0.0;
								sumTotalVinai1Lev = 0.0;
								sumTotalVinai2 = 0.0;
								sumTotalRefund = 0.0;
								sumTotalCar = 0.0;
								sumTotalMotor = 0.0;
								sumTotalRick = 0.0;
								sumTotalStudy = 0.0;
								sumTotalNetDec = 0.0;
								sumTotalNet = 0.0;
								sumTotalSsr = 0.0;
								sumTotalKys = 0.0;
								sumTotalKro = 0.0;
								sumTotalLawLoan = 0.0;
								sumTotalOomsin = 0.0;
								sumTotalPangLoan = 0.0;
								
								
								startRow++;
							}
							sheet.addCell(new Label(0, startRow, helVo.getOrgCode() + " " + helVo.getAreaDesc(),group));
							sheet.addCell(new Blank(1, startRow, borderLR));
							sheet.addCell(new Blank(2, startRow, borderLR));
							sheet.addCell(new Blank(3, startRow, borderLR));
							sheet.addCell(new Blank(4, startRow, borderLR));
							sheet.addCell(new Blank(5, startRow, borderLR));
							sheet.addCell(new Blank(6, startRow, borderLR));
							
							sheet.addCell(new Blank(7, startRow,  borderLR));
							sheet.addCell(new Blank(8, startRow,  borderLR));
							sheet.addCell(new Blank(9, startRow,  borderLR));
							sheet.addCell(new Blank(10, startRow,  borderLR));
							sheet.addCell(new Blank(11, startRow,  borderLR));
							sheet.addCell(new Blank(12, startRow, borderLR));
							sheet.addCell(new Blank(13, startRow, borderLR));
							sheet.addCell(new Blank(14, startRow,  borderLR));
							sheet.addCell(new Blank(15, startRow,  borderLR));
							sheet.addCell(new Blank(16, startRow,  borderLR));
							sheet.addCell(new Blank(17, startRow,  borderLR));
							sheet.addCell(new Blank(18, startRow,  borderLR));
							sheet.addCell(new Blank(19, startRow,  borderLR));
							sheet.addCell(new Blank(20, startRow,  borderLR));
							sheet.addCell(new Blank(21, startRow,  borderLR));
							sheet.addCell(new Blank(22, startRow,  borderLR));
							sheet.addCell(new Blank(23, startRow,  borderLR));
						
							sheet.setRowView(startRow, 320);
							startRow++;
							seq = seq + 1;
							sheet.addCell(new Label(0, startRow, String
									.valueOf(seq), borderNumber));
							sheet.addCell(new Label(1, startRow, helVo
									.getEmpCode(), boldCenterFormat));
							sheet.addCell(new Label(2, startRow, helVo.getFullName(), borderLR));
							sheet.addCell(new Label(3, startRow, String.valueOf(df.format(helVo.getNewSalary())),borderNumber));
							sheet.addCell(new Label(4, startRow, String.valueOf(df.format(helVo.getSalary())),borderNumber));
							sheet.addCell(new Label(5, startRow, String.valueOf(df.format(helVo.getSalary02())),borderNumber));
							sheet.addCell(new Label(6, startRow, String.valueOf(df.format(helVo.getSalary03())),borderNumber));
							sheet.addCell(new Label(7, startRow, String.valueOf(df.format(helVo.getKlongchev())),borderNumber));
							sheet.addCell(new Label(8, startRow, String.valueOf(df.format(helVo.getChilds())),borderNumber));
						
							sheet.addCell(new Label(9, startRow, String.valueOf(df.format(helVo.getNetIncome())),borderNumber));
							sheet.addCell(new Label(10, startRow, String.valueOf(df.format(helVo.getVinai2())),borderNumber));
							sheet.addCell(new Label(11, startRow, String.valueOf(df.format(helVo.getVinai1Pct())),borderNumber));
							sheet.addCell(new Label(12, startRow, String.valueOf(df.format(helVo.getVinai1Lev())),borderNumber));
							
							sheet.addCell(new Label(13, startRow, String.valueOf(df.format(helVo.getRefund())),borderNumber));
							sheet.addCell(new Label(14, startRow, String.valueOf(df.format(helVo.getMotor())),borderNumber));
							sheet.addCell(new Label(15, startRow, String.valueOf(df.format(helVo.getRick())),borderNumber));
							sheet.addCell(new Label(16, startRow, String.valueOf(df.format(helVo.getStudy())),borderNumber));
							
							sheet.addCell(new Label(17, startRow, String.valueOf(df.format(helVo.getLawLoan())),borderNumber));
							sheet.addCell(new Label(18, startRow, String.valueOf(df.format(helVo.getLoanPost())),borderNumber));
							sheet.addCell(new Label(19, startRow, String.valueOf(df.format(helVo.getSsr() )),borderNumber));
							sheet.addCell(new Label(20, startRow, String.valueOf(df.format(helVo.getKys() )),borderNumber));
							sheet.addCell(new Label(21, startRow, String.valueOf(df.format(helVo.getKro() )),borderNumber));
							
							sheet.addCell(new Label(22, startRow, String.valueOf(df.format(helVo.getNetDec())),borderNumber));
							sheet.addCell(new Label(23, startRow, String.valueOf(df.format(helVo.getNet())),borderNumber));
						
							
							flagTotalHour = "sum";
							
							if (helVo.getNewSalary() != null) {
								sumTotalNewSalary = sumTotalNewSalary
										+ helVo.getNewSalary().doubleValue();
								sumAllTotalNewSalary = sumAllTotalNewSalary
										+ helVo.getNewSalary().doubleValue();

							}
							if (helVo.getSalary() != null) {
								sumTotalSalary = sumTotalSalary
										+ helVo.getSalary().doubleValue();
								sumAllTotalSalary = sumAllTotalSalary
										+ helVo.getSalary().doubleValue();

							}
							if (helVo.getChilds() != null) {
								sumTotalChilds = sumTotalChilds
										+ helVo.getChilds().doubleValue();
								sumAllTotalChilds = sumAllTotalChilds
										+ helVo.getChilds().doubleValue();

							}
							if (helVo.getKlongchev() != null) {
								sumTotalKlongchev = sumTotalKlongchev
										+ helVo.getKlongchev().doubleValue();
								sumAllTotalKlongchev = sumAllTotalKlongchev
										+ helVo.getKlongchev().doubleValue();

							}
							
							
							
							if (helVo.getSalary02() != null) {
								sumTotalSalary02 = sumTotalSalary02
										+ helVo.getSalary02().doubleValue();
								sumAllTotalSalary02 = sumAllTotalSalary02
										+ helVo.getSalary02().doubleValue();

							}

							if (helVo.getSalary03() != null) {
								sumTotalSalary03 = sumTotalSalary03
										+ helVo.getSalary03().doubleValue();
								sumAllTotalSalary03 = sumAllTotalSalary03
										+ helVo.getSalary03().doubleValue();

							}
							if (helVo.getNetIncome() != null) {
								sumTotalNetIncome = sumTotalNetIncome
										+ helVo.getNetIncome().doubleValue();
								sumAllTotalNetIncome = sumAllTotalNetIncome
										+ helVo.getNetIncome().doubleValue();

							}
							if (helVo.getVinai1Pct() != null) {
								sumTotalVinai1Pct = sumTotalVinai1Pct
										+ helVo.getVinai1Pct().doubleValue();
								sumAllTotalVinai1Pct = sumAllTotalVinai1Pct
										+ helVo.getVinai1Pct().doubleValue();

							}
							
							if (helVo.getVinai1Lev() != null) {
								sumTotalVinai1Lev = sumTotalVinai1Lev
										+ helVo.getVinai1Lev().doubleValue();
								sumAllTotalVinai1Lev = sumAllTotalVinai1Lev
										+ helVo.getVinai1Lev().doubleValue();

							}
							
							if (helVo.getVinai2() != null) {
								sumTotalVinai2 = sumTotalVinai2
										+ helVo.getVinai2().doubleValue();
								sumAllTotalVinai2 = sumAllTotalVinai2
										+ helVo.getVinai2().doubleValue();

							}
							if (helVo.getCar() != null) {
								sumTotalCar = sumTotalCar
										+ helVo.getCar().doubleValue();
								sumAllTotalCar = sumAllTotalCar
										+ helVo.getCar().doubleValue();

							}
							
							if (helVo.getRefund() != null) {
								sumTotalRefund = sumTotalRefund
										+ helVo.getRefund().doubleValue();
								sumAllTotalRefund = sumAllTotalRefund
										+ helVo.getRefund().doubleValue();

							}
							
							if (helVo.getMotor() != null) {
								sumTotalMotor = sumTotalMotor
										+ helVo.getMotor().doubleValue();
								sumAllTotalMotor = sumAllTotalMotor
										+ helVo.getMotor().doubleValue();

							}
							if (helVo.getRick() != null) {
								sumTotalRick = sumTotalRick
										+ helVo.getRick().doubleValue();
								sumAllTotalRick = sumAllTotalRick
										+ helVo.getRick().doubleValue();

							}
							if (helVo.getStudy() != null) {
								sumTotalStudy = sumTotalStudy
										+ helVo.getStudy().doubleValue();
								sumAllTotalStudy = sumAllTotalStudy
										+ helVo.getStudy().doubleValue();

							}
							
							if (helVo.getOomSin() != null) {
								sumTotalOomsin = sumTotalOomsin
										+ helVo.getOomSin().doubleValue();
								sumAllTotalOomsin = sumAllTotalOomsin
										+ helVo.getOomSin().doubleValue();

							}
							
							if (helVo.getLoanPost() != null) {
								sumTotalPangLoan = sumTotalPangLoan
										+ helVo.getLoanPost().doubleValue();
								sumAllTotalPangLoan = sumAllTotalPangLoan
										+ helVo.getLoanPost().doubleValue();

							}
							
							
							if (helVo.getLawLoan() != null) {
								sumTotalLawLoan = sumTotalLawLoan
										+ helVo.getLawLoan().doubleValue();
								sumAllTotalLawLoan = sumAllTotalLawLoan
										+ helVo.getLawLoan().doubleValue();

							}
							if (helVo.getSsr() != null) {
								sumTotalSsr = sumTotalSsr
										+ helVo.getSsr().doubleValue();
								sumAllTotalSsr = sumAllTotalSsr
										+ helVo.getSsr().doubleValue();

							}
							
							if (helVo.getKys() != null) {
								sumTotalKys = sumTotalKys
										+ helVo.getKys().doubleValue();
								sumAllTotalKys = sumAllTotalKys
										+ helVo.getKys().doubleValue();

							}
							
							if (helVo.getKro() != null) {
								sumTotalKro = sumTotalKro
										+ helVo.getKro().doubleValue();
								sumAllTotalKro = sumAllTotalKro
										+ helVo.getKro().doubleValue();

							}
							
						
						
							
							if (helVo.getNetDec() != null) {
								sumTotalNetDec = sumTotalNetDec
										+ helVo.getNetDec().doubleValue();
								sumAllTotalNetDec = sumAllTotalNetDec
										+ helVo.getNetDec().doubleValue();

							}
							
							if (helVo.getNet() != null) {
								sumTotalNet = sumTotalNet
										+ helVo.getNet().doubleValue();
								sumAllTotalNet = sumAllTotalNet
										+ helVo.getNet().doubleValue();

							}
							
							
							sheet.setRowView(startRow, 320);
							startRow++;
							if (helRpt.size() == (i + 1)) {
								sheet.mergeCells(0, startRow, 2, startRow);
								sheet.addCell(new Label(0, startRow, "���", borderNumber2));
								sheet.addCell(new Label(3, startRow, String.valueOf(df.format(sumTotalNewSalary)),borderNumber2));
								sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalSalary)),borderNumber2));
								sheet.addCell(new Label(5, startRow, String.valueOf(df.format(sumTotalSalary02)),borderNumber2));
								sheet.addCell(new Label(6, startRow, String.valueOf(df.format(sumTotalSalary03)),borderNumber2));
								sheet.addCell(new Label(7, startRow, String.valueOf(df.format(sumTotalKlongchev)),borderNumber2));
								sheet.addCell(new Label(8, startRow, String.valueOf(df.format(sumTotalChilds)),borderNumber2));
							
								sheet.addCell(new Label(9, startRow, String.valueOf(df.format(sumTotalNetIncome)),borderNumber2));
								sheet.addCell(new Label(10, startRow, String.valueOf(df.format(sumTotalVinai2)),borderNumber2));
								sheet.addCell(new Label(11, startRow, String.valueOf(df.format(sumTotalVinai1Pct)),borderNumber2));
								sheet.addCell(new Label(12, startRow, String.valueOf(df.format(sumTotalVinai1Lev)),borderNumber2));
								
								sheet.addCell(new Label(13, startRow, String.valueOf(df.format(sumTotalRefund)),borderNumber2));
								sheet.addCell(new Label(14, startRow, String.valueOf(df.format(sumTotalMotor)),borderNumber2));
								sheet.addCell(new Label(15, startRow, String.valueOf(df.format(sumTotalRick)),borderNumber2));
								sheet.addCell(new Label(16, startRow, String.valueOf(df.format(sumTotalStudy)),borderNumber2));
								
								sheet.addCell(new Label(17, startRow, String.valueOf(df.format(sumTotalLawLoan)),borderNumber2));
								sheet.addCell(new Label(18, startRow, String.valueOf(df.format(sumTotalPangLoan)),borderNumber2));
								sheet.addCell(new Label(19, startRow, String.valueOf(df.format(sumTotalSsr)),borderNumber2));
								sheet.addCell(new Label(20, startRow, String.valueOf(df.format(sumTotalKys)),borderNumber2));
								sheet.addCell(new Label(21, startRow, String.valueOf(df.format(sumTotalKro)),borderNumber2));
								
								
								sheet.addCell(new Label(22, startRow, String.valueOf(df.format(sumTotalNetDec)),borderNumber2));
								sheet.addCell(new Label(23, startRow, String.valueOf(df.format(sumTotalNet)),borderNumber2));
								
								sumTotalNewSalary = 0.0;
								sumTotalSalary02 = 0.0;
								sumTotalSalary03 = 0.0;
								sumTotalSalary = 0.0;
								sumTotalChilds = 0.0;
								sumTotalKlongchev = 0.0;
								
								sumTotalNetIncome = 0.0;
								sumTotalVinai1Pct = 0.0;
								sumTotalVinai1Lev = 0.0;
								sumTotalVinai2 = 0.0;
								sumTotalRefund = 0.0;
								sumTotalCar = 0.0;
								sumTotalMotor = 0.0;
								sumTotalRick = 0.0;
								sumTotalStudy = 0.0;
								sumTotalNetDec = 0.0;
								sumTotalNet = 0.0;
								sumTotalOomsin = 0.0;
								sumTotalPangLoan = 0.0;
								sumTotalLawLoan = 0.0;
								sumTotalSsr = 0.0;
								sumTotalKys = 0.0;
								sumTotalKro = 0.0;
								
								startRow++;
							}
						} else {
							seq = seq + 1;
							sheet.addCell(new Label(0, startRow, String
									.valueOf(seq), borderNumber));
							sheet.addCell(new Label(1, startRow, helVo
									.getEmpCode(), boldCenterFormat));
							sheet.addCell(new Label(2, startRow, helVo.getFullName(), borderLR));
							sheet.addCell(new Label(3, startRow, String.valueOf(df.format(helVo.getNewSalary())),borderNumber));
							sheet.addCell(new Label(4, startRow, String.valueOf(df.format(helVo.getSalary())),borderNumber));
							sheet.addCell(new Label(5, startRow, String.valueOf(df.format(helVo.getSalary02())),borderNumber));
							sheet.addCell(new Label(6, startRow, String.valueOf(df.format(helVo.getSalary03())),borderNumber));
							sheet.addCell(new Label(7, startRow, String.valueOf(df.format(helVo.getKlongchev())),borderNumber));
							sheet.addCell(new Label(8, startRow, String.valueOf(df.format(helVo.getChilds())),borderNumber));
						
							sheet.addCell(new Label(9, startRow, String.valueOf(df.format(helVo.getNetIncome())),borderNumber));
							sheet.addCell(new Label(10, startRow, String.valueOf(df.format(helVo.getVinai2())),borderNumber));
							sheet.addCell(new Label(11, startRow, String.valueOf(df.format(helVo.getVinai1Pct())),borderNumber));
							sheet.addCell(new Label(12, startRow, String.valueOf(df.format(helVo.getVinai1Lev())),borderNumber));
							
							sheet.addCell(new Label(13, startRow, String.valueOf(df.format(helVo.getRefund())),borderNumber));
							sheet.addCell(new Label(14, startRow, String.valueOf(df.format(helVo.getMotor())),borderNumber));
							sheet.addCell(new Label(15, startRow, String.valueOf(df.format(helVo.getRick())),borderNumber));
							sheet.addCell(new Label(16, startRow, String.valueOf(df.format(helVo.getStudy())),borderNumber));
							
							sheet.addCell(new Label(17, startRow, String.valueOf(df.format(helVo.getLawLoan())),borderNumber));
							
							sheet.addCell(new Label(18, startRow, String.valueOf(df.format(helVo.getLoanPost())),borderNumber));
							sheet.addCell(new Label(19, startRow, String.valueOf(df.format(helVo.getSsr())),borderNumber));
							sheet.addCell(new Label(20, startRow, String.valueOf(df.format(helVo.getKys())),borderNumber));
							sheet.addCell(new Label(21, startRow, String.valueOf(df.format(helVo.getKro())),borderNumber));
							
							sheet.addCell(new Label(22, startRow, String.valueOf(df.format(helVo.getNetDec())),borderNumber));
							sheet.addCell(new Label(23, startRow, String.valueOf(df.format(helVo.getNet())),borderNumber));
						
							flagTotalHour = "sum";
							
							if (helVo.getNewSalary() != null) {
								sumTotalNewSalary = sumTotalNewSalary
										+ helVo.getNewSalary().doubleValue();
								sumAllTotalNewSalary = sumAllTotalNewSalary
										+ helVo.getNewSalary().doubleValue();

							}
							if (helVo.getSalary() != null) {
								sumTotalSalary = sumTotalSalary
										+ helVo.getSalary().doubleValue();
								sumAllTotalSalary = sumAllTotalSalary
										+ helVo.getSalary().doubleValue();

							}
							if (helVo.getChilds() != null) {
								sumTotalChilds = sumTotalChilds
										+ helVo.getChilds().doubleValue();
								sumAllTotalChilds = sumAllTotalChilds
										+ helVo.getChilds().doubleValue();

							}
							if (helVo.getKlongchev() != null) {
								sumTotalKlongchev = sumTotalKlongchev
										+ helVo.getKlongchev().doubleValue();
								sumAllTotalKlongchev = sumAllTotalKlongchev
										+ helVo.getKlongchev().doubleValue();

							}
							
							if (helVo.getSalary02() != null) {
								sumTotalSalary02 = sumTotalSalary02
										+ helVo.getSalary02().doubleValue();
								sumAllTotalSalary02 = sumAllTotalSalary02
										+ helVo.getSalary02().doubleValue();

							}
							if (helVo.getSalary03() != null) {
								sumTotalSalary03 = sumTotalSalary03
										+ helVo.getSalary03().doubleValue();
								sumAllTotalSalary03 = sumAllTotalSalary03
										+ helVo.getSalary03().doubleValue();

							}
							if (helVo.getNetIncome() != null) {
								sumTotalNetIncome = sumTotalNetIncome
										+ helVo.getNetIncome().doubleValue();
								sumAllTotalNetIncome = sumAllTotalNetIncome
										+ helVo.getNetIncome().doubleValue();

							}
							if (helVo.getVinai1Pct() != null) {
								sumTotalVinai1Pct = sumTotalVinai1Pct
										+ helVo.getVinai1Pct().doubleValue();
								sumAllTotalVinai1Pct = sumAllTotalVinai1Pct
										+ helVo.getVinai1Pct().doubleValue();

							}
							
							if (helVo.getVinai1Lev() != null) {
								sumTotalVinai1Lev = sumTotalVinai1Lev
										+ helVo.getVinai1Lev().doubleValue();
								sumAllTotalVinai1Lev = sumAllTotalVinai1Lev
										+ helVo.getVinai1Lev().doubleValue();

							}
							
							if (helVo.getVinai2() != null) {
								sumTotalVinai2 = sumTotalVinai2
										+ helVo.getVinai2().doubleValue();
								sumAllTotalVinai2 = sumAllTotalVinai2
										+ helVo.getVinai2().doubleValue();

							}
							if (helVo.getCar() != null) {
								sumTotalCar = sumTotalCar
										+ helVo.getCar().doubleValue();
								sumAllTotalCar = sumAllTotalCar
										+ helVo.getCar().doubleValue();

							}
							
							if (helVo.getRefund() != null) {
								sumTotalRefund = sumTotalRefund
										+ helVo.getRefund().doubleValue();
								sumAllTotalRefund = sumAllTotalRefund
										+ helVo.getRefund().doubleValue();

							}
							
							
							if (helVo.getMotor() != null) {
								sumTotalMotor = sumTotalMotor
										+ helVo.getMotor().doubleValue();
								sumAllTotalMotor = sumAllTotalMotor
										+ helVo.getMotor().doubleValue();

							}
							if (helVo.getRick() != null) {
								sumTotalRick = sumTotalRick
										+ helVo.getRick().doubleValue();
								sumAllTotalRick = sumAllTotalRick
										+ helVo.getRick().doubleValue();

							}
							if (helVo.getStudy() != null) {
								sumTotalStudy = sumTotalStudy
										+ helVo.getStudy().doubleValue();
								sumAllTotalStudy = sumAllTotalStudy
										+ helVo.getStudy().doubleValue();

							}
							if (helVo.getNetDec() != null) {
								sumTotalNetDec = sumTotalNetDec
										+ helVo.getNetDec().doubleValue();
								sumAllTotalNetDec = sumAllTotalNetDec
										+ helVo.getNetDec().doubleValue();

							}
							
							if (helVo.getOomSin() != null) {
								sumTotalOomsin = sumTotalOomsin
										+ helVo.getOomSin().doubleValue();
								sumAllTotalOomsin = sumAllTotalOomsin
										+ helVo.getOomSin().doubleValue();

							}
							
							if (helVo.getLoanPost() != null) {
								sumTotalPangLoan = sumTotalPangLoan
										+ helVo.getLoanPost().doubleValue();
								sumAllTotalPangLoan = sumAllTotalPangLoan
										+ helVo.getLoanPost().doubleValue();

							}
							
							
							if (helVo.getLawLoan() != null) {
								sumTotalLawLoan = sumTotalLawLoan
										+ helVo.getLawLoan().doubleValue();
								sumAllTotalLawLoan = sumAllTotalLawLoan
										+ helVo.getLawLoan().doubleValue();

							}
							
							if (helVo.getSsr() != null) {
								sumTotalSsr = sumTotalSsr
										+ helVo.getSsr().doubleValue();
								sumAllTotalSsr = sumAllTotalSsr
										+ helVo.getSsr().doubleValue();

							}
							
							if (helVo.getKys() != null) {
								sumTotalKys = sumTotalKys
										+ helVo.getKys().doubleValue();
								sumAllTotalKys = sumAllTotalKys
										+ helVo.getKys().doubleValue();

							}
							
							if (helVo.getKro() != null) {
								sumTotalKro = sumTotalKro
										+ helVo.getKro().doubleValue();
								sumAllTotalKro = sumAllTotalKro
										+ helVo.getKro().doubleValue();

							}
							
							
							if (helVo.getNet() != null) {
								sumTotalNet = sumTotalNet
										+ helVo.getNet().doubleValue();
								sumAllTotalNet = sumAllTotalNet
										+ helVo.getNet().doubleValue();

							}
							
							sheet.setRowView(startRow, 320);
							startRow++;
							if (helRpt.size() == i + 1) {
								sheet.mergeCells(0, startRow, 2, startRow);
								sheet.addCell(new Label(0, startRow, "���", borderNumber2));
								sheet.addCell(new Label(3, startRow, String.valueOf(df.format(sumTotalNewSalary)),borderNumber2));
								sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalSalary)),borderNumber2));
								sheet.addCell(new Label(5, startRow, String.valueOf(df.format(sumTotalSalary02)),borderNumber2));
								sheet.addCell(new Label(6, startRow, String.valueOf(df.format(sumTotalSalary03)),borderNumber2));
								sheet.addCell(new Label(7, startRow, String.valueOf(df.format(sumTotalKlongchev)),borderNumber2));
								sheet.addCell(new Label(8, startRow, String.valueOf(df.format(sumTotalChilds)),borderNumber2));
								sheet.addCell(new Label(9, startRow, String.valueOf(df.format(sumTotalNetIncome)),borderNumber2));
								sheet.addCell(new Label(10, startRow, String.valueOf(df.format(sumTotalVinai2)),borderNumber2));
								sheet.addCell(new Label(11, startRow, String.valueOf(df.format(sumTotalVinai1Pct)),borderNumber2));
								sheet.addCell(new Label(12, startRow, String.valueOf(df.format(sumTotalVinai1Lev)),borderNumber2));
								
								sheet.addCell(new Label(13, startRow, String.valueOf(df.format(sumTotalRefund)),borderNumber2));
								sheet.addCell(new Label(14, startRow, String.valueOf(df.format(sumTotalMotor)),borderNumber2));
								sheet.addCell(new Label(15, startRow, String.valueOf(df.format(sumTotalRick)),borderNumber2));
								sheet.addCell(new Label(16, startRow, String.valueOf(df.format(sumTotalStudy)),borderNumber2));
								
								sheet.addCell(new Label(17, startRow, String.valueOf(df.format(sumTotalLawLoan)),borderNumber2));
								
								sheet.addCell(new Label(18, startRow, String.valueOf(df.format(sumTotalPangLoan)),borderNumber2));
								sheet.addCell(new Label(19, startRow, String.valueOf(df.format(sumTotalSsr)),borderNumber2));
								sheet.addCell(new Label(20, startRow, String.valueOf(df.format(sumTotalKys)),borderNumber2));
								sheet.addCell(new Label(21, startRow, String.valueOf(df.format(sumTotalKro)),borderNumber2));
								
								sheet.addCell(new Label(22, startRow, String.valueOf(df.format(sumTotalNetDec)),borderNumber2));
								sheet.addCell(new Label(23, startRow, String.valueOf(df.format(sumTotalNet)),borderNumber2));
								
								sumTotalNewSalary = 0.0;
								sumTotalSalary02 = 0.0;
								sumTotalSalary03 = 0.0;
								sumTotalSalary = 0.0;
								sumTotalChilds = 0.0;
								sumTotalKlongchev = 0.0;
								
								sumTotalNetIncome = 0.0;
								sumTotalVinai1Pct = 0.0;
								sumTotalVinai1Lev = 0.0;
								sumTotalVinai2 = 0.0;
								sumTotalRefund = 0.0;
								sumTotalCar = 0.0;
								sumTotalMotor = 0.0;
								sumTotalRick = 0.0;
								sumTotalStudy = 0.0;
								sumTotalNetDec = 0.0;
								sumTotalNet = 0.0;
								sumTotalOomsin = 0.0;
								sumTotalPangLoan = 0.0;
								sumTotalLawLoan = 0.0;
								sumTotalSsr = 0.0;
								sumTotalKys = 0.0;
								sumTotalKro = 0.0;
								
								startRow++;
							}
						}
						tmpCode = helVo.getAreaCode();
					} else if (checkLV == 4) {
						if (!tmpCode.equals(helVo.getSecCode())) {
							if (!flagTotalHour.equals("")) {
								sheet.mergeCells(0, startRow, 2, startRow);
								sheet.addCell(new Label(0, startRow, "���", borderNumber2));
								sheet.addCell(new Label(3, startRow, String.valueOf(df.format(sumTotalNewSalary)),borderNumber2));
								sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalSalary)),borderNumber2));
								sheet.addCell(new Label(5, startRow, String.valueOf(df.format(sumTotalSalary02)),borderNumber2));
								sheet.addCell(new Label(6, startRow, String.valueOf(df.format(sumTotalSalary03)),borderNumber2));
								sheet.addCell(new Label(7, startRow, String.valueOf(df.format(sumTotalKlongchev)),borderNumber2));
								sheet.addCell(new Label(8, startRow, String.valueOf(df.format(sumTotalChilds)),borderNumber2));
							
								sheet.addCell(new Label(9, startRow, String.valueOf(df.format(sumTotalNetIncome)),borderNumber2));
								sheet.addCell(new Label(10, startRow, String.valueOf(df.format(sumTotalVinai2)),borderNumber2));
								sheet.addCell(new Label(11, startRow, String.valueOf(df.format(sumTotalVinai1Pct)),borderNumber2));
								sheet.addCell(new Label(12, startRow, String.valueOf(df.format(sumTotalVinai1Lev)),borderNumber2));
								
								sheet.addCell(new Label(13, startRow, String.valueOf(df.format(sumTotalRefund)),borderNumber2));
								sheet.addCell(new Label(14, startRow, String.valueOf(df.format(sumTotalMotor)),borderNumber2));
								sheet.addCell(new Label(15, startRow, String.valueOf(df.format(sumTotalRick)),borderNumber2));
								sheet.addCell(new Label(16, startRow, String.valueOf(df.format(sumTotalStudy)),borderNumber2));
								
								sheet.addCell(new Label(17, startRow, String.valueOf(df.format(sumTotalLawLoan)),borderNumber2));
								
								sheet.addCell(new Label(18, startRow, String.valueOf(df.format(sumTotalPangLoan)),borderNumber2));
								sheet.addCell(new Label(19, startRow, String.valueOf(df.format(sumTotalSsr)),borderNumber2));
								sheet.addCell(new Label(20, startRow, String.valueOf(df.format(sumTotalKys)),borderNumber2));
								sheet.addCell(new Label(21, startRow, String.valueOf(df.format(sumTotalKro)),borderNumber2));
								
								sheet.addCell(new Label(22, startRow, String.valueOf(df.format(sumTotalNetDec)),borderNumber2));
								sheet.addCell(new Label(23, startRow, String.valueOf(df.format(sumTotalNet)),borderNumber2));
								sumTotalNewSalary = 0.0;
								sumTotalSalary02 = 0.0;
								sumTotalSalary03 = 0.0;
								sumTotalSalary = 0.0;
								sumTotalChilds = 0.0;
								sumTotalKlongchev = 0.0;
								
								sumTotalNetIncome = 0.0;
								sumTotalVinai1Pct = 0.0;
								sumTotalVinai1Lev = 0.0;
								sumTotalVinai2 = 0.0;
								sumTotalRefund = 0.0;
								sumTotalCar = 0.0;
								sumTotalMotor = 0.0;
								sumTotalRick = 0.0;
								sumTotalStudy = 0.0;
								sumTotalNetDec = 0.0;
								sumTotalNet = 0.0;
								sumTotalLawLoan= 0.0;
								sumTotalOomsin = 0.0;
								sumTotalPangLoan = 0.0;
								sumTotalSsr = 0.0;
								sumTotalKys = 0.0;
								sumTotalKro = 0.0;
								startRow++;

							}
							sheet.addCell(new Label(0, startRow, helVo
									.getOrgCode()
									+ " "
									+ helVo.getAreaDesc()
									+ " " + helVo.getSecDesc(), group));
							sheet.addCell(new Blank(1, startRow, borderLR));
							sheet.addCell(new Blank(2, startRow, borderLR));
							sheet.addCell(new Blank(3, startRow, borderLR));
							sheet.addCell(new Blank(4, startRow, borderLR));
							sheet.addCell(new Blank(5, startRow, borderLR));
							sheet.addCell(new Blank(6, startRow, borderLR));
							
							sheet.addCell(new Blank(7, startRow,  borderLR));
							sheet.addCell(new Blank(8, startRow,  borderLR));
							sheet.addCell(new Blank(9, startRow,  borderLR));
							sheet.addCell(new Blank(10, startRow,  borderLR));
							sheet.addCell(new Blank(11, startRow,  borderLR));
							sheet.addCell(new Blank(12, startRow, borderLR));
							sheet.addCell(new Blank(13, startRow, borderLR));
							sheet.addCell(new Blank(14, startRow,  borderLR));
							sheet.addCell(new Blank(15, startRow,  borderLR));
							sheet.addCell(new Blank(16, startRow,  borderLR));
							sheet.addCell(new Blank(17, startRow,  borderLR));
							sheet.addCell(new Blank(18, startRow,  borderLR));
							sheet.addCell(new Blank(19, startRow,  borderLR));
							sheet.addCell(new Blank(20, startRow,  borderLR));
							sheet.addCell(new Blank(21, startRow,  borderLR));
							sheet.addCell(new Blank(22, startRow,  borderLR));
							sheet.addCell(new Blank(23, startRow,  borderLR));
							
							sheet.setRowView(startRow, 320);
							startRow++;
							seq = seq + 1;
							sheet.addCell(new Label(0, startRow, String
									.valueOf(seq), borderNumber));
							sheet.addCell(new Label(1, startRow, helVo
									.getEmpCode(), boldCenterFormat));
							sheet.addCell(new Label(2, startRow, helVo.getFullName(), borderLR));
							sheet.addCell(new Label(3, startRow, String.valueOf(df.format(helVo.getNewSalary())),borderNumber));
							sheet.addCell(new Label(4, startRow, String.valueOf(df.format(helVo.getSalary())),borderNumber));
							sheet.addCell(new Label(5, startRow, String.valueOf(df.format(helVo.getSalary02())),borderNumber));
							sheet.addCell(new Label(6, startRow, String.valueOf(df.format(helVo.getSalary03())),borderNumber));
							sheet.addCell(new Label(7, startRow, String.valueOf(df.format(helVo.getKlongchev())),borderNumber));
							sheet.addCell(new Label(8, startRow, String.valueOf(df.format(helVo.getChilds())),borderNumber));
						
							sheet.addCell(new Label(9, startRow, String.valueOf(df.format(helVo.getNetIncome())),borderNumber));
							sheet.addCell(new Label(10, startRow, String.valueOf(df.format(helVo.getVinai2())),borderNumber));
							sheet.addCell(new Label(11, startRow, String.valueOf(df.format(helVo.getVinai1Pct())),borderNumber));
							sheet.addCell(new Label(12, startRow, String.valueOf(df.format(helVo.getVinai1Lev())),borderNumber));
						
							sheet.addCell(new Label(13, startRow, String.valueOf(df.format(helVo.getRefund())),borderNumber));
							sheet.addCell(new Label(14, startRow, String.valueOf(df.format(helVo.getMotor())),borderNumber));
							sheet.addCell(new Label(15, startRow, String.valueOf(df.format(helVo.getRick())),borderNumber));
							sheet.addCell(new Label(16, startRow, String.valueOf(df.format(helVo.getStudy())),borderNumber));
							
							sheet.addCell(new Label(17, startRow, String.valueOf(df.format(helVo.getLawLoan())),borderNumber));
							
							sheet.addCell(new Label(18, startRow, String.valueOf(df.format(helVo.getLoanPost())),borderNumber));
							sheet.addCell(new Label(19, startRow, String.valueOf(df.format(helVo.getSsr())),borderNumber));
							sheet.addCell(new Label(20, startRow, String.valueOf(df.format(helVo.getKys())),borderNumber));
							sheet.addCell(new Label(21, startRow, String.valueOf(df.format(helVo.getKro())),borderNumber));
							
							sheet.addCell(new Label(22, startRow, String.valueOf(df.format(helVo.getNetDec())),borderNumber));
							sheet.addCell(new Label(23, startRow, String.valueOf(df.format(helVo.getNet())),borderNumber));
							
							flagTotalHour = "sum";
							
							if (helVo.getNewSalary() != null) {
								sumTotalNewSalary = sumTotalNewSalary
										+ helVo.getNewSalary().doubleValue();
								sumAllTotalNewSalary = sumAllTotalNewSalary
										+ helVo.getNewSalary().doubleValue();
							}
							if (helVo.getSalary() != null) {
								sumTotalSalary = sumTotalSalary
										+ helVo.getSalary().doubleValue();
								sumAllTotalSalary = sumAllTotalSalary
										+ helVo.getSalary().doubleValue();
							}
							
							if (helVo.getChilds() != null) {
								sumTotalChilds = sumTotalChilds
										+ helVo.getChilds().doubleValue();
								sumAllTotalChilds = sumAllTotalChilds
										+ helVo.getChilds().doubleValue();

							}
							if (helVo.getKlongchev() != null) {
								sumTotalKlongchev = sumTotalKlongchev
										+ helVo.getKlongchev().doubleValue();
								sumAllTotalKlongchev = sumAllTotalKlongchev
										+ helVo.getKlongchev().doubleValue();

							}
							
							
							
							
							if (helVo.getSalary02() != null) {
								sumTotalSalary02 = sumTotalSalary02
										+ helVo.getSalary02().doubleValue();
								sumAllTotalSalary02 = sumAllTotalSalary02
										+ helVo.getSalary02().doubleValue();

							}
							if (helVo.getSalary03() != null) {
								sumTotalSalary03 = sumTotalSalary03
										+ helVo.getSalary03().doubleValue();
								sumAllTotalSalary03 = sumAllTotalSalary03
										+ helVo.getSalary03().doubleValue();

							}
							if (helVo.getNetIncome() != null) {
								sumTotalNetIncome = sumTotalNetIncome
										+ helVo.getNetIncome().doubleValue();
								sumAllTotalNetIncome = sumAllTotalNetIncome
										+ helVo.getNetIncome().doubleValue();

							}
							if (helVo.getVinai1Pct() != null) {
								sumTotalVinai1Pct = sumTotalVinai1Pct
										+ helVo.getVinai1Pct().doubleValue();
								sumAllTotalVinai1Pct = sumAllTotalVinai1Pct
										+ helVo.getVinai1Pct().doubleValue();

							}
							
							if (helVo.getVinai1Lev() != null) {
								sumTotalVinai1Lev = sumTotalVinai1Lev
										+ helVo.getVinai1Lev().doubleValue();
								sumAllTotalVinai1Lev = sumAllTotalVinai1Lev
										+ helVo.getVinai1Lev().doubleValue();

							}
							
							if (helVo.getVinai2() != null) {
								sumTotalVinai2 = sumTotalVinai2
										+ helVo.getVinai2().doubleValue();
								sumAllTotalVinai2 = sumAllTotalVinai2
										+ helVo.getVinai2().doubleValue();

							}
							if (helVo.getCar() != null) {
								sumTotalCar = sumTotalCar
										+ helVo.getCar().doubleValue();
								sumAllTotalCar = sumAllTotalCar
										+ helVo.getCar().doubleValue();

							}
							
							if (helVo.getRefund() != null) {
								sumTotalRefund = sumTotalRefund
										+ helVo.getRefund().doubleValue();
								sumAllTotalRefund = sumAllTotalRefund
										+ helVo.getRefund().doubleValue();

							}
							
							
							if (helVo.getMotor() != null) {
								sumTotalMotor = sumTotalMotor
										+ helVo.getMotor().doubleValue();
								sumAllTotalMotor = sumAllTotalMotor
										+ helVo.getMotor().doubleValue();

							}
							
							if (helVo.getOomSin() != null) {
								sumTotalOomsin = sumTotalOomsin
										+ helVo.getOomSin().doubleValue();
								sumAllTotalOomsin = sumAllTotalOomsin
										+ helVo.getOomSin().doubleValue();

							}
							
							if (helVo.getLoanPost() != null) {
								sumTotalPangLoan = sumTotalPangLoan
										+ helVo.getLoanPost().doubleValue();
								sumAllTotalPangLoan = sumAllTotalPangLoan
										+ helVo.getLoanPost().doubleValue();

							}
							
							
							if (helVo.getLawLoan() != null) {
								sumTotalLawLoan = sumTotalLawLoan
										+ helVo.getLawLoan().doubleValue();
								sumAllTotalLawLoan = sumAllTotalLawLoan
										+ helVo.getLawLoan().doubleValue();

							}
							
							if (helVo.getRick() != null) {
								sumTotalRick = sumTotalRick
										+ helVo.getRick().doubleValue();
								sumAllTotalRick = sumAllTotalRick
										+ helVo.getRick().doubleValue();

							}
							if (helVo.getStudy() != null) {
								sumTotalStudy = sumTotalStudy
										+ helVo.getStudy().doubleValue();
								sumAllTotalStudy = sumAllTotalStudy
										+ helVo.getStudy().doubleValue();

							}
							
							if (helVo.getSsr() != null) {
								sumTotalSsr = sumTotalSsr
										+ helVo.getSsr().doubleValue();
								sumAllTotalSsr = sumAllTotalSsr
										+ helVo.getSsr().doubleValue();

							}
							
							if (helVo.getKys() != null) {
								sumTotalKys = sumTotalKys
										+ helVo.getKys().doubleValue();
								sumAllTotalKys = sumAllTotalKys
										+ helVo.getKys().doubleValue();

							}
							
							if (helVo.getKro() != null) {
								sumTotalKro = sumTotalKro
										+ helVo.getKro().doubleValue();
								sumAllTotalKro = sumAllTotalKro
										+ helVo.getKro().doubleValue();

							}
							if (helVo.getNetDec() != null) {
								sumTotalNetDec = sumTotalNetDec
										+ helVo.getNetDec().doubleValue();
								sumAllTotalNetDec = sumAllTotalNetDec
										+ helVo.getNetDec().doubleValue();

							}
							
							if (helVo.getNet() != null) {
								sumTotalNet = sumTotalNet + helVo.getNet().doubleValue();
								sumAllTotalNet = sumAllTotalNet	+ helVo.getNet().doubleValue();

							}
							
							startRow++;
							if (helRpt.size() == i + 1) {
								sheet.mergeCells(0, startRow, 2, startRow);
								sheet.addCell(new Label(0, startRow, "���", borderNumber2));
								sheet.addCell(new Label(3, startRow, String.valueOf(df.format(sumTotalNewSalary)),borderNumber2));
								sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalSalary)),borderNumber2));
								sheet.addCell(new Label(5, startRow, String.valueOf(df.format(sumTotalSalary02)),borderNumber2));
								sheet.addCell(new Label(6, startRow, String.valueOf(df.format(sumTotalSalary03)),borderNumber2));
								sheet.addCell(new Label(7, startRow, String.valueOf(df.format(sumTotalKlongchev)),borderNumber2));
								sheet.addCell(new Label(8, startRow, String.valueOf(df.format(sumTotalChilds)),borderNumber2));
								sheet.addCell(new Label(9, startRow, String.valueOf(df.format(sumTotalNetIncome)),borderNumber2));
								sheet.addCell(new Label(10, startRow, String.valueOf(df.format(sumTotalVinai2)),borderNumber2));
								sheet.addCell(new Label(11, startRow, String.valueOf(df.format(sumTotalVinai1Pct)),borderNumber2));
								sheet.addCell(new Label(12, startRow, String.valueOf(df.format(sumTotalVinai1Lev)),borderNumber2));
								
								sheet.addCell(new Label(13, startRow, String.valueOf(df.format(sumTotalRefund)),borderNumber2));
								sheet.addCell(new Label(14, startRow, String.valueOf(df.format(sumTotalMotor)),borderNumber2));
								sheet.addCell(new Label(15, startRow, String.valueOf(df.format(sumTotalRick)),borderNumber2));
								sheet.addCell(new Label(16, startRow, String.valueOf(df.format(sumTotalStudy)),borderNumber2));
								
								sheet.addCell(new Label(17, startRow, String.valueOf(df.format(sumTotalLawLoan)),borderNumber2));
								
								sheet.addCell(new Label(18, startRow, String.valueOf(df.format(sumTotalPangLoan)),borderNumber2));
								sheet.addCell(new Label(19, startRow, String.valueOf(df.format(sumTotalSsr)),borderNumber2));
								sheet.addCell(new Label(20, startRow, String.valueOf(df.format(sumTotalKys)),borderNumber2));
								sheet.addCell(new Label(21, startRow, String.valueOf(df.format(sumTotalKro)),borderNumber2));
								
								sheet.addCell(new Label(22, startRow, String.valueOf(df.format(sumTotalNetDec)),borderNumber2));
								sheet.addCell(new Label(23, startRow, String.valueOf(df.format(sumTotalNet)),borderNumber2));
								
								sumTotalNewSalary = 0.0;
								sumTotalSalary02 = 0.0;
								sumTotalSalary03 = 0.0;
								sumTotalSalary = 0.0;
								sumTotalChilds = 0.0;
								sumTotalKlongchev = 0.0;
								sumTotalNetIncome = 0.0;
								sumTotalVinai1Pct = 0.0;
								sumTotalVinai1Lev = 0.0;
								sumTotalVinai2 = 0.0;
								sumTotalRefund = 0.0;
								sumTotalCar = 0.0;
								sumTotalMotor = 0.0;
								sumTotalRick = 0.0;
								sumTotalStudy = 0.0;
								sumTotalNetDec = 0.0;
								sumTotalNet = 0.0;
								sumTotalOomsin = 0.0;
								sumTotalSsr = 0.0;
								sumTotalKys = 0.0;
								sumTotalKro = 0.0;
								sumTotalLawLoan = 0.0;
								sumTotalPangLoan = 0.0;
								startRow++;
							}

							sheet.setRowView(startRow, 320);
						} else {
							seq = seq + 1;
							sheet.addCell(new Label(0, startRow, String
									.valueOf(seq), borderNumber));
							sheet.addCell(new Label(1, startRow, helVo
									.getEmpCode(), boldCenterFormat));
							sheet.addCell(new Label(2, startRow, helVo.getFullName(), borderLR));
							sheet.addCell(new Label(3, startRow, String.valueOf(df.format(helVo.getNewSalary())),borderNumber));
							sheet.addCell(new Label(4, startRow, String.valueOf(df.format(helVo.getSalary())),borderNumber));
							sheet.addCell(new Label(5, startRow, String.valueOf(df.format(helVo.getSalary02())),borderNumber));
							sheet.addCell(new Label(6, startRow, String.valueOf(df.format(helVo.getSalary03())),borderNumber));
							sheet.addCell(new Label(7, startRow, String.valueOf(df.format(helVo.getKlongchev())),borderNumber));
							sheet.addCell(new Label(8, startRow, String.valueOf(df.format(helVo.getChilds())),borderNumber));
							
							sheet.addCell(new Label(9, startRow, String.valueOf(df.format(helVo.getNetIncome())),borderNumber));
							sheet.addCell(new Label(10, startRow, String.valueOf(df.format(helVo.getVinai2())),borderNumber));
							sheet.addCell(new Label(11, startRow, String.valueOf(df.format(helVo.getVinai1Pct())),borderNumber));
							sheet.addCell(new Label(12, startRow, String.valueOf(df.format(helVo.getVinai1Lev())),borderNumber));
							
							sheet.addCell(new Label(13, startRow, String.valueOf(df.format(helVo.getRefund())),borderNumber));
							sheet.addCell(new Label(14, startRow, String.valueOf(df.format(helVo.getMotor())),borderNumber));
							sheet.addCell(new Label(15, startRow, String.valueOf(df.format(helVo.getRick())),borderNumber));
							sheet.addCell(new Label(16, startRow, String.valueOf(df.format(helVo.getStudy())),borderNumber));
							
							sheet.addCell(new Label(17, startRow, String.valueOf(df.format(helVo.getLawLoan())),borderNumber));
							
							sheet.addCell(new Label(18, startRow, String.valueOf(df.format(helVo.getLoanPost())),borderNumber));
							sheet.addCell(new Label(19, startRow, String.valueOf(df.format(helVo.getSsr())),borderNumber));
							sheet.addCell(new Label(20, startRow, String.valueOf(df.format(helVo.getKys())),borderNumber));
							sheet.addCell(new Label(21, startRow, String.valueOf(df.format(helVo.getKro())),borderNumber));
							
							sheet.addCell(new Label(22, startRow, String.valueOf(df.format(helVo.getNetDec())),borderNumber));
							sheet.addCell(new Label(23, startRow, String.valueOf(df.format(helVo.getNet())),borderNumber));
							
							flagTotalHour = "sum";
							
							
							if (helVo.getNewSalary() != null) {
								sumTotalNewSalary = sumTotalNewSalary
										+ helVo.getNewSalary().doubleValue();
								sumAllTotalNewSalary = sumAllTotalNewSalary
										+ helVo.getNewSalary().doubleValue();

							}
							if (helVo.getSalary() != null) {
								sumTotalSalary = sumTotalSalary
										+ helVo.getSalary().doubleValue();
								sumAllTotalSalary = sumAllTotalSalary
										+ helVo.getSalary().doubleValue();

							}
							if (helVo.getChilds() != null) {
								sumTotalChilds = sumTotalChilds
										+ helVo.getChilds().doubleValue();
								sumAllTotalChilds = sumAllTotalChilds
										+ helVo.getChilds().doubleValue();

							}
							if (helVo.getKlongchev() != null) {
								sumTotalKlongchev = sumTotalKlongchev
										+ helVo.getKlongchev().doubleValue();
								sumAllTotalKlongchev = sumAllTotalKlongchev
										+ helVo.getKlongchev().doubleValue();

							}
							
							
							if (helVo.getSalary02() != null) {
								sumTotalSalary02 = sumTotalSalary02
										+ helVo.getSalary02().doubleValue();
								sumAllTotalSalary02 = sumAllTotalSalary02
										+ helVo.getSalary02().doubleValue();

							}
							if (helVo.getSalary03() != null) {
								sumTotalSalary03 = sumTotalSalary03
										+ helVo.getSalary03().doubleValue();
								sumAllTotalSalary03 = sumAllTotalSalary03
										+ helVo.getSalary03().doubleValue();

							}
							if (helVo.getNetIncome() != null) {
								sumTotalNetIncome = sumTotalNetIncome
										+ helVo.getNetIncome().doubleValue();
								sumAllTotalNetIncome = sumAllTotalNetIncome
										+ helVo.getNetIncome().doubleValue();

							}
							if (helVo.getVinai1Pct() != null) {
								sumTotalVinai1Pct = sumTotalVinai1Pct
										+ helVo.getVinai1Pct().doubleValue();
								sumAllTotalVinai1Pct = sumAllTotalVinai1Pct
										+ helVo.getVinai1Pct().doubleValue();

							}
							
							if (helVo.getVinai1Lev() != null) {
								sumTotalVinai1Lev = sumTotalVinai1Lev
										+ helVo.getVinai1Lev().doubleValue();
								sumAllTotalVinai1Lev = sumAllTotalVinai1Lev
										+ helVo.getVinai1Lev().doubleValue();

							}
							
							if (helVo.getVinai2() != null) {
								sumTotalVinai2 = sumTotalVinai2
										+ helVo.getVinai2().doubleValue();
								sumAllTotalVinai2 = sumAllTotalVinai2
										+ helVo.getVinai2().doubleValue();

							}
							if (helVo.getCar() != null) {
								sumTotalCar = sumTotalCar
										+ helVo.getCar().doubleValue();
								sumAllTotalCar = sumAllTotalCar
										+ helVo.getCar().doubleValue();

							}
							
							if (helVo.getRefund() != null) {
								sumTotalRefund = sumTotalRefund
										+ helVo.getRefund().doubleValue();
								sumAllTotalRefund = sumAllTotalRefund
										+ helVo.getRefund().doubleValue();

							}
							
							if (helVo.getMotor() != null) {
								sumTotalMotor = sumTotalMotor
										+ helVo.getMotor().doubleValue();
								sumAllTotalMotor = sumAllTotalMotor
										+ helVo.getMotor().doubleValue();

							}
							if (helVo.getRick() != null) {
								sumTotalRick = sumTotalRick
										+ helVo.getRick().doubleValue();
								sumAllTotalRick = sumAllTotalRick
										+ helVo.getRick().doubleValue();

							}
							
							if (helVo.getOomSin() != null) {
								sumTotalOomsin = sumTotalOomsin
										+ helVo.getOomSin().doubleValue();
								sumAllTotalOomsin = sumAllTotalOomsin
										+ helVo.getOomSin().doubleValue();

							}
							
							if (helVo.getLoanPost() != null) {
								sumTotalPangLoan = sumTotalPangLoan
										+ helVo.getLoanPost().doubleValue();
								sumAllTotalPangLoan = sumAllTotalPangLoan
										+ helVo.getLoanPost().doubleValue();

							}
							
							
							if (helVo.getLawLoan() != null) {
								sumTotalLawLoan = sumTotalLawLoan
										+ helVo.getLawLoan().doubleValue();
								sumAllTotalLawLoan = sumAllTotalLawLoan
										+ helVo.getLawLoan().doubleValue();

							}
							
							if (helVo.getStudy() != null) {
								sumTotalStudy = sumTotalStudy
										+ helVo.getStudy().doubleValue();
								sumAllTotalStudy = sumAllTotalStudy
										+ helVo.getStudy().doubleValue();

							}
							
							
							if (helVo.getSsr() != null) {
								sumTotalSsr = sumTotalSsr
										+ helVo.getSsr().doubleValue();
								sumAllTotalSsr = sumAllTotalSsr
										+ helVo.getSsr().doubleValue();

							}
							
							if (helVo.getKys() != null) {
								sumTotalKys = sumTotalKys
										+ helVo.getKys().doubleValue();
								sumAllTotalKys = sumAllTotalKys
										+ helVo.getKys().doubleValue();

							}
							if (helVo.getKro() != null) {
								sumTotalKro = sumTotalKro
										+ helVo.getKro().doubleValue();
								sumAllTotalKro = sumAllTotalKro
										+ helVo.getKro().doubleValue();

							}
							
							if (helVo.getNetDec() != null) {
								sumTotalNetDec = sumTotalNetDec
										+ helVo.getNetDec().doubleValue();
								sumAllTotalNetDec = sumAllTotalNetDec
										+ helVo.getNetDec().doubleValue();

							}
							
							if (helVo.getNet() != null) {
								sumTotalNet = sumTotalNet + helVo.getNet().doubleValue();
								sumAllTotalNet = sumAllTotalNet + helVo.getNet().doubleValue();

							}
							
							sheet.setRowView(startRow, 320);
							startRow++;
							if (helRpt.size() == i + 1) {
								sheet.mergeCells(0, startRow, 2, startRow);
								sheet.addCell(new Label(0, startRow, "���", borderNumber2));
								sheet.addCell(new Label(3, startRow, String.valueOf(df.format(sumTotalNewSalary)),borderNumber2));
								sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalSalary)),borderNumber2));
								sheet.addCell(new Label(5, startRow, String.valueOf(df.format(sumTotalSalary02)),borderNumber2));
								sheet.addCell(new Label(6, startRow, String.valueOf(df.format(sumTotalSalary03)),borderNumber2));
								sheet.addCell(new Label(7, startRow, String.valueOf(df.format(sumTotalKlongchev)),borderNumber2));
								sheet.addCell(new Label(8, startRow, String.valueOf(df.format(sumTotalChilds)),borderNumber2));
							
								sheet.addCell(new Label(9, startRow, String.valueOf(df.format(sumTotalNetIncome)),borderNumber2));
								sheet.addCell(new Label(10, startRow, String.valueOf(df.format(sumTotalVinai2)),borderNumber2));
								sheet.addCell(new Label(11, startRow, String.valueOf(df.format(sumTotalVinai1Pct)),borderNumber2));
								sheet.addCell(new Label(12, startRow, String.valueOf(df.format(sumTotalVinai1Lev)),borderNumber2));
								
								sheet.addCell(new Label(13, startRow, String.valueOf(df.format(sumTotalRefund)),borderNumber2));
								sheet.addCell(new Label(14, startRow, String.valueOf(df.format(sumTotalMotor)),borderNumber2));
								sheet.addCell(new Label(15, startRow, String.valueOf(df.format(sumTotalRick)),borderNumber2));
								sheet.addCell(new Label(16, startRow, String.valueOf(df.format(sumTotalStudy)),borderNumber2));
								
								sheet.addCell(new Label(17, startRow, String.valueOf(df.format(sumTotalLawLoan)),borderNumber2));
							
								sheet.addCell(new Label(18, startRow, String.valueOf(df.format(sumTotalPangLoan)),borderNumber2));
								sheet.addCell(new Label(19, startRow, String.valueOf(df.format(sumTotalSsr)),borderNumber2));
								sheet.addCell(new Label(20, startRow, String.valueOf(df.format(sumTotalKys)),borderNumber2));
								sheet.addCell(new Label(21, startRow, String.valueOf(df.format(sumTotalKro)),borderNumber2));
								
								sheet.addCell(new Label(22, startRow, String.valueOf(df.format(sumTotalNetDec)),borderNumber2));
								sheet.addCell(new Label(23, startRow, String.valueOf(df.format(sumTotalNet)),borderNumber2));
								sumTotalNewSalary = 0.0;
								sumTotalSalary02 = 0.0;
								sumTotalSalary03 = 0.0;
								sumTotalSalary = 0.0;
								sumTotalChilds = 0.0;
								sumTotalKlongchev = 0.0;
								sumTotalNetIncome = 0.0;
								sumTotalVinai1Pct = 0.0;
								sumTotalVinai1Lev = 0.0;
								sumTotalVinai2 = 0.0;
								sumTotalRefund = 0.0;
								sumTotalCar = 0.0;
								sumTotalMotor = 0.0;
								sumTotalRick = 0.0;
								sumTotalStudy = 0.0;
								sumTotalNetDec = 0.0;
								sumTotalNet = 0.0;
								sumTotalOomsin  =0.0;
								sumTotalSsr = 0.0;
								sumTotalKys = 0.0;
								sumTotalKro = 0.0;
								sumTotalPangLoan = 0.0;
								sumTotalLawLoan = 0.0;
								startRow++;
							}
						}
						tmpCode = helVo.getSecCode();
					} else if (checkLV == 0) { // area code == null
						seq = seq + 1;
						sheet.addCell(new Label(0, startRow, String
								.valueOf(seq), borderNumber));
						sheet.addCell(new Label(1, startRow,
								helVo.getEmpCode(), boldCenterFormat));
						sheet.addCell(new Label(2, startRow, helVo.getFullName(), borderLR));
						sheet.addCell(new Label(3, startRow, String.valueOf(df.format(helVo.getNewSalary())),borderNumber));
						sheet.addCell(new Label(4, startRow, String.valueOf(df.format(helVo.getSalary())),borderNumber));
						sheet.addCell(new Label(5, startRow, String.valueOf(df.format(helVo.getSalary02())),borderNumber));
						sheet.addCell(new Label(6, startRow, String.valueOf(df.format(helVo.getSalary03())),borderNumber));
						sheet.addCell(new Label(7, startRow, String.valueOf(df.format(helVo.getKlongchev())),borderNumber));
						sheet.addCell(new Label(8, startRow, String.valueOf(df.format(helVo.getChilds())),borderNumber));
					
						sheet.addCell(new Label(9, startRow, String.valueOf(df.format(helVo.getNetIncome())),borderNumber));
						sheet.addCell(new Label(10, startRow, String.valueOf(df.format(helVo.getVinai2())),borderNumber));
						sheet.addCell(new Label(11, startRow, String.valueOf(df.format(helVo.getVinai1Pct())),borderNumber));
						sheet.addCell(new Label(12, startRow, String.valueOf(df.format(helVo.getVinai1Lev())),borderNumber));
						
						sheet.addCell(new Label(13, startRow, String.valueOf(df.format(helVo.getRefund())),borderNumber));
						sheet.addCell(new Label(14, startRow, String.valueOf(df.format(helVo.getMotor())),borderNumber));
						sheet.addCell(new Label(15, startRow, String.valueOf(df.format(helVo.getRick())),borderNumber));
						sheet.addCell(new Label(16, startRow, String.valueOf(df.format(helVo.getStudy())),borderNumber));
						
						sheet.addCell(new Label(17, startRow, String.valueOf(df.format(helVo.getLawLoan())),borderNumber));
						
						sheet.addCell(new Label(18, startRow, String.valueOf(df.format(helVo.getLoanPost())),borderNumber));
						
						sheet.addCell(new Label(19, startRow, String.valueOf(df.format(helVo.getSsr())),borderNumber));
						sheet.addCell(new Label(20, startRow, String.valueOf(df.format(helVo.getKys())),borderNumber));
						sheet.addCell(new Label(21, startRow, String.valueOf(df.format(helVo.getKro())),borderNumber));
						
						sheet.addCell(new Label(20, startRow, String.valueOf(df.format(helVo.getNetDec())),borderNumber));
						sheet.addCell(new Label(21, startRow, String.valueOf(df.format(helVo.getNet())),borderNumber));
						
						
						
						flagTotalHour = "sum";
						
						
						if (helVo.getNewSalary() != null) {
							sumTotalNewSalary = sumTotalNewSalary
									+ helVo.getNewSalary().doubleValue();
							sumAllTotalNewSalary = sumAllTotalNewSalary
									+ helVo.getNewSalary().doubleValue();
						}
						if (helVo.getSalary() != null) {
							sumTotalSalary = sumTotalSalary
									+ helVo.getSalary().doubleValue();
							sumAllTotalSalary = sumAllTotalSalary
									+ helVo.getSalary().doubleValue();
						}
						if (helVo.getChilds() != null) {
							sumTotalChilds = sumTotalChilds
									+ helVo.getChilds().doubleValue();
							sumAllTotalChilds = sumAllTotalChilds
									+ helVo.getChilds().doubleValue();

						}
						if (helVo.getKlongchev() != null) {
							sumTotalKlongchev = sumTotalKlongchev
									+ helVo.getKlongchev().doubleValue();
							sumAllTotalKlongchev = sumAllTotalKlongchev
									+ helVo.getKlongchev().doubleValue();

						}
						
						
						if (helVo.getSalary02() != null) {
							sumTotalSalary02 = sumTotalSalary02
									+ helVo.getSalary02().doubleValue();
							sumAllTotalSalary02 = sumAllTotalSalary02
									+ helVo.getSalary02().doubleValue();

						}
						
						
						if (helVo.getSalary03() != null) {
							sumTotalSalary03 = sumTotalSalary03
									+ helVo.getSalary03().doubleValue();
							sumAllTotalSalary03 = sumAllTotalSalary03
									+ helVo.getSalary03().doubleValue();

						}
						if (helVo.getNetIncome() != null) {
							sumTotalNetIncome = sumTotalNetIncome
									+ helVo.getNetIncome().doubleValue();
							sumAllTotalNetIncome = sumAllTotalNetIncome
									+ helVo.getNetIncome().doubleValue();

						}
						if (helVo.getVinai1Pct() != null) {
							sumTotalVinai1Pct = sumTotalVinai1Pct
									+ helVo.getVinai1Pct().doubleValue();
							sumAllTotalVinai1Pct = sumAllTotalVinai1Pct
									+ helVo.getVinai1Pct().doubleValue();

						}
						
						if (helVo.getVinai1Lev() != null) {
							sumTotalVinai1Lev = sumTotalVinai1Lev
									+ helVo.getVinai1Lev().doubleValue();
							sumAllTotalVinai1Lev = sumAllTotalVinai1Lev
									+ helVo.getVinai1Lev().doubleValue();

						}
						
						if (helVo.getVinai2() != null) {
							sumTotalVinai2 = sumTotalVinai2
									+ helVo.getVinai2().doubleValue();
							sumAllTotalVinai2 = sumAllTotalVinai2
									+ helVo.getVinai2().doubleValue();

						}
						if (helVo.getCar() != null) {
							sumTotalCar = sumTotalCar
									+ helVo.getCar().doubleValue();
							sumAllTotalCar = sumAllTotalCar
									+ helVo.getCar().doubleValue();

						}
						
						if (helVo.getRefund() != null) {
							sumTotalRefund = sumTotalRefund
									+ helVo.getRefund().doubleValue();
							sumAllTotalRefund = sumAllTotalRefund
									+ helVo.getRefund().doubleValue();

						}
						
						
						if (helVo.getMotor() != null) {
							sumTotalMotor = sumTotalMotor
									+ helVo.getMotor().doubleValue();
							sumAllTotalMotor = sumAllTotalMotor
									+ helVo.getMotor().doubleValue();

						}
						if (helVo.getRick() != null) {
							sumTotalRick = sumTotalRick
									+ helVo.getRick().doubleValue();
							sumAllTotalRick = sumAllTotalRick
									+ helVo.getRick().doubleValue();

						}
						if (helVo.getStudy() != null) {
							sumTotalStudy = sumTotalStudy
									+ helVo.getStudy().doubleValue();
							sumAllTotalStudy = sumAllTotalStudy
									+ helVo.getStudy().doubleValue();

						}
						
						if (helVo.getSsr() != null) {
							sumTotalSsr = sumTotalSsr
									+ helVo.getSsr().doubleValue();
							sumAllTotalSsr = sumAllTotalSsr
									+ helVo.getSsr().doubleValue();

						}
						
						
						if (helVo.getKys() != null) {
							sumTotalKys = sumTotalKys
									+ helVo.getKys().doubleValue();
							sumAllTotalKys = sumAllTotalKys
									+ helVo.getKys().doubleValue();

						}
						
						if (helVo.getKro() != null) {
							sumTotalKro = sumTotalKro
									+ helVo.getKro().doubleValue();
							sumAllTotalKro = sumAllTotalKro
									+ helVo.getKro().doubleValue();

						}
						
						if (helVo.getOomSin() != null) {
							sumTotalOomsin = sumTotalOomsin
									+ helVo.getOomSin().doubleValue();
							sumAllTotalOomsin = sumAllTotalOomsin
									+ helVo.getOomSin().doubleValue();

						}
						
						if (helVo.getLoanPost() != null) {
							sumTotalPangLoan = sumTotalPangLoan
									+ helVo.getLoanPost().doubleValue();
							sumAllTotalPangLoan = sumAllTotalPangLoan
									+ helVo.getLoanPost().doubleValue();

						}
						
						
						if (helVo.getLawLoan() != null) {
							sumTotalLawLoan = sumTotalLawLoan
									+ helVo.getLawLoan().doubleValue();
							sumAllTotalLawLoan = sumAllTotalLawLoan
									+ helVo.getLawLoan().doubleValue();

						}
						
						
						if (helVo.getNetDec() != null) {
							sumTotalNetDec = sumTotalNetDec
									+ helVo.getNetDec().doubleValue();
							sumAllTotalNetDec = sumAllTotalNetDec
									+ helVo.getNetDec().doubleValue();

						}
						
						if (helVo.getNet() != null) {
							sumTotalNet = sumTotalNet + helVo.getNet().doubleValue();
							sumAllTotalNet = sumAllTotalNet + helVo.getNet().doubleValue();

						}
						
						sheet.setRowView(startRow, 320);
						startRow++;
						if (helRpt.size() == i + 1) {
							sheet.mergeCells(0, startRow, 2, startRow);
							sheet.addCell(new Label(0, startRow, "���", borderNumber2));
							sheet.addCell(new Label(3, startRow, String.valueOf(df.format(sumTotalNewSalary)),borderNumber2));
							sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalSalary)),borderNumber2));
							sheet.addCell(new Label(5, startRow, String.valueOf(df.format(sumTotalSalary02)),borderNumber2));
							sheet.addCell(new Label(6, startRow, String.valueOf(df.format(sumTotalSalary03)),borderNumber2));
							sheet.addCell(new Label(7, startRow, String.valueOf(df.format(sumTotalKlongchev)),borderNumber2));
							sheet.addCell(new Label(8, startRow, String.valueOf(df.format(sumTotalChilds)),borderNumber2));
							
							sheet.addCell(new Label(9, startRow, String.valueOf(df.format(sumTotalNetIncome)),borderNumber2));
							sheet.addCell(new Label(10, startRow, String.valueOf(df.format(sumTotalVinai2)),borderNumber2));
							sheet.addCell(new Label(11, startRow, String.valueOf(df.format(sumTotalVinai1Pct)),borderNumber2));
							sheet.addCell(new Label(12, startRow, String.valueOf(df.format(sumTotalVinai1Lev)),borderNumber2));
							
							sheet.addCell(new Label(13, startRow, String.valueOf(df.format(sumTotalRefund)),borderNumber2));
							sheet.addCell(new Label(14, startRow, String.valueOf(df.format(sumTotalMotor)),borderNumber2));
							sheet.addCell(new Label(15, startRow, String.valueOf(df.format(sumTotalRick)),borderNumber2));
							sheet.addCell(new Label(16, startRow, String.valueOf(df.format(sumTotalStudy)),borderNumber2));
							
							sheet.addCell(new Label(17, startRow, String.valueOf(df.format(sumTotalLawLoan)),borderNumber2));
							
							sheet.addCell(new Label(18, startRow, String.valueOf(df.format(sumTotalPangLoan)),borderNumber2));
							sheet.addCell(new Label(19, startRow, String.valueOf(df.format(sumTotalSsr)),borderNumber2));
							sheet.addCell(new Label(20, startRow, String.valueOf(df.format(sumTotalKys)),borderNumber2));
							sheet.addCell(new Label(21, startRow, String.valueOf(df.format(sumTotalKro)),borderNumber2));
							sheet.addCell(new Label(22, startRow, String.valueOf(df.format(sumTotalNetDec)),borderNumber2));
							sheet.addCell(new Label(23, startRow, String.valueOf(df.format(sumTotalNet)),borderNumber2));
						
							
							sumTotalNewSalary = 0.0;
							sumTotalSalary02 = 0.0;
							sumTotalSalary03 = 0.0;
							sumTotalSalary = 0.0;
							sumTotalChilds = 0.0;
							sumTotalKlongchev = 0.0;
							sumTotalNetIncome = 0.0;
							sumTotalVinai1Pct = 0.0;
							sumTotalVinai1Lev = 0.0;
							sumTotalVinai2 = 0.0;
							sumTotalRefund = 0.0;
							sumTotalCar = 0.0;
							sumTotalMotor = 0.0;
							sumTotalRick = 0.0;
							sumTotalStudy = 0.0;
							sumTotalNetDec = 0.0;
							sumTotalNet = 0.0;
							sumTotalOomsin = 0.0;
							sumTotalPangLoan = 0.0;
							sumTotalSsr = 0.0;
							sumTotalKys = 0.0;
							sumTotalKro = 0.0;
							sumTotalLawLoan = 0.0;
							startRow++;
						}
						tmpCode = helVo.getAreaCode();
					}
				}

				sheet.mergeCells(0, startRow, 2, startRow);
				sheet.addCell(new Label(0, startRow, "���������", borderNumber2));
				sheet.addCell(new Label(3, startRow,String.valueOf(df.format(new Double(sumAllTotalNewSalary))),borderNumber2));
				sheet.addCell(new Label(4, startRow,String.valueOf(df.format(new Double(sumAllTotalSalary))),borderNumber2));
				sheet.addCell(new Label(5, startRow, String.valueOf(df.format(new Double(sumAllTotalSalary02))),borderNumber2));
				sheet.addCell(new Label(6, startRow, String.valueOf(df.format(new Double(sumAllTotalSalary03))),borderNumber2));
				sheet.addCell(new Label(7, startRow,String.valueOf(df.format(new Double(sumAllTotalKlongchev))),borderNumber2));
				sheet.addCell(new Label(8, startRow,String.valueOf(df.format(new Double(sumAllTotalChilds))),borderNumber2));
				
				sheet.addCell(new Label(9, startRow, String.valueOf(df.format(new Double(sumAllTotalNetIncome))),borderNumber2));
				sheet.addCell(new Label(10, startRow, String.valueOf(df.format(new Double(sumAllTotalVinai2))),borderNumber2));
				sheet.addCell(new Label(11, startRow, String.valueOf(df.format(new Double(sumAllTotalVinai1Pct))),borderNumber2));
				sheet.addCell(new Label(12, startRow, String.valueOf(df.format(new Double(sumAllTotalVinai1Lev))),borderNumber2));
				
				sheet.addCell(new Label(13, startRow, String.valueOf(df.format(new Double(sumAllTotalRefund))),borderNumber2));
				sheet.addCell(new Label(14, startRow, String.valueOf(df.format(new Double(sumAllTotalMotor))),borderNumber2));
				sheet.addCell(new Label(15, startRow, String.valueOf(df.format(new Double(sumAllTotalRick))),borderNumber2));
				sheet.addCell(new Label(16, startRow, String.valueOf(df.format(new Double(sumAllTotalStudy))),borderNumber2));
				
				sheet.addCell(new Label(17, startRow, String.valueOf(df.format(new Double(sumAllTotalLawLoan))),borderNumber2));
				
				sheet.addCell(new Label(18, startRow, String.valueOf(df.format(new Double(sumAllTotalPangLoan))),borderNumber2));
				sheet.addCell(new Label(19, startRow, String.valueOf(df.format(new Double(sumAllTotalSsr))),borderNumber2));
				sheet.addCell(new Label(20, startRow, String.valueOf(df.format(new Double(sumAllTotalKys))),borderNumber2));
				sheet.addCell(new Label(21, startRow, String.valueOf(df.format(new Double(sumAllTotalKro))),borderNumber2));
				
				sheet.addCell(new Label(22, startRow, String.valueOf(df.format(new Double(sumAllTotalNetDec))),borderNumber2));
				sheet.addCell(new Label(23, startRow, String.valueOf(df.format(new Double(sumAllTotalNet))),borderNumber2));
				
				startRow++;
				sheet.addCell(new Label(0, startRow, "", borderEndLine));
				sheet.addCell(new Label(1, startRow, "", borderEndLine));
				sheet.addCell(new Label(2, startRow, "", borderEndLine));
				sheet.addCell(new Label(3, startRow, "", borderEndLine));
				sheet.addCell(new Label(4, startRow, "", borderEndLine));
				sheet.addCell(new Label(5, startRow, "", borderEndLine));
				sheet.addCell(new Label(6, startRow, "", borderEndLine));
				
				sheet.addCell(new Label(7, startRow, "", borderEndLine));
				sheet.addCell(new Label(8, startRow, "", borderEndLine));
				sheet.addCell(new Label(9, startRow, "", borderEndLine));
				sheet.addCell(new Label(10, startRow, "", borderEndLine));
				sheet.addCell(new Label(11, startRow, "", borderEndLine));
				sheet.addCell(new Label(12, startRow, "", borderEndLine));
				sheet.addCell(new Label(13, startRow, "", borderEndLine));
				sheet.addCell(new Label(14, startRow, "", borderEndLine));
				sheet.addCell(new Label(15, startRow, "", borderEndLine));
				sheet.addCell(new Label(16, startRow, "", borderEndLine));
				sheet.addCell(new Label(17, startRow, "", borderEndLine));
				sheet.addCell(new Label(18, startRow, "", borderEndLine));
				sheet.addCell(new Label(19, startRow, "", borderEndLine));
				sheet.addCell(new Label(20, startRow, "", borderEndLine));
				sheet.addCell(new Label(21, startRow, "", borderEndLine));
				sheet.addCell(new Label(22, startRow, "", borderEndLine));
				sheet.addCell(new Label(23, startRow, "", borderEndLine));
				
				sheet.getSettings().setPassword("028313766");
				sheet.getSettings().setProtected(true);
			} else {
				sheet.mergeCells(0, 4, 5, 4);
				Alignment noAlg = Alignment.getAlignment(1);
				WritableCellFormat border = new WritableCellFormat();
				border.setAlignment(Alignment.CENTRE);
				border.setBorder(Border.ALL, BorderLineStyle.THIN);
				sheet.addCell(new Label(0,2, "�������  "+userInfo.getUserName(), headLeft));
				sheet.addCell(new Label(10, 2, "��ШӧǴ��� " + periodName + "  �.�. " + year, headRight));
				sheet.addCell(new Label(23, 2, "�ѹ������� : "+sdfPrint.format(gd.getTime()), headRight));
				sheet.addCell(new Label(0, 23, "��辺������", border));
				sheet.removeRow(5);
			}

			ww.write();
			ww.close();
			wb.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	private String convertFormatHour(Double hour) {
		String rlst = "";
		DecimalFormat dec = new DecimalFormat("###,##0");
		rlst = dec.format(hour != null ? hour.intValue() : 0);
		return rlst;
	}

	/**
	 * method ����Ѻ��Ǩ�ͺ Level �ͧ Data
	 * 
	 * @param div
	 * @param sec
	 * @return
	 */
	private int checkLV(String area, String sec) {
		int rlst = 0;
		if ((area != null && sec != null)) {
			rlst = 4;
		} else if (area != null && sec == null) {
			rlst = 3;
		}
		return rlst;
	}
}
