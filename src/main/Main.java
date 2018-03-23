package main;

import complementRessource.Parser;

public class Main {

    public static void main(String[] args) {
        Parser parse = new Parser();
        parse.remplirTabNoeuds();
        System.out.println(parse.getCoord());


    }

}