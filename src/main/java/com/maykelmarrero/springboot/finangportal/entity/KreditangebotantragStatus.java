package com.maykelmarrero.springboot.finangportal.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "kreditangebotantrag_status")
public class KreditangebotantragStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "antragstatus_id")
    private int antragstatusId;
    @Column(name = "antragstatus_name ")
    private String antragstatusName;
    @Column(name = "antragstatus_beschreibung")
    private String antragstatusBeschreibung;

    // refers to "kreditangebotantragStatus" property in "Kreditangebotantrag" class
    @OneToMany(mappedBy = "kreditangebotantragStatus",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<Kreditangebotantrag> kreditangebotantrag;

    public KreditangebotantragStatus() {

    }

    public KreditangebotantragStatus(String antragstatusName, String antragstatusBeschreibung) {
        this.antragstatusName = antragstatusName;
        this.antragstatusBeschreibung = antragstatusBeschreibung;
    }

    public int getAntragstatusId() {
        return antragstatusId;
    }

    public void setAntragstatusId(int antragstatusId) {
        this.antragstatusId = antragstatusId;
    }

    public String getAntragstatusName() {
        return antragstatusName;
    }

    public void setAntragstatusName(String antragstatusName) {
        this.antragstatusName = antragstatusName;
    }

    public String getAntragstatusBeschreibung() {
        return antragstatusBeschreibung;
    }

    public void setAntragstatusBeschreibung(String antragstatusBeschreibung) {
        this.antragstatusBeschreibung = antragstatusBeschreibung;
    }

    public List<Kreditangebotantrag> getKreditangebotantrag() {
        return kreditangebotantrag;
    }

    public void setKreditangebotantrag(List<Kreditangebotantrag> kreditangebotantrag) {
        this.kreditangebotantrag = kreditangebotantrag;
    }

    @Override
    public String toString() {
        return "KreditangebotantragStatus{" +
                "antragstatusId=" + antragstatusId +
                ", antragstatusName='" + antragstatusName + '\'' +
                ", antragstatusBeschreibung='" + antragstatusBeschreibung + '\'' +
                ", kreditangebotantrag=" + kreditangebotantrag +
                '}';
    }

    // add convenience methods for bidirectional relationship
    public void add(Kreditangebotantrag tempKreditangebotantrag) {
        if (kreditangebotantrag == null) {
            kreditangebotantrag = new ArrayList<>();
        }
        kreditangebotantrag.add(tempKreditangebotantrag);
        tempKreditangebotantrag.setKreditangebotantragStatus(this);
    }

}
