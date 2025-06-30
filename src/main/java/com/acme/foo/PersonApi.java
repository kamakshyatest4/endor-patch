package com.acme.foo;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/persons")
public class PersonApi {

	private static Logger log = LoggerFactory.getLogger(PersonApi.class);

	@Autowired
	PersonApi() {
		;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<Person> getPerson(@PathVariable String id) {
		try {
			final Person p = new Person();
			p.setId(1);
			p.setFirstname("John");
			p.setLastname("Doe");

			final Date d = Calendar.getInstance().getTime();
			p.setCreatedAt(d);
			p.setModifiedAt(d);
			return new ResponseEntity<Person>(p, HttpStatus.OK);
		} catch (Exception enfe) {
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "", method = RequestMethod.POST, consumes = {
			"application/json;charset=UTF-8" }, produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<Person> postPerson(@RequestBody Person person) {
		try {
			System.out.println("Upload of person [" + person + "]");
			return new ResponseEntity<Person>(person, HttpStatus.OK);
		} catch (Exception enfe) {
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
	}
}