package com.gopi.response;

public class NetPaymentCalcResponse {

	private Double finalBillAmt;
	private String errorCode;
	private String errorDesc;

	
	public Double getFinalBillAmt() {
		return finalBillAmt;
	}

	public void setFinalBillAmt(Double finalBillAmt) {
		this.finalBillAmt = finalBillAmt;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	
}
