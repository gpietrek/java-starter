package de.conciso.shop;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
@Value
@Builder
public class ArtikelRestClientRepresentation {

    String nummer;
    String bezeichnung;
    int anzahl;

    private Artikel toArtikel() {
        return Artikel.builder()
                .nummer(nummer)
                .bezeichnung(bezeichnung)
                .anzahl(anzahl)
                .build();
    }

    public static List<Artikel> toList(List<ArtikelRestClientRepresentation> artikel) {
        return artikel.stream()
                .map(ArtikelRestClientRepresentation::toArtikel)
                .toList();
    }

    private static ArtikelRestClientRepresentation from(Artikel artikel) {
        return ArtikelRestClientRepresentation.builder()
                .nummer(artikel.getNummer())
                .bezeichnung(artikel.getBezeichnung())
                .anzahl(artikel.getAnzahl())
                .build();
    }

    public static List<ArtikelRestClientRepresentation> fromList(List<Artikel> artikel) {
        return artikel.stream()
                .map(ArtikelRestClientRepresentation::from)
                .toList();
    }
}
