package com.temelt.appname.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.temelt.appname.dto.PersonDto;

/**
 * 
 * @author taner.temel
 *
 */
@Service
public class PersonService {

	private Map<Integer, PersonDto> persons = new HashMap<Integer, PersonDto>();

	public PersonService() {
		persons.put(1, new PersonDto(1, "Ahmet", "ÞEKER"));
		persons.put(2, new PersonDto(2, "Hýdýr", "TEMEL"));
	}

	public List<PersonDto> getAll() {
		return new ArrayList<PersonDto>(persons.values());
	}

	public void add(PersonDto person) {
		int id = 1;
		while (persons.get(id) != null) {
			id++;
		}
		person.setId(id);
		persons.put(id, person);
	}

	public void delete(int id) {
		persons.remove(id);
	}
}