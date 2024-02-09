package fr.btn.sdbm_web.service;

import fr.btn.sdbm_web.dao.DAOFactory;
import fr.btn.sdbm_web.metier.Continent;
import fr.btn.sdbm_web.metier.Pays;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class ContinentBean implements Serializable {
    private List<Continent> allContinents;

    private List<Continent> filteredContinents;
    private Continent selectedContinent;

    @PostConstruct
    public void init() {
        initialize();
    }
    public List<Continent> getAllContinents() {
        return allContinents;
    }

    public void setAllContinents(List<Continent> allContinents) {
        this.allContinents = allContinents;
    }

    public List<Continent> getFilteredContinents() {
        return filteredContinents;
    }

    public void setFilteredContinents(List<Continent> filteredContinents) {
        this.filteredContinents = filteredContinents;
    }

    public Continent getSelectedContinent() {
        return selectedContinent;
    }

    public void setSelectedContinent(Continent selectedContinent) {
        this.selectedContinent = selectedContinent;
    }

    public void initialize() {
        if(allContinents == null) {
            allContinents = DAOFactory.getContinentDAO().getAll();
            allContinents.add(0, new Continent(0, "Choisir un Continent"));
        }
        filteredContinents = allContinents;
        selectedContinent = new Continent();
    }

}
