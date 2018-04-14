package main;

import complementRessource.Parser;
import factory.Graph;

public class Main {

    public static void main(String[] args) {
        /*Parser parse = new Parser();
        parse.remplirTabNoeuds("data01.txt");
        System.out.println(parse.getCoord()); */

        Graph graph = new Graph("data01.txt");
        System.out.println(graph.getSommets());

        Graph graph1 = new Graph("data02.txt");
        System.out.println(graph1.getSommets());

        System.out.println("data1: " + graph.getSommets().size() + "\n" + "data2: " + graph1.getSommets().size());

        System.out.println(graph.getSommets().get(0).getDistanceTo(graph.getSommets().get(1)));
        System.out.println(graph1.getSommets().get(0).getDistanceTo(graph1.getSommets().get(1)));
        System.out.println(graph.getSommets().get(2).getDistanceTo(graph.getSommets().get(3)));

        System.out.println(graph);
        System.out.println(graph1);



    }

}