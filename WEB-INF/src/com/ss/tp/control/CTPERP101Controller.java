/*
 * Created on 17 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.control;

import java.io.InputStream;

// import java.util.Iterator;
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
import jxl.write.Label;

import jxl.write.Blank;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.ss.tp.service.TaReportService;
import com.ss.tp.service.VPeWgEmployeeScoreService;
import com.ss.tp.dto.VPeWgEmployeeScoreVO;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * @author Kiet
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class CTPERP101Controller extends MultiActionController {

	private String ChgNullToEmpty(String str1, String str2) {
		if (str1 == null || str1.equals("null")) {
			str1 = str2;
		}
		return str1;
	}

	/**
	 * @return Returns the taReportService.
	 */
	public TaReportService getTaReportService() {

		return (TaReportService) this.getApplicationContext().getBean(
				"taReportService");
	}

	private VPeWgEmployeeScoreService getVPeWgEmpScoreService() {
		return (VPeWgEmployeeScoreService) this.getApplicationContext()
				.getBean("vpeWgEmployeeScoreService");
	}

	public ModelAndView doPrintReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DecimalFormat dfInt = new DecimalFormat("###,##0");
		String ouCode = "";
		String userId = "";
		int evaYear = 0;
		int evaYearTo = 0;
		int countEmp = 0;
		String orgCodeF = "";
		String orgCodeT = "";
		String empFrom = "";
		String empTo = "";
		String divCode = ""; // Lv.3
		String areaCode = ""; // Lv.3_1
		String secCode = "null"; // Lv.4
		String workDesc = "null"; // Lv.5
		String empCodeTmp = "";

		if (request.getParameter("ouCode") != null) {
			ouCode = request.getParameter("ouCode");
		}

		if (request.getParameter("userId") != null) {
			userId = request.getParameter("userId");
		}
		if (request.getParameter("evaYear") != null
				&& !"".equals(request.getParameter("evaYear"))) {
			evaYear = Integer.parseInt(request.getParameter("evaYear"));
		} else {
			evaYear = 0;
		}

		if (request.getParameter("evaYearTo") != null
				&& !"".equals(request.getParameter("evaYearTo"))) {
			evaYearTo = Integer.parseInt(request.getParameter("evaYearTo"));
		} else {
			evaYearTo = 9999;
		}

		if (request.getParameter("orgCodeFrom") != null) {
			orgCodeF = request.getParameter("orgCodeFrom");
		}

		if (request.getParameter("orgCodeTo") != null) {
			orgCodeT = request.getParameter("orgCodeTo");
		}

		if (request.getParameter("empF") != null) {
			empFrom = request.getParameter("empF");
		}

		if (request.getParameter("empT") != null) {
			empTo = request.getParameter("empT");
		}

		String orgName = this.getVPeWgEmpScoreService().findOrgName(ouCode);
		String nameAreaF = this.getTaReportService().findAreaDesc(orgCodeF);
		String nameAreaT = this.getTaReportService().findAreaDesc(orgCodeT);
		List vpeEmpList = this.getVPeWgEmpScoreService()
				.findVPeWgEmpScoreReport(ouCode, userId, evaYear, evaYearTo,
						orgCodeF, orgCodeT, empFrom, empTo);

		// ----------------- Generate Report Detail
		// -------------------------------
		System.out.println("list size=" + vpeEmpList.size());

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition",
				"attachment; filename=./page/report/CTPERP101.xls");
		InputStream in = this.getServletContext().getResourceAsStream(
				"/page/report/CTPERP101.xls");
		Workbook wb = Workbook.getWorkbook(in);
		WritableWorkbook ww = Workbook.createWorkbook(
				response.getOutputStream(), wb);
		WritableSheet sheet1 = ww.getSheet(0);

		WritableCellFormat borderEndLine = new WritableCellFormat();
		borderEndLine.setBorder(Border.BOTTOM, BorderLineStyle.THIN);

		WritableCellFormat borderAll = new WritableCellFormat();
		borderAll.setBorder(Border.ALL, BorderLineStyle.THIN);
		borderAll.setAlignment(Alignment.CENTRE);

		CellFormat cellOrgName = sheet1.getWritableCell(0, 0).getCellFormat();

		WritableFont fontBold = new WritableFont(WritableFont.ARIAL);
		fontBold.setBoldStyle(WritableFont.BOLD);
		fontBold.setPointSize(9);

		WritableFont fontNormal = new WritableFont(WritableFont.ARIAL);
		fontNormal.setBoldStyle(WritableFont.NO_BOLD);
		fontNormal.setPointSize(9);

		Alignment dataAlignLeft = Alignment.LEFT;
		Alignment dataAlignRight = Alignment.RIGHT;
		Alignment dataAlignCenter = Alignment.CENTRE;

		WritableCellFormat headRight = new WritableCellFormat();
		headRight.setAlignment(dataAlignRight);
		headRight.setVerticalAlignment(VerticalAlignment.CENTRE);
		headRight.setFont(fontBold);

		GregorianCalendar gd = new GregorianCalendar();
		SimpleDateFormat sdfPrint = new SimpleDateFormat("dd/MM/yyyy HH:mm",
				new java.util.Locale("th", "TH"));

		if (vpeEmpList.size() > 0) {

			// ++++++++++Set Cell Format++++++++++

			WritableCellFormat boldLeftFormat = new WritableCellFormat();
			boldLeftFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
			boldLeftFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			boldLeftFormat.setAlignment(dataAlignLeft);
			boldLeftFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			boldLeftFormat.setFont(fontBold);

			WritableCellFormat boldCenterFormat = new WritableCellFormat();
			// boldCenterFormat.setBorder(Border.LEFT,BorderLineStyle.THIN);
			// boldCenterFormat.setBorder(Border.RIGHT,BorderLineStyle.THIN);
			boldCenterFormat.setAlignment(dataAlignCenter);
			boldCenterFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			boldCenterFormat.setFont(fontBold);

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

			WritableCellFormat normalLeftFormat = new WritableCellFormat();
			normalLeftFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
			normalLeftFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			normalLeftFormat.setAlignment(dataAlignLeft);
			normalLeftFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			normalLeftFormat.setFont(fontNormal);

			// CellFormat colDivDescF =
			// sheet1.getWritableCell(0,2).getCellFormat();

			// CellFormat colEmpNameF =
			// sheet1.getWritableCell(0,5).getCellFormat();
			WritableCellFormat cellEmpName = new WritableCellFormat();
			cellEmpName.setBorder(Border.LEFT, BorderLineStyle.THIN);
			cellEmpName.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			cellEmpName.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
			cellEmpName.setAlignment(dataAlignLeft);
			cellEmpName.setVerticalAlignment(VerticalAlignment.CENTRE);
			cellEmpName.setFont(fontNormal);

			// CellFormat colEvaYearF =
			// sheet1.getWritableCell(1,4).getCellFormat();
			WritableCellFormat cellEvaYear = new WritableCellFormat();
			cellEvaYear.setBorder(Border.LEFT, BorderLineStyle.THIN);
			cellEvaYear.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			cellEvaYear.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
			cellEvaYear.setAlignment(dataAlignCenter);
			cellEvaYear.setVerticalAlignment(VerticalAlignment.CENTRE);
			cellEvaYear.setFont(fontNormal);

			// CellFormat colOtherF =
			// sheet1.getWritableCell(2,5).getCellFormat();
			WritableCellFormat cellOther = new WritableCellFormat();
			cellOther.setBorder(Border.LEFT, BorderLineStyle.THIN);
			cellOther.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			cellOther.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
			cellOther.setAlignment(dataAlignRight);
			cellOther.setVerticalAlignment(VerticalAlignment.CENTRE);
			cellOther.setFont(fontNormal);

			WritableCellFormat boldLeftFormat2 = new WritableCellFormat();
			boldLeftFormat2.setAlignment(dataAlignLeft);
			boldLeftFormat2.setVerticalAlignment(VerticalAlignment.CENTRE);
			boldLeftFormat2.setFont(fontBold);

			VPeWgEmployeeScoreVO vo = null;
			VPeWgEmployeeScoreVO vo2 = null;

			int startRow = 4;
			int i = 0;
			int j = 0;
			int s = 1; // Sheet's name
			int n = 0; // For count row in list

			// //============= Format Cell WorkDesc (Lv.5)===============
			// WritableCellFormat cellWorkDesc = new
			// WritableCellFormat(colEmpNameF);
			// WritableFont font1 = new WritableFont(WritableFont.ARIAL);
			// font1.setBoldStyle(WritableFont.BOLD);
			// font1.setPointSize(9);
			// cellWorkDesc.setFont(font1);
			//
			// //==============Format Cell SecDesc (Lv.4)===============
			// WritableCellFormat cellSecDesc = new
			// WritableCellFormat(colEmpNameF);
			// WritableFont font2 = new WritableFont(WritableFont.ARIAL);
			// font2.setBoldStyle(WritableFont.BOLD);
			// font2.setPointSize(9);
			// font2.setUnderlineStyle(UnderlineStyle.SINGLE);
			// cellSecDesc.setFont(font2);

			for (int m = 0; m < vpeEmpList.size(); m++) {

				vo = (VPeWgEmployeeScoreVO) vpeEmpList.get(m);
				/*
				 * // Check Lv.3 (Separate Sheet By Lv.3-1 AreaCode) if
				 * (!divCode
				 * .equalsIgnoreCase(ChgNullToEmpty(vo.getDivCode(),""))) {
				 * //Rename Sheet ww.copySheet("Template",
				 * ChgNullToEmpty(vo.getDivCode(),"ï¿½ï¿½ï¿½ï¿½Ðºï¿½á¼¹ï¿½ï¿½Ò¹" + s),
				 * ww.getNumberOfSheets()); if
				 * (ChgNullToEmpty(vo.getSecCode(),"").equals("")) { s++; }
				 * 
				 * if (j > 0) { //Protect Previous Sheet
				 * sheet1.getSettings().setPassword("123");
				 * sheet1.getSettings().setProtected(true); }
				 * 
				 * j++;
				 * 
				 * //Set New Sheet sheet1 = ww.getSheet(j); sheet1.addCell(new
				 * Label(0, 0,ChgNullToEmpty(orgName,""),cellOrgName));
				 * sheet1.addCell(new Label(0,
				 * 2,ChgNullToEmpty(vo.getDivDesc(),"" )+" ï¿½Ñ¹ï¿½ï¿½ï¿½ï¿½~ï¿½ : "
				 * +sdfPrint.format(gd.getTime()),headRight)); divCode =
				 * vo.getDivCode(); areaCode = "null"; secCode = "null";
				 * workDesc = "null"; countEmp = 0; i = 0; } // Check Lv.3_1 if
				 * (!areaCode.equalsIgnoreCase(ChgNullToEmpty(
				 * vo.getAreaCode(),""))) { sheet1.setRowView(startRow+i,320);
				 * areaCode = ChgNullToEmpty(vo.getAreaCode(),""); secCode = "";
				 * workDesc = ""; if (!areaCode.equals("")) { sheet1.addCell(new
				 * Label(0,
				 * startRow+i,String.valueOf(ChgNullToEmpty(vo.getAreaCode
				 * (),"")) + " " +
				 * String.valueOf(ChgNullToEmpty(vo.getAreaDesc()
				 * ,"")),boldLeftFormat));
				 * 
				 * sheet1.addCell(new Blank(1, startRow+i, normalCenterFormat));
				 * sheet1.addCell(new Blank(2, startRow+i, normalRightFormat));
				 * 
				 * areaCode = ChgNullToEmpty(vo.getAreaCode(),""); secCode = "";
				 * workDesc = "";
				 * 
				 * i++; } }
				 */

				if (!areaCode.equalsIgnoreCase(ChgNullToEmpty(vo.getAreaCode(),
						""))) {
					sheet1.setRowView(startRow + i, 320);
					divCode = vo.getDivCode();
					areaCode = ChgNullToEmpty(vo.getAreaCode(), "");
					secCode = "";
					workDesc = "";
					ww.copySheet("Template",ChgNullToEmpty(areaCode, "äÁèÃÐºØá¼¹¡§Ò¹" + s),ww.getNumberOfSheets());
					if (ChgNullToEmpty(vo.getSecCode(), "").equals("")) {
						s++;
					}

					if (j > 0) {
						// Protect Previous Sheet
						sheet1.getSettings().setPassword("123");
						sheet1.getSettings().setProtected(true);
					}

					j++;

					// Set New Sheet
					sheet1 = ww.getSheet(j);
					sheet1.addCell(new Label(0, 0, ChgNullToEmpty(orgName, ""),
							cellOrgName));
					sheet1.addCell(new Label(0, 2, ChgNullToEmpty(vo.getDivDesc(), "")+ "             ÇÑ¹·Õè¾ÔÁ¾ì : "+ sdfPrint.format(gd.getTime()), headRight));
					countEmp = 0;
					i = 0;
				}
				// Check Lv.4
				if (!secCode.equalsIgnoreCase(ChgNullToEmpty(vo.getSecCode(),
						""))) {
					sheet1.setRowView(startRow + i, 320);
					areaCode = ChgNullToEmpty(vo.getAreaCode(), "");
					secCode = ChgNullToEmpty(vo.getSecCode(), "");
					workDesc = "";
					if (!secCode.equals("")) {
						sheet1.addCell(new Label(0, startRow + i, String.valueOf(ChgNullToEmpty(vo.getSecCode(), ""))+ " "+
								String.valueOf(ChgNullToEmpty(vo.getSecDesc(), "")), boldLeftFormat));

						sheet1.addCell(new Blank(1, startRow + i,normalCenterFormat));
						sheet1.addCell(new Blank(2, startRow + i,normalRightFormat));
						areaCode = ChgNullToEmpty(vo.getAreaCode(), "");
						secCode = ChgNullToEmpty(vo.getSecCode(), "");
						workDesc = "";

						i++;
					}
				}

				// Check Lv.5
				if (!workDesc.equalsIgnoreCase(ChgNullToEmpty(vo.getWorkDesc(),
						""))) {
					sheet1.setRowView(startRow + i, 320);
					workDesc = ChgNullToEmpty(vo.getWorkDesc(), "");
					if (!workDesc.equals("")) {
						sheet1.addCell(new Label(0, startRow + i, String.valueOf(ChgNullToEmpty(vo.getWorkCode(), ""))+ " "+
								String.valueOf(ChgNullToEmpty(vo.getWorkDesc(), "")), boldLeftFormat));

						sheet1.addCell(new Blank(1, startRow + i,normalCenterFormat));
						sheet1.addCell(new Blank(2, startRow + i,normalRightFormat));
						i++;
					}
				}

				if ((m + 1) < vpeEmpList.size()) {
					vo2 = (VPeWgEmployeeScoreVO) vpeEmpList.get(m + 1);
					sheet1.setRowView(startRow + i, 320);
					if (!areaCode.equalsIgnoreCase(ChgNullToEmpty(vo2.getAreaCode(), ""))) {
						if (!empCodeTmp.equalsIgnoreCase(ChgNullToEmpty(vo.getEmpCode(), ""))) {
							empCodeTmp = ChgNullToEmpty(vo.getEmpCode(), "");
							sheet1.addCell(new Label(0, startRow + i,ChgNullToEmpty(vo.getEmpCode(), "")+ " "+ ChgNullToEmpty(vo.getEmpName(),""), cellEmpName));
							countEmp = countEmp + 1;
						} else {
							sheet1.addCell(new Blank(0, startRow + i,cellEmpName));
						}
						sheet1.addCell(new Label(1, startRow + i,ChgNullToEmpty(String.valueOf(vo.getEvaYear()),""), cellEvaYear));
						sheet1.addCell(new Label(2, startRow + i,ChgNullToEmpty(String.valueOf(vo.getScoreNet()), ""),cellOther));

						// i++;
						// sheet1.addCell(new Label(0, startRow+i,
						// "ï¿½ï¿½wï¿½ï¿½ï¿½ï¿½ï¿½"+"ï¿½ï¿½",boldLeftFormat2));
						// sheet1.addCell(new Blank(1, startRow+i,
						// boldLeftFormat2));
						// sheet1.addCell(new Blank(2, startRow+i,
						// boldLeftFormat2));
					} else {
						if (!empCodeTmp.equalsIgnoreCase(ChgNullToEmpty(
								vo.getEmpCode(), ""))) {
							empCodeTmp = ChgNullToEmpty(vo.getEmpCode(), "");
							sheet1.addCell(new Label(0, startRow + i,ChgNullToEmpty(vo.getEmpCode(), "")+ " "+ ChgNullToEmpty(vo.getEmpName(),""), normalLeftFormat));
							countEmp = countEmp + 1;
						} else {
							sheet1.addCell(new Blank(0, startRow + i,normalLeftFormat));
						}
						sheet1.addCell(new Label(1, startRow + i,
								ChgNullToEmpty(String.valueOf(vo.getEvaYear()),""), normalCenterFormat));
						sheet1.addCell(new Label(2, startRow + i,ChgNullToEmpty(String.valueOf(vo.getScoreNet()), ""),normalRightFormat));
					}
				} else {
					sheet1.setRowView(startRow + i, 320);
					if (!empCodeTmp.equalsIgnoreCase(ChgNullToEmpty(vo.getEmpCode(), ""))) {
						empCodeTmp = ChgNullToEmpty(vo.getEmpCode(), "");
						sheet1.addCell(new Label(0, startRow + i,ChgNullToEmpty(vo.getEmpCode(), "") + " "+ ChgNullToEmpty(vo.getEmpName(), ""),cellEmpName));
						countEmp = countEmp + 1;
					} else {
						sheet1.addCell(new Blank(0, startRow + i, cellEmpName));
					}
					sheet1.addCell(new Label(1, startRow + i, ChgNullToEmpty(String.valueOf(vo.getEvaYear()), ""), cellEvaYear));
					sheet1.addCell(new Label(2, startRow + i, ChgNullToEmpty(String.valueOf(vo.getScoreNet()), ""), cellOther));
				}

				i++;
				sheet1.addCell(new Label(0, startRow+i, "ÃÇÁ·Ñé§ÊÔé¹ "+dfInt.format(countEmp)+" ¤¹",boldLeftFormat2));
				sheet1.addCell(new Blank(1, startRow + i, boldLeftFormat2));
				sheet1.addCell(new Blank(2, startRow + i, boldLeftFormat2));
			}

			sheet1.getSettings().setPassword("123");
			sheet1.getSettings().setProtected(true);

			// IF Not Copy Sheet
			// if (ww.getNumberOfSheets() == 1) {
			// ww.copySheet("Template", "ï¿½ï¿½ï¿½ï¿½Õ¢ï¿½ï¿½ï¿½ï¿½ï¿½" , ww.getNumberOfSheets());
			// sheet1 = ww.getSheet(1);
			// borderEndLine.setAlignment(Alignment.CENTRE);
			// sheet1.addCell(new Label(0,
			// 0,ChgNullToEmpty(orgName,""),cellOrgName));
			// if(nameAreaF!=null && nameAreaF !=null ){
			// if(!nameAreaF.equals("") && !nameAreaT.equals("")){
			// sheet1.addCell(new Label(0, 2, "Ê¾./ï¿½ï¿½. : "+nameAreaF+
			// " - "+nameAreaT,cellOrgName));
			// }else{
			// sheet1.addCell(new Label(0, 2,
			// "Ê¾./ï¿½ï¿½. : "+nameAreaF,cellOrgName));
			// }
			// }
			// sheet1.mergeCells(0,4, 2,4);
			// sheet1.addCell(new Label(0, 4, "ï¿½ï¿½ï¿½ï¿½Õ¢ï¿½ï¿½ï¿½ï¿½ï¿½", borderEndLine));
			//
			// sheet1.getSettings().setPassword("123");
			// sheet1.getSettings().setProtected(true);
			// }

			ww.removeSheet(0);
			ww.setProtected(true);
			ww.write();
			ww.close();

			wb.close();
			in.close();

			divCode = "NullValue";
			secCode = "NullValue";
			workDesc = "";

			return null;
		} else {
			ww.copySheet("Template", "äÁèÁÕ¢éÍÁÙÅ" , ww.getNumberOfSheets());
			sheet1 = ww.getSheet(1);

			borderEndLine.setAlignment(Alignment.CENTRE);

			sheet1.addCell(new Label(0, 0, ChgNullToEmpty(orgName, ""),
					cellOrgName));
			if (nameAreaF != null && nameAreaF != null) {
				if (!nameAreaF.equals("") && !nameAreaT.equals("")) {
					sheet1.addCell(new Label(0, 2, "ÇÑ¹·Õè¾ÔÁ¾ì : "+ sdfPrint.format(gd.getTime()), headRight));
				} else {
					sheet1.addCell(new Label(0, 2, "ÇÑ¹·Õè¾ÔÁ¾ì : "+ sdfPrint.format(gd.getTime()), headRight));
				}
			}
			sheet1.mergeCells(0, 4, 2, 4);
			sheet1.addCell(new Label(0, 4, "äÁèÁÕ¢éÍÁÙÅ", borderAll));

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
}
