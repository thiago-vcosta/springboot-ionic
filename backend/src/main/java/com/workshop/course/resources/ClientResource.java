package com.workshop.course.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.workshop.course.dto.ClientDTO;
import com.workshop.course.dto.ClientNewDTO;
import com.workshop.course.entities.Client;
import com.workshop.course.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

	@Autowired
	private ClientService service;
	
	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll() {		
		List<ClientDTO> list = service.findAll();		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/paged")
	public ResponseEntity<Page<ClientDTO>> findAllPaged(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,		
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy
			) {		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);		
		Page<ClientDTO> list = service.findAllPaged(pageRequest);		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
		ClientDTO obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Client> insert(@Valid @RequestBody ClientNewDTO dto) {
		Client obj = service.copyDtoToEntity(dto);
		obj = service.insert(obj);
		return ResponseEntity.created(null).body(obj);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Client> update(@Valid @PathVariable Long id, @RequestBody ClientDTO dto) {
		Client obj = service.copyDtoToEntity(dto);
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
