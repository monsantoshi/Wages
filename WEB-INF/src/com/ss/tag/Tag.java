/*
 * Created on 23 �.�. 2548
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tag;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author sommaik
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class Tag extends TagSupport {
	private Properties props;
	private String name;

	public int doStartTag() throws JspException {
		try {
			this.pageContext.getOut().write(this.getProp(this.name + ".begin"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspException {
		try {
			this.pageContext.getOut().write(this.getProp(this.name + ".end"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	private String getProp(String key) {
		String ret = "";
		if (this.props == null) {
			InputStream is = this.getClass().getResourceAsStream(
					"Tag.properties");
			props = new Properties();
			try {
				props.load(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ret = props.getProperty(key);
		return ret;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
