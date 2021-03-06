package com.workshop.course.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workshop.course.dto.CategoryDTO;
import com.workshop.course.entities.Category;
import com.workshop.course.repository.CategoryRepository;
import com.workshop.course.services.exeptions.DatabaseException;
import com.workshop.course.services.exeptions.ResourceNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {
		List<Category> list = repository.findAll();	
		return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());				
		}
	
	@Transactional(readOnly = true)
	public Page<CategoryDTO> findAllPaged(PageRequest pageRequest) {
		Page<Category> list = repository.findAll(pageRequest);		
		return list.map(x -> new CategoryDTO(x));				
		}
	
	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity Not Found Id: " + id));
		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		try {
			Category entity = new Category();
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new CategoryDTO(entity);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Erro no cadastro");
		}
	}

	@Transactional
	public CategoryDTO update(Long id, CategoryDTO dto) {
		try {
			Category entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new CategoryDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id n??o encontrado " + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id n??o encontrado " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Viola????o de integridade");
		}
	}

	private void copyDtoToEntity(CategoryDTO dto, Category entity) {
		entity.setName(dto.getName());
	}

}