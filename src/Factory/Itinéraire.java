package Factory;

import com.sun.javafx.sg.prism.NodeEffectInput;

import java.util.ArrayList;
import java.util.List;

public class Itinéraire {

    private ArrayList<Noeud> itinéraire = new ArrayList();

    public Itinéraire(){

    }

    public ArrayList<Noeud> getItinéraire() {
        return itinéraire;
    }

    public void setItinéraire(ArrayList<Noeud> itinéraire) {
        this.itinéraire = itinéraire;
    }

    public int getSize(){
        return itinéraire.size();
    }

    public void addNoeud(Noeud noeud){
        itinéraire.add(noeud);
    }

    public void addNoeud(Noeud noeud, int i){
        itinéraire.add(i, noeud);
    }

    public Noeud getNoeud(int i){
        return(itinéraire.get(i));
    }

    public void deleteNoeud(int i){
        itinéraire.remove(i);
    }

    public int getCout(){
        int cout = 0;
        for(int i = 0; i < this.getSize(); i++){

        }
    }




}
