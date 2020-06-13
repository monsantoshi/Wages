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
public class CTWPAYAPRP017Controller extends MultiActionController {

	
	
	public FeeWpayPrEmployeeService getFeeWpayPrEmployeeService() {
		return (FeeWpayPrEmployeeService) this.getApplicationContext().getBean("feeWpayPrEmployeeService");
	}

	public SuOrganizeService getSuOrganizeService() {
		return (SuOrganizeService) this.getApplicationContext().getBean(
				"suOrganizeService");
	}

	/**
	 * method ๏ฟฝ๏ฟฝ๏ฟฝ๏ฟฝับ๏ฟฝ๏ฟฝ๏ฟฝาง๏ฟฝ๏ฟฝยงาน๏ฟฝิน๏ฟฝ๏ฟฝ๏ฟฝุง๏ฟฝุข๏ฟฝาพ
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
					"attachment; filename=./page/report/CTWPAYAPRP017.xls");
			InputStream in = this.getServletContext().getResourceAsStream(
					"/page/report/CTWPAYAPRP017.xls");

			Workbook wb = Workbook.getWorkbook(in);
			WritableWorkbook ww = Workbook.createWorkbook(
					response.getOutputStream(), wb);
			WritableSheet sheet = ww.getSheet(0);
			String ouName = this.getSuOrganizeService().findOrganizeName(ouCode);
			WritableFont font = new WritableFont(WritableFont.ARIAL);
			font.setPointSize(9);

			Alignment dataAlignLeft = Alignment.LEFT;
			Alignment dataAlignRight = Alignment.RIGHT;
			Alignment dataAlignCenter = Alignment.CENTRE;

			WritableFont fontBold = new WritableFont(WritableFont.ARIAL);
			fontBold.setBoldStyle(WritableFont.BOLD);
			fontBold.setPointSize(9);
			
			WritableFont fontBoldBig = new WritableFont(WritableFont.ARIAL);
			fontBoldBig.setBoldStyle(WritableFont.BOLD);
			fontBoldBig.setPointSize(11);

			Alignment alg = Alignment.getAlignment(1);

			WritableCellFormat boldCenterFormat = new WritableCellFormat();
			boldCenterFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
			boldCenterFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			boldCenterFormat.setAlignment(dataAlignCenter);
			boldCenterFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			boldCenterFormat.setFont(font);
			

			WritableCellFormat boldBigCenterFormat = new WritableCellFormat();
			//boldBigCenterFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
			//boldBigCenterFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			boldBigCenterFormat.setAlignment(dataAlignCenter);
			boldBigCenterFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			boldBigCenterFormat.setFont(fontBoldBig);

			WritableCellFormat normalCenterFormat = new WritableCellFormat();
			normalCenterFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
			normalCenterFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			normalCenterFormat.setAlignment(dataAlignCenter);
			normalCenterFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			normalCenterFormat.setFont(font);

			WritableCellFormat borderNumber = new WritableCellFormat();
			borderNumber.setBorder(Border.ALL, BorderLineStyle.THIN);
			//borderNumber.setBorder(Border.LEFT, BorderLineStyle.THIN);
			//borderNumber.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			borderNumber.setAlignment(Alignment.RIGHT);
			borderNumber.setFont(font);

			WritableCellFormat borderNumber2 = new WritableCellFormat();
			//borderNumber2.setBorder(Border.ALL, BorderLineStyle.THIN);
			//borderNumber2.setAlignment(Alignment.RIGHT);
			borderNumber2.setFont(font);

			WritableCellFormat HeadFormat = new WritableCellFormat();
			HeadFormat.setAlignment(Alignment.LEFT);
			HeadFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			HeadFormat.setFont(fontBold);

			WritableCellFormat borderEndLine = new WritableCellFormat();
			borderEndLine.setBorder(Border.TOP, BorderLineStyle.THIN);
			borderEndLine.setAlignment(Alignment.LEFT);

			//CellFormat title = sheet.getWritableCell(3, 0).getCellFormat();
			// CellFormat headRight = sheet.getWritableCell(0,
			// 2).getCellFormat();
			//CellFormat headerRpt = sheet.getWritableCell(4, 0).getCellFormat();
			//CellFormat group = sheet.getWritableCell(0, 5).getCellFormat();
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
			WritableCellFormat headLeftAll = new WritableCellFormat();
			headLeftAll.setBorder(Border.ALL, BorderLineStyle.THIN);
			headLeftAll.setAlignment(dataAlignLeft);
			headLeftAll.setVerticalAlignment(VerticalAlignment.CENTRE);
			headLeftAll.setFont(fontBold);

			WritableCellFormat head = new WritableCellFormat();
			//head.setBorder(Border.ALL, BorderLineStyle.THIN);
			//borderLR.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			head.setAlignment(dataAlignCenter);
			head.setVerticalAlignment(VerticalAlignment.CENTRE);
			head.setFont(fontBold);
			
			WritableCellFormat headAll = new WritableCellFormat();
			headAll.setBorder(Border.ALL, BorderLineStyle.THIN);
			//headAll.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			headAll.setAlignment(dataAlignCenter);
			headAll.setVerticalAlignment(VerticalAlignment.CENTRE);
			headAll.setFont(fontBold);
			
			GregorianCalendar gd = new GregorianCalendar();
			SimpleDateFormat sdfPrint = new SimpleDateFormat("dd/MM/yyyy HH:mm",
					new java.util.Locale("th", "TH"));
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
					"UserLogin");
			System.out.println("ouName : " + ouName);
			System.out.println("head : " + head);

		

			FeeWpayPrDailyNetAmtRepVO helVo = null;
			List helRpt = this.getFeeWpayPrEmployeeService().feeWpayPrDailyRepByOrg(userId, ouCode,year, period);
			int startRow = 0;
			int startCol = 0;
			
			String tmpCode = "";
			
			
			
			int seq = 0;
			if (helRpt.size() != 0) {
				for (int i = 0; i < helRpt.size(); i++) {
					helVo = (FeeWpayPrDailyNetAmtRepVO) helRpt.get(i);
					        
							/* start title*/
							sheet.mergeCells(startCol, startRow,startCol+5, startRow);
							sheet.addCell(new Label(startCol, startRow,ouName, head));
							startRow++;
							sheet.mergeCells(startCol, startRow, startCol+5, startRow);
							sheet.addCell(new Label(startCol,startRow,"ใบแจ้งรายละเอียดการจ่ายค่าจ้าง  ", head));
							startRow++;
							sheet.mergeCells(startCol, startRow, startCol+5, startRow);
							sheet.addCell(new Label(startCol,startRow,"ประจำงวด  "+periodName+" พ.ศ."+year , head));
							startRow++;
							sheet.mergeCells(startCol+4, startRow, startCol+5, startRow);
							sheet.addCell(new Label(startCol+4,startRow," วันที่พิมพ์ : "+sdfPrint.format(gd.getTime()),headRight));
							
