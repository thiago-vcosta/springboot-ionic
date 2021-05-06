package com.workshop.course.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.workshop.course.entities.Address;
import com.workshop.course.entities.Client;
import com.workshop.course.services.validation.ClientUpdate;

@ClientUpdate
public class ClientDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@Size(min = 5, max = 60, message = "Deve ter entre 5 e 60 caracteres")
	@NotEmpty(message = "Campo obrigatório")
	private String name;
	
	@NotEmpty(message = "Campo obrigatório")
	@Email(message = "Email inválido")
	private String email;

	Set<AddressDTO> address = new HashSet<>();

	Set<String> phone = new HashSet<>();
	
	public ClientDTO() {		
	}

	public ClientDTO(Long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	public ClientDTO(Client entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.email = entity.getEmail();
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

	public Set<AddressDTO> getAddress() {
		return address;
	}

	public Set<String> getPhone() {
		return phone;
	}
	
	
}
