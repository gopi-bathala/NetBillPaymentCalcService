package com.gopi;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gopi.response.NetPaymentCalcResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NetPayCalcServiceApplication.class)
@WebAppConfiguration
public class NetPayCalcServiceApplicationTests {

	protected MockMvc mvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	protected <T> T jsontoObject(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	@Test
	public void positiveCase1() throws UnsupportedEncodingException {

		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		NetPaymentCalcResponse res = null;

		String uri = "/calculateNetBillAmt/1000/LoyalCustomer/Groceries";
		MvcResult mvcResult = null;
		try {
			mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();

			int status = mvcResult.getResponse().getStatus();
			String content = mvcResult.getResponse().getContentAsString();

			res = jsontoObject(content, NetPaymentCalcResponse.class);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(Double.valueOf(1000.0), res.getFinalBillAmt());
	}
	
	
	@Test
	public void positiveCase2() throws UnsupportedEncodingException {

		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		NetPaymentCalcResponse res = null;

		String uri = "/calculateNetBillAmt/1000/Employee/Appliances";
		MvcResult mvcResult = null;
		try {
			mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();

			int status = mvcResult.getResponse().getStatus();
			String content = mvcResult.getResponse().getContentAsString();

			res = jsontoObject(content, NetPaymentCalcResponse.class);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(Double.valueOf(700.0), res.getFinalBillAmt());
	}

	@Test
	public void positiveCase3() throws UnsupportedEncodingException {

		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		NetPaymentCalcResponse res = null;

		String uri = "/calculateNetBillAmt/1000/Affiliate/Appliances";
		MvcResult mvcResult = null;
		try {
			mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();

			int status = mvcResult.getResponse().getStatus();
			String content = mvcResult.getResponse().getContentAsString();

			res = jsontoObject(content, NetPaymentCalcResponse.class);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(Double.valueOf(900.0), res.getFinalBillAmt());
	}

	@Test
	public void positiveCase4() throws UnsupportedEncodingException {

		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		NetPaymentCalcResponse res = null;

		String uri = "/calculateNetBillAmt/1000/NewCustomer/Appliances";
		MvcResult mvcResult = null;
		try {
			mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();

			int status = mvcResult.getResponse().getStatus();
			String content = mvcResult.getResponse().getContentAsString();

			res = jsontoObject(content, NetPaymentCalcResponse.class);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(Double.valueOf(950.0), res.getFinalBillAmt());
	}

	@Test
	public void positiveCase5() throws UnsupportedEncodingException {

		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		NetPaymentCalcResponse res = null;

		String uri = "/calculateNetBillAmt/1000/LoyalCustomer/HomeAppliances";
		MvcResult mvcResult = null;
		try {
			mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();

			int status = mvcResult.getResponse().getStatus();
			String content = mvcResult.getResponse().getContentAsString();

			res = jsontoObject(content, NetPaymentCalcResponse.class);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(Double.valueOf(950.0), res.getFinalBillAmt());
	}
	

	

	@Test
	public void negativeCase1() {

		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		NetPaymentCalcResponse res = null;

		String uri = "/calculateNetBillAmt/1000a/LoyalCustomer/Groceries";
		MvcResult mvcResult = null;
		try {
			mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();

			String content = mvcResult.getResponse().getContentAsString();

			res = jsontoObject(content, NetPaymentCalcResponse.class);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(Double.valueOf(0.0), res.getFinalBillAmt());
	}

	@Test
	public void negativeCase2() {

		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		NetPaymentCalcResponse res = null;

		String uri = "/abcd/1000a/LoyalCustomer/Groceries";
		MvcResult mvcResult = null;
		try {
			mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int status = mvcResult.getResponse().getStatus();

		assertEquals(404, status);
	}

	
	
}
