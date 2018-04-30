package Factory;

import sun.java2d.pipe.AAShapePipe;

import java.util.ArrayList;
import java.util.Random;

public class Solution {

    private ArrayList<ArrayList<Noeud>> solution = new ArrayList<>();

    public Solution(){
    }

    public ArrayList<ArrayList<Noeud>> getSolution() {
        return solution;
    }

    public void setSolution(ArrayList<ArrayList<Noeud>> solution) {
        this.solution = solution;
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

    public ArrayList<Solution> getVoisinage(int nbVoisin){
        Random random = new Random();
        ArrayList<Solution> voisins = new ArrayList<>();

        for(int i = 0; i < nbVoisin; i++){

            boolean verif = false;

            while(!verif) {

                Solution voisin = new Solution();
                voisin.setSolution(this.remplissageVoisin());

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
                    nbInvertions1 = random.nextInt(3) + 1;
                    nbInvertions2 = random.nextInt(3) + 1;
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
                    debutEchangeFrom1 = random.nextInt(x) + 1;
                }
                if (y > 0) {
                    debutEchangeFrom2 = random.nextInt(y) + 1;
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




                //On verifie que la solution respecte les contraintes: C <= 100
                verif = true;
                for(int a = 0; a < voisin.getSolution().size(); a++){
                    int capaciteItineraire = 0;
                    for(int b = 0; b < voisin.getSolution().get(a).size() - 1; b++){
                       capaciteItineraire += voisin.getSolution().get(a).get(b).getQuantite();
                    }
                    if(capaciteItineraire > 100){
                        verif = false;
                    }
                }

                //Si les contraintes sont respectés, on ajoute la solution dans la liste des voisins
                if( verif == true){
                    voisins.add(voisin);
                }

            }

        }
        return voisins;
    }

    public ArrayList<ArrayList<Noeud>> remplissageVoisin(){
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


/*
    public boolean refaire(int tailleSol, int lgChange, int iti1, int changeStart){
        boolean refaire = false;
        for(int j=0; j<tailleSol; j++){
            // System.out.println("iti1 && changeStart + lgChange " + iti1 + changeStart + lgChange + " " + tailleSol);

            if(!refaire && j == iti1 && changeStart + lgChange >= tailleSol && changeStart == 0){
                refaire = true;
            }
        }
        return refaire;
    }


    //TODO A FAIRE
    public ArrayList<Solution> getVoisinage(int nbVoisinage){
        Random r = new Random();
        boolean aRefaire = false;
        int tailleSol = this.solution.size();           // le nombre d'itinéraires
        ArrayList<Solution> result = new ArrayList<>();

        for(int i = 0; i < nbVoisinage; i++){       // on répète nbVoisinage fois les changements

            ArrayList<Integer> capacite;
            Solution solution2;
            int changeStart1;
            int changeStart2;
            int lgChange;
            int iti1;
            int tailleIti1;
            boolean capaOk;

            do {
                do {
                    capacite = new ArrayList<>();
                    solution2 = new Solution();

                    iti1 = r.nextInt(tailleSol);                        //quel itinéraire on prend en premier
                    tailleIti1 = this.solution.get(iti1).size();       // taille itinéraire
                    if(tailleIti1 <= 3){
                        changeStart1 = 1;
                    }
                    else{
                        changeStart1 = r.nextInt(tailleIti1 - 3);         //premier noeud à changer (du premier élément)
                    }
                    if(changeStart1 < 1) changeStart1 = 1;

                    lgChange = 1 + r.nextInt(3 - 1);
                }     //longueur du changement
                while (refaire(tailleSol-1, lgChange, iti1, changeStart1));


                lgChange = changeStart1 + lgChange > tailleIti1-1 ? tailleIti1 - changeStart1 - 2 : lgChange;       // si lgChange dépasse le nombre de solutions alors on le réduit pour que ça passe
                if(lgChange <= 0) lgChange = 1;
                int changeEnd1 = changeStart1 + lgChange;




                int iti2 = r.nextInt(tailleSol);
                while (iti1 == iti2) {                                      //2ème itinéraire du changement différent du premier
                    iti2 = r.nextInt(tailleSol);
                }
                int tailleIti2 = this.solution.get(iti2).size();       // taille itinéraire 2

                if(tailleIti2 <= 3){
                    changeStart2 = 1;
                }
                else{
                    changeStart2 = r.nextInt(tailleIti2 - 3);         //premier noeud à changer (du premier élément)
                }

                if(changeStart2 < 1) changeStart2 = 1;

                //changeStart2 = r.nextInt(tailleIti2 - 2) + 1;         //premier noeud à changer iti 2
                if(tailleIti2 - lgChange < 2 || tailleIti2 - changeStart2 < 2 ){
                    aRefaire = true;
                }
                else{
                    aRefaire = false;
                }

                    /*while ( !aRefaire && changeStart2 + lgChange > tailleIti2-1) {        //éviter de dépasser la limite
                    changeStart2 = r.nextInt(tailleIti2);
                }*/
 /*               capaOk = true;
                if(!aRefaire){
                    int changeEnd2 = changeStart2 + lgChange;


                    //on a les 2 éléments à échanger

                    solution2.setSolution(intervertir(iti1, iti2, this.solution, changeStart1, changeStart2, changeEnd1, changeEnd2));

                    for (int m = 0; m < solution2.getSolution().size(); m++) {
                        int capa1 = 0;
                        for (int n = 0; n < solution2.getSolution().get(m).size(); n++) {
                            capa1 += solution2.getSolution().get(m).get(n).getQuantite();
                            capacite.add(capa1);
                        }
                    }



                    for(int a = 0; a < capacite.size(); a++){
                        //System.out.println(capacite.get(a));
                        if(capacite.get(a) > 100) capaOk = false;
                    }
                }

            }
            while( aRefaire || !capaOk);
            result.add(solution2);

            System.out.println("rrrrr" + solution2);


        }

        return result;
    }


    public ArrayList<ArrayList<Noeud>> intervertir(int iti1, int iti2, ArrayList<ArrayList<Noeud>> solution, int changeStart1, int changeStart2, int changeEnd1, int changeEnd2){
        ArrayList<Noeud> intermediaire = new ArrayList<>();
        ArrayList<ArrayList<Noeud>> solutionIntermediaire = new ArrayList<>();
        solutionIntermediaire = solution;

        ArrayList<Noeud> changements1 = new ArrayList<>();

        intermediaire = solution.get(iti1);


        for(int i = 0; i<intermediaire.size(); i++){
            if(i >= changeStart1 && i < changeEnd1){
                changements1.add(intermediaire.get(i));

                intermediaire.set(i, solution.get(iti2).get(changeStart2 + i - changeStart1));      //prend la valeur à échanger
            }
        }
        solutionIntermediaire.set(iti1, intermediaire);

        intermediaire = solution.get(iti2);
        for(int i = 0; i<intermediaire.size(); i++){
            if(i >= changeStart2 && i < changeEnd2){

                intermediaire.set(i, changements1.get(i - changeStart2));      //prend la valeur à échanger
            }
        }
        solutionIntermediaire.set(iti2, intermediaire);

        return solutionIntermediaire;
    }
*/
    @Override
    public String toString(){
        String stringSol = "Solution: \n";
        for(int i = 0; i < solution.size(); i++){
            stringSol += "\t" + solution.get(i) + "\n";
        }
        return stringSol;
    }


}