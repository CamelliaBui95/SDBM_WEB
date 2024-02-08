package fr.btn.sdbm_web.dao;

public class DAOFactory {
    private static CouleurDAO couleurDAO;
    private static ArticleDAO articleDAO;
    private static PaysDAO paysDAO;
    private static ContinentDAO continentDAO;

    private DAOFactory() {

    }

    public static CouleurDAO getCouleurDAO() {
        if(couleurDAO == null)
            couleurDAO = new CouleurDAO();

        return couleurDAO;
    }

    public static ArticleDAO getArticleDAO() {
        if(articleDAO == null)
            articleDAO = new ArticleDAO();

        return articleDAO;
    }

    public static PaysDAO getPaysDAO() {
        if(paysDAO == null)
            paysDAO = new PaysDAO();

        return paysDAO;
    }

    public static ContinentDAO getContinentDAO() {
        if(continentDAO == null)
            continentDAO = new ContinentDAO();

        return continentDAO;
    }
}
