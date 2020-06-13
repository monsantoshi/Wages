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

import com.ss.tp.dto.VFeeWpayRwVinai2VO;
import com.ss.tp.security.UserInfo;
import com.ss.tp.service.SuOrganizeService;
import com.ss.tp.service.FeeWpayRwVinai2Service;

/**
 * @author
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class CTWPAYRP010Controller extends MultiActionController {

	// SimpleDateFormat sdf3 = new
	// SimpleDateFormat("EEE dd/MM/yyyy HH:mm:ss",Locale.ENGLISH);
	// SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm a",Locale.ENGLISH);

	private String ChgNullToEmpty(String str1, String str2) {
		if (str1 == null) {
			str1 = str2;
		}
		return str1;
	}

	private FeeWpayRwVinai2Service getFeeWpayRwVinai2Service() {
		return (FeeWpayRwVinai2Service) this.getApplicationContext().getBean("feeWpayRwVinai2Service");
	}

	public SuOrganizeService getSuOrganizeService() {
		return (SuOrganizeService) this.getApplicationContext().getBean("suOrganizeService");
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

		List empList = this.getFeeWpayRwVinai2Service().rwVinai2Report(userId, ouCode,
				year, period, flag);
		String ouDesc = this.getSuOrganizeService().findOrganizeName(ouCode);

		// ----------------- Generate Report Detail
		// -------------------------------

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition",
				"attachment; filename=./page/report/CTWPAYRP010.xls");
		InputStream in = this.getServletContext().getResourceAsStream(
				"/page/report/CTWPAYRP010.xls");

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
		sheet1.addCell(new Label(0, 2,"พิมพ์โดย  "+userInfo.getUserName()+"                                      "+"ประจำงวด "+section+"   พ.ศ. "+year+"                                    "+"วันที่พิมพ์ : "+sdfPrint.format(gd.getTime()),HeadLeftFormat));
		if (flag.equals("A")){
			sheet1.addCell(new Label(0, 3,"ประเภทวัน เพิ่มวัน",HeadFormat));
		}
		else if(flag.equals("R")){
			sheet1.addCell(new Label(0, 3,"ประเภทวัน ลดวัน",HeadFormat));
		}else {
			sheet1.addCell(new Label(0,3," ประเภทรายการทั้งหมด",HeadFormat));
		}
	
		if (empList.size() > 0) {

			int startRow = 6;
			String orgDesc = "";
			int row = startRow;
			int count = 0;
			
			
		
		
			double sumDecDay = 0.0;
			
		
		
			double sumAllDecDay = 0.0;
			String flagSum = "";

			int countRow = 1;

			for (Iterator itt = empList.iterator(); itt.hasNext();) {
				VFeeWpayRwVinai2VO vo = (VFeeWpayRwVinai2VO) itt.next();
				count = count + 1;
				if (!orgDesc.equals(vo.getOrgDesc())) {
					if (!flagSum.equals("")) {
						sheet1.mergeCells(0, row, 3, row);
						sheet1.addCell(new Label(0, row, "รวม", borderNumber2));
						sheet1.mergeCells(3, row, 4, row);
						
						sheet1.addCell(new Label(5, row, "", borderNumber2));
						sheet1.addCell(new Label(6, row, "", borderNumber2));
					
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
					if (vo.getYear1() != null) {
						sheet1.addCell(new Label(3, row, vo.getYear1(), normalRightLastFormat));
					} else {
						sheet1.addCell(new Blank(3, row, normalRightLastFormat));
					}
				
					if (vo.getDecDay() != null) {
						sheet1.addCell(new Label(4, row, String.valueOf(df.format(vo.getDecDay())), normalRightLastFormat));
					} else {
						sheet1.addCell(new Blank(4, row, normalRightLastFormat));
					}
					if (vo.getFlagPr().equals("A")){
						sheet1.addCell(new Label(5, row,"เพิ่มวันทำงาน",normalCenterLastFormat));
					}else if(vo.getFlagPr().equals("R")){
						sheet1.addCell(new Label(5, row,"ลดวันทำงาน",normalCenterLastFormat));
					}
					
					if (vo.getRemark() != null) {
						sheet1.addCell(new Label(6, row, vo.getRemark(), normalLeftLastFormat));
					} else {
						sheet1.addCell(new Blank(6, row, normalLeftLastFormat));
					}
					countRow++;
					flagSum = "sum";
				
					if (vo.getDecDay() != null) {
						sumDecDay += vo.getDecDay().doubleValue();
						sumAllDecDay += vo.getDecDay().doubleValue();
					}
					row++;
					if (!flagSum.equals("")) {
						
						sheet1.mergeCells(0, row, 3, row);
						sheet1.addCell(new Label(0, row, "รวม", borderNumber2));
						sheet1.addCell(new Label(
								4,
								row,
								convertDoubleNumberFormat(new Double(sumDecDay)),
								borderNumber2));
						sheet1.addCell(new Label(5, row, "", borderNumber2));
						sheet1.addCell(new Label(6, row, "", borderNumber2));
						
						
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
					if (vo.getYear1() != null) {
						sheet1.addCell(new Label(3, row, vo.getYear1(), normalRightFormat));
					} else {
						sheet1.addCell(new Blank(3, row, normalRightFormat));
					}
				
					if (vo.getDecDay() != null) {
						sheet1.addCell(new Label(4, row, String.valueOf(df.format(vo.getDecDay())), normalRightFormat));
					} else {
						sheet1.addCell(new Blank(4, row, normalRightFormat));
					}
					if (vo.getFlagPr().equals("A")){
							sheet1.addCell(new Label(5, row,"เพิ่มวันทำงาน",normalCenterFormat));
					}else if(vo.getFlagPr().equals("R")){
							sheet1.addCell(new Label(5, row,"ลดวันทำงาน",normalCenterFormat));
					}
					
					if (vo.getRemark() != null) {
						sheet1.addCell(new Label(6, row, vo.getRemark(), normalLeftFormat));
					} else {
						sheet1.addCell(new Blank(6, row, normalLeftFormat));
					}
				
					countRow++;
					flagSum = "sum";
					
					if (vo.getDecDay() != null) {
						sumDecDay += vo.getDecDay().doubleValue();
						sumAllDecDay += vo.getDecDay().doubleValue();
					}

					row++;

				}
			}
			sheet1.mergeCells(0, row, 3, row);
			sheet1.addCell(new Label(0, row, "รวมทั้งหมด", borderNumber2));
			sheet1.addCell(new Label(4, row,
					convertDoubleNumberFormat(new Double(sumAllDecDay)),
					borderNumber2));
			sheet1.addCell(new Label(5, row, "", borderNumber2));
			sheet1.addCell(new Label(6, row, "", borderNumber2));
		
			ww.write();
			ww.close();
			wb.close();
			in.close();
			return null;

		} else {
			sheet1.addCell(new Label(0, 0, ouDesc, HeadFormat));
			sheet1.setRowView(6, 320);
		
	        if (flag.equals("A")){
				sheet1.addCell(new Label(0, 3,"ประเภทวัน เพิ่มวัน",HeadFormat));
			}
			else if(flag.equals("R")){
				sheet1.addCell(new Label(0, 3,"ประเภทวัน ลดวัน",HeadFormat));
			}else {
				sheet1.addCell(new Label(0,3," ประเภทรายการทั้งหมด",HeadFormat));
			}
	        sheet1.mergeCells(0,7,7,7);
			sheet1.addCell(new Label(0, 7, "ไม่พบข้อมูล",normalCenterLastFormat));		
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
