package de.conciso.shop;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class AuftragListRepresentation {

  List<AuftragRepresentation> auftragList;

  public static AuftragListRepresentation from(List<Auftrag> auftragList) {
    return AuftragListRepresentation.builder()
            .auftragList(auftragList.stream()
                    .map(AuftragRepresentation::from)
                    .toList()
            )
            .build();
  }
}
