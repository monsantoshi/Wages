package com.ss.tp.model;

public class PrEmployeeTextErrorForSort implements Comparable {

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
		if (!(prEmpErrorSort instanceof PrEmployeeTextErrorForSort))
			throw new ClassCastException(
					"A PrEmployeeTextErrorForSort object expected.");
		int empCode = Integer.parseInt(((PrEmployeeTextErrorForSort) prEmpErrorSort)
				.getEmpCode());
		return Integer.parseInt(this.empCode) - empCode;
	}

}
