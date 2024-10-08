/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
import java.util.ArrayList;

class Vehicule{
    private String nom;
    private double vitesseMax;
    private int poids;
    private int niveauCarburant;
    public Vehicule(){
        this.nom="Anonyme";
        this.vitesseMax=130.0;
        this.poids=1000;
        this.niveauCarburant=0;
    }

    public Vehicule(String nom, double vMAX, int poids, int nCarb){
        this.nom=nom;
        this.vitesseMax=vMAX;
        this.poids=poids;
        this.niveauCarburant=nCarb;
    }

    public String toString(){
        return nom+" -> vitesse max = "+vitesseMax+" km/h, poids = "+poids+" kg";
    }

    private double performance(){
        return vitesseMax/poids;
    }

    public boolean meilleur(Vehicule autrVehicule){
        return performance()>autrVehicule.performance(); 
    }

    public String getNom(){
        return nom;
    }

    public double getVitesseMax(){
        return vitesseMax;
    }

    public int getPoids(){
        return poids;
    }

    public int getCarburant(){
        return niveauCarburant;
    }

    public boolean estDeuxRoues(){
        return false;
    }
    public void setCarburant(int car){
        this.niveauCarburant=car;
    }
}

class Voiture extends Vehicule{
    private String categorie;
    public Voiture(String cat){
        super();
        this.categorie=cat;
    }
    public Voiture(String nom, double vmax, int pods, int carb, String cat){
        super(nom, vmax, pods, carb);
        this.categorie=cat;
    }

    public String toString(){
        return super.toString()+", Voiture de "+categorie;
    }

    public String getCategorie(){
        return categorie;
    }
    public  boolean estDeuxRoues(){
        return false;
    }

}

class Moto extends Vehicule{
    private boolean sidecar;
    public Moto(boolean side){
        super();
        this.sidecar=side;
    }
    public Moto(String nom, double vmax, int pods, int carb, boolean side){
        super(nom, vmax, pods, carb);
        this.sidecar=side;
    }

    public Moto(String nom, double vmax, int pods, int carb){
        super(nom, vmax, pods, carb);
        this.sidecar=false;
    }
    public String toString(){
        if(sidecar){
            return super.toString()+", Moto, avec sidecar";
        }
        else{
            return super.toString()+", Moto";
        }
    } 
    public  boolean estDeuxRoues(){
        if(sidecar){
            return false;
        }
        else{
            return true;
        }
    }
   
}

class GrandPrix extends Rallye{
    private ArrayList<Vehicule> collection;
    public GrandPrix(){
        collection=new ArrayList<>();
    }
    public boolean check(){
        for(int i=0;i<collection.size();i++){
            if (collection.get(0).estDeuxRoues()!=collection.get(i).estDeuxRoues())
 {
                return false;
            }
        }
        return true;

    }
    public void ajouter(Vehicule veh){
        collection.add(veh);
    }
    public void run(int tours){
        if(!check()){
            System.out.println("Pas de Grand Prix");
        }
        else{
            for(Vehicule ve:collection){
                ve.setCarburant(ve.getCarburant()-tours);
            }
            if (!finCourse()){
                System.out.println("Elimination de tous les vehicules");
            }
            else{
                System.out.println("Le gagnant du grand prix est :");
                System.out.println(plusPerfo().toString());
            }
        }
    }
    private boolean finCourse(){
        for(Vehicule ve:collection){
            if(ve.getCarburant()>0){
                return true;
            }
        }
        return false;    
    }
    private Vehicule plusPerfo(){
        Vehicule best=null;
        for(Vehicule ve:collection){
            if(best==null||ve.meilleur(best)&&ve.getCarburant()>0){
                best=ve;
            }
        }
        return best;
    }


}
abstract class Rallye{
    public abstract boolean check();
}
/*******************************************
 * Ne pas modifier apres cette ligne
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/
public class Course {

    public static void main(String[] args) {

        // PARTIE 1
        System.out.println("Test partie 1 : ");
        System.out.println("----------------");
        Vehicule v0 = new Vehicule();
        System.out.println(v0);

        // les arguments du constructeurs sont dans l'ordre:
        // le nom, la vitesse, le poids, le carburant
        Vehicule v1 = new Vehicule("Ferrari", 300.0, 800, 30);
        Vehicule v2 = new Vehicule("Renault Clio", 180.0, 1000, 20);
        System.out.println();
        System.out.println(v1);
        System.out.println();
        System.out.println(v2);

        if (v1.meilleur(v2)) {
            System.out.println("Le premier vehicule est meilleur que le second");
        } else {
            System.out.println("Le second vehicule est meilleur que le premier");
        }
        // FIN PARTIE 1

        // PARTIE2
        System.out.println();
        System.out.println("Test partie 2 : ");
        System.out.println("----------------");

        // les trois premiers arguments sont dans l'ordre:
        // le nom, la vitesse, le poids, le carburant
        // le dernier argument indique la presence d'un sidecar
        Moto m1 = new Moto("Honda", 200.0, 250, 15, true);
        Moto m2 = new Moto("Kawasaki", 280.0, 180, 10);
        System.out.println(m1);
        System.out.println();
        System.out.println(m2);
        System.out.println();

        // les trois premiers arguments sont dans l'ordre:
        // le nom, la vitesse, le poids, le carburant
        // le dernier argument indique la categorie
        Voiture vt1 = new Voiture("Lamborghini", 320, 1200, 40, "course");
        Voiture vt2 = new Voiture("BMW", 190, 2000, 35, "tourisme");
        System.out.println(vt1);
        System.out.println();
        System.out.println(vt2);
        System.out.println();
        // FIN PARTIE 2

        // PARTIE 3
        System.out.println();
        System.out.println("Test partie 3 : ");
        System.out.println("----------------");

        GrandPrix gp1 = new GrandPrix();
        gp1.ajouter(v1);
        gp1.ajouter(v2);
        System.out.println(gp1.check());

        GrandPrix gp2 = new GrandPrix();
        gp2.ajouter(vt1);
        gp2.ajouter(vt2);
        gp2.ajouter(m2);
        System.out.println(gp2.check());

        GrandPrix gp3 = new GrandPrix();
        gp3.ajouter(vt1);
        gp3.ajouter(vt2);
        gp3.ajouter(m1);
        System.out.println(gp3.check());

        GrandPrix gp4 = new GrandPrix();
        gp4.ajouter(m1);
        gp4.ajouter(m2);
        System.out.println(gp4.check());
        // FIN PARTIE 3

        // PARTIE 4
        System.out.println();
        System.out.println("Test partie 4 : ");
        System.out.println("----------------");
        GrandPrix gp5 = new GrandPrix();
        gp5.ajouter(vt1);
        gp5.ajouter(vt2);

        System.out.println("Premiere course :");
        gp5.run(11);
        System.out.println();

        System.out.println("Deuxieme  course :");
        gp3.run(40);
        System.out.println();

        System.out.println("Troisieme  course :");
        gp2.run(11);
        // FIN PARTIE 4
    }
}

