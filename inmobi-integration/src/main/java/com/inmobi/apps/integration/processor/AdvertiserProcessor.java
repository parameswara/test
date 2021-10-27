package com.inmobi.apps.integration.processor;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inmobi.apps.integration.service.AdvertiserService;

@Component
public class AdvertiserProcessor implements Processor {
	private Logger logger = LoggerFactory.getLogger(AdvertiserProcessor.class);
	@Autowired
	private AdvertiserService service;

	@Override
	public void process(Exchange exchange) throws Exception {
		String originalFileName = (String) exchange.getIn().getHeader(Exchange.FILE_NAME, String.class);

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String changedFileName = dateFormat.format(date) + originalFileName;
		exchange.getIn().setHeader(Exchange.FILE_NAME, changedFileName);
		logger.info(changedFileName);
	}

}
