package de.conciso.person;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class PersonListRepresentation {

    List<PersonRepresentation> persons;

    public static PersonListRepresentation from(List<Person> persons) {
        return PersonListRepresentation.builder()
                .persons(persons.stream()
                        .map(PersonRepresentation::from)
                        .toList()
                )
                .build();
    }
}
