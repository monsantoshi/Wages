/*
 * Created on 17 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.control;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.CellFormat;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.ss.tp.service.RwPremiumService;

/**
 * @author
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class TrainReportController extends MultiActionController {

	private CellFormat headerFormat = null;// sheet1.getWritableCell(0,

	// 2).getCellFormat();

	private CellFormat headTableEmpCodeFormat = null;// sheet1.getWritableCell(0,

	// 4).getCellFormat();

	private CellFormat headTableEmpNameFormat = null;// sheet1.getWritableCell(1,

	// 4).getCellFormat();

	private CellFormat headTableTaxIdFormat = null;// sheet1.getWritableCell(2,

	// 4).getCellFormat();

	private CellFormat headTableBankIdFormat = null;// sheet1.getWritableCell(3,

	// 4).getCellFormat();

	private String headEmpCode = null;// sheet1.getCell(0,4).getContents();

	private String headEmpName = null;// sheet1.getCell(1,4).getContents();

	private String headTaxId = null;// sheet1.getCell(2,4).getContents();

	private String headBankId = null;// sheet1.getCell(3,4).getContents();

	private CellFormat leftFormat = null;// sheet1.getWritableCell(0,

	// 5).getCellFormat();

	private CellFormat centerFormat = null;// sheet1.getWritableCell(1,

	// 5).getCellFormat();

	private CellFormat rightBoldBgPinkFormat = null;// sheet1.getWritableCell(5,

	// 4).getCellFormat();

	private CellFormat leftBoldBgPinkFormat = null;// sheet1.getWritableCell(5,

	// 5).getCellFormat();

	private CellFormat leftBoldBgBlueFormat = null;// sheet1.getWritableCell(5,

	// 6).getCellFormat();

	private CellFormat emptyFormat = null;// sheet1.getWritableCell(5,

	// 7).getCellFormat();

	private int row = 0;

	private int countPerson = 0;

	private boolean firstTime = true;

	int maxPerson = 0;

	private String nowOrgCode = "";

	private RwPremiumService getRwPremiumService() {
		return (RwPremiumService) this.getApplicationContext().getBean(
				"rwPremiumService");
	}

	public ModelAndView doPrintReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String ouCode = "";
		int year = 0;
		int period = 0;
		String orgStart = "";
		String orgEnd = "";

		// Get Parameter from Web Page
		if (request.getParameter("ouCode") != null) {
			ouCode = request.getParameter("ouCode");
		}

		if (request.getParameter("year") != null
				&& !"".equals(request.getParameter("year"))) {
			year = Integer.parseInt(request.getParameter("year"));
		}

		if (request.getParameter("period") != null) {
			period = Integer.parseInt(request.getParameter("period"));
		}

		if (request.getParameter("orgStart") != null) {
			orgStart = request.getParameter("orgStart");
		}

		if (request.getParameter("orgEnd") != null) {
			orgEnd = request.getParameter("orgEnd");
		}

		// Query Data
		// List list = this.getRwPremiumService().queryTrainReport(ouCode, year,
		// period, orgStart, orgEnd);

		// ----------------- Generate Report Detail
		// -------------------------------

		List list = new ArrayList();

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition",
				"attachment; filename=./page/report/TrainReport.xls");

		InputStream in = this.getServletContext().getResourceAsStream(
				"/page/report/TrainReport.xls"); // Case Sensitive
		Workbook wb = Workbook.getWorkbook(in);
		WritableWorkbook ww = Workbook.createWorkbook(
				response.getOutputStream(), wb);
		WritableSheet sheet1 = ww.getSheet(0);

		// ///////////////////// Set Format //////////////////////////

		setFormat(sheet1);

		// /////////////////////////////////////////////////////////////

		// Excel Protected
		sheet1.getSettings().setPassword("123");
		sheet1.getSettings().setProtected(true);

		sheet1.addCell(new Label(0, 2, "ประจำงวดที่ : " + period + "   ปี : " + year, headerFormat));
		sheet1.addCell(new Label(1, 3, "หน่วยงาน : " + orgStart + " - " + orgEnd, headerFormat));

		row = 4;

		if ((list != null) && (list.size() > 0)) {
			firstTime = true;
			maxPerson = list.size();
			int countRecord = 0;

			for (Iterator it = list.iterator(); it.hasNext();) {
				Object[] obj = (Object[]) it.next();
				countRecord++;

				String orgCode = (String) obj[0];
				String orgDesc = (String) obj[1];
				String empCode = (String) obj[2];
				String empName = (String) obj[3];
				String taxId = (obj[4] != null) ? (String) obj[4] : "";
				String bankId = (obj[5] != null) ? ((String) obj[5]).trim()
						: "";

				if (orgCode.equals(nowOrgCode)) {

					sheet1.addCell(new Label(0, row, empCode, leftFormat));
					sheet1.addCell(new Label(1, row, empName, leftFormat));
					sheet1.addCell(new Label(2, row, taxId, centerFormat));
					sheet1.addCell(new Label(3, row, bankId, centerFormat));

					countPerson++;
					row++;

					if (countRecord == maxPerson)
						writeFooter(sheet1);
				} else {
					if (!firstTime) {
						writeFooter(sheet1);

						// clear count person
						countPerson = 0;

						row += 2; // space row
					} else
						firstTime = false;

					writeHead(sheet1, orgCode, orgDesc, empCode, empName,
							taxId, bankId);

				}

			}

			// sheet1.addCell(new Label(4, row, "", centerBoldBgPinkFormat));
			// sheet1.addCell(new Label(5, row, "", centerBoldBgPinkFormat));

			sheet1.addCell(new Label(5, 4, "", emptyFormat));
			sheet1.addCell(new Label(5, 5, "", emptyFormat));
			sheet1.addCell(new Label(5, 6, "", emptyFormat));

			ww.write();
			ww.close();
			wb.close();
			in.close();
			return null;
		} else {

			System.out.println("row header : " + row);
			// write header table
			sheet1.addCell(new Label(0, row, headEmpCode,
					headTableEmpCodeFormat));
			sheet1.addCell(new Label(1, row, headEmpName,
					headTableEmpNameFormat));
			sheet1.addCell(new Label(2, row, headTaxId, headTableTaxIdFormat));
			sheet1.addCell(new Label(3, row, headBankId, headTableBankIdFormat));
			row++;

			System.out.println("row no data : " + row);
			sheet1.mergeCells(0, row, 3, row);
			sheet1.addCell(new Label(0, row, "No Data !!!", centerFormat));
			sheet1.addCell(new Label(1, row, "", centerFormat));
			sheet1.addCell(new Label(2, row, "", centerFormat));
			sheet1.addCell(new Label(3, row, "", centerFormat));

			// Disable format
			row++;

			sheet1.addCell(new Label(5, 4, "", emptyFormat));
			sheet1.addCell(new Label(5, 5, "", emptyFormat));
			sheet1.addCell(new Label(5, 6, "", emptyFormat));

			// sheet1.addCell(new Label(0, 9, "",emptyFormat));

			ww.write();
			ww.close();
			wb.close();
			in.close();
			return null;
		}
	}

	private void setFormat(WritableSheet sheet1) {
		headerFormat = sheet1.getWritableCell(0, 2).getCellFormat();
		headTableEmpCodeFormat = sheet1.getWritableCell(0, 4).getCellFormat();
		headTableEmpNameFormat = sheet1.getWritableCell(1, 4).getCellFormat();
		headTableTaxIdFormat = sheet1.getWritableCell(2, 4).getCellFormat();
		headTableBankIdFormat = sheet1.getWritableCell(3, 4).getCellFormat();

		headEmpCode = sheet1.getCell(0, 4).getContents();
		headEmpName = sheet1.getCell(1, 4).getContents();
		headTaxId = sheet1.getCell(2, 4).getContents();
		headBankId = sheet1.getCell(3, 4).getContents();

		leftFormat = sheet1.getWritableCell(0, 5).getCellFormat();
		centerFormat = sheet1.getWritableCell(1, 5).getCellFormat();
		rightBoldBgPinkFormat = sheet1.getWritableCell(5, 4).getCellFormat();
		leftBoldBgPinkFormat = sheet1.getWritableCell(5, 5).getCellFormat();
		leftBoldBgBlueFormat = sheet1.getWritableCell(5, 6).getCellFormat();
		emptyFormat = sheet1.getWritableCell(5, 7).getCellFormat();
	}

	private void writeHead(WritableSheet sheet1, String orgCode,
			String orgDesc, String empCode, String empName, String taxId,
			String bankId) throws Exception {

		nowOrgCode = orgCode;
		// write header table
		sheet1.addCell(new Label(0, row, headEmpCode, headTableEmpCodeFormat));
		sheet1.addCell(new Label(1, row, headEmpName, headTableEmpNameFormat));
		sheet1.addCell(new Label(2, row, headTaxId, headTableTaxIdFormat));
		sheet1.addCell(new Label(3, row, headBankId, headTableBankIdFormat));
		row++;

		// write organization
		sheet1.mergeCells(0, row, 3, row);
		sheet1.addCell(new Label(0, row, "หน๏ฟฝ๏ฟฝยงาน : " + nowOrgCode + " "
				+ orgDesc, leftBoldBgBlueFormat));
		sheet1.addCell(new Label(1, row, "", leftBoldBgBlueFormat));
		sheet1.addCell(new Label(2, row, "", leftBoldBgBlueFormat));
		sheet1.addCell(new Label(3, row, "", leftBoldBgBlueFormat));
		row++;

		// write first person
		sheet1.addCell(new Label(0, row, empCode, leftFormat));
		sheet1.addCell(new Label(1, row, empName, leftFormat));
		sheet1.addCell(new Label(2, row, taxId, centerFormat));
		sheet1.addCell(new Label(3, row, bankId, centerFormat));

		countPerson++;
		row++;
	}

	private void writeFooter(WritableSheet sheet1) throws Exception {
		// write footer table
		sheet1.addCell(new Label(0, row, "Total", leftBoldBgPinkFormat));
		sheet1.addCell(new Label(1, row, countPerson + " person",
				rightBoldBgPinkFormat));
		sheet1.addCell(new Label(2, row, "", leftBoldBgPinkFormat));
		sheet1.addCell(new Label(3, row, "", leftBoldBgPinkFormat));
	}

}
