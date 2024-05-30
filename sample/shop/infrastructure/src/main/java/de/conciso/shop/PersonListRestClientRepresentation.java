package de.conciso.shop;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
@Value
@Builder
public class PersonListRestClientRepresentation {

    List<PersonRestClientRepresentation> persons;

    public List<Person> toList() {
        return persons.stream()
                .map(PersonRestClientRepresentation::toPerson)
                .toList();
    }
}
