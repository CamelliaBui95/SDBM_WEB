package fr.btn.sdbm_web.service;

import fr.btn.sdbm_web.dao.DAOFactory;
import fr.btn.sdbm_web.metier.Fabricant;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class FabricantBean implements Serializable {
    private List<Fabricant> allFabricants;
    private List<Fabricant> filteredFabricants;
    private Fabricant selectedFabricant;

    @PostConstruct
    public void init() {
        initialize();
    }
    public List<Fabricant> getAllFabricants() {
        return allFabricants;
    }

    public void setAllFabricants(List<Fabricant> allFabricants) {
        this.allFabricants = allFabricants;
    }

    public List<Fabricant> getFilteredFabricants() {
        return filteredFabricants;
    }

    public void setFilteredFabricants(List<Fabricant> filteredFabricants) {
        this.filteredFabricants = filteredFabricants;
    }

    public void initialize() {
        if(allFabricants == null) {
            allFabricants = DAOFactory.getFabricantDAO().getAll();
            allFabricants.add(0, new Fabricant(0, "Choisir un Fabricant"));
        }

        filteredFabricants = allFabricants;
        selectedFabricant = new Fabricant();
    }
}
