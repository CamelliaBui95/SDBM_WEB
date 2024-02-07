package fr.btn.sdbm_web.dao;

import fr.btn.sdbm_web.metier.Couleur;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CouleurDAO extends DAO<Couleur, Couleur>{
    @Override
    public ArrayList<Couleur> getAll() {
        ArrayList<Couleur> couleurs = new ArrayList<>();
        String request = "SELECT * FROM COULEUR";
        try {
            Statement stm = this.connection.createStatement();
            ResultSet rs = stm.executeQuery(request);
            while(rs.next())
                couleurs.add(new Couleur(rs.getInt(1), rs.getString(2)));
            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return couleurs;
    }

    @Override
    public ArrayList<Couleur> getLike(Couleur couleur) {
        return null;
    }

    @Override
    public boolean update(Couleur couleur) {
        return false;
    }

    @Override
    public boolean post(Couleur couleur) {
        return false;
    }

    @Override
    public boolean delete(Couleur couleur) {
        return false;
    }
}
