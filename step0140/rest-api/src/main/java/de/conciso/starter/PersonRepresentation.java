package de.conciso.starter;

import lombok.Value;

@Value
public class PersonRepresentation {

    private int id;

    private String vorname;

    private String name;

    static PersonRepresentation from(Person person) {
        return new PersonRepresentation(person.getId(), person.getVorname(), person.getName());
    }
}
