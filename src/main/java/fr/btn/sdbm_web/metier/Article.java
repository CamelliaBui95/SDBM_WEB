package fr.btn.sdbm_web.metier;

import java.util.Objects;

public class Article {
    private int id;
    private String nomArticle;
    private Volume volume;
    private Type type;
    private Couleur couleur;
    private Marque marque;
    private Titrage titrage;
    private int quantite;

    public Article(int id, String nomArticle) {
        this.id = id;
        this.nomArticle = nomArticle;
        volume = new Volume();
        titrage = new Titrage(0);
        type = new Type();
        couleur = new Couleur();
        marque = new Marque();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public Volume getVolume() {
        return volume;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public Titrage getTitrage() {
        return titrage;
    }

    public void setTitrage(Titrage titrage) {
        this.titrage = titrage;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id == article.id && quantite == article.quantite && Objects.equals(nomArticle, article.nomArticle) && Objects.equals(volume, article.volume) && Objects.equals(type, article.type) && Objects.equals(couleur, article.couleur) && Objects.equals(marque, article.marque) && Objects.equals(titrage, article.titrage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomArticle, volume, type, couleur, marque, titrage, quantite);
    }
}
