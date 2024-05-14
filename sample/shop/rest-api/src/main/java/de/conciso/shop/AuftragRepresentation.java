package de.conciso.shop;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class AuftragRepresentation {

    int id;
    String bestellNummer;
    LieferadresseRepresentation lieferadresse;
    List<ArtikelRepresentation> artikel;

    public static AuftragRepresentation from(Auftrag auftrag) {
        return AuftragRepresentation.builder()
                .id(auftrag.getId())
                .bestellNummer(auftrag.getBestellNummer())
                .lieferadresse(LieferadresseRepresentation.from(auftrag.getLieferadresse()))
                .artikel(ArtikelRepresentation.from(auftrag.getArtikel()))
                .build();
    }
}
