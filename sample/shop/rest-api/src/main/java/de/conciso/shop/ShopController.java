package de.conciso.shop;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    private final Shop shop;

    public ShopController(Shop shop) {
        this.shop = shop;
    }

    @PostMapping(
            path = "/person",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PersonRepresentation> create(@RequestBody PersonRepresentation personRepresentation) {
        try {
            var person = shop.createPerson(personRepresentation.toPerson());
            return ResponseEntity.ok(PersonRepresentation.from(person));
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(
            path = "/person/{id}/address",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PersonRepresentation> addAddress(
            @PathVariable("id") int personId,
            @RequestBody AddressRepresentation addressRepresentation
    ) {
        try {
            return shop.addAddress(personId, addressRepresentation.toAddress())
                    .map(PersonRepresentation::from)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/person/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonRepresentation> findPerson(@PathVariable("id") int id) {
        try {
            return shop.findPerson(id)
                    .map(PersonRepresentation::from)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonListRepresentation> findAllPersons() {
        try {
            return ResponseEntity.ok(PersonListRepresentation.from(shop.findAllPersons()));
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(path = "/auftrag", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuftragRepresentation> placeOrder(
            @RequestBody AuftragRequestRepresentation auftragRequest
    ) {
        try {
            return shop.placeOrder(auftragRequest.getPersonId(), auftragRequest.getBestellNummer(), ArtikelRepresentation.toList(auftragRequest.getArtikel()))
                    .map(AuftragRepresentation::from)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(path = "/auftrag/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuftragRepresentation> findOrder(@PathVariable("id") int id) {
        try {
            return shop.findAuftrag(id)
                    .map(AuftragRepresentation::from)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(path = "/auftrag", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuftragListRepresentation> findAllOrders() {
        try {
            return ResponseEntity.ok(AuftragListRepresentation.from(shop.findAllOrders()));
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
