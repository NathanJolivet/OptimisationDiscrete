package Main;

import Factory.*;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        //GRAPHES  (décommentez un graphe pour qu'il s'exécute)

        ///////////////////////////////////////////////////////////////////////  GRAPHE 1  ///////////////////////////////////////////////////////////////////////
        Graph graph1 = new Graph("data01.txt", "graph1");
        System.out.println(graph1);
        Solution solInitiale1 = graph1.getSolutionInitiale();
        System.out.println(solInitiale1);
        System.out.println("Coût de la solution initiale:   " + solInitiale1.getCoutTotal() + "\n\n");
        System.out.println("-------------------------------RECUIT SIMULE GRAPHE 1-------------------------------");

        Solution solRecuit = graph1.recuitSimule(100, 100);         //(n1, n2)
        System.out.println(solRecuit);
        System.out.println("Coût de la solution du recuit:   " + solRecuit.getCoutTotal() + "\n");

        System.out.println("-------------------------------FIN RECUIT SIMULE GRAPHE 1------------------------------- \n");


        System.out.println("-------------------------------ALGORITHME GENETIQUE GRAPHE 1-------------------------------");

        Solution solAlgogen = graph1.algoGenetique(1000);        //nbGenerations
        System.out.println(solAlgogen);
        System.out.println(solAlgogen.getCoutTotal());

        System.out.println("-------------------------------FIN ALGORITHME GENETIQUE GRAPHE 1------------------------------- \n");




/*
        ///////////////////////////////////////////////////////////////////////  GRAPHE 2  ///////////////////////////////////////////////////////////////////////
        Graph graph2 = new Graph("data02.txt", "graph2");
        System.out.println(graph2);
        Solution solInitiale2 = graph2.getSolutionInitiale();
        System.out.println(solInitiale2);
        System.out.println("Coût de la solution initiale:   " + solInitiale2.getCoutTotal() + "\n\n");
        System.out.println("-------------------------------RECUIT SIMULE GRAPHE 2-------------------------------");

        Solution solRecuit2 = graph2.recuitSimule(100, 100);         //(n1, n2)
        System.out.println(solRecuit2);
        System.out.println("Coût de la solution du recuit:   " + solRecuit2.getCoutTotal() + "\n");

        System.out.println("-------------------------------FIN RECUIT SIMULE GRAPHE 2------------------------------- \n");


        System.out.println("-------------------------------ALGORITHME GENETIQUE GRAPHE 2-------------------------------");

        Solution solAlgogen2 = graph2.algoGenetique(1000);        //nbGenerations
        System.out.println(solAlgogen2);
        System.out.println(solAlgogen2.getCoutTotal());

        System.out.println("-------------------------------FIN ALGORITHME GENETIQUE GRAPHE 2------------------------------- \n");
*/


/*
        ///////////////////////////////////////////////////////////////////////  GRAPHE 3  ///////////////////////////////////////////////////////////////////////
        Graph graph3 = new Graph("data03.txt", "graph3");
        System.out.println(graph3);
        Solution solInitiale3 = graph3.getSolutionInitiale();
        System.out.println(solInitiale3);
        System.out.println("Coût de la solution initiale:   " + solInitiale3.getCoutTotal() + "\n\n");
        System.out.println("-------------------------------RECUIT SIMULE GRAPHE 3-------------------------------");

        Solution solRecuit3 = graph3.recuitSimule(100, 100);         //(n1, n2)
        System.out.println(solRecuit3);
        System.out.println("Coût de la solution du recuit:   " + solRecuit3.getCoutTotal() + "\n");

        System.out.println("-------------------------------FIN RECUIT SIMULE GRAPHE 3------------------------------- \n");


        System.out.println("-------------------------------ALGORITHME GENETIQUE GRAPHE 3-------------------------------");

        Solution solAlgogen3 = graph3.algoGenetique(1000);        //nbGenerations
        System.out.println(solAlgogen3);
        System.out.println(solAlgogen3.getCoutTotal());

        System.out.println("-------------------------------FIN ALGORITHME GENETIQUE GRAPHE 3------------------------------- \n");
*/


/*
        ///////////////////////////////////////////////////////////////////////  GRAPHE 4  ///////////////////////////////////////////////////////////////////////
        Graph graph4 = new Graph("data04.txt", "graph4");
        System.out.println(graph4);
        Solution solInitiale4 = graph4.getSolutionInitiale();
        System.out.println(solInitiale4);
        System.out.println("Coût de la solution initiale:   " + solInitiale4.getCoutTotal() + "\n\n");
        System.out.println("-------------------------------RECUIT SIMULE GRAPHE 4-------------------------------");

        Solution solRecuit4 = graph4.recuitSimule(100, 100);         //(n1, n2)
        System.out.println(solRecuit4);
        System.out.println("Coût de la solution du recuit:   " + solRecuit4.getCoutTotal() + "\n");

        System.out.println("-------------------------------FIN RECUIT SIMULE GRAPHE 4------------------------------- \n");


        System.out.println("-------------------------------ALGORITHME GENETIQUE GRAPHE 4-------------------------------");

        Solution solAlgogen4 = graph4.algoGenetique(1000);        //nbGenerations
        System.out.println(solAlgogen4);
        System.out.println(solAlgogen4.getCoutTotal());

        System.out.println("-------------------------------FIN ALGORITHME GENETIQUE GRAPHE 4------------------------------- \n");
*/


/*
        ///////////////////////////////////////////////////////////////////////  GRAPHE 5  ///////////////////////////////////////////////////////////////////////
        Graph graph5 = new Graph("data05.txt", "graph5");
        System.out.println(graph5);
        Solution solInitiale5 = graph5.getSolutionInitiale();
        System.out.println(solInitiale5);
        System.out.println("Coût de la solution initiale:   " + solInitiale5.getCoutTotal() + "\n\n");
        System.out.println("-------------------------------RECUIT SIMULE GRAPHE 5-------------------------------");

        Solution solRecuit5 = graph5.recuitSimule(100, 100);         //(n1, n2)
        System.out.println(solRecuit5);
        System.out.println("Coût de la solution du recuit:   " + solRecuit5.getCoutTotal() + "\n");

        System.out.println("-------------------------------FIN RECUIT SIMULE GRAPHE 5------------------------------- \n");


        System.out.println("-------------------------------ALGORITHME GENETIQUE GRAPHE 5-------------------------------");

        Solution solAlgogen5 = graph5.algoGenetique(1000);        //nbGenerations
        System.out.println(solAlgogen5);
        System.out.println(solAlgogen5.getCoutTotal());

        System.out.println("-------------------------------FIN ALGORITHME GENETIQUE GRAPHE 5------------------------------- \n");
*/

    }

}