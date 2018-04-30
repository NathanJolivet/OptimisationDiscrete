package Factory;

import ComplementRessource.Parser;
import java.util.Random;

import java.util.ArrayList;

public class Graph {

    private Parser parse = new Parser();
    ArrayList<Noeud> sommets = new ArrayList<>();
    String nom;

    public ArrayList<Noeud> getSommets() {
        return sommets;
    }

    public Graph(String nomFichier, String nomGraph){

        nom = nomGraph;

        parse.remplirTabNoeuds(nomFichier);

        for(int i = 0; i < parse.getCoord().size(); i++){
            int indice = parse.getCoord().get(i).get(0);
            int x = parse.getCoord().get(i).get(1);
            int y = parse.getCoord().get(i).get(2);
            int q = parse.getCoord().get(i).get(3);
            sommets.add(new Noeud(indice, x, y, q));
        }

    }

    public Solution getSolutionInitiale(){
        Solution solInitiale = new Solution();

        int j = 0;
        while (!this.allNodesVisited()) {
            j++;
            Camion camion = new Camion(j, this.getSommets().get(0));
            while(this.noeudsRestant(camion).size() != 0){
                ArrayList<Noeud> noeudRestant = this.noeudsRestant(camion);
                camion.addNoeud(noeudRestant.get(0));
                sommets.get(noeudRestant.get(0).getId()).setEtatVisite();
            }
            camion.addNoeud(sommets.get(0));
            solInitiale.getSolution().add(camion.getItineraire());
        }
        for (int i = 1; i < sommets.size(); i++){
            sommets.get(i).setEtatNonVisite();
        }
        return solInitiale;
    }

    public boolean allNodesVisited(){
        for (int i = 1; i < sommets.size(); i++){
            if(!sommets.get(i).getEtatVisite()){
                return false;
            }
        }
        return true;
    }

    public ArrayList<Noeud> noeudsRestant(Camion camion){
        ArrayList<Noeud> noeudsPossible = new ArrayList<>();
        for(int i = 1; i < sommets.size(); i++){
            if(!(sommets.get(i).getEtatVisite()) && sommets.get(i).getQuantite() <= camion.getCapacite()){
                noeudsPossible.add(sommets.get(i));
            }
        }
        return noeudsPossible;
    }


    @Override
    public String toString(){
        String graph = "-------------------------\n" + nom + ":\n";
        for(int i = 0; i<sommets.size(); i++) {
            graph += "\t" + sommets.get(i) + "\n";
        }
        return graph + "-------------------------";
    }

    public Solution recuitSimule(int n1, int n2){
        Random random = new Random();
        Solution x = this.getSolutionInitiale();
        double t = 100;
        Solution xmin = x;
        for(int i = 0; i < n1; i++){
            for (int j = 0 ; j < n2; j++){
                int n = 20;
                int rand1 = random.nextInt(n);
                Solution y = x.getVoisinage(n).get(rand1);
                double deltaf = y.getCoutTotal() - x.getCoutTotal();
                if(deltaf <= 0){
                    x = y;
                    if(x.getCoutTotal() < xmin.getCoutTotal()){
                        xmin = x;
                    }

                }
                else{
                    int p = random.nextInt(1);
                    if(p < Math.exp(-(deltaf)/t)){
                        x = y;
                    }
                }
            }
            decroissTemp(t);
        }

        return xmin;
    }

    public static void decroissTemp(double t){
        t *= 0.5;
    }


}
