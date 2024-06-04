package com.may.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.may.example.model.InternPerson;
import com.may.example.query.InternPersonQuery;
import com.may.example.repository.InternPersonRepository;

@Service
@Transactional
public class InternPersonServiceImpl implements InternPersonService {

	@Autowired
	private InternPersonRepository internPersonRepository;

	@Autowired
	private InternPersonQuery internPersonQuery;

	@Override
	public void createInternPerson(InternPerson person) {

		this.internPersonRepository.saveAndFlush(person);
	}

	@Override
	public void updateInternPerson(InternPerson person) {

		this.internPersonRepository.saveAndFlush(person);

	}

	@Override
	public InternPerson getById(int id) {

		InternPerson person = this.internPersonRepository.getOne(id);
		return person;
	}

	@Override
	public List<InternPerson> getAll() {

		List<InternPerson> internPersons = this.internPersonRepository.findAll();
		return internPersons;
	}

	@Override
	public List<InternPerson> findInternPersons(Integer page, Integer size, String email, String phoneNo) {
		
		List<InternPerson> internPersons = new ArrayList<InternPerson>();

		internPersons = this.internPersonQuery.findInternPersons(page, size, email, phoneNo).getContent();

		return internPersons;
	}

}
