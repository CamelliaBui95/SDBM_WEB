package fr.btn.sdbm_web.dao;

import fr.btn.sdbm_web.metier.*;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ArticleDAO extends DAO<Article, ArticleSearch>{
    @Override
    public ArrayList<Article> getAll() {
        ArrayList<Article> articles = new ArrayList<>();
        String ps = "{call ps_allArticles}";
        try(CallableStatement cStmt = this.connection.prepareCall(ps)) {
            ResultSet rs = cStmt.executeQuery();
            while(rs.next()) {
                Article article = new Article(rs.getInt("ID_ARTICLE"), rs.getString("NOM_ARTICLE"));

                Volume volume = new Volume(rs.getInt("VOLUME"));
                Titrage titrage = new Titrage(rs.getFloat("TITRAGE"));
                Type type = new Type(rs.getInt("ID_TYPE"), rs.getString("NOM_TYPE"));
                Couleur couleur = new Couleur(rs.getInt("ID_COULEUR"), rs.getString("NOM_COULEUR"));
                Marque marque = new Marque(rs.getInt("ID_MARQUE"), rs.getString("NOM_MARQUE"));

                article.setVolume(volume);
                article.setTitrage(titrage);
                article.setType(type);
                article.setCouleur(couleur);
                article.setMarque(marque);

                articles.add(article);
            }
            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return articles;
    }

    @Override
    public ArrayList<Article> getLike(ArticleSearch object) {
        return null;
    }

    @Override
    public boolean update(Article object) {
        return false;
    }

    @Override
    public boolean post(Article object) {
        return false;
    }

    @Override
    public boolean delete(Article object) {
        return false;
    }
}