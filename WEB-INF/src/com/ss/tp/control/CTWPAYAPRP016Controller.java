/*
 * Created on 31 ๏ฟฝ.๏ฟฝ. 2549
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
import jxl.write.Blank;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;


import com.ss.tp.dto.VFeeWpay401VO;
import com.ss.tp.security.UserInfo;

import com.ss.tp.service.FeeWpayPrEmployeeService;
import com.ss.tp.service.SuOrganizeService;

/**
 * @author bowpoko
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class CTWPAYAPRP016Controller extends MultiActionController {
	
	
	
	
	public FeeWpayPrEmployeeService getFeeWpayPrEmployeeService() {
		return (FeeWpayPrEmployeeService) this.getApplicationContext().getBean(
				"feeWpayPrEmployeeService");
	}

	public SuOrganizeService getSuOrganizeService() {
		return (SuOrganizeService) this.getApplicationContext().getBean(
				"suOrganizeService");
	}

	
	public ModelAndView doPrintReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String ouCode = "";
		String userId = "";
		String flag = "";
		int year = 0;
		int period = 0;
		String periodName = "";
		DecimalFormat df = new DecimalFormat("###,##0.00");
		DecimalFormat dfInt = new DecimalFormat("###,##0");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition",
				"attachment; filename=./page/report/CTWPAYAPRP014.xls");
		InputStream in = this.getServletContext().getResourceAsStream(
				"/page/report/CTWPAYAPRP014.xls");
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

			period = (new Double(request.getParameter("period")).intValue());
		}
		if (request.getParameter("periodName") != null) {
			periodName = request.getParameter("periodName");
		}
		
		try {
		Workbook wb = Workbook.getWorkbook(in);
		WritableWorkbook ww = Workbook.createWorkbook(
				response.getOutputStream(), wb);
		String ouName = this.getSuOrganizeService().findOrganizeName(ouCode);

		WritableSheet sheet = ww.getSheet(0);
		WritableFont font = new WritableFont(WritableFont.ARIAL);
		font.setPointSize(10);

		WritableFont fontBold = new WritableFont(WritableFont.ARIAL);
		fontBold.setBoldStyle(WritableFont.BOLD);
		fontBold.setPointSize(11);

		

		Alignment alg = Alignment.getAlignment(1);
		WritableCellFormat borderGroup = new WritableCellFormat();
		borderGroup.setBorder(Border.LEFT, BorderLineStyle.THIN);
		borderGroup.setAlignment(alg.LEFT);

		WritableCellFormat borderRight = new WritableCellFormat();
		borderRight.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		borderRight.setAlignment(alg.LEFT);

		WritableCellFormat borderData = new WritableCellFormat();
		borderData.setBorder(Border.LEFT, BorderLineStyle.THIN);
		borderData.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		borderData.setAlignment(alg.LEFT);
		borderData.setFont(font);

		WritableCellFormat borderDataR = new WritableCellFormat();
		borderDataR.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		borderDataR.setAlignment(alg.CENTRE);
		borderDataR.setFont(font);
		
		WritableCellFormat borderDataL = new WritableCellFormat();
		borderDataL.setBorder(Border.LEFT, BorderLineStyle.THIN);
		borderDataL.setAlignment(alg.CENTRE);
		borderDataL.setFont(font);
		
		

		WritableCellFormat borderData2 = new WritableCellFormat();
		borderData2.setBorder(Border.LEFT, BorderLineStyle.THIN);
		borderData2.setAlignment(alg.RIGHT);
		borderData2.setFont(font);

		WritableCellFormat borderNumber = new WritableCellFormat();
		borderNumber.setBorder(Border.LEFT, BorderLineStyle.THIN);
		borderNumber.setAlignment(alg.RIGHT);
		borderNumber.setFont(font);

		WritableCellFormat borderMoney = new WritableCellFormat();
		borderMoney.setBorder(Border.LEFT, BorderLineStyle.THIN);
		borderMoney.setAlignment(alg.RIGHT);
		borderMoney.setFont(font);

		WritableCellFormat borderNo = new WritableCellFormat();
		//borderNo.setBorder(Border.LEFT, BorderLineStyle.THIN);
		borderNo.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		borderNo.setAlignment(alg.CENTRE);
		borderNo.setFont(font);
		
		WritableCellFormat borderNoo = new WritableCellFormat();
		//borderNo.setBorder(Border.LEFT, BorderLineStyle.THIN);
		borderNoo.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		borderNoo.setBorder(Border.LEFT, BorderLineStyle.THIN);
		borderNoo.setAlignment(alg.CENTRE);
		borderNoo.setFont(font);

		WritableFont fontRemark = new WritableFont(WritableFont.ARIAL);
		fontRemark.setPointSize(9);
		WritableCellFormat remark = new WritableCellFormat();
		remark.setBorder(Border.NONE, BorderLineStyle.THIN);
		remark.setAlignment(alg.LEFT);
		remark.setFont(fontRemark);

		WritableCellFormat borderEndLine = new WritableCellFormat();
		borderEndLine.setBorder(Border.TOP, BorderLineStyle.THIN);
		borderEndLine.setAlignment(alg.LEFT);

		WritableCellFormat borderNumber2 = new WritableCellFormat();
		//borderNumber2.setBorder(Border.ALL, BorderLineStyle.THIN);
		borderNumber2.setBorder(Border.LEFT, BorderLineStyle.THIN);
		borderNumber2.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		borderNumber2.setAlignment(alg.RIGHT);
		borderNumber2.setFont(font);
		
		WritableCellFormat borderNumber3 = new WritableCellFormat();
		borderNumber3.setBorder(Border.ALL, BorderLineStyle.THIN);
		borderNumber3.setAlignment(alg.RIGHT);
		borderNumber3.setFont(fontBold);
		
		WritableCellFormat borderData3 = new WritableCellFormat();
		//borderNumber2.setBorder(Border.ALL, BorderLineStyle.THIN);
		borderData3.setBorder(Border.LEFT, BorderLineStyle.THIN);
		borderData3.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		borderData3.setAlignment(alg.LEFT);
		borderData3.setFont(font);
		
		WritableCellFormat borderData4 = new WritableCellFormat();
		//borderNumber2.setBorder(Border.ALL, BorderLineStyle.THIN);
		borderData4.setBorder(Border.LEFT, BorderLineStyle.THIN);
		borderData4.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		borderData4.setAlignment(alg.RIGHT);
		borderData4.setFont(fontBold);
		
		WritableCellFormat borderData5 = new WritableCellFormat();
		//borderNumber2.setBorder(Border.ALL, BorderLineStyle.THIN);
		borderData5.setBorder(Border.ALL, BorderLineStyle.MEDIUM);
		//borderData5.setBorder(Border.LEFT, BorderLineStyle.THIN);
		//borderData5.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		borderData5.setAlignment(alg.RIGHT);
		borderData5.setFont(fontBold);
		
		WritableCellFormat borderDataLR = new WritableCellFormat();
		//borderNumber2.setBorder(Border.ALL, BorderLineStyle.THIN);
		borderDataLR.setBorder(Border.LEFT, BorderLineStyle.THIN);
		borderDataLR.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		//borderData5.setBorder(Border.LEFT, BorderLineStyle.THIN);
		//borderData5.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		//borderDataLR.setAlignment(alg.RIGHT);
		//.setFont(fontBold);
		
		WritableCellFormat borderDataLRT = new WritableCellFormat();
		//borderNumber2.setBorder(Border.ALL, BorderLineStyle.THIN);
		borderDataLRT.setBorder(Border.TOP, BorderLineStyle.THIN);
		borderDataLRT.setBorder(Border.LEFT, BorderLineStyle.THIN);
		borderDataLRT.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		//borderData5.setBorder(Border.LEFT, BorderLineStyle.THIN);
		//borderData5.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		//borderDataLR.setAlignment(alg.RIGHT);
		//.setFont(fontBold);
	    

		CellFormat head = sheet.getWritableCell(0 + 1, 0).getCellFormat();

		CellFormat headLeft = sheet.getWritableCell(0, 2).getCellFormat();
		CellFormat headRight = sheet.getWritableCell(3, 2).getCellFormat();
		CellFormat group = sheet.getWritableCell(2 + 1, 7).getCellFormat();
		CellFormat borderLR = sheet.getWritableCell(5 + 1, 7).getCellFormat();
		CellFormat endLeft = sheet.getWritableCell(1, 6).getCellFormat();
		CellFormat textFt = sheet.getWritableCell(4 + 1, 7).getCellFormat();
		CellFormat number = sheet.getWritableCell(1 + 1, 7).getCellFormat();

		CellFormat numberCenter = sheet.getWritableCell(7 + 1, 7).getCellFormat();

		GregorianCalendar gd = new GregorianCalendar();
		SimpleDateFormat sdfPrint = new SimpleDateFormat("dd/MM/yyyy HH:mm",
				new java.util.Locale("th", "TH"));
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		System.out.println("ouName : " + ouName);
		System.out.println("head : " + head);

		/* start title*/
		sheet.mergeCells(2, 1, 4, 1);
		sheet.addCell(new Label(2, 1,ouName, head));
		
		sheet.addCell(new Label(0,2,"พิมพ์โดย  "+userInfo.getUserName(), headLeft));
		sheet.addCell(new Label(2,2,"                                 ประจำงวด  "+periodName+" พ.ศ."+year+"                                              วันที่พิมพ์ : "+sdfPrint.format(gd.getTime()),headRight));
			/* end title */
		

		
		VFeeWpay401VO Rep401Vo = null;
		List Rep01 = this.getFeeWpayPrEmployeeService().feeWpay401UserReport(ouCode, year, period,"1",userId);
		
		List Rep02 = this.getFeeWpayPrEmployeeService().feeWpay401UserReport(ouCode, year, period,"2",userId);
		
		List Rep03 = this.getFeeWpayPrEmployeeService().feeWpay401UserReport(ouCode, year, period,"3",userId);
		List Rep04 = this.getFeeWpayPrEmployeeService().feeWpay401UserReport(ouCode, year, period,"4",userId);

		int startRow = 6;
		int checkLV = 0;
	
		double sumLevelGroup1 =0.0;
		double sumTotalLevelGroup1 = 0.0;
		double sumLevelGroup2 =0.0;
		double sumTotalLevelGroup2 = 0.0;
		double sumLevelGroup3 =0.0;
		double sumTotalLevelGroup3 = 0.0;
		double sumLevelGroup4 =0.0;
		double sumTotalLevelGroup4 = 0.0;
		double sumNetIncome = 0.0;
		
		String flagTotalHour = "";
		int seq = 0;
		sheet.mergeCells(0, startRow, 1, startRow);
		sheet.addCell(new Label(0, startRow,"รายการเงินได้ (เดบิต)" ,borderData3));
		//sheet.addCell(new Blank(1, startRow, borderLR));
		sheet.addCell(new Blank(2, startRow, borderData));
		sheet.addCell(new Blank(3, startRow, borderData));
		sheet.addCell(new Blank(4, startRow, borderData));
		sheet.addCell(new Blank(5, startRow, borderData));
		sheet.addCell(new Blank(6, startRow, borderData));
		startRow++;
		if (Rep01.size() != 0) {
			for (int i = 0; i < Rep01.size(); i++) {
				Rep401Vo = (VFeeWpay401VO) Rep01.get(i);
				checkLV = Integer.parseInt((Rep401Vo.getLevelGroupSum()));
				if (checkLV == 1) {
					
						/*if (!flagTotalHour.equals("")) {
							sheet.mergeCells(0, startRow, 2, startRow);
							sheet.addCell(new Label(0, startRow, "รวมเงินได้1", borderNumber2));
							//sheet.addCell(new Label(3, startRow, String.valueOf(df.format(sumTotalNewSalary)),borderNumber2));
							//sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalSalary)),borderNumber2));
							sheet.addCell(new Label(5, startRow, String.valueOf(df.format(sumTotalLevelGroup1)),borderNumber2));
							
							//sumTotalLevelGroup1 = 0.0;
							
							startRow++;
						}*/
						sheet.addCell(new Blank(0, startRow, borderData));
						sheet.addCell(new Blank(1, startRow, borderData));
						sheet.addCell(new Blank(2, startRow, borderData));
						sheet.addCell(new Blank(3, startRow, borderData));
						sheet.addCell(new Blank(4, startRow, borderData));
						sheet.addCell(new Blank(5, startRow, borderData));
						sheet.addCell(new Blank(6, startRow, borderData));
						
						
					
						//sheet.setRowView(startRow, 320);
						startRow++;
						seq = seq + 1;
						sheet.addCell(new Label(0, startRow, String.valueOf(seq), borderNumber));
						sheet.addCell(new Label(1, startRow, Rep401Vo.getAccNoSum(), borderNumber));
						sheet.addCell(new Label(2, startRow, Rep401Vo.getAccNameSum(), borderData3));
						sheet.addCell(new Label(3, startRow, String.valueOf(dfInt.format(Rep401Vo.getEmpAmountSum())),borderNumber));
						sheet.addCell(new Label(4, startRow, String.valueOf(df.format(Rep401Vo.getAmountSum())),borderNumber));
						sheet.addCell(new Label(5, startRow, Rep401Vo.getIncDecSubCodeSum(),borderNumber));
						sheet.addCell(new Label(6, startRow, Rep401Vo.getIncDecNameSum(),borderData3));
						
						
					
						
						flagTotalHour = "sum";
						
						if (Rep401Vo.getAmountSum() != null) {
							sumLevelGroup1 = sumLevelGroup1	+ Rep401Vo.getAmountSum().doubleValue();
							sumTotalLevelGroup1 = sumTotalLevelGroup1	+ Rep401Vo.getAmountSum().doubleValue();

						}
						
						
						sheet.setRowView(startRow, 320);
						startRow++;
						    //sheet.mergeCells(2, startRow, 3, startRow);
							sheet.addCell(new Label(0, startRow, "",borderDataLR));
							sheet.addCell(new Label(2, startRow, "รวมเงินได้", borderData4));
							//sheet.addCell(new Label(3, startRow, String.valueOf(df.format(sumTotalNewSalary)),borderNumber2));
							//sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalSalary)),borderNumber2));
							sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalLevelGroup1)),borderNumber3));
							sheet.addCell(new Label(5, startRow, "",borderDataLR));
							sheet.addCell(new Label(6, startRow, "",borderDataLR));
							//sumTotalLevelGroup1 = 0.0;
						
							//startRow++;

						
						
						//startRow++;
						
						
					}
				
				
				/* else if (checkLV == 2) {
						if (!flagTotalHour.equals("")) {
							sheet.mergeCells(0, startRow, 2, startRow);
							sheet.addCell(new Label(0, startRow, "รวมเงินหัก1", borderNumber2));
							//sheet.addCell(new Label(3, startRow, String.valueOf(df.format(sumTotalNewSalary)),borderNumber2));
							//sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalSalary)),borderNumber2));
							sheet.addCell(new Label(5, startRow, String.valueOf(df.format(sumTotalLevelGroup2)),borderNumber2));
							
							//sumTotalLevelGroup1 = 0.0;
							
							startRow++;
						}
						sheet.addCell(new Label(0, startRow, Rep401Vo.getLevelGroupSum(), group));
						sheet.addCell(new Blank(1, startRow, borderLR));
						sheet.addCell(new Blank(2, startRow, borderLR));
						sheet.addCell(new Blank(3, startRow, borderLR));
						sheet.addCell(new Blank(4, startRow, borderLR));
						sheet.addCell(new Blank(5, startRow, borderLR));
						sheet.addCell(new Blank(6, startRow, borderLR));
						
					
						
						sheet.setRowView(startRow, 320);
						startRow++;
						seq = seq + 1;
						sheet.addCell(new Label(0, startRow, String.valueOf(seq), borderNumber));
						sheet.addCell(new Label(1, startRow, Rep401Vo.getAccNoSum(), borderNumber));
						sheet.addCell(new Label(2, startRow, Rep401Vo.getAccNameSum(), borderLR));
						sheet.addCell(new Label(3, startRow, String.valueOf(dfInt.format(Rep401Vo.getEmpAmountSum())),borderNumber));
						sheet.addCell(new Label(4, startRow, String.valueOf(df.format(Rep401Vo.getAmountSum())),borderNumber));
						sheet.addCell(new Label(5, startRow, Rep401Vo.getIncDecSubCodeSum(),borderNumber));
						sheet.addCell(new Label(6, startRow, Rep401Vo.getIncDecNameSum(),borderNumber));
						
						flagTotalHour = "sum";
						
						if (Rep401Vo.getAmountSum() != null) {
							sumLevelGroup2 = sumLevelGroup2	+ Rep401Vo.getAmountSum().doubleValue();
							sumTotalLevelGroup2 = sumTotalLevelGroup2 + Rep401Vo.getAmountSum().doubleValue();
						}
						sheet.setRowView(startRow, 320);
						startRow++;
						
							sheet.mergeCells(0, startRow, 2, startRow);
							sheet.addCell(new Label(0, startRow, "รวมเงินหัก2", borderNumber2));
							//sheet.addCell(new Label(3, startRow, String.valueOf(df.format(sumTotalNewSalary)),borderNumber2));
							//sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalSalary)),borderNumber2));
							sheet.addCell(new Label(5, startRow, String.valueOf(df.format(sumTotalLevelGroup2)),borderNumber2));
							
							//sumLevelGroup2 = 0.0;
							
							startRow++;
						

						//sheet.setRowView(startRow, 320);
					} */
				} 
			

			/*sheet.mergeCells(0, startRow, 2, startRow);
			sheet.addCell(new Label(0, startRow, "รวมทั้งหมด", borderNumber2));
			//sheet.addCell(new Label(3, startRow,String.valueOf(df.format(new Double(sumAllTotalNewSalary))),borderNumber2));
			//sheet.addCell(new Label(4, startRow,String.valueOf(df.format(new Double(sumAllTotalSalary))),borderNumber2));
			sheet.addCell(new Label(5, startRow,String.valueOf(df.format(new Double(sumTotalLevelGroup1))),borderNumber2));
			*/
			
			startRow++;
			sheet.addCell(new Label(0, startRow, "", borderEndLine));
			sheet.addCell(new Label(1, startRow, "", borderEndLine));
			sheet.addCell(new Label(2, startRow, "", borderEndLine));
			sheet.addCell(new Label(3, startRow, "", borderEndLine));
			sheet.addCell(new Label(4, startRow, "", borderEndLine));
			sheet.addCell(new Label(5, startRow, "", borderEndLine));
			sheet.addCell(new Label(6, startRow, "", borderEndLine));
			
		
			
			sheet.getSettings().setPassword("028313766");
			sheet.getSettings().setProtected(true);
			sheet.mergeCells(0, startRow, 1, startRow);
			sheet.addCell(new Label(0, startRow,"รายการเงินหัก (เครดิต)" ,borderData3));
			//sheet.addCell(new Blank(1, startRow, borderLR));
			sheet.addCell(new Blank(2, startRow, borderData));
			sheet.addCell(new Blank(3, startRow, borderData));
			sheet.addCell(new Blank(4, startRow, borderData));
			sheet.addCell(new Blank(5, startRow, borderData));
			sheet.addCell(new Blank(6, startRow, borderData));
			startRow++;
			if (Rep02.size() != 0) {
				for (int i = 0; i < Rep02.size(); i++) {
					Rep401Vo = (VFeeWpay401VO) Rep02.get(i);
					checkLV = Integer.parseInt((Rep401Vo.getLevelGroupSum()));
					if (checkLV == 2) {
						
							/*if (!flagTotalHour.equals("")) {
								sheet.mergeCells(0, startRow, 2, startRow);
								sheet.addCell(new Label(0, startRow, "รวมเงินได้1", borderNumber2));
								//sheet.addCell(new Label(3, startRow, String.valueOf(df.format(sumTotalNewSalary)),borderNumber2));
								//sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalSalary)),borderNumber2));
								sheet.addCell(new Label(5, startRow, String.valueOf(df.format(sumTotalLevelGroup1)),borderNumber2));
								
								//sumTotalLevelGroup1 = 0.0;
								
								startRow++;
							}*/
							sheet.addCell(new Blank(0, startRow, borderData));
							sheet.addCell(new Blank(1, startRow, borderData));
							sheet.addCell(new Blank(2, startRow, borderData));
							sheet.addCell(new Blank(3, startRow, borderData));
							sheet.addCell(new Blank(4, startRow, borderData));
							sheet.addCell(new Blank(5, startRow, borderData));
							sheet.addCell(new Blank(6, startRow, borderData));
							
							
						
							sheet.setRowView(startRow, 320);
							startRow++;
							seq = seq + 1;
							sheet.addCell(new Label(0, startRow, String.valueOf(seq), borderNumber));
							sheet.addCell(new Label(1, startRow, Rep401Vo.getAccNoSum(), borderNumber));
							sheet.addCell(new Label(2, startRow, Rep401Vo.getAccNameSum(), borderData3));
							sheet.addCell(new Label(3, startRow, String.valueOf(dfInt.format(Rep401Vo.getEmpAmountSum())),borderNumber));
							sheet.addCell(new Label(4, startRow, String.valueOf(df.format(Rep401Vo.getAmountSum())),borderNumber));
							sheet.addCell(new Label(5, startRow, Rep401Vo.getIncDecSubCodeSum(),borderNumber));
							sheet.addCell(new Label(6, startRow, Rep401Vo.getIncDecNameSum(),borderData3));
							
							
						
							
							flagTotalHour = "sum";
							
							if (Rep401Vo.getAmountSum() != null) {
								sumLevelGroup2 = sumLevelGroup2	+ Rep401Vo.getAmountSum().doubleValue();
								sumTotalLevelGroup2 = sumTotalLevelGroup2	+ Rep401Vo.getAmountSum().doubleValue();

							}
							
							
							sheet.setRowView(startRow, 320);
							startRow++;
								sheet.addCell(new Label(0, startRow, "",borderDataLR));
								sheet.addCell(new Label(2, startRow, "รวมเงินหัก", borderData4));
								//sheet.addCell(new Label(3, startRow, String.valueOf(df.format(sumTotalNewSalary)),borderNumber2));
								//sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalSalary)),borderNumber2));
								sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalLevelGroup2)),borderNumber3));
								sheet.addCell(new Label(5, startRow, "",borderDataLR));
								sheet.addCell(new Label(6, startRow, "",borderDataLR));
								//sumTotalLevelGroup1 = 0.0;
							
								//startRow++;

							
							
							//startRow++;
							
							
						}
					
					
					/* else if (checkLV == 2) {
							if (!flagTotalHour.equals("")) {
								sheet.mergeCells(0, startRow, 2, startRow);
								sheet.addCell(new Label(0, startRow, "รวมเงินหัก1", borderNumber2));
								//sheet.addCell(new Label(3, startRow, String.valueOf(df.format(sumTotalNewSalary)),borderNumber2));
								//sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalSalary)),borderNumber2));
								sheet.addCell(new Label(5, startRow, String.valueOf(df.format(sumTotalLevelGroup2)),borderNumber2));
								
								//sumTotalLevelGroup1 = 0.0;
								
								startRow++;
							}
							sheet.addCell(new Label(0, startRow, Rep401Vo.getLevelGroupSum(), group));
							sheet.addCell(new Blank(1, startRow, borderLR));
							sheet.addCell(new Blank(2, startRow, borderLR));
							sheet.addCell(new Blank(3, startRow, borderLR));
							sheet.addCell(new Blank(4, startRow, borderLR));
							sheet.addCell(new Blank(5, startRow, borderLR));
							sheet.addCell(new Blank(6, startRow, borderLR));
							
						
							
							sheet.setRowView(startRow, 320);
							startRow++;
							seq = seq + 1;
							sheet.addCell(new Label(0, startRow, String.valueOf(seq), borderNumber));
							sheet.addCell(new Label(1, startRow, Rep401Vo.getAccNoSum(), borderNumber));
							sheet.addCell(new Label(2, startRow, Rep401Vo.getAccNameSum(), borderLR));
							sheet.addCell(new Label(3, startRow, String.valueOf(dfInt.format(Rep401Vo.getEmpAmountSum())),borderNumber));
							sheet.addCell(new Label(4, startRow, String.valueOf(df.format(Rep401Vo.getAmountSum())),borderNumber));
							sheet.addCell(new Label(5, startRow, Rep401Vo.getIncDecSubCodeSum(),borderNumber));
							sheet.addCell(new Label(6, startRow, Rep401Vo.getIncDecNameSum(),borderNumber));
							
							flagTotalHour = "sum";
							
							if (Rep401Vo.getAmountSum() != null) {
								sumLevelGroup2 = sumLevelGroup2	+ Rep401Vo.getAmountSum().doubleValue();
								sumTotalLevelGroup2 = sumTotalLevelGroup2 + Rep401Vo.getAmountSum().doubleValue();
							}
							sheet.setRowView(startRow, 320);
							startRow++;
							
								sheet.mergeCells(0, startRow, 2, startRow);
								sheet.addCell(new Label(0, startRow, "รวมเงินหัก2", borderNumber2));
								//sheet.addCell(new Label(3, startRow, String.valueOf(df.format(sumTotalNewSalary)),borderNumber2));
								//sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalSalary)),borderNumber2));
								sheet.addCell(new Label(5, startRow, String.valueOf(df.format(sumTotalLevelGroup2)),borderNumber2));
								
								//sumLevelGroup2 = 0.0;
								
								startRow++;
							

							//sheet.setRowView(startRow, 320);
						} */
					} 
				

				/*sheet.mergeCells(0, startRow, 2, startRow);
				sheet.addCell(new Label(0, startRow, "รวมทั้งหมด", borderNumber2));
				//sheet.addCell(new Label(3, startRow,String.valueOf(df.format(new Double(sumAllTotalNewSalary))),borderNumber2));
				//sheet.addCell(new Label(4, startRow,String.valueOf(df.format(new Double(sumAllTotalSalary))),borderNumber2));
				sheet.addCell(new Label(5, startRow,String.valueOf(df.format(new Double(sumTotalLevelGroup1))),borderNumber2));
				*/
				
				startRow++;
				sheet.addCell(new Label(0, startRow, "", borderEndLine));
				sheet.addCell(new Label(1, startRow, "", borderEndLine));
				sheet.addCell(new Label(2, startRow, "", borderEndLine));
				sheet.addCell(new Label(3, startRow, "", borderEndLine));
				sheet.addCell(new Label(4, startRow, "", borderEndLine));
				sheet.addCell(new Label(5, startRow, "", borderEndLine));
				sheet.addCell(new Label(6, startRow, "", borderEndLine));
				
			
			}
			sumNetIncome = sumTotalLevelGroup1-sumTotalLevelGroup2;
			sheet.addCell(new Label(0, startRow, "",borderDataLRT));
			sheet.addCell(new Label(2, startRow, "เงินได้สุทธินำส่งธนาคาร", borderData5));
			sheet.addCell(new Label(4, startRow,String.valueOf(df.format(sumNetIncome)),borderData5));
			sheet.addCell(new Label(5, startRow, "",borderDataLRT));
			sheet.addCell(new Label(6, startRow, "",borderDataLRT));
			startRow++;
			sheet.addCell(new Label(0, startRow, "", borderEndLine));
			sheet.addCell(new Label(1, startRow, "", borderEndLine));
			sheet.addCell(new Label(2, startRow, "", borderEndLine));
			sheet.addCell(new Label(3, startRow, "", borderEndLine));
			sheet.addCell(new Label(4, startRow, "", borderEndLine));
			sheet.addCell(new Label(5, startRow, "", borderEndLine));
			sheet.addCell(new Label(6, startRow, "", borderEndLine));
			if (Rep03.size() != 0) {
				sheet.mergeCells(0, startRow, 1, startRow);
				sheet.addCell(new Label(0, startRow,"รายการเงินได้-ปรับปรุงรายการรับ (เดบิต)" ,borderData3));
				//sheet.addCell(new Blank(1, startRow, borderLR));
				sheet.addCell(new Blank(2, startRow, borderData));
				sheet.addCell(new Blank(3, startRow, borderData));
				sheet.addCell(new Blank(4, startRow, borderData));
				sheet.addCell(new Blank(5, startRow, borderData));
				sheet.addCell(new Blank(6, startRow, borderData));
				startRow++;
				for (int i = 0; i < Rep03.size(); i++) {
					Rep401Vo = (VFeeWpay401VO) Rep03.get(i);
					checkLV = Integer.parseInt((Rep401Vo.getLevelGroupSum()));
					if (checkLV == 3) {
						
							/*if (!flagTotalHour.equals("")) {
								sheet.mergeCells(0, startRow, 2, startRow);
								sheet.addCell(new Label(0, startRow, "รวมเงินได้1", borderNumber2));
								//sheet.addCell(new Label(3, startRow, String.valueOf(df.format(sumTotalNewSalary)),borderNumber2));
								//sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalSalary)),borderNumber2));
								sheet.addCell(new Label(5, startRow, String.valueOf(df.format(sumTotalLevelGroup1)),borderNumber2));
								
								//sumTotalLevelGroup1 = 0.0;
								
								startRow++;
							}*/
							sheet.addCell(new Blank(0, startRow, borderData));
							sheet.addCell(new Blank(1, startRow, borderData));
							sheet.addCell(new Blank(2, startRow, borderData));
							sheet.addCell(new Blank(3, startRow, borderData));
							sheet.addCell(new Blank(4, startRow, borderData));
							sheet.addCell(new Blank(5, startRow, borderData));
							sheet.addCell(new Blank(6, startRow, borderData));
							
							
						
							sheet.setRowView(startRow, 320);
							startRow++;
							seq = seq + 1;
							sheet.addCell(new Label(0, startRow, String.valueOf(seq), borderNumber));
							sheet.addCell(new Label(1, startRow, Rep401Vo.getAccNoSum(), borderNumber));
							sheet.addCell(new Label(2, startRow, Rep401Vo.getAccNameSum(), borderData3));
							sheet.addCell(new Label(3, startRow, String.valueOf(dfInt.format(Rep401Vo.getEmpAmountSum())),borderNumber));
							sheet.addCell(new Label(4, startRow, String.valueOf(df.format(Rep401Vo.getAmountSum())),borderNumber));
							sheet.addCell(new Label(5, startRow, Rep401Vo.getIncDecSubCodeSum(),borderNumber));
							sheet.addCell(new Label(6, startRow, Rep401Vo.getIncDecNameSum(),borderData3));
							
							
						
							
							flagTotalHour = "sum";
							
							if (Rep401Vo.getAmountSum() != null) {
								sumLevelGroup3 = sumLevelGroup3	+ Rep401Vo.getAmountSum().doubleValue();
								sumTotalLevelGroup3 = sumTotalLevelGroup3	+ Rep401Vo.getAmountSum().doubleValue();

							}
							
							
							sheet.setRowView(startRow, 320);
							startRow++;
								sheet.addCell(new Label(0, startRow, "",borderDataLR));
								sheet.addCell(new Label(2, startRow, "รวมเงินได้-ปรับปรุงรายการรับ", borderData4));
								//sheet.addCell(new Label(3, startRow, String.valueOf(df.format(sumTotalNewSalary)),borderNumber2));
								//sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalSalary)),borderNumber2));
								sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalLevelGroup3)),borderNumber3));
								sheet.addCell(new Label(5, startRow, "",borderDataLR));
								sheet.addCell(new Label(6, startRow, "",borderDataLR));
								//sumTotalLevelGroup1 = 0.0;
							
								//startRow++;

							
							
							//startRow++;
							
							
						}
					
					
					/* else if (checkLV == 2) {
							if (!flagTotalHour.equals("")) {
								sheet.mergeCells(0, startRow, 2, startRow);
								sheet.addCell(new Label(0, startRow, "รวมเงินหัก1", borderNumber2));
								//sheet.addCell(new Label(3, startRow, String.valueOf(df.format(sumTotalNewSalary)),borderNumber2));
								//sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalSalary)),borderNumber2));
								sheet.addCell(new Label(5, startRow, String.valueOf(df.format(sumTotalLevelGroup2)),borderNumber2));
								
								//sumTotalLevelGroup1 = 0.0;
								
								startRow++;
							}
							sheet.addCell(new Label(0, startRow, Rep401Vo.getLevelGroupSum(), group));
							sheet.addCell(new Blank(1, startRow, borderLR));
							sheet.addCell(new Blank(2, startRow, borderLR));
							sheet.addCell(new Blank(3, startRow, borderLR));
							sheet.addCell(new Blank(4, startRow, borderLR));
							sheet.addCell(new Blank(5, startRow, borderLR));
							sheet.addCell(new Blank(6, startRow, borderLR));
							
						
							
							sheet.setRowView(startRow, 320);
							startRow++;
							seq = seq + 1;
							sheet.addCell(new Label(0, startRow, String.valueOf(seq), borderNumber));
							sheet.addCell(new Label(1, startRow, Rep401Vo.getAccNoSum(), borderNumber));
							sheet.addCell(new Label(2, startRow, Rep401Vo.getAccNameSum(), borderLR));
							sheet.addCell(new Label(3, startRow, String.valueOf(dfInt.format(Rep401Vo.getEmpAmountSum())),borderNumber));
							sheet.addCell(new Label(4, startRow, String.valueOf(df.format(Rep401Vo.getAmountSum())),borderNumber));
							sheet.addCell(new Label(5, startRow, Rep401Vo.getIncDecSubCodeSum(),borderNumber));
							sheet.addCell(new Label(6, startRow, Rep401Vo.getIncDecNameSum(),borderNumber));
							
							flagTotalHour = "sum";
							
							if (Rep401Vo.getAmountSum() != null) {
								sumLevelGroup2 = sumLevelGroup2	+ Rep401Vo.getAmountSum().doubleValue();
								sumTotalLevelGroup2 = sumTotalLevelGroup2 + Rep401Vo.getAmountSum().doubleValue();
							}
							sheet.setRowView(startRow, 320);
							startRow++;
							
								sheet.mergeCells(0, startRow, 2, startRow);
								sheet.addCell(new Label(0, startRow, "รวมเงินหัก2", borderNumber2));
								//sheet.addCell(new Label(3, startRow, String.valueOf(df.format(sumTotalNewSalary)),borderNumber2));
								//sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalSalary)),borderNumber2));
								sheet.addCell(new Label(5, startRow, String.valueOf(df.format(sumTotalLevelGroup2)),borderNumber2));
								
								//sumLevelGroup2 = 0.0;
								
								startRow++;
							

							//sheet.setRowView(startRow, 320);
						} */
					} 
				

				/*sheet.mergeCells(0, startRow, 2, startRow);
				sheet.addCell(new Label(0, startRow, "รวมทั้งหมด", borderNumber2));
				//sheet.addCell(new Label(3, startRow,String.valueOf(df.format(new Double(sumAllTotalNewSalary))),borderNumber2));
				//sheet.addCell(new Label(4, startRow,String.valueOf(df.format(new Double(sumAllTotalSalary))),borderNumber2));
				sheet.addCell(new Label(5, startRow,String.valueOf(df.format(new Double(sumTotalLevelGroup1))),borderNumber2));
				*/
				
				startRow++;
				sheet.addCell(new Label(0, startRow, "", borderEndLine));
				sheet.addCell(new Label(1, startRow, "", borderEndLine));
				sheet.addCell(new Label(2, startRow, "", borderEndLine));
				sheet.addCell(new Label(3, startRow, "", borderEndLine));
				sheet.addCell(new Label(4, startRow, "", borderEndLine));
				sheet.addCell(new Label(5, startRow, "", borderEndLine));
				sheet.addCell(new Label(6, startRow, "", borderEndLine));
				
			
			}
			if (Rep04.size() != 0) {
				sheet.mergeCells(0, startRow, 1, startRow);
				sheet.addCell(new Label(0, startRow,"รายการเงินหัก-รายการเรียกคืน (เครดิต)" ,borderData3));
				//sheet.addCell(new Blank(1, startRow, borderLR));
				sheet.addCell(new Blank(2, startRow, borderData));
				sheet.addCell(new Blank(3, startRow, borderData));
				sheet.addCell(new Blank(4, startRow, borderData));
				sheet.addCell(new Blank(5, startRow, borderData));
				sheet.addCell(new Blank(6, startRow, borderData));
				startRow++;
				for (int i = 0; i < Rep04.size(); i++) {
					Rep401Vo = (VFeeWpay401VO) Rep04.get(i);
					checkLV = Integer.parseInt((Rep401Vo.getLevelGroupSum()));
					if (checkLV == 4) {
						
							/*if (!flagTotalHour.equals("")) {
								sheet.mergeCells(0, startRow, 2, startRow);
								sheet.addCell(new Label(0, startRow, "รวมเงินได้1", borderNumber2));
								//sheet.addCell(new Label(3, startRow, String.valueOf(df.format(sumTotalNewSalary)),borderNumber2));
								//sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalSalary)),borderNumber2));
								sheet.addCell(new Label(5, startRow, String.valueOf(df.format(sumTotalLevelGroup1)),borderNumber2));
								
								//sumTotalLevelGroup1 = 0.0;
								
								startRow++;
							}*/
							sheet.addCell(new Blank(0, startRow, borderData));
							sheet.addCell(new Blank(1, startRow, borderData));
							sheet.addCell(new Blank(2, startRow, borderData));
							sheet.addCell(new Blank(3, startRow, borderData));
							sheet.addCell(new Blank(4, startRow, borderData));
							sheet.addCell(new Blank(5, startRow, borderData));
							sheet.addCell(new Blank(6, startRow, borderData));
							
							
						
							sheet.setRowView(startRow, 320);
							startRow++;
							seq = seq + 1;
							sheet.addCell(new Label(0, startRow, String.valueOf(seq), borderNumber));
							sheet.addCell(new Label(1, startRow, Rep401Vo.getAccNoSum(), borderNumber));
							sheet.addCell(new Label(2, startRow, Rep401Vo.getAccNameSum(), borderData3));
							sheet.addCell(new Label(3, startRow, String.valueOf(dfInt.format(Rep401Vo.getEmpAmountSum())),borderNumber));
							sheet.addCell(new Label(4, startRow, String.valueOf(df.format(Rep401Vo.getAmountSum())),borderNumber));
							sheet.addCell(new Label(5, startRow, Rep401Vo.getIncDecSubCodeSum(),borderNumber));
							sheet.addCell(new Label(6, startRow, Rep401Vo.getIncDecNameSum(),borderData3));
							
							
						
							
							flagTotalHour = "sum";
							
							if (Rep401Vo.getAmountSum() != null) {
								sumLevelGroup4 = sumLevelGroup4	+ Rep401Vo.getAmountSum().doubleValue();
								sumTotalLevelGroup4 = sumTotalLevelGroup4	+ Rep401Vo.getAmountSum().doubleValue();

							}
							
							
							sheet.setRowView(startRow, 320);
							startRow++;
								sheet.addCell(new Label(0, startRow, "",borderDataLR));
								sheet.addCell(new Label(2, startRow, "รวมเงินหัก-รายการเรียกคืน", borderData4));
								//sheet.addCell(new Label(3, startRow, String.valueOf(df.format(sumTotalNewSalary)),borderNumber2));
								//sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalSalary)),borderNumber2));
								sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalLevelGroup4)),borderNumber3));
								sheet.addCell(new Label(5, startRow, "",borderDataLR));
								sheet.addCell(new Label(6, startRow, "",borderDataLR));
								//sumTotalLevelGroup1 = 0.0;
							
								//startRow++;

							
							
							//startRow++;
							
							
						}
					
					
					/* else if (checkLV == 2) {
							if (!flagTotalHour.equals("")) {
								sheet.mergeCells(0, startRow, 2, startRow);
								sheet.addCell(new Label(0, startRow, "รวมเงินหัก1", borderNumber2));
								//sheet.addCell(new Label(3, startRow, String.valueOf(df.format(sumTotalNewSalary)),borderNumber2));
								//sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalSalary)),borderNumber2));
								sheet.addCell(new Label(5, startRow, String.valueOf(df.format(sumTotalLevelGroup2)),borderNumber2));
								
								//sumTotalLevelGroup1 = 0.0;
								
								startRow++;
							}
							sheet.addCell(new Label(0, startRow, Rep401Vo.getLevelGroupSum(), group));
							sheet.addCell(new Blank(1, startRow, borderLR));
							sheet.addCell(new Blank(2, startRow, borderLR));
							sheet.addCell(new Blank(3, startRow, borderLR));
							sheet.addCell(new Blank(4, startRow, borderLR));
							sheet.addCell(new Blank(5, startRow, borderLR));
							sheet.addCell(new Blank(6, startRow, borderLR));
							
						
							
							sheet.setRowView(startRow, 320);
							startRow++;
							seq = seq + 1;
							sheet.addCell(new Label(0, startRow, String.valueOf(seq), borderNumber));
							sheet.addCell(new Label(1, startRow, Rep401Vo.getAccNoSum(), borderNumber));
							sheet.addCell(new Label(2, startRow, Rep401Vo.getAccNameSum(), borderLR));
							sheet.addCell(new Label(3, startRow, String.valueOf(dfInt.format(Rep401Vo.getEmpAmountSum())),borderNumber));
							sheet.addCell(new Label(4, startRow, String.valueOf(df.format(Rep401Vo.getAmountSum())),borderNumber));
							sheet.addCell(new Label(5, startRow, Rep401Vo.getIncDecSubCodeSum(),borderNumber));
							sheet.addCell(new Label(6, startRow, Rep401Vo.getIncDecNameSum(),borderNumber));
							
							flagTotalHour = "sum";
							
							if (Rep401Vo.getAmountSum() != null) {
								sumLevelGroup2 = sumLevelGroup2	+ Rep401Vo.getAmountSum().doubleValue();
								sumTotalLevelGroup2 = sumTotalLevelGroup2 + Rep401Vo.getAmountSum().doubleValue();
							}
							sheet.setRowView(startRow, 320);
							startRow++;
							
								sheet.mergeCells(0, startRow, 2, startRow);
								sheet.addCell(new Label(0, startRow, "รวมเงินหัก2", borderNumber2));
								//sheet.addCell(new Label(3, startRow, String.valueOf(df.format(sumTotalNewSalary)),borderNumber2));
								//sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumTotalSalary)),borderNumber2));
								sheet.addCell(new Label(5, startRow, String.valueOf(df.format(sumTotalLevelGroup2)),borderNumber2));
								
								//sumLevelGroup2 = 0.0;
								
								startRow++;
							

							//sheet.setRowView(startRow, 320);
						} */
					} 
				

				/*sheet.mergeCells(0, startRow, 2, startRow);
				sheet.addCell(new Label(0, startRow, "รวมทั้งหมด", borderNumber2));
				//sheet.addCell(new Label(3, startRow,String.valueOf(df.format(new Double(sumAllTotalNewSalary))),borderNumber2));
				//sheet.addCell(new Label(4, startRow,String.valueOf(df.format(new Double(sumAllTotalSalary))),borderNumber2));
				sheet.addCell(new Label(5, startRow,String.valueOf(df.format(new Double(sumTotalLevelGroup1))),borderNumber2));
				*/
				
				startRow++;
				sheet.addCell(new Label(0, startRow, "", borderEndLine));
				sheet.addCell(new Label(1, startRow, "", borderEndLine));
				sheet.addCell(new Label(2, startRow, "", borderEndLine));
				sheet.addCell(new Label(3, startRow, "", borderEndLine));
				sheet.addCell(new Label(4, startRow, "", borderEndLine));
				sheet.addCell(new Label(5, startRow, "", borderEndLine));
				sheet.addCell(new Label(6, startRow, "", borderEndLine));
				
			
			}
			
			
			
		} else {
			
			Alignment noAlg = Alignment.getAlignment(1);
			WritableCellFormat border = new WritableCellFormat();
			border.setAlignment(Alignment.CENTRE);
			border.setBorder(Border.ALL, BorderLineStyle.THIN);
			sheet.mergeCells(2, 1, 4, 1);
			sheet.addCell(new Label(2, 1,ouName, head));
			
			sheet.addCell(new Label(0,2,"พิมพ์โดย  "+userInfo.getUserName(), headLeft));
			sheet.addCell(new Label(2,2,"                                 ประจำงวด  "+periodName+" พ.ศ."+year+"                                              วันที่พิมพ์ : "+sdfPrint.format(gd.getTime()),headRight));
			sheet.mergeCells(0, 7, 6, 7);
			sheet.addCell(new Label(0, 7, "ไม่พบข้อมูล", border));
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
 * method ๏ฟฝ๏ฟฝ๏ฟฝ๏ฟฝับ๏ฟฝ๏ฟฝวจ๏ฟฝอบ Level ๏ฟฝอง Data
 * 
 * @param div
 * @param sec
 * @return
 */


}
