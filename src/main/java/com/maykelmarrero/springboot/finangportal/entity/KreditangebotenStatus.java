package com.maykelmarrero.springboot.finangportal.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "kreditangeboten_status")
public class KreditangebotenStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private int statusId;
    @Column(name = "status_name ")
    private String statusName;
    @Column(name = "status_beschreibung")
    private String statusBeschreibung;

    // refers to "kreditangebotStatus" property in "Kreditangeboten" class
    @OneToMany(mappedBy = "kreditangebotenStatus",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<Kreditangeboten> kreditangeboten;


    public KreditangebotenStatus() {

    }

    public KreditangebotenStatus(String statusName, String statusBeschreibung) {
        this.statusName = statusName;
        this.statusBeschreibung = statusBeschreibung;
    }


    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusBeschreibung() {
        return statusBeschreibung;
    }

    public void setStatusBeschreibung(String statusBeschreibung) {
        this.statusBeschreibung = statusBeschreibung;
    }

    public List<Kreditangeboten> getKreditangeboten() {
        return kreditangeboten;
    }

    public void setKreditangeboten(List<Kreditangeboten> kreditangeboten) {
        this.kreditangeboten = kreditangeboten;
    }

    @Override
    public String toString() {
        return "KreditangebotenStatus{" +
                "statusId=" + statusId +
                ", statusName='" + statusName + '\'' +
                ", statusBeschreibung='" + statusBeschreibung + '\'' +
                '}';
    }

    // add convenience methods for bi-directional relationship
    public void add(Kreditangeboten tempKreditangeboten) {
        if (kreditangeboten == null) {
            kreditangeboten = new ArrayList<>();
        }
        kreditangeboten.add(tempKreditangeboten);
        tempKreditangeboten.setKreditangebotenStatus(this);
    }
}
