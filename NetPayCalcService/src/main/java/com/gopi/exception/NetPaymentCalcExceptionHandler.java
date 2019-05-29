package com.gopi.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gopi.response.NetPaymentCalcResponse;

@ControllerAdvice
public class NetPaymentCalcExceptionHandler {

	@Autowired
	NetPaymentCalcResponse response;

	@ExceptionHandler(value = NetPaymentCalcException.class)
	public ResponseEntity<Object> exception(NetPaymentCalcException exception) {

		response.setFinalBillAmt(0.0);
		response.setErrorCode("111");
		response.setErrorDesc(exception.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
