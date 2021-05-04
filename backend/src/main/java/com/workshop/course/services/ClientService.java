package com.workshop.course.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workshop.course.entities.Client;
import com.workshop.course.entities.dto.ClientDTO;
import com.workshop.course.repository.ClientRepository;
import com.workshop.course.services.exeptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity Not Found Id: " + id));
		return new ClientDTO(entity, entity.getAddress(), entity.getPhone());
	}
}