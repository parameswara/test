package com.inmobi.apps.integration.router;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.inmobi.apps.integration.exception.AdvertiserFailedException;
import com.inmobi.apps.integration.processor.MyProcessor;

@Component
public class AdvertiserRouteBuilder extends RouteBuilder {
	
	private Logger logger =LoggerFactory.getLogger(AdvertiserRouteBuilder.class);
	
	@Autowired
	private Environment env;
	
	@Override
	public void configure() throws Exception {
		//quartz should be used
		restConfiguration().host(env.getProperty("server.host")).port(env.getProperty("advertiser.server.port"));
		from("timer:advertiser?period={{timer.period}}")
		.doTry()
		.log("${body}")
		.transform().constant("My Constant Message")
		.log("${body}")
		.process(new MyProcessor())
		.doCatch(AdvertiserFailedException.class).process(new Processor() {
            public void process(Exchange exchange) throws Exception {
            	logger.info("Handling ex");
            }
        })
		.to("log:advertiser");
	}

}
