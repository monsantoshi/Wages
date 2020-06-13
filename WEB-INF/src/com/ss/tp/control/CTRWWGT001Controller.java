/*
 * Created on 22 ï¿½.ï¿½. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.control;

import com.ss.tp.dto.TaReportVO;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
//import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.format.Colour;
import jxl.write.Blank;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WritableFont.FontName;

import jxl.format.VerticalAlignment;

//import com.ss.tp.dto.TaMonthEmpWorkVO;
//import com.ss.tp.dto.TaReportVO;
import com.ss.tp.service.SuOrganizeService;
//import com.ss.tp.service.TaMonthEmpWorkService;
//import com.ss.tp.service.TaReportService;

import com.ss.tp.dto.WeWgEmployeeTextVO;
import com.ss.tp.dto.WeWgEmpTextReportVO;
import com.ss.tp.service.WeWgEmployeeTextService;
import com.ss.tp.service.WeWgEmpTextReportService;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * @author bowpoko
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class CTRWWGT001Controller extends MultiActionController {

	/**
	 * @return Returns the taReportService.
	 */
	public WeWgEmpTextReportService getWeWgEmpTextReportService() {

		return (WeWgEmpTextReportService) this.getApplicationContext().getBean(
				"weWgEmpTextReportService");
	}

	public SuOrganizeService getSuOrganizeService() {
		return (SuOrganizeService) this.getApplicationContext().getBean(
				"suOrganizeService");
	}

	public WeWgEmployeeTextService getWeWgEmployeeTextService() {
		return (WeWgEmployeeTextService) this.getApplicationContext().getBean(
				"weWgEmployeeTextService");
	}

	/**
	 * method ï¿½ï¿½ï¿½ï¿½Ñºï¿½ï¿½ï¿½Ò§ï¿½ï¿½Â§Ò¹ï¿½ï¿½ï¿½ï¿½ï¿½Å¡ï¿½ï¿½ï¿½Ò»ï¿½Ð¨Ó¢Í§ï¿½ï¿½Ñ¡ï¿½Ò¹
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView doPrintReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String ouCode = "";
		String userId = "";

		String empCodeFrom = "";
		String empCodeTo = "";
		String orgCodeFrom = "";
		String orgCodeTo = "";

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition",
				"attachment; filename=./page/report/CTRWWGTRP001.xls");
		InputStream in = this.getServletContext().getResourceAsStream(
				"/page/report/CTRWWGTRP001.xls");
		if (request.getParameter("ouCode") != null) {
			ouCode = request.getParameter("ouCode");
		}
		if (request.getParameter("userId") != null) {
			userId = request.getParameter("userId");
		}

		if (request.getParameter("orgCodeFrom") != null) {
			orgCodeFrom = request.getParameter("orgCodeFrom");
		}
		if (request.getParameter("orgCodeTo") != null) {
			orgCodeTo = request.getParameter("orgCodeTo");
		}
		if (request.getParameter("empF") != null) {
			empCodeFrom = request.getParameter("empF");
		}
		if (request.getParameter("empT") != null) {
			empCodeTo = request.getParameter("empT");
		}

		Workbook wb = Workbook.getWorkbook(in);
		WritableWorkbook ww = Workbook.createWorkbook(
				response.getOutputStream(), wb);
		String ouName = this.getSuOrganizeService().findOrganizeName(ouCode);

		List secList = this.getWeWgEmpTextReportService()
				.findListSecPntReportNew(ouCode, userId, orgCodeFrom,
						orgCodeTo, empCodeFrom, empCodeTo);
		List taReplist = this.getWeWgEmpTextReportService()
				.findListPntReportNew(ouCode, userId, orgCodeFrom, orgCodeTo,
						empCodeFrom, empCodeTo);
		// ---------------------------------Generate Report

		WritableCellFormat border = new WritableCellFormat();
		border.setBorder(Border.ALL, BorderLineStyle.THIN);
		border.setBackground(Colour.GRAY_50);
		Alignment data = Alignment.getAlignment(1);
		border.setAlignment(data.CENTRE);

		WritableFont font = new WritableFont(WritableFont.ARIAL);
		font.setBoldStyle(WritableFont.BOLD);
		font.setPointSize(9);
		border.setFont(font);

		WritableCellFormat head = new WritableCellFormat();
		head.setAlignment(data.CENTRE);
		head.setFont(font);

		WritableCellFormat headRight = new WritableCellFormat();
		headRight.setAlignment(data.RIGHT);
		headRight.setFont(font);

		WritableCellFormat headLeft = new WritableCellFormat();
		headLeft.setAlignment(data.LEFT);
		headLeft.setFont(font);

		WritableCellFormat nameMonth = new WritableCellFormat();
		nameMonth.setBorder(Border.ALL, BorderLineStyle.THIN);
		nameMonth.setBackground(Colour.GRAY_25);
		nameMonth.setAlignment(data.CENTRE);
		nameMonth.setFont(font);

		WritableCellFormat borderRight = new WritableCellFormat();
		borderRight.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		borderRight.setAlignment(data.RIGHT);

		WritableCellFormat borderEndLine = new WritableCellFormat();
		borderEndLine.setBorder(Border.TOP, BorderLineStyle.THIN);
		borderEndLine.setAlignment(data.LEFT);

		WritableFont font2 = new WritableFont(WritableFont.ARIAL);
		font2.setPointSize(9);

		WritableCellFormat group2 = new WritableCellFormat();
		group2.setBorder(Border.LEFT, BorderLineStyle.THIN);
		group2.setAlignment(data.LEFT);
		group2.setFont(font);

		WritableCellFormat group3 = new WritableCellFormat();
		group3.setBorder(Border.LEFT, BorderLineStyle.THIN);
		group3.setAlignment(data.LEFT);
		group3.setFont(font2);

		WritableCellFormat dataBorder = new WritableCellFormat();
		dataBorder.setBorder(Border.LEFT, BorderLineStyle.THIN);
		dataBorder.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		dataBorder.setAlignment(data.LEFT);
		dataBorder.setFont(font2);

		WritableCellFormat dataBorderCenter = new WritableCellFormat();
		dataBorderCenter.setBorder(Border.LEFT, BorderLineStyle.THIN);
		dataBorderCenter.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		dataBorderCenter.setAlignment(data.CENTRE);
		dataBorderCenter.setFont(font2);

		GregorianCalendar gd = new GregorianCalendar();
		SimpleDateFormat sdfPrint = new SimpleDateFormat("dd/MM/yyyy HH:mm",
				new java.util.Locale("th", "TH"));

		WeWgEmpTextReportVO tavo = null;
		int startRow = 5;
		String empTemp = "";
		String empName = "";

		List nameSecList = secList;
		List headColum = taReplist;
		int numOfSheet = secList.size();
		int numLastUnderline = numOfSheet;
		String tempSecCode = "";
		String tempSecDesc = "";
		String tempDivCode = "";
		String tempDivDesc = "";
		String tempDivShort = "";
		String tempAreaCode = "";
		String tempAreaDesc = "";
		String nameSheet = "";

		WeWgEmpTextReportVO vo = null;

		if (secList.size() > 0) {
			vo = (WeWgEmpTextReportVO) secList.get(0);

			tempDivCode = (String) vo.getDivCode();
			tempDivDesc = (String) vo.getDivDesc();
			tempDivShort = (String) vo.getDivShort();

			tempAreaCode = (String) vo.getAreaCode();
			tempAreaDesc = (String) vo.getAreaDesc();
		}
		if (headColum.size() > 0) {
			vo = new WeWgEmpTextReportVO();
			vo = (WeWgEmpTextReportVO) secList.get(0);
		}
		WritableSheet sheet[];

		if (numOfSheet == 0) {
			sheet = new WritableSheet[1];
		} else {
			sheet = new WritableSheet[numOfSheet];
		}

		int sheetNum = 1;
		for (int i = 0; i < numOfSheet - 1; i++) {
			tavo = (WeWgEmpTextReportVO) nameSecList.get(i + 1);
			if (tavo.getAreaCode() == null) {
				nameSheet = "Sheet" + sheetNum;
				sheetNum++;
			} else {
				nameSheet = tavo.getAreaCode();
			}
			ww.copySheet("Sheet1", nameSheet, ww.getNumberOfSheets());

		}

		sheet[0] = ww.getSheet(0);
		if (numOfSheet == 0) {
			sheet[0].setName("Sheet");
		} else {
			if (tempAreaCode == null) {
				sheet[0].setName("Sheet");
			} else {
				sheet[0].setName(tempAreaCode);
			}

		}

		CellFormat group = sheet[0].getWritableCell(1, 6).getCellFormat();
		CellFormat borderLR = sheet[0].getWritableCell(4, 6).getCellFormat();

		int i = 0;
		int j = -1;
		tempSecCode = "";
		tempSecDesc = "";
		tempDivCode = "";
		tempDivDesc = "";
		tempDivShort = "";

		tempAreaCode = "";
		tempAreaDesc = "";

		try {
			if (taReplist.size() > 0) {
				for (Iterator itt = taReplist.iterator(); itt.hasNext();) {
					tavo = (WeWgEmpTextReportVO) itt.next();
					if (!tempAreaCode.equals(tavo.getAreaCode())) {
						if (j >= 0) {
							for (int y = 0; y < 5; y++) {
								sheet[j].setRowView(startRow, 320);
								sheet[j].addCell(new Label(y, startRow + i, "",
										borderEndLine));
							}
						}

						tempDivCode = tavo.getDivCode();
						tempDivDesc = tavo.getDivDesc();
						tempDivShort = tavo.getDivShort();
						tempAreaCode = tavo.getAreaCode();
						tempAreaDesc = tavo.getAreaDesc();
						i = 0;
						j++;
						startRow = 5;
						sheet[j] = ww.getSheet(j);
						sheet[j].setRowView(startRow, 320);
						sheet[j].addCell(new Label(0, 0, ouName, head));
						if (tempAreaCode != null) {
							sheet[j].addCell(new Label(0, 2, "ÊÑ§¡Ñ´ : " 
									+ tempAreaDesc + " " + tempDivShort, head));
							sheet[j].addCell(new Label(4, 2, "         ÇÑ¹·Õè¾ÔÁ¾ì : "
									+ sdfPrint.format(gd.getTime()), headRight));
						} else {
							sheet[j].addCell(new Label(0, 2, "ÊÑ§¡Ñ´ : "
									+ tempDivDesc, head));
							sheet[j].addCell(new Label(4, 2, "         ÇÑ¹·Õè¾ÔÁ¾ì : "
									+ sdfPrint.format(gd.getTime()), headRight));
						}

					}

					if (!tempSecCode.equals(tavo.getSecCode())) {
						tempSecCode = tavo.getSecCode();
						tempSecDesc = tavo.getSecDesc();
						sheet[j].setRowView(startRow, 320);
						sheet[j].addCell(new Label(0, startRow + i, tempSecCode
								+ "  " + tempSecDesc, group));
						for (int x = 1; x < 5; x++) {
							sheet[j].setRowView(startRow + i, 320);
							sheet[j].addCell(new Blank(x, startRow + i,
									borderLR));
						}
						i++;
					}

					if (empTemp.equals("")) {
						empTemp = tavo.getEmpCode();
						empName = tavo.getFullName();
						sheet[j].addCell(new Label(0, startRow + i, empTemp
								+ " " + empName, group3));
					} else if (!empTemp.equals(tavo.getEmpCode())) {
						empTemp = tavo.getEmpCode();
						empName = tavo.getFullName();
						sheet[j].addCell(new Label(0, startRow + i, empTemp
								+ " " + empName, group3));
					} else {
						sheet[j].addCell(new Label(0, startRow + i, " ", group3));
					}

					sheet[j].setRowView(startRow + i, 320);
					sheet[j].addCell(new Label(1, startRow + i, tavo.getPol(),
							dataBorderCenter));
					sheet[j].addCell(new Label(2, startRow + i, tavo.getPreN(),
							dataBorderCenter));
					sheet[j].addCell(new Label(3, startRow + i, tavo
							.getEngName(), dataBorder));
					sheet[j].addCell(new Label(4, startRow + i, tavo
							.getEngLastname(), dataBorder));

					i++;

					// if(j==0 || numLastUnderline == j ){
					// for(int y=0;y<15;y++){
					// sheet[j].setRowView(startRow+i,320);
					// sheet[j].addCell(new Label(y,
					// startRow+i,"",borderEndLine));
					// }
					// }
					sheet[j].getSettings().setPassword("123");
					sheet[j].getSettings().setProtected(true);
				}
				for (int y = 0; y < 5; y++) {
					sheet[j].setRowView(startRow, 320);
					sheet[j].addCell(new Label(y, startRow + i, "",
							borderEndLine));
				}
			} else {
				WritableSheet sheetNoData = ww.getSheet(0);
				Alignment noAlg = Alignment.getAlignment(1);
				WritableCellFormat border2 = new WritableCellFormat();
				border2.setAlignment(noAlg.CENTRE);
				border2.setBorder(Border.ALL, BorderLineStyle.THIN);
				sheetNoData.setRowView(startRow, 320);
				sheetNoData.mergeCells(0, 6, 5, 6);
				sheetNoData.addCell(new Label(0, 6, "äÁè¾º¢éÍÁÙÅ", border2));
				sheetNoData.removeRow(6);

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
