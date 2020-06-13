/*
 * Created on 17 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.control;



import com.ss.tp.dto.ApEmpBankVO;
import com.ss.tp.security.UserInfo;
//import com.ss.tp.service.ApTableService;
import com.ss.tp.service.FeeWpayPrEmployeeService;
//import com.ss.tp.service.SuOrganizeService;

import java.io.InputStream;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

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
//import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableFont.FontName;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;


/**
 * @author
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class CTWPAYRPKTB001Controller extends MultiActionController {

	

	private FeeWpayPrEmployeeService getFeeWpayPrEmployeeService() {
		return (FeeWpayPrEmployeeService) this.getApplicationContext().getBean(
				"feeWpayPrEmployeeService");
	}

	

	public ModelAndView doPrintReport(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DecimalFormat df = new DecimalFormat("###,##0.00");
		DecimalFormat dfInt = new DecimalFormat("###,##0");
		DecimalFormat dfYear = new DecimalFormat("#####0");
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("UserLogin");
		//String ouCode = "";
		//String userId = "";
		int year = 0;
		int month = 0;
		//String volume="";
		int row = 0;
		int seq = 0;

		String evaYear = "";
		String evaMonth = "";
	

		//int countEmp = 0;

		if (request.getParameter("workYear") != null
				&& !"".equals(request.getParameter("workYear"))) {
			evaYear = request.getParameter("workYear");
		}
		if (request.getParameter("workMonth") != null
				&& !"".equals(request.getParameter("workMonth"))) {
			evaMonth = request.getParameter("workMonth");
		}

	
		//System.out.println(evaVolume);
		System.out.println(evaYear);
		System.out.println(evaMonth);
        year =  Integer.parseInt(evaYear);
        month = Integer.parseInt(evaMonth);

		List empList = this.getFeeWpayPrEmployeeService().wpayKtbBankReport(userInfo.getUserId(), userInfo.getOuCode(),
				year, month);
		//String ouDesc = this.getSuOrganizeService().findOrganizeName(ouCode);

		// ----------------- Generate Report Detail
		// -------------------------------
		
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition","attachment; filename=./page/report/CTWPAYKTBRP001.xls");
		InputStream in = this.getServletContext().getResourceAsStream("/page/report/CTWPAYKTBRP001.xls");
		WritableFont fontBold = new WritableFont(WritableFont.ARIAL);
		fontBold.setBoldStyle(WritableFont.BOLD);
		fontBold.setPointSize(12);

		// WritableFont fontNormal = new WritableFont(WritableFont.ARIAL);
		// fontNormal.setBoldStyle(WritableFont.NO_BOLD);
		// fontNormal.setPointSize(9);

		FontName fontName = WritableFont.createFont("ARIAL");
		WritableFont fontNormal = new WritableFont(fontName, 10);
		fontNormal.setBoldStyle(WritableFont.NO_BOLD);
		fontNormal.setPointSize(10);

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

		WritableCellFormat normalCenterFormat = new WritableCellFormat();
		// normalCenterFormat.setBorder(Border.LEFT,BorderLineStyle.THIN);
		// normalCenterFormat.setBorder(Border.RIGHT,BorderLineStyle.THIN);
		normalCenterFormat.setAlignment(dataAlignCenter);
		normalCenterFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		normalCenterFormat.setFont(fontNormal);

		WritableCellFormat normalRightFormat = new WritableCellFormat();
		// normalRightFormat.setBorder(Border.LEFT,BorderLineStyle.THIN);
		// normalRightFormat.setBorder(Border.RIGHT,BorderLineStyle.THIN);
		normalRightFormat.setAlignment(dataAlignRight);
		normalRightFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		normalRightFormat.setFont(fontNormal);

		WritableCellFormat normalLeftLastFormat = new WritableCellFormat();
		// normalLeftLastFormat.setBorder(Border.LEFT,BorderLineStyle.THIN);
		// normalLeftLastFormat.setBorder(Border.RIGHT,BorderLineStyle.THIN);
		// normalLeftLastFormat.setBorder(Border.BOTTOM,BorderLineStyle.THIN);
		normalLeftLastFormat.setAlignment(dataAlignLeft);
		normalLeftLastFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		normalLeftLastFormat.setFont(fontNormal);

		WritableCellFormat normalCenterLastFormat = new WritableCellFormat();
		// normalCenterLastFormat.setBorder(Border.LEFT,BorderLineStyle.THIN);
		// normalCenterLastFormat.setBorder(Border.RIGHT,BorderLineStyle.THIN);
		// normalCenterLastFormat.setBorder(Border.BOTTOM,BorderLineStyle.THIN);
		normalCenterLastFormat.setAlignment(dataAlignCenter);
		normalCenterLastFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		normalCenterLastFormat.setFont(fontNormal);

		WritableCellFormat normalRightLastFormat = new WritableCellFormat();
		// normalRightLastFormat.setBorder(Border.LEFT,BorderLineStyle.THIN);
		// normalRightLastFormat.setBorder(Border.RIGHT,BorderLineStyle.THIN);
		// normalRightLastFormat.setBorder(Border.BOTTOM,BorderLineStyle.THIN);
		normalRightLastFormat.setAlignment(dataAlignRight);
		normalRightLastFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		normalRightLastFormat.setFont(fontNormal);

		WritableCellFormat borderNumber2 = new WritableCellFormat();
		borderNumber2.setBorder(Border.ALL, BorderLineStyle.THIN);
		borderNumber2.setAlignment(Alignment.RIGHT);
		borderNumber2.setFont(fontBold);

		Workbook wb = Workbook.getWorkbook(in);
		WritableWorkbook ww = Workbook.createWorkbook(
				response.getOutputStream(), wb);
		WritableSheet sheet1 = ww.getSheet(0);

		SimpleDateFormat sdfPrint = new SimpleDateFormat("dd/MM/yyyy",
				new java.util.Locale("th", "TH"));
		if (empList.size() > 0) {
		row = 2;
	
		
		for (int i = 0; i < empList.size(); i++) {
			ApEmpBankVO vo = (ApEmpBankVO) empList.get(i);
		
			sheet1.addCell(new Label(0, row, vo.getBankId().trim(),
					normalCenterLastFormat));
			sheet1.addCell(new Label(1, row, vo.getEmpName(),
					normalLeftLastFormat));
			double d = Double.valueOf(vo.getAmount().trim()).doubleValue();
			//double d = Double.valueOf(vo.getAmount().trim()).doubleValue();
			sheet1.addCell(new Label(2, row, String.valueOf(df.format(d)),
					normalRightLastFormat));
			//sheet1.addCell(new Number(2, row, d));
			sheet1.addCell(new Label(3, row, vo.getEmpCode(),
					normalCenterLastFormat));
			// double d = Double.valueOf(vo.getAmount().trim()).doubleValue();

			/*
			 * tempEmpCode=vo.getEmpCode();
			 * 
			 * tempEmpCode1=tempEmpCode;
			 * 
			 * if(tempEmpCode1 != tempEmpCode){ sheet1.addCell(new Label
			 * (1,row,vo.getEmpCode())); }else{ sheet1.addCell(new Label
			 * (1,row,null)); }
			 */
			row++;
			/* tempEmpCode1=""; */
		}
		sheet1.setName("Detail");
		//ww.copySheet("Sheet1","Detail", ww.getNumberOfSheets());
		sheet1.getSettings().setPassword("028313766#$");
		sheet1.getSettings().setProtected(true);
		ww.setProtected(true);
		ww.write();
		ww.close();
		wb.close();
		in.close();

		return null;
	} else {
		//sheet1.addCell(new Label(0, 0, ouDesc, HeadFormat));

		sheet1.addCell(new Label(0, 8, "ไม่พบข้อมูล",normalCenterLastFormat));	
		sheet1.getSettings().setPassword("#028313766#$");
		sheet1.getSettings().setProtected(true);
		ww.setProtected(true);
       
		ww.close();
		wb.close();
		in.close();
		return null;
	}
}
}