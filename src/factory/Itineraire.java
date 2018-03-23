package factory;

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

    public int getSize(){
        return itineraire.size();
    }

    public void addNoeud(Noeud noeud){
        itineraire.add(noeud);
    }

    public void addNoeud(Noeud noeud, int i){
        itineraire.add(i, noeud);
    }

    public Noeud getNoeud(int i){
        return(itineraire.get(i));
    }

    public void deleteNoeud(int i){
        itineraire.remove(i);
    }



    public int getCoutTotal(){
        int cout = 0;
        for(int i = 0; i < this.getSize()-1; i++){
            cout += itineraire.get(i).getDistanceTo(itineraire.get(i+1));
        }
        return cout;
    }




}
