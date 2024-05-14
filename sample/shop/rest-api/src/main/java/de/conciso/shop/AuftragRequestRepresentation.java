package de.conciso.shop;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class AuftragRequestRepresentation {
  int personId;
  String bestellNummer;
  List<ArtikelRepresentation> artikel;
}
