package org.debugroom.sample.javaee6.web.sample;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.enterprise.inject.Model;

import lombok.Data;

@Data
@Model
public class SampleConverterBean {

	private Boolean booleanConverterTest;
	private Byte byteConverterTest;
	private Character charactorConverterTest;
	private Short shortConverterTest;
	private Integer integerConverterTest;
	private Long longConverterTest;
	private Float floatConverterTest;
	private Double doubleConverterTest;
	private BigDecimal bigDecimalConverterTest;
	private BigInteger bigIntegerConverterTest;
	private Enum<SampleEnum> enumConverterTest;
	private Date dateConverterTest;
	private Number numberConverterTest;

	public String next(){
		return "/jsf/sample/converterTestOutput";
	}

}
