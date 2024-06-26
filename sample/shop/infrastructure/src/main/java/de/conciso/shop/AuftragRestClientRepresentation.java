package de.conciso.shop;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
@Value
@Builder
public class AuftragRestClientRepresentation {

    int id;
    String bestellNummer;
    LieferadresseRestClientRepresentation lieferadresse;
    List<ArtikelRestClientRepresentation> artikel;

    public Auftrag toAuftrag() {
        return Auftrag.builder()
                .id(id)
                .bestellNummer(bestellNummer)
                .lieferadresse(lieferadresse.toLieferadresse())
                .artikel(ArtikelRestClientRepresentation.toList(artikel))
                .build();
    }

    public static AuftragRestClientRepresentation from(Auftrag auftrag) {
        return AuftragRestClientRepresentation.builder()
                .bestellNummer(auftrag.getBestellNummer())
                .lieferadresse(LieferadresseRestClientRepresentation.from(auftrag.getLieferadresse()))
                .artikel(ArtikelRestClientRepresentation.fromList(auftrag.getArtikel()))
                .build();
    }
}
