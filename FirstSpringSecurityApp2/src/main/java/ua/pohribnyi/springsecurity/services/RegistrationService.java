package ua.pohribnyi.springsecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ua.pohribnyi.springsecurity.models.Person;
import ua.pohribnyi.springsecurity.repositories.PeopleRepository;

@Service
public class RegistrationService {

	private final PeopleRepository peopleRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public RegistrationService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
		this.peopleRepository = peopleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional
	public void register(Person person) {
		person.setPassword(passwordEncoder.encode(person.getPassword()));
		person.setRole("ROLE_USER");
		peopleRepository.save(person);
	}

}
