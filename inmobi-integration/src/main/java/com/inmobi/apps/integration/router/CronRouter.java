package com.inmobi.apps.integration.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inmobi.apps.integration.InMobiDateUtility;

@Component
public class CronRouter extends RouteBuilder {
	@Autowired
	InMobiDateUtility inMobiDateUtility;

	@Override
	public void configure() throws Exception {
		from("cron:tab?schedule={{cron.exp}}").setBody().constant("event").log("${body}");
		inMobiDateUtility.displayCurrentDate();
	}
}
