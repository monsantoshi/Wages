package com.ss.tp.model;

public class FeeWpayPrMonthlyLineComparator implements Comparable {

	private String incDecCode;
	private String incDecName;
	private Double totAmt;

	public String getIncDecCode() {
		return incDecCode;
	}

	public void setIncDecCode(String incDecCode) {
		this.incDecCode = incDecCode;
	}

	public String getIncDecName() {
		return incDecName;
	}

	public void setIncDecName(String incDecName) {
		this.incDecName = incDecName;
	}

	public Double getTotAmt() {
		return totAmt;
	}

	public void setTotAmt(Double totAmt) {
		this.totAmt = totAmt;
	}

	public int compareTo(Object prEmpSort) {
		// TODO Auto-generated method stub
		if (!(prEmpSort instanceof FeeWpayPrMonthlyLineComparator))
			throw new ClassCastException(
					"A PrEmployeeTextForSort object expected.");
		int inDecCode = Integer.parseInt(((FeeWpayPrMonthlyLineComparator) prEmpSort)
				.getIncDecCode());
		return Integer.parseInt(this.incDecCode) - inDecCode;
	}

}
