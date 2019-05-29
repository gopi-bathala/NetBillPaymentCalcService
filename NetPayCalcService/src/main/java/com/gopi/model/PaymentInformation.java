package com.gopi.model;

import java.io.Serializable;

public class PaymentInformation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Double billAmt;
	private String customerType;
	private String purchaseType;
	
	public Double getBillAmt() {
		return billAmt;
	}
	public void setBillAmt(Double billAmt) {
		this.billAmt = billAmt;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getPurchaseType() {
		return purchaseType;
	}
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	
	
}
