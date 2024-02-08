package fr.btn.sdbm_web.converter;

import fr.btn.sdbm_web.service.ArticleBean;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

@FacesConverter(value = "articleNameConverter", managed = true)
public class ArticleNameConverter implements Converter<String> {
    @Inject
    private ArticleBean articleBean;

    @Override
    public String getAsObject(FacesContext facesContext, UIComponent uiComponent, String name) {
        articleBean.getArticleSearch().setNomArticle(name);
        return articleBean.getArticleSearch().getNomArticle();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, String s) {
        return s;
    }
}
