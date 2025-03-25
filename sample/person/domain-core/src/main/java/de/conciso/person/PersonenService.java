package de.conciso.person;

import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class PersonenService implements Personen {

  private final PersonDAO personDAO;

  public PersonenService(PersonDAO personDAO) {
    this.personDAO = personDAO;
  }

  @Override
  public Person create(Person person) {
    log.info("create person: " + person.getVorname() + " " + person.getName());
    return personDAO.save(person);
  }

  @Override
  public Optional<Person> findById(int id) {
    log.info("looking for person with id: " + id);
    var found = personDAO.findById(id);
    if (found.isEmpty()) {
      log.warn("no person found with id: " + id);
    }
    return found;
  }

  @Override
  @Transactional
  public Optional<Person> addAddress(int personId, Address address) {
    log.info("adding address to person with id: " +personId);
    return findById(personId)
        .map(person -> {
          person.addAddress(address);
          return person;
        });
  }

  @Override
  public List<Person> findAll() {
    log.info("list all persons");
    return personDAO.findAll();
  }
}
