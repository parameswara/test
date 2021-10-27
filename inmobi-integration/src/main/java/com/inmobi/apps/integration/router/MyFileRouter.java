package com.inmobi.apps.integration.router;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spi.DataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.inmobi.apps.integration.model.Data;
import com.inmobi.apps.integration.processor.AdvertiserProcessor;

@Component
public class MyFileRouter extends RouteBuilder{

	@Override
	public void configure() throws Exception {

		// .cvs to .json
		DataFormat bindy = new BindyCsvDataFormat(Data.class);
		from("file:src/test/resources/?fileName=data1.csv&noop=true")// from properties file
	    .unmarshal(bindy)
	    .marshal()
	    .json(JsonLibrary.Jackson, true).log("${body}")
	    .process(new AdvertiserProcessor())
	    .to("file:src/test/resources/?fileName=data1.json");// from properties file
		
	}

}

class CvsUnmarshalProcessor implements Processor {
	private Logger logger =LoggerFactory.getLogger(CvsUnmarshalProcessor.class);
	
	@Override
	public void process(Exchange exchange) throws Exception {
		logger.info(exchange.getIn().getBody().toString());
		
	}
}


