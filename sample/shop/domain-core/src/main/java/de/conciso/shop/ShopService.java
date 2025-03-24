package de.conciso.shop;

import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShopService implements Shop {

    private final Personen personen;

    private final Auftraege auftraege;

    public ShopService(Personen personen, Auftraege auftraege) {
        this.personen = personen;
        this.auftraege = auftraege;
    }

    @Override
    public Person createPerson(Person person) {
        log.info("create person: " + person.getVorname() + " " + person.getName());
        return personen.create(person);
    }

    @Override
    public Optional<Person> addAddress(int personId, Address address) {
        log.info("adding address to person with id: " + personId);
        return personen.addAddress(personId, address);
    }

    @Override
    public Optional<Person> findPerson(int id) {
        log.info("looking for person with id: " + id);
        var found = personen.findById(id);
        if (found.isEmpty()) {
            log.warn("no person found with id: " + id);
        }
        return found;
    }

    @Override
    public List<Person> findAllPersons() {
        return personen.findAll();
    }

    @Override
    public Optional<Auftrag> placeOrder(int personId, String bestellNummer, List<Artikel> artikel) {
        log.info("creating auftrag for person with id: " + personId + ", order number: " + bestellNummer);
        return personen.findById(personId)
                .filter(person -> !person.getAdresses().isEmpty())
                .map(person -> {
                    var address = person.getAdresses().getFirst();
                    var auftrag = Auftrag.builder()
                            .bestellNummer(bestellNummer)
                            .lieferadresse(Lieferadresse.builder()
                                    .name(person.getVorname() + " " + person.getName())
                                    .strasse(address.getStrasse())
                                    .plz(address.getPlz())
                                    .ort(address.getOrt())
                                    .build()
                            )
                            .artikel(artikel)
                            .build();
                    return auftraege.create(auftrag);
                });
    }

    @Override
    public Optional<Auftrag> findAuftrag(int id) {
        log.info("looking for auftrag with id: " + id);
        var found = auftraege.findById(id);
        if (found.isEmpty()) {
            log.warn("no auftrag found with id: " + id);
        }
        return found;
    }

    @Override
    public List<Auftrag> findAllOrders() {
        return auftraege.findAll();
    }
}
