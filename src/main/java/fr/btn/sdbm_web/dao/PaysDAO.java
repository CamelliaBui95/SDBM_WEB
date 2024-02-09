package fr.btn.sdbm_web.dao;

import fr.btn.sdbm_web.metier.Continent;
import fr.btn.sdbm_web.metier.Pays;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PaysDAO extends DAO<Pays, Continent>{
    @Override
    public ArrayList<Pays> getAll() {
        ArrayList<Pays> paysList = new ArrayList<>();
        String request = "SELECT ID_PAYS,\n" +
                "\t\tNOM_PAYS,\n" +
                "\t\tC.ID_CONTINENT,\n" +
                "\t\tNOM_CONTINENT\n" +
                "FROM PAYS P\n" +
                "JOIN CONTINENT C\n" +
                "ON P.ID_CONTINENT = C.ID_CONTINENT;";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(request);
            while(rs.next()) {
                Continent continent = new Continent(rs.getInt("ID_CONTINENT"), rs.getString("NOM_CONTINENT"));
                Pays pays = new Pays(rs.getInt("ID_PAYS"), rs.getString("NOM_PAYS"), continent);
                paysList.add(pays);
            }
            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return paysList;
    }

    @Override
    public ArrayList<Pays> getLike(Continent continent) {
        ArrayList<Pays> paysList = new ArrayList<>();
        String request = "{call ps_searchPays(?)}";

        try(PreparedStatement stmt = connection.prepareCall(request);) {
            stmt.setInt(1, continent.getId());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                int idPays = rs.getInt(1);
                String nomPays = rs.getString(2);

                paysList.add(new Pays(idPays, nomPays, continent));
            }
            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return paysList;
    }

    @Override
    public boolean update(Pays object) {
        return false;
    }

    @Override
    public boolean post(Pays object) {
        return false;
    }

    @Override
    public boolean delete(Pays object) {
        return false;
    }
}
