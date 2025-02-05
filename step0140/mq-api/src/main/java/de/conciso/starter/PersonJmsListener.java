package de.conciso.starter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonJmsListener {

    private final Personen personen;
    private final ObjectMapper objectMapper;

    @JmsListener(destination = "someQueue")
    public void deleteUser(String message) throws JsonProcessingException {
        int id = objectMapper.readTree(message).get("id").asInt();
        personen.deleteById(id);
    }
}
