package Factory;

import ComplementRessource.Parser;

import java.util.ArrayList;

public class Graph {

    private Parser parse = new Parser();
    ArrayList<Noeud> sommets = new ArrayList<>();

    public ArrayList<Noeud> getSommets() {
        return sommets;
    }

    public Graph(String nomFichier){

        parse.remplirTabNoeuds(nomFichier);

        for(int i = 0; i < parse.getCoord().size(); i++){
            int indice = parse.getCoord().get(i).get(0);
            int x = parse.getCoord().get(i).get(1);
            int y = parse.getCoord().get(i).get(2);
            int q = parse.getCoord().get(i).get(3);
            sommets.add(new Noeud(indice, x, y, q));
        }

    }

    //TODO A FAIRE
    public Solution getSolutionInitiale(){

        return null;
    }


    @Override
    public String toString(){
        String graph = "-------------------------\n";
        for(int i = 0; i<sommets.size(); i++) {
            graph += "\t" + sommets.get(i) + "\n";
        }
        return graph + "-------------------------";
    }


}
