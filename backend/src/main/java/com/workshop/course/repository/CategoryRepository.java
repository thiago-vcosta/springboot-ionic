package com.workshop.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workshop.course.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
