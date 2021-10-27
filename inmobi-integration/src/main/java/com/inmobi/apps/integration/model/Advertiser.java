package com.inmobi.apps.integration.model;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Advertiser {
	@NotBlank(message = "AccountRegion is mandatory")
	private String accountRegion;
	@NotBlank(message = "AccountSubRegion is mandatory")
	private String accountSubRegion;
	private String iONumber;
	private String agencyDiscount;
	private String volumeDiscount;
	private String oRGName;
	private String sFAdvertiserName;
	private String sFAdvertiserID;
}