							startRow++;
						/* end title */
							seq = seq + 1;
							sheet.addCell(new Label(startCol, startRow,"ลำดับ" ,headAll));
							sheet.addCell(new Label(startCol+1, startRow,"เลขประจำตัว" ,headAll));
							sheet.addCell(new Label(startCol+2, startRow,"ชื่อสกุล" ,headAll));
							sheet.addCell(new Label(startCol+3, startRow,"เลขที่บัญชี" ,headAll));
							sheet.mergeCells(startCol+4, startRow, startCol+5, startRow);
							sheet.addCell(new Label(startCol+4, startRow,"เงินค่าจ้างสุทธิ" ,headAll));
							//sheet.addCell(new Label(startCol+5, startRow, String.valueOf(df.format(helVo.getNet())),borderNumber));
							startRow++;
							sheet.addCell(new Label(startCol, startRow, String.valueOf(seq), borderNumber));
							//sheet.addCell(new Label(startCol+1, startRow,"เลขประจำตัว" ,borderNumber));
							sheet.addCell(new Label(startCol+1, startRow, helVo.getEmpCode(), boldCenterFormat));
							//sheet.addCell(new Label(2, startRow,"ชื่อสกุล" ,borderNumber));
							sheet.addCell(new Label(startCol+2, startRow, helVo.getFullName(), borderLR));
							//sheet.addCell(new Label(2, startRow,"เลขที่บัญชี" ,borderNumber));
							sheet.addCell(new Label(startCol+3, startRow, helVo.getBankId(),boldCenterFormat));
							sheet.mergeCells(startCol+4, startRow, startCol+5, startRow);
							sheet.addCell(new Label(startCol+4, startRow, String.valueOf(df.format(helVo.getNet())),borderNumber));
							startRow++;
							sheet.addCell(new Label(startCol, startRow,"สังกัด" ,headAll));
							sheet.mergeCells(startCol+1, startRow, startCol+5, startRow);
							sheet.addCell(new Label(startCol+1, startRow, helVo.getOrgCode()+ " " + helVo.getAreaDesc() + " " + helVo.getSecDesc(), headLeftAll));
							startRow++;
							sheet.mergeCells(0, startRow, 1, startRow);
							sheet.addCell(new Label(0, startRow,"เงินได้สะสมถึงเดือนนี้" ,headLeftAll));
							sheet.addCell(new Label(2, startRow, String.valueOf(df.format(helVo.getIncAmt())),borderNumber));
							sheet.addCell(new Label(3, startRow,"ภาษีสะสมถึงเดือนนี้" ,headLeftAll));
							sheet.addCell(new Label(4, startRow, String.valueOf(df.format(helVo.getTaxAmt())),borderNumber));
							startRow++;
							startRow++;
							//if (String.valueOf(df.format(helVo.getNewSalary()))!= null && !String.valueOf(df.format(helVo.getNewSalary())).equals("0.00") ){
							//sheet.addCell(new Label(startCol+3, startRow, String.valueOf(df.format(helVo.getNewSalary())),borderNumber));
							//startRow++;
							//}
							
