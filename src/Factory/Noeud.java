package Factory;

public class Noeud {

    private int id;
    private int abscisse;
    private int ordonnee;
    private int quantite;
    private boolean etat; //true = visité || false = non visité


    public Noeud(int i, int x, int y, int q){
        id = i;
        abscisse = x;
        ordonnee = y;
        quantite = q;
        etat = false;
    }

    public int getAbscisse() {
        return abscisse;
    }

    public void setAbscisse(int abscisse) {
        this.abscisse = abscisse;
    }

    public int getOrdonnee() {
        return ordonnee;
    }

    public void setOrdonnee(int ordonnee) {
        this.ordonnee = ordonnee;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getEtat() {
            return etat;
    }

    public void setEtatVisite() {
        if(this.getId() != 0) {
            this.etat = true;
        }
    }

    public void setEtatNonVisite() {
        if(this.getId() != 0) {
            this.etat = false;
        }
    }

    public double getDistanceTo(Noeud nDest){
        return  Math.sqrt((Math.pow(nDest.getAbscisse() - this.getAbscisse(), 2 )) + Math.pow(nDest.getOrdonnee() - this.getOrdonnee(), 2));
    }

    @Override
    public String toString() {
        return "[" + id + ", " + abscisse + ", " + ordonnee + ", " + quantite + "]";
    }
}
