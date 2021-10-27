package com.inmobi.apps.integration.router;

import java.time.LocalDateTime;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

//@Component
public class TimerRouter extends RouteBuilder{
	
	@Autowired
	private Environment env;
	
	@Autowired
	GetCurrentTimeBean getCurrentTimeBean;
	
	@Autowired
	SimpleLoggingProcessingComponent loggingComponent;

	@Override
	public void configure() throws Exception {
		restConfiguration().host(env.getProperty("server.host")).port(env.getProperty("server.port"));
		from("timer:first-timer?period={{timer.period}}")
		.log("${body}")
		.transform().constant("My Constant Message")
		.log("${body}")
//		.transform().constant("Time now is:"+ LocalDateTime.now())
//		.bean("getCurrentTimeBean", "getCurrentTime")
		.bean(getCurrentTimeBean)
		.log("${body}")
		.bean(loggingComponent)
		.log("${body}")
		.process(new SimpleLoggingProcessor())
		.to("log:first-timer");
		
	}

}

@Component
class GetCurrentTimeBean{
	public String getCurrentTime() {
		return "Time now is:"+ LocalDateTime.now();
	}
}

@Component
class SimpleLoggingProcessingComponent{
	private Logger logger =LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);
	public void process(String message) {
		logger.info("SimpleLoggingProcessingComponent {}", message);
	}
}

class SimpleLoggingProcessor implements Processor {
	private Logger logger =LoggerFactory.getLogger(SimpleLoggingProcessor.class);
	
	@Override
	public void process(Exchange exchange) throws Exception {
		logger.info("SimpleLoggingProcessor {}", exchange.getMessage().getBody());
	}

}
