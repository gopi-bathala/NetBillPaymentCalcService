package com.gopi.service;

import com.gopi.model.PaymentInformation;

public interface NetPaymentCalcService {

	Double calculateNetBillAmount(PaymentInformation paymentDet) throws Exception;
}
