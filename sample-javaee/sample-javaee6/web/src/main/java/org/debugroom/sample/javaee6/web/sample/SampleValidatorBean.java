package org.debugroom.sample.javaee6.web.sample;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.enterprise.inject.Model;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Model
public class SampleValidatorBean {

	@SampleAnnotation(from = 2010, to = 2050)
	private String code = "a2011000";
	private Boolean booleanValidatorTest;
	private Byte byteValidatorTest;
	private Character charactorValidatorTest;
	private Short shortValidatorTest;
	private Integer integerValidatorTest = 5;
	@NotNull
	@Min(0)
	@Max(100)
	private Long longValidatorTest = Long.parseLong("10");
	private Float floatValidatorTest;
	private Double doubleValidatorTest;
	private BigDecimal bigDecimalValidatorTest;
	private BigInteger bigIntegerValidatorTest;
	private Enum<SampleEnum> enumValidatorTest;
	private Date dateValidatorTest = new Date();
	private Number numberValidatorTest;

	public String next(){
		return "/jsf/sample/validatorTestOutput";
	}

}
