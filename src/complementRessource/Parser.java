package complementRessource;

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
        URL fileUrl = getClass().getClassLoader().getResource("ressources/" + nomFichier);
        if(fileUrl != null) {
        try {
            Files.lines(Paths.get(fileUrl.toURI())).forEach(line -> temp.add(line));
            //System.out.println(temp);
            for (int i = 1; i<temp.size(); i++){
                String[] array = temp.get(i).split(";");
                Coord.add(new ArrayList<Integer>());
                Coord.get(i-1).add(0,Integer.parseInt(array[0]));
                Coord.get(i-1).add(1,Integer.parseInt(array[1]));
                Coord.get(i-1).add(2,Integer.parseInt(array[2]));
                Coord.get(i-1).add(3,Integer.parseInt(array[3]));
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    }
}
