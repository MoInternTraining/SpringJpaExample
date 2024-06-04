package com.may.example.service;

import java.util.List;

import com.may.example.model.InternPerson;

public interface InternPersonService {

	public void createInternPerson(InternPerson person);

	public void updateInternPerson(InternPerson person);

	public InternPerson getById(int id);

	public List<InternPerson> getAll();

	public List<InternPerson> findInternPersons(Integer page, Integer size,String email, String phoneNo);

}
