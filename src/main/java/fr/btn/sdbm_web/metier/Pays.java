package fr.btn.sdbm_web.metier;

import java.util.Objects;

public class Pays {
    private int id;
    private String nomPays;

    private Continent continent;

    public Pays(int id, String nomPays, Continent continent) {
        this.id = id;
        this.nomPays = nomPays;
        this.continent = continent;
    }

    public Pays() {
        this.id = 0;
        this.nomPays = "";
        this.continent = new Continent();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomPays() {
        return nomPays;
    }

    public void setNomPays(String nomPays) {
        this.nomPays = nomPays;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pays pays = (Pays) o;
        return id == pays.id && Objects.equals(nomPays, pays.nomPays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomPays);
    }

    @Override
    public String toString() {
        return nomPays;
    }
}
