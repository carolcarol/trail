package trail.rest.beans;

import trail.rest.validation.IMEI;
import trail.rest.validation.Msisdn;

public class Device {
	
	Long id;
	
	@Msisdn
	String msisdn;
	
	@IMEI
	String imei;

	public Long getId () {
		return id;
	}
	
	public void setId (Long id) {
		this.id = id;
	}
	
	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

}
