package fr.btn.sdbm_web.converter;

import fr.btn.sdbm_web.metier.Continent;
import fr.btn.sdbm_web.service.ContinentBean;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

@FacesConverter(value="continentConverter", managed = true)
public class ContinentConverter implements Converter<Continent> {

    @Inject
    private ContinentBean continentBean;
    @Override
    public Continent getAsObject(FacesContext facesContext, UIComponent uiComponent, String id) {
        if(id != null & !id.trim().isEmpty()) {
            for(Continent c : continentBean.getAllContinents())
                if(c.getId() == Integer.parseInt(id))
                    return c;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Continent continent) {
        return String.valueOf(continent.getId());
    }
}
