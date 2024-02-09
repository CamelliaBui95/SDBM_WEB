package fr.btn.sdbm_web.converter;
import fr.btn.sdbm_web.metier.Couleur;
import fr.btn.sdbm_web.service.ArticleBean;
import fr.btn.sdbm_web.service.CouleurBean;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

@FacesConverter(value="couleurConverter", managed = true)
public class CouleurConverter implements Converter<Couleur> {
    @Inject
    private CouleurBean couleurBean;
    @Override
    public Couleur getAsObject(FacesContext facesContext, UIComponent uiComponent, String id) {
        if(id != null && !id.trim().isEmpty())
            for(Couleur couleur : couleurBean.getAllCouleurs())
                if(couleur.getId() == Integer.parseInt(id))
                    return couleur;

        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Couleur couleur) {
        return String.valueOf(couleur.getId());
    }
}
