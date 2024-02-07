package fr.btn.sdbm_web.service;

import fr.btn.sdbm_web.dao.DAOFactory;
import fr.btn.sdbm_web.metier.Couleur;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.tagcloud.DefaultTagCloudItem;
import org.primefaces.model.tagcloud.DefaultTagCloudModel;
import org.primefaces.model.tagcloud.TagCloudItem;
import org.primefaces.model.tagcloud.TagCloudModel;

import java.io.Serializable;
import java.util.List;

@Named (value="couleurBean")
@SessionScoped
public class CouleurBean implements Serializable {
    private List<Couleur> allCouleurs;
    private Couleur selectedCouleur;
    private TagCloudModel model;

    @PostConstruct
    public void init() {
        allCouleurs = DAOFactory.getCouleurDAO().getAll();
        model = new DefaultTagCloudModel();

        int[] strengths = {2,5,3,4};
        int index = 0;
        for(Couleur c : allCouleurs) {
            model.addTag(new DefaultTagCloudItem(c.toString(),strengths[index++]));
            if(index == strengths.length)
                index = 0;
        }
    }

    public List<Couleur> getAllCouleurs() {
        return allCouleurs;
    }

    public void setAllCouleurs(List<Couleur> allCouleurs) {
        this.allCouleurs = allCouleurs;
    }

    public Couleur getSelectedCouleur() {
        return selectedCouleur;
    }

    public void setSelectedCouleur(Couleur selectedCouleur) {
        this.selectedCouleur = selectedCouleur;
    }

    public TagCloudModel getModel() {
        return model;
    }

    public void couleurChanged(SelectEvent<TagCloudItem> event) {
        TagCloudItem item = event.getObject();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Couleur Choisie:", item.getLabel());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
