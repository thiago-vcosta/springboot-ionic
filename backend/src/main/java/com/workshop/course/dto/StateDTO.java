package com.workshop.course.dto;

import java.io.Serializable;

import com.workshop.course.entities.State;

public class StateDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	
	public StateDTO() {		
	}

	public StateDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public StateDTO(State entity) {
		this.id = entity.getId();
		this.name = entity.getName();
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

}
