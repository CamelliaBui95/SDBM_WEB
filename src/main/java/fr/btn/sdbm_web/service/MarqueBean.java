package fr.btn.sdbm_web.service;

import fr.btn.sdbm_web.dao.DAOFactory;
import fr.btn.sdbm_web.metier.Fabricant;
import fr.btn.sdbm_web.metier.Marque;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class MarqueBean implements Serializable {
    private List<Marque> allMarques;
    private List<Marque> filteredMarques;
    private Marque selectedMarque;

    @PostConstruct
    public void init() {
        initialize();
    }

    public List<Marque> getAllMarques() {
        return allMarques;
    }

    public void setAllMarques(List<Marque> allMarques) {
        this.allMarques = allMarques;
    }

    public List<Marque> getFilteredMarques() {
        return filteredMarques;
    }

    public void setFilteredMarques(List<Marque> filteredMarques) {
        this.filteredMarques = filteredMarques;
    }

    public Marque getSelectedMarque() {
        return selectedMarque;
    }

    public void setSelectedMarque(Marque selectedMarque) {
        this.selectedMarque = selectedMarque;
    }

    public void initialize() {
        if(allMarques == null) {
            allMarques = DAOFactory.getMarqueDAO().getAll();
            allMarques.add(0, new Marque(0, "Choisir une marque"));
        }

        filteredMarques = allMarques;
        selectedMarque = new Marque();
    }

    public void onFabricantChange(Fabricant fabricant) {
        if(fabricant.getId() == 0) {
            filteredMarques = allMarques;
            return;
        }
        filteredMarques = DAOFactory.getMarqueDAO().getLike(fabricant);
    }
}
