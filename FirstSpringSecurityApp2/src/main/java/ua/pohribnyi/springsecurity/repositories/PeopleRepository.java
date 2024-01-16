package ua.pohribnyi.springsecurity.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.pohribnyi.springsecurity.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

	Optional<Person> findByUserName(String userName);

}
