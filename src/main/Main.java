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

        System.out.println("data2: " + graph1.getSommets().size() + "\n" + "data1: " + graph.getSommets().size());


    }

}