package com.workshop.course.entities.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.workshop.course.entities.Address;
import com.workshop.course.entities.Client;
import com.workshop.course.entities.enums.ClientType;

public class ClientDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String email;
	private String cpfCnpj;
	private Integer type;

	Set<AddressDTO> address = new HashSet<>();

	Set<String> phone = new HashSet<>();
	
	public ClientDTO() {		
	}

	public ClientDTO(Long id, String name, String email, String cpfCnpj, ClientType type) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpfCnpj = cpfCnpj;
		this.type = type.getCode();
	}
	
	public ClientDTO(Client entity) {
		super();
		this.id = entity.getId();
		this.name = entity.getName();
		this.email = entity.getEmail();
		this.cpfCnpj = entity.getCpfCnpj();
		this.type = entity.getType().getCode();
	}
	
	public ClientDTO(Client entity, Set<Address> address, Set<String> phone) {
		this(entity);
		entity.getAddress().forEach(c -> this.address.add(new AddressDTO(c)));
		entity.getPhone().forEach(p -> this.phone.add(new String(p)));
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Set<AddressDTO> getAddress() {
		return address;
	}

	public Set<String> getPhone() {
		return phone;
	}
	
	
}
