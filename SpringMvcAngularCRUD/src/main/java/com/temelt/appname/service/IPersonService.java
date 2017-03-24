package com.temelt.appname.service;

import java.util.List;

import com.temelt.appname.dto.PersonDto;

/**
 * 
 * @author taner.temel
 *
 */

public interface IPersonService {
	
    List<PersonDto> getAllPersons();

    void addPerson(PersonDto person);

    void deletePerson(int id);
}
