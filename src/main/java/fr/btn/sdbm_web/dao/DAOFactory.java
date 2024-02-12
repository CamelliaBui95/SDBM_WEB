package fr.btn.sdbm_web.dao;

public class DAOFactory {
    private static CouleurDAO couleurDAO;
    private static ArticleDAO articleDAO;
    private static PaysDAO paysDAO;
    private static ContinentDAO continentDAO;
    private static TypeDAO typeDAO;
    private static MarqueDAO marqueDAO;
    private static FabricantDAO fabricantDAO;

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

    public static TypeDAO getTypeDAO() {
        if(typeDAO == null)
            typeDAO = new TypeDAO();

        return typeDAO;
    }

    public static MarqueDAO getMarqueDAO() {
        if(marqueDAO == null)
            marqueDAO = new MarqueDAO();

        return marqueDAO;
    }

    public static FabricantDAO getFabricantDAO() {
        if(fabricantDAO == null)
            fabricantDAO = new FabricantDAO();

        return fabricantDAO;
    }


}
