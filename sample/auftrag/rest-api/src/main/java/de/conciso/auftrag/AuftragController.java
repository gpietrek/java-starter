package de.conciso.auftrag;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auftrag")
public class AuftragController {

    private final Auftraege auftraege;

    public AuftragController(Auftraege auftraege) {
        this.auftraege = auftraege;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuftragRepresentation> create(@RequestBody AuftragRepresentation auftragRepresentation) {
        try {
            var auftrag = auftraege.create(auftragRepresentation.toAuftrag());
            return ResponseEntity.ok(AuftragRepresentation.from(auftrag));
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuftragRepresentation> findById(@PathVariable("id") int id) {
        try {
            return auftraege.findById(id)
                    .map(AuftragRepresentation::from)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AuftragRepresentation>> findAll() {
        try {
            return ResponseEntity.ok(auftraege.findAll()
                    .stream()
                    .map(AuftragRepresentation::from)
                    .toList()
            );
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
