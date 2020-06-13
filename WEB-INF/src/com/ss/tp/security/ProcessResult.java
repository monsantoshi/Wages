/*
 * Created on 2 �.�. 2548
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sommaik
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class ProcessResult implements Serializable {
	private String type;
	private String result;
	private String source;
	private String reqCode;
	private List results;

	// private ResourceBundleMessageSource messageSource;

	public ProcessResult() {
		results = new ArrayList();
		this.type = "INFO";
	}

	// public ProcessResult(ResourceBundleMessageSource messageSource){
	// this();
	// this.messageSource = messageSource;
	// }

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getReqCode() {
		return reqCode;
	}

	public void setReqCode(String reqCode) {
		this.reqCode = reqCode;
	}

	public List getResults() {
		return results;
	}

	public void setResults(List results) {
		this.results = results;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	// public void addResult(Result r){
	// r.setMessage(this.messageSource.getMessage(r.getMessage(), r.getArgs(),
	// r.getMessage(), Locale.ENGLISH));
	// this.results.add(r);
	// if("ERROR".equals(r.getType())){
	// this.type=r.getType();
	// }
	// }
	public int getResultSize() {
		return this.results.size();
	}
}
