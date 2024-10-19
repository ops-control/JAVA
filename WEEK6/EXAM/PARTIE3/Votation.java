import java.util.ArrayList;
import java.util.Random;

/*******************************************
 * Completez le programme Ã  partir d'ici.
 *******************************************/
/*"vote Ã©lectronique"
System.out.println("Scrutin annulÃ©, pas de votants");
"RÃ©partition des Ã©lecteurs "
" -> %.1f pour cent des Ã©lecteurs\n"*/
class Postulant{
    private String nom;
    private int nbElecteurs;
    public Postulant(String nom){
        this.nom=nom;
        this.nbElecteurs=0;
    }

    public Postulant(String nom,int nbElecteurs){
        this.nom=nom;
        this.nbElecteurs=nbElecteurs;
    }

    public Postulant(Postulant autre){
        this.nom=autre.nom;
        this.nbElecteurs=autre.nbElecteurs;
    }

    public void elect(){
        nbElecteurs=nbElecteurs+1;
    }

    public void init(){
        nbElecteurs=0;
    }

    public int getVotes(){
        return nbElecteurs;
    }

    public String getNom(){
        return nom;
    }
}

class Scrutin{
    private int nbVotantMax;
    private int dateScrutin;
    public ArrayList<Postulant> postulants;
    private ArrayList<Vote> votes ;
    public Scrutin(ArrayList<Postulant> post, int nb, int date,boolean bol){
        postulants = new ArrayList<Postulant>();
        for(int i =0;i<post.size();i++){
            postulants.add(new Postulant(post.get(i)));
            if(bol){
                postulants.get(i).init();
            }
        }
        this.dateScrutin=date;
        this.nbVotantMax=nb;
        this.votes = new ArrayList<Vote>();

    }

    public Scrutin(ArrayList<Postulant> post, int nb, int date){
        postulants = new ArrayList<Postulant>();
        for(int i =0;i<post.size();i++){
            postulants.add(new Postulant(post.get(i)));
            postulants.get(i).init();
        }
        this.dateScrutin=date;
        this.nbVotantMax=nb;
        this.votes = new ArrayList<Vote>();

    }
    
    public int calculerVotants(){
        int res=0;
        for(Postulant e: postulants){
            res=res+e.getVotes();
        }
        return res;
    }

    public String gagnant(){
        int tmp=0;
        for (int i=0;i<postulants.size()-1;i++){
            if(postulants.get(i).getVotes()<postulants.get(i+1).getVotes()){
                tmp=i+1;
            }
        }
        return postulants.get(tmp).getNom();
    }

    public void resultats(){
        StringBuilder str = new StringBuilder();
        str.append("Taux de participation -> "+String.format("%.1f",(((double)(calculerVotants())/nbVotantMax))*100)+
            " pour cent\nNombre effectif de votants -> "+calculerVotants()+
            "\nLe chef choisi est -> "+gagnant()+"\n\nRépartition des electeurs\n");
        for(Postulant e:postulants){
            str.append(e.getNom()+" -> "+String.format("%.1f", ((double)(e.getVotes())/calculerVotants())*100)+" pour cent des électeurs\n");
        }
        System.out.print(str.toString()+"\n");    
    }

    public void compterVotes(){
        for (Vote e:votes){
            if(!e.estInvalide()){
                for(Postulant el:postulants){
                    if(e.getPostulant().equals(el.getNom())){
                        el.elect(); 
                    }
                }
            }
        }
    }

    public void simuler(double taux, int date){
        int nbVotant=(int)(nbVotantMax*taux);
        int cadNum;
        for (int i =0;i<nbVotant;i++){
            cadNum=Utils.randomInt(postulants.size()-1);
            if(i%3==0){
                votes.add(new BulletinElectronique(postulants.get(cadNum).getNom(), date,dateScrutin));
            }
            else if(i%3==1){
                if (i%2==0){
                    votes.add(new BulletinPapier(postulants.get(cadNum).getNom(), date,dateScrutin,false));
                }
                else{
                    votes.add(new BulletinPapier(postulants.get(cadNum).getNom(), date,dateScrutin,true));

                }
            }    
            else if(i%3==2){
                if (i%2==0){
                    votes.add(new BulletinPapier(postulants.get(cadNum).getNom(), date,dateScrutin,false));
                }
                else{
                    votes.add(new BulletinPapier(postulants.get(cadNum).getNom(), date,dateScrutin,true));

                }            
            }

            System.out.println(votes.get(i).toString());
        }

    }

}

