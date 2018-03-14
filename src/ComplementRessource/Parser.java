package ComplementRessource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Parser {

    //TODO: Splitter les éléments pour de l'arraylist pour les ajouter dans le tableau Coord

    public void remplirTabNoeuds(){
        ArrayList<int[]> Coord = new ArrayList<>();
    URL fileUrl = getClass().getClassLoader().getResource("data01.txt");
        if(fileUrl != null) {
        try {
            ArrayList<String> temp = new ArrayList<>();

            Files.lines(Paths.get(fileUrl.toURI())).forEach(line -> temp.add(line));
            System.out.println(temp);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
}
