package com.inmobi.apps.integration.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@CsvRecord(separator = ",") // from properties file
public class Data{
	@DataField(pos = 1) // from properties file
	private String name;
	@NotNull
	@Size(min = 1, max = 3)
	@DataField(pos = 2) // from properties file
	private Integer id;
	@DataField(pos = 3) // from properties file
	private String description;
	
}
//unit test cases and integration test
//utility project
//jdk version - 1.8
//