package fr.btn.sdbm_web.dao;

import fr.btn.sdbm_web.metier.*;

import java.sql.*;
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

                Continent continent = new Continent(rs.getInt("ID_CONTINENT"), rs.getString("CONTINENT"));
                Pays pays = new Pays(rs.getInt("ID_PAYS"), rs.getString("PAYS"), continent);
                Fabricant fabricant = new Fabricant(rs.getInt("ID_FABRICANT"), rs.getString("FABRICANT"));

                Marque marque = new Marque(rs.getInt("ID_MARQUE"), rs.getString("MARQUE"));
                marque.setPays(pays);
                marque.setFabricant(fabricant);

                article.setPrixAchat(rs.getFloat("PRIX_ACHAT"));
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
            stm.setInt(11, articleSearch.getOffset());
            stm.setInt(12, articleSearch.getPageSize());
            stm.registerOutParameter(13, Types.INTEGER);

            ResultSet rs = stm.executeQuery();
            rs.next();
            articleSearch.setRowCount(rs.getInt(1));

            stm.getMoreResults();
            rs = stm.getResultSet();

            while(rs.next()) {
                Article article = new Article(rs.getInt("Référence"), rs.getString("Désignation"));

                Volume volume = new Volume(rs.getInt("VOLUME"));
                Titrage titrage = new Titrage(rs.getFloat("TITRAGE"));
                Type type = new Type(rs.getInt("ID_TYPE"), rs.getString("TYPE"));
                Couleur couleur = new Couleur(rs.getInt("ID_COULEUR"), rs.getString("COULEUR"));

                Continent continent = new Continent(rs.getInt("ID_CONTINENT"), rs.getString("CONTINENT"));
                Pays pays = new Pays(rs.getInt("ID_PAYS"), rs.getString("PAYS"), continent);
                Fabricant fabricant = new Fabricant(rs.getInt("ID_FABRICANT"), rs.getString("FABRICANT"));

                Marque marque = new Marque(rs.getInt("ID_MARQUE"), rs.getString("MARQUE"));
                marque.setPays(pays);
                marque.setFabricant(fabricant);

                article.setPrixAchat(rs.getFloat("PRIX_ACHAT"));
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
    public boolean update(Article article) {
        String rq = "{call ps_modifierArticle(?,?,?,?,?,?,?,?,?)}";
        try ( PreparedStatement stmt = connection.prepareStatement(rq, Statement.RETURN_GENERATED_KEYS)){
            stmt.setInt(1, article.getId());
            stmt.setString(2, article.getNomArticle());
            stmt.setFloat(3, article.getPrixAchat());
            stmt.setInt(4, article.getVolume().getVolume());
            stmt.setFloat(5, article.getTitrage().getTitrage());
            stmt.setInt(6, article.getMarque().getId());
            stmt.setInt(7, article.getCouleur().getId());
            stmt.setInt(8, article.getType().getId());
            stmt.setInt(9, article.getQuantite());
            stmt.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean post(Article article) {
        String ps = "{call ps_insertArticle(?,?,?,?,?,?,?,?)}";
        try(PreparedStatement stmt = connection.prepareStatement(ps)) {
            stmt.setString(1, article.getNomArticle());
            stmt.setFloat(2, article.getPrixAchat());
            stmt.setInt(3, article.getVolume().getVolume());
            stmt.setFloat(4, article.getTitrage().getTitrage());
            stmt.setInt(5, article.getMarque().getId());
            stmt.setInt(6, article.getCouleur().getId());
            stmt.setInt(7, article.getType().getId());
            stmt.setInt(8, article.getQuantite());
            stmt.execute();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Article article) {
        String rq = "DELETE FROM ARTICLE WHERE ID_ARTICLE=?";
        try(PreparedStatement stmt = connection.prepareStatement(rq)) {
            stmt.setInt(1, article.getId());
            stmt.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
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
