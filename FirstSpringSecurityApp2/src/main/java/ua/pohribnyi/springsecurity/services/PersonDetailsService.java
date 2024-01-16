package ua.pohribnyi.springsecurity.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.pohribnyi.springsecurity.models.Person;
import ua.pohribnyi.springsecurity.repositories.PeopleRepository;
import ua.pohribnyi.springsecurity.security.PersonDetails;

@Service
public class PersonDetailsService implements UserDetailsService {

	private final PeopleRepository peopleRepository;

	@Autowired
	public PersonDetailsService(PeopleRepository peopleRepository) {
		this.peopleRepository = peopleRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Person> person = peopleRepository.findByUserName(username);
		if (person.isEmpty()) {
			throw new UsernameNotFoundException("User not found!");
		}
		return new PersonDetails(person.get());
	}

}
