package com.gopi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gopi.constants.NetPayCalcConstants;
import com.gopi.response.NetPaymentCalcResponse;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class NetPaymentCalcConfig {

	
	@Bean
	public NetPayCalcConstants calcConstants() {
		return new NetPayCalcConstants();	
	}
	
	@Bean
	public NetPaymentCalcResponse calcResponse() {
		return new NetPaymentCalcResponse();
	}
	
	@Bean
    public Docket api() { 
		 return new Docket(DocumentationType.SWAGGER_2)
		            .groupName("calc-api")
		            .useDefaultResponseMessages(false)
		            .apiInfo(apiInfo())
		            .select()
		                .paths(PathSelectors.regex("/calc.*"))
		            .build();                                         
    }
	
	 private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("Simple API")
	                .description("API for calculating net bill amount of a retail store")
	                .version("1.0")
	                .build();
	    }

}
