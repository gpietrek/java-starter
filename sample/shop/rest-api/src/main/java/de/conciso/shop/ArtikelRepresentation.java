package de.conciso.shop;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ArtikelRepresentation {

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

    public static List<Artikel> toList(List<ArtikelRepresentation> artikel) {
        return artikel.stream()
                .map(ArtikelRepresentation::toArtikel)
                .toList();
    }

    private static ArtikelRepresentation from(Artikel artikel) {
        return ArtikelRepresentation.builder()
                .nummer(artikel.getNummer())
                .bezeichnung(artikel.getBezeichnung())
                .anzahl(artikel.getAnzahl())
                .build();
    }

    public static List<ArtikelRepresentation> from(List<Artikel> artikel) {
        return artikel.stream()
                .map(ArtikelRepresentation::from)
                .toList();
    }

}
