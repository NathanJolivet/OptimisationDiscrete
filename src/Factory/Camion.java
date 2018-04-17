package Factory;

public class Camion {

    private int indice;
    private int capacite = 100;
    private double distanceParcouru = 0;
    private Graph graph;
    private Noeud noeudActuel;
    private Itineraire itineraire = new Itineraire();

    public Camion(int indice, Graph graph){
        this.indice = indice;
        this.graph = graph;
        itineraire.getItineraire().add(graph.getSommets().get(0));
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

    public Itineraire getItineraire() {
        return itineraire;
    }

    public void setItineraire(Itineraire itineraire) {
        this.itineraire = itineraire;
    }

    public Noeud getNoeudActuel() {
        return noeudActuel;
    }

    public void setNoeudActuel(Noeud noeudActuel) {
        this.noeudActuel = noeudActuel;
    }
}
