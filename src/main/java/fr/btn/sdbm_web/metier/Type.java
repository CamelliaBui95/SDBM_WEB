package fr.btn.sdbm_web.metier;

import java.util.Objects;

public class Type {
    private int id;
    private String nomType;

    public Type() {
        this.id = 0;
        nomType = "";
    }

    public Type(int id, String nomType) {
        this.id = id;
        this.nomType = nomType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    @Override
    public String toString() {
        return nomType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type = (Type) o;
        return id == type.id && Objects.equals(nomType, type.nomType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomType);
    }
}
