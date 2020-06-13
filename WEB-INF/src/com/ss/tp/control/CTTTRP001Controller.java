/*
 * Created on 22 ๏ฟฝ.๏ฟฝ. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.control;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Blank;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WritableFont.FontName;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.ss.tp.dto.WeEmployeeVO;
import com.ss.tp.security.UserInfo;
import com.ss.tp.service.SuOrganizeService;
import com.ss.tp.service.WePnPromoteInstService;

/**
 * @author amorn
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */

public class CTTTRP001Controller extends MultiActionController {
	public WePnPromoteInstService getWePnPromoteInstService() {
		return (WePnPromoteInstService) this.getApplicationContext().getBean(
				"wePnPromoteInstService");
	}

	public SuOrganizeService getSuOrganizeService() {
		return (SuOrganizeService) this.getApplicationContext().getBean(
				"suOrganizeService");
	}

	public ModelAndView doPrintReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		UserInfo userInfo = (UserInfo) request.getSession().getAttribute(
				"UserLogin");
		List resultList = new ArrayList();
		int startRow = 4;
		int row = startRow;
		int modRow = 0;
		int seq = 0;

		String evaYear = "";
		String evaMonth = "";
		String evaVolume = "";

		int countEmp = 0;

		if (request.getParameter("workYear") != null
				&& !"".equals(request.getParameter("workYear"))) {
			evaYear = request.getParameter("workYear");
		}
		if (request.getParameter("workMonth") != null
				&& !"".equals(request.getParameter("workMonth"))) {
			evaMonth = request.getParameter("workMonth");
		}

		if (request.getParameter("volumeSet") != null
				&& !"".equals(request.getParameter("volumeSet"))) {
			evaVolume = request.getParameter("volumeSet");
		}

		System.out.println(evaVolume);
		System.out.println(evaYear);
		System.out.println(evaMonth);

