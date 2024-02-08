package fr.btn.sdbm_web.dao;

import fr.btn.sdbm_web.metier.Type;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TypeDAO extends DAO<Type, Type>{
    @Override
    public ArrayList<Type> getAll() {
        ArrayList<Type> types = new ArrayList<>();
        String sqlRequest = "SELECT * FROM TYPEBIERE";

        try(Statement stm = this.connection.createStatement();) {
            ResultSet rs = stm.executeQuery(sqlRequest);
            while(rs.next())
                types.add(new Type(rs.getInt(1), rs.getString(2)));
            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return types;
    }

    @Override
    public ArrayList<Type> getLike(Type object) {
        return null;
    }

    @Override
    public boolean update(Type object) {
        return false;
    }

    @Override
    public boolean post(Type object) {
        return false;
    }

    @Override
    public boolean delete(Type object) {
        return false;
    }
}
