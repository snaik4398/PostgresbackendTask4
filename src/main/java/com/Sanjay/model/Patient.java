package com.Sanjay.model;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import lombok.ToString;
@Entity

@Table(name = "patient", schema = "public")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@ToString
public class Patient {
	@Id
	@Column(name = "patient_id")
	private String patid = "KH"
			+ Long.toString((long) ((Math.random() * (99999999999999L - 10000000000000L + 1) + 10000000000000L)));
	@Column(name = "name")
	private String name;
	@Column(name = "dob")
	private String dob;
	@Column(name = "contact_no")
	private String mobileno;
	@Column(name = "email_id")
	private String emailId;
	@Column(name = "gender")
	private Gender gender;

	@Type(type = "jsonb")
	@Column(name = "address", columnDefinition = "jsonb")
	private Address address;
	
	@Type(type = "jsonb")
	@Column(name = "extension", columnDefinition = "jsonb")
	private Map<String, String> extensionmap;
	
	@Column(name = "status")
	private boolean status = true;

	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Patient(String patid, String name, String dob, String mobileno, String emailId, Gender gender,
			Address address, Map<String, String> extenstionmap, boolean status) {
		super();
		this.patid = patid;
		this.name = name;
		this.dob = dob;
		this.mobileno = mobileno;
		this.emailId = emailId;
		this.gender = gender;
		this.address = address;
		this.extensionmap = extenstionmap;
		this.status = status;
	}

	public String getPatid() {
		return patid;
	}

	public void setPatid(String patid) {
		this.patid = patid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Address getAddress() {
		return address;
//		return "{street=" + address.getStreet() + ", zipcode=" + address.getZipcode() + ", state=" + address.getState() + ", country=" + address.getCountry() + "}";
	}

	public String jonbAddress() {
		return "{\"street\":\"" + address.getStreet() + "\", \"zipcode\":\"" + address.getZipcode() + "\", \"state\":\""
				+ address.getState() + "\", \"country\":\"" + address.getCountry() + "\"}";
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Map<String, String> getExtentionmap() {
		return extensionmap;
	}

	public void setExtensionmap(Map<String, String> extenstionmap) {
		this.extensionmap = extenstionmap;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean flag) {
		this.status = flag;
	}

	@Override
	public String toString() {
		return "Pat [patid=" + patid + ", name=" + name + ", dob=" + dob + ", mobileno=" + mobileno + ", emailId="
				+ emailId + ", gender=" + gender + ", address=" + address + ", extenstionmap=" + extensionmap
				+ ", status=" + status + "]";
	}

}
