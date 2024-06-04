package com.may.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.may.example.model.InternPerson;

@Repository
public interface InternPersonRepository extends JpaRepository<InternPerson, Integer>, QuerydslPredicateExecutor<InternPerson>{

}
