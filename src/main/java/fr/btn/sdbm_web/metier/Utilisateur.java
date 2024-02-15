package fr.btn.sdbm_web.metier;

import fr.btn.sdbm_web.dao.DAOFactory;

public class Utilisateur {
    private String nomLogin;
    private String email;
    private String password;
    private String role;

    public Utilisateur() {
        nomLogin = "";
        email = "";
        password = "";
        role = "";
    }

    public Utilisateur(String nomLogin, String password) {
        this.nomLogin = nomLogin;
        this.password = password;
    }

    public String getNomLogin() {
        return nomLogin;
    }

    public void setNomLogin(String nomLogin) {
        this.nomLogin = nomLogin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
