package de.conciso.starter;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonJmsListener {

    private final Personen personen;

    @JmsListener(destination = "someQueue")
    public void deleteUser(PersonRepresentation person) {
        personen.deleteById(person.getId());
    }
}
