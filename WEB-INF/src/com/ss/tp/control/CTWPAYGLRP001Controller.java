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


import com.ss.tp.dto.VFeeWpayGlTransferVO;
import com.ss.tp.security.UserInfo;

import com.ss.tp.service.FeeWpayPrEmployeeService;
import com.ss.tp.service.SuOrganizeService;

/**
 * @author bowpoko
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CTWPAYGLRP001Controller extends MultiActionController {



	private String ConvertMonth(String evaMonth) {
		int m = Integer.parseInt(evaMonth);
		String monthText = "";
		if (m == 1) {
			monthText = "มกราคม";
		} else if (m == 2) {
			monthText = "กุมภาพันธ์";
		} else if (m == 3) {
			monthText = "มีนาคม";
		} else if (m == 4) {
			monthText = "เมษายน";
		} else if (m == 5) {
			monthText = "พฤษภาคม";
		} else if (m == 6) {
			monthText = "มิถุนายน";
		} else if (m == 7) {
			monthText = "กรกฎาคม";
		} else if (m == 8) {
			monthText = "สิงหาคม";
		} else if (m == 9) {
			monthText = "กันยายน";
		} else if (m == 10) {
			monthText = "ตุลาคม";
		} else if (m == 11) {
			monthText = "พฤศจิกายน";
		} else if (m == 12) {
			monthText = "ธันวาคม";
		}
		return monthText;
	}

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
		
	
		
		String evaYear = "";
		String evaMonth = "";
		
		
		String ouCode ="001";
	
		double sumAllDebitMoney = 0.00;
		double sumAllCreditMoney = 0.00;

		DecimalFormat df = new DecimalFormat("###,##0.00");
		DecimalFormat dfInt = new DecimalFormat("###,##0");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition",
				"attachment; filename=./page/report/CTWPAYGLRP001.xls");
		InputStream in = this.getServletContext().getResourceAsStream(
				"/page/report/CTWPAYGLRP001.xls");
		
		if (request.getParameter("workYear") != null
				&& !"".equals(request.getParameter("workYear"))) {
			evaYear =request.getParameter("workYear");
		}
		if (request.getParameter("workMonth") != null
				&& !"".equals(request.getParameter("workMonth"))) {
			evaMonth = request.getParameter("workMonth");
			//evaMonth = request.getParameter("workMonth");
		}

		try {
			Workbook wb = Workbook.getWorkbook(in);
			WritableWorkbook ww = Workbook.createWorkbook(response.getOutputStream(), wb);
			
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
			borderNumber.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			borderNumber.setAlignment(alg.RIGHT);
			borderNumber.setFont(font);

			WritableCellFormat borderMoney = new WritableCellFormat();
			borderMoney.setBorder(Border.LEFT, BorderLineStyle.THIN);
			borderMoney.setAlignment(alg.RIGHT);
			borderMoney.setFont(font);

			WritableCellFormat borderNo = new WritableCellFormat();
			// borderNo.setBorder(Border.LEFT, BorderLineStyle.THIN);
			borderNo.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			borderNo.setAlignment(alg.CENTRE);
			borderNo.setFont(font);

			WritableCellFormat borderNoo = new WritableCellFormat();
			// borderNo.setBorder(Border.LEFT, BorderLineStyle.THIN);
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
			// borderNumber2.setBorder(Border.ALL, BorderLineStyle.THIN);
			borderNumber2.setBorder(Border.LEFT, BorderLineStyle.THIN);
			borderNumber2.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			borderNumber2.setAlignment(alg.RIGHT);
			borderNumber2.setFont(font);

			WritableCellFormat borderNumber3 = new WritableCellFormat();
			borderNumber3.setBorder(Border.ALL, BorderLineStyle.THIN);
			borderNumber3.setAlignment(alg.RIGHT);
			borderNumber3.setFont(fontBold);

			WritableCellFormat borderData3 = new WritableCellFormat();
			// borderNumber2.setBorder(Border.ALL, BorderLineStyle.THIN);
			borderData3.setBorder(Border.LEFT, BorderLineStyle.THIN);
			borderData3.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			borderData3.setAlignment(alg.LEFT);
			borderData3.setFont(font);

			WritableCellFormat borderData4 = new WritableCellFormat();
			// borderNumber2.setBorder(Border.ALL, BorderLineStyle.THIN);
			borderData4.setBorder(Border.LEFT, BorderLineStyle.THIN);
			borderData4.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			borderData4.setAlignment(alg.RIGHT);
			borderData4.setFont(fontBold);

			WritableCellFormat borderData5 = new WritableCellFormat();
			// borderNumber2.setBorder(Border.ALL, BorderLineStyle.THIN);
			borderData5.setBorder(Border.ALL, BorderLineStyle.MEDIUM);
			// borderData5.setBorder(Border.LEFT, BorderLineStyle.THIN);
			// borderData5.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			borderData5.setAlignment(alg.RIGHT);
			borderData5.setFont(fontBold);

			WritableCellFormat borderDataLR = new WritableCellFormat();
			// borderNumber2.setBorder(Border.ALL, BorderLineStyle.THIN);
			borderDataLR.setBorder(Border.LEFT, BorderLineStyle.THIN);
			borderDataLR.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			// borderData5.setBorder(Border.LEFT, BorderLineStyle.THIN);
			// borderData5.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			// borderDataLR.setAlignment(alg.RIGHT);
			// .setFont(fontBold);

			WritableCellFormat borderDataLRT = new WritableCellFormat();
			// borderNumber2.setBorder(Border.ALL, BorderLineStyle.THIN);
			borderDataLRT.setBorder(Border.TOP, BorderLineStyle.THIN);
			borderDataLRT.setBorder(Border.LEFT, BorderLineStyle.THIN);
			borderDataLRT.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			// borderData5.setBorder(Border.LEFT, BorderLineStyle.THIN);
			// borderData5.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			// borderDataLR.setAlignment(alg.RIGHT);
			// .setFont(fontBold);

			CellFormat head = sheet.getWritableCell(0 + 1, 0).getCellFormat();

			CellFormat headLeft = sheet.getWritableCell(0, 2).getCellFormat();
			CellFormat headRight = sheet.getWritableCell(3, 2).getCellFormat();
		
			GregorianCalendar gd = new GregorianCalendar();
			SimpleDateFormat sdfPrint = new SimpleDateFormat(
					"dd/MM/yyyy HH:mm", new java.util.Locale("th", "TH"));
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute("UserLogin");
			

			/* start title */
			sheet.mergeCells(2, 1, 3, 1);
			sheet.addCell(new Label(2, 1,ouName, head));
			sheet.addCell(new Label(0, 2,"พิมพ์โดย  " + userInfo.getUserName(), headLeft));
			sheet.addCell(new Label(2, 2,"                 ประจำเดือน  "+ ConvertMonth(evaMonth)	+ " พ.ศ."+ evaYear+ "                  วันที่พิมพ์ : "+ sdfPrint.format(gd.getTime()), headRight));
			/* end title */

			VFeeWpayGlTransferVO wpayVo = null;
			List Rep01 = this.getFeeWpayPrEmployeeService().feeWpayGlTransferReport(evaYear,evaMonth);
			int startRow = 6;

		

			String flagTotalHour = "";
			int seq = 0;
			//sheet.mergeCells(0, startRow, 1, startRow);
			sheet.addCell(new Blank(0, startRow, borderData));
			sheet.addCell(new Blank(1, startRow, borderData));
			sheet.addCell(new Blank(2, startRow, borderData));
			sheet.addCell(new Blank(3, startRow, borderData));
			sheet.addCell(new Blank(4, startRow, borderData));
		
			startRow++;
			if (Rep01.size() != 0) {
				for (int i = 0; i < Rep01.size(); i++) {
					wpayVo = (VFeeWpayGlTransferVO) Rep01.get(i);

					sheet.addCell(new Blank(0, startRow, borderData));
					sheet.addCell(new Blank(1, startRow, borderData));
					sheet.addCell(new Blank(2, startRow, borderData));
					sheet.addCell(new Blank(3, startRow, borderData));
					sheet.addCell(new Blank(4, startRow, borderData));
					
					sheet.setRowView(startRow, 320);
					startRow++;
					seq = seq + 1;
					sheet.addCell(new Label(0, startRow, String.valueOf(seq),borderNumber));
					sheet.addCell(new Label(1, startRow, wpayVo.getAccountCode(), borderNoo));
					sheet.addCell(new Label(2, startRow, wpayVo.getAccountName(), borderData3));
					if (wpayVo.getDebit()== null){
					sheet.addCell(new Label(3, startRow,"", borderNumber));	
					}	
					else{
					sheet.addCell(new Label(3, startRow, String.valueOf(df.format(wpayVo.getDebit())), borderNumber));
					}
					if (wpayVo.getCredit()== null){
					sheet.addCell(new Label(4, startRow,"", borderNumber));	
					}else{
					sheet.addCell(new Label(4, startRow, String.valueOf(df.format(wpayVo.getCredit())), borderNumber));	
					}
					
					startRow++;
					flagTotalHour = "sum";

					if (wpayVo.getDebit() != null) {
						sumAllDebitMoney = sumAllDebitMoney
								+ wpayVo.getDebit().doubleValue();

					}

					if (wpayVo.getCredit() != null) {
						sumAllCreditMoney = sumAllCreditMoney
								+ wpayVo.getCredit().doubleValue();

					}

				}
				

			

				
				sheet.addCell(new Label(0, startRow, "", borderEndLine));
				sheet.addCell(new Label(1, startRow, "", borderEndLine));
				sheet.addCell(new Label(2, startRow, "", borderEndLine));
				sheet.addCell(new Label(3, startRow, "", borderEndLine));
				sheet.addCell(new Label(4, startRow, "", borderEndLine));

				//startRow++;

				sheet.mergeCells(0, startRow, 2, startRow);
				//sheet.addCell(new Label(0, startRow, "", borderDataLR));
				sheet.addCell(new Label(0, startRow, "รวม", borderNumber3));
				sheet.addCell(new Label(3, startRow, String.valueOf(df.format(sumAllDebitMoney)), borderNumber3));
				sheet.addCell(new Label(4, startRow, String.valueOf(df.format(sumAllCreditMoney)), borderNumber3));

				// sumTotalLevelGroup1 = 0.0;

				startRow++;

				
				sheet.addCell(new Label(0, startRow, "", borderEndLine));
				sheet.addCell(new Label(1, startRow, "", borderEndLine));
				sheet.addCell(new Label(2, startRow, "", borderEndLine));
				sheet.addCell(new Label(3, startRow, "", borderEndLine));
				sheet.addCell(new Label(4, startRow, "", borderEndLine));
			

				sheet.getSettings().setPassword("028313766");
				sheet.getSettings().setProtected(true);
				sheet.mergeCells(0, startRow, 1, startRow);

				startRow++;

			} else {
				Alignment noAlg = Alignment.getAlignment(1);
				WritableCellFormat border = new WritableCellFormat();
				border.setAlignment(Alignment.CENTRE);
				border.setBorder(Border.ALL, BorderLineStyle.THIN);
				sheet.mergeCells(2, 1, 3, 1);
				sheet.addCell(new Label(2, 1,ouName, head));
				sheet.addCell(new Label(0, 2,"พิมพ์โดย  " + userInfo.getUserName(), headLeft));
				sheet.addCell(new Label(2, 2,"                 ประจำเดือน  "+ ConvertMonth(evaMonth)	+ " พ.ศ."+ evaYear+ "                  วันที่พิมพ์ : "+ sdfPrint.format(gd.getTime()), headRight));
				sheet.mergeCells(0, 7, 4, 7);
				sheet.addCell(new Label(0,7, "ไม่พบข้อมูล", border));
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

	

}
