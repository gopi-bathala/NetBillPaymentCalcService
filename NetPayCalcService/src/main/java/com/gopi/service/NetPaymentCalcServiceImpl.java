package com.gopi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gopi.constants.NetPayCalcConstants;
import com.gopi.model.PaymentInformation;



@Service
public class NetPaymentCalcServiceImpl implements NetPaymentCalcService {

	private static final Logger log = LogManager.getLogger(NetPaymentCalcServiceImpl.class);
	
	@Autowired
	NetPayCalcConstants constants;
	
	@Override
	public Double calculateNetBillAmount(PaymentInformation paymentDet) throws Exception {
		
		log.info("NetPaymentCalcServiceImpl.calculateNetBillAmount started"); 
		
		String customerType = paymentDet.getCustomerType();
		String purchaseType = paymentDet.getPurchaseType();
		Double initialBillAmt = paymentDet.getBillAmt(); 
		Double discountPercent = 0.0;
		
		// Applying aforesaid logic to calculate the final discounted net amount. 
		//Please note, this logic details can be stored in DB or property file for better maintenance in enterprise usage. Since this is simple program, i have hard coded in pgm.
		
		log.info("NetPaymentCalcServiceImpl.calculateNetBillAmount input values -> customerType:" + customerType + ":purchaseType:" + purchaseType + ":initialBillAmt:" + initialBillAmt);
		
		if (NetPayCalcConstants.GROCERIES.equalsIgnoreCase(purchaseType)) {
			return initialBillAmt;
		}
		if (NetPayCalcConstants.EMPLOYEE.equalsIgnoreCase(customerType)) {
			discountPercent = 30.0d;
		} else if (NetPayCalcConstants.AFFILIATE.equalsIgnoreCase(customerType)) {
			discountPercent = 10.0d;
		} else if (NetPayCalcConstants.LOYALCUSTOMER.equalsIgnoreCase(customerType)) {
			discountPercent = 5.0d;
		} 
		
		log.info("NetPaymentCalcServiceImpl.calculateNetBillAmount ended");
		
		return calculateFinalAmt(initialBillAmt, discountPercent);
		
	}

	/**
	 * Description : This method is used to calculate the final discounted bill amount based on specific criteria.
	 * @param initialBillAmt Double
	 * @param discountPercent Double
	 * @return Double
	 */
	private Double calculateFinalAmt(Double initialBillAmt, Double discountPercent) throws Exception {
		
		log.info("NetPaymentCalcServiceImpl.calculateFinalAmt started");
		
		 Double finalAmt = 0.0;
		 Double discountedAmt = 0.0;
		
		if (discountPercent == 0.0) {
			
			Double tempVal = (initialBillAmt/100);
			discountedAmt = Math.floor(tempVal) * 5;
			finalAmt = initialBillAmt - discountedAmt;
			
		} else {
			discountedAmt = initialBillAmt * (discountPercent/100);
			finalAmt = initialBillAmt - discountedAmt;
		}
		
		log.info("NetPaymentCalcServiceImpl.calculateFinalAmt ended");
		
		return finalAmt;
	}
	
}
