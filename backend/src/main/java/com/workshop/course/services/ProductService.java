package com.workshop.course.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workshop.course.dto.ProductDTO;
import com.workshop.course.entities.Category;
import com.workshop.course.entities.Product;
import com.workshop.course.repository.CategoryRepository;
import com.workshop.course.repository.ProductRepository;
import com.workshop.course.services.exeptions.DatabaseException;
import com.workshop.course.services.exeptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;

//	@Transactional(readOnly = true)
//	public List<ProductDTO> findAll() {
//		List<Product> list = repository.findAll();	
//		return list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());				
//		}
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> search(String name, List<Long> ids, PageRequest pageRequest) {
		List<Category> category = categoryRepository.findAllById(ids);
		Page<Product> list = repository.findDistinctByNameContainingAndCategoriesIn(name, category, pageRequest);		
		return list.map(x -> new ProductDTO(x));				
		}
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity Not Found Id: " + id));
		return new ProductDTO(entity);
	}

	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		try {
			Product entity = new Product();
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new ProductDTO(entity);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Erro no cadastro");
		}
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new ProductDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não encontrado " + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id não encontrado " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade");
		}
	}

	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		entity.setName(dto.getName());
	}

}