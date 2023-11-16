package com.maykelmarrero.springboot.finangportal.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "kreditangeboten")
public class Kreditangeboten {
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "status_id")
    private KreditangebotenStatus kreditangebotenStatus;

    // refers to "kreditangeboten" property in "Kreditangebotantrag" class
    @OneToMany(mappedBy = "kreditangeboten",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<Kreditangebotantrag> kreditangebotantrag;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kreditangebot_id")
    private int kreditangebotId;
    @Column(name = "kreditangebot_name")
    private String kreditangebotName;
    @Column(name = "kreditangebot_beschreibung")
    private String kreditangebotBeschreibung;
    @Column(name = "kredit_punkte")
    private int kreditPunkte;
    @Column(name = "kreditangebot_jahresgebuehr")
    private BigDecimal kreditangebotJahresgebuehr;
    @Column(name = "kreditangebot_erstellungsdatum")
    private LocalDate kreditangebotErstellungsdatum;
    @Column(name = "kreditangebot_ablaufdatum")
    private LocalDate kreditangebotAblaufdatum;

    public Kreditangeboten() {

    }


    public Kreditangeboten(String kreditangebotName,
                           String kreditangebotBeschreibung,
                           int kreditPunkte,
                           BigDecimal kreditangebotJahresgebuehr,
                           LocalDate kreditangebotErstellungsdatum,
                           LocalDate kreditangebotAblaufdatum,
                           KreditangebotenStatus kreditangebotenStatus) {
        this.kreditangebotName = kreditangebotName;
        this.kreditangebotBeschreibung = kreditangebotBeschreibung;
        this.kreditPunkte = kreditPunkte;
        this.kreditangebotJahresgebuehr = kreditangebotJahresgebuehr;
        this.kreditangebotErstellungsdatum = kreditangebotErstellungsdatum;
        this.kreditangebotAblaufdatum = kreditangebotAblaufdatum;
        this.kreditangebotenStatus = kreditangebotenStatus;
    }

    public int getKreditangebotId() {
        return kreditangebotId;
    }

    public void setKreditangebotId(int kreditangebotId) {
        this.kreditangebotId = kreditangebotId;
    }

    public String getKreditangebotName() {
        return kreditangebotName;
    }

    public void setKreditangebotName(String kreditangebotName) {
        this.kreditangebotName = kreditangebotName;
    }

    public String getKreditangebotBeschreibung() {
        return kreditangebotBeschreibung;
    }

    public void setKreditangebotBeschreibung(String kreditangebotBeschreibung) {
        this.kreditangebotBeschreibung = kreditangebotBeschreibung;
    }

    public int getKreditPunkte() {
        return kreditPunkte;
    }

    public void setKreditPunkte(int kreditPunkte) {
        this.kreditPunkte = kreditPunkte;
    }

    public BigDecimal getKreditangebotJahresgebuehr() {
        return kreditangebotJahresgebuehr;
    }

    public void setKreditangebotJahresgebuehr(BigDecimal kreditangebotJahresgebuehr) {
        this.kreditangebotJahresgebuehr = kreditangebotJahresgebuehr;
    }

    public LocalDate getKreditangebotErstellungsdatum() {
        return kreditangebotErstellungsdatum;
    }

    public void setKreditangebotErstellungsdatum(LocalDate kreditangebotErstellungsdatum) {
        this.kreditangebotErstellungsdatum = kreditangebotErstellungsdatum;
    }

    public LocalDate getKreditangebotAblaufdatum() {
        return kreditangebotAblaufdatum;
    }

    public void setKreditangebotAblaufdatum(LocalDate kreditangebotAblaufdatum) {
        this.kreditangebotAblaufdatum = kreditangebotAblaufdatum;
    }

    public KreditangebotenStatus getKreditangebotenStatus() {
        return kreditangebotenStatus;
    }

    public void setKreditangebotenStatus(KreditangebotenStatus kreditangebotenStatus) {
        this.kreditangebotenStatus = kreditangebotenStatus;
    }

    @Override
    public String toString() {
        return "Kreditangeboten{" +
                "kreditangebotId=" + kreditangebotId +
                ", kreditangebotName='" + kreditangebotName + '\'' +
                ", kreditangebotBeschreibung='" + kreditangebotBeschreibung + '\'' +
                ", kreditPunkte=" + kreditPunkte +
                ", kreditangebotJahresgebuehr=" + kreditangebotJahresgebuehr +
                ", kreditangebotErstellungsdatum=" + kreditangebotErstellungsdatum +
                ", kreditangebotAblaufdatum=" + kreditangebotAblaufdatum +
                '}';
    }
}
