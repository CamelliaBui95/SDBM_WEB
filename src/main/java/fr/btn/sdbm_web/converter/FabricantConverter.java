package fr.btn.sdbm_web.converter;

import fr.btn.sdbm_web.metier.Couleur;
import fr.btn.sdbm_web.metier.Fabricant;
import fr.btn.sdbm_web.service.FabricantBean;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

@FacesConverter(value = "fabricantConverter", managed = true)
public class FabricantConverter implements Converter<Fabricant> {
    @Inject
    private FabricantBean fabricantBean;
    @Override
    public Fabricant getAsObject(FacesContext facesContext, UIComponent uiComponent, String id) {
        if(id != null && !id.trim().isEmpty())
            for(Fabricant fabricant : fabricantBean.getAllFabricants())
                if(fabricant.getId() == Integer.parseInt(id))
                    return fabricant;

        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Fabricant fabricant) {
        return String.valueOf(fabricant.getId());
    }
}
