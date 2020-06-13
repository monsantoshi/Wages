/*
 * Created on 23 ��.�. 2547
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.ss.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.taglib.TagUtils;

/**
 * @author Sommai.K
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class DateInputTag extends TagSupport {
	private String[] months = { "", "January", "Febuary", "March", "April",
			"May", "June", "July", "August", "September", "October",
			"November", "December" };
	private StringBuffer dbuf;
	private StringBuffer mbuf;
	private StringBuffer ybuf;
	private char qoute = 34;
	private char ln = 10;
	private char close = 62;

	private String name = "org.apache.struts.taglib.html.BEAN";
	private String property;
	private String scope = "page";
	private DateBean calendar;

	/**
	 * @return Returns the calendar.
	 */
	public DateBean getCalendar() {
		return calendar;
	}

	/**
	 * @param calendar
	 *            The calendar to set.
	 */
	public void setCalendar(DateBean calendar) {
		this.calendar = calendar;
	}

	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		String dayId = this.property + "_day_";
		String monthId = this.property + "_month_";
		String yearId = this.property + "_year";
		try {
			Object value = TagUtils.getInstance().lookup(this.pageContext,
					this.name, this.property, this.scope);
			calendar = (DateBean) value;
			if (calendar == null)
				calendar = new DateBean();
			out.write("<select name=" + qoute);
			out.write(this.property + ".day" + qoute);
			out.write(" id=" + qoute);
			out.write(dayId + qoute + close + ln);
			out.write("<option value='-1'>&nbsp;</option>" + ln);
			for (int i = 1; i <= calendar.getAmountDay(calendar.getMonth(),
					calendar.getYear()); i++) {
				String select = "";
				if (i == calendar.getDay()) {
					select = " selected";
				}
				out.write("<option value=" + i + select + ">" + i + "</option>"
						+ ln);
			}
			out.write("</select>");
			out.write("<select name=" + qoute);
			out.write(this.property + ".month" + qoute);
			out.write(" id=" + qoute);
			out.write(monthId + qoute);
			out.write(" onChange=" + qoute + "redirect(" + dayId + ","
					+ monthId + ".options.value," + yearId + ".options.value)"
					+ qoute + close + ln);
			out.write("<option value='-1'>&nbsp;</option>" + ln);
			for (int i = 0; i < calendar.getMonthName().length; i++) {
				String select = "";
				if (i == calendar.getMonth()) {
					select = " selected";
				}
				out.write("<option value=" + i + select + ">"
						+ calendar.getMonthName()[i] + "</option>" + ln);
			}
			out.write("</select>");
			out.write("<select name=" + qoute);
			out.write(this.property + ".year" + qoute);
			out.write(" id=" + qoute);
			out.write(yearId + qoute);
			out.write(" onChange=" + qoute + "redirect(" + dayId + ","
					+ monthId + ".options.value," + yearId + ".options.value)"
					+ qoute + close + ln);
			out.write("<option value='-1'>&nbsp;</option>" + ln);
			for (int i = calendar.getYearCurrent() - 10; i <= calendar
					.getYearCurrent() + 10; i++) {
				String select = "";
				if (i == calendar.getYear()) {
					select = " selected";
				}
				if (i > 2500) {
					out.println("<option value=" + (i) + select + ">"
							+ (i - 543) + "</option>");
				} else {
					out.println("<option value=" + i + select + ">" + (i + 543)
							+ "</option>");
				}

			}
			out.write("</select>");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SKIP_BODY;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * @return
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * @param string
	 */
	public void setProperty(String string) {
		property = string;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
}
