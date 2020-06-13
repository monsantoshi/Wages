/*
 * Created on 7 �.�. 2548
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.dto;

import java.io.Serializable;

/**
 * @author sommaik
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class SelectOption implements Serializable {
	private String label;
	private String value;

	public SelectOption() {
	}

	/**
	 * @param label
	 * @param value
	 */
	public SelectOption(String label, String value) {
		super();
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
