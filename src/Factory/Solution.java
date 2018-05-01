package Factory;

import java.util.ArrayList;
import java.util.Random;

public class Solution {

    private ArrayList<ArrayList<Noeud>> solution = new ArrayList<>();
    Graph graph;

    public Solution(Graph graph){
        this.graph = graph;
    }

    public ArrayList<ArrayList<Noeud>> getSolution() {
        return solution;
    }

    public void setSolution(ArrayList<ArrayList<Noeud>> solution) {
        this.solution = solution;
    }

    public Graph getGraph() {
        return graph;
    }

    public double getCoutTotal(){
        double cout = 0;
        for(int i = 0; i < solution.size(); i++){
            ArrayList<Noeud> itineraire = solution.get(i);
            for(int j = 0; j < itineraire.size()-1; j++){
                cout += itineraire.get(j).getDistanceTo(itineraire.get(j+1));
            }
        }
        return cout;
    }

    public ArrayList<Solution> getVoisinage(int nbVoisins){
        Random random = new Random();
        ArrayList<Solution> voisins = new ArrayList<>();

        for(int i = 0; i < nbVoisins; i++){

            boolean verif = false;

            while(!verif) {

                Solution voisin = new Solution(this.getGraph());
                voisin.setSolution(this.remplissageCopie());

                // Choix de nos randoms
                int itineraire1 = random.nextInt(voisin.getSolution().size());
                int itineraire2 = 0;
                do {
                    itineraire2 = random.nextInt(voisin.getSolution().size());
                } while (itineraire2 == itineraire1);
                int nbInvertions1 = 0;
                int nbInvertions2 = 0;
                int x = 0;
                int y = 0;
                do {
                    nbInvertions1 = random.nextInt(5) + 1;
                    nbInvertions2 = random.nextInt(5) + 1;
                    x = voisin.getSolution().get(itineraire1).size() - nbInvertions1 - 2;
                    y = voisin.getSolution().get(itineraire2).size() - nbInvertions2 - 2;
                } while ((x < 0) || (y < 0));
                int debutEchangeFrom1 = 0;
                int debutEchangeFrom2 = 0;
                if (x == 0) {
                    debutEchangeFrom1 = 1;
                }
                if (y == 0) {
                    debutEchangeFrom2 = 1;
                }
                if (x > 0) {
                    debutEchangeFrom1 = random.nextInt(x+1) + 1;
                }
                if (y > 0) {
                    debutEchangeFrom2 = random.nextInt(y+1) + 1;
                }

                //Echange des noeuds entre les deux itinéraires

                ArrayList<Noeud> noeud_a_Echanger1 = new ArrayList<>();
                for (int j1 = 0; j1 < nbInvertions1; j1++) {
                    Noeud noeudFrom1 = voisin.getSolution().get(itineraire1).get(debutEchangeFrom1);
                    noeud_a_Echanger1.add(noeudFrom1);
                    voisin.getSolution().get(itineraire1).remove(debutEchangeFrom1);
                }


                for (int j2 = 0; j2 < nbInvertions2; j2++) {
                    Noeud noeudFrom2 = voisin.getSolution().get(itineraire2).get(debutEchangeFrom2);
                    voisin.getSolution().get(itineraire1).add(debutEchangeFrom1 + j2, noeudFrom2);
                    voisin.getSolution().get(itineraire2).remove(debutEchangeFrom2);
                }

                for (int j3 = 0; j3< nbInvertions1; j3++){
                    voisin.getSolution().get(itineraire2).add(debutEchangeFrom2 + j3, noeud_a_Echanger1.get(j3));
                }




                //On vérifie que la solution respecte les contraintes: C <= 100
                verif = voisin.contraintesVerifie();

                //Si les contraintes sont respectées, on ajoute la solution dans la liste des voisins
                if( verif == true){
                    voisins.add(voisin);
                }

            }

        }
        return voisins;
    }

    public ArrayList<ArrayList<Noeud>> remplissageCopie(){
        ArrayList<ArrayList<Noeud>> solutionVoisin = new ArrayList<>();
        for(int i = 0; i < solution.size(); i++){
            ArrayList<Noeud> itineraire_i = new ArrayList<>();
            for(int j = 0; j < solution.get(i).size(); j++){
                Noeud n = solution.get(i).get(j);
                Noeud noeud = new Noeud(n.getId(), n.getAbscisse(), n.getOrdonnee(), n.getQuantite());
                itineraire_i.add(noeud);
            }
            solutionVoisin.add(itineraire_i);
        }
        return solutionVoisin;
    }

    public boolean contraintesVerifie(){

        for(int i = 0; i < this.getSolution().size(); i++){
            int capaciteItineraire = 0;
            for(int j = 0; j < this.getSolution().get(i).size(); j++){
                capaciteItineraire += this.getSolution().get(i).get(j).getQuantite();
            }
            if(capaciteItineraire > 100){
                return false;
            }
        }
        return true;
    }


    @Override
    public String toString(){
        String stringSol = "Solution du " + graph.getNom() + ":\n";
        for(int i = 0; i < solution.size(); i++){
            stringSol += "\t" + solution.get(i).get(0).getId()+ " => ";
            for(int j = 1; j < solution.get(i).size()-1; j++) {
                stringSol += solution.get(i).get(j).getId() + " (" + solution.get(i).get(j).getQuantite() + ") => ";
            }
            stringSol += solution.get(i).get(solution.get(i).size()-1).getId()+  "\n" ;
        }
        return stringSol;
    }


}