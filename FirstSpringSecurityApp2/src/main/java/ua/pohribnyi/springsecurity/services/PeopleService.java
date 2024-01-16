package ua.pohribnyi.springsecurity.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.pohribnyi.springsecurity.models.Person;
import ua.pohribnyi.springsecurity.repositories.PeopleRepository;

@Service
@Transactional(readOnly = true)
public class PeopleService {

	private final PeopleRepository peopleRepository;

	@Autowired
	public PeopleService(PeopleRepository peopleRepository) {
		this.peopleRepository = peopleRepository;
	}

	public Optional<Person> findPersonByUsername(String username) {
		return peopleRepository.findByUserName(username);
	}

}
