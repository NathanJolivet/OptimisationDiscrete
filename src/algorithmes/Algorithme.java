package algorithmes;

import factory.*;

public class Algorithme {

    private AlgoStrategy algo;

    public void setStrategy(AlgoStrategy algo){ this.algo = algo;}

    public Solution execute(Graph graph){ return algo.recherche(graph);}

}
