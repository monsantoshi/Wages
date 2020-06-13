package com.ss.tp.model;

public class PrEmployeeTextForSort implements Comparable {

	private String inDecCode;
	private String name;
	private Double count;

	public Double getCount() {
		return count;
	}

	public void setCount(Double count) {
		this.count = count;
	}

	public String getInDecCode() {
		return inDecCode;
	}

	public void setInDecCode(String inDecCode) {
		this.inDecCode = inDecCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int compareTo(Object prEmpSort) {
		// TODO Auto-generated method stub
		if (!(prEmpSort instanceof PrEmployeeTextForSort))
			throw new ClassCastException(
					"A PrEmployeeTextForSort object expected.");
		int inDecCode = Integer.parseInt(((PrEmployeeTextForSort) prEmpSort)
				.getInDecCode());
		return Integer.parseInt(this.inDecCode) - inDecCode;
	}

}
