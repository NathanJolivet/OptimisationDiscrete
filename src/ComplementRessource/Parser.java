package ComplementRessource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Parser {

    public void lire(){
    URL fileUrl = getClass().getClassLoader().getResource("data01.txt");
        if(fileUrl != null) {
        try {
            Files.lines(Paths.get(fileUrl.toURI())).forEach(line -> System.out.println(line));

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
}
