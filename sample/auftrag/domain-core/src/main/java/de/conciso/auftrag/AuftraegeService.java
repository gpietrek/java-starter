package de.conciso.auftrag;

import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuftraegeService implements Auftraege {

  private final AuftragDAO auftragDAO;

  public AuftraegeService(AuftragDAO auftragDAO) {
    this.auftragDAO = auftragDAO;
  }

  @Override
  public Auftrag create(Auftrag auftrag) {
    log.info("create auftrag: " + auftrag.getBestellNummer());
    var created = auftragDAO.save(auftrag);
    log.info("auftrag id: " + auftrag.getId());
    return created;
  }

  @Override
  public Optional<Auftrag> findById(int id) {
    log.info("looking for auftrag with id: " + id);
    var found = auftragDAO.findById(id);
    if (found.isEmpty()) {
      log.warn("no auftrag found with id: " + id);
    }
    return found;
  }

  @Override
  public List<Auftrag> findAll() {
    log.info("list all orders");
    return auftragDAO.findAll();
  }
}
