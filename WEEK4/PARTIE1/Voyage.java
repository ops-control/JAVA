/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
import java.util.ArrayList;
class OptionVoyage{


    private String nom;
    private double prix;
    public OptionVoyage(String nom,double prix){
        this.nom =nom;
        this.prix=prix;
    }

    public String getNom(){
        return nom;
    }
    public double prix(){
        return prix;
    }
    public String toString(){
        return nom+" -> "+prix+" CHF";
    }    
}

class Sejour extends OptionVoyage{
    private int nbNuits;
    private double prixNuit;
    public Sejour(String nom, double pforf,int nbNuits, double prixNuits){
        super(nom,pforf);
        this.nbNuits=nbNuits;
        this.prixNuit=prixNuits;
    }
    public double prix(){
        return prixNuit*nbNuits+super.prix();
    }

    public String toString(){
        return getNom()+" -> "+prix()+" CHF";
    } 
}


class Transport extends OptionVoyage{
    static final double TARIF_LONG=1500.0;
    static final double TARIF_BASE=200.0;
    private boolean lon;
    public Transport(String nom, double pforf){
        super(nom, pforf);
        this.lon=false;
    }    
    public Transport(String nom, double pforf,boolean lon){
        super(nom, pforf);
        this.lon=lon;
    }
    public double prix(){
        if(lon){
            return TARIF_LONG+super.prix();
        }
        else{
            return TARIF_BASE+super.prix();
        }
    }
    
    public String toString(){
        return getNom()+" -> "+(prix())+" CHF";
    } 
}

class KitVoyage{
    private ArrayList<OptionVoyage> options;
    private String depart;
    private String destination;
    public KitVoyage(String dep, String dest){
        this.depart=dep;
        this.destination=dest;
        options= new ArrayList<>();
    }
    public double prix(){
        double res = 0.0;
        for (OptionVoyage option : options) {
            res = res+option.prix();
        }
        return res;        
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        double res=0.0;
        sb.append("Voyage de "+depart+" à "+destination+", avec pour options :\n");
        for (OptionVoyage option : options) {
            sb.append("   - "+option.toString()+"\n");
            res=res+option.prix();
        }
        sb.append("Prix total : "+res+" CHF");
        return sb.toString();
    }

    public void ajouterOption(OptionVoyage ops){
        options.add(ops);
    }

    public void annuler(){
        options.clear();
    }

    public int getNbOptions(){
        if(options==null){
            return 0;
        }
        else if (options.get(0) == null) {
            return 0;
        }
        return options.size();
    }
    
}
/*******************************************
 * Ne pas modifier apres cette ligne
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/

public class Voyage {
    public static void main(String args[]) {

        // TEST 1
        System.out.println("Test partie 1 : ");
        System.out.println("----------------");
        OptionVoyage option1 = new OptionVoyage("Séjour au camping", 40.0);
        System.out.println(option1.toString());

        OptionVoyage option2 = new OptionVoyage("Visite guidée : London by night" , 50.0);
        System.out.println(option2.toString());
        System.out.println();

        // FIN TEST 1


        // TEST 2
        System.out.println("Test partie 2 : ");
        System.out.println("----------------");

        Transport transp1 = new Transport("Trajet en car ", 50.0);
        System.out.println(transp1.toString());

        Transport transp2 = new Transport("Croisière", 1300.0);
        System.out.println(transp2.toString());

        Sejour sejour1 = new Sejour("Camping les flots bleus", 20.0, 10, 30.0);
        System.out.println(sejour1.toString());
        System.out.println();

        // FIN TEST 2


        // TEST 3
        System.out.println("Test partie 3 : ");
        System.out.println("----------------");

        KitVoyage kit1 = new KitVoyage("Zurich", "Paris");
        kit1.ajouterOption(new Transport("Trajet en train", 50.0));
        kit1.ajouterOption(new Sejour("Hotel 3* : Les amandiers ", 40.0, 5, 100.0));
        System.out.println(kit1.toString());
        System.out.println();
        kit1.annuler();

        KitVoyage kit2 = new KitVoyage("Zurich", "New York");
        kit2.ajouterOption(new Transport("Trajet en avion", 50.0, true));
        kit2.ajouterOption(new Sejour("Hotel 4* : Ambassador Plazza  ", 100.0, 2, 250.0));
        System.out.println(kit2.toString());
        kit2.annuler();

        // FIN TEST 3
    }
}

