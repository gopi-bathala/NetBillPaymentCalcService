package com.gopi.controller;

import java.text.ParseException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gopi.exception.NetPaymentCalcException;
import com.gopi.model.PaymentInformation;
import com.gopi.response.NetPaymentCalcResponse;
import com.gopi.service.NetPaymentCalcService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api("/calculateNetBillAmt")
public class NetPaymentCalcController {

	@Autowired
	NetPaymentCalcService service;
	
	@Autowired
	NetPaymentCalcResponse response;
	
	
	private static final Logger log = LogManager.getLogger(NetPaymentCalcController.class);

	@RequestMapping(value = "/calculateNetBillAmt/{billAmt}/{customerType}/{purchaseType}", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Calculate Net Bill Amount", notes = "Calculate Net Bill Amount", response = NetPaymentCalcResponse.class)
    @ApiResponses({
        @ApiResponse(code = 200, message = "Success", response = NetPaymentCalcResponse.class),
        @ApiResponse(code = 400, message = "Bad request", response = NetPaymentCalcResponse.class),
        @ApiResponse(code = 404, message = "Not found", response = NetPaymentCalcResponse.class)
    })
	public ResponseEntity<Object> calculateNetPaymentValue(
	@ApiParam(required = true, name = "billAmt", value = "Enter the orignal bill amount before applying discount")
    @PathVariable String billAmt,
    
    @ApiParam(required = true, name = "customerType", value = "Enter Customer Type", allowableValues ="Employee,Affiliate,LoyalCustomer,Other")
    @PathVariable String customerType,
    
    @ApiParam(required = true, name = "purchaseType", value = "Enter Purchase Type", allowableValues = "Groceries,Other")
    @PathVariable String purchaseType) {

		log.info("NetPaymentCalcController.calculateNetPaymentValue started");
		
		Double finalAmt = 0.0;

		try {

			PaymentInformation paymentDetails = new PaymentInformation();
			paymentDetails.setBillAmt(Double.parseDouble(billAmt));
			paymentDetails.setCustomerType(customerType);
			paymentDetails.setPurchaseType(purchaseType);

			// calling service layer
			finalAmt = service.calculateNetBillAmount(paymentDetails);
			
			log.info("NetPaymentCalcController.calculateNetPaymentValue finalAmt:" + finalAmt);

			response.setFinalBillAmt(finalAmt);
			response.setErrorCode("000");
			response.setErrorDesc("Success");

		} catch (NumberFormatException | ParseException ne) {
			log.error("NetPaymentCalcController.calculateNetPaymentValue exception block1" + ne.getMessage());
			throw new NetPaymentCalcException("Invalid Bill Amount");
		}  catch (Exception e) {
			log.error("NetPaymentCalcController.calculateNetPaymentValue exception block2" + e.getMessage());
			throw new NetPaymentCalcException(e.getMessage());
		} finally {
			log.info("NetPaymentCalcController.calculateNetPaymentValue ended");
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
