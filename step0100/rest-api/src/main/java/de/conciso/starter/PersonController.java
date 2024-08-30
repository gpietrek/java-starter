package de.conciso.starter;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/person")
public class PersonController {

  private final Personen personen;

  public PersonController(Personen personen) {
    this.personen = personen;
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Person> create(@RequestParam("vorname") String vorname,
                                       @RequestParam("name") String name) {
    try {
      var person = personen.create(vorname, name);
      return ResponseEntity.ok(person);
    } catch (IllegalArgumentException exception) {
      return ResponseEntity.badRequest().build();
    } catch (Exception exception) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Person> findById(@PathVariable("id") int id) {
    try {
      var found = personen.findById(id);
      return found.map(ResponseEntity::ok)
          .orElseGet(() -> ResponseEntity.notFound().build());
    } catch (Exception exception) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Person> findByRequestParam(@RequestParam("id") int id) {
    return findById(id);
  }
}
