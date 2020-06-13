/*
 * Created on 29 ï¿½.ï¿½. 2549
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

import com.ss.tp.dto.TaMonthEmpWorkVO;
import com.ss.tp.service.SuOrganizeService;
import com.ss.tp.service.TaReportService;
import com.ss.tp.service.TaWgMonthEmpWorkService;

/**
 * @author bowpoko
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class CTTAMT101Controller extends MultiActionController {

	// add bybow Show areaDesc L31 for List data is null
	/**
	 * @return Returns the taReportService.
	 */
	public TaReportService getTaReportService() {

		return (TaReportService) this.getApplicationContext().getBean(
				"taReportService");
	}

	/**
	 * @return Returns the taWgMonthEmpWorkService.
	 */
	public TaWgMonthEmpWorkService getTaWgMonthEmpWorkService() {
		return (TaWgMonthEmpWorkService) this.getApplicationContext().getBean(
				"taWgMonthEmpWorkService");
	}

	public SuOrganizeService getSuOrganizeService() {
		return (SuOrganizeService) this.getApplicationContext().getBean(
				"suOrganizeService");
	}

	/**
	 * method ï¿½ï¿½ï¿½ï¿½Ñºï¿½ï¿½ï¿½Ò§ï¿½ï¿½Â§Ò¹ï¿½ï¿½ï¿½ï¿½Ò»ï¿½Ð¨Ó»Õ¢Í§ï¿½Ù¡ï¿½ï¿½Ò§
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
		int workYear = 0;
		int workMonth = 0;
		String areaCode = "";
		String secCode = "";
		String workCode = "";
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition",
				"attachment; filename=./page/report/CTTAMT101.xls");
		InputStream in = this.getServletContext().getResourceAsStream(
				"/page/report/CTTAMT101.xls");
		if (request.getParameter("hidOuCode") != null) {
			ouCode = request.getParameter("hidOuCode");
		}
		if (request.getParameter("userId") != null) {
			userId = request.getParameter("userId");
		}

		if (request.getParameter("workYear") != null
				&& !"".equals(request.getParameter("workYear"))) {
			workYear = Integer.parseInt(request.getParameter("workYear"));
		}
		if (request.getParameter("workMonth") != null
				&& !"".equals(request.getParameter("workMonth"))) {
			workMonth = Integer.parseInt(request.getParameter("workMonth"));
		}
		if (request.getParameter("areaCode") != null) {
			areaCode = request.getParameter("areaCode");
		}
		if (request.getParameter("secCode") != null) {
			secCode = request.getParameter("secCode");
		}
		if (request.getParameter("workCode") != null) {
			workCode = request.getParameter("workCode");
		}

		Workbook wb = Workbook.getWorkbook(in);
		WritableWorkbook ww = Workbook.createWorkbook(
				response.getOutputStream(), wb);
		String ouName = this.getSuOrganizeService().findOrganizeName(ouCode);
		String nameArea = this.getTaReportService().findAreaDescTwo(areaCode);
		List areaList = this.getTaWgMonthEmpWorkService()
				.findListDivReportEmpMonth(ouCode, userId, workMonth, workYear,
						areaCode, secCode, workCode);
		List empList = this.getTaWgMonthEmpWorkService()
				.findListReportEmpMonth(ouCode, userId, workMonth, workYear,
						areaCode, secCode, workCode);
		// ---------------------------------Generate Report
		// Detail--------------------------------------------------
		WritableCellFormat border = new WritableCellFormat();
		WritableFont font = new WritableFont(WritableFont.ARIAL);
		font.setPointSize(9);
		Alignment data = Alignment.getAlignment(1);
		border.setAlignment(data.CENTRE);
		border.setFont(font);

		WritableCellFormat head = new WritableCellFormat();
		WritableFont font2 = new WritableFont(WritableFont.ARIAL);
		font2.setBoldStyle(WritableFont.BOLD);
		font2.setPointSize(9);
		head.setAlignment(data.CENTRE);
		head.setFont(font2);

		WritableCellFormat headRight = new WritableCellFormat();
		WritableFont font3 = new WritableFont(WritableFont.ARIAL);
		font3.setBoldStyle(WritableFont.BOLD);
		font3.setPointSize(9);
		headRight.setAlignment(data.RIGHT);
		headRight.setFont(font3);

		WritableCellFormat borderEndLine = new WritableCellFormat();
		borderEndLine.setBorder(Border.TOP, BorderLineStyle.THIN);
		borderEndLine.setAlignment(data.LEFT);

		DecimalFormat df = new DecimalFormat("###,##0.0");
		DecimalFormat dfInt = new DecimalFormat("###,##0");

		GregorianCalendar gd = new GregorianCalendar();
		SimpleDateFormat sdfPrint = new SimpleDateFormat("dd/MM/yyyy HH:mm",
				new java.util.Locale("th", "TH"));

		TaMonthEmpWorkVO tamVo = null;
		int startRow = 5;
		String empTemp = "";
		String empName = "";
		String extraEmpTemp = "";
		String extraEmpName = "";
		List numDivList = areaList;
		int numOfSheet = areaList.size();
		int numLastUnderline = numOfSheet;
		String tempAreaCode = "";
		String tempAreaDesc = "";
		String tempDivCode = "";
		String tempDivDesc = "";
		String nameSheet = "";
		if (numDivList.size() > 0) {
			tamVo = (TaMonthEmpWorkVO) numDivList.get(0);
			tempAreaCode = tamVo.getAreaCode();
			tempAreaDesc = tamVo.getAreaDesc();
			tempDivCode = tamVo.getDivCode();
			tempDivDesc = tamVo.getDivDesc();
		}

		WritableSheet sheet[];
		if (numOfSheet == 0) {
			sheet = new WritableSheet[1];
		} else {
			sheet = new WritableSheet[numOfSheet];
		}
		for (int i = 0; i < numOfSheet - 1; i++) {
			tamVo = (TaMonthEmpWorkVO) numDivList.get(i + 1);

			if (tamVo.getAreaCode() == null) {
				if (tamVo.getDivCode() != null) {
					nameSheet = tamVo.getDivCode();
				} else {
					nameSheet = "Sheet" + i + 2;
				}
			} else {
				nameSheet = tamVo.getAreaCode();
			}
			ww.copySheet("Sheet1", nameSheet, ww.getNumberOfSheets());
		}

		sheet[0] = ww.getSheet(0);
		if (numOfSheet == 0) {
			sheet[0].setName("Sheet");
		} else {
			if (tempAreaCode == null) {
				if (tempDivCode != null) {
					sheet[0].setName(tempDivCode);
				} else {
					sheet[0].setName("Sheet1");
				}
			} else {
				sheet[0].setName(tempAreaCode);
			}
		}
		CellFormat group = sheet[0].getWritableCell(1, 6).getCellFormat();
		CellFormat group2 = sheet[0].getWritableCell(0, 7).getCellFormat();
		CellFormat id = sheet[0].getWritableCell(0, 6).getCellFormat();
		CellFormat name = sheet[0].getWritableCell(2, 6).getCellFormat();
		CellFormat number = sheet[0].getWritableCell(3, 6).getCellFormat();

		sheet[0].addCell(new Label(0, 0, ouName, head));
		if(tempAreaCode!=null){
    		if(tempAreaCode.equals("")){
    			if(nameArea !=null){
    				if(!nameArea.equals("")){
    					sheet[0].addCell(new Label(0, 2, "Ê¾./»¨. : "+nameArea+"         ÇÑ¹·Õè¾ÔÁ¾ì : "+sdfPrint.format(gd.getTime()),headRight));
    				}else{
    					sheet[0].addCell(new Label(0, 2, "Ê¾./»¨. :          ÇÑ¹·Õè¾ÔÁ¾ì : "+sdfPrint.format(gd.getTime()),headRight));
    				}
    			}
    		}else{
    			sheet[0].addCell(new Label(0, 2, "Ê¾./»¨. : "+tempAreaDesc+"         ÇÑ¹·Õè¾ÔÁ¾ì : "+sdfPrint.format(gd.getTime()),headRight));
    		}
    		
    	}else{
    		sheet[0].addCell(new Label(0, 2, "Ê¾./»¨. : "+tempDivDesc+"         ÇÑ¹·Õè¾ÔÁ¾ì : "+sdfPrint.format(gd.getTime()),headRight));
    	}
    
		if(workYear!=0 && workMonth!=0){
			sheet[0].addCell(new Label(0, 3, "  »ÃÐ¨Ó»Õ   "+ workYear +"  à´×Í¹  "+convertMonth(new Integer(workMonth)), head));
		}
		int i = 0;
		int j = 0;
		int m = 0;
		String secCodeTemp = "";
		String secDescTemp = "";
		String orgWorkCodeTemp = "";
		String orgWorkDescTemp = "";
		String totalDay = "";
		String totaltime = "";
		String extraTotalDay = "";
		String extraTotaltime = "";
		if (numLastUnderline != 0) {
			numLastUnderline--;
		}
		if (empList.size() > 0) {

			for (Iterator itt = empList.iterator(); itt.hasNext();) {
				tamVo = (TaMonthEmpWorkVO) itt.next();

				if (tamVo.getAreaCode() != null) {// ï¿½ï¿½ï¿½ sheet ï¿½ï¿½ï¿½ï¿½
					if (tempAreaCode == null) {
						tempAreaCode = "";
					}
					if (!tempAreaCode.equals(tamVo.getAreaCode())) {
						if (j > 0) {
							for (int y = 0; y < 3; y++) {
								sheet[j].setRowView(startRow, 320);
								sheet[j].addCell(new Label(y, startRow + i, "",
										borderEndLine));
							}
						}
						tempAreaCode = tamVo.getAreaCode();
						tempAreaDesc = tamVo.getAreaDesc();
						tempDivCode = tamVo.getDivCode();
						tempDivDesc = tamVo.getDivDesc();
						j++;
						i = 0;
						startRow = 5;
						sheet[j] = ww.getSheet(j);
						sheet[j].addCell(new Label(0, 0, ouName, head));
						if(tempAreaCode!=null){
							sheet[j].addCell(new Label(0, 2, "Ê¾./»¨. : "+tempAreaDesc+"         ÇÑ¹·Õè¾ÔÁ¾ì : "+sdfPrint.format(gd.getTime()),headRight));
						}else{
							sheet[j].addCell(new Label(0, 2, "Ê¾./»¨. : "+tempDivDesc+"         ÇÑ¹·Õè¾ÔÁ¾ì : "+sdfPrint.format(gd.getTime()),headRight));
						}
						if(workYear!=0 && workMonth!=0){
							sheet[j].addCell(new Label(0, 3, "  »ÃÐ¨Ó»Õ  "+ workYear +"  à´×Í¹ "+convertMonth(new Integer(workMonth)),head));
						}
					}
				} else {
					if (tamVo.getAreaCode() == null
							&& tamVo.getSecCode() != null) {
						// ***************************************** come in
						// extra case ***********"*********************
						startRow = 5;

						if (extraEmpTemp.equals("")) {
							extraEmpTemp = tamVo.getEmpCode();
							extraEmpName = tamVo.getFullName();
							sheet[0].addCell(new Label(0, startRow + m,
									extraEmpTemp, id));
							sheet[0].addCell(new Label(1, startRow + m,
									extraEmpName, name));
						} else if (!extraEmpTemp.equals(tamVo.getEmpCode())) {
							extraEmpTemp = tamVo.getEmpCode();
							extraEmpName = tamVo.getFullName();
							sheet[0].addCell(new Label(0, startRow + m,
									extraEmpTemp, id));
							sheet[0].addCell(new Label(1, startRow + m,
									extraEmpName, name));
						} else {
							sheet[0].addCell(new Label(0, startRow + m, "", id));
							sheet[0].addCell(new Label(1, startRow + m, "",
									name));
						}

						sheet[0].addCell(new Label(2, startRow + m, tamVo
								.getWorkCode() + " " + tamVo.getWorkDesc(),
								name));

						if (tamVo.getTotalDays() != null) {

							extraTotalDay = tamVo.getTotalDays().toString();
						} else {
							extraTotalDay = "0.0";
						}
						if (tamVo.getTotalTime() != null) {

							extraTotaltime = tamVo.getTotalTime().toString();
						} else {
							extraTotaltime = "0";
						}
						sheet[0].addCell(new Label(3, startRow + m,
								extraTotalDay, number));
						// sheet[0].addCell(new Label(4,
						// startRow+m,extraTotaltime,number));
						m++;

					}
					if (m != 0) {
						for (int k = 0; k < 4; k++) {
							sheet[0].setRowView(startRow + m, 320);
							sheet[0].addCell(new Label(k, startRow + m, "",
									borderEndLine));
						}
					}
				}
				if (tamVo.getSecCode() == null) {

					secCodeTemp = "";
					secDescTemp = "";
					orgWorkCodeTemp = "";
					orgWorkDescTemp = "";
					sheet[j].addCell(new Label(0, startRow + i, secCodeTemp
							+ " " + secDescTemp, group));
				} else if (tamVo.getSecCode() != null
						&& tamVo.getOrgWorkCode() == null) {
					// ï¿½ï¿½ 3case ï¿½ï¿½ï¿½ï¿½ case ï¿½ï¿½ï¿½ secCode
					// ï¿½ï¿½ï¿½ï¿½ç¹µï¿½ï¿½ï¿½ï¿½ï¿½*******************
					if (secCodeTemp.equals("")) {
						secCodeTemp = tamVo.getSecCode();
						secDescTemp = tamVo.getSecDesc();
						sheet[j].addCell(new Label(0, startRow + i, secCodeTemp
								+ " " + secDescTemp, group));
						for (int y = 1; y < 4; y++) {
							sheet[j].setRowView(startRow, 320);
							sheet[j].addCell(new Blank(y, startRow + i, group));
						}
						startRow++;

					} else if (!secCodeTemp.equals(tamVo.getSecCode())) {
						secCodeTemp = tamVo.getSecCode();
						secDescTemp = tamVo.getSecDesc();
						sheet[j].addCell(new Label(0, startRow + i, secCodeTemp
								+ " " + secDescTemp, group));
						for (int y = 1; y < 4; y++) {
							sheet[j].setRowView(startRow, 320);
							sheet[j].addCell(new Blank(y, startRow + i, group));
						}
						startRow++;
					}
				} else if (tamVo.getSecCode() != null
						&& tamVo.getOrgWorkCode() != null) {

					if (orgWorkCodeTemp.equals("") && secCodeTemp.equals("")) {
						orgWorkCodeTemp = tamVo.getOrgWorkCode();
						orgWorkDescTemp = tamVo.getOrgWorkDesc();
						secCodeTemp = tamVo.getSecCode();
						secDescTemp = tamVo.getSecDesc();
						sheet[j].addCell(new Label(0, startRow + i, secCodeTemp
								+ " " + secDescTemp, group));
						for (int y = 1; y < 4; y++) {
							sheet[j].setRowView(startRow, 320);
							sheet[j].addCell(new Blank(y, startRow + i, group));
						}
						startRow++;
						sheet[j].addCell(new Label(0, startRow + i,
								orgWorkCodeTemp + " " + orgWorkDescTemp, group2));
						for (int y = 1; y < 4; y++) {
							sheet[j].setRowView(startRow, 320);
							sheet[j].addCell(new Blank(y, startRow + i, group2));
						}
						startRow++;
					} else if (secCodeTemp.equals(tamVo.getSecCode())) {
						if (!orgWorkCodeTemp.equals(tamVo.getOrgWorkCode())) {
							orgWorkCodeTemp = tamVo.getOrgWorkCode();
							orgWorkDescTemp = tamVo.getOrgWorkDesc();
							sheet[j].addCell(new Label(0, startRow + i,
									orgWorkCodeTemp + " " + orgWorkDescTemp,
									group2));
							for (int y = 1; y < 4; y++) {
								sheet[j].setRowView(startRow, 320);
								sheet[j].addCell(new Blank(y, startRow + i,
										group2));
							}
							startRow++;
						}

					} else {
						orgWorkCodeTemp = tamVo.getOrgWorkCode();
						orgWorkDescTemp = tamVo.getOrgWorkDesc();
						secCodeTemp = tamVo.getSecCode();
						secDescTemp = tamVo.getSecDesc();
						sheet[j].addCell(new Label(0, startRow + i, secCodeTemp
								+ " " + secDescTemp, group));
						for (int y = 1; y < 4; y++) {
							sheet[j].setRowView(startRow, 320);
							sheet[j].addCell(new Blank(y, startRow + i, group));
						}
						startRow++;
						sheet[j].addCell(new Label(0, startRow + i,
								orgWorkCodeTemp + " " + orgWorkDescTemp, group2));
						for (int y = 1; y < 4; y++) {
							sheet[j].setRowView(startRow, 320);
							sheet[j].addCell(new Blank(y, startRow + i, group2));
						}
						startRow++;
					}

				}

				if (empTemp.equals("")) {
					empTemp = tamVo.getEmpCode();
					empName = tamVo.getFullName();
					sheet[j].addCell(new Label(0, startRow + i, empTemp, id));
					sheet[j].addCell(new Label(1, startRow + i, empName, name));
				} else if (!empTemp.equals(tamVo.getEmpCode())) {
					empTemp = tamVo.getEmpCode();
					empName = tamVo.getFullName();
					sheet[j].addCell(new Label(0, startRow + i, empTemp, id));
					sheet[j].addCell(new Label(1, startRow + i, empName, name));
				} else {
					sheet[j].addCell(new Label(0, startRow + i, "", id));
					sheet[j].addCell(new Label(1, startRow + i, "", name));
				}

				sheet[j].addCell(new Label(2, startRow + i, tamVo.getWorkCode()
						+ " " + tamVo.getWorkDesc(), name));

				if (tamVo.getTotalDays() != null) {

					totalDay = tamVo.getTotalDays().toString();
				} else {
					totalDay = "0.0";
				}
				if (tamVo.getTotalTime() != null) {

					totaltime = tamVo.getTotalTime().toString();
				} else {
					totaltime = "0";
				}

				sheet[j].addCell(new Label(3, startRow + i, totalDay, number));
				// sheet[j].addCell(new Label(4, startRow+i,totaltime,number));
				sheet[j].getSettings().setPassword("123");
				sheet[j].getSettings().setProtected(true);
				i++;
				if (j == 0 || numLastUnderline == j) {
					for (int y = 0; y < 4; y++) {
						sheet[j].setRowView(startRow + i, 320);
						sheet[j].addCell(new Label(y, startRow + i, "",
								borderEndLine));
					}
				}

			}
		} else {
			WritableSheet sheetNoData = ww.getSheet(0);
			Alignment noAlg = Alignment.getAlignment(1);
			WritableCellFormat border2 = new WritableCellFormat();
			border2.setAlignment(noAlg.CENTRE);
			border2.setBorder(Border.ALL, BorderLineStyle.THIN);
			sheet[0].setRowView(startRow + i, 320);
			sheetNoData.mergeCells(0, startRow + i, 3, startRow);
			sheetNoData.addCell(new Label(0, startRow+i, "äÁè¾º¢éÍÁÙÅ",border2));
			sheetNoData.removeRow(6);
			sheetNoData.removeRow(6);

		}
		sheet[0].getSettings().setPassword("123");
		sheet[0].getSettings().setProtected(true);
		ww.write();
		ww.close();
		wb.close();
		in.close();
		return null;
	}

	/**
	 * 
	 * @param numOfMonth
	 * @return
	 */
	public String convertMonth(Integer numOfMonth) {
		String conM = "";
		if (numOfMonth != null) {
			switch (numOfMonth.byteValue()) {
			case 1 : conM = "Á¡ÃÒ¤Á"; 		break;
			case 2 : conM = "¡ØÁÀÒ¾Ñ¹¸ì"; 		break;
			case 3 : conM = "ÁÕ¹Ò¤Á"; 		break;
			case 4 : conM = "àÁÉÒÂ¹"; 		break;
			case 5 : conM = "¾ÄÉÀÒ¤Á"; 		break;
			case 6 : conM = "ÁÔ¶Ø¹ÒÂ¹"; 		break;
			case 7 : conM = "¡Ã¡®Ò¤Á"; 		break;
			case 8 : conM = "ÊÔ§ËÒ¤Á"; 		break;
			case 9 : conM = "¡Ñ¹ÂÒÂ¹"; 		break;
			case 10 : conM = "µØÅÒ¤Á"; 		break;
			case 11 : conM = "¾ÄÈ¨Ô¡ÒÂ¹"; 	break;
			case 12 : conM = "¸Ñ¹ÇÒ¤Á"; 		break;
			case 0 : conM = ""; 			break;
			}
		}
		return conM;
	}

}
