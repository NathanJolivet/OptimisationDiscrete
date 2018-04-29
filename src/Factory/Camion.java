package Factory;

import java.util.ArrayList;

public class Camion {

    private int indice;
    private int capacite = 100;
    private double distanceParcouru = 0;
    private Graph graph;
    private Noeud noeudActuel;
    private ArrayList<Noeud> itineraire = new ArrayList();

    public Camion(int indice, Graph graph){
        this.indice = indice;
        this.graph = graph;
        itineraire.add(graph.getSommets().get(0));
        noeudActuel = graph.getSommets().get(0);

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

    public double getDistanceParcouru() {
        return distanceParcouru;
    }

    public void modifDistanceParcouru(double distanceNoeudSuivant) {
        distanceParcouru += distanceNoeudSuivant;
    }

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
}
