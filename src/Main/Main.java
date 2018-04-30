package Main;

import Factory.*;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Graph graph1 = new Graph("data01.txt", "graph1");
        System.out.println(graph1);

        //Solution initiale
        Solution solInitiale1 = graph1.getSolutionInitiale();
        System.out.println(solInitiale1);
        System.out.println(" cout de la solution initiale:   " + solInitiale1.getCoutTotal() + "\n\n");

        //System.out.println(solInitiale1.getVoisinage(20) + "\n\n");

        //Test du recuit simulé avec 50*50 itération
        System.out.println("-------------------------------RECUIT SIMULE-------------------------------");
        Solution solRecuit = graph1.recuitSimule(50, 50);
        System.out.println(solRecuit);
        System.out.println("cout de la solution du recuit:   " + solRecuit.getCoutTotal() + "\n");
        System.out.println("-------------------------------FIN RECUIT SIMULE-------------------------------");





/*
      //TODO TEST DES AUTRES GRAPH (fonctionne)
        Graph graph2 = new Graph("data02.txt", "graph2");
        Graph graph3 = new Graph("data03.txt", "graph3");
        Graph graph4 = new Graph("data04.txt", "graph4");
        Graph graph5 = new Graph("data05.txt", "graph5");

        System.out.println(graph2);
        System.out.println(graph2.getSolutionInitiale());
        System.out.println(graph2.getSolutionInitiale().getCoutTotal());

        System.out.println(graph3);
        System.out.println(graph3.getSolutionInitiale());
        System.out.println(graph3.getSolutionInitiale().getCoutTotal());

        System.out.println(graph4);
        System.out.println(graph4.getSolutionInitiale());
        System.out.println(graph4.getSolutionInitiale().getCoutTotal());

        System.out.println(graph5);
        System.out.println(graph5.getSolutionInitiale());
        System.out.println(graph5.getSolutionInitiale().getCoutTotal());
*/





    }

}