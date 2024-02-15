package fr.btn.sdbm_web.service;

import fr.btn.sdbm_web.dao.DAOFactory;
import fr.btn.sdbm_web.metier.*;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class SessionBean implements Serializable {
    private List<Article> allArticles;
    private List<Continent> allContinents;
    private List<Pays> allPays;
    private List<Marque> allMarques;
    private List<Fabricant> allFabricants;
    private List<Couleur> allCouleurs;
    private List<Type> allTypes;
    
}
