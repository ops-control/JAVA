import java.util.ArrayList;

class Timbre {

    public static final int ANNEE_COURANTE = 2016;
    public static final int VALEUR_TIMBRE_DEFAUT = 1;
    public static final String PAYS_DEFAUT = "Suisse";
    public static final String CODE_DEFAUT = "lambda";

    public static final int BASE_1_EXEMPLAIRES = 100;
    public static final int BASE_2_EXEMPLAIRES = 1000;
    public static final double PRIX_BASE_1 = 600;
    public static final double PRIX_BASE_2 = 400;
    public static final double PRIX_BASE_3 = 50;

    /*******************************************
     * Completez le programme a partir d'ici.
     *******************************************/
    private String code;
    private int anneeEmission;
    private String pays;
    private double valeurFaciale;
    public Timbre(String code,int an,String pays,double vf){
        this.code=code;
        this.anneeEmission=an;
        this.pays=pays;
        this.valeurFaciale=vf;
    }

    public Timbre(){
        this.code=CODE_DEFAUT;
        this.anneeEmission=ANNEE_COURANTE;
        this.pays=PAYS_DEFAUT;
        this.valeurFaciale=VALEUR_TIMBRE_DEFAUT;
    }



    public double vente(){
        if(anneeEmission<5) return valeurFaciale;
        else return valeurFaciale*age()*2.5;
    }

    public String toString(){
        return "Timbre de code "+this.code+" datant de "+this.anneeEmission+" (provenance "+pays+") ayant pour valeur faciale "+this.valeurFaciale+" francs";
    }
        
    public int age(){
        return ANNEE_COURANTE-this.anneeEmission;
    }

    public String getCode(){
        return code;
    }

    public int getAnnee(){
        return anneeEmission;
    }
        
    public String getPays(){
        return pays;
    }

    public double getValurFaciale(){
        return valeurFaciale;
    }
}

class Rare extends Timbre{
    private int nombreExemplaires;
    public Rare(String code,int an,String pays,double vf,int nbEx){
        super(code,an,pays,vf);
        this.nombreExemplaires=nbEx;
    }
    public Rare(int nbEx){
        super();
        this.nombreExemplaires=nbEx;
    }
    public int getExemplaires(){
        return nombreExemplaires;
    }

    public String toString(){
        return "Timbre de code "+getCode()+" datant de "+getAnnee()+" (provenance "+getPays()+") ayant pour valeur faciale "+getValurFaciale()+" francs\nNombre d'exemplaires -> "+nombreExemplaires;
    }

    public double vente(){
        if (nombreExemplaires<100) return PRIX_BASE_1*(age()/10);
        else if (nombreExemplaires>=100 && nombreExemplaires<1000) return PRIX_BASE_2*(age()/10);
        else return PRIX_BASE_3*(age()/10);
    }
}

class Commemoratif extends Timbre{
    public Commemoratif(String code,int an,String pays,double vf){
        super(code, an, pays, vf);
    }
    public Commemoratif(){
        super();
    }

    public String toString(){
        return "Timbre de code "+getCode()+" datant de "+getAnnee()+" (provenance "+getPays()+") ayant pour valeur faciale "+getValurFaciale()+" francs\nTimbre celebrant un evenement";
    }

    public double vente(){
        return 2*super.vente();
    }

}

	
/*******************************************
 * Ne rien modifier apres cette ligne.
 *******************************************/

class Philatelie {

    public static void main(String[] args) {

        // ordre des parametres: nom, annee d'emission, pays, valeur faciale,
        // nombre d'exemplaires
        Rare t1 = new Rare("Guarana-4574", 1960, "Mexique", 0.2, 98);

        // ordre des parametres: nom, annee d'emission, pays, valeur faciale
        Commemoratif t2 = new Commemoratif("700eme-501", 2002, "Suisse", 1.5);
        Timbre t3 = new Timbre("Setchuan-302", 2004, "Chine", 0.2);

        Rare t4 = new Rare("Yoddle-201", 1916, "Suisse", 0.8, 3);


        ArrayList<Timbre> collection = new ArrayList<Timbre>();

        collection.add(t1);
        collection.add(t2);
        collection.add(t3);
        collection.add(t4);

        for (Timbre timbre : collection) {
            System.out.println(timbre);
            System.out.println("Prix vente : " + timbre.vente() + " francs");
            System.out.println();
        }
    }
}

