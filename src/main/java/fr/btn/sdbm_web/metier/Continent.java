package fr.btn.sdbm_web.metier;

import java.util.Objects;

public class Continent {
    private int id;
    private String nomContinent;

    public Continent() {
        this.id = 0;
        this.nomContinent = "";
    }

    public Continent(int id, String nomContinent) {
        this.id = id;
        this.nomContinent = nomContinent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomContinent() {
        return nomContinent;
    }

    public void setNomContinent(String nomContinent) {
        this.nomContinent = nomContinent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Continent continent = (Continent) o;
        return id == continent.id && Objects.equals(nomContinent, continent.nomContinent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomContinent);
    }

    @Override
    public String toString() {
        return nomContinent;
    }
}
