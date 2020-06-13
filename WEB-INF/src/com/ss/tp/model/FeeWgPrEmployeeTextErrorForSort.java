package com.ss.tp.model;

public class FeeWgPrEmployeeTextErrorForSort implements Comparable {

    private String empCode;
	//private String name;
	private Double count;

	public Double getCount() {
		return count;
	}

	public void setCount(Double count) {
		this.count = count;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public int compareTo(Object prEmpErrorSort) {
		// TODO Auto-generated method stub
		if (!(prEmpErrorSort instanceof FeeWgPrEmployeeTextErrorForSort))
			throw new ClassCastException(
					"A PrEmployeeTextErrorForSort object expected.");
		int empCode = Integer.parseInt(((FeeWgPrEmployeeTextErrorForSort) prEmpErrorSort)
				.getEmpCode());
		return Integer.parseInt(this.empCode) - empCode;
	}

}
