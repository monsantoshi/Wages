/*
 * Created on 17 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.control;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

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

import com.ss.tp.dto.VFeeWpayRwVinaiVO;
import com.ss.tp.security.UserInfo;
import com.ss.tp.service.SuOrganizeService;
import com.ss.tp.service.VFeeWpayRwVinaiService;

/**
 * @author
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class CTWPAYRP009Controller extends MultiActionController {

	// SimpleDateFormat sdf3 = new
	// SimpleDateFormat("EEE dd/MM/yyyy HH:mm:ss",Locale.ENGLISH);
	// SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm a",Locale.ENGLISH);

	private String ChgNullToEmpty(String str1, String str2) {
		if (str1 == null) {
			str1 = str2;
		}
		return str1;
	}

	private VFeeWpayRwVinaiService getVFeeWpayRwVinaiService() {
		return (VFeeWpayRwVinaiService) this.getApplicationContext().getBean(
				"vFeeWpayRwVinaiService");
	}

	public SuOrganizeService getSuOrganizeService() {
		return (SuOrganizeService) this.getApplicationContext().getBean(
				"suOrganizeService");
	}

	public ModelAndView doPrintReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy", new Locale(
				"th", "TH"));
		DecimalFormat df = new DecimalFormat("###,##0.00");
		DecimalFormat dfInt = new DecimalFormat("###,##0");

		String ouCode = "";
		String userId = "";
		int year = 0;
		int period = 0;
		String section = "";
		String flag = "";

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

		if (request.getParameter("flag") != null) {
			flag = request.getParameter("flag");
		}

		if (request.getParameter("section") != null) {
			section = request.getParameter("section");
		}

		List empList = this.getVFeeWpayRwVinaiService().rwVinaiReport(userId, ouCode,
				year, period, flag);
		String ouDesc = this.getSuOrganizeService().findOrganizeName(ouCode);

		// ----------------- Generate Report Detail
		// -------------------------------

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition",
				"attachment; filename=./page/report/CTWPAYRP009.xls");
		InputStream in = this.getServletContext().getResourceAsStream(
				"/page/report/CTWPAYRP009.xls");

		// ///////////////////// Set Format //////////////////////////
		WritableFont fontBold = new WritableFont(WritableFont.ARIAL);
		fontBold.setBoldStyle(WritableFont.BOLD);
		fontBold.setPointSize(9);

		WritableFont fontNormal = new WritableFont(WritableFont.ARIAL);
		fontNormal.setBoldStyle(WritableFont.NO_BOLD);
		fontNormal.setPointSize(9);

		Alignment dataAlignLeft = Alignment.LEFT;
		Alignment dataAlignRight = Alignment.RIGHT;
		Alignment dataAlignCenter = Alignment.CENTRE;

		WritableCellFormat boldLeftFormat = new WritableCellFormat();
		boldLeftFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
		boldLeftFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		boldLeftFormat.setAlignment(dataAlignLeft);
		boldLeftFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		boldLeftFormat.setFont(fontBold);

		WritableCellFormat HeadFormat = new WritableCellFormat();
		HeadFormat.setAlignment(dataAlignCenter);
		HeadFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		HeadFormat.setFont(fontBold);

		WritableCellFormat HeadLeftFormat = new WritableCellFormat();
		HeadLeftFormat.setAlignment(dataAlignLeft);
		HeadLeftFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		HeadLeftFormat.setFont(fontBold);

		WritableCellFormat borderNumber2 = new WritableCellFormat();
		borderNumber2.setBorder(Border.ALL, BorderLineStyle.THIN);
		borderNumber2.setAlignment(dataAlignRight);
		borderNumber2.setFont(fontBold);

		WritableCellFormat normalLeftFormat = new WritableCellFormat();
		normalLeftFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
		normalLeftFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		normalLeftFormat.setAlignment(dataAlignLeft);
		normalLeftFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		normalLeftFormat.setFont(fontNormal);

		WritableCellFormat normalCenterFormat = new WritableCellFormat();
		normalCenterFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
		normalCenterFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		normalCenterFormat.setAlignment(dataAlignCenter);
		normalCenterFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		normalCenterFormat.setFont(fontNormal);

		WritableCellFormat normalRightFormat = new WritableCellFormat();
		normalRightFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
		normalRightFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		normalRightFormat.setAlignment(dataAlignRight);
		normalRightFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		normalRightFormat.setFont(fontNormal);

		WritableCellFormat normalLeftLastFormat = new WritableCellFormat();
		normalLeftLastFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
		normalLeftLastFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		normalLeftLastFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
		normalLeftLastFormat.setAlignment(dataAlignLeft);
		normalLeftLastFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		normalLeftLastFormat.setFont(fontNormal);

		WritableCellFormat normalCenterLastFormat = new WritableCellFormat();
		normalCenterLastFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
		normalCenterLastFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		normalCenterLastFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
		normalCenterLastFormat.setAlignment(dataAlignCenter);
		normalCenterLastFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		normalCenterLastFormat.setFont(fontNormal);

		WritableCellFormat normalRightLastFormat = new WritableCellFormat();
		normalRightLastFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
		normalRightLastFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		normalRightLastFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
		normalRightLastFormat.setAlignment(dataAlignRight);
		normalRightLastFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		normalRightLastFormat.setFont(fontNormal);
		// /////////////////////////////////////////////////////////////

		Workbook wb = Workbook.getWorkbook(in);
		WritableWorkbook ww = Workbook.createWorkbook(
				response.getOutputStream(), wb);
		WritableSheet sheet1 = ww.getSheet(0);

		CellFormat headCenter = sheet1.getWritableCell(3, 2).getCellFormat();
		CellFormat headRight = sheet1.getWritableCell(11, 2).getCellFormat();
		GregorianCalendar gd = new GregorianCalendar();
		SimpleDateFormat sdfPrint = new SimpleDateFormat("dd/MM/yyyy HH:mm",
				new java.util.Locale("th", "TH"));
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		sheet1.getSettings().setPassword("028313766");
		sheet1.getSettings().setProtected(true);
		// sheet1.addCell(new Label(0,
		// 2,"๏ฟฝ๏ฟฝะจำงวด "+section+" ๏ฟฝ.๏ฟฝ. "+year+" ๏ฟฝัน๏ฟฝ๏ฟฝ๏ฟฝ๏ฟฝ~๏ฟฝ :
		// "+sdfPrint.format(gd.getTime()),headRight));
		sheet1.addCell(new Label(0,2, "พิมพ์โดย  "+userInfo.getUserName()+"                  "+"              ประจำงวด "+section+" พ.ศ. "+year+"              "+"          วันที่พิมพ์ : "+sdfPrint.format(gd.getTime()), HeadLeftFormat));
	
		if (flag.equals("N")){
			sheet1.addCell(new Label(0, 3,"ประเภทรายการ ปกติ",HeadFormat));
		}
		else if(flag.equals("A")){
			sheet1.addCell(new Label(0, 3,"ประเภทรายการ ปรับปรุงรายการหัก",HeadFormat));
		}
		else{
			sheet1.addCell(new Label(0, 3,"ประเภทรายการ ทั้งหมด",HeadFormat));
		}
		if (empList.size() > 0) {

			int startRow = 6;
			String orgDesc = "";
			int row = startRow;
			int count = 0;
			double sumDecSal = 0.0;
			double sumAbsDay1 = 0.0;
			double sumAbsDay2 = 0.0;
			double sumTotalDay = 0.0;
			double sumDecDay = 0.0;
			double sumAllDecSal = 0.0;
			double sumAllAbsDay1 = 0.0;
			double sumAllAbsDay2 = 0.0;
			double sumAllTotalDay = 0.0;
			double sumAllDecDay = 0.0;
			String flagSum = "";

			int countRow = 1;

			for (Iterator itt = empList.iterator(); itt.hasNext();) {
				VFeeWpayRwVinaiVO vo = (VFeeWpayRwVinaiVO) itt.next();
				count = count + 1;
				if (!orgDesc.equals(vo.getOrgDesc())) {
					if (!flagSum.equals("")) {
						sheet1.mergeCells(0, row, 2, row);
						sheet1.addCell(new Label(0, row, "รวม", borderNumber2));
						sheet1.mergeCells(3, row, 4, row);
						sheet1.addCell(new Label(3, row,convertNumberFormat(new Double(sumDecSal)),borderNumber2));
						sheet1.mergeCells(5, row, 8, row);
						sheet1.addCell(new Label(5, row, "", borderNumber2));

						sheet1.addCell(new Label(
								9,
								row,
								convertDoubleNumberFormat(new Double(sumAbsDay1)),
								borderNumber2));
						sheet1.mergeCells(10, row, 12, row);
						sheet1.addCell(new Label(10, row, "", borderNumber2));
						sheet1.addCell(new Label(14, row,"",borderNumber2));
						sumDecSal = 0.0;
						sumAbsDay1 = 0.0;
						sumAbsDay2 = 0.0;
						sumTotalDay = 0.0;
						sumDecDay = 0.0;
						flagSum = "";
						row++;
					}
					sheet1.setRowView(row, 320);
					sheet1.addCell(new Label(0, row, ChgNullToEmpty(
							vo.getOrgDesc(), ""), boldLeftFormat));
					sheet1.addCell(new Blank(1, row, boldLeftFormat));
					sheet1.addCell(new Blank(2, row, boldLeftFormat));
					sheet1.addCell(new Blank(3, row, boldLeftFormat));
					sheet1.addCell(new Blank(4, row, boldLeftFormat));
					sheet1.addCell(new Blank(5, row, boldLeftFormat));
					sheet1.addCell(new Blank(6, row, boldLeftFormat));
					sheet1.addCell(new Blank(7, row, boldLeftFormat));
					sheet1.addCell(new Blank(8, row, boldLeftFormat));
					sheet1.addCell(new Blank(9, row, boldLeftFormat));
					sheet1.addCell(new Blank(10, row, boldLeftFormat));
					sheet1.addCell(new Blank(11, row, boldLeftFormat));
					sheet1.addCell(new Blank(12, row, boldLeftFormat));
					sheet1.addCell(new Blank(13, row, boldLeftFormat));
					sheet1.addCell(new Blank(14, row, boldLeftFormat));
					orgDesc = ChgNullToEmpty(vo.getOrgDesc(), "");
					row++;
				}

				if (empList.size() == count) {
					sheet1.setRowView(row, 320);
					sheet1.addCell(new Label(0, row, String.valueOf(countRow),
							normalCenterLastFormat));
					sheet1.addCell(new Label(1, row, vo.getEmpCode(),
							normalCenterLastFormat));
					sheet1.addCell(new Label(2, row, vo.getEmpName(),
							normalLeftLastFormat));
					sheet1.addCell(new Label(0, row, String.valueOf(countRow),
							normalCenterLastFormat));
				
					
					if (vo.getCutSal() != null) {
						sheet1.addCell(new Label(6, row, String.valueOf(vo
								.getCutSal()), normalCenterLastFormat));
					} else {
						sheet1.addCell(new Blank(6, row, normalRightLastFormat));
					}
					if (vo.getCutSalPercent() != null) {
						sheet1.addCell(new Label(7, row, String.valueOf(vo
								.getCutSalPercent().intValue()),
								normalCenterLastFormat));
					} else {
						sheet1.addCell(new Blank(7, row, normalRightLastFormat));
					}
					if (vo.getAbsYear1() != null) {
						sheet1.addCell(new Label(8, row, String.valueOf(vo
								.getAbsYear1()), normalCenterLastFormat));
					} else {
						sheet1.addCell(new Blank(8, row, normalRightLastFormat));
					}
					if (vo.getAbsDay1() != null) {
						sheet1.addCell(new Label(9, row, String.valueOf(df
								.format(vo.getAbsDay1())),
								normalCenterLastFormat));
					} else {
						sheet1.addCell(new Blank(9, row, normalRightLastFormat));
					}
					String flagDesc = "";
					if(vo.getFlagPr() != null){
						if(vo.getFlagPr().equals("A"))
							flagDesc="ปรับปรุงรายการหัก";
						else if(vo.getFlagPr().equals("N"))
							flagDesc="";
					}
					sheet1.addCell(new Label(14, row, flagDesc,
							normalCenterLastFormat));

					countRow++;
					flagSum = "sum";
					
					if (vo.getAbsDay1() != null) {
						sumAbsDay1 += vo.getAbsDay1().doubleValue();
						sumAllAbsDay1 += vo.getAbsDay1().doubleValue();

					}
					
					
				
					row++;
					if (!flagSum.equals("")) {
						sheet1.mergeCells(0, row, 2, row);
						sheet1.mergeCells(0, row, 2, row);
						sheet1.addCell(new Label(0, row, "รวม", borderNumber2));
						sheet1.mergeCells(3, row, 4, row);
						sheet1.mergeCells(5, row, 8, row);
						sheet1.addCell(new Label(5, row, "", borderNumber2));

						sheet1.addCell(new Label(
								9,
								row,
								convertDoubleNumberFormat(new Double(sumAbsDay1)),
								borderNumber2));
						sheet1.mergeCells(10, row, 12, row);
						sheet1.addCell(new Label(10, row, "", borderNumber2));
						sheet1.addCell(new Label(14, row, "", borderNumber2));
					
						sumDecSal = 0.0;
						sumAbsDay1 = 0.0;
						sumAbsDay2 = 0.0;
						sumTotalDay = 0.0;
						sumDecDay = 0.0;
						flagSum = "";
						row++;
					}
				} else {
					sheet1.setRowView(row, 320);
					sheet1.addCell(new Label(0, row, String.valueOf(countRow),
							normalCenterFormat));
					sheet1.addCell(new Label(1, row, vo.getEmpCode(),
							normalCenterFormat));
					sheet1.addCell(new Label(2, row, vo.getEmpName(),
							normalLeftFormat));
				
					
					if (vo.getCutSal() != null) {
						sheet1.addCell(new Label(6, row, String.valueOf(vo.getCutSal()), normalCenterFormat));
					} else {
						sheet1.addCell(new Blank(6, row, normalRightFormat));
					}
					if (vo.getCutSalPercent() != null) {
						sheet1.addCell(new Label(7, row, String.valueOf(vo.getCutSalPercent().intValue()),
								normalCenterFormat));
					} else {
						sheet1.addCell(new Blank(7, row, normalRightFormat));
					}
					if (vo.getAbsYear1() != null) {
						sheet1.addCell(new Label(8, row, String.valueOf(vo.getAbsYear1()), normalCenterFormat));
					} else {
						sheet1.addCell(new Blank(8, row, normalRightFormat));
					}
					if (vo.getAbsDay1() != null) {
						sheet1.addCell(new Label(9, row, String.valueOf(df.format(vo.getAbsDay1())), normalCenterFormat));
					} else {
						sheet1.addCell(new Blank(9, row, normalRightFormat));
					}
					
					String flagDesc = "";
					if(vo.getFlagPr() != null){
						if(vo.getFlagPr().equals("A"))
							flagDesc="ปรับปรุงรายการหัก";
						else if(vo.getFlagPr().equals("N"))
							flagDesc="";
					}
					sheet1.addCell(new Label(14, row, flagDesc, normalCenterFormat));
					
				
					countRow++;
					flagSum = "sum";
				
					if (vo.getAbsDay1() != null) {
						sumAbsDay1 += vo.getAbsDay1().doubleValue();
						sumAllAbsDay1 += vo.getAbsDay1().doubleValue();

					}
					if (vo.getAbsDay2() != null) {
						sumAbsDay2 += vo.getAbsDay2().doubleValue();
						sumAllAbsDay2 += vo.getAbsDay2().doubleValue();

					}
					
				
					row++;

				}
			}
			sheet1.mergeCells(0, row, 2, row);
			sheet1.addCell(new Label(0, row, "รวมทั้งหมด", borderNumber2));
			sheet1.mergeCells(3, row, 4, row);
			sheet1.mergeCells(5, row, 8, row);
			sheet1.addCell(new Label(5, row, "", borderNumber2));

			sheet1.addCell(new Label(9, row,convertDoubleNumberFormat(new Double(sumAllAbsDay1)),borderNumber2));
			sheet1.mergeCells(10, row, 12, row);
			sheet1.addCell(new Label(10, row, "", borderNumber2));
			sheet1.addCell(new Label(13, row,"",borderNumber2));
			sheet1.addCell(new Label(14, row,"",borderNumber2));
			ww.write();
			ww.close();
			wb.close();
			in.close();
			return null;

		} else {
			sheet1.addCell(new Label(0, 0, ouDesc, HeadFormat));
			sheet1.setRowView(6, 320);
			sheet1.mergeCells(0, 6, 14, 6);
			sheet1.addCell(new Label(0,2, "พิมพ์โดย  "+userInfo.getUserName()+"                  "+"              ประจำงวด "+section+" พ.ศ. "+year+"              "+"          วันที่พิมพ์ : "+sdfPrint.format(gd.getTime()), HeadLeftFormat));

			if (flag.equals("N")){
				sheet1.addCell(new Label(0, 3,"ประเภทรายการ ปกติ",HeadFormat));
			}
			else if(flag.equals("A")){
				sheet1.addCell(new Label(0, 3,"ประเภทรายการ ปรับปรุงรายการหัก",HeadFormat));
			}
			else{
				sheet1.addCell(new Label(0, 3,"ประเภทรายการ ทั้งหมด",HeadFormat));
			}
			sheet1.addCell(new Label(0, 6, "ไม่พบข้อมูล",normalCenterLastFormat));		
			ww.write();
			ww.close();
			wb.close();
			in.close();
			return null;
		}

	}

	/**
	 * method ๏ฟฝ๏ฟฝ๏ฟฝ๏ฟฝับ๏ฟฝลง Number Format
	 * 
	 * @param hour
	 * @return
	 */
	private String convertNumberFormat(Double hour) {
		String rlst = "";
		DecimalFormat dec = new DecimalFormat("###,##0");
		rlst = dec.format(hour != null ? hour.intValue() : 0);
		return rlst;
	}

	private String convertDoubleNumberFormat(Double hour) {
		String rlst = "";
		DecimalFormat dec = new DecimalFormat("###,##0.00");
		rlst = dec.format(hour != null ? hour.doubleValue() : 0);

		return rlst;
	}
}
