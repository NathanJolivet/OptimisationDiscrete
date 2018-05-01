package Factory;

import ComplementRessource.Parser;

import java.lang.reflect.Array;
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
    //////////////////////////////////////////////////////SOLUTION INITIALE////////////////////////////////////////////////////////////
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

    //////////////////////////////////////////////////////fin solution initiale////////////////////////////////////////////////////////////


    @Override
    public String toString(){
        String graph = "-------------------------\n" + nom + ":\n";
        for(int i = 0; i<sommets.size(); i++) {
            graph += "\t" + sommets.get(i) + "\n";
        }
        return graph + "-------------------------";
    }
    //////////////////////////////////////////////////////RECUIT SIMULE////////////////////////////////////////////////////////////
    public Solution recuitSimule(int n1, int n2){
        Random random = new Random();
        Solution x = this.getSolutionInitiale();
        double t = 100;
        Solution xmin = x;
        for(int i = 0; i < n1; i++){
            for (int j = 0 ; j < n2; j++){
                int n = 30;
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
        t *= 0.8;
    }
    //////////////////////////////////////////////////////fin recuit simule////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////ALGORITHME GENETIQUE////////////////////////////////////////////////////////////
    public Solution algoGenetique(){

        ArrayList<Solution> population = new ArrayList<>();
        ArrayList<Integer> choixReproduction = new ArrayList<>();
        int nbPopulation = 100;

        //Remplissage population
        Solution solutionInitiale = this.getSolutionInitiale();
        for(int i = 0; i < 20; i++) {
            System.out.println(mutation(solutionInitiale));
        }
        population.add(solutionInitiale);
        for(int i = 0; i < nbPopulation - 1; i++){
            Solution voisinDuPrecedent;
            do{
                voisinDuPrecedent = population.get(i).getVoisinage(1).get(0);
            }while(population.contains(voisinDuPrecedent));
            population.add(voisinDuPrecedent);
        }

        //REPRODUCTION
        ArrayList<Solution> popReproduction = new ArrayList<>();
        //double coutTotalePopulation = 0;
        double coutMax = 0;
        for (int i = 0; i < population.size(); i++){
            //coutTotalePopulation += population.get(i).getCoutTotal();
            if(population.get(i).getCoutTotal() > coutMax){
                coutMax = population.get(i).getCoutTotal();
            }
        }
        for(int i = 0; i < population.size(); i++){
            double proba = coutMax + 10 - population.get(i).getCoutTotal();
            int nbIndice = (int) proba;
            for(int j = 0; j < nbIndice ; j++){
                choixReproduction.add(i);
            }
        }

        Random random = new Random();
        for(int i = 0; i < nbPopulation ; i++){
            int choix = random.nextInt(choixReproduction.size());
            int indice = choixReproduction.get(choix);
            Solution solutionReproduction = population.get(indice);
            popReproduction.add(solutionReproduction);

        }

        population.clear();
        population.addAll(popReproduction);


        //CROISEMENT
        for(int i = 0; i < population.size(); i+=2){
            population.addAll(croisement(population.get(i), population.get(i+1)));
        }

        //MUTATION
        for (int i = 0; i < population.size(); i++){

            //1/8 chance pour un élément de muter
            int doMutation = random.nextInt(8);
            if(doMutation == 7) {
                population.add(mutation(population.get(i)));
            }
        }

        //Choix de la meilleure solution dans notre population
        int indice_best_sol = 0;
        for(int i = 1; i < population.size(); i++){
            if(population.get(i).getCoutTotal() < population.get(indice_best_sol).getCoutTotal()){
                indice_best_sol = i;
            }
        }

        return population.get(indice_best_sol);
    }

    //TODO: ECHANGER QUE 1 NOEUD ?? car avec itineraire si rien en commun => quand on va le reagancer ca va redevenir la solution de depart
    //TODO: ATTENTION, TOUJOURS TESTER SI LES CONTRAINTES SONT RESPECTE
    public static ArrayList<Solution> croisement(Solution solution1, Solution solution2){
        Solution newSol1 = new Solution();
        Solution newSol2 = new Solution();
        newSol1.setSolution(solution1.remplissageCopie());
        newSol2.setSolution(solution2.remplissageCopie());

        Random random = new Random();
        int indiceIti1 = random.nextInt(newSol1.getSolution().size());
        int indiceIti2 = random.nextInt(newSol2.getSolution().size());

        ArrayList<Noeud> itineraire1 = solution1.getSolution().get(indiceIti1);
        ArrayList<Noeud> itineraire2 = solution2.getSolution().get(indiceIti2);
        ArrayList<Noeud> itineraire_a_Echanger = new ArrayList<>();

        itineraire_a_Echanger = itineraire1;
        solution1.getSolution().set(indiceIti1, itineraire2);
        solution2.getSolution().set(indiceIti2, itineraire_a_Echanger);


        return null;
    }

    //TODO: Probabilité de 1/4 ou de 1/8 de muter, si mute, echanger 2 noeuds entre 2 itineraires differents d'une solution
    //TODO: ATTENTION, TOUJOURS TESTER SI LES CONTRAINTES SONT RESPECTE
    public static Solution mutation(Solution solution){

        Random random = new Random();
        Solution solutionMute = new Solution();
        boolean verif = false;

        while(!verif) {

            solutionMute = new Solution();
            solutionMute.setSolution(solution.remplissageCopie());

            // Choix de nos randoms
            int itineraire1 = random.nextInt(solutionMute.getSolution().size());
            int itineraire2 = 0;
            do {
                itineraire2 = random.nextInt(solutionMute.getSolution().size());
            } while (itineraire2 == itineraire1);

            int x = solutionMute.getSolution().get(itineraire1).size() - 3;
            int y = solutionMute.getSolution().get(itineraire2).size() - 3;
            int indiceNoeudEchange1 = 0;
            int indiceNoeudEchange2 = 0;
            if (x == 0) {
                indiceNoeudEchange1 = 1;
            }
            if (y == 0) {
                indiceNoeudEchange2 = 1;
            }
            if (x > 0) {
                indiceNoeudEchange1 = random.nextInt(x+1) + 1;
            }
            if (y > 0) {
                indiceNoeudEchange2 = random.nextInt(y+1) + 1;
            }

            //Echange des noeuds entre les deux itinéraires

            Noeud noeud_a_Echanger = solutionMute.getSolution().get(itineraire1).get(indiceNoeudEchange1);
            solutionMute.getSolution().get(itineraire1).set(indiceNoeudEchange1, solutionMute.getSolution().get(itineraire2).get(indiceNoeudEchange2));
            solutionMute.getSolution().get(itineraire2).set(indiceNoeudEchange2, noeud_a_Echanger);


            //On vérifie que la solution respecte les contraintes: C <= 100
            verif = true;
            for(int i = 0; i < solutionMute.getSolution().size(); i++){
                int capaciteItineraire = 0;
                for(int b = 0; b < solutionMute.getSolution().get(i).size(); b++){
                    capaciteItineraire += solutionMute.getSolution().get(i).get(b).getQuantite();
                }
                if(capaciteItineraire > 100){
                    verif = false;
                }
            }

        }

        return solutionMute;
    }

    //////////////////////////////////////////////////////fin algorithme genetique////////////////////////////////////////////////////////////

}


