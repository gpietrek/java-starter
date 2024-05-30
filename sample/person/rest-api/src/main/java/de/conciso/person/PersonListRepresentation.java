package de.conciso.person;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class PersonListRepresentation {

    List<PersonRepresentation> personList;

    public static PersonListRepresentation from(List<Person> personList) {
        return PersonListRepresentation.builder()
                .personList(personList.stream()
                        .map(PersonRepresentation::from)
                        .toList()
                )
                .build();
    }
}
