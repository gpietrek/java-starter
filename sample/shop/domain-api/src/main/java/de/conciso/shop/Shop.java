package de.conciso.shop;

import java.util.List;
import java.util.Optional;

public interface Shop {
  Person createPerson(Person person);

  Optional<Person> addAddress(int personId, Address address);

  Optional<Person> findPerson(int id);

  Optional<Auftrag> placeOrder(int personId, String bestellNummer, List<Artikel> artikel);

  Optional<Auftrag> findAuftrag(int id);
}
