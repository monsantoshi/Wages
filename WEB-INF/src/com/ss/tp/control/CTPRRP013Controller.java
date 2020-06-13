/*
 * Created on 31 ï¿½.ï¿½. 2549
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


import com.ss.tp.dto.PrDailyNetAmtRepVO;
import com.ss.tp.security.UserInfo;

import com.ss.tp.service.FeeWpayPrEmployeeService;
import com.ss.tp.service.SuOrganizeService;

/**
 * @author airsenal
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class CTPRRP013Controller extends MultiActionController {

	
	
	public FeeWpayPrEmployeeService getFeeWpayPrEmployeeService() {
		return (FeeWpayPrEmployeeService) this.getApplicationContext().getBean("feeWpayPrEmployeeService");
	}

	public SuOrganizeService getSuOrganizeService() {
		return (SuOrganizeService) this.getApplicationContext().getBean(
				"suOrganizeService");
	}

	/**
	 * method ï¿½ï¿½ï¿½ï¿½Ñºï¿½ï¿½ï¿½Ò§ï¿½ï¿½Â§Ò¹ï¿½Ô¹ï¿½ï¿½ï¿½Ø§ï¿½Ø¢ï¿½Ò¾
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
					"attachment; filename=./page/report/CTPRRP013.xls");
			InputStream in = this.getServletContext().getResourceAsStream(
					"/page/report/CTPRRP013.xls");

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
			// "ï¿½ï¿½ï¿½ï¿½ï¿½ CTRWRP004 ï¿½ï¿½Â§Ò¹ï¿½Ô¹ï¿½ï¿½ï¿½zï¿½ï¿½Ø§ï¿½Ø¢ï¿½Ò¾",
			// HeadFormat));
			// sheet.addCell(new Label(0, 0,
			// this.getSuOrganizeService().findOrganizeName(ouCode), title));
			sheet.addCell(new Label(0,2, "¾ÔÁ¾ìâ´Â  "+userInfo.getUserName(), headLeft));
			sheet.addCell(new Label(21, 2, "»ÃÐ¨Ó§Ç´·Õè " + periodName + "  ¾.È. " + year, headRight));
			sheet.addCell(new Label(40, 2, "ÇÑ¹·Õè¾ÔÁ¾ì : "+sdfPrint.format(gd.getTime()), headRight));
			/* end title */

			PrDailyNetAmtRepVO helVo = null;
			List helRpt = this.getFeeWpayPrEmployeeService().prDailyRepByOrg(userId, ouCode,year, period);
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
			
			double sum07 = 0.0;
			double sumAllTotal07 = 0.0;
			
			double sum08 = 0.0;
			double sumAllTotal08 = 0.0;
			
			double sum11 = 0.0;
			double sumAllTotal11 = 0.0;
			
			double sum12 = 0.0;
			double sumAllTotal12 = 0.0;
			
			double sum13 = 0.0;
			double sumAllTotal13 = 0.0;
			
			double sum14 = 0.0;
			double sumAllTotal14 = 0.0;
			
			double sum17 = 0.0;
			double sumAllTotal17 = 0.0;
			
			double sum19 = 0.0;
			double sumAllTotal19 = 0.0;
			
			double sum20 = 0.0;
			double sumAllTotal20 = 0.0;
			
			double sum24 = 0.0;
			double sumAllTotal24 = 0.0;
			
			double sum25 = 0.0;
			double sumAllTotal25 = 0.0;
			
			double sum28 = 0.0;
			double sumAllTotal28 = 0.0;
			
			double sum29 = 0.0;
			double sumAllTotal29 = 0.0;
			double sum32 = 0.0;
			double sumAllTotal32 = 0.0;
			
			double sum42 = 0.0;
			double sumAllTotal42 = 0.0;
			double sum46 = 0.0;
			double sumAllTotal46 = 0.0;
			
			double sum47 = 0.0;
			double sumAllTotal47 = 0.0;
			
			double sum48 = 0.0;
			double sumAllTotal48 = 0.0;
			
			double sum49 = 0.0;
			double sumAllTotal49 = 0.0;
						
			double sum50 = 0.0;
			double sumAllTotal50 = 0.0;
			
			double sum53 = 0.0;
			double sumAllTotal53 = 0.0;
			
			double sum59 = 0.0;
			double sumAllTotal59 = 0.0;
			
			double sum60 = 0.0;
			double sumAllTotal60 = 0.0;
			
			double sum61 = 0.0;
			double sumAllTotal61 = 0.0;
			
			double sum62 = 0.0;
			double sumAllTotal62 = 0.0;
			
			
			
			String flagTotalHour = "";
			int seq = 0;
			if (helRpt.size() != 0) {
				for (int i = 0; i < helRpt.size(); i++) {
					helVo = (PrDailyNetAmtRepVO) helRpt.get(i);
					checkLV = checkLV(helVo.getAreaCode(), helVo.getSecCode());
					if (checkLV == 3) {
						if (!tmpCode.equals(helVo.getAreaCode())) {
							if (!flagTotalHour.equals("")) {
								sheet.mergeCells(0, startRow, 2, startRow);
								sheet.addCell(new Label(0, startRow, "ÃÇÁ", borderNumber2));
								sheet.addCell(new Label(3, startRow, String.valueOf(df.format(sumTotalNewSalary)),borderNumber2));
								sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalSalary02)),borderNumber2));
								sheet.addCell(new Label(5, startRow, String.valueOf(df.format(sumTotalSalary03)),borderNumber2));
								sheet.addCell(new Label(6, startRow, String.valueOf(df.format(sumTotalKlongchev)),borderNumber2));
								
								sheet.addCell(new Label(7, startRow, String.valueOf(df.format(sum07)),borderNumber2));
								sheet.addCell(new Label(8, startRow, String.valueOf(df.format(sum08)),borderNumber2));
								
								sheet.addCell(new Label(9, startRow, String.valueOf(df.format(sumTotalChilds)),borderNumber2));
								
								sheet.addCell(new Label(10, startRow, String.valueOf(df.format(sum11)),borderNumber2));
								sheet.addCell(new Label(11, startRow, String.valueOf(df.format(sum12)),borderNumber2));
								sheet.addCell(new Label(12, startRow, String.valueOf(df.format(sum13)),borderNumber2));
								sheet.addCell(new Label(13, startRow, String.valueOf(df.format(sum14)),borderNumber2));
								
								sheet.addCell(new Label(14, startRow, String.valueOf(df.format(sum17)),borderNumber2));
								sheet.addCell(new Label(15, startRow, String.valueOf(df.format(sum19)),borderNumber2));
								
								
								sheet.addCell(new Label(16, startRow, String.valueOf(df.format(sumTotalNetIncome)),borderNumber2));
								
								sheet.addCell(new Label(17, startRow, String.valueOf(df.format(sum20)),borderNumber2));
								sheet.addCell(new Label(18, startRow, String.valueOf(df.format(sumTotalVinai1Pct)),borderNumber2));
								
								sheet.addCell(new Label(19, startRow, String.valueOf(df.format(sumTotalVinai2)),borderNumber2));
								
								sheet.addCell(new Label(20, startRow, String.valueOf(df.format(sumTotalRefund)),borderNumber2));
								
								sheet.addCell(new Label(21, startRow, String.valueOf(df.format(sum24)),borderNumber2));
								sheet.addCell(new Label(22, startRow, String.valueOf(df.format(sum25)),borderNumber2));
								sheet.addCell(new Label(23, startRow, String.valueOf(df.format(sum28)),borderNumber2));
								sheet.addCell(new Label(24, startRow, String.valueOf(df.format(sum29)),borderNumber2));
								sheet.addCell(new Label(25, startRow, String.valueOf(df.format(sum32)),borderNumber2));
								
								
								sheet.addCell(new Label(26, startRow, String.valueOf(df.format(sumTotalMotor)),borderNumber2));
								sheet.addCell(new Label(27, startRow, String.valueOf(df.format(sumTotalRick)),borderNumber2));
								sheet.addCell(new Label(28, startRow, String.valueOf(df.format(sumTotalStudy)),borderNumber2));
								
								sheet.addCell(new Label(29, startRow, String.valueOf(df.format(sum42)),borderNumber2));
								sheet.addCell(new Label(30, startRow, String.valueOf(df.format(sum46)),borderNumber2));
								sheet.addCell(new Label(31, startRow, String.valueOf(df.format(sum47)),borderNumber2));
								sheet.addCell(new Label(32, startRow, String.valueOf(df.format(sum48)),borderNumber2));
								sheet.addCell(new Label(33, startRow, String.valueOf(df.format(sum49)),borderNumber2));
								sheet.addCell(new Label(34, startRow, String.valueOf(df.format(sum50)),borderNumber2));
								sheet.addCell(new Label(35, startRow, String.valueOf(df.format(sum53)),borderNumber2));
								sheet.addCell(new Label(36, startRow, String.valueOf(df.format(sum59)),borderNumber2));
								sheet.addCell(new Label(37, startRow, String.valueOf(df.format(sum60)),borderNumber2));
								sheet.addCell(new Label(38, startRow, String.valueOf(df.format(sum61)),borderNumber2));
								sheet.addCell(new Label(39, startRow, String.valueOf(df.format(sum62)),borderNumber2));
								
								
								sheet.addCell(new Label(40, startRow, String.valueOf(df.format(sumTotalNetDec)),borderNumber2));
								sheet.addCell(new Label(41, startRow, String.valueOf(df.format(sumTotalNet)),borderNumber2));
								
								
								sumTotalNewSalary = 0.0;
								sumTotalSalary02 = 0.0;
								sumTotalSalary03 = 0.0;
								sumTotalSalary = 0.0;
								sumTotalKlongchev = 0.0;
								
								sum07 = 0.0;
								sum08 = 0.0;
								
								sumTotalChilds = 0.0;
								
								sum11 = 0.0;
								sum12 = 0.0;
								sum13 = 0.0;
								sum14 = 0.0;
								sum17 = 0.0;
								sum19 = 0.0;
								sum20 = 0.0;
								sum24 = 0.0;
								sum25 = 0.0;
								sum28 = 0.0;
								
								sum29 = 0.0;
								sum32 = 0.0;
								sum42 = 0.0;
								sum46 = 0.0;
								sum47 = 0.0;
								sum48 = 0.0;
								sum49 = 0.0;
								sum50 = 0.0;
								sum53 = 0.0;
								
								sum59 = 0.0;
								sum60 = 0.0;
								sum61 = 0.0;
								sum62 = 0.0;
								
								sumTotalNetIncome = 0.0;
								sumTotalVinai1Pct = 0.0;
								
								sumTotalVinai2 = 0.0;
								sumTotalRefund = 0.0;
								sumTotalCar = 0.0;
								sumTotalMotor = 0.0;
								sumTotalRick = 0.0;
								sumTotalStudy = 0.0;
								sumTotalNetDec = 0.0;
								sumTotalNet = 0.0;
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
							sheet.addCell(new Blank(24, startRow,  borderLR));
							sheet.addCell(new Blank(25, startRow,  borderLR));
							sheet.addCell(new Blank(26, startRow,  borderLR));
							sheet.addCell(new Blank(27, startRow,  borderLR));
							sheet.addCell(new Blank(28, startRow,  borderLR));
							sheet.addCell(new Blank(29, startRow,  borderLR));
							sheet.addCell(new Blank(30, startRow,  borderLR));
							sheet.addCell(new Blank(31, startRow,  borderLR));
							sheet.addCell(new Blank(32, startRow,  borderLR));
							sheet.addCell(new Blank(33, startRow,  borderLR));
							sheet.addCell(new Blank(34, startRow,  borderLR));
							sheet.addCell(new Blank(35, startRow,  borderLR));
							sheet.addCell(new Blank(36, startRow,  borderLR));
							sheet.addCell(new Blank(37, startRow,  borderLR));
							sheet.addCell(new Blank(38, startRow,  borderLR));
							sheet.addCell(new Blank(39, startRow,  borderLR));
							sheet.addCell(new Blank(40, startRow,  borderLR));
							sheet.addCell(new Blank(41, startRow,  borderLR));
							
						
							sheet.setRowView(startRow, 320);
							startRow++;
							seq = seq + 1;
							sheet.addCell(new Label(0, startRow, String.valueOf(seq), borderNumber));
							sheet.addCell(new Label(1, startRow, helVo.getEmpCode(), boldCenterFormat));
							sheet.addCell(new Label(2, startRow, helVo.getFullName(), borderLR));
							sheet.addCell(new Label(3, startRow, String.valueOf(df.format(helVo.getNewSalary())),borderNumber));
							sheet.addCell(new Label(4, startRow, String.valueOf(df.format(helVo.getSalary02())),borderNumber));
							sheet.addCell(new Label(5, startRow, String.valueOf(df.format(helVo.getSalary03())),borderNumber));
							sheet.addCell(new Label(6, startRow, String.valueOf(df.format(helVo.getKlongchev())),borderNumber));
							
							sheet.addCell(new Label(7, startRow, String.valueOf(df.format(helVo.getYangchev())),borderNumber));
							sheet.addCell(new Label(8, startRow, String.valueOf(df.format(helVo.getSpec())),borderNumber));
							
							
							sheet.addCell(new Label(9, startRow, String.valueOf(df.format(helVo.getChilds())),borderNumber));
							sheet.addCell(new Label(10, startRow, String.valueOf(df.format(helVo.getOt())),borderNumber));
							sheet.addCell(new Label(11, startRow, String.valueOf(df.format(helVo.getPremium())),borderNumber));
							sheet.addCell(new Label(12, startRow, String.valueOf(df.format(helVo.getHealth())),borderNumber));
							
							sheet.addCell(new Label(13, startRow, String.valueOf(df.format(helVo.getRisk())),borderNumber));
							
							sheet.addCell(new Label(14, startRow, String.valueOf(df.format(helVo.getFeetax())),borderNumber));
							sheet.addCell(new Label(15, startRow, String.valueOf(df.format(helVo.getStep())),borderNumber));
						
							sheet.addCell(new Label(16, startRow, String.valueOf(df.format(helVo.getNetIncome())),borderNumber));
							
							sheet.addCell(new Label(17, startRow, String.valueOf(df.format(helVo.getTax())),borderNumber));
							sheet.addCell(new Label(18, startRow, String.valueOf(df.format(helVo.getVinai1Pct())),borderNumber));
							
							sheet.addCell(new Label(19, startRow, String.valueOf(df.format(helVo.getVinai2())),borderNumber));
							
							sheet.addCell(new Label(20, startRow, String.valueOf(df.format(helVo.getRefund())),borderNumber));
							sheet.addCell(new Label(21, startRow, String.valueOf(df.format(helVo.getPvf())),borderNumber));
							sheet.addCell(new Label(22, startRow, String.valueOf(df.format(helVo.getPangLoan())),borderNumber));
							
							sheet.addCell(new Label(23, startRow, String.valueOf(df.format(helVo.getHor())),borderNumber));
							
							sheet.addCell(new Label(24, startRow, String.valueOf(df.format(helVo.getCar())),borderNumber));
							sheet.addCell(new Label(25, startRow, String.valueOf(df.format(helVo.getLoanPost())),borderNumber));
							
							sheet.addCell(new Label(26, startRow, String.valueOf(df.format(helVo.getMotor())),borderNumber));
							sheet.addCell(new Label(27, startRow, String.valueOf(df.format(helVo.getRick())),borderNumber));
							sheet.addCell(new Label(28, startRow, String.valueOf(df.format(helVo.getStudy())),borderNumber));
							
							sheet.addCell(new Label(29, startRow, String.valueOf(df.format(helVo.getLoan())),borderNumber));
							sheet.addCell(new Label(30, startRow, String.valueOf(df.format(helVo.getHomegro())),borderNumber));
							sheet.addCell(new Label(31, startRow, String.valueOf(df.format(helVo.getHomektb())),borderNumber));
							sheet.addCell(new Label(32, startRow, String.valueOf(df.format(helVo.getLawLoan())),borderNumber));
							sheet.addCell(new Label(33, startRow, String.valueOf(df.format(helVo.getCpt())),borderNumber));
							sheet.addCell(new Label(34, startRow, String.valueOf(df.format(helVo.getSahakorn())),borderNumber));
							sheet.addCell(new Label(35, startRow, String.valueOf(df.format(helVo.getOomSin())),borderNumber));
							sheet.addCell(new Label(36, startRow, String.valueOf(df.format(helVo.getEtc())),borderNumber));
							sheet.addCell(new Label(37, startRow, String.valueOf(df.format(helVo.getKtb())),borderNumber));
							sheet.addCell(new Label(38, startRow, String.valueOf(df.format(helVo.getKys())),borderNumber));
							sheet.addCell(new Label(39, startRow, String.valueOf(df.format(helVo.getKro())),borderNumber));
							
							
							sheet.addCell(new Label(40, startRow, String.valueOf(df.format(helVo.getNetDec())),borderNumber));
							sheet.addCell(new Label(41, startRow, String.valueOf(df.format(helVo.getNet())),borderNumber));
						
							
							flagTotalHour = "sum";
							
							if (helVo.getNewSalary() != null) {
								sumTotalNewSalary = sumTotalNewSalary + helVo.getNewSalary().doubleValue();
								sumAllTotalNewSalary = sumAllTotalNewSalary	+ helVo.getNewSalary().doubleValue();

							}
							if (helVo.getSalary() != null) {
								sumTotalSalary = sumTotalSalary	+ helVo.getSalary().doubleValue();
								sumAllTotalSalary = sumAllTotalSalary + helVo.getSalary().doubleValue();

							}
							if (helVo.getChilds() != null) {
								sumTotalChilds = sumTotalChilds	+ helVo.getChilds().doubleValue();
								sumAllTotalChilds = sumAllTotalChilds + helVo.getChilds().doubleValue();

							}
							if (helVo.getKlongchev() != null) {
								sumTotalKlongchev = sumTotalKlongchev + helVo.getKlongchev().doubleValue();
								sumAllTotalKlongchev = sumAllTotalKlongchev + helVo.getKlongchev().doubleValue();

							}
							
							
							
							if (helVo.getSalary02() != null) {
								sumTotalSalary02 = sumTotalSalary02 + helVo.getSalary02().doubleValue();
								sumAllTotalSalary02 = sumAllTotalSalary02 + helVo.getSalary02().doubleValue();

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
								sumTotalNet = sumTotalNet
										+ helVo.getNet().doubleValue();
								sumAllTotalNet = sumAllTotalNet
										+ helVo.getNet().doubleValue();

							}
							
							if (helVo.getYangchev() != null) {
								sum07 = sum07	+ helVo.getYangchev().doubleValue();
								sumAllTotal07 = sumAllTotal07 + helVo.getYangchev().doubleValue();

							}
							
							if (helVo.getSpec() != null) {
								sum08 = sum08	+ helVo.getSpec().doubleValue();
								sumAllTotal08 = sumAllTotal08 + helVo.getSpec().doubleValue();

							}
							
							if (helVo.getOt() != null) {
								sum11 = sum11	+ helVo.getOt().doubleValue();
								sumAllTotal11 = sumAllTotal11 + helVo.getOt().doubleValue();

							}
							
							if (helVo.getPremium() != null) {
								sum12 = sum12	+ helVo.getPremium().doubleValue();
								sumAllTotal12 = sumAllTotal12 + helVo.getPremium().doubleValue();

							}
							
							if (helVo.getHealth() != null) {
								sum13 = sum13	+ helVo.getHealth().doubleValue();
								sumAllTotal13 = sumAllTotal13 + helVo.getHealth().doubleValue();

							}
							
							if (helVo.getRisk() != null) {
								sum14 = sum14	+ helVo.getRisk().doubleValue();
								sumAllTotal14 = sumAllTotal14 + helVo.getRisk().doubleValue();

							}
							
							if (helVo.getFeetax() != null) {
								sum17 = sum17	+ helVo.getFeetax().doubleValue();
								sumAllTotal17 = sumAllTotal17 + helVo.getFeetax().doubleValue();

							}
							
							if (helVo.getStep() != null) {
								sum19 = sum19	+ helVo.getStep().doubleValue();
								sumAllTotal19 = sumAllTotal19 + helVo.getStep().doubleValue();

							}
							
							if (helVo.getTax() != null) {
								sum20 = sum20	+ helVo.getTax().doubleValue();
								sumAllTotal20 = sumAllTotal20 + helVo.getTax().doubleValue();

							}
							
							if (helVo.getPvf() != null) {
								sum24 = sum24	+ helVo.getPvf().doubleValue();
								sumAllTotal24 = sumAllTotal24 + helVo.getPvf().doubleValue();

							}
							
							
							if (helVo.getPangLoan() != null) {
								sum25 = sum25	+ helVo.getPangLoan().doubleValue();
								sumAllTotal25 = sumAllTotal25 + helVo.getPangLoan().doubleValue();

							}
							
							if (helVo.getHor() != null) {
								sum28 = sum28	+ helVo.getHor().doubleValue();
								sumAllTotal28 = sumAllTotal28 + helVo.getHor().doubleValue();

							}
							
							if (helVo.getCar() != null) {
								sum29 = sum29	+ helVo.getCar().doubleValue();
								sumAllTotal29 = sumAllTotal29 + helVo.getCar().doubleValue();

							}
							
							
							if (helVo.getLoanPost() != null) {
								sum32 = sum32	+ helVo.getLoanPost().doubleValue();
								sumAllTotal32 = sumAllTotal32 + helVo.getLoanPost().doubleValue();

							}
							
							
							if (helVo.getLoan() != null) {
								sum42 = sum42	+ helVo.getLoan().doubleValue();
								sumAllTotal42 = sumAllTotal42 + helVo.getLoan().doubleValue();

							}
							if (helVo.getHomegro() != null) {
								sum46 = sum46	+ helVo.getHomegro().doubleValue();
								sumAllTotal46 = sumAllTotal46 + helVo.getHomegro().doubleValue();

							}
							if (helVo.getHomektb() != null) {
								sum47 = sum47	+ helVo.getHomektb().doubleValue();
								sumAllTotal47 = sumAllTotal47 + helVo.getHomektb().doubleValue();

							}
							if (helVo.getLawLoan() != null) {
								sum48 = sum48	+ helVo.getLawLoan().doubleValue();
								sumAllTotal48 = sumAllTotal48 + helVo.getLawLoan().doubleValue();

							}
							if (helVo.getCpt() != null) {
								sum49 = sum49	+ helVo.getCpt().doubleValue();
								sumAllTotal49 = sumAllTotal49 + helVo.getCpt().doubleValue();

							}
							
							if (helVo.getSahakorn() != null) {
								sum50 = sum50	+ helVo.getSahakorn().doubleValue();
								sumAllTotal50 = sumAllTotal50 + helVo.getSahakorn().doubleValue();

							}
							

							if (helVo.getOomSin() != null) {
								sum53 = sum53	+ helVo.getOomSin().doubleValue();
								sumAllTotal53 = sumAllTotal53 + helVo.getOomSin().doubleValue();

							}
							
							if (helVo.getEtc() != null) {
								sum59 = sum59	+ helVo.getEtc().doubleValue();
								sumAllTotal59 = sumAllTotal59 + helVo.getEtc().doubleValue();

							}
							if (helVo.getKtb() != null) {
								sum60 = sum60	+ helVo.getKtb().doubleValue();
								sumAllTotal60 = sumAllTotal60 + helVo.getKtb().doubleValue();

							}
							if (helVo.getKys() != null) {
								sum61 = sum61	+ helVo.getKys().doubleValue();
								sumAllTotal61 = sumAllTotal61 + helVo.getKys().doubleValue();

							}
							if (helVo.getKro() != null) {
								sum62 = sum62	+ helVo.getKro().doubleValue();
								sumAllTotal62 = sumAllTotal62 + helVo.getKro().doubleValue();

							}
							
						
							
							
							
							sheet.setRowView(startRow, 320);
							startRow++;
							if (helRpt.size() == (i + 1)) {
								sheet.mergeCells(0, startRow, 2, startRow);
								sheet.addCell(new Label(0, startRow, "ÃÇÁ", borderNumber2));
								sheet.addCell(new Label(3, startRow, String.valueOf(df.format(sumTotalNewSalary)),borderNumber2));
								
								sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalSalary02)),borderNumber2));
								sheet.addCell(new Label(5, startRow, String.valueOf(df.format(sumTotalSalary03)),borderNumber2));
								sheet.addCell(new Label(6, startRow, String.valueOf(df.format(sumTotalKlongchev)),borderNumber2));
								
								sheet.addCell(new Label(7, startRow, String.valueOf(df.format(sum07)),borderNumber2));
								sheet.addCell(new Label(8, startRow, String.valueOf(df.format(sum08)),borderNumber2));
								
								
								sheet.addCell(new Label(9, startRow, String.valueOf(df.format(sumTotalChilds)),borderNumber2));
								
								sheet.addCell(new Label(10, startRow, String.valueOf(df.format(sum11)),borderNumber2));
								sheet.addCell(new Label(11, startRow, String.valueOf(df.format(sum12)),borderNumber2));
								sheet.addCell(new Label(12, startRow, String.valueOf(df.format(sum13)),borderNumber2));
								sheet.addCell(new Label(13, startRow, String.valueOf(df.format(sum14)),borderNumber2));
								sheet.addCell(new Label(14, startRow, String.valueOf(df.format(sum17)),borderNumber2));
								sheet.addCell(new Label(15, startRow, String.valueOf(df.format(sum19)),borderNumber2));
							
								sheet.addCell(new Label(16, startRow, String.valueOf(df.format(sumTotalNetIncome)),borderNumber2));
								sheet.addCell(new Label(17, startRow, String.valueOf(df.format(sum20)),borderNumber2));
								
								sheet.addCell(new Label(18, startRow, String.valueOf(df.format(sumTotalVinai1Pct)),borderNumber2));
								
								sheet.addCell(new Label(19, startRow, String.valueOf(df.format(sumTotalVinai2)),borderNumber2));
								
								sheet.addCell(new Label(20, startRow, String.valueOf(df.format(sumTotalRefund)),borderNumber2));
								
								sheet.addCell(new Label(21, startRow, String.valueOf(df.format(sum24)),borderNumber2));
								sheet.addCell(new Label(22, startRow, String.valueOf(df.format(sum25)),borderNumber2));
								
								sheet.addCell(new Label(23, startRow, String.valueOf(df.format(sum28)),borderNumber2));
								sheet.addCell(new Label(24, startRow, String.valueOf(df.format(sum29)),borderNumber2));
								
								sheet.addCell(new Label(25, startRow, String.valueOf(df.format(sum32)),borderNumber2));
								sheet.addCell(new Label(26, startRow, String.valueOf(df.format(sumTotalMotor)),borderNumber2));
								sheet.addCell(new Label(27, startRow, String.valueOf(df.format(sumTotalRick)),borderNumber2));
								sheet.addCell(new Label(28, startRow, String.valueOf(df.format(sumTotalStudy)),borderNumber2));
								sheet.addCell(new Label(29, startRow, String.valueOf(df.format(sum42)),borderNumber2));
								
								sheet.addCell(new Label(30, startRow, String.valueOf(df.format(sum46)),borderNumber2));
								sheet.addCell(new Label(31, startRow, String.valueOf(df.format(sum47)),borderNumber2));
								sheet.addCell(new Label(32, startRow, String.valueOf(df.format(sum48)),borderNumber2));
								sheet.addCell(new Label(33, startRow, String.valueOf(df.format(sum49)),borderNumber2));
								sheet.addCell(new Label(34, startRow, String.valueOf(df.format(sum50)),borderNumber2));
								sheet.addCell(new Label(35, startRow, String.valueOf(df.format(sum53)),borderNumber2));
								
								
								sheet.addCell(new Label(36, startRow, String.valueOf(df.format(sum59)),borderNumber2));
								sheet.addCell(new Label(37, startRow, String.valueOf(df.format(sum60)),borderNumber2));
								sheet.addCell(new Label(38, startRow, String.valueOf(df.format(sum61)),borderNumber2));
								sheet.addCell(new Label(39, startRow, String.valueOf(df.format(sum62)),borderNumber2));
							
								
								sheet.addCell(new Label(40, startRow, String.valueOf(df.format(sumTotalNetDec)),borderNumber2));
								sheet.addCell(new Label(41, startRow, String.valueOf(df.format(sumTotalNet)),borderNumber2));
								
								sumTotalNewSalary = 0.0;
								sumTotalSalary02 = 0.0;
								sumTotalSalary03 = 0.0;
								sumTotalSalary = 0.0;
								sumTotalChilds = 0.0;
								sumTotalKlongchev = 0.0;
								
								sumTotalNetIncome = 0.0;
								sumTotalVinai1Pct = 0.0;

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
								
								sum07 = 0.0;
								sum08 = 0.0;
								
								sum11 = 0.0;
								sum12 = 0.0;
								sum13 = 0.0;
								sum14 = 0.0;
								sum17 = 0.0;
								sum19 = 0.0;
								sum20 = 0.0;
								sum24 = 0.0;
								sum25 = 0.0;
								sum28 = 0.0;
								
								sum29 = 0.0;
								sum32 = 0.0;
								sum42 = 0.0;
								sum46 = 0.0;
								sum47 = 0.0;
								sum48 = 0.0;
								sum49 = 0.0;
								sum50 = 0.0;
								sum53 = 0.0;
								
								sum59 = 0.0;
								sum60 = 0.0;
								sum61 = 0.0;
								sum62 = 0.0;
								
								
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
							sheet.addCell(new Label(4, startRow, String.valueOf(df.format(helVo.getSalary02())),borderNumber));
							sheet.addCell(new Label(5, startRow, String.valueOf(df.format(helVo.getSalary03())),borderNumber));
							sheet.addCell(new Label(6, startRow, String.valueOf(df.format(helVo.getKlongchev())),borderNumber));
							
							sheet.addCell(new Label(7, startRow, String.valueOf(df.format(helVo.getYangchev())),borderNumber));
							sheet.addCell(new Label(8, startRow, String.valueOf(df.format(helVo.getSpec())),borderNumber));
							
							
							sheet.addCell(new Label(9, startRow, String.valueOf(df.format(helVo.getChilds())),borderNumber));
							sheet.addCell(new Label(10, startRow, String.valueOf(df.format(helVo.getOt())),borderNumber));
							sheet.addCell(new Label(11, startRow, String.valueOf(df.format(helVo.getPremium())),borderNumber));
							sheet.addCell(new Label(12, startRow, String.valueOf(df.format(helVo.getHealth())),borderNumber));
							sheet.addCell(new Label(13, startRow, String.valueOf(df.format(helVo.getRisk())),borderNumber));
							
							sheet.addCell(new Label(14, startRow, String.valueOf(df.format(helVo.getFeetax())),borderNumber));
							sheet.addCell(new Label(15, startRow, String.valueOf(df.format(helVo.getStep())),borderNumber));
						
							sheet.addCell(new Label(16, startRow, String.valueOf(df.format(helVo.getNetIncome())),borderNumber));
							
							sheet.addCell(new Label(17, startRow, String.valueOf(df.format(helVo.getTax())),borderNumber));
							sheet.addCell(new Label(18, startRow, String.valueOf(df.format(helVo.getVinai1Pct())),borderNumber));
							
							sheet.addCell(new Label(19, startRow, String.valueOf(df.format(helVo.getVinai2())),borderNumber));
							
							sheet.addCell(new Label(20, startRow, String.valueOf(df.format(helVo.getRefund())),borderNumber));
							sheet.addCell(new Label(21, startRow, String.valueOf(df.format(helVo.getPvf())),borderNumber));
							sheet.addCell(new Label(22, startRow, String.valueOf(df.format(helVo.getPangLoan())),borderNumber));
							
							sheet.addCell(new Label(23, startRow, String.valueOf(df.format(helVo.getHor())),borderNumber));
							
							sheet.addCell(new Label(24, startRow, String.valueOf(df.format(helVo.getCar())),borderNumber));
							sheet.addCell(new Label(25, startRow, String.valueOf(df.format(helVo.getLoanPost())),borderNumber));
							
							sheet.addCell(new Label(26, startRow, String.valueOf(df.format(helVo.getMotor())),borderNumber));
							sheet.addCell(new Label(27, startRow, String.valueOf(df.format(helVo.getRick())),borderNumber));
							sheet.addCell(new Label(28, startRow, String.valueOf(df.format(helVo.getStudy())),borderNumber));
							
							sheet.addCell(new Label(29, startRow, String.valueOf(df.format(helVo.getLoan())),borderNumber));
							sheet.addCell(new Label(30, startRow, String.valueOf(df.format(helVo.getHomegro())),borderNumber));
							sheet.addCell(new Label(31, startRow, String.valueOf(df.format(helVo.getHomektb())),borderNumber));
							sheet.addCell(new Label(32, startRow, String.valueOf(df.format(helVo.getLawLoan())),borderNumber));
							sheet.addCell(new Label(33, startRow, String.valueOf(df.format(helVo.getCpt())),borderNumber));
							sheet.addCell(new Label(34, startRow, String.valueOf(df.format(helVo.getSahakorn())),borderNumber));
							sheet.addCell(new Label(35, startRow, String.valueOf(df.format(helVo.getOomSin())),borderNumber));
							sheet.addCell(new Label(36, startRow, String.valueOf(df.format(helVo.getEtc())),borderNumber));
							sheet.addCell(new Label(37, startRow, String.valueOf(df.format(helVo.getKtb())),borderNumber));
							sheet.addCell(new Label(38, startRow, String.valueOf(df.format(helVo.getKys())),borderNumber));
							sheet.addCell(new Label(39, startRow, String.valueOf(df.format(helVo.getKro())),borderNumber));
							
							
							sheet.addCell(new Label(40, startRow, String.valueOf(df.format(helVo.getNetDec())),borderNumber));
							sheet.addCell(new Label(41, startRow, String.valueOf(df.format(helVo.getNet())),borderNumber));
						
						
						
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
								sumTotalNetIncome = sumTotalNetIncome + helVo.getNetIncome().doubleValue();
								sumAllTotalNetIncome = sumAllTotalNetIncome	+ helVo.getNetIncome().doubleValue();

							}
							if (helVo.getVinai1Pct() != null) {
								sumTotalVinai1Pct = sumTotalVinai1Pct
										+ helVo.getVinai1Pct().doubleValue();
								sumAllTotalVinai1Pct = sumAllTotalVinai1Pct
										+ helVo.getVinai1Pct().doubleValue();

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
								sumTotalNetDec = sumTotalNetDec	+ helVo.getNetDec().doubleValue();
								sumAllTotalNetDec = sumAllTotalNetDec + helVo.getNetDec().doubleValue();

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
							
							
							if (helVo.getNet() != null) {
								sumTotalNet = sumTotalNet + helVo.getNet().doubleValue();
								sumAllTotalNet = sumAllTotalNet + helVo.getNet().doubleValue();

							}
							

							if (helVo.getYangchev() != null) {
								sum07 = sum07	+ helVo.getYangchev().doubleValue();
								sumAllTotal07 = sumAllTotal07 + helVo.getYangchev().doubleValue();

							}
							
							if (helVo.getSpec() != null) {
								sum08 = sum08	+ helVo.getSpec().doubleValue();
								sumAllTotal08 = sumAllTotal08 + helVo.getSpec().doubleValue();

							}
							
							if (helVo.getOt() != null) {
								sum11 = sum11	+ helVo.getOt().doubleValue();
								sumAllTotal11 = sumAllTotal11 + helVo.getOt().doubleValue();

							}
							
							if (helVo.getPremium() != null) {
								sum12 = sum12	+ helVo.getPremium().doubleValue();
								sumAllTotal12 = sumAllTotal12 + helVo.getPremium().doubleValue();

							}
							
							if (helVo.getHealth() != null) {
								sum13 = sum13	+ helVo.getHealth().doubleValue();
								sumAllTotal13 = sumAllTotal13 + helVo.getHealth().doubleValue();

							}
							
							
							if (helVo.getRisk() != null) {
								sum14 = sum14	+ helVo.getRisk().doubleValue();
								sumAllTotal14 = sumAllTotal14 + helVo.getRisk().doubleValue();

							}
							
							if (helVo.getFeetax() != null) {
								sum17 = sum17	+ helVo.getFeetax().doubleValue();
								sumAllTotal17 = sumAllTotal17 + helVo.getFeetax().doubleValue();

							}
							
							if (helVo.getStep() != null) {
								sum19 = sum19	+ helVo.getStep().doubleValue();
								sumAllTotal19 = sumAllTotal19 + helVo.getStep().doubleValue();

							}
							
							if (helVo.getTax() != null) {
								sum20 = sum20	+ helVo.getTax().doubleValue();
								sumAllTotal20 = sumAllTotal20 + helVo.getTax().doubleValue();

							}
							
							if (helVo.getPvf() != null) {
								sum24 = sum24	+ helVo.getPvf().doubleValue();
								sumAllTotal24 = sumAllTotal24 + helVo.getPvf().doubleValue();

							}
							
							
							if (helVo.getPangLoan() != null) {
								sum25 = sum25	+ helVo.getPangLoan().doubleValue();
								sumAllTotal25 = sumAllTotal25 + helVo.getPangLoan().doubleValue();

							}
							
							if (helVo.getHor() != null) {
								sum28 = sum28	+ helVo.getHor().doubleValue();
								sumAllTotal28 = sumAllTotal28 + helVo.getHor().doubleValue();

							}
							
							if (helVo.getCar() != null) {
								sum29 = sum29	+ helVo.getCar().doubleValue();
								sumAllTotal29 = sumAllTotal29 + helVo.getCar().doubleValue();

							}
							
							
							if (helVo.getLoanPost() != null) {
								sum32 = sum32	+ helVo.getLoanPost().doubleValue();
								sumAllTotal32 = sumAllTotal32 + helVo.getLoanPost().doubleValue();

							}
							
							
							if (helVo.getLoan() != null) {
								sum42 = sum42	+ helVo.getLoan().doubleValue();
								sumAllTotal42 = sumAllTotal42 + helVo.getLoan().doubleValue();

							}
							if (helVo.getHomegro() != null) {
								sum46 = sum46	+ helVo.getHomegro().doubleValue();
								sumAllTotal46 = sumAllTotal46 + helVo.getHomegro().doubleValue();

							}
							if (helVo.getHomektb() != null) {
								sum47 = sum47	+ helVo.getHomektb().doubleValue();
								sumAllTotal47 = sumAllTotal47 + helVo.getHomektb().doubleValue();

							}
							if (helVo.getLawLoan() != null) {
								sum48 = sum48	+ helVo.getLawLoan().doubleValue();
								sumAllTotal48 = sumAllTotal48 + helVo.getLawLoan().doubleValue();

							}
							if (helVo.getCpt() != null) {
								sum49 = sum49	+ helVo.getCpt().doubleValue();
								sumAllTotal49 = sumAllTotal49 + helVo.getCpt().doubleValue();

							}
							
							if (helVo.getSahakorn() != null) {
								sum50 = sum50	+ helVo.getSahakorn().doubleValue();
								sumAllTotal50 = sumAllTotal50 + helVo.getSahakorn().doubleValue();

							}
							

							if (helVo.getOomSin() != null) {
								sum53 = sum53	+ helVo.getOomSin().doubleValue();
								sumAllTotal53 = sumAllTotal53 + helVo.getOomSin().doubleValue();

							}
							
							if (helVo.getEtc() != null) {
								sum59 = sum59	+ helVo.getEtc().doubleValue();
								sumAllTotal59 = sumAllTotal59 + helVo.getEtc().doubleValue();

							}
							if (helVo.getKtb() != null) {
								sum60 = sum60	+ helVo.getKtb().doubleValue();
								sumAllTotal60 = sumAllTotal60 + helVo.getKtb().doubleValue();

							}
							if (helVo.getKys() != null) {
								sum61 = sum61	+ helVo.getKys().doubleValue();
								sumAllTotal61 = sumAllTotal61 + helVo.getKys().doubleValue();

							}
							if (helVo.getKro() != null) {
								sum62 = sum62	+ helVo.getKro().doubleValue();
								sumAllTotal62 = sumAllTotal62 + helVo.getKro().doubleValue();

							}
							
						
						
							
							sheet.setRowView(startRow, 320);
							startRow++;
							if (helRpt.size() == i + 1) {
								sheet.mergeCells(0, startRow, 2, startRow);
								sheet.addCell(new Label(0, startRow, "ÃÇÁ", borderNumber2));
								sheet.addCell(new Label(3, startRow, String.valueOf(df.format(sumTotalNewSalary)),borderNumber2));
								sheet.addCell(new Label(4, startRow, String.valueOf(df.format(helVo.getSalary02())),borderNumber));
								sheet.addCell(new Label(5, startRow, String.valueOf(df.format(helVo.getSalary03())),borderNumber));
								sheet.addCell(new Label(6, startRow, String.valueOf(df.format(helVo.getKlongchev())),borderNumber));
								
								sheet.addCell(new Label(7, startRow, String.valueOf(df.format(helVo.getYangchev())),borderNumber));
								sheet.addCell(new Label(8, startRow, String.valueOf(df.format(helVo.getSpec())),borderNumber));
								
								
								sheet.addCell(new Label(9, startRow, String.valueOf(df.format(helVo.getChilds())),borderNumber));
								sheet.addCell(new Label(10, startRow, String.valueOf(df.format(helVo.getOt())),borderNumber));
								sheet.addCell(new Label(11, startRow, String.valueOf(df.format(helVo.getPremium())),borderNumber));
								sheet.addCell(new Label(12, startRow, String.valueOf(df.format(helVo.getHealth())),borderNumber));
								sheet.addCell(new Label(13, startRow, String.valueOf(df.format(helVo.getRisk())),borderNumber));
								
								sheet.addCell(new Label(14, startRow, String.valueOf(df.format(helVo.getFeetax())),borderNumber));
								sheet.addCell(new Label(15, startRow, String.valueOf(df.format(helVo.getStep())),borderNumber));
							
								sheet.addCell(new Label(16, startRow, String.valueOf(df.format(helVo.getNetIncome())),borderNumber));
								
								sheet.addCell(new Label(17, startRow, String.valueOf(df.format(helVo.getTax())),borderNumber));
								sheet.addCell(new Label(18, startRow, String.valueOf(df.format(helVo.getVinai1Pct())),borderNumber));
								
								sheet.addCell(new Label(19, startRow, String.valueOf(df.format(helVo.getVinai2())),borderNumber));
								
								sheet.addCell(new Label(20, startRow, String.valueOf(df.format(helVo.getRefund())),borderNumber));
								sheet.addCell(new Label(21, startRow, String.valueOf(df.format(helVo.getPvf())),borderNumber));
								sheet.addCell(new Label(22, startRow, String.valueOf(df.format(helVo.getPangLoan())),borderNumber));
								
								sheet.addCell(new Label(23, startRow, String.valueOf(df.format(helVo.getHor())),borderNumber));
								
								sheet.addCell(new Label(24, startRow, String.valueOf(df.format(helVo.getCar())),borderNumber));
								sheet.addCell(new Label(25, startRow, String.valueOf(df.format(helVo.getLoanPost())),borderNumber));
								
								sheet.addCell(new Label(26, startRow, String.valueOf(df.format(helVo.getMotor())),borderNumber));
								sheet.addCell(new Label(27, startRow, String.valueOf(df.format(helVo.getRick())),borderNumber));
								sheet.addCell(new Label(28, startRow, String.valueOf(df.format(helVo.getStudy())),borderNumber));
								
								sheet.addCell(new Label(29, startRow, String.valueOf(df.format(helVo.getLoan())),borderNumber));
								sheet.addCell(new Label(30, startRow, String.valueOf(df.format(helVo.getHomegro())),borderNumber));
								sheet.addCell(new Label(31, startRow, String.valueOf(df.format(helVo.getHomektb())),borderNumber));
								sheet.addCell(new Label(32, startRow, String.valueOf(df.format(helVo.getLawLoan())),borderNumber));
								sheet.addCell(new Label(33, startRow, String.valueOf(df.format(helVo.getCpt())),borderNumber));
								sheet.addCell(new Label(34, startRow, String.valueOf(df.format(helVo.getSahakorn())),borderNumber));
								sheet.addCell(new Label(35, startRow, String.valueOf(df.format(helVo.getOomSin())),borderNumber));
								sheet.addCell(new Label(36, startRow, String.valueOf(df.format(helVo.getEtc())),borderNumber));
								sheet.addCell(new Label(37, startRow, String.valueOf(df.format(helVo.getKtb())),borderNumber));
								sheet.addCell(new Label(38, startRow, String.valueOf(df.format(helVo.getKys())),borderNumber));
								sheet.addCell(new Label(39, startRow, String.valueOf(df.format(helVo.getKro())),borderNumber));
								
								
								sheet.addCell(new Label(40, startRow, String.valueOf(df.format(helVo.getNetDec())),borderNumber));
								sheet.addCell(new Label(41, startRow, String.valueOf(df.format(helVo.getNet())),borderNumber));
								
								sumTotalNewSalary = 0.0;
								sumTotalSalary02 = 0.0;
								sumTotalSalary03 = 0.0;
								sumTotalSalary = 0.0;
								sumTotalChilds = 0.0;
								sumTotalKlongchev = 0.0;
								
								sumTotalNetIncome = 0.0;
								sumTotalVinai1Pct = 0.0;

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
								sum07 = 0.0;
								sum08 = 0.0;
								
								sum11 = 0.0;
								sum12 = 0.0;
								sum13 = 0.0;
								sum14 = 0.0;
								sum17 = 0.0;
								sum19 = 0.0;
								sum20 = 0.0;
								sum24 = 0.0;
								sum25 = 0.0;
								sum28 = 0.0;
								
								sum29 = 0.0;
								sum32 = 0.0;
								sum42 = 0.0;
								sum46 = 0.0;
								sum47 = 0.0;
								sum48 = 0.0;
								sum49 = 0.0;
								sum50 = 0.0;
								sum53 = 0.0;
								
								sum59 = 0.0;
								sum60 = 0.0;
								sum61 = 0.0;
								sum62 = 0.0;
								startRow++;
							}
						}
						tmpCode = helVo.getAreaCode();
					} else if (checkLV == 4) {
						if (!tmpCode.equals(helVo.getSecCode())) {
							if (!flagTotalHour.equals("")) {
								sheet.mergeCells(0, startRow, 2, startRow);
								sheet.addCell(new Label(0, startRow, "ÃÇÁ", borderNumber2));
								sheet.addCell(new Label(3, startRow, String.valueOf(df.format(sumTotalNewSalary)),borderNumber2));
								
								sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalSalary02)),borderNumber2));
								sheet.addCell(new Label(5, startRow, String.valueOf(df.format(sumTotalSalary03)),borderNumber2));
								sheet.addCell(new Label(6, startRow, String.valueOf(df.format(sumTotalKlongchev)),borderNumber2));
								sheet.addCell(new Label(7, startRow, String.valueOf(df.format(sum07)),borderNumber2));
								sheet.addCell(new Label(8, startRow, String.valueOf(df.format(sum08)),borderNumber2));
								
								
								sheet.addCell(new Label(9, startRow, String.valueOf(df.format(sumTotalChilds)),borderNumber2));
								
								sheet.addCell(new Label(10, startRow, String.valueOf(df.format(sum11)),borderNumber2));
								sheet.addCell(new Label(11, startRow, String.valueOf(df.format(sum12)),borderNumber2));
								sheet.addCell(new Label(12, startRow, String.valueOf(df.format(sum13)),borderNumber2));
								sheet.addCell(new Label(13, startRow, String.valueOf(df.format(sum14)),borderNumber2));
								sheet.addCell(new Label(14, startRow, String.valueOf(df.format(sum17)),borderNumber2));
								sheet.addCell(new Label(15, startRow, String.valueOf(df.format(sum19)),borderNumber2));
							
								sheet.addCell(new Label(16, startRow, String.valueOf(df.format(sumTotalNetIncome)),borderNumber2));
								sheet.addCell(new Label(17, startRow, String.valueOf(df.format(sum20)),borderNumber2));
								
								sheet.addCell(new Label(18, startRow, String.valueOf(df.format(sumTotalVinai1Pct)),borderNumber2));
								
								sheet.addCell(new Label(19, startRow, String.valueOf(df.format(sumTotalVinai2)),borderNumber2));
								
								sheet.addCell(new Label(20, startRow, String.valueOf(df.format(sumTotalRefund)),borderNumber2));
								
								sheet.addCell(new Label(21, startRow, String.valueOf(df.format(sum24)),borderNumber2));
								sheet.addCell(new Label(22, startRow, String.valueOf(df.format(sum25)),borderNumber2));
								
								sheet.addCell(new Label(23, startRow, String.valueOf(df.format(sum28)),borderNumber2));
								sheet.addCell(new Label(24, startRow, String.valueOf(df.format(sum29)),borderNumber2));
								
								sheet.addCell(new Label(25, startRow, String.valueOf(df.format(sum32)),borderNumber2));
								sheet.addCell(new Label(26, startRow, String.valueOf(df.format(sumTotalMotor)),borderNumber2));
								sheet.addCell(new Label(27, startRow, String.valueOf(df.format(sumTotalRick)),borderNumber2));
								sheet.addCell(new Label(28, startRow, String.valueOf(df.format(sumTotalStudy)),borderNumber2));
								sheet.addCell(new Label(29, startRow, String.valueOf(df.format(sum42)),borderNumber2));
								
								sheet.addCell(new Label(30, startRow, String.valueOf(df.format(sum46)),borderNumber2));
								sheet.addCell(new Label(31, startRow, String.valueOf(df.format(sum47)),borderNumber2));
								sheet.addCell(new Label(32, startRow, String.valueOf(df.format(sum48)),borderNumber2));
								sheet.addCell(new Label(33, startRow, String.valueOf(df.format(sum49)),borderNumber2));
								sheet.addCell(new Label(34, startRow, String.valueOf(df.format(sum50)),borderNumber2));
								sheet.addCell(new Label(35, startRow, String.valueOf(df.format(sum53)),borderNumber2));
								
								
								sheet.addCell(new Label(36, startRow, String.valueOf(df.format(sum59)),borderNumber2));
								sheet.addCell(new Label(37, startRow, String.valueOf(df.format(sum60)),borderNumber2));
								sheet.addCell(new Label(38, startRow, String.valueOf(df.format(sum61)),borderNumber2));
								sheet.addCell(new Label(39, startRow, String.valueOf(df.format(sum62)),borderNumber2));
							
								
								sheet.addCell(new Label(40, startRow, String.valueOf(df.format(sumTotalNetDec)),borderNumber2));
								sheet.addCell(new Label(41, startRow, String.valueOf(df.format(sumTotalNet)),borderNumber2));
								sumTotalNewSalary = 0.0;
								sumTotalSalary02 = 0.0;
								sumTotalSalary03 = 0.0;
								sumTotalSalary = 0.0;
								sumTotalChilds = 0.0;
								sumTotalKlongchev = 0.0;
								
								sumTotalNetIncome = 0.0;
								sumTotalVinai1Pct = 0.0;

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
								
								sum07 = 0.0;
								sum08 = 0.0;
								
								sum11 = 0.0;
								sum12 = 0.0;
								sum13 = 0.0;
								sum14 = 0.0;
								sum17 = 0.0;
								sum19 = 0.0;
								sum20 = 0.0;
								sum24 = 0.0;
								sum25 = 0.0;
								sum28 = 0.0;
								
								sum29 = 0.0;
								sum32 = 0.0;
								sum42 = 0.0;
								sum46 = 0.0;
								sum47 = 0.0;
								sum48 = 0.0;
								sum49 = 0.0;
								sum50 = 0.0;
								sum53 = 0.0;
								
								sum59 = 0.0;
								sum60 = 0.0;
								sum61 = 0.0;
								sum62 = 0.0;
								startRow++;

							}
							sheet.addCell(new Label(0, startRow, helVo.getOrgCode()+ " "+ helVo.getAreaDesc()+ " " + helVo.getSecDesc(), group));
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
							sheet.addCell(new Blank(24, startRow,  borderLR));
							sheet.addCell(new Blank(25, startRow,  borderLR));
							sheet.addCell(new Blank(26, startRow, borderLR));
							sheet.addCell(new Blank(27, startRow, borderLR));
							sheet.addCell(new Blank(28, startRow,  borderLR));
							sheet.addCell(new Blank(29, startRow,  borderLR));
							sheet.addCell(new Blank(30, startRow,  borderLR));
							sheet.addCell(new Blank(31, startRow,  borderLR));
							sheet.addCell(new Blank(32, startRow,  borderLR));
							sheet.addCell(new Blank(33, startRow,  borderLR));
							sheet.addCell(new Blank(34, startRow,  borderLR));
							sheet.addCell(new Blank(35, startRow,  borderLR));
							sheet.addCell(new Blank(36, startRow,  borderLR));
							sheet.addCell(new Blank(37, startRow,  borderLR));
							sheet.addCell(new Blank(38, startRow,  borderLR));
							sheet.addCell(new Blank(39, startRow,  borderLR));
							sheet.addCell(new Blank(40, startRow, borderLR));
							sheet.addCell(new Blank(41, startRow, borderLR));
							
							
							
							sheet.setRowView(startRow, 320);
							startRow++;
							seq = seq + 1;
							sheet.addCell(new Label(0, startRow, String.valueOf(seq), borderNumber));
							sheet.addCell(new Label(1, startRow, helVo.getEmpCode(), boldCenterFormat));
							sheet.addCell(new Label(2, startRow, helVo.getFullName(), borderLR));
							sheet.addCell(new Label(3, startRow, String.valueOf(df.format(helVo.getNewSalary())),borderNumber));
						
							sheet.addCell(new Label(4, startRow, String.valueOf(df.format(helVo.getSalary02())),borderNumber));
							sheet.addCell(new Label(5, startRow, String.valueOf(df.format(helVo.getSalary03())),borderNumber));
							sheet.addCell(new Label(6, startRow, String.valueOf(df.format(helVo.getKlongchev())),borderNumber));
							
							sheet.addCell(new Label(7, startRow, String.valueOf(df.format(helVo.getYangchev())),borderNumber));
							sheet.addCell(new Label(8, startRow, String.valueOf(df.format(helVo.getSpec())),borderNumber));
							
							
							sheet.addCell(new Label(9, startRow, String.valueOf(df.format(helVo.getChilds())),borderNumber));
							sheet.addCell(new Label(10, startRow, String.valueOf(df.format(helVo.getOt())),borderNumber));
							sheet.addCell(new Label(11, startRow, String.valueOf(df.format(helVo.getPremium())),borderNumber));
							sheet.addCell(new Label(12, startRow, String.valueOf(df.format(helVo.getHealth())),borderNumber));
							
							sheet.addCell(new Label(13, startRow, String.valueOf(df.format(helVo.getRisk())),borderNumber));
							
							sheet.addCell(new Label(14, startRow, String.valueOf(df.format(helVo.getFeetax())),borderNumber));
							sheet.addCell(new Label(15, startRow, String.valueOf(df.format(helVo.getStep())),borderNumber));
						
							sheet.addCell(new Label(16, startRow, String.valueOf(df.format(helVo.getNetIncome())),borderNumber));
							
							sheet.addCell(new Label(17, startRow, String.valueOf(df.format(helVo.getTax())),borderNumber));
							sheet.addCell(new Label(18, startRow, String.valueOf(df.format(helVo.getVinai1Pct())),borderNumber));
							
							sheet.addCell(new Label(19, startRow, String.valueOf(df.format(helVo.getVinai2())),borderNumber));
							
							sheet.addCell(new Label(20, startRow, String.valueOf(df.format(helVo.getRefund())),borderNumber));
							sheet.addCell(new Label(21, startRow, String.valueOf(df.format(helVo.getPvf())),borderNumber));
							sheet.addCell(new Label(22, startRow, String.valueOf(df.format(helVo.getPangLoan())),borderNumber));
							
							sheet.addCell(new Label(23, startRow, String.valueOf(df.format(helVo.getHor())),borderNumber));
							
							sheet.addCell(new Label(24, startRow, String.valueOf(df.format(helVo.getCar())),borderNumber));
							sheet.addCell(new Label(25, startRow, String.valueOf(df.format(helVo.getLoanPost())),borderNumber));
							
							sheet.addCell(new Label(26, startRow, String.valueOf(df.format(helVo.getMotor())),borderNumber));
							sheet.addCell(new Label(27, startRow, String.valueOf(df.format(helVo.getRick())),borderNumber));
							sheet.addCell(new Label(28, startRow, String.valueOf(df.format(helVo.getStudy())),borderNumber));
							
							sheet.addCell(new Label(29, startRow, String.valueOf(df.format(helVo.getLoan())),borderNumber));
							sheet.addCell(new Label(30, startRow, String.valueOf(df.format(helVo.getHomegro())),borderNumber));
							sheet.addCell(new Label(31, startRow, String.valueOf(df.format(helVo.getHomektb())),borderNumber));
							sheet.addCell(new Label(32, startRow, String.valueOf(df.format(helVo.getLawLoan())),borderNumber));
							sheet.addCell(new Label(33, startRow, String.valueOf(df.format(helVo.getCpt())),borderNumber));
							sheet.addCell(new Label(34, startRow, String.valueOf(df.format(helVo.getSahakorn())),borderNumber));
							sheet.addCell(new Label(35, startRow, String.valueOf(df.format(helVo.getOomSin())),borderNumber));
							sheet.addCell(new Label(36, startRow, String.valueOf(df.format(helVo.getEtc())),borderNumber));
							sheet.addCell(new Label(37, startRow, String.valueOf(df.format(helVo.getKtb())),borderNumber));
							sheet.addCell(new Label(38, startRow, String.valueOf(df.format(helVo.getKys())),borderNumber));
							sheet.addCell(new Label(39, startRow, String.valueOf(df.format(helVo.getKro())),borderNumber));
							
							
							sheet.addCell(new Label(40, startRow, String.valueOf(df.format(helVo.getNetDec())),borderNumber));
							sheet.addCell(new Label(41, startRow, String.valueOf(df.format(helVo.getNet())),borderNumber));
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
							if (helVo.getYangchev() != null) {
								sum07 = sum07	+ helVo.getYangchev().doubleValue();
								sumAllTotal07 = sumAllTotal07 + helVo.getYangchev().doubleValue();

							}
							
							if (helVo.getSpec() != null) {
								sum08 = sum08	+ helVo.getSpec().doubleValue();
								sumAllTotal08 = sumAllTotal08 + helVo.getSpec().doubleValue();

							}
							
							if (helVo.getOt() != null) {
								sum11 = sum11	+ helVo.getOt().doubleValue();
								sumAllTotal11 = sumAllTotal11 + helVo.getOt().doubleValue();

							}
							
							if (helVo.getPremium() != null) {
								sum12 = sum12	+ helVo.getPremium().doubleValue();
								sumAllTotal12 = sumAllTotal12 + helVo.getPremium().doubleValue();

							}
							
							if (helVo.getHealth() != null) {
								sum13 = sum13	+ helVo.getHealth().doubleValue();
								sumAllTotal13 = sumAllTotal13 + helVo.getHealth().doubleValue();

							}
							
							if (helVo.getRisk() != null) {
								sum14 = sum14	+ helVo.getRisk().doubleValue();
								sumAllTotal14 = sumAllTotal14 + helVo.getRisk().doubleValue();

							}
							
							if (helVo.getFeetax() != null) {
								sum17 = sum17	+ helVo.getFeetax().doubleValue();
								sumAllTotal17 = sumAllTotal17 + helVo.getFeetax().doubleValue();

							}
							
							if (helVo.getStep() != null) {
								sum19 = sum19	+ helVo.getStep().doubleValue();
								sumAllTotal19 = sumAllTotal19 + helVo.getStep().doubleValue();

							}
							
							if (helVo.getTax() != null) {
								sum20 = sum20	+ helVo.getTax().doubleValue();
								sumAllTotal20 = sumAllTotal20 + helVo.getTax().doubleValue();

							}
							
							if (helVo.getPvf() != null) {
								sum24 = sum24	+ helVo.getPvf().doubleValue();
								sumAllTotal24 = sumAllTotal24 + helVo.getPvf().doubleValue();

							}
							
							
							if (helVo.getPangLoan() != null) {
								sum25 = sum25	+ helVo.getPangLoan().doubleValue();
								sumAllTotal25 = sumAllTotal25 + helVo.getPangLoan().doubleValue();

							}
							
							if (helVo.getHor() != null) {
								sum28 = sum28	+ helVo.getHor().doubleValue();
								sumAllTotal28 = sumAllTotal28 + helVo.getHor().doubleValue();

							}
							
							if (helVo.getCar() != null) {
								sum29 = sum29	+ helVo.getCar().doubleValue();
								sumAllTotal29 = sumAllTotal29 + helVo.getCar().doubleValue();

							}
							
							
							if (helVo.getLoanPost() != null) {
								sum32 = sum32	+ helVo.getLoanPost().doubleValue();
								sumAllTotal32 = sumAllTotal32 + helVo.getLoanPost().doubleValue();

							}
							
							
							if (helVo.getLoan() != null) {
								sum42 = sum42	+ helVo.getLoan().doubleValue();
								sumAllTotal42 = sumAllTotal42 + helVo.getLoan().doubleValue();

							}
							if (helVo.getHomegro() != null) {
								sum46 = sum46	+ helVo.getHomegro().doubleValue();
								sumAllTotal46 = sumAllTotal46 + helVo.getHomegro().doubleValue();

							}
							if (helVo.getHomektb() != null) {
								sum47 = sum47	+ helVo.getHomektb().doubleValue();
								sumAllTotal47 = sumAllTotal47 + helVo.getHomektb().doubleValue();

							}
							if (helVo.getLawLoan() != null) {
								sum48 = sum48	+ helVo.getLawLoan().doubleValue();
								sumAllTotal48 = sumAllTotal48 + helVo.getLawLoan().doubleValue();

							}
							if (helVo.getCpt() != null) {
								sum49 = sum49	+ helVo.getCpt().doubleValue();
								sumAllTotal49 = sumAllTotal49 + helVo.getCpt().doubleValue();

							}
							
							if (helVo.getSahakorn() != null) {
								sum50 = sum50	+ helVo.getSahakorn().doubleValue();
								sumAllTotal50 = sumAllTotal50 + helVo.getSahakorn().doubleValue();

							}

							if (helVo.getOomSin() != null) {
								sum53 = sum53	+ helVo.getOomSin().doubleValue();
								sumAllTotal53 = sumAllTotal53 + helVo.getOomSin().doubleValue();

							}
							
							if (helVo.getEtc() != null) {
								sum59 = sum59	+ helVo.getEtc().doubleValue();
								sumAllTotal59 = sumAllTotal59 + helVo.getEtc().doubleValue();

							}
							if (helVo.getKtb() != null) {
								sum60 = sum60	+ helVo.getKtb().doubleValue();
								sumAllTotal60 = sumAllTotal60 + helVo.getKtb().doubleValue();

							}
							if (helVo.getKys() != null) {
								sum61 = sum61	+ helVo.getKys().doubleValue();
								sumAllTotal61 = sumAllTotal61 + helVo.getKys().doubleValue();

							}
							if (helVo.getKro() != null) {
								sum62 = sum62	+ helVo.getKro().doubleValue();
								sumAllTotal62 = sumAllTotal62 + helVo.getKro().doubleValue();

							}
							
						
							
							startRow++;
							if (helRpt.size() == i + 1) {
								sheet.mergeCells(0, startRow, 2, startRow);
								sheet.addCell(new Label(0, startRow, "ÃÇÁ", borderNumber2));
								sheet.addCell(new Label(3, startRow, String.valueOf(df.format(sumTotalNewSalary)),borderNumber2));
								
								sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalSalary02)),borderNumber2));
								sheet.addCell(new Label(5, startRow, String.valueOf(df.format(sumTotalSalary03)),borderNumber2));
								sheet.addCell(new Label(6, startRow, String.valueOf(df.format(sumTotalKlongchev)),borderNumber2));
								sheet.addCell(new Label(7, startRow, String.valueOf(df.format(sum07)),borderNumber2));
								sheet.addCell(new Label(8, startRow, String.valueOf(df.format(sum08)),borderNumber2));
								
								
								sheet.addCell(new Label(9, startRow, String.valueOf(df.format(sumTotalChilds)),borderNumber2));
								
								sheet.addCell(new Label(10, startRow, String.valueOf(df.format(sum11)),borderNumber2));
								sheet.addCell(new Label(11, startRow, String.valueOf(df.format(sum12)),borderNumber2));
								sheet.addCell(new Label(12, startRow, String.valueOf(df.format(sum13)),borderNumber2));
								
								
								sheet.addCell(new Label(13, startRow, String.valueOf(df.format(sum14)),borderNumber2));
								sheet.addCell(new Label(14, startRow, String.valueOf(df.format(sum17)),borderNumber2));
								sheet.addCell(new Label(15, startRow, String.valueOf(df.format(sum19)),borderNumber2));
							
								sheet.addCell(new Label(16, startRow, String.valueOf(df.format(sumTotalNetIncome)),borderNumber2));
								sheet.addCell(new Label(17, startRow, String.valueOf(df.format(sum20)),borderNumber2));
								
								sheet.addCell(new Label(18, startRow, String.valueOf(df.format(sumTotalVinai1Pct)),borderNumber2));
								
								sheet.addCell(new Label(19, startRow, String.valueOf(df.format(sumTotalVinai2)),borderNumber2));
								
								sheet.addCell(new Label(20, startRow, String.valueOf(df.format(sumTotalRefund)),borderNumber2));
								
								sheet.addCell(new Label(21, startRow, String.valueOf(df.format(sum24)),borderNumber2));
								sheet.addCell(new Label(22, startRow, String.valueOf(df.format(sum25)),borderNumber2));
								
								sheet.addCell(new Label(23, startRow, String.valueOf(df.format(sum28)),borderNumber2));
								sheet.addCell(new Label(24, startRow, String.valueOf(df.format(sum29)),borderNumber2));
								
								sheet.addCell(new Label(25, startRow, String.valueOf(df.format(sum32)),borderNumber2));
								sheet.addCell(new Label(26, startRow, String.valueOf(df.format(sumTotalMotor)),borderNumber2));
								sheet.addCell(new Label(27, startRow, String.valueOf(df.format(sumTotalRick)),borderNumber2));
								sheet.addCell(new Label(28, startRow, String.valueOf(df.format(sumTotalStudy)),borderNumber2));
								sheet.addCell(new Label(29, startRow, String.valueOf(df.format(sum42)),borderNumber2));
								
								sheet.addCell(new Label(30, startRow, String.valueOf(df.format(sum46)),borderNumber2));
								sheet.addCell(new Label(31, startRow, String.valueOf(df.format(sum47)),borderNumber2));
								sheet.addCell(new Label(32, startRow, String.valueOf(df.format(sum48)),borderNumber2));
								sheet.addCell(new Label(33, startRow, String.valueOf(df.format(sum49)),borderNumber2));
								sheet.addCell(new Label(34, startRow, String.valueOf(df.format(sum50)),borderNumber2));
								sheet.addCell(new Label(35, startRow, String.valueOf(df.format(sum53)),borderNumber2));
								
								
								sheet.addCell(new Label(36, startRow, String.valueOf(df.format(sum59)),borderNumber2));
								sheet.addCell(new Label(37, startRow, String.valueOf(df.format(sum60)),borderNumber2));
								sheet.addCell(new Label(38, startRow, String.valueOf(df.format(sum61)),borderNumber2));
								sheet.addCell(new Label(39, startRow, String.valueOf(df.format(sum62)),borderNumber2));
								
								sheet.addCell(new Label(40, startRow, String.valueOf(df.format(sumTotalNetDec)),borderNumber2));
								sheet.addCell(new Label(41, startRow, String.valueOf(df.format(sumTotalNet)),borderNumber2));
								sumTotalNewSalary = 0.0;
								sumTotalSalary02 = 0.0;
								sumTotalSalary03 = 0.0;
								sumTotalSalary = 0.0;
								sumTotalChilds = 0.0;
								sumTotalKlongchev = 0.0;
								sumTotalNetIncome = 0.0;
								sumTotalVinai1Pct = 0.0;

								sumTotalVinai2 = 0.0;
								sumTotalRefund = 0.0;
								sumTotalCar = 0.0;
								sumTotalMotor = 0.0;
								sumTotalRick = 0.0;
								sumTotalStudy = 0.0;
								sumTotalNetDec = 0.0;
								sumTotalNet = 0.0;
								sumTotalOomsin = 0.0;
								sumTotalLawLoan = 0.0;
								sumTotalPangLoan = 0.0;
								
								sum07 = 0.0;
								sum08 = 0.0;
								
								sum11 = 0.0;
								sum12 = 0.0;
								sum13 = 0.0;
								sum14 = 0.0;
								sum17 = 0.0;
								sum19 = 0.0;
								sum20 = 0.0;
								sum24 = 0.0;
								sum25 = 0.0;
								sum28 = 0.0;
								
								sum29 = 0.0;
								sum32 = 0.0;
								sum42 = 0.0;
								sum46 = 0.0;
								sum47 = 0.0;
								sum48 = 0.0;
								sum49 = 0.0;
								sum50 = 0.0;
								sum53 = 0.0;
								
								sum59 = 0.0;
								sum60 = 0.0;
								sum61 = 0.0;
								sum62 = 0.0;
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
							
							
							sheet.addCell(new Label(4, startRow, String.valueOf(df.format(helVo.getSalary02())),borderNumber));
							sheet.addCell(new Label(5, startRow, String.valueOf(df.format(helVo.getSalary03())),borderNumber));
							sheet.addCell(new Label(6, startRow, String.valueOf(df.format(helVo.getKlongchev())),borderNumber));
							
							sheet.addCell(new Label(7, startRow, String.valueOf(df.format(helVo.getYangchev())),borderNumber));
							sheet.addCell(new Label(8, startRow, String.valueOf(df.format(helVo.getSpec())),borderNumber));
							
							
							sheet.addCell(new Label(9, startRow, String.valueOf(df.format(helVo.getChilds())),borderNumber));
							sheet.addCell(new Label(10, startRow, String.valueOf(df.format(helVo.getOt())),borderNumber));
							sheet.addCell(new Label(11, startRow, String.valueOf(df.format(helVo.getPremium())),borderNumber));
							sheet.addCell(new Label(12, startRow, String.valueOf(df.format(helVo.getHealth())),borderNumber));
							sheet.addCell(new Label(13, startRow, String.valueOf(df.format(helVo.getRisk())),borderNumber));
							
							sheet.addCell(new Label(14, startRow, String.valueOf(df.format(helVo.getFeetax())),borderNumber));
							sheet.addCell(new Label(15, startRow, String.valueOf(df.format(helVo.getStep())),borderNumber));
						
							sheet.addCell(new Label(16, startRow, String.valueOf(df.format(helVo.getNetIncome())),borderNumber));
							
							sheet.addCell(new Label(17, startRow, String.valueOf(df.format(helVo.getTax())),borderNumber));
							sheet.addCell(new Label(18, startRow, String.valueOf(df.format(helVo.getVinai1Pct())),borderNumber));
							
							sheet.addCell(new Label(19, startRow, String.valueOf(df.format(helVo.getVinai2())),borderNumber));
							
							sheet.addCell(new Label(20, startRow, String.valueOf(df.format(helVo.getRefund())),borderNumber));
							sheet.addCell(new Label(21, startRow, String.valueOf(df.format(helVo.getPvf())),borderNumber));
							sheet.addCell(new Label(22, startRow, String.valueOf(df.format(helVo.getPangLoan())),borderNumber));
							
							sheet.addCell(new Label(23, startRow, String.valueOf(df.format(helVo.getHor())),borderNumber));
							
							sheet.addCell(new Label(24, startRow, String.valueOf(df.format(helVo.getCar())),borderNumber));
							sheet.addCell(new Label(25, startRow, String.valueOf(df.format(helVo.getLoanPost())),borderNumber));
							
							sheet.addCell(new Label(26, startRow, String.valueOf(df.format(helVo.getMotor())),borderNumber));
							sheet.addCell(new Label(27, startRow, String.valueOf(df.format(helVo.getRick())),borderNumber));
							sheet.addCell(new Label(28, startRow, String.valueOf(df.format(helVo.getStudy())),borderNumber));
							
							sheet.addCell(new Label(29, startRow, String.valueOf(df.format(helVo.getLoan())),borderNumber));
							sheet.addCell(new Label(30, startRow, String.valueOf(df.format(helVo.getHomegro())),borderNumber));
							sheet.addCell(new Label(31, startRow, String.valueOf(df.format(helVo.getHomektb())),borderNumber));
							sheet.addCell(new Label(32, startRow, String.valueOf(df.format(helVo.getLawLoan())),borderNumber));
							sheet.addCell(new Label(33, startRow, String.valueOf(df.format(helVo.getCpt())),borderNumber));
							sheet.addCell(new Label(34, startRow, String.valueOf(df.format(helVo.getSahakorn())),borderNumber));
							sheet.addCell(new Label(35, startRow, String.valueOf(df.format(helVo.getOomSin())),borderNumber));
							sheet.addCell(new Label(36, startRow, String.valueOf(df.format(helVo.getEtc())),borderNumber));
							sheet.addCell(new Label(37, startRow, String.valueOf(df.format(helVo.getKtb())),borderNumber));
							sheet.addCell(new Label(38, startRow, String.valueOf(df.format(helVo.getKys())),borderNumber));
							sheet.addCell(new Label(39, startRow, String.valueOf(df.format(helVo.getKro())),borderNumber));
							
							
							sheet.addCell(new Label(40, startRow, String.valueOf(df.format(helVo.getNetDec())),borderNumber));
							sheet.addCell(new Label(41, startRow, String.valueOf(df.format(helVo.getNet())),borderNumber));
							
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
							if (helVo.getYangchev() != null) {
								sum07 = sum07	+ helVo.getYangchev().doubleValue();
								sumAllTotal07 = sumAllTotal07 + helVo.getYangchev().doubleValue();

							}
							
							if (helVo.getSpec() != null) {
								sum08 = sum08	+ helVo.getSpec().doubleValue();
								sumAllTotal08 = sumAllTotal08 + helVo.getSpec().doubleValue();

							}
							
							if (helVo.getOt() != null) {
								sum11 = sum11	+ helVo.getOt().doubleValue();
								sumAllTotal11 = sumAllTotal11 + helVo.getOt().doubleValue();

							}
							
							if (helVo.getPremium() != null) {
								sum12 = sum12	+ helVo.getPremium().doubleValue();
								sumAllTotal12 = sumAllTotal12 + helVo.getPremium().doubleValue();

							}
							
							if (helVo.getHealth() != null) {
								sum13 = sum13	+ helVo.getHealth().doubleValue();
								sumAllTotal13 = sumAllTotal13 + helVo.getHealth().doubleValue();

							}
							
							if (helVo.getRisk() != null) {
								sum14 = sum14	+ helVo.getRisk().doubleValue();
								sumAllTotal14 = sumAllTotal14 + helVo.getRisk().doubleValue();

							}
							
							
							
							if (helVo.getFeetax() != null) {
								sum17 = sum17	+ helVo.getFeetax().doubleValue();
								sumAllTotal17 = sumAllTotal17 + helVo.getFeetax().doubleValue();

							}
							
							if (helVo.getStep() != null) {
								sum19 = sum19	+ helVo.getStep().doubleValue();
								sumAllTotal19 = sumAllTotal19 + helVo.getStep().doubleValue();

							}
							
							if (helVo.getTax() != null) {
								sum20 = sum20	+ helVo.getTax().doubleValue();
								sumAllTotal20 = sumAllTotal20 + helVo.getTax().doubleValue();

							}
							
							if (helVo.getPvf() != null) {
								sum24 = sum24	+ helVo.getPvf().doubleValue();
								sumAllTotal24 = sumAllTotal24 + helVo.getPvf().doubleValue();

							}
							
							
							if (helVo.getPangLoan() != null) {
								sum25 = sum25	+ helVo.getPangLoan().doubleValue();
								sumAllTotal25 = sumAllTotal25 + helVo.getPangLoan().doubleValue();

							}
							
							if (helVo.getHor() != null) {
								sum28 = sum28	+ helVo.getHor().doubleValue();
								sumAllTotal28 = sumAllTotal28 + helVo.getHor().doubleValue();

							}
							
							if (helVo.getCar() != null) {
								sum29 = sum29	+ helVo.getCar().doubleValue();
								sumAllTotal29 = sumAllTotal29 + helVo.getCar().doubleValue();

							}
							
							
							if (helVo.getLoanPost() != null) {
								sum32 = sum32	+ helVo.getLoanPost().doubleValue();
								sumAllTotal32 = sumAllTotal32 + helVo.getLoanPost().doubleValue();

							}
							
							
							if (helVo.getLoan() != null) {
								sum42 = sum42	+ helVo.getLoan().doubleValue();
								sumAllTotal42 = sumAllTotal42 + helVo.getLoan().doubleValue();

							}
							if (helVo.getHomegro() != null) {
								sum46 = sum46	+ helVo.getHomegro().doubleValue();
								sumAllTotal46 = sumAllTotal46 + helVo.getHomegro().doubleValue();

							}
							if (helVo.getHomektb() != null) {
								sum47 = sum47	+ helVo.getHomektb().doubleValue();
								sumAllTotal47 = sumAllTotal47 + helVo.getHomektb().doubleValue();

							}
							if (helVo.getLawLoan() != null) {
								sum48 = sum48	+ helVo.getLawLoan().doubleValue();
								sumAllTotal48 = sumAllTotal48 + helVo.getLawLoan().doubleValue();

							}
							if (helVo.getCpt() != null) {
								sum49 = sum49	+ helVo.getCpt().doubleValue();
								sumAllTotal49 = sumAllTotal49 + helVo.getCpt().doubleValue();

							}
							
							if (helVo.getSahakorn() != null) {
								sum50 = sum50	+ helVo.getSahakorn().doubleValue();
								sumAllTotal50 = sumAllTotal50 + helVo.getSahakorn().doubleValue();

							}
							

							if (helVo.getOomSin() != null) {
								sum53 = sum53	+ helVo.getOomSin().doubleValue();
								sumAllTotal53 = sumAllTotal53 + helVo.getOomSin().doubleValue();

							}
							
							if (helVo.getEtc() != null) {
								sum59 = sum59	+ helVo.getEtc().doubleValue();
								sumAllTotal59 = sumAllTotal59 + helVo.getEtc().doubleValue();

							}
							if (helVo.getKtb() != null) {
								sum60 = sum60	+ helVo.getKtb().doubleValue();
								sumAllTotal60 = sumAllTotal60 + helVo.getKtb().doubleValue();

							}
							if (helVo.getKys() != null) {
								sum61 = sum61	+ helVo.getKys().doubleValue();
								sumAllTotal61 = sumAllTotal61 + helVo.getKys().doubleValue();

							}
							if (helVo.getKro() != null) {
								sum62 = sum62	+ helVo.getKro().doubleValue();
								sumAllTotal62 = sumAllTotal62 + helVo.getKro().doubleValue();

							}
							
						
							sheet.setRowView(startRow, 320);
							startRow++;
							if (helRpt.size() == i + 1) {
								sheet.mergeCells(0, startRow, 2, startRow);
								sheet.addCell(new Label(0, startRow, "ÃÇÁ", borderNumber2));
								sheet.addCell(new Label(3, startRow, String.valueOf(df.format(sumTotalNewSalary)),borderNumber2));
								
								sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalSalary02)),borderNumber2));
								sheet.addCell(new Label(5, startRow, String.valueOf(df.format(sumTotalSalary03)),borderNumber2));
								sheet.addCell(new Label(6, startRow, String.valueOf(df.format(sumTotalKlongchev)),borderNumber2));
								sheet.addCell(new Label(7, startRow, String.valueOf(df.format(sum07)),borderNumber2));
								sheet.addCell(new Label(8, startRow, String.valueOf(df.format(sum08)),borderNumber2));
								
								
								sheet.addCell(new Label(9, startRow, String.valueOf(df.format(sumTotalChilds)),borderNumber2));
								
								sheet.addCell(new Label(10, startRow, String.valueOf(df.format(sum11)),borderNumber2));
								sheet.addCell(new Label(11, startRow, String.valueOf(df.format(sum12)),borderNumber2));
								sheet.addCell(new Label(12, startRow, String.valueOf(df.format(sum13)),borderNumber2));
								sheet.addCell(new Label(13, startRow, String.valueOf(df.format(sum14)),borderNumber2));
								sheet.addCell(new Label(14, startRow, String.valueOf(df.format(sum17)),borderNumber2));
								sheet.addCell(new Label(15, startRow, String.valueOf(df.format(sum19)),borderNumber2));
							
								sheet.addCell(new Label(16, startRow, String.valueOf(df.format(sumTotalNetIncome)),borderNumber2));
								sheet.addCell(new Label(17, startRow, String.valueOf(df.format(sum20)),borderNumber2));
								
								sheet.addCell(new Label(18, startRow, String.valueOf(df.format(sumTotalVinai1Pct)),borderNumber2));
								
								sheet.addCell(new Label(19, startRow, String.valueOf(df.format(sumTotalVinai2)),borderNumber2));
								
								sheet.addCell(new Label(20, startRow, String.valueOf(df.format(sumTotalRefund)),borderNumber2));
								
								sheet.addCell(new Label(21, startRow, String.valueOf(df.format(sum24)),borderNumber2));
								sheet.addCell(new Label(22, startRow, String.valueOf(df.format(sum25)),borderNumber2));
								
								sheet.addCell(new Label(23, startRow, String.valueOf(df.format(sum28)),borderNumber2));
								sheet.addCell(new Label(24, startRow, String.valueOf(df.format(sum29)),borderNumber2));
								
								sheet.addCell(new Label(25, startRow, String.valueOf(df.format(sum32)),borderNumber2));
								sheet.addCell(new Label(26, startRow, String.valueOf(df.format(sumTotalMotor)),borderNumber2));
								sheet.addCell(new Label(27, startRow, String.valueOf(df.format(sumTotalRick)),borderNumber2));
								sheet.addCell(new Label(28, startRow, String.valueOf(df.format(sumTotalStudy)),borderNumber2));
								sheet.addCell(new Label(29, startRow, String.valueOf(df.format(sum42)),borderNumber2));
								
								sheet.addCell(new Label(30, startRow, String.valueOf(df.format(sum46)),borderNumber2));
								sheet.addCell(new Label(31, startRow, String.valueOf(df.format(sum47)),borderNumber2));
								sheet.addCell(new Label(32, startRow, String.valueOf(df.format(sum48)),borderNumber2));
								sheet.addCell(new Label(33, startRow, String.valueOf(df.format(sum49)),borderNumber2));
								sheet.addCell(new Label(34, startRow, String.valueOf(df.format(sum50)),borderNumber2));
								sheet.addCell(new Label(35, startRow, String.valueOf(df.format(sum53)),borderNumber2));
								
								
								sheet.addCell(new Label(36, startRow, String.valueOf(df.format(sum59)),borderNumber2));
								sheet.addCell(new Label(37, startRow, String.valueOf(df.format(sum60)),borderNumber2));
								sheet.addCell(new Label(38, startRow, String.valueOf(df.format(sum61)),borderNumber2));
								sheet.addCell(new Label(39, startRow, String.valueOf(df.format(sum62)),borderNumber2));
								
								sheet.addCell(new Label(40, startRow, String.valueOf(df.format(sumTotalNetDec)),borderNumber2));
								sheet.addCell(new Label(41, startRow, String.valueOf(df.format(sumTotalNet)),borderNumber2));
								
								sumTotalNewSalary = 0.0;
								sumTotalSalary02 = 0.0;
								sumTotalSalary03 = 0.0;
								sumTotalSalary = 0.0;
								sumTotalChilds = 0.0;
								sumTotalKlongchev = 0.0;
								sumTotalNetIncome = 0.0;
								sumTotalVinai1Pct = 0.0;
					
								sumTotalVinai2 = 0.0;
								sumTotalRefund = 0.0;
								sumTotalCar = 0.0;
								sumTotalMotor = 0.0;
								sumTotalRick = 0.0;
								sumTotalStudy = 0.0;
								sumTotalNetDec = 0.0;
								sumTotalNet = 0.0;
								sumTotalOomsin  =0.0;
								sumTotalPangLoan = 0.0;
								sumTotalLawLoan = 0.0;
								
								sum07 = 0.0;
								sum08 = 0.0;
								
								sum11 = 0.0;
								sum12 = 0.0;
								sum13 = 0.0;
								sum14 = 0.0;
								sum17 = 0.0;
								sum19 = 0.0;
								sum20 = 0.0;
								sum24 = 0.0;
								sum25 = 0.0;
								sum28 = 0.0;
								
								sum29 = 0.0;
								sum32 = 0.0;
								sum42 = 0.0;
								sum46 = 0.0;
								sum47 = 0.0;
								sum48 = 0.0;
								sum49 = 0.0;
								sum50 = 0.0;
								sum53 = 0.0;
								
								sum59 = 0.0;
								sum60 = 0.0;
								sum61 = 0.0;
								sum62 = 0.0;
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
						
						sheet.addCell(new Label(4, startRow, String.valueOf(df.format(helVo.getSalary02())),borderNumber));
						sheet.addCell(new Label(5, startRow, String.valueOf(df.format(helVo.getSalary03())),borderNumber));
						sheet.addCell(new Label(6, startRow, String.valueOf(df.format(helVo.getKlongchev())),borderNumber));
						
						sheet.addCell(new Label(7, startRow, String.valueOf(df.format(helVo.getYangchev())),borderNumber));
						sheet.addCell(new Label(8, startRow, String.valueOf(df.format(helVo.getSpec())),borderNumber));
						
						
						sheet.addCell(new Label(9, startRow, String.valueOf(df.format(helVo.getChilds())),borderNumber));
						sheet.addCell(new Label(10, startRow, String.valueOf(df.format(helVo.getOt())),borderNumber));
						sheet.addCell(new Label(11, startRow, String.valueOf(df.format(helVo.getPremium())),borderNumber));
						sheet.addCell(new Label(12, startRow, String.valueOf(df.format(helVo.getHealth())),borderNumber));
						sheet.addCell(new Label(13, startRow, String.valueOf(df.format(helVo.getRisk())),borderNumber));
						
						sheet.addCell(new Label(14, startRow, String.valueOf(df.format(helVo.getFeetax())),borderNumber));
						sheet.addCell(new Label(15, startRow, String.valueOf(df.format(helVo.getStep())),borderNumber));
					
						sheet.addCell(new Label(16, startRow, String.valueOf(df.format(helVo.getNetIncome())),borderNumber));
						
						sheet.addCell(new Label(17, startRow, String.valueOf(df.format(helVo.getTax())),borderNumber));
						sheet.addCell(new Label(18, startRow, String.valueOf(df.format(helVo.getVinai1Pct())),borderNumber));
						
						sheet.addCell(new Label(19, startRow, String.valueOf(df.format(helVo.getVinai2())),borderNumber));
						
						sheet.addCell(new Label(20, startRow, String.valueOf(df.format(helVo.getRefund())),borderNumber));
						sheet.addCell(new Label(21, startRow, String.valueOf(df.format(helVo.getPvf())),borderNumber));
						sheet.addCell(new Label(22, startRow, String.valueOf(df.format(helVo.getPangLoan())),borderNumber));
						
						sheet.addCell(new Label(23, startRow, String.valueOf(df.format(helVo.getHor())),borderNumber));
						
						sheet.addCell(new Label(24, startRow, String.valueOf(df.format(helVo.getCar())),borderNumber));
						sheet.addCell(new Label(25, startRow, String.valueOf(df.format(helVo.getLoanPost())),borderNumber));
						
						sheet.addCell(new Label(26, startRow, String.valueOf(df.format(helVo.getMotor())),borderNumber));
						sheet.addCell(new Label(27, startRow, String.valueOf(df.format(helVo.getRick())),borderNumber));
						sheet.addCell(new Label(28, startRow, String.valueOf(df.format(helVo.getStudy())),borderNumber));
						
						sheet.addCell(new Label(29, startRow, String.valueOf(df.format(helVo.getLoan())),borderNumber));
						sheet.addCell(new Label(30, startRow, String.valueOf(df.format(helVo.getHomegro())),borderNumber));
						sheet.addCell(new Label(31, startRow, String.valueOf(df.format(helVo.getHomektb())),borderNumber));
						sheet.addCell(new Label(32, startRow, String.valueOf(df.format(helVo.getLawLoan())),borderNumber));
						sheet.addCell(new Label(33, startRow, String.valueOf(df.format(helVo.getCpt())),borderNumber));
						sheet.addCell(new Label(34, startRow, String.valueOf(df.format(helVo.getSahakorn())),borderNumber));
						sheet.addCell(new Label(35, startRow, String.valueOf(df.format(helVo.getOomSin())),borderNumber));
						sheet.addCell(new Label(36, startRow, String.valueOf(df.format(helVo.getEtc())),borderNumber));
						sheet.addCell(new Label(37, startRow, String.valueOf(df.format(helVo.getKtb())),borderNumber));
						sheet.addCell(new Label(38, startRow, String.valueOf(df.format(helVo.getKys())),borderNumber));
						sheet.addCell(new Label(39, startRow, String.valueOf(df.format(helVo.getKro())),borderNumber));
						
						
						sheet.addCell(new Label(40, startRow, String.valueOf(df.format(helVo.getNetDec())),borderNumber));
						sheet.addCell(new Label(41, startRow, String.valueOf(df.format(helVo.getNet())),borderNumber));
						
						
						
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
						if (helVo.getYangchev() != null) {
							sum07 = sum07	+ helVo.getYangchev().doubleValue();
							sumAllTotal07 = sumAllTotal07 + helVo.getYangchev().doubleValue();

						}
						
						if (helVo.getSpec() != null) {
							sum08 = sum08	+ helVo.getSpec().doubleValue();
							sumAllTotal08 = sumAllTotal08 + helVo.getSpec().doubleValue();

						}
						
						if (helVo.getOt() != null) {
							sum11 = sum11	+ helVo.getOt().doubleValue();
							sumAllTotal11 = sumAllTotal11 + helVo.getOt().doubleValue();

						}
						
						if (helVo.getPremium() != null) {
							sum12 = sum12	+ helVo.getPremium().doubleValue();
							sumAllTotal12 = sumAllTotal12 + helVo.getPremium().doubleValue();

						}
						
						if (helVo.getHealth() != null) {
							sum13 = sum13	+ helVo.getHealth().doubleValue();
							sumAllTotal13 = sumAllTotal13 + helVo.getHealth().doubleValue();

						}
						
						
						if (helVo.getRisk() != null) {
							sum14 = sum14	+ helVo.getRisk().doubleValue();
							sumAllTotal14 = sumAllTotal14 + helVo.getRisk().doubleValue();

						}
						if (helVo.getFeetax() != null) {
							sum17 = sum17	+ helVo.getFeetax().doubleValue();
							sumAllTotal17 = sumAllTotal17 + helVo.getFeetax().doubleValue();

						}
						
						if (helVo.getStep() != null) {
							sum19 = sum19	+ helVo.getStep().doubleValue();
							sumAllTotal19 = sumAllTotal19 + helVo.getStep().doubleValue();

						}
						
						if (helVo.getTax() != null) {
							sum20 = sum20	+ helVo.getTax().doubleValue();
							sumAllTotal20 = sumAllTotal20 + helVo.getTax().doubleValue();

						}
						
						if (helVo.getPvf() != null) {
							sum24 = sum24	+ helVo.getPvf().doubleValue();
							sumAllTotal24 = sumAllTotal24 + helVo.getPvf().doubleValue();

						}
						
						
						if (helVo.getPangLoan() != null) {
							sum25 = sum25	+ helVo.getPangLoan().doubleValue();
							sumAllTotal25 = sumAllTotal25 + helVo.getPangLoan().doubleValue();

						}
						
						if (helVo.getHor() != null) {
							sum28 = sum28	+ helVo.getHor().doubleValue();
							sumAllTotal28 = sumAllTotal28 + helVo.getHor().doubleValue();

						}
						
						if (helVo.getCar() != null) {
							sum29 = sum29	+ helVo.getCar().doubleValue();
							sumAllTotal29 = sumAllTotal29 + helVo.getCar().doubleValue();

						}
						
						
						if (helVo.getLoanPost() != null) {
							sum32 = sum32	+ helVo.getLoanPost().doubleValue();
							sumAllTotal32 = sumAllTotal32 + helVo.getLoanPost().doubleValue();

						}
						
						
						if (helVo.getLoan() != null) {
							sum42 = sum42	+ helVo.getLoan().doubleValue();
							sumAllTotal42 = sumAllTotal42 + helVo.getLoan().doubleValue();

						}
						if (helVo.getHomegro() != null) {
							sum46 = sum46	+ helVo.getHomegro().doubleValue();
							sumAllTotal46 = sumAllTotal46 + helVo.getHomegro().doubleValue();

						}
						if (helVo.getHomektb() != null) {
							sum47 = sum47	+ helVo.getHomektb().doubleValue();
							sumAllTotal47 = sumAllTotal47 + helVo.getHomektb().doubleValue();

						}
						if (helVo.getLawLoan() != null) {
							sum48 = sum48	+ helVo.getLawLoan().doubleValue();
							sumAllTotal48 = sumAllTotal48 + helVo.getLawLoan().doubleValue();

						}
						if (helVo.getCpt() != null) {
							sum49 = sum49	+ helVo.getCpt().doubleValue();
							sumAllTotal49 = sumAllTotal49 + helVo.getCpt().doubleValue();

						}
						
						if (helVo.getSahakorn() != null) {
							sum50 = sum50	+ helVo.getSahakorn().doubleValue();
							sumAllTotal50 = sumAllTotal50 + helVo.getSahakorn().doubleValue();

						}
						
						
						if (helVo.getOomSin() != null) {
							sum53 = sum53	+ helVo.getOomSin().doubleValue();
							sumAllTotal53 = sumAllTotal53 + helVo.getOomSin().doubleValue();

						}
						
						
						if (helVo.getEtc() != null) {
							sum59 = sum59	+ helVo.getEtc().doubleValue();
							sumAllTotal59 = sumAllTotal59 + helVo.getEtc().doubleValue();

						}
						if (helVo.getKtb() != null) {
							sum60 = sum60	+ helVo.getKtb().doubleValue();
							sumAllTotal60 = sumAllTotal60 + helVo.getKtb().doubleValue();

						}
						if (helVo.getKys() != null) {
							sum61 = sum61	+ helVo.getKys().doubleValue();
							sumAllTotal61 = sumAllTotal61 + helVo.getKys().doubleValue();

						}
						if (helVo.getKro() != null) {
							sum62 = sum62	+ helVo.getKro().doubleValue();
							sumAllTotal62 = sumAllTotal62 + helVo.getKro().doubleValue();

						}
						
					
						
						sheet.setRowView(startRow, 320);
						startRow++;
						if (helRpt.size() == i + 1) {
							sheet.mergeCells(0, startRow, 2, startRow);
							sheet.addCell(new Label(0, startRow, "ÃÇÁ", borderNumber2));
							sheet.addCell(new Label(3, startRow, String.valueOf(df.format(sumTotalNewSalary)),borderNumber2));
							sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalSalary02)),borderNumber2));
							sheet.addCell(new Label(5, startRow, String.valueOf(df.format(sumTotalSalary03)),borderNumber2));
							sheet.addCell(new Label(6, startRow, String.valueOf(df.format(sumTotalKlongchev)),borderNumber2));
							sheet.addCell(new Label(7, startRow, String.valueOf(df.format(sum07)),borderNumber2));
							sheet.addCell(new Label(8, startRow, String.valueOf(df.format(sum08)),borderNumber2));
							
							
							sheet.addCell(new Label(9, startRow, String.valueOf(df.format(sumTotalChilds)),borderNumber2));
							
							sheet.addCell(new Label(10, startRow, String.valueOf(df.format(sum11)),borderNumber2));
							sheet.addCell(new Label(11, startRow, String.valueOf(df.format(sum12)),borderNumber2));
							sheet.addCell(new Label(12, startRow, String.valueOf(df.format(sum13)),borderNumber2));
							
							sheet.addCell(new Label(13, startRow, String.valueOf(df.format(sum14)),borderNumber2));
							sheet.addCell(new Label(14, startRow, String.valueOf(df.format(sum17)),borderNumber2));
							sheet.addCell(new Label(15, startRow, String.valueOf(df.format(sum19)),borderNumber2));
						
							sheet.addCell(new Label(16, startRow, String.valueOf(df.format(sumTotalNetIncome)),borderNumber2));
							sheet.addCell(new Label(17, startRow, String.valueOf(df.format(sum20)),borderNumber2));
							
							sheet.addCell(new Label(18, startRow, String.valueOf(df.format(sumTotalVinai1Pct)),borderNumber2));
							
							sheet.addCell(new Label(19, startRow, String.valueOf(df.format(sumTotalVinai2)),borderNumber2));
							
							sheet.addCell(new Label(20, startRow, String.valueOf(df.format(sumTotalRefund)),borderNumber2));
							
							sheet.addCell(new Label(21, startRow, String.valueOf(df.format(sum24)),borderNumber2));
							sheet.addCell(new Label(22, startRow, String.valueOf(df.format(sum25)),borderNumber2));
							
							sheet.addCell(new Label(23, startRow, String.valueOf(df.format(sum28)),borderNumber2));
							sheet.addCell(new Label(24, startRow, String.valueOf(df.format(sum29)),borderNumber2));
							
							sheet.addCell(new Label(25, startRow, String.valueOf(df.format(sum32)),borderNumber2));
							sheet.addCell(new Label(26, startRow, String.valueOf(df.format(sumTotalMotor)),borderNumber2));
							sheet.addCell(new Label(27, startRow, String.valueOf(df.format(sumTotalRick)),borderNumber2));
							sheet.addCell(new Label(28, startRow, String.valueOf(df.format(sumTotalStudy)),borderNumber2));
							sheet.addCell(new Label(29, startRow, String.valueOf(df.format(sum42)),borderNumber2));
							
							sheet.addCell(new Label(30, startRow, String.valueOf(df.format(sum46)),borderNumber2));
							sheet.addCell(new Label(31, startRow, String.valueOf(df.format(sum47)),borderNumber2));
							sheet.addCell(new Label(32, startRow, String.valueOf(df.format(sum48)),borderNumber2));
							sheet.addCell(new Label(33, startRow, String.valueOf(df.format(sum49)),borderNumber2));
							sheet.addCell(new Label(34, startRow, String.valueOf(df.format(sum50)),borderNumber2));
							sheet.addCell(new Label(35, startRow, String.valueOf(df.format(sum53)),borderNumber2));
							
							
							sheet.addCell(new Label(36, startRow, String.valueOf(df.format(sum59)),borderNumber2));
							sheet.addCell(new Label(37, startRow, String.valueOf(df.format(sum60)),borderNumber2));
							sheet.addCell(new Label(38, startRow, String.valueOf(df.format(sum61)),borderNumber2));
							sheet.addCell(new Label(39, startRow, String.valueOf(df.format(sum62)),borderNumber2));
							
							sheet.addCell(new Label(40, startRow, String.valueOf(df.format(sumTotalNetDec)),borderNumber2));
							sheet.addCell(new Label(41, startRow, String.valueOf(df.format(sumTotalNet)),borderNumber2));
						
							
							sumTotalNewSalary = 0.0;
							sumTotalSalary02 = 0.0;
							sumTotalSalary03 = 0.0;
							sumTotalSalary = 0.0;
							sumTotalChilds = 0.0;
							sumTotalKlongchev = 0.0;
							sumTotalNetIncome = 0.0;
							sumTotalVinai1Pct = 0.0;
	
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
							sum07 = 0.0;
							sum08 = 0.0;
							
							sum11 = 0.0;
							sum12 = 0.0;
							sum13 = 0.0;
							sum14 = 0.0;
							sum17 = 0.0;
							sum19 = 0.0;
							sum20 = 0.0;
							sum24 = 0.0;
							sum25 = 0.0;
							sum28 = 0.0;
							
							sum29 = 0.0;
							sum32 = 0.0;
							sum42 = 0.0;
							sum46 = 0.0;
							sum47 = 0.0;
							sum48 = 0.0;
							sum49 = 0.0;
							sum50 = 0.0;
							sum53 = 0.0;
							
							sum59 = 0.0;
							sum60 = 0.0;
							sum61 = 0.0;
							sum62 = 0.0;
							startRow++;
						}
						tmpCode = helVo.getAreaCode();
					}
				}

				sheet.mergeCells(0, startRow, 2, startRow);
				sheet.addCell(new Label(0, startRow, "ÃÇÁ·Ñé§ËÁ´", borderNumber2));
				sheet.addCell(new Label(3, startRow,String.valueOf(df.format(new Double(sumAllTotalNewSalary))),borderNumber2));
				//sheet.addCell(new Label(4, startRow,String.valueOf(df.format(new Double(sumAllTotalSalary))),borderNumber2));
				sheet.addCell(new Label(4, startRow, String.valueOf(df.format(new Double(sumAllTotalSalary02))),borderNumber2));
				sheet.addCell(new Label(5, startRow, String.valueOf(df.format(new Double(sumAllTotalSalary03))),borderNumber2));
				sheet.addCell(new Label(6, startRow,String.valueOf(df.format(new Double(sumAllTotalKlongchev))),borderNumber2));
				
				sheet.addCell(new Label(7, startRow,String.valueOf(df.format(new Double(sumAllTotal07))),borderNumber2));
				sheet.addCell(new Label(8, startRow,String.valueOf(df.format(new Double(sumAllTotal08))),borderNumber2));
				
				sheet.addCell(new Label(9, startRow,String.valueOf(df.format(new Double(sumAllTotalChilds))),borderNumber2));
				
				sheet.addCell(new Label(10, startRow,String.valueOf(df.format(new Double(sumAllTotal11))),borderNumber2));
				sheet.addCell(new Label(11, startRow,String.valueOf(df.format(new Double(sumAllTotal12))),borderNumber2));
				sheet.addCell(new Label(12, startRow,String.valueOf(df.format(new Double(sumAllTotal13))),borderNumber2));
				sheet.addCell(new Label(13, startRow,String.valueOf(df.format(new Double(sumAllTotal14))),borderNumber2));
				
				sheet.addCell(new Label(14, startRow,String.valueOf(df.format(new Double(sumAllTotal17))),borderNumber2));
				sheet.addCell(new Label(15, startRow,String.valueOf(df.format(new Double(sumAllTotal19))),borderNumber2));
				
				sheet.addCell(new Label(16, startRow, String.valueOf(df.format(new Double(sumAllTotalNetIncome))),borderNumber2));
				
				sheet.addCell(new Label(17, startRow,String.valueOf(df.format(new Double(sumAllTotal20))),borderNumber2));
				
				sheet.addCell(new Label(18, startRow, String.valueOf(df.format(new Double(sumAllTotalVinai1Pct))),borderNumber2));
				sheet.addCell(new Label(19, startRow, String.valueOf(df.format(new Double(sumAllTotalVinai2))),borderNumber2));
				sheet.addCell(new Label(20, startRow, String.valueOf(df.format(new Double(sumAllTotalRefund))),borderNumber2));
				
				sheet.addCell(new Label(21, startRow,String.valueOf(df.format(new Double(sumAllTotal24))),borderNumber2));
				sheet.addCell(new Label(22, startRow,String.valueOf(df.format(new Double(sumAllTotal25))),borderNumber2));
				sheet.addCell(new Label(23, startRow,String.valueOf(df.format(new Double(sumAllTotal28))),borderNumber2));
				sheet.addCell(new Label(24, startRow,String.valueOf(df.format(new Double(sumAllTotal29))),borderNumber2));
				sheet.addCell(new Label(25, startRow, String.valueOf(df.format(new Double(sumAllTotal32))),borderNumber2));
				sheet.addCell(new Label(26, startRow, String.valueOf(df.format(new Double(sumAllTotalMotor))),borderNumber2));
				sheet.addCell(new Label(27, startRow, String.valueOf(df.format(new Double(sumAllTotalRick))),borderNumber2));
				
				
				sheet.addCell(new Label(28, startRow, String.valueOf(df.format(new Double(sumAllTotalStudy))),borderNumber2));
				
				sheet.addCell(new Label(29, startRow, String.valueOf(df.format(new Double(sumAllTotal42))),borderNumber2));
				
				sheet.addCell(new Label(30, startRow, String.valueOf(df.format(new Double(sumAllTotal46))),borderNumber2));
				sheet.addCell(new Label(31, startRow, String.valueOf(df.format(new Double(sumAllTotal47))),borderNumber2));
				sheet.addCell(new Label(32, startRow, String.valueOf(df.format(new Double(sumAllTotal48))),borderNumber2));
				sheet.addCell(new Label(33, startRow, String.valueOf(df.format(new Double(sumAllTotal49))),borderNumber2));
				sheet.addCell(new Label(34, startRow, String.valueOf(df.format(new Double(sumAllTotal50))),borderNumber2));
				sheet.addCell(new Label(35, startRow, String.valueOf(df.format(new Double(sumAllTotal53))),borderNumber2));
				sheet.addCell(new Label(36, startRow, String.valueOf(df.format(new Double(sumAllTotal59))),borderNumber2));
				sheet.addCell(new Label(37, startRow, String.valueOf(df.format(new Double(sumAllTotal60))),borderNumber2));
				sheet.addCell(new Label(38, startRow, String.valueOf(df.format(new Double(sumAllTotal61))),borderNumber2));
				sheet.addCell(new Label(39, startRow, String.valueOf(df.format(new Double(sumAllTotal62))),borderNumber2));
				
				
				
				sheet.addCell(new Label(40, startRow, String.valueOf(df.format(new Double(sumAllTotalNetDec))),borderNumber2));
				sheet.addCell(new Label(41, startRow, String.valueOf(df.format(new Double(sumAllTotalNet))),borderNumber2));
				
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
				sheet.addCell(new Label(24, startRow, "", borderEndLine));
				sheet.addCell(new Label(25, startRow, "", borderEndLine));
				sheet.addCell(new Label(26, startRow, "", borderEndLine));
				sheet.addCell(new Label(27, startRow, "", borderEndLine));
				sheet.addCell(new Label(28, startRow, "", borderEndLine));
				sheet.addCell(new Label(29, startRow, "", borderEndLine));
				sheet.addCell(new Label(30, startRow, "", borderEndLine));
				sheet.addCell(new Label(31, startRow, "", borderEndLine));
				sheet.addCell(new Label(32, startRow, "", borderEndLine));
				sheet.addCell(new Label(33, startRow, "", borderEndLine));
				sheet.addCell(new Label(34, startRow, "", borderEndLine));
				sheet.addCell(new Label(35, startRow, "", borderEndLine));
				sheet.addCell(new Label(36, startRow, "", borderEndLine));
				sheet.addCell(new Label(37, startRow, "", borderEndLine));
				sheet.addCell(new Label(38, startRow, "", borderEndLine));
				sheet.addCell(new Label(39, startRow, "", borderEndLine));
				sheet.addCell(new Label(40, startRow, "", borderEndLine));
				sheet.addCell(new Label(41, startRow, "", borderEndLine));
				
				//sheet.addCell(new Label(34, startRow, "", borderEndLine));
				
				//sheet.getSettings().setPassword("028313766");
				//sheet.getSettings().setProtected(true);
			} else {
				sheet.mergeCells(0, 4, 5, 4);
				Alignment noAlg = Alignment.getAlignment(1);
				WritableCellFormat border = new WritableCellFormat();
				border.setAlignment(Alignment.CENTRE);
				border.setBorder(Border.ALL, BorderLineStyle.THIN);
				sheet.addCell(new Label(0,2, "¾ÔÁ¾ìâ´Â  "+userInfo.getUserName(), headLeft));
				sheet.addCell(new Label(21, 2, "»ÃÐ¨Ó§Ç´·Õè " + periodName + "  ¾.È. " + year, headRight));
				sheet.addCell(new Label(41, 2, "ÇÑ¹·Õè¾ÔÁ¾ì : "+sdfPrint.format(gd.getTime()), headRight));
				sheet.addCell(new Label(0, 41, "äÁè¾º¢éÍÁÙÅ", border));
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
	 * method ï¿½ï¿½ï¿½ï¿½Ñºï¿½ï¿½Ç¨ï¿½Íº Level ï¿½Í§ Data
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
