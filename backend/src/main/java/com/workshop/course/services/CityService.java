package com.workshop.course.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workshop.course.dto.CityDTO;
import com.workshop.course.entities.City;
import com.workshop.course.repository.CityRepository;

@Service
public class CityService {

	@Autowired
	CityRepository repository;
	
	@Transactional(readOnly = true)
	public List<CityDTO> findCities(Long stateId) {
		List<City> list = repository.findCities(stateId);
		return list.stream().map(x -> new CityDTO(x)).collect(Collectors.toList());
	}
	
}
