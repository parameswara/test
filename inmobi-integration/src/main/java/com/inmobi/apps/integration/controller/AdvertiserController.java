package com.inmobi.apps.integration.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdvertiserController {

	@GetMapping("/welcome")
	public String welcomePage() {
		return "Welcome to InMobi";
	}
}