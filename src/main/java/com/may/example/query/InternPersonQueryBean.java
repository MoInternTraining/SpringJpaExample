package com.may.example.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.may.example.model.InternPerson;
import com.may.example.repository.InternPersonRepository;
import com.may.example.repository.filters.InternPersonFilter;
import com.querydsl.core.types.dsl.BooleanExpression;

@Component
public class InternPersonQueryBean implements InternPersonQuery {

	@Autowired
	private InternPersonRepository internPersonRepository;

	@Override
	public Page<InternPerson> findInternPersons(Integer page, Integer size, String email, String phoneNo) {

		BooleanExpression where = null;
		if (email != null) {

			where = InternPersonFilter.withEmail(email);
		}

		if (phoneNo != null) {
			if (where != null) {

				where = where.and(InternPersonFilter.withPhoneNo(phoneNo));
			} else {

				where = InternPersonFilter.withPhoneNo(phoneNo);
			}

		}

		Page<InternPerson> internPersons = null;
		if (where != null) {

			if (page != null && size != null) {

				internPersons = (Page<InternPerson>) this.internPersonRepository.findAll(where,
						PageRequest.of(page, size, Sort.by("phoneNo").ascending()));
			} else {

				internPersons = this.internPersonRepository.findAll(where, Pageable.unpaged());
			}

		} else {

			if (page != null && size != null) {

				internPersons = this.internPersonRepository
						.findAll(PageRequest.of(page, size, Sort.by("phoneNo").ascending()));

			} else {

				internPersons = this.internPersonRepository.findAll(Pageable.unpaged());

			}
		}

		return internPersons;
	}

}
