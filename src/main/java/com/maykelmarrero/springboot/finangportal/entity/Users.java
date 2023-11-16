package com.maykelmarrero.springboot.finangportal.entity;

import com.maykelmarrero.springboot.finangportal.user.WebUser;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;
    @Column(name = "user_vname")
    private String vName;
    @Column(name = "user_name")
    private String name;

    @Column(name = "user_username")
    private String userName;
    @Column(name = "user_password")
    private String password;
    @Column(name = "user_email")
    private String email;
    @Column(name = "user_telefon")
    private String telefon;
    @Column(name = "user_erstellungsdatum")
    private LocalDate userErstellungsdatum;
    @Column(name = "enabled")
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    public Users() {

    }

    public Users(WebUser webUser) {
        this.userName = webUser.getUserName();
        this.password = webUser.getPassword();
        this.vName = webUser.getFirstName();
        this.name = webUser.getLastName();
        this.email = webUser.getEmail();
        this.telefon = webUser.getTelefon();
        this.userErstellungsdatum = webUser.getWebUserErstellungsdatum();
        this.enabled = webUser.isEnabled();
        this.roles = webUser.getRoles();
    }

    public Users(String vName,
                 String name,
                 String userName,
                 String password,
                 String email,
                 String telefon,
                 LocalDate userErstellungsdatum,
                 boolean enabled) {
        this.vName = vName;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.telefon = telefon;
        this.userErstellungsdatum = userErstellungsdatum;
        this.enabled = enabled;
    }

    public Users(String vName,
                 String name,
                 String userName,
                 String password,
                 String email,
                 String telefon,
                 LocalDate userErstellungsdatum,
                 boolean enabled,
                 Collection<Role> roles) {
        this.vName = vName;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.telefon = telefon;
        this.userErstellungsdatum = userErstellungsdatum;
        this.enabled = enabled;
        this.roles = roles;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName = vName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public LocalDate getUserErstellungsdatum() {
        return userErstellungsdatum;
    }

    public void setUserErstellungsdatum(LocalDate userErstellungsdatum) {
        this.userErstellungsdatum = userErstellungsdatum;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", vName='" + vName + '\'' +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                ", userErstellungsdatum=" + userErstellungsdatum +
                ", enabled=" + enabled +
                ", roles=" + roles +
                '}';
    }
}
