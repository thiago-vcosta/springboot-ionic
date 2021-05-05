package com.workshop.course.dto;

import java.io.Serializable;

public class ClientNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String email;
	private String cpfCnpj;
	private Integer type;
	private String street;
	private String num;
	private String complement;
	private String district;
	private String cep;
	
	private String phone1;
	private String phone2;
	private String phone3;
	
	private Long cityId;
	
	public ClientNewDTO() {		
	}
	
	

	public ClientNewDTO(String name, String email, String cpfCnpj, Integer type, String street, String num,
			String complement, String district, String cep, String phone1, String phone2, String phone3, Long cityId) {
		super();
		this.name = name;
		this.email = email;
		this.cpfCnpj = cpfCnpj;
		this.type = type;
		this.street = street;
		this.num = num;
		this.complement = complement;
		this.district = district;
		this.cep = cep;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.phone3 = phone3;
		this.cityId = cityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
		
}
