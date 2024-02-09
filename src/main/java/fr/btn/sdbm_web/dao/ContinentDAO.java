package fr.btn.sdbm_web.dao;

import fr.btn.sdbm_web.metier.Continent;
import fr.btn.sdbm_web.metier.Pays;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ContinentDAO extends DAO<Continent, Pays>{
    @Override
    public ArrayList<Continent> getAll() {
        ArrayList<Continent> continents = new ArrayList<>();
        String request = "SELECT * FROM CONTINENT";
        try(Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(request);
            while(rs.next())
                continents.add(new Continent(rs.getInt("ID_CONTINENT"), rs.getString("NOM_CONTINENT")));
            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return continents;
    }

    @Override
    public ArrayList<Continent> getLike(Pays pays) {
        return null;
    }

    @Override
    public boolean update(Continent object) {
        return false;
    }

    @Override
    public boolean post(Continent object) {
        return false;
    }

    @Override
    public boolean delete(Continent object) {
        return false;
    }
}
