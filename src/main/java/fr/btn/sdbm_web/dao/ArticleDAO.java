package fr.btn.sdbm_web.dao;

import fr.btn.sdbm_web.metier.*;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
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
                article.setQuantite(rs.getInt("STOCK"));

                articles.add(article);
            }
            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return articles;
    }

    @Override
    public ArrayList<Article> getLike(ArticleSearch articleSearch) {
        ArrayList<Article> articles = new ArrayList<>();
        String spReq = "{call ps_searchArticles(?,?,?,?,?,?,?,?,?,?,?,?,?)}";

        try(CallableStatement stm = connection.prepareCall(spReq)) {
            stm.setString(1, articleSearch.getNomArticle());
            stm.setInt(2, articleSearch.getVolume().getVolume());
            stm.setFloat(3, articleSearch.getTitrageMin().getTitrage());
            stm.setFloat(4, articleSearch.getTitrageMax().getTitrage());
            stm.setInt(5, articleSearch.getCouleur().getId());
            stm.setInt(6, articleSearch.getMarque().getId());
            stm.setInt(7, articleSearch.getType().getId());
            stm.setInt(8, articleSearch.getPays().getId());
            stm.setInt(9, articleSearch.getFabricant().getId());
            stm.setInt(10, articleSearch.getContinent().getId());
            stm.setInt(11, 0);
            stm.setInt(12, 0);
            stm.registerOutParameter(13, Types.INTEGER);

            stm.executeQuery();
            stm.getMoreResults();
            ResultSet rs = stm.getResultSet();

            while(rs.next()) {
                Article article = new Article(rs.getInt("Référence"), rs.getString("Désignation"));

                Volume volume = new Volume(rs.getInt("VOLUME"));
                Titrage titrage = new Titrage(rs.getFloat("TITRAGE"));
                Type type = new Type(rs.getInt("ID_TYPE"), rs.getString("TYPE"));
                Couleur couleur = new Couleur(rs.getInt("ID_COULEUR"), rs.getString("COULEUR"));
                Marque marque = new Marque(rs.getInt("ID_MARQUE"), rs.getString("MARQUE"));

                article.setVolume(volume);
                article.setTitrage(titrage);
                article.setType(type);
                article.setCouleur(couleur);
                article.setMarque(marque);
                article.setQuantite(rs.getInt("STOCK"));

                articles.add(article);
            }
            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return articles;
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

    public int getNbRecords() {
        int total = 0;
        String rq = "SELECT COUNT(*) AS TOTAL_RECORD FROM View_1";
        try(Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery(rq);
            if(rs.next())
                total = rs.getInt("TOTAL_RECORD");
            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return total;
    }
}
