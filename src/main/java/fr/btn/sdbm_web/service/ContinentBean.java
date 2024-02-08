package fr.btn.sdbm_web.service;

import fr.btn.sdbm_web.dao.DAOFactory;
import fr.btn.sdbm_web.metier.Continent;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ContinentBean implements Serializable {
    private List<Continent> allContinents;
    private Continent selectedContinent;

    @PostConstruct
    public void init() {
        allContinents = DAOFactory.getContinentDAO().getAll();
        allContinents.add(0, new Continent(0, "Choisir un Continent"));
        selectedContinent = new Continent();
    }
    public List<Continent> getAllContinents() {
        return allContinents;
    }

    public void setAllContinents(List<Continent> allContinents) {
        this.allContinents = allContinents;
    }

    public Continent getSelectedContinent() {
        return selectedContinent;
    }

    public void setSelectedContinent(Continent selectedContinent) {
        this.selectedContinent = selectedContinent;
    }
}
