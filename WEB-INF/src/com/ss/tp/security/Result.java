/*
 * Created on 25 �.�. 2548
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.security;

import java.io.Serializable;

/**
 * @author sommaik
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class Result implements Serializable {
	private String type;
	private String message;
	private Object[] args;
	private Exception exception;

	public Result() {
		this.type = "INFO";
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
		this.type = "ERROR";
		this.message = "error.default";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setMessage(String message, Object[] args) {
		this.message = message;
		this.args = args;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}
}
