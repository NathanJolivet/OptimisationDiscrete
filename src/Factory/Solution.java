package Factory;

import java.util.ArrayList;

public class Solution {

    private int id;
    private ArrayList<Itineraire> solution = new ArrayList<>();

    public Solution(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Itineraire> getSolution() {
        return solution;
    }

    public int getCoutTotal(){
        int cout = 0;
        for(int i = 0; i < solution.size()-1; i++){
            cout += solution.get(i).getCoutTotal();
        }
        return cout;
    }

    public ArrayList<Solution> getVoisinage(){
        return null;
    }



}
