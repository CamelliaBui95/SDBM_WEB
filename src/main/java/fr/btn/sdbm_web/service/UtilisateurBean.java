package fr.btn.sdbm_web.service;

import fr.btn.sdbm_web.dao.DAOFactory;
import fr.btn.sdbm_web.metier.Utilisateur;
import fr.btn.sdbm_web.security.Argon2;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
public class UtilisateurBean implements Serializable {
    private Utilisateur currentUtilisateur;

    @PostConstruct
    public void init() {
        System.out.println("init utilisateur bean");
        currentUtilisateur = new Utilisateur();
    }

    public Utilisateur getCurrentUtilisateur() {
        return currentUtilisateur;
    }

    public void setCurrentUtilisateur(Utilisateur currentUtilisateur) {
        this.currentUtilisateur = currentUtilisateur;
    }
    public boolean isValid() {
        String nomLogin = currentUtilisateur.getNomLogin();
        String password = currentUtilisateur.getPassword();


        if(nomLogin.isEmpty() || password.isEmpty())
            return false;

        Utilisateur o = DAOFactory.getUtilisateurDAO().getUtilisateur(currentUtilisateur);

        if(o == null || o.getNomLogin().isEmpty())
            return false;

        boolean isValid = Argon2.validate(password, o.getPassword());

        if(isValid) {
            currentUtilisateur.setPassword(o.getPassword());
            currentUtilisateur.setEmail(o.getEmail());
            currentUtilisateur.setRole(o.getRole());
        }

        return isValid;
    }

}
