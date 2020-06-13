/*
 * Created on 13 �.�. 2549
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.ss.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.time.FastDateFormat;
import org.apache.struts.taglib.TagUtils;

/**
 * @author Administrator
 * 
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DateTag extends BodyTagSupport {

	private String buttonId;
	private String name = "org.apache.struts.taglib.html.BEAN";
	private String property;
	private String scope = "page";
	private String styleClass;
	private String readonly;

	public int doStartTag() throws JspException {
		StringBuffer buff = new StringBuffer();
		String val;
		JspWriter out = pageContext.getOut();
		try {
			Object value = TagUtils.getInstance().lookup(this.pageContext,
					this.property, this.scope);
			FastDateFormat ff = FastDateFormat.getInstance("yyyy/MM/dd");
			if (value == null) {
				val = "";
			} else
				val = ff.format(value);
			buff.append(" <input type=\"text\"");
			buff.append(" value=\"");
			buff.append(val);
			buff.append("\"");
			buff.append(" name=\"");
			buff.append(this.property);
			buff.append("\" size=\"10\" maxlength=\"10\"");
			buff.append(" class=\"");
			buff.append(this.styleClass);
			buff.append("\"");
			if (readonly.equalsIgnoreCase("true")) {
				buff.append(" readonly");
			}
			buff.append(" />");
			buff.append("<input type='button'");
			buff.append(" name=\"");
			buff.append(buttonId);
			buff.append("\"");
			buff.append(" value=\"...\"");
			buff.append(" class=\"");
			buff.append(this.styleClass);
			buff.append("\"");
			// if(readonly.equalsIgnoreCase("true")){
			// buff.append(" disabled");
			// }
			buff.append(" /> \n");
			buff.append(" <script type=\"text/javascript\"> \n");
			buff.append(" Calendar.setup( \n");
			buff.append(" { \n");
			buff.append(" inputField: \"");
			buff.append(this.property);
			buff.append("\", \n");
			buff.append(" ifFormat: \"%Y/%m/%d\", \n");
			buff.append(" button: \"");
			buff.append(this.buttonId);
			buff.append("\",\n");
			buff.append(" align:\"Tr\"");
			buff.append("}\n);\n");
			buff.append(" </script>\n");
			out.println(buff);
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
	 * @return
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * @return
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * @param string
	 */
	public void setProperty(String string) {
		property = string;
	}

	/**
	 * @param string
	 */
	public void setScope(String string) {
		scope = string;
	}

	/**
	 * @return
	 */
	public String getButtonId() {
		return buttonId;
	}

	/**
	 * @param string
	 */
	public void setButtonId(String string) {
		buttonId = string;
	}

	/**
	 * @return
	 */
	public String getReadonly() {
		return readonly;
	}

	/**
	 * @return
	 */
	public String getStyleClass() {
		return styleClass;
	}

	/**
	 * @param string
	 */
	public void setReadonly(String string) {
		readonly = string;
	}

	/**
	 * @param string
	 */
	public void setStyleClass(String string) {
		styleClass = string;
	}

}
