package com.Sanjay.model;



public class Address {
	private String street;
	private String zipcode;
	private String state;
	private String country;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Address(String street, String zipcode, String state, String country) {
		super();
		this.street = street;
		this.zipcode = zipcode;
		this.state = state;
		this.country = country;
	}

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", zipcode=" + zipcode + ", state=" + state + ", country=" + country + "]";
	}

}
