package fr.btn.sdbm_web.view;

import fr.btn.sdbm_web.metier.Pays;
import fr.btn.sdbm_web.service.ArticleBean;
import fr.btn.sdbm_web.service.ContinentBean;
import fr.btn.sdbm_web.service.PaysBean;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ViewScoped
public class ArticleView implements Serializable {
    @Inject
    private ArticleBean articleBean;
    @Inject
    private PaysBean paysBean;
    @Inject
    private ContinentBean continentBean;

    public void initialize() {
        paysBean.initialize();
        continentBean.initialize();
        articleBean.initialize();
    }
}
