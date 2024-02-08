package fr.btn.sdbm_web.service;

import fr.btn.sdbm_web.dao.DAOFactory;
import fr.btn.sdbm_web.metier.Article;
import fr.btn.sdbm_web.metier.ArticleSearch;
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

    private ArticleSearch articleSearch;

    @PostConstruct
    public void init() {
        articleSearch = new ArticleSearch();
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

    public ArticleSearch getArticleSearch() {
        return articleSearch;
    }

    public void setArticleSearch(ArticleSearch articleSearch) {
        this.articleSearch = articleSearch;
    }
}