		resultList = this.getWePnPromoteInstService().findByCriteriaReport(
				userInfo.getUserId(), userInfo.getOuCode(), evaYear, evaMonth,
				evaVolume);

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition",
				"attachment; filename=./page/report/CTTTRP001.xls");
		InputStream in = this.getServletContext().getResourceAsStream(
				"/page/report/CTTTRP001.xls");
		WritableFont fontBold = new WritableFont(WritableFont.ARIAL);
		fontBold.setBoldStyle(WritableFont.BOLD);
		fontBold.setPointSize(12);

		// FontName fontName = WritableFont.createFont("veronda");
		// WritableFont font = new WritableFont(fontName, 12);
		// WritableCellFormat format = new WritableCellFormat(font);

		FontName fontName = WritableFont.createFont("AngsanaUPC");
		WritableFont fontNormal = new WritableFont(fontName, 16);
		fontNormal.setBoldStyle(WritableFont.NO_BOLD);
		fontNormal.setPointSize(16);

		Alignment dataAlignLeft = Alignment.LEFT;
		Alignment dataAlignRight = Alignment.RIGHT;
		Alignment dataAlignCenter = Alignment.CENTRE;

		WritableCellFormat head = new WritableCellFormat();
		// WritableFont font2 = new WritableFont(WritableFont.ARIAL);
		WritableFont font2 = new WritableFont(fontName, 18);
		font2.setBoldStyle(WritableFont.BOLD);
		font2.setPointSize(18);
		head.setAlignment(dataAlignCenter);
		head.setFont(font2);

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

		WritableCellFormat headRight = new WritableCellFormat();
		headRight.setAlignment(dataAlignRight);
		headRight.setVerticalAlignment(VerticalAlignment.CENTRE);
		headRight.setFont(fontBold);

		WritableCellFormat headLeft = new WritableCellFormat();
		headLeft.setAlignment(dataAlignLeft);
		headLeft.setVerticalAlignment(VerticalAlignment.CENTRE);
		headLeft.setFont(fontBold);

		WritableCellFormat normalLeftFormat = new WritableCellFormat();
		// normalLeftFormat.setBorder(Border.LEFT,BorderLineStyle.THIN);
		// normalLeftFormat.setBorder(Border.RIGHT,BorderLineStyle.THIN);
		normalLeftFormat.setAlignment(dataAlignLeft);
		normalLeftFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		normalLeftFormat.setFont(fontNormal);

		WritableCellFormat normalLeftLineFormat = new WritableCellFormat();
		normalLeftLineFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
		// normalLeftLineFormat.setBorder(Border.RIGHT,BorderLineStyle.THIN);
		normalLeftLineFormat.setAlignment(dataAlignLeft);
		normalLeftLineFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		normalLeftLineFormat.setFont(fontNormal);

		WritableCellFormat normalCenterFormat = new WritableCellFormat();
		normalCenterFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
		normalCenterFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
		normalCenterFormat.setAlignment(dataAlignCenter);
		normalCenterFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		normalCenterFormat.setFont(fontNormal);

		WritableCellFormat normalCenterCenterFormat = new WritableCellFormat();
		// normalCenterFormat.setBorder(Border.RIGHT,BorderLineStyle.THIN);
		normalCenterCenterFormat.setAlignment(dataAlignCenter);
		normalCenterCenterFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		normalCenterCenterFormat.setFont(fontNormal);

		WritableCellFormat normalRightFormat = new WritableCellFormat();
		// normalRightFormat.setBorder(Border.LEFT,BorderLineStyle.THIN);
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

		WritableCellFormat borderNumber2 = new WritableCellFormat();
		borderNumber2.setBorder(Border.ALL, BorderLineStyle.THIN);
		borderNumber2.setAlignment(Alignment.RIGHT);
		borderNumber2.setFont(fontBold);

		WritableCellFormat borderEndLine = new WritableCellFormat();
		borderEndLine.setBorder(Border.TOP, BorderLineStyle.THIN);
		borderEndLine.setAlignment(Alignment.LEFT);

		Workbook wb = Workbook.getWorkbook(in);
		WritableWorkbook ww = Workbook.createWorkbook(
				response.getOutputStream(), wb);
		WritableSheet sheet1 = ww.getSheet(0);
		DecimalFormat dfInt = new DecimalFormat("###,##0");

		SimpleDateFormat sdfPrint = new SimpleDateFormat("dd/MM/yyyy",
				new java.util.Locale("th", "TH"));
		for (int i = 0; i < resultList.size(); i++) {
			WeEmployeeVO vo = (WeEmployeeVO) resultList.get(i);
			seq = seq + 1;
			if (row < 25) {
				sheet1.mergeCells(0, 0, 11, 0);
				sheet1.addCell(new Label(0,0,  "บัญชีรายละเอียดแนบท้ายคำสั่ง ปณท ที่                                             สั่ง ณ วันที่ ",head));
				
				
				if (vo.getOldDuty() != null && vo.getNewDuty() != null) {
					if (vo.getOldSec() == null && vo.getNewSec() == null) {

						sheet1.addCell(new Label(0, row, String.valueOf(dfInt
								.format(seq)), normalCenterCenterFormat));
						sheet1.addCell(new Label(1, row, vo.getName(),
								normalLeftLineFormat));
						sheet1.addCell(new Label(2, row, vo.getOldPosition(),
								normalLeftLineFormat));
						sheet1.addCell(new Label(3, row, vo.getOldWork(),
								normalLeftFormat));
						sheet1.addCell(new Label(5, row, vo.getEmpCode(),
								normalCenterFormat));
						sheet1.addCell(new Label(6, row, vo.getAccount(),
								normalCenterFormat));
						sheet1.addCell(new Label(7, row, vo.getNewPosition(),
								normalLeftFormat));
						sheet1.addCell(new Label(8, row, vo.getNewWork(),
								normalLeftFormat));
						sheet1.addCell(new Label(10, row, vo.getNewGworkCode(),
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 1, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 1, normalCenterFormat));
						sheet1.addCell(new Label(2, row + 1, vo.getOldDuty(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 1, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 1, normalCenterFormat));
						sheet1.addCell(new Label(7, row + 1, vo.getNewDuty(),
								normalLeftLineFormat));
						sheet1.addCell(new Blank(10, row + 1,
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 2, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 2, normalCenterFormat));
						sheet1.addCell(new Label(4, row + 2, vo.getOldDiv(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 2, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 2, normalCenterFormat));
						sheet1.addCell(new Label(9, row + 2, vo.getNewDiv(),
								normalLeftFormat));
						sheet1.addCell(new Blank(10, row + 2,
								normalCenterFormat));
						row = row + 3;

					} else {

						sheet1.addCell(new Label(0, row, String.valueOf(dfInt
								.format(seq)), normalCenterCenterFormat));
						sheet1.addCell(new Label(1, row, vo.getName(),
								normalLeftLineFormat));
						sheet1.addCell(new Label(2, row, vo.getOldPosition(),
								normalLeftLineFormat));
						sheet1.addCell(new Label(3, row, vo.getOldWork(),
								normalLeftFormat));
						sheet1.addCell(new Label(5, row, vo.getEmpCode(),
								normalCenterFormat));
						sheet1.addCell(new Label(6, row, vo.getAccount(),
								normalCenterFormat));
						sheet1.addCell(new Label(7, row, vo.getNewPosition(),
								normalLeftFormat));
						sheet1.addCell(new Label(8, row, vo.getNewWork(),
								normalLeftFormat));
						sheet1.addCell(new Label(10, row, vo.getNewGworkCode(),
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 1, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 1, normalCenterFormat));
						sheet1.addCell(new Label(2, row + 1, vo.getOldSec(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 1, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 1, normalCenterFormat));
						sheet1.addCell(new Label(7, row + 1, vo.getNewSec(),
								normalLeftLineFormat));
						sheet1.addCell(new Blank(10, row + 1,
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 2, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 2, normalCenterFormat));
						sheet1.addCell(new Label(2, row + 2, vo.getOldDuty(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 2, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 2, normalCenterFormat));
						sheet1.addCell(new Label(7, row + 2, vo.getNewDuty(),
								normalLeftLineFormat));
						sheet1.addCell(new Blank(10, row + 2,
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 3, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 3, normalCenterFormat));
						sheet1.addCell(new Label(4, row + 3, vo.getOldDiv(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 3, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 3, normalCenterFormat));
						sheet1.addCell(new Label(9, row + 3, vo.getNewDiv(),
								normalLeftFormat));
						sheet1.addCell(new Blank(10, row + 3,
								normalCenterFormat));
						row = row + 4;

					}
				} else if (vo.getOldDuty() == null && vo.getNewDuty() == null) {
					if (vo.getOldSec() == null && vo.getNewSec() == null) {

						sheet1.addCell(new Label(0, row, String.valueOf(dfInt
								.format(seq)), normalCenterCenterFormat));
						sheet1.addCell(new Label(1, row, vo.getName(),
								normalLeftLineFormat));
						sheet1.addCell(new Label(2, row, vo.getOldPosition(),
								normalLeftLineFormat));
						sheet1.addCell(new Label(3, row, vo.getOldWork(),
								normalLeftFormat));
						sheet1.addCell(new Label(5, row, vo.getEmpCode(),
								normalCenterFormat));
						sheet1.addCell(new Label(6, row, vo.getAccount(),
								normalCenterFormat));
						sheet1.addCell(new Label(7, row, vo.getNewPosition(),
								normalLeftFormat));
						sheet1.addCell(new Label(8, row, vo.getNewWork(),
								normalLeftFormat));
						sheet1.addCell(new Label(10, row, vo.getNewGworkCode(),
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 1, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 1, normalCenterFormat));
						sheet1.addCell(new Label(4, row + 1, vo.getOldDiv(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 1, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 1, normalCenterFormat));
						sheet1.addCell(new Label(9, row + 1, vo.getNewDiv(),
								normalLeftFormat));
						sheet1.addCell(new Blank(10, row + 1,
								normalCenterFormat));
						row = row + 2;

					} else {

						sheet1.addCell(new Label(0, row, String.valueOf(dfInt
								.format(seq)), normalCenterCenterFormat));
						sheet1.addCell(new Label(1, row, vo.getName(),
								normalLeftLineFormat));
						sheet1.addCell(new Label(2, row, vo.getOldPosition(),
								normalLeftLineFormat));
						sheet1.addCell(new Label(3, row, vo.getOldWork(),
								normalLeftFormat));
						sheet1.addCell(new Label(5, row, vo.getEmpCode(),
								normalCenterFormat));
						sheet1.addCell(new Label(6, row, vo.getAccount(),
								normalCenterFormat));
						sheet1.addCell(new Label(7, row, vo.getNewPosition(),
								normalLeftFormat));
						sheet1.addCell(new Label(8, row, vo.getNewWork(),
								normalLeftFormat));
						sheet1.addCell(new Label(10, row, vo.getNewGworkCode(),
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 1, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 1, normalCenterFormat));
						sheet1.addCell(new Label(2, row + 1, vo.getOldSec(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 1, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 1, normalCenterFormat));
						sheet1.addCell(new Label(7, row + 1, vo.getNewSec(),
								normalLeftLineFormat));
						sheet1.addCell(new Blank(10, row + 1,
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 2, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 2, normalCenterFormat));
						sheet1.addCell(new Label(4, row + 2, vo.getOldDiv(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 2, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 2, normalCenterFormat));
						sheet1.addCell(new Label(9, row + 2, vo.getNewDiv(),
								normalLeftFormat));
						sheet1.addCell(new Blank(10, row + 2,
								normalCenterFormat));
						row = row + 3;

					}

				} else if (vo.getOldDuty() != null && vo.getNewDuty() == null) {
					if (vo.getOldSec() == null && vo.getNewSec() == null) {

						sheet1.addCell(new Label(0, row, String.valueOf(dfInt
								.format(seq)), normalCenterCenterFormat));
						sheet1.addCell(new Label(1, row, vo.getName(),
								normalLeftLineFormat));
						sheet1.addCell(new Label(2, row, vo.getOldPosition(),
								normalLeftLineFormat));
						sheet1.addCell(new Label(3, row, vo.getOldWork(),
								normalLeftFormat));
						sheet1.addCell(new Label(5, row, vo.getEmpCode(),
								normalCenterFormat));
						sheet1.addCell(new Label(6, row, vo.getAccount(),
								normalCenterFormat));
						sheet1.addCell(new Label(7, row, vo.getNewPosition(),
								normalLeftFormat));
						sheet1.addCell(new Label(8, row, vo.getNewWork(),
								normalLeftFormat));
						sheet1.addCell(new Label(10, row, vo.getNewGworkCode(),
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 1, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 1, normalCenterFormat));
						sheet1.addCell(new Label(2, row + 1, vo.getOldDuty(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 1, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 1, normalCenterFormat));
						sheet1.addCell(new Label(7, row + 1, vo.getNewDuty(),
								normalLeftLineFormat));
						sheet1.addCell(new Blank(10, row + 1,
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 2, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 2, normalCenterFormat));
						sheet1.addCell(new Label(4, row + 2, vo.getOldDiv(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 2, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 2, normalCenterFormat));
						sheet1.addCell(new Label(9, row + 2, vo.getNewDiv(),
								normalLeftFormat));
						sheet1.addCell(new Blank(10, row + 2,
								normalCenterFormat));
						row = row + 3;

					} else {

						// row=row+3;

						sheet1.addCell(new Label(0, row, String.valueOf(dfInt
								.format(seq)), normalCenterCenterFormat));
						sheet1.addCell(new Label(1, row, vo.getName(),
								normalLeftLineFormat));
						sheet1.addCell(new Label(2, row, vo.getOldPosition(),
								normalLeftLineFormat));
						sheet1.addCell(new Label(3, row, vo.getOldWork(),
								normalLeftFormat));
						sheet1.addCell(new Label(5, row, vo.getEmpCode(),
								normalCenterFormat));
						sheet1.addCell(new Label(6, row, vo.getAccount(),
								normalCenterFormat));
						sheet1.addCell(new Label(7, row, vo.getNewPosition(),
								normalLeftFormat));
						sheet1.addCell(new Label(8, row, vo.getNewWork(),
								normalLeftFormat));
						sheet1.addCell(new Label(10, row, vo.getNewGworkCode(),
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 1, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 1, normalCenterFormat));
						sheet1.addCell(new Label(2, row + 1, vo.getOldSec(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 1, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 1, normalCenterFormat));
						sheet1.addCell(new Label(7, row + 1, vo.getNewSec(),
								normalLeftLineFormat));
						sheet1.addCell(new Blank(10, row + 1,
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 2, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 2, normalCenterFormat));
						sheet1.addCell(new Label(2, row + 2, vo.getOldDuty(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 2, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 2, normalCenterFormat));
						sheet1.addCell(new Label(7, row + 2, vo.getNewDuty(),
								normalLeftLineFormat));
						sheet1.addCell(new Blank(10, row + 2,
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 3, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 3, normalCenterFormat));
						sheet1.addCell(new Label(4, row + 3, vo.getOldDiv(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 3, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 3, normalCenterFormat));
						sheet1.addCell(new Label(9, row + 3, vo.getNewDiv(),
								normalLeftFormat));
						sheet1.addCell(new Blank(10, row + 3,
								normalCenterFormat));
						row = row + 4;

					}

				} else if (vo.getOldDuty() == null && vo.getNewDuty() != null) {
					if (vo.getOldSec() == null && vo.getNewSec() == null) {

						sheet1.addCell(new Label(0, row, String.valueOf(dfInt
								.format(seq)), normalCenterCenterFormat));
						sheet1.addCell(new Label(1, row, vo.getName(),
								normalLeftLineFormat));
						sheet1.addCell(new Label(2, row, vo.getOldPosition(),
								normalLeftLineFormat));
						sheet1.addCell(new Label(3, row, vo.getOldWork(),
								normalLeftFormat));
						sheet1.addCell(new Label(5, row, vo.getEmpCode(),
								normalCenterFormat));
						sheet1.addCell(new Label(6, row, vo.getAccount(),
								normalCenterFormat));
						sheet1.addCell(new Label(7, row, vo.getNewPosition(),
								normalLeftFormat));
						sheet1.addCell(new Label(8, row, vo.getNewWork(),
								normalLeftFormat));
						sheet1.addCell(new Label(10, row, vo.getNewGworkCode(),
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 1, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 1, normalCenterFormat));
						sheet1.addCell(new Label(2, row + 1, vo.getOldDuty(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 1, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 1, normalCenterFormat));
						sheet1.addCell(new Label(7, row + 1, vo.getNewDuty(),
								normalLeftLineFormat));
						sheet1.addCell(new Blank(10, row + 1,
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 2, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 2, normalCenterFormat));
						sheet1.addCell(new Label(4, row + 2, vo.getOldDiv(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 2, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 2, normalCenterFormat));
						sheet1.addCell(new Label(9, row + 2, vo.getNewDiv(),
								normalLeftFormat));
						sheet1.addCell(new Blank(10, row + 2,
								normalCenterFormat));
						row = row + 3;

					} else {

						sheet1.addCell(new Label(0, row, String.valueOf(dfInt
								.format(seq)), normalCenterCenterFormat));
						sheet1.addCell(new Label(1, row, vo.getName(),
								normalLeftLineFormat));
						sheet1.addCell(new Label(2, row, vo.getOldPosition(),
								normalLeftLineFormat));
						sheet1.addCell(new Label(3, row, vo.getOldWork(),
								normalLeftFormat));
						sheet1.addCell(new Label(5, row, vo.getEmpCode(),
								normalCenterFormat));
						sheet1.addCell(new Label(6, row, vo.getAccount(),
								normalCenterFormat));
						sheet1.addCell(new Label(7, row, vo.getNewPosition(),
								normalLeftFormat));
						sheet1.addCell(new Label(8, row, vo.getNewWork(),
								normalLeftFormat));
						sheet1.addCell(new Label(10, row, vo.getNewGworkCode(),
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 1, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 1, normalCenterFormat));
						sheet1.addCell(new Label(2, row + 1, vo.getOldSec(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 1, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 1, normalCenterFormat));
						sheet1.addCell(new Label(7, row + 1, vo.getNewSec(),
								normalLeftLineFormat));
						sheet1.addCell(new Blank(10, row + 1,
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 2, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 2, normalCenterFormat));
						sheet1.addCell(new Label(2, row + 2, vo.getOldDuty(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 2, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 2, normalCenterFormat));
						sheet1.addCell(new Label(7, row + 2, vo.getNewDuty(),
								normalLeftLineFormat));
						sheet1.addCell(new Blank(10, row + 2,
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 3, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 3, normalCenterFormat));
						sheet1.addCell(new Label(4, row + 3, vo.getOldDiv(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 3, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 3, normalCenterFormat));
						sheet1.addCell(new Label(9, row + 3, vo.getNewDiv(),
								normalLeftFormat));
						sheet1.addCell(new Blank(10, row + 3,
								normalCenterFormat));
						row = row + 4;

					}

				}
			} else { // line

				if (vo.getOldDuty() != null && vo.getNewDuty() != null) {
					if (vo.getOldSec() == null && vo.getNewSec() == null) {

						sheet1.addCell(new Label(0, row, String.valueOf(dfInt
								.format(seq)), normalCenterCenterFormat));
						sheet1.addCell(new Label(1, row, vo.getName(),
								normalLeftLineFormat));
						sheet1.addCell(new Label(2, row, vo.getOldPosition(),
								normalLeftLineFormat));
						sheet1.addCell(new Label(3, row, vo.getOldWork(),
								normalLeftFormat));
						sheet1.addCell(new Label(5, row, vo.getEmpCode(),
								normalCenterFormat));
						sheet1.addCell(new Label(6, row, vo.getAccount(),
								normalCenterFormat));
						sheet1.addCell(new Label(7, row, vo.getNewPosition(),
								normalLeftFormat));
						sheet1.addCell(new Label(8, row, vo.getNewWork(),
								normalLeftFormat));
						sheet1.addCell(new Label(10, row, vo.getNewGworkCode(),
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 1, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 1, normalCenterFormat));
						sheet1.addCell(new Label(2, row + 1, vo.getOldDuty(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 1, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 1, normalCenterFormat));
						sheet1.addCell(new Label(7, row + 1, vo.getNewDuty(),
								normalLeftLineFormat));
						sheet1.addCell(new Blank(10, row + 1,
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 2, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 2, normalCenterFormat));
						sheet1.addCell(new Label(4, row + 2, vo.getOldDiv(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 2, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 2, normalCenterFormat));
						sheet1.addCell(new Label(9, row + 2, vo.getNewDiv(),
								normalLeftFormat));
						sheet1.addCell(new Blank(10, row + 2,
								normalCenterFormat));
						row = row + 3;

					} else {

						sheet1.addCell(new Label(0, row, String.valueOf(dfInt
								.format(seq)), normalCenterCenterFormat));
						sheet1.addCell(new Label(1, row, vo.getName(),
								normalLeftLineFormat));
						sheet1.addCell(new Label(2, row, vo.getOldPosition(),
								normalLeftLineFormat));
						sheet1.addCell(new Label(3, row, vo.getOldWork(),
								normalLeftFormat));
						sheet1.addCell(new Label(5, row, vo.getEmpCode(),
								normalCenterFormat));
						sheet1.addCell(new Label(6, row, vo.getAccount(),
								normalCenterFormat));
						sheet1.addCell(new Label(7, row, vo.getNewPosition(),
								normalLeftFormat));
						sheet1.addCell(new Label(8, row, vo.getNewWork(),
								normalLeftFormat));
						sheet1.addCell(new Label(10, row, vo.getNewGworkCode(),
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 1, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 1, normalCenterFormat));
						sheet1.addCell(new Label(2, row + 1, vo.getOldSec(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 1, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 1, normalCenterFormat));
						sheet1.addCell(new Label(7, row + 1, vo.getNewSec(),
								normalLeftLineFormat));
						sheet1.addCell(new Blank(10, row + 1,
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 2, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 2, normalCenterFormat));
						sheet1.addCell(new Label(2, row + 2, vo.getOldDuty(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 2, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 2, normalCenterFormat));
						sheet1.addCell(new Label(7, row + 2, vo.getNewDuty(),
								normalLeftLineFormat));
						sheet1.addCell(new Blank(10, row + 2,
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 3, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 3, normalCenterFormat));
						sheet1.addCell(new Label(4, row + 3, vo.getOldDiv(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 3, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 3, normalCenterFormat));
						sheet1.addCell(new Label(9, row + 3, vo.getNewDiv(),
								normalLeftFormat));
						sheet1.addCell(new Blank(10, row + 3,
								normalCenterFormat));
						row = row + 4;

					}
				} else if (vo.getOldDuty() == null && vo.getNewDuty() == null) {
					if (vo.getOldSec() == null && vo.getNewSec() == null) {

						sheet1.addCell(new Label(0, row, String.valueOf(dfInt
								.format(seq)), normalCenterCenterFormat));
						sheet1.addCell(new Label(1, row, vo.getName(),
								normalLeftLineFormat));
						sheet1.addCell(new Label(2, row, vo.getOldPosition(),
								normalLeftLineFormat));
						sheet1.addCell(new Label(3, row, vo.getOldWork(),
								normalLeftFormat));
						sheet1.addCell(new Label(5, row, vo.getEmpCode(),
								normalCenterFormat));
						sheet1.addCell(new Label(6, row, vo.getAccount(),
								normalCenterFormat));
						sheet1.addCell(new Label(7, row, vo.getNewPosition(),
								normalLeftFormat));
						sheet1.addCell(new Label(8, row, vo.getNewWork(),
								normalLeftFormat));
						sheet1.addCell(new Label(10, row, vo.getNewGworkCode(),
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 1, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 1, normalCenterFormat));
						sheet1.addCell(new Label(4, row + 1, vo.getOldDiv(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 1, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 1, normalCenterFormat));
						sheet1.addCell(new Label(9, row + 1, vo.getNewDiv(),
								normalLeftFormat));
						sheet1.addCell(new Blank(10, row + 1,
								normalCenterFormat));
						row = row + 2;

					} else {

						sheet1.addCell(new Label(0, row, String.valueOf(dfInt
								.format(seq)), normalCenterCenterFormat));
						sheet1.addCell(new Label(1, row, vo.getName(),
								normalLeftLineFormat));
						sheet1.addCell(new Label(2, row, vo.getOldPosition(),
								normalLeftLineFormat));
						sheet1.addCell(new Label(3, row, vo.getOldWork(),
								normalLeftFormat));
						sheet1.addCell(new Label(5, row, vo.getEmpCode(),
								normalCenterFormat));
						sheet1.addCell(new Label(6, row, vo.getAccount(),
								normalCenterFormat));
						sheet1.addCell(new Label(7, row, vo.getNewPosition(),
								normalLeftFormat));
						sheet1.addCell(new Label(8, row, vo.getNewWork(),
								normalLeftFormat));
						sheet1.addCell(new Label(10, row, vo.getNewGworkCode(),
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 1, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 1, normalCenterFormat));
						sheet1.addCell(new Label(2, row + 1, vo.getOldSec(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 1, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 1, normalCenterFormat));
						sheet1.addCell(new Label(7, row + 1, vo.getNewSec(),
								normalLeftLineFormat));
						sheet1.addCell(new Blank(10, row + 1,
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 2, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 2, normalCenterFormat));
						sheet1.addCell(new Label(4, row + 2, vo.getOldDiv(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 2, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 2, normalCenterFormat));
						sheet1.addCell(new Label(9, row + 2, vo.getNewDiv(),
								normalLeftFormat));
						sheet1.addCell(new Blank(10, row + 2,
								normalCenterFormat));
						row = row + 3;

					}

				} else if (vo.getOldDuty() != null && vo.getNewDuty() == null) {
					if (vo.getOldSec() == null && vo.getNewSec() == null) {

						sheet1.addCell(new Label(0, row, String.valueOf(dfInt
								.format(seq)), normalCenterCenterFormat));
						sheet1.addCell(new Label(1, row, vo.getName(),
								normalLeftLineFormat));
						sheet1.addCell(new Label(2, row, vo.getOldPosition(),
								normalLeftLineFormat));
						sheet1.addCell(new Label(3, row, vo.getOldWork(),
								normalLeftFormat));
						sheet1.addCell(new Label(5, row, vo.getEmpCode(),
								normalCenterFormat));
						sheet1.addCell(new Label(6, row, vo.getAccount(),
								normalCenterFormat));
						sheet1.addCell(new Label(7, row, vo.getNewPosition(),
								normalLeftFormat));
						sheet1.addCell(new Label(8, row, vo.getNewWork(),
								normalLeftFormat));
						sheet1.addCell(new Label(10, row, vo.getNewGworkCode(),
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 1, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 1, normalCenterFormat));
						sheet1.addCell(new Label(2, row + 1, vo.getOldDuty(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 1, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 1, normalCenterFormat));
						sheet1.addCell(new Label(7, row + 1, vo.getNewDuty(),
								normalLeftLineFormat));
						sheet1.addCell(new Blank(10, row + 1,
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 2, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 2, normalCenterFormat));
						sheet1.addCell(new Label(4, row + 2, vo.getOldDiv(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 2, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 2, normalCenterFormat));
						sheet1.addCell(new Label(9, row + 2, vo.getNewDiv(),
								normalLeftFormat));
						sheet1.addCell(new Blank(10, row + 2,
								normalCenterFormat));
						row = row + 3;

					} else {

						sheet1.addCell(new Label(0, row, String.valueOf(dfInt
								.format(seq)), normalCenterCenterFormat));
						sheet1.addCell(new Label(1, row, vo.getName(),
								normalLeftLineFormat));
						sheet1.addCell(new Label(2, row, vo.getOldPosition(),
								normalLeftLineFormat));
						sheet1.addCell(new Label(3, row, vo.getOldWork(),
								normalLeftFormat));
						sheet1.addCell(new Label(5, row, vo.getEmpCode(),
								normalCenterFormat));
						sheet1.addCell(new Label(6, row, vo.getAccount(),
								normalCenterFormat));
						sheet1.addCell(new Label(7, row, vo.getNewPosition(),
								normalLeftFormat));
						sheet1.addCell(new Label(8, row, vo.getNewWork(),
								normalLeftFormat));
						sheet1.addCell(new Label(10, row, vo.getNewGworkCode(),
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 1, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 1, normalCenterFormat));
						sheet1.addCell(new Label(2, row + 1, vo.getOldSec(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 1, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 1, normalCenterFormat));
						sheet1.addCell(new Label(7, row + 1, vo.getNewSec(),
								normalLeftLineFormat));
						sheet1.addCell(new Blank(10, row + 1,
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 2, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 2, normalCenterFormat));
						sheet1.addCell(new Label(2, row + 2, vo.getOldDuty(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 2, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 2, normalCenterFormat));
						sheet1.addCell(new Label(7, row + 2, vo.getNewDuty(),
								normalLeftLineFormat));
						sheet1.addCell(new Blank(10, row + 2,
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 3, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 3, normalCenterFormat));
						sheet1.addCell(new Label(4, row + 3, vo.getOldDiv(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 3, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 3, normalCenterFormat));
						sheet1.addCell(new Label(9, row + 3, vo.getNewDiv(),
								normalLeftFormat));
						sheet1.addCell(new Blank(10, row + 3,
								normalCenterFormat));
						row = row + 4;

					}

				} else if (vo.getOldDuty() == null && vo.getNewDuty() != null) {
					if (vo.getOldSec() == null && vo.getNewSec() == null) {

						sheet1.addCell(new Label(0, row, String.valueOf(dfInt
								.format(seq)), normalCenterCenterFormat));
						sheet1.addCell(new Label(1, row, vo.getName(),
								normalLeftLineFormat));
						sheet1.addCell(new Label(2, row, vo.getOldPosition(),
								normalLeftLineFormat));
						sheet1.addCell(new Label(3, row, vo.getOldWork(),
								normalLeftFormat));
						sheet1.addCell(new Label(5, row, vo.getEmpCode(),
								normalCenterFormat));
						sheet1.addCell(new Label(6, row, vo.getAccount(),
								normalCenterFormat));
						sheet1.addCell(new Label(7, row, vo.getNewPosition(),
								normalLeftFormat));
						sheet1.addCell(new Label(8, row, vo.getNewWork(),
								normalLeftFormat));
						sheet1.addCell(new Label(10, row, vo.getNewGworkCode(),
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 1, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 1, normalCenterFormat));
						sheet1.addCell(new Label(2, row + 1, vo.getOldDuty(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 1, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 1, normalCenterFormat));
						sheet1.addCell(new Label(7, row + 1, vo.getNewDuty(),
								normalLeftLineFormat));
						sheet1.addCell(new Blank(10, row + 1,
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 2, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 2, normalCenterFormat));
						sheet1.addCell(new Label(4, row + 2, vo.getOldDiv(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 2, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 2, normalCenterFormat));
						sheet1.addCell(new Label(9, row + 2, vo.getNewDiv(),
								normalLeftFormat));
						sheet1.addCell(new Blank(10, row + 2,
								normalCenterFormat));
						row = row + 3;

					} else {

						sheet1.addCell(new Label(0, row, String.valueOf(dfInt
								.format(seq)), normalCenterFormat));
						sheet1.addCell(new Label(1, row, vo.getName(),
								normalLeftLineFormat));
						sheet1.addCell(new Label(2, row, vo.getOldPosition(),
								normalLeftLineFormat));
						sheet1.addCell(new Label(3, row, vo.getOldWork(),
								normalLeftFormat));
						sheet1.addCell(new Label(5, row, vo.getEmpCode(),
								normalCenterFormat));
						sheet1.addCell(new Label(6, row, vo.getAccount(),
								normalCenterFormat));
						sheet1.addCell(new Label(7, row, vo.getNewPosition(),
								normalLeftFormat));
						sheet1.addCell(new Label(8, row, vo.getNewWork(),
								normalLeftFormat));
						sheet1.addCell(new Label(10, row, vo.getNewGworkCode(),
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 1, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 1, normalCenterFormat));
						sheet1.addCell(new Label(2, row + 1, vo.getOldSec(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 1, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 1, normalCenterFormat));
						sheet1.addCell(new Label(7, row + 1, vo.getNewSec(),
								normalLeftLineFormat));
						sheet1.addCell(new Blank(10, row + 1,
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 2, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 2, normalCenterFormat));
						sheet1.addCell(new Label(2, row + 2, vo.getOldDuty(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 2, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 2, normalCenterFormat));
						sheet1.addCell(new Label(7, row + 2, vo.getNewDuty(),
								normalLeftLineFormat));
						sheet1.addCell(new Blank(10, row + 2,
								normalCenterFormat));
						sheet1.addCell(new Blank(0, row + 3, normalRightFormat));
						sheet1.addCell(new Blank(1, row + 3, normalCenterFormat));
						sheet1.addCell(new Label(4, row + 3, vo.getOldDiv(),
								normalLeftFormat));
						sheet1.addCell(new Blank(5, row + 3, normalCenterFormat));
						sheet1.addCell(new Blank(6, row + 3, normalCenterFormat));
						sheet1.addCell(new Label(9, row + 3, vo.getNewDiv(),
								normalLeftFormat));
						sheet1.addCell(new Blank(10, row + 3,
								normalCenterFormat));
						row = row + 4;

					}

				}
			}// end line

		}// end for
		for (int i = 0; i < 12; i++) {
			sheet1.setRowView(row, 320);
			sheet1.addCell(new Label(i, row, "", borderEndLine));
		}

		ww.write();
		ww.close();
		wb.close();
		in.close();

		return null;
	}
}

/*
 * sheet1.addCell(new Label(0,
 * row,String.valueOf(dfInt.format(seq)),normalRightFormat)); sheet1.addCell(new
 * Label (1,row,vo.getName(),normalLeftLineFormat)); sheet1.addCell(new Label
 * (2,row,vo.getOldPosition(),normalLeftLineFormat)); sheet1.addCell(new Label
 * (3,row,vo.getOldWork(),normalLeftFormat)); sheet1.addCell(new Label
 * (5,row,vo.getEmpCode(),normalCenterFormat)); sheet1.addCell(new Label
 * (6,row,vo.getAccount(),normalCenterFormat)); sheet1.addCell(new Label
 * (7,row,vo.getNewPosition(),normalLeftFormat)); sheet1.addCell(new Label
 * (8,row,vo.getNewWork(),normalLeftFormat)); sheet1.addCell(new Label
 * (10,row,vo.getNewGworkCode(),normalCenterFormat)); sheet1.addCell(new
 * Blank(0, row+1, normalRightFormat)); sheet1.addCell(new Blank(1, row+1,
 * normalCenterFormat)); sheet1.addCell(new Label
 * (2,row+1,vo.getOldSec(),normalLeftFormat)); sheet1.addCell(new Blank(5,
 * row+1, normalCenterFormat)); sheet1.addCell(new Blank(6, row+1,
 * normalCenterFormat)); sheet1.addCell(new Label
 * (7,row+1,vo.getNewSec(),normalLeftLineFormat)); sheet1.addCell(new Blank(10,
 * row+1, normalCenterFormat)); sheet1.addCell(new Blank(0, row+2,
 * normalRightFormat)); sheet1.addCell(new Blank(1, row+2, normalCenterFormat));
 * sheet1.addCell(new Label (4,row+2,vo.getOldDiv(),normalLeftFormat));
 * sheet1.addCell(new Blank(5, row+2, normalCenterFormat)); sheet1.addCell(new
 * Blank(6, row+2, normalCenterFormat)); sheet1.addCell(new Label
 * (9,row+2,vo.getNewDiv(),normalLeftFormat)); sheet1.addCell(new Blank(10,
 * row+2, normalCenterFormat)); row=row+3;
 */
/*
 * sheet1.addCell(new Label(0,
 * row,String.valueOf(dfInt.format(seq)),normalRightFormat)); sheet1.addCell(new
 * Label (1,row,vo.getName(),normalLeftLineFormat)); sheet1.addCell(new Label
 * (2,row,vo.getOldPosition(),normalLeftLineFormat)); sheet1.addCell(new Label
 * (3,row,vo.getOldWork(),normalLeftFormat)); sheet1.addCell(new Label
 * (5,row,vo.getEmpCode(),normalCenterFormat)); sheet1.addCell(new Label
 * (6,row,vo.getAccount(),normalCenterFormat)); sheet1.addCell(new Label
 * (7,row,vo.getNewPosition(),normalLeftFormat)); sheet1.addCell(new Label
 * (8,row,vo.getNewWork(),normalLeftFormat)); sheet1.addCell(new Label
 * (10,row,vo.getNewGworkCode(),normalCenterFormat)); //sheet1.addCell(new
 * Blank(0, row+1, normalRightFormat)); //sheet1.addCell(new Blank(1, row+1,
 * normalCenterFormat)); //sheet1.addCell(new Label
 * (2,row+1,vo.getOldSec(),normalLeftFormat)); //sheet1.addCell(new Blank(5,
 * row+1, normalCenterFormat)); //sheet1.addCell(new Blank(6, row+1,
 * normalCenterFormat)); //sheet1.addCell(new Label
 * (7,row+1,vo.getNewSec(),normalLeftLineFormat)); //sheet1.addCell(new
 * Blank(10, row+1, normalCenterFormat)); sheet1.addCell(new Blank(0, row+1,
 * normalRightFormat)); sheet1.addCell(new Blank(1, row+1, normalCenterFormat));
 * sheet1.addCell(new Blank(2, row+1, normalLeftFormat)); sheet1.addCell(new
 * Label (4,row+1,vo.getOldDiv(),normalLeftFormat)); sheet1.addCell(new Blank(5,
 * row+1, normalCenterFormat)); sheet1.addCell(new Blank(6, row+1,
 * normalCenterFormat)); sheet1.addCell(new Label
 * (9,row+1,vo.getNewDiv(),normalLeftFormat)); sheet1.addCell(new Blank(10,
 * row+1, normalCenterFormat)); row=row+2;
 */

