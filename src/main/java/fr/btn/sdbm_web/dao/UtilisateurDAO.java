package fr.btn.sdbm_web.dao;

import fr.btn.sdbm_web.metier.Utilisateur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UtilisateurDAO extends DAO<Utilisateur, Utilisateur> {

    @Override
    public ArrayList<Utilisateur> getAll() {
        return null;
    }

    @Override
    public ArrayList<Utilisateur> getLike(Utilisateur utilisateur) {

        return null;
    }

    @Override
    public boolean update(Utilisateur object) {
        return false;
    }

    @Override
    public boolean post(Utilisateur object) {
        return false;
    }

    @Override
    public boolean delete(Utilisateur object) {
        return false;
    }

    public Utilisateur getUtilisateur(Utilisateur utilisateur) {
        String rq = "SELECT * FROM UTILISATEUR WHERE NOM_LOGIN = ?";
        Utilisateur queriedUtilisateur = new Utilisateur();

        try(PreparedStatement stm = connection.prepareStatement(rq)) {
            stm.setString(1, utilisateur.getNomLogin());
            ResultSet rs = stm.executeQuery();

            while(rs.next()) {
                queriedUtilisateur.setEmail(rs.getString("EMAIL"));
                queriedUtilisateur.setNomLogin(rs.getString("NOM_LOGIN"));
                queriedUtilisateur.setPassword(rs.getString("PASSWORD"));
                queriedUtilisateur.setRole(rs.getString("ROLE"));
            }
            return queriedUtilisateur;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
