package de.conciso.shop;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Artikel {
  String nummer;
  String bezeichnung;
  int anzahl;
}
