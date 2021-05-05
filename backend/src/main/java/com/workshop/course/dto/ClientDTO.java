package com.workshop.course.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.workshop.course.entities.Address;
import com.workshop.course.entities.Client;

public class ClientDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@Size(min = 5, max = 60, message = "Deve ter entre 5 e 60 caracteres")
	@NotBlank(message = "Campo obrigatório")
	private String name;
	
	@NotBlank(message = "Campo obrigatório")
	@Email(message = "Email inválido")
	private String email;
	private String cpfCnpj;

	Set<AddressDTO> address = new HashSet<>();

	Set<String> phone = new HashSet<>();
	
	public ClientDTO() {		
	}

	public ClientDTO(Long id, String name, String email, String cpfCnpj) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpfCnpj = cpfCnpj;
	}
	
	public ClientDTO(Client entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.email = entity.getEmail();
		this.cpfCnpj = entity.getCpfCnpj();
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

	public Set<AddressDTO> getAddress() {
		return address;
	}

	public Set<String> getPhone() {
		return phone;
	}
	
	
}
