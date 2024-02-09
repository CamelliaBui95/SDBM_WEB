package fr.btn.sdbm_web.dao;

import fr.btn.sdbm_web.metier.Fabricant;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class FabricantDAO extends DAO<Fabricant, Fabricant>{
    @Override
    public ArrayList<Fabricant> getAll() {
        ArrayList<Fabricant> fabricants = new ArrayList<>();
        String rq = "SELECT ID_FABRICANT,\n" +
                "\t\tNOM_FABRICANT\n" +
                "FROM FABRICANT\n" +
                "UNION\n" +
                "SELECT ID_FABRICANT = (SELECT MAX(ID_FABRICANT) FROM FABRICANT) + 1, \n" +
                "\t\tNOM_FABRICANT = 'Autre';\n";

        try(Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery(rq);

            while(rs.next())
                fabricants.add(new Fabricant(rs.getInt("ID_FABRICANT"), rs.getString("NOM_FABRICANT")));

            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return fabricants;
    }

    @Override
    public ArrayList<Fabricant> getLike(Fabricant object) {
        return null;
    }

    @Override
    public boolean update(Fabricant object) {
        return false;
    }

    @Override
    public boolean post(Fabricant object) {
        return false;
    }

    @Override
    public boolean delete(Fabricant object) {
        return false;
    }
}
