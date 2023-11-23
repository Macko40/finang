package com.maykelmarrero.springboot.finangportal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "kreditangebotantrag")
public class Kreditangebotantrag {


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "antragstatus_id")
    private KreditangebotantragStatus kreditangebotantragStatus;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "antrag_id")
    private int antragId;

    @Column(name = "antragsteller_vname")
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String antragstellerVname = "";

    @Column(name = "antragsteller_name")
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String antragstellerName = "";

    @Column(name = "antragsteller_telefon")
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String antragstellerTelefon = "";

    @Column(name = "antragsteller_gehalt")
    @NotNull(message = "is required")
    private BigDecimal antragstellerGehalt;

    @Column(name = "antragsteller_geburtsdatum")
    @NotNull(message = "is required")
    private LocalDate antragstellerGeburtsdatum;

    @Column(name = "antragsteller_email")
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String antragstellerEmail = "";

    @Column(name = "antrag_erstellungsdatum")
    private LocalDate antragErstellungsdatum;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "kreditangebot_id")
    private Kreditangeboten kreditangeboten;


    public Kreditangebotantrag() {

    }

    public Kreditangebotantrag(String antragstellerVname,
                               String antragstellerName,
                               String antragstellerTelefon,
                               BigDecimal antragstellerGehalt,
                               LocalDate antragstellerGeburtsdatum,
                               String antragstellerEmail,
                               LocalDate antragErstellungsdatum,
                               Kreditangeboten kreditangeboten,
                               KreditangebotantragStatus kreditangebotantragStatus) {
        this.antragstellerVname = antragstellerVname;
        this.antragstellerName = antragstellerName;
        this.antragstellerTelefon = antragstellerTelefon;
        this.antragstellerGehalt = antragstellerGehalt;
        this.antragstellerGeburtsdatum = antragstellerGeburtsdatum;
        this.antragstellerEmail = antragstellerEmail;
        this.antragErstellungsdatum = antragErstellungsdatum;
        this.kreditangeboten = kreditangeboten;
        this.kreditangebotantragStatus = kreditangebotantragStatus;
    }

    public int getAntragId() {
        return antragId;
    }

    public void setAntragId(int antragId) {
        this.antragId = antragId;
    }

    public String getAntragstellerVname() {
        return antragstellerVname;
    }

    public void setAntragstellerVname(String antragstellerVname) {
        this.antragstellerVname = antragstellerVname;
    }

    public String getAntragstellerName() {
        return antragstellerName;
    }

    public void setAntragstellerName(String antragstellerName) {
        this.antragstellerName = antragstellerName;
    }

    public String getAntragstellerTelefon() {
        return antragstellerTelefon;
    }

    public void setAntragstellerTelefon(String antragstellerTelefon) {
        this.antragstellerTelefon = antragstellerTelefon;
    }

    public BigDecimal getAntragstellerGehalt() {
        return antragstellerGehalt;
    }

    public void setAntragstellerGehalt(BigDecimal antragstellerGehalt) {
        this.antragstellerGehalt = antragstellerGehalt;
    }

    public LocalDate getAntragstellerGeburtsdatum() {
        return antragstellerGeburtsdatum;
    }

    public void setAntragstellerGeburtsdatum(LocalDate antragstellerGeburtsdatum) {
        this.antragstellerGeburtsdatum = antragstellerGeburtsdatum;
    }

    public String getAntragstellerEmail() {
        return antragstellerEmail;
    }

    public void setAntragstellerEmail(String antragstellerEmail) {
        this.antragstellerEmail = antragstellerEmail;
    }

    public LocalDate getAntragErstellungsdatum() {
        return antragErstellungsdatum;
    }

    public void setAntragErstellungsdatum(LocalDate antragErstellungsdatum) {
        this.antragErstellungsdatum = antragErstellungsdatum;
    }

    public Kreditangeboten getKreditangeboten() {
        return kreditangeboten;
    }

    public void setKreditangeboten(Kreditangeboten kreditangeboten) {
        this.kreditangeboten = kreditangeboten;
    }

    public KreditangebotantragStatus getKreditangebotantragStatus() {
        return kreditangebotantragStatus;
    }

    public void setKreditangebotantragStatus(KreditangebotantragStatus kreditangebotantragStatus) {
        this.kreditangebotantragStatus = kreditangebotantragStatus;
    }

    @Override
    public String toString() {
        return "Kreditangebotantrag{" +
                "kreditangebotantragStatusId=" + (kreditangebotantragStatus == null ? null : kreditangebotantragStatus.getAntragstatusId()) +
                ", antragId=" + antragId +
                ", antragstellerVname='" + antragstellerVname + '\'' +
                ", antragstellerName='" + antragstellerName + '\'' +
                ", antragstellerTelefon='" + antragstellerTelefon + '\'' +
                ", antragstellerGehalt=" + antragstellerGehalt +
                ", antragstellerGeburtsdatum=" + antragstellerGeburtsdatum +
                ", antragstellerEmail='" + antragstellerEmail + '\'' +
                ", antragErstellungsdatum=" + antragErstellungsdatum +
                ", kreditangeboten=" + kreditangeboten +
                '}';
    }
}