abstract class Vote{
    private String nom;
    private int date;
    private int dateLimite;
    public Vote(String nom,int date , int datelim){
        this.nom=nom;
        this.date=date;
        this.dateLimite=datelim;
    }

    public int getDate(){
        return date;
    }

    
    public String getPostulant(){
        return nom;
    }

    public int getDateLimite(){
        return dateLimite;
    }
    public String toString(){
        if(date<dateLimite){
            
        }
    }
    public abstract boolean estInvalide();
}


class BulletinPapier extends Vote{
    private boolean signe;
    public BulletinPapier(String nom, int date, int datelim, boolean signe){
        super(nom, date, datelim);
        this.signe=signe;
    }
    public boolean estInvalide(){
        return (!signe);
    }
    public boolean estSigne(){
        return signe;
    }
    public String toString(){
        if (estInvalide()){
            return " vote par bulletin papier pour "+getPostulant()+" -> invalide";
        }
        else{
            return " vote par bulletin papier pour "+getPostulant()+" -> valide";
        }
    }

}

interface CheckBulletin{
    boolean checkDate();
}

class BulletinCourrier  extends BulletinPapier implements CheckBulletin{
    
    public BulletinCourrier(String nom, int date, int datelim, boolean signe){
        super(nom,date,datelim,signe);
    }

    public boolean checkDate(){
        return (getDate()>getDateLimite());
    }

    public boolean estInvalide(){
        return checkDate()||!estSigne();
    }  
    
    public String toString(){
        if (estInvalide()){
            return " envoi par courrier d’un vote par bulletin papier pour "+getPostulant()+" -> invalide";
        }
        else{
            return " envoi par courrier d’un vote par bulletin papier pour "+getPostulant()+" -> valide";
        }
    }   

}

class BulletinElectronique extends Vote implements CheckBulletin{

    public BulletinElectronique(String nom, int date, int datelim){
        super(nom, date, datelim);
    }
    public boolean checkDate(){
        return (getDate()>getDateLimite()-2);
    }
    public boolean estInvalide(){
        return checkDate();
    }

    public String toString(){
        if (estInvalide()){
            return " vote electronique pour "+getPostulant()+" -> invalide";
        }
        else{
            return " vote electronique pour "+getPostulant()+" -> valide";
        }
    }   


}
/*******************************************
 * Ne pas modifier les parties fournies
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/

class Utils {

    private static final Random RANDOM = new Random();

    // NE PAS UTILISER CETTE METHODE DANS LES PARTIES A COMPLETER
    public static void setSeed(long seed) {
        RANDOM.setSeed(seed);
    }

    // gÃ©nÃ¨re un entier entre 0 et max (max non compris)
    public static int randomInt(int max) {
        return RANDOM.nextInt(max);
    }
}

/**
 * Classe pour tester la simulation
 */

class Votation {

    public static void main(String args[]) {
        Utils.setSeed(20000);
        // TEST 1
        System.out.println("Test partie I:");
        System.out.println("--------------");

        ArrayList<Postulant> postulants = new ArrayList<Postulant>();
        postulants.add(new Postulant("Tarek Oxlama", 2));
        postulants.add(new Postulant("Nicolai Tarcozi", 3));
        postulants.add(new Postulant("Vlad Imirboutine", 2));
        postulants.add(new Postulant("Angel Anerckjel", 4));

        // 30 -> nombre maximal de votants
        // 15 jour du scrutin
        Scrutin scrutin = new Scrutin(postulants, 30, 15, false);

        scrutin.resultats();

        // FIN TEST 1

        // TEST 2
        System.out.println("Test partie II:");
        System.out.println("---------------");

        scrutin = new Scrutin(postulants, 20, 15);
        // tous les bulletins passent le check de la date
        // les parametres de simuler sont dans l'ordre:
        // le pourcentage de votants et le jour du vote
        scrutin.simuler(0.75, 12);
        scrutin.compterVotes();
        scrutin.resultats();

        scrutin = new Scrutin(postulants, 20, 15);
        // seuls les bulletins papier non courrier passent
        scrutin.simuler(0.75, 15);
        scrutin.compterVotes();
        scrutin.resultats();

        scrutin = new Scrutin(postulants, 20, 15);
        // les bulletins electroniques ne passent pas
        scrutin.simuler(0.75, 15);
        scrutin.compterVotes();
        scrutin.resultats();
        //FIN TEST 2

    }
}
