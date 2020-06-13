/*
 * Created on 15 �.�. 2549
 *
 */
package com.ss.tag;

import javax.servlet.jsp.JspException;

import org.displaytag.tags.el.ExpressionEvaluator;

/**
 * @author sommaik
 * 
 */
public class ELDateTag extends DateTag {

	public int doStartTag() throws JspException {
		evaluateExpressions();
		return super.doStartTag();
	}

	private void evaluateExpressions() throws JspException {
		ExpressionEvaluator eval = new ExpressionEvaluator(this, pageContext);
		super.setStyleClass(eval.evalString("styleClass", super.getStyleClass()));
		super.setReadonly(eval.evalString("readonly", super.getReadonly()));
	}

}
