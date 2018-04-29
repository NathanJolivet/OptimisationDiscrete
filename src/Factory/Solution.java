package Factory;

import java.util.ArrayList;
import java.util.Random;

public class Solution {

    private ArrayList<ArrayList<Noeud>> solution = new ArrayList<>();

    public Solution(){
    }

    public ArrayList<ArrayList<Noeud>> getSolution() {
        return solution;
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

    public boolean refaire(int tailleSol, int lgChange, int iti1){
        boolean refaire = true;
        for(int j=0; j<tailleSol; j++){
            if(refaire && j != iti1 && lgChange < tailleSol){
                refaire = false;
            }
        }
        return refaire;
    }

    //TODO A FAIRE
    public ArrayList<Solution> getVoisinage(int nbVoisinage){
        
        return null;
    }
/*    public ArrayList<Solution> getVoisinage(int nbVoisinage){
        Random r = new Random();
        int tailleSol = this.solution.size();           // le nombre d'itinéraires
        ArrayList<Solution> result = new ArrayList<>();

        for(int i = 0; i < nbVoisinage; i++){       // on répète nbVoisinage fois les changements

            ArrayList<Integer> capacite;
            Solution solution2;

            boolean capaOk;

            do {
                capacite = new ArrayList<>();
                solution2 = new Solution();

                int iti1 = r.nextInt(tailleSol);                        //quel itinéraire on prend en premier
                int tailleIti1 = this.solution.get(iti1).size();       // taille itinéraire
                int changeStart1 = r.nextInt(tailleIti1 - 1) + 1;         //premier noeud à changer (du premier élément)

                int lgChange;

                do {
                    lgChange = 1 + r.nextInt(3 - 1);
                }     //longueur du changement
                while (refaire(tailleSol, lgChange, iti1));


                lgChange = changeStart1 + lgChange > tailleIti1 ? tailleIti1 - changeStart1 - 1 : lgChange;       // si lgChange dépasse le nombre de solutions alors on le réduit pour que ça passe
                int changeEnd1 = changeStart1 + lgChange;


                int iti2 = r.nextInt(tailleSol);
                while (iti1 == iti2) {                                      //2ème itinéraire du changement différent du premier
                    iti2 = r.nextInt(tailleSol);
                }
                int tailleIti2 = this.solution.get(iti2).size();       // taille itinéraire 2
                int changeStart2 = r.nextInt(tailleIti2 - 1) + 1;         //premier noeud à changer iti 2
                while (changeStart2 + lgChange > tailleIti2) {        //éviter de dépasser la limite
                    changeStart2 = r.nextInt(tailleIti2);
                }
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

                capaOk = true;

                for(int a = 0; a < capacite.size(); a++){
                    //System.out.println(capacite.get(a));
                    if(capacite.get(a) > 100) capaOk = false;
                }
            }
            while(!capaOk);
            result.add(solution2);

            //System.out.println("rrrrr" + solution2);


        }

        return result;
    }

    public void setSolution(ArrayList<ArrayList<Noeud>> solution) {
        this.solution = solution;
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