							sheet.addCell(new Label(1, startRow,"รายการเงินได้" ,headLeft));
							startRow++;
							if (String.valueOf(df.format(helVo.getSalary()))!= null && !String.valueOf(df.format(helVo.getSalary())).equals("0.00") ){
								sheet.addCell(new Label(startCol+2, startRow,"เงินค่าจ้าง",headAll));
							sheet.addCell(new Label(startCol+3, startRow, String.valueOf(df.format(helVo.getSalary())),borderNumber));
							startRow++;
							}
							if (String.valueOf(df.format(helVo.getSalary02()))!= null && !String.valueOf(df.format(helVo.getSalary02())).equals("0.00") ){
								sheet.addCell(new Label(startCol+2, startRow,"ปรับปรุงรายการรับ",headAll));
							sheet.addCell(new Label(startCol+3, startRow, String.valueOf(df.format(helVo.getSalary02())),borderNumber));
							startRow++;
							}
							
							if (String.valueOf(df.format(helVo.getSalary03()))!= null && !String.valueOf(df.format(helVo.getSalary03())).equals("0.00") ){
								sheet.addCell(new Label(startCol+2, startRow,"ปรับปรุงรายการหัก",headAll));
							sheet.addCell(new Label(startCol+3, startRow, String.valueOf(df.format(helVo.getSalary03())),borderNumber));
							startRow++;
							}
							
							if (String.valueOf(df.format(helVo.getKlongchev()))!= null && !String.valueOf(df.format(helVo.getKlongchev())).equals("0.00") ){
								sheet.addCell(new Label(startCol+2, startRow,"ค่าครองชีพ",headAll));
							sheet.addCell(new Label(startCol+3, startRow, String.valueOf(df.format(helVo.getKlongchev())),borderNumber));
							startRow++;
							}
							if (String.valueOf(df.format(helVo.getChilds()))!= null && !String.valueOf(df.format(helVo.getChilds())).equals("0.00") ){
								sheet.addCell(new Label(startCol+2, startRow,"ค่าช่วยเหลือบุตร",headAll));
							sheet.addCell(new Label(startCol+3, startRow, String.valueOf(df.format(helVo.getChilds())),borderNumber));
							startRow++;
							}
							
							sheet.addCell(new Label(startCol+2, startRow,"รวมเงินได้",headAll));
							sheet.addCell(new Label(startCol+3, startRow, String.valueOf(df.format(helVo.getNetIncome())),borderNumber));
							startRow++;
							startRow++;
							if (String.valueOf(df.format(helVo.getNetDec()))!= null && !String.valueOf(df.format(helVo.getNetDec())).equals("0.00") ){
							sheet.addCell(new Label(1, startRow,"รายการเงินหัก" ,headLeft));
							startRow++;
							}
							if (String.valueOf(df.format(helVo.getVinai2()))!= null && !String.valueOf(df.format(helVo.getVinai2())).equals("0.00") ){
								sheet.addCell(new Label(startCol+2, startRow,"หักค่าจ้าง",headAll));
							sheet.addCell(new Label(startCol+3, startRow, String.valueOf(df.format(helVo.getVinai2())),borderNumber));
							startRow++;
							}
							
							if (String.valueOf(df.format(helVo.getVinai1Pct()))!= null && !String.valueOf(df.format(helVo.getVinai1Pct())).equals("0.00") ){
								sheet.addCell(new Label(startCol+2, startRow,"หักวินัย",headAll));
							sheet.addCell(new Label(startCol+3, startRow, String.valueOf(df.format(helVo.getVinai1Pct())),borderNumber));
							startRow++;
							}
							
							if (String.valueOf(df.format(helVo.getVinai1Lev()))!= null && !String.valueOf(df.format(helVo.getVinai1Lev())).equals("0.00") ){
								sheet.addCell(new Label(startCol+2, startRow,"หักขาดงาน",headAll));
							sheet.addCell(new Label(startCol+3, startRow, String.valueOf(df.format(helVo.getVinai1Lev())),borderNumber));
							startRow++;
							}
							
							if (String.valueOf(df.format(helVo.getRefund()))!= null && !String.valueOf(df.format(helVo.getRefund())).equals("0.00") ){
								sheet.addCell(new Label(startCol+2, startRow,"รับเรียกคืน",headAll));
							sheet.addCell(new Label(startCol+3, startRow, String.valueOf(df.format(helVo.getRefund())),borderNumber));
							startRow++;
							}
							
							if (String.valueOf(df.format(helVo.getMotor()))!= null && !String.valueOf(df.format(helVo.getMotor())).equals("0.00") ){
								sheet.addCell(new Label(startCol+2, startRow,"กู้จักรยานยนต์",headAll));
							sheet.addCell(new Label(startCol+3, startRow, String.valueOf(df.format(helVo.getMotor())),borderNumber));
							startRow++;
							}
							
							if (String.valueOf(df.format(helVo.getRick()))!= null && !String.valueOf(df.format(helVo.getRick())).equals("0.00") ){
								sheet.addCell(new Label(startCol+2, startRow,"กู้ประสบภัย",headAll));
								sheet.addCell(new Label(startCol+3, startRow, String.valueOf(df.format(helVo.getRick())),borderNumber));
							startRow++;
							}
							
							if (String.valueOf(df.format(helVo.getStudy()))!= null && !String.valueOf(df.format(helVo.getStudy())).equals("0.00") ){
								sheet.addCell(new Label(startCol+2, startRow,"กู้ศึกษาบุตร",headAll));
								sheet.addCell(new Label(startCol+3, startRow, String.valueOf(df.format(helVo.getStudy())),borderNumber));
							startRow++;
							}
							
							if (String.valueOf(df.format(helVo.getLawLoan()))!= null && !String.valueOf(df.format(helVo.getLawLoan())).equals("0.00") ){
							sheet.addCell(new Label(startCol+2, startRow,"หักหนี้คำพิพากษา",headAll));
							sheet.addCell(new Label(startCol+3, startRow, String.valueOf(df.format(helVo.getLawLoan())),borderNumber));
							startRow++;
							}
							
							if (String.valueOf(df.format(helVo.getLoanPost()))!= null && !String.valueOf(df.format(helVo.getLoanPost())).equals("0.00") ){
							sheet.addCell(new Label(startCol+2, startRow,"หักรับสภาพหนี้",headAll));
							sheet.addCell(new Label(startCol+3, startRow, String.valueOf(df.format(helVo.getLoanPost())),borderNumber));
							startRow++;
							}
							
							if (String.valueOf(df.format(helVo.getSsr()))!= null && !String.valueOf(df.format(helVo.getSsr())).equals("0.00") ){
								sheet.addCell(new Label(startCol+2, startRow,"ค่าบำรุงสมาชิกสหภาพฯ",headAll));
								sheet.addCell(new Label(startCol+3, startRow, String.valueOf(df.format(helVo.getSsr())),borderNumber));
								startRow++;
								}
							if (String.valueOf(df.format(helVo.getKys()))!= null && !String.valueOf(df.format(helVo.getKys())).equals("0.00") ){
								sheet.addCell(new Label(startCol+2, startRow,"เงินกู้ กยศ.",headAll));
								sheet.addCell(new Label(startCol+3, startRow, String.valueOf(df.format(helVo.getKys())),borderNumber));
								startRow++;
								}
							if (String.valueOf(df.format(helVo.getKro()))!= null && !String.valueOf(df.format(helVo.getKro())).equals("0.00") ){
								sheet.addCell(new Label(startCol+2, startRow,"เงินกู้ กรอ.",headAll));
								sheet.addCell(new Label(startCol+3, startRow, String.valueOf(df.format(helVo.getKro())),borderNumber));
								startRow++;
								}
							
							
							
							if (String.valueOf(df.format(helVo.getNetDec()))!= null && !String.valueOf(df.format(helVo.getNetDec())).equals("0.00") ){
							sheet.addCell(new Label(startCol+2, startRow,"รวมเงินหัก",headAll));
							sheet.addCell(new Label(startCol+3, startRow, String.valueOf(df.format(helVo.getNetDec())),borderNumber));
							startRow++;
							startRow++;
							}
							
							sheet.mergeCells(4, startRow, 5, startRow);
							sheet.addCell(new Label(startCol+4,startRow,"สำเนาถูกต้อง",head));
							startRow++;
							startRow++;
							sheet.mergeCells(4, startRow, 5, startRow);
							sheet.addCell(new Label(startCol+4,startRow,"ชื่อ (.......................................)",head));
							//sheet.addCell(new Label(startCol+5,startRow,"(........................)",boldBigCenterFormat));
							
					    	startRow++;
					    	sheet.mergeCells(4, startRow, 5, startRow);
					    	sheet.addCell(new Label(startCol+4,startRow,"ตำแหน่ง (.............................)",head));
					    	//sheet.addCell(new Label(startCol+5,startRow,"(................)",boldBigCenterFormat));
					    	startRow++;
					    	sheet.mergeCells(4, startRow, 5, startRow);
					    	sheet.addCell(new Label(startCol+4,startRow,"วันที่   ......../........./..........",head));
					    	//sheet.addCell(new Label(startCol+5,startRow,"..../...../......",boldBigCenterFormat));
					    	startRow++;
					    	for(int j=startRow;(j%25!= 0);j++){
								sheet.addCell(new Label(startCol+2,startRow,"",borderNumber2));
								startRow++;
							}
					    }
							
							
							
						
		        //sheet.getSettings().setPassword("028313766");
				//sheet.getSettings().setProtected(true);
				
				
				
			}
		 else {
				//sheet.mergeCells(0, 4, 5, 4);
				Alignment noAlg = Alignment.getAlignment(1);
				WritableCellFormat border = new WritableCellFormat();
				border.setAlignment(Alignment.CENTRE);
				border.setBorder(Border.ALL, BorderLineStyle.THIN);
				//sheet.addCell(new Label(0,2, "พิมพ์โดย  "+userInfo.getUserName(), headLeft));
				//sheet.addCell(new Label(1, 2, "ประจำงวดที่ " + periodName + "  พ.ศ. " + year, headRight));
				//sheet.addCell(new Label(2, 2, "วันที่พิมพ์ : "+sdfPrint.format(gd.getTime()), headRight));
				sheet.addCell(new Label(0, 2, "ไม่พบข้อมูล", border));
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
