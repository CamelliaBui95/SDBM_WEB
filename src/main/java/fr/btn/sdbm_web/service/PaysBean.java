package fr.btn.sdbm_web.service;

import fr.btn.sdbm_web.dao.DAOFactory;
import fr.btn.sdbm_web.metier.Pays;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class PaysBean implements Serializable {
    private List<Pays> allPays;
    private Pays selectedPays;

    @PostConstruct
    public void init() {
        selectedPays = new Pays();
        allPays = DAOFactory.getPaysDAO().getAll();
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
}
