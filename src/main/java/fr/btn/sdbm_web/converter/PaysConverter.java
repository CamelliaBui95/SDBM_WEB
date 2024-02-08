package fr.btn.sdbm_web.converter;

import fr.btn.sdbm_web.metier.Couleur;
import fr.btn.sdbm_web.metier.Pays;
import fr.btn.sdbm_web.service.PaysBean;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

@FacesConverter(value="paysConverter", managed = true)
public class PaysConverter implements Converter<Pays> {

    @Inject
    private PaysBean paysBean;
    @Override
    public Pays getAsObject(FacesContext facesContext, UIComponent uiComponent, String id) {
        if(id != null && !id.trim().isEmpty())
            for(Pays pays : paysBean.getAllPays())
                if(pays.getId() == Integer.parseInt(id))
                    return pays;

        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Pays pays) {
        return String.valueOf(pays.getId());
    }
}
