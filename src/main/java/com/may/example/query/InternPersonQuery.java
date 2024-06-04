package com.may.example.query;

import org.springframework.data.domain.Page;

import com.may.example.model.InternPerson;

public interface InternPersonQuery {

	Page<InternPerson> findInternPersons(Integer page, Integer size, String email, String phoneNo);

}
