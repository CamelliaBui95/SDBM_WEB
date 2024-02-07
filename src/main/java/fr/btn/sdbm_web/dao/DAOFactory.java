package fr.btn.sdbm_web.dao;

public class DAOFactory {
    private static CouleurDAO couleurDAO;

    private DAOFactory() {

    }

    public static CouleurDAO getCouleurDAO() {
        if(couleurDAO == null)
            couleurDAO = new CouleurDAO();

        return couleurDAO;
    }
}
