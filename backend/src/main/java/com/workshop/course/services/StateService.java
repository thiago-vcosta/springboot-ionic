package com.workshop.course.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workshop.course.dto.StateDTO;
import com.workshop.course.entities.State;
import com.workshop.course.repository.StateRepository;

@Service
public class StateService {

	@Autowired
	StateRepository repository;
	
	@Transactional(readOnly = true)
	public List<StateDTO> findAllByOrderByName() {
		List<State> list = repository.findAllByOrderByName();
		return list.stream().map(x -> new StateDTO(x)).collect(Collectors.toList());
	}
	
}
