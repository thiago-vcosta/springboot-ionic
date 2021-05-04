package com.workshop.course.dto;

import java.io.Serializable;

import com.workshop.course.entities.Address;
import com.workshop.course.entities.City;

public class AddressDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String street;
	private String num;
	private String complement;
	private String district;
	private String cep;

	private City city;

	public AddressDTO() {
	}

	public AddressDTO(Long id, String street, String num, String complement, String district, String cep, City city) {
		this.id = id;
		this.street = street;
		this.num = num;
		this.complement = complement;
		this.district = district;
		this.cep = cep;
		this.city = city;
	}

	public AddressDTO(Address entity) {
		this.id = entity.getId();
		this.street = entity.getStreet();
		this.num = entity.getNum();
		this.complement = entity.getComplement();
		this.district = entity.getDitrict();
		this.cep = entity.getCep();
		this.city = entity.getCity();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}