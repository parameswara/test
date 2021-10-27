package com.inmobi.apps.integration.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.inmobi.apps.integration.exception.AdvertiserFailedException;

public class MyProcessor implements Processor {

    public void process(Exchange exchange) throws Exception {
        throw new AdvertiserFailedException("Exception Thrown");
    }

}
