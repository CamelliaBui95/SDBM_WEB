package fr.btn.sdbm_web.converter;

import fr.btn.sdbm_web.metier.Couleur;
import fr.btn.sdbm_web.metier.Marque;
import fr.btn.sdbm_web.service.MarqueBean;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

@FacesConverter(value = "marqueConverter", managed = true)
public class MarqueConverter implements Converter<Marque> {

    @Inject
    private MarqueBean marqueBean;
    @Override
    public Marque getAsObject(FacesContext facesContext, UIComponent uiComponent, String id) {
        if(id != null && !id.trim().isEmpty())
            for(Marque marque : marqueBean.getAllMarques())
                if(marque.getId() == Integer.parseInt(id))
                    return marque;

        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Marque marque) {
        return String.valueOf(marque.getId());
    }
}
