package com.workshop.course.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.workshop.course.entities.Category;

public class CategoryDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@Size(min = 5, max = 60, message = "Deve ter entre 5 e 60 caracteres")
	@NotBlank(message = "Campo obrigatório")
	private String name;

	public CategoryDTO() {
	}

	public CategoryDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public CategoryDTO(Category entity) {
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
