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


    }

}