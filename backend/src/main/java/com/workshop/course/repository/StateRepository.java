package com.workshop.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workshop.course.entities.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long>{

}
