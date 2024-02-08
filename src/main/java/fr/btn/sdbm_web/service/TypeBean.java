package fr.btn.sdbm_web.service;

import fr.btn.sdbm_web.dao.DAOFactory;
import fr.btn.sdbm_web.metier.Type;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class TypeBean implements Serializable {
    private List<Type> allTypes;
    private Type selectedType;

    @PostConstruct
    public void init() {
        allTypes = DAOFactory.getTypeDAO().getAll();
        allTypes.add(0, new Type(0, "Choisir un type"));
        selectedType = new Type();
    }

    public List<Type> getAllTypes() {
        return allTypes;
    }

    public void setAllTypes(List<Type> allTypes) {
        this.allTypes = allTypes;
    }

    public Type getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(Type selectedType) {
        this.selectedType = selectedType;
    }
}
