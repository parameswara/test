package com.inmobi.apps.integration.service;

import java.util.Map;

import org.apache.camel.Body;
import org.apache.camel.Headers;
import org.springframework.stereotype.Service;

import com.inmobi.apps.integration.exception.AdvertiserFailedException;

@Service
public class AdvertiserService {
	/**
     * This method handle our advertiser input and return the advertiser
     */
    public Object handleOrder(@Headers Map<String, String> headers, @Body String payload) throws AdvertiserFailedException {
        headers.put("sFAdvertiserID", headers.get("sFAdvertiserID"));
        if ("Order: kaboom".equals(payload)) {
            throw new AdvertiserFailedException("Cannot order: kaboom");
        } else {
            headers.put("iONumber", "123");
            return "Advertiser OK";
        }
    }

    /**
     * This method creates the response to the caller if the order could not
     * be processed
     */
    public Object orderFailed(@Headers Map<String, String> headers, @Body String payload) {
        headers.put("sFAdvertiserID", headers.get("sFAdvertiserID"));
        headers.put("iONumber", "failed");
        return "Advertiser ERROR";
    }
}
