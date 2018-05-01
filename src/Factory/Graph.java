package Factory;

import ComplementRessource.Parser;

import javax.xml.bind.SchemaOutputResolver;
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

    public String getNom() {
        return nom;
    }

    //////////////////////////////////////////////////////SOLUTION INITIALE////////////////////////////////////////////////////////////
    public Solution getSolutionInitiale(){
        Solution solInitiale = new Solution(this);

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

    //////////////////////////////////////////////////////RECUIT SIMULE////////////////////////////////////////////////////////////
    public Solution recuitSimule(int n1, int n2){
        Random random = new Random();
        Solution x = this.getSolutionInitiale();
        double t = 100;
        Solution xmin = x;
        for(int i = 0; i < n1; i++){
            for (int j = 0 ; j < n2; j++){
                ArrayList<Solution> voisins = x.getVoisinage(100);
                Solution y = voisins.get(0);
                for (int a = 1; a < voisins.size(); a++){
                    if(y.getCoutTotal() > voisins.get(a).getCoutTotal()){
                        y = voisins.get(a);
                    }
                }
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
    public Solution algoGenetique(int n){

        ArrayList<Solution> population = new ArrayList<>();
        int nbPopulation = 10000;

        //Remplissage population

        Solution solutionInitiale = this.getSolutionInitiale();

        population.add(solutionInitiale);
        for(int i = 0; i < nbPopulation - 1; i++){
            Solution voisinDuPrecedent;
            do{
                voisinDuPrecedent = population.get(i).getVoisinage(1).get(0);
            }while(population.contains(voisinDuPrecedent));
            population.add(voisinDuPrecedent);
        }

        for(int a = 0; a < n; a++) {
            //REPRODUCTION
            ArrayList<Integer> choixReproduction = new ArrayList<>();
            ArrayList<Solution> popReproduction = new ArrayList<>();

            double coutMax = 0;
            for (int i = 0; i < population.size(); i++) {
                if (population.get(i).getCoutTotal() > coutMax) {
                    coutMax = population.get(i).getCoutTotal();
                }
            }
            for (int i = 0; i < population.size(); i++) {
                double proba = coutMax + 10 - population.get(i).getCoutTotal();
                int nbIndice = (int) proba;
                for (int j = 0; j < nbIndice; j++) {
                    choixReproduction.add(i);
                }
            }

            Random random = new Random();
            for (int i = 0; i < nbPopulation; i++) {
                int choix = random.nextInt(choixReproduction.size());
                int indice = choixReproduction.get(choix);
                Solution solutionReproduction = population.get(indice);
                popReproduction.add(solutionReproduction);

            }

            population.clear();
            population.addAll(popReproduction);

            //CROISEMENT
            int size = population.size();
            for (int i = 0; i < size; i += 2) {
                population.addAll(croisement(population.get(i), population.get(i + 1)));
            }

            //MUTATION
            for (int i = 0; i < population.size(); i++) {

                //1/4 chance pour un élément de muter
                int doMutation = random.nextInt(4);
                if (doMutation == 3) {
                    population.add(mutation(population.get(i)));
                }
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

        ArrayList<Solution> solutionsCroisees = new ArrayList<>();

        Solution newSol1 = new Solution(solution1.getGraph());
        Solution newSol2 = new Solution(solution2.getGraph());
        newSol1.setSolution(solution1.remplissageCopie());
        newSol2.setSolution(solution2.remplissageCopie());

        Random random = new Random();
        int indiceIti1 = random.nextInt(newSol1.getSolution().size());
        int indiceIti2 = random.nextInt(newSol2.getSolution().size());


        ArrayList<Noeud> itineraire_a_Echanger1 = new ArrayList<>();
        itineraire_a_Echanger1.addAll(newSol1.getSolution().get(indiceIti1));
        ArrayList<Noeud> itineraire_a_Echanger2 = new ArrayList<>();
        itineraire_a_Echanger2.addAll(newSol2.getSolution().get(indiceIti2));

        newSol1.getSolution().get(indiceIti1).clear();
        newSol2.getSolution().get(indiceIti2).clear();

        for(int i = 0; i < itineraire_a_Echanger1.size(); i++){
            if(itineraire_a_Echanger2.contains(itineraire_a_Echanger1.get(i))){
                newSol2.getSolution().get(indiceIti2).add(itineraire_a_Echanger1.get(i));
            }
            else{
                newSol1.getSolution().get(indiceIti1).add(itineraire_a_Echanger1.get(i));
            }
        }

        for(int i = 0; i < itineraire_a_Echanger2.size(); i++){
            int t = 0;
            if(itineraire_a_Echanger1.contains(itineraire_a_Echanger2.get(i))){
                newSol1.getSolution().get(indiceIti1).add(t, itineraire_a_Echanger2.get(i));
                t++;
            }
            else{
                newSol2.getSolution().get(indiceIti2).add(itineraire_a_Echanger2.get(i));
            }
        }

        solutionsCroisees.add(newSol1);
        solutionsCroisees.add(newSol2);

        return solutionsCroisees;
    }

    //TODO: Probabilité de 1/4 ou de 1/8 de muter, si mute, echanger 2 noeuds entre 2 itineraires differents d'une solution
    //TODO: ATTENTION, TOUJOURS TESTER SI LES CONTRAINTES SONT RESPECTE
    public static Solution mutation(Solution solution){

        Random random = new Random();
        Solution solutionMute = new Solution(solution.getGraph());
        boolean verif = false;

        while(!verif) {

            solutionMute = new Solution(solution.getGraph());
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
            verif = solutionMute.contraintesVerifie();

        }

        return solutionMute;
    }

    //////////////////////////////////////////////////////fin algorithme genetique////////////////////////////////////////////////////////////

    @Override
    public String toString(){
        String graph = "-------------------------\n" + nom + ":\n";
        for(int i = 0; i<sommets.size(); i++) {
            graph += "\t" + sommets.get(i) + "\n";
        }
        return graph + "-------------------------";
    }

}


