package Factory;

import java.util.ArrayList;

public class Itineraire {

    private ArrayList<Noeud> itineraire = new ArrayList<>();

    public Itineraire(){

    }

    public ArrayList<Noeud> getItineraire() {
        return itineraire;
    }

    public void setItineraire(ArrayList<Noeud> itineraire) {
        this.itineraire = itineraire;
    }

    public int getCoutTotal(){
        int cout = 0;
        for(int i = 0; i < itineraire.size()-1; i++){
            cout += itineraire.get(i).getDistanceTo(itineraire.get(i+1));
        }
        return cout;
    }




}
