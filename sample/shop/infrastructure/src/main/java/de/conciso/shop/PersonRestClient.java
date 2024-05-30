package de.conciso.shop;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

@Component
public class PersonRestClient implements Personen {

    private final WebClient personenWebClient;

    private final AuthorizationTokenHolder tokenHolder;

    public PersonRestClient(WebClient personenWebClient, AuthorizationTokenHolder tokenHolder) {
        this.personenWebClient = personenWebClient;
        this.tokenHolder = tokenHolder;
    }

    @Override
    public Person create(Person person) {
        return personenWebClient.post()
                .bodyValue(PersonRestClientRepresentation.from(person))
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(PersonRestClientRepresentation.class)
                        .map(PersonRestClientRepresentation::toPerson)
                )
                .contextWrite(context -> context.put("authorizationToken", tokenHolder.getToken()))
                .block();
    }

    @Override
    public Optional<Person> addAddress(int personId, Address address) {
        return personenWebClient.post()
                .uri(uriBuilder -> uriBuilder.path("/{id}/address").build(personId))
                .bodyValue(AddressRestClientRepresentation.from(address))
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(this::processResponse)
                .contextWrite(context -> context.put("authorizationToken", tokenHolder.getToken()))
                .block();
    }

    @Override
    public Optional<Person> findById(int id) {
        return personenWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/{id}").build(id))
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(this::processResponse)
                .contextWrite(context -> context.put("authorizationToken", tokenHolder.getToken()))
                .block();
    }

    private Mono<Optional<Person>> processResponse(ClientResponse response) {
        if (response.statusCode().equals(OK)) {
            return response.bodyToMono(PersonRestClientRepresentation.class)
                    .map(PersonRestClientRepresentation::toPerson)
                    .map(Optional::of);
        } else {
            return Mono.just(Optional.empty());
        }
    }

    @Override
    public List<Person> findAll() {
        return personenWebClient.get()
                .uri(UriBuilder::build)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(this::processListResponse)
                .contextWrite(context -> context.put("authorizationToken", tokenHolder.getToken()))
                .block();
    }

    private Mono<List<Person>> processListResponse(ClientResponse response) {
        if (response.statusCode().equals(OK)) {
            return response.bodyToMono(PersonListRestClientRepresentation.class)
                    .map(PersonListRestClientRepresentation::toList);
        } else {
            return Mono.just(List.of());
        }
    }
}
