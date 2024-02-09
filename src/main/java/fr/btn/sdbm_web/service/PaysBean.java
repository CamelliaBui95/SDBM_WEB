package fr.btn.sdbm_web.service;

import fr.btn.sdbm_web.dao.DAOFactory;
import fr.btn.sdbm_web.metier.Continent;
import fr.btn.sdbm_web.metier.Pays;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class PaysBean implements Serializable {
    private List<Pays> allPays;
    private List<Pays> filteredPays;
    private Pays selectedPays;

    @PostConstruct
    public void init() {
       initialize();
    }

    public List<Pays> getAllPays() {
        return allPays;
    }

    public void setAllPays(List<Pays> allPays) {
        this.allPays = allPays;
    }

    public Pays getSelectedPays() {
        return selectedPays;
    }

    public void setSelectedPays(Pays selectedPays) {
        this.selectedPays = selectedPays;
    }

    public List<Pays> getFilteredPays() {
        return filteredPays;
    }

    public void setFilteredPays(List<Pays> filteredPays) {
        this.filteredPays = filteredPays;
    }

    public void onContinentChange(Continent continent) {
        if(continent.getId() == 0) {
            filteredPays = allPays;
            return;
        }

        filteredPays = DAOFactory.getPaysDAO().getLike(continent);
    }

    public void initialize() {
        if(allPays == null) {
            allPays = DAOFactory.getPaysDAO().getAll();
            allPays.add(0, new Pays(0, "Choisir un pays",new Continent()));
        }

        filteredPays = allPays;
        selectedPays = new Pays();
    }
}
