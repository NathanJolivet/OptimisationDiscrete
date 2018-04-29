package Factory;

import java.util.ArrayList;

public class Camion {

    private int indice;
    private int capacite = 100;
    //private double distanceParcouru = 0;
    private Noeud noeudPrecedent;
    private Noeud noeudActuel;
    private ArrayList<Noeud> itineraire = new ArrayList();

    public Camion(int indice, Noeud noeudInitial){
        this.indice = indice;
        itineraire.add(noeudInitial);
        noeudActuel = noeudInitial;

    }

    public int getIndice() {
        return indice;
    }

    public int getCapacite() {
        return capacite;
    }

    public void modifCapacite() {
        capacite -= noeudActuel.getQuantite();
    }

    /*
    public double getDistanceParcouru() {
        return distanceParcouru;
    }

    public void modifDistanceParcouru() {
        distanceParcouru += noeudPrecedent.getDistanceTo(noeudActuel);
    }

    */
    public ArrayList<Noeud> getItineraire() {
        return itineraire;
    }

    public void setItineraire(ArrayList<Noeud> itineraire) {
        this.itineraire = itineraire;
    }

    public Noeud getNoeudActuel() {
        return noeudActuel;
    }

    public void setNoeudActuel(Noeud noeudActuel) {
        this.noeudActuel = noeudActuel;
    }

    public Noeud getNoeudPrecedent() {
        return noeudPrecedent;
    }

    public void setNoeudPrecedent(Noeud noeudPrecedent) {
        this.noeudPrecedent = noeudPrecedent;
    }

    public void addNoeud(Noeud noeud){
        this.getItineraire().add(noeud);
        this.setNoeudPrecedent(noeudActuel);
        this.setNoeudActuel(noeud);
        this.modifCapacite();
        //this.modifDistanceParcouru();
    }
}
