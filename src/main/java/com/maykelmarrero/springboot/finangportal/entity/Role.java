package com.maykelmarrero.springboot.finangportal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "role")
    private String role;

    @Column(name = "role_beschreibung")
    private String roleBeschreibung;


    public Role() {

    }

    public Role(String role, String roleBeschreibung) {
        this.role = role;
        this.roleBeschreibung = roleBeschreibung;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoleBeschreibung() {
        return roleBeschreibung;
    }

    public void setRoleBeschreibung(String roleBeschreibung) {
        this.roleBeschreibung = roleBeschreibung;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", role='" + role + '\'' +
                ", roleBeschreibung='" + roleBeschreibung + '\'' +
                '}';
    }
}
