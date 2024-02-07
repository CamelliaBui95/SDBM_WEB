package fr.btn.sdbm_web.metier;

import java.util.Objects;

public class Fabricant {
    private int id;
    private String nomFabricant;

    public Fabricant(int id, String nomFabricant) {
        this.id = id;
        this.nomFabricant = nomFabricant;
    }

    public Fabricant() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomFabricant() {
        return nomFabricant;
    }

    public void setNomFabricant(String nomFabricant) {
        this.nomFabricant = nomFabricant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fabricant fabricant = (Fabricant) o;
        return id == fabricant.id && Objects.equals(nomFabricant, fabricant.nomFabricant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomFabricant);
    }
}
