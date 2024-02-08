package fr.btn.sdbm_web.metier;

public class ArticleSearch {
    private String nomArticle = "";
    private Continent continent = new Continent();
    private Couleur couleur = new Couleur();
    private Marque marque = new Marque();
    private Fabricant fabricant = new Fabricant();
    private Pays pays = new Pays();
    private Type type = new Type();
    private Volume volume = new Volume();
    private Titrage titrageMin = new Titrage(0);
    private Titrage titrageMax = new Titrage(30);

    private int limit = 10;
    private int page = 1;

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        System.out.println(nomArticle);
        this.nomArticle = nomArticle;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
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

    public Fabricant getFabricant() {
        return fabricant;
    }

    public void setFabricant(Fabricant fabricant) {
        this.fabricant = fabricant;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Volume getVolume() {
        return volume;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }

    public Titrage getTitrageMin() {
        return titrageMin;
    }

    public void setTitrageMin(Titrage titrageMin) {
        this.titrageMin = titrageMin;
    }

    public Titrage getTitrageMax() {
        return titrageMax;
    }

    public void setTitrageMax(Titrage titrageMax) {
        this.titrageMax = titrageMax;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
