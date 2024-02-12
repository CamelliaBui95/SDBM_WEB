package fr.btn.sdbm_web.service;

import fr.btn.sdbm_web.dao.DAOFactory;
import fr.btn.sdbm_web.metier.Article;
import fr.btn.sdbm_web.metier.ArticleSearch;
import fr.btn.sdbm_web.utils.LazySorter;
import fr.btn.sdbm_web.utils.Messenger;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named(value="articleBean")
@SessionScoped
public class ArticleBean implements Serializable {
    private LazyDataModel<Article> articleModel;
    private List<Article> allArticles;
    private Article selectedArticle;
    private ArticleSearch articleSearch;
    private boolean addNew = false;

    @PostConstruct
    public void init() {
        initialize();
        articleModel = new LazyDataModel<>() {
            @Override
            public int count(Map<String, FilterMeta> map) {
                return 0;
            }

            @Override
            public List<Article> load(int offset, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
                articleSearch.setOffset((offset / pageSize) + 1);
                articleSearch.setPageSize(pageSize);

                allArticles = DAOFactory.getArticleDAO().getLike(articleSearch);

                if(!sortBy.isEmpty()) {
                    List<Comparator<Article>> comparators = sortBy.values().stream()
                            .map(o -> new LazySorter(o.getField(), o.getOrder()))
                            .collect(Collectors.toList());

                   for(Comparator<Article> cp : comparators)
                       allArticles.sort(cp);
                }


                DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("articles");
                if(dataTable != null)
                    dataTable.reset();

                this.setRowCount(articleSearch.getRowCount());

                return allArticles;
            }

            @Override
            public Article getRowData(String rowKey) {
                for (Article article : allArticles) {
                    if (article.getId() == Integer.parseInt(rowKey)) {
                        return article;
                    }
                }

                return null;
            }

            @Override
            public String getRowKey(Article article) {
                return String.valueOf(article.getId());
            }
        };
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
    public LazyDataModel<Article> getArticleModel() {
        return articleModel;
    }

    public void setArticleModel(LazyDataModel<Article> articleModel) {
        this.articleModel = articleModel;
    }

    public void setArticleSearch(ArticleSearch articleSearch) {
        this.articleSearch = articleSearch;
    }

    public ArticleSearch getArticleSearch() {
        return articleSearch;
    }

    public void updateArticles() {
        allArticles = DAOFactory.getArticleDAO().getLike(articleSearch);
    }

    public void postArticle() {
        boolean isUpdated;
        String msgDetail = "";

        if(addNew)
            isUpdated = DAOFactory.getArticleDAO().post(selectedArticle);
        else
            isUpdated = DAOFactory.getArticleDAO().update(selectedArticle);

        if(isUpdated)
            Messenger.addMessage(FacesMessage.SEVERITY_INFO, "Message", "L'article a été ajouté/modifié.");
        else
            Messenger.addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Impossible d'ajouter/modifier cet article. Veuillez réessayer.");

        PrimeFaces.current().executeScript("PF('manageArticleDialog').hide()");
        PrimeFaces.current().ajax().update("articles", "messages");
    }

    public void deleteArticle() {
        boolean isDeleted = DAOFactory.getArticleDAO().delete(selectedArticle);
        if(isDeleted)
            Messenger.addMessage(FacesMessage.SEVERITY_INFO, "Message", "L'article a été supprimé.");
        else
            Messenger.addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Impossible de supprimer cet article. Veuillez réessayer.");

        PrimeFaces.current().executeScript("PF('deleteArticleDialog').hide()");
        PrimeFaces.current().ajax().update("articles", "messages");
    }

    public void openNew() {
        this.selectedArticle = new Article(0, "");
        addNew = true;
    }

    public void initialize() {
        articleSearch = new ArticleSearch();
        allArticles = DAOFactory.getArticleDAO().getLike(articleSearch);
        selectedArticle = new Article(0, "");
    }

}
