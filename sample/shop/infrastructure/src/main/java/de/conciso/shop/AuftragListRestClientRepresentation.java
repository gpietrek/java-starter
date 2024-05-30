package de.conciso.shop;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
@Value
@Builder
public class AuftragListRestClientRepresentation {

    List<AuftragRestClientRepresentation> auftragList;

    public List<Auftrag> toList() {
        return auftragList.stream()
                .map(AuftragRestClientRepresentation::toAuftrag)
                .toList();
    }
}
