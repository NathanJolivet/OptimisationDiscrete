package Main;

import Factory.Graph;

public class Main {

    public static void main(String[] args) {
        /*Parser parse = new Parser();
        parse.remplirTabNoeuds("data01.txt");
        System.out.println(parse.getCoord()); */

        Graph graph1 = new Graph("data01.txt");
        System.out.println(graph1.getSommets());

        Graph graph2 = new Graph("data02.txt");
        System.out.println(graph2.getSommets());

        System.out.println("data1: " + graph1.getSommets().size() + "\n" + "data2: " + graph2.getSommets().size());

        System.out.println("distance graph1 sommet 0 à 1: " + graph1.getSommets().get(0).getDistanceTo(graph1.getSommets().get(1)));
        System.out.println("distance graph2 sommet 0 à 1: " + graph2.getSommets().get(0).getDistanceTo(graph2.getSommets().get(1)));
        System.out.println("distance graph1 sommet 2 à 3: " + graph1.getSommets().get(2).getDistanceTo(graph1.getSommets().get(3)));

        System.out.println(graph1);
        System.out.println(graph2);



    }

}