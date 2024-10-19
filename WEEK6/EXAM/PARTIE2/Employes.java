/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
/* "Nous avons un nouvel employé : "
"Montant de la prime souhaitée par "
"  A voyagé "
" jours et apporté "
"  A corrigé "
"  A mené à bien "*/
import java.util.Locale;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.lang.Exception;
class Employe{
    private final String  nom;
    private double salaire;
    private int taux;
    private double prime;
    public Employe(String nom,double salaire,int taux){
        this.nom=nom;
        this.salaire=salaire;
        this.prime=0;
        if(taux>100){
            this.taux=100;
        }
        else if(taux<10){
            this.taux=10;
        }
        else{
            this.taux=taux;
        }
    }
    public Employe(String nom,double salaire){
        this.prime=0;
        this.nom=nom;
        this.salaire=salaire;
        this.taux=100;
    }

    public String getNom(){
        return nom;
    }
    public double getSalaire(){
        return salaire;
    }    
    public int getTaux(){
        return taux;
    }
     public double getPrime(){
        return prime;
    }


    
    public void demandePrime(){
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
        boolean demandeReussie = false;
        int count = 0;
        double tmp=0;

        while (!demandeReussie && count < 5) {
            System.out.println("Montant de la prime souhaitée par " + nom + " ?");
            try {
                tmp = scanner.nextDouble();
                if (tmp > 0.02 * revenuAnnuel()) {
                    throw new Exception("Trop cher!");
                }
                prime = tmp;
                demandeReussie = true;    
            } catch (InputMismatchException e) {
                System.out.println("Vous devez introduire un nombre!");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            }
            count++;
        }
        scanner.close();
    }

    //public abstract String toString();
    public  double revenuAnnuel(){
        return salaire*12;
    };

}

class  Manager extends Employe{
    public static final double FACTEUR_GAIN_CLIENT=500;
    public static final double FACTEUR_GAIN_VOYAGE=100;

    private int jourVoyage;
    private int nbClients;
    public Manager(String nom,double salaire,int taux,int jour, int client){
        super(nom, salaire, taux);
        this.jourVoyage=jour;
        this.nbClients=client;
        System.out.println("Nous avons un nouvel employé : "+nom+", c’est un manager.");
    }

    public Manager(String nom,double salaire,int jour, int client){
        super(nom, salaire);
        this.jourVoyage=jour;
        this.nbClients=client;
        System.out.println("Nous avons un nouvel employé : "+nom+", c’est un manager.");

    }

    public  double revenuAnnuel(){

        return (getSalaire()*12*(getTaux()/100.00)+(FACTEUR_GAIN_CLIENT*nbClients)+(FACTEUR_GAIN_VOYAGE*jourVoyage)+getPrime());

    }


    public String toString(){
        if (getPrime()==0){
            return getNom()+" :\n"+"  Taux d'occupation : "+getTaux()+"%. Salaire annuel : "+String.format("%.2f", revenuAnnuel())+" francs.\n"
                        +"  A voyagé "+jourVoyage+" jours et apporté "+nbClients+" nouveaux clients.\n";
        }
        else{
            return getNom()+" :\n"+"  Taux d'occupation : "+getTaux()+"%. Salaire annuel : "+String.format("%.2f", revenuAnnuel())+" francs, Prime : "+String.format("%.2f", getPrime())+".\n"
            +"  A voyagé "+jourVoyage+" jours et apporté "+nbClients+" nouveaux clients.\n";
        }    
    }


}


class Testeur extends Employe{
    private int nbErreur;
    public static final double FACTEUR_GAIN_ERREURS=10;

    public Testeur(String nom,double salaire,int err,int taux){
        super(nom, salaire, taux);
        this.nbErreur=err;
        System.out.println("Nous avons un nouvel employé : "+nom+", c’est un testeur.");

    }

    public Testeur(String nom,double salaire,int err){
        super(nom, salaire);
        this.nbErreur=err;
        System.out.println("Nous avons un nouvel employé : "+nom+", c’est un testeur.");

    }
    public double revenuAnnuel(){
        System.out.println("salaire : "+getSalaire());
        return (getSalaire()*12*(getTaux()/100.00)+(nbErreur*FACTEUR_GAIN_ERREURS)+getPrime());
    }

    public String toString(){
        if(getPrime()==0){
            return getNom()+" :\n"+"  Taux d'occupation : "+getTaux()+"%. Salaire annuel : "+String.format("%.2f", revenuAnnuel())+" francs.\n"
                        +"  A corrigé "+nbErreur+" erreurs.\n";
        }
        else{
            return getNom()+" :\n"+"  Taux d'occupation : "+getTaux()+"%. Salaire annuel : "+String.format("%.2f", revenuAnnuel())+" francs, Prime : "+String.format("%.2f", getPrime())+".\n"
            +"  A corrigé "+nbErreur+" erreurs.\n"; 
        }
    }    

}



class Programmeur extends Employe{
    private int projetsFini;
    public static final double FACTEUR_GAIN_PROJETS=200;

    public Programmeur(String nom,double salaire,int pro,int taux){
        super(nom, salaire, taux);
        this.projetsFini=pro;
        System.out.println("Nous avons un nouvel employé : "+nom+", c’est un programmeur.");

    }
    public Programmeur(String nom,double salaire,int pro){
        super(nom, salaire);
        this.projetsFini=pro;
        System.out.println("Nous avons un nouvel employé : "+nom+", c’est un programmeur.");

    }
    public double revenuAnnuel(){
        double salaire = (getSalaire()*12*(getTaux()/100.00)+(projetsFini*FACTEUR_GAIN_PROJETS)+getPrime());

        return salaire;
    }

    public String toString(){
        if(getPrime()==0){
            return getNom()+" :\n"+"  Taux d'occupation : "+getTaux()+"%. Salaire annuel : "+String.format("%.2f", revenuAnnuel())+" francs.\n"
                        +"  A mené à bien "+projetsFini+" projets.\n";
        }
        else{
            return getNom()+" :\n"+"  Taux d'occupation : "+getTaux()+"%. Salaire annuel : "+String.format("%.2f", revenuAnnuel())+" francs, Prime : "+String.format("%.2f", getPrime())+".\n"
            +"  A mené à bien "+projetsFini+" projets.\n";
        }    
            
    }
}


/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/
class Employes {
    public static void main(String[] args) {

        List<Employe> staff = new ArrayList<Employe>();

        // TEST PARTIE 1:

        System.out.println("Test partie 1 : ");
        staff.add(new Manager("Serge Legrand", 7456, 30, 4 ));
        staff.add(new Programmeur("Paul Lepetit" , 6456, 3, 75 ));
        staff.add(new Testeur("Pierre Lelong", 5456, 124, 50 ));

        System.out.println("Affichage des employés : ");
        for (Employe modele : staff) {
            System.out.println(modele);
        }
        // FIN TEST PARTIE 1
        // TEST PARTIE 2
        System.out.println("Test partie 2 : ");

        staff.get(0).demandePrime();

        System.out.println("Affichage après demande de prime : ");
        System.out.println(staff.get(0));

        // FIN TEST PARTIE 2
    }
}

