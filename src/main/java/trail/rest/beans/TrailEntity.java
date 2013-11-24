package trail.rest.beans;

import javax.xml.bind.annotation.XmlRootElement;

public class TrailEntity {

	private Long id;

	private String type;

	private String fullName;

	private String name;

	private String plateNo;

	private String msisdn;

	private String imei;

	private String email;

	public Long getId () {
		return id;
	}

	public void setId (Long id) {
		this.id = id;
	}

	public String getType () {
		return type;
	}

	public void setType (String type) {
		this.type = type;
	}

	public String getFullName () {
		return fullName;
	}

	public void setFullName (String fullName) {
		this.fullName = fullName;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public String getPlateNo () {
		return plateNo;
	}

	public void setPlateNo (String plateNo) {
		this.plateNo = plateNo;
	}

	public String getMsisdn () {
		return msisdn;
	}

	public void setMsisdn (String msisdn) {
		this.msisdn = msisdn;
	}

	public String getImei () {
		return imei;
	}

	public void setImei (String imei) {
		this.imei = imei;
	}

	public String getEmail () {
		return email;
	}

	public void setEmail (String email) {
		this.email = email;
	}

	@Override
	public String toString () {
		return "TrailEntity [id="
			+ id + ", type=" + type + ", fullName=" + fullName + ", name=" + name + ", plateNo="
			+ plateNo + ", msisdn=" + msisdn + ", imei=" + imei + ", email=" + email + "]";
	}
	
	

	
}
