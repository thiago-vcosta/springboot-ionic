package com.workshop.course.entities.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.workshop.course.entities.Category;
import com.workshop.course.entities.Product;

public class CategoryDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;

	Set<ProductDTO> products = new HashSet<>();

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

	public CategoryDTO(Category entity, Set<Product> products) {
		this(entity);
		entity.getProducts().forEach(p -> this.products.add(new ProductDTO(p)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return name;
	}

	public void setNome(String nome) {
		this.name = nome;
	}

	public Set<ProductDTO> getProducts() {
		return products;
	}

}
