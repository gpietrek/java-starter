package de.conciso.person;

import java.util.List;
import java.util.Optional;

public interface PersonDAO {
  Person save(Person person);
  Optional<Person> findById(int id);
  List<Person> findAll();
}
