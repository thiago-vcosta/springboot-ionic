package com.workshop.course.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.workshop.course.entities.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM tb_city obj WHERE obj.state.id = :stateId ORDER BY obj.name")
	public List<City> findCities(Long stateId);
}
