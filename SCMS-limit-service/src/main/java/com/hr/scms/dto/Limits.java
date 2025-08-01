package com.hr.scms.dto;

import java.io.Serializable;

public class Limits implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int min;
	private int max;

	public Limits(int min, int max) {
		super();
		this.min = min;
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

}
