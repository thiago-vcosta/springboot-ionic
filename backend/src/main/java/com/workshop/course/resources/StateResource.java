package com.workshop.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workshop.course.dto.CityDTO;
import com.workshop.course.dto.StateDTO;
import com.workshop.course.services.CityService;
import com.workshop.course.services.StateService;

@RestController
@RequestMapping(value = "/states")
public class StateResource {

	@Autowired
	private StateService service;
	
	@Autowired
	private CityService cityService;
		
	@GetMapping
	public ResponseEntity<List<StateDTO>> findAllByOrderByName() {		
		List<StateDTO> list = service.findAllByOrderByName();		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{stateId}/cities")
	public ResponseEntity<List<CityDTO>> findCidades(@PathVariable Long stateId) {
		List<CityDTO> list = cityService.findCities(stateId);
		return ResponseEntity.ok().body(list);
	}
	
}
