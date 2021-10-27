package com.inmobi.apps.integration;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class InMobiDateUtilityImpl implements InMobiDateUtility {
	private Logger logger = LoggerFactory.getLogger(InMobiDateUtilityImpl.class);

	@Override
	public void displayCurrentDate() {
		logger.info("", new Date());
	}

}
