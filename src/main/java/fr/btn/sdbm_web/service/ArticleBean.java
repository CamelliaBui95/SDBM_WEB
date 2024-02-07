package fr.btn.sdbm_web.service;

import fr.btn.sdbm_web.dao.DAOFactory;
import fr.btn.sdbm_web.metier.Article;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named(value="articleBean")
@SessionScoped
public class ArticleBean implements Serializable {
    private List<Article> allArticles;

    private Article selectedArticle;

    @PostConstruct
    public void init() {
        allArticles = DAOFactory.getArticleDAO().getAll();
    }

    public List<Article> getAllArticles() {
        return allArticles;
    }

    public void setAllArticles(List<Article> allArticles) {
        this.allArticles = allArticles;
    }

    public Article getSelectedArticle() {
        return selectedArticle;
    }

    public void setSelectedArticle(Article selectedArticle) {
        this.selectedArticle = selectedArticle;
    }
}
