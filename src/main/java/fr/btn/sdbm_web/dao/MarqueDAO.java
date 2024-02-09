package fr.btn.sdbm_web.dao;

import fr.btn.sdbm_web.metier.Continent;
import fr.btn.sdbm_web.metier.Fabricant;
import fr.btn.sdbm_web.metier.Marque;
import fr.btn.sdbm_web.metier.Pays;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MarqueDAO extends DAO<Marque, Fabricant>{
    @Override
    public ArrayList<Marque> getAll() {
        ArrayList<Marque> marques = new ArrayList<>();
        String rq = "SELECT ID_MARQUE,\n" +
                "\t\tNOM_MARQUE,\n" +
                "\t\tP.ID_PAYS,\n" +
                "\t\tP.NOM_PAYS,\n" +
                "\t\tC.ID_CONTINENT,\n" +
                "\t\tC.NOM_CONTINENT,\n" +
                "\t\tISNULL(F.ID_FABRICANT, (SELECT MAX(ID_FABRICANT) FROM FABRICANT) + 1) AS ID_FABRICANT,\n" +
                "\t\tISNULL(F.NOM_FABRICANT, 'Autre') AS NOM_FABRICANT\n" +
                "FROM MARQUE M\n" +
                "LEFT JOIN FABRICANT F ON M.ID_FABRICANT = F.ID_FABRICANT\n" +
                "LEFT JOIN PAYS P ON M.ID_PAYS = P.ID_PAYS\n" +
                "JOIN CONTINENT C ON P.ID_CONTINENT = C.ID_CONTINENT;";

        try(Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery(rq);
            while(rs.next()) {
                Continent continent = new Continent(rs.getInt("ID_CONTINENT"), rs.getString("NOM_CONTINENT"));
                Pays pays = new Pays(rs.getInt("ID_PAYS"), rs.getString("NOM_PAYS"), continent);
                Fabricant fabricant = new Fabricant(rs.getInt("ID_FABRICANT"), rs.getString("NOM_FABRICANT"));

                Marque marque = new Marque(rs.getInt("ID_MARQUE"), rs.getString("NOM_MARQUE"));
                marque.setPays(pays);
                marque.setFabricant(fabricant);

                marques.add(marque);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return marques;
    }

    @Override
    public ArrayList<Marque> getLike(Fabricant object) {
        return null;
    }

    @Override
    public boolean update(Marque object) {
        return false;
    }

    @Override
    public boolean post(Marque object) {
        return false;
    }

    @Override
    public boolean delete(Marque object) {
        return false;
    }
}
