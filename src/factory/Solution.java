package factory;

import java.util.ArrayList;

public class Solution {

    private int id;
    private ArrayList<Itineraire> sol;

    public int getId() {
        return id;
    }

    public ArrayList<Itineraire> getSol() {
        return sol;
    }

    public Solution(int id){
        this.id = id;
    }

    public ArrayList<Solution> getVoisinage(){
        return null;
    }


}
