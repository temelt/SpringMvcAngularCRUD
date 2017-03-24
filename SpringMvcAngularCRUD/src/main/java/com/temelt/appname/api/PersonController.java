package com.temelt.appname.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.temelt.appname.dto.PersonDto;
import com.temelt.appname.dto.ResponseMessage;
import com.temelt.appname.service.impl.PersonService;

/**
 * 
 * @author taner.temel
 *
 */
@Controller
public class PersonController {
	
    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    @ResponseBody
    public List<PersonDto> getPersons() {
        return personService.getAll();
    }

    @RequestMapping(value = "/person", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage addPerson(@RequestBody PersonDto person) {
        if (person.getFirstName().length() <= 3 || person.getLastName().length() <= 3) {
            throw new IllegalArgumentException("moreThan3Chars");
        }
        personService.add(person);
        return new ResponseMessage(ResponseMessage.Type.success, "personAdded");
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseMessage deletePerson(@PathVariable int id) {
        personService.delete(id);
        return new ResponseMessage(ResponseMessage.Type.success, "personDeleted");
    }

}
