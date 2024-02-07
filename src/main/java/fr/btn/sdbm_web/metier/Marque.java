package fr.btn.sdbm_web.metier;

import java.util.Objects;

public class Marque {
    private int id;
    private String nomMarque;
    private Pays pays;
    private Fabricant fabricant;
    public Marque(int id, String nomMarque, Pays pays, Fabricant fabricant) {
        this.id = id;
        this.nomMarque = nomMarque;
        this.pays = pays;
        this.fabricant = fabricant;
    }

    public Marque(int id, String nomMarque) {
        this.id = id;
        this.nomMarque = nomMarque;
        this.pays = new Pays();
        this.fabricant = new Fabricant();
    }

    public Marque() {
        this.id = 0;
        this.nomMarque = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomMarque() {
        return nomMarque;
    }

    public void setNomMarque(String nomMarque) {
        this.nomMarque = nomMarque;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public Fabricant getFabricant() {
        return fabricant;
    }

    public void setFabricant(Fabricant fabricant) {
        this.fabricant = fabricant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marque marque = (Marque) o;
        return id == marque.id && Objects.equals(nomMarque, marque.nomMarque) && Objects.equals(pays, marque.pays) && Objects.equals(fabricant, marque.fabricant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomMarque, pays, fabricant);
    }
}
