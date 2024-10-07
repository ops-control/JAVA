import java.util.ArrayList;


abstract class Carte{
    private int cout;
    public Carte(double cout){
        this.cout=cout;
    }
    public abstract String toString();
}

class Terrain extends Carte{
    private String couleur;
    public Terrain(String couleur){
        super(10);
        this.couleur=couleur;
    }
    public String toString(){
        return "Un nouveau terrain.";
    }
}

class Creature extends Carte{
    private String nom;
    private int pointVie;
    private int pointDegat;
    public Creature(int cout, String nom, int pv, int pd){
        super(cout);
        this.nom=nom;
        this.pointVie=pv;
        this.pointDegat=pd;
    }
    public String toString(){
        return "Une nouvelle créature.";
    }
    
}

class Sortilege extends Carte{
    private String nom;
    private String explication;
    public Sortilege(int cout, String nom, String exp){
        super(cout);
        this.nom=nom;
        this.explication=exp;
    }
    public String toString(){
        return "Un sortilège de plus.";
    }
    
}

class Jeu{
    private int nbCartes;
    private ArrayList<Carte> tableauCartes;
    public Jeu(int nbCartes){
        this.nbCartes=nbCartes;
        this.tableauCartes= new ArrayList<>(nbCartes);
    }

    public void piocher(Carte carte ){
        tableauCartes.add(carte);
    }

    
}

class Magic {
    public static void main(String[] args) {
        Jeu maMain = new Jeu(10);
        maMain.piocher(new Terrain('b'));
        maMain.piocher(new Creature(6, "Golem", 4, 6));
        maMain.piocher(new Sortilege(1, "Croissance Gigantesque",
                "La créature ciblée gagne +3/+3 jusqu'à la fin du tour"));
        System.out.println("Là, j'ai en stock :");
        maMain.afficher();
        maMain.joue();
    }
}