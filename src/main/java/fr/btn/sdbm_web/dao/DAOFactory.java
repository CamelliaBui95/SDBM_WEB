package fr.btn.sdbm_web.dao;

public class DAOFactory {
    private static CouleurDAO couleurDAO;
    private static ArticleDAO articleDAO;

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
}
