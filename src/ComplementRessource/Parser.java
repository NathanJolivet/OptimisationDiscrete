package ComplementRessource;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Parser {

    private ArrayList<ArrayList<Integer>> Coord = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> getCoord() {
        return Coord;
    }

    public void remplirTabNoeuds(String nomFichier){
        Coord.clear();
        ArrayList<String> temp = new ArrayList<>();
        File file = new File("src/Ressources/" + nomFichier);
        try {
            Files.lines(Paths.get(file.toURI())).forEach(temp::add);
            //System.out.println(temp);
            for (int i = 1; i<temp.size(); i++){
                String[] array = temp.get(i).split(";");
                Coord.add(new ArrayList<Integer>());
                Coord.get(i-1).add(0,Integer.parseInt(array[0]));
                Coord.get(i-1).add(1,Integer.parseInt(array[1]));
                Coord.get(i-1).add(2,Integer.parseInt(array[2]));
                Coord.get(i-1).add(3,Integer.parseInt(array[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
