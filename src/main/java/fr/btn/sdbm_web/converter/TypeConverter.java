package fr.btn.sdbm_web.converter;

import fr.btn.sdbm_web.metier.Pays;
import fr.btn.sdbm_web.metier.Type;
import fr.btn.sdbm_web.service.TypeBean;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

@FacesConverter(value = "typeConverter", managed = true)
public class TypeConverter implements Converter<Type> {

    @Inject
    private TypeBean typeBean;

    @Override
    public Type getAsObject(FacesContext facesContext, UIComponent uiComponent, String id) {
        if(id != null && !id.trim().isEmpty())
            for(Type type : typeBean.getAllTypes())
                if(type.getId() == Integer.parseInt(id))
                    return type;

        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Type type) {
        return String.valueOf(type.getId());
    }
}
