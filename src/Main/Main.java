package Main;

import Factory.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Graph graph1 = new Graph("data01.txt", "graph1");
        System.out.println(graph1.getSommets());

        /*Graph graph2 = new Graph("data02.txt");
        System.out.println(graph2.getSommets());

        System.out.println("data1: " + graph1.getSommets().size() + "\n" + "data2: " + graph2.getSommets().size());

        System.out.println("distance graph1 sommet 0 à 1: " + graph1.getSommets().get(0).getDistanceTo(graph1.getSommets().get(1)));
        System.out.println("distance graph2 sommet 0 à 1: " + graph2.getSommets().get(0).getDistanceTo(graph2.getSommets().get(1)));
        System.out.println("distance graph1 sommet 2 à 3: " + graph1.getSommets().get(2).getDistanceTo(graph1.getSommets().get(3)));

        System.out.println(graph1);
        System.out.println(graph2);

*/


        Solution solInitiale1 = graph1.getSolutionInitiale();
        System.out.println(solInitiale1);
        System.out.println(solInitiale1.getCoutTotal() + "\n\n");
        Solution s = graph1.recuitSimule(10,10);
        System.out.println(s);
        System.out.println(s.getCoutTotal());
        /*
        ArrayList<Solution> voisins = solInitiale1.getVoisinage(3);
        System.out.println(voisins);
        */





       /* Solution solInitiale2 = graph2.getSolutionInitiale();
        System.out.println(solInitiale2);
        System.out.println(solInitiale2.getCoutTotal());*/

        /* //TODO TEST DES AUTRES GRAPH (fonctionne)
        Graph graph3 = new Graph("data03.txt");
        Graph graph4 = new Graph("data04.txt");
        Graph graph5 = new Graph("data05.txt");


        System.out.println(graph3);
        System.out.println(graph3.getSolutionInitiale());
        System.out.println(graph3.getSolutionInitiale().getCoutTotal());
        System.out.println(graph4);
        System.out.println(graph3.getSolutionInitiale());
        System.out.println(graph3.getSolutionInitiale().getCoutTotal());
        System.out.println(graph5);
        System.out.println(graph3.getSolutionInitiale());
        System.out.println(graph3.getSolutionInitiale().getCoutTotal());
*/





    }

}