import java.util.ArrayList;

/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
class Position{
    private double x;
    private double y;
    public Position(double x,double y){
        this.x=x;
        this.y=y;
    }

    public Position(){
        this.x=0.0;
        this.y=0.0;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public String toString(){
        return"("+x+", "+y+")";
    }
}


class Neurone{
    private Position position;
    private double signalInterne;
    private double facteurAttenuation;
    private ArrayList<Neurone> connexions;
    public Neurone(Position pos, double factAtt){
        this.position=pos;
        this.facteurAttenuation=factAtt;
        this.signalInterne=0;
        this.connexions= new ArrayList<>();
    }

    public Position gePosition(){
        return position;
    }

    public int getNnConnexions(){
        return connexions.size();
    }

    public Neurone getConnexion(int index){
        return connexions.get(index);
    }

    public double getAttenuation(){
        return facteurAttenuation;
    }

    public double getSignal(){
        return signalInterne;
    }

    public void connexion(Neurone n){
        connexions.add(n);
    }

    public void recoitStimulus(double stimulus){
        this.signalInterne=stimulus*facteurAttenuation;
        for(Neurone e:connexions){

            e.recoitStimulus(signalInterne);
        }

    }

    public void setSignal(double sig){
        this.signalInterne=sig;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();;
        if(getNnConnexions()==0){
            str.append("Le neurone en position "+position.toString()+" avec attenuation "+facteurAttenuation+" sans connexion\n");
        }
        else{
            str.append("Le neurone en position "+position.toString()+" avec attenuation "+facteurAttenuation+" en connexion avec\n");
            for(Neurone e: connexions){
                str.append("  - un neurone en position "+e.gePosition()+"\n");
            }
        }
        return str.toString();

    }
}

class NeuroneCumulatif extends Neurone{
    public NeuroneCumulatif(Position pos,double att){
        super(pos,att);
    }
    public void recoitStimulus(double stimulus){
        setSignal(getSignal()+stimulus*getAttenuation());
        for(int i=0;i<getNnConnexions();i++){
            getConnexion(i).recoitStimulus(getSignal());
        }
    }
}

class Cerveau{
    private ArrayList<Neurone> cerveau;
    public Cerveau(){
        cerveau=new ArrayList<>();
    }
    public int getNbNeurones(){
        return cerveau.size();
    }
    public Neurone getNeurone(int index){
        return cerveau.get(index);
    }

    public void ajouterNeurone(Position pos, double att){
        cerveau.add(new Neurone(pos, att));
    }

    public void ajouterNeuroneCumulatif(Position pos, double att){
        cerveau.add(new NeuroneCumulatif(pos, att));

    }

    public void stimuler(int index, double stimulus){
        cerveau.get(index).recoitStimulus(stimulus);
    }

    public double sonder(int index){
        return cerveau.get(index).getSignal();
    }

    public void creerConnexions(){
        if(getNbNeurones()<3)
        {
            if (getNbNeurones()==2){
                cerveau.get(0).connexion(cerveau.get(1));
            }        
        }
        else{
            cerveau.get(0).connexion(cerveau.get(1));
            cerveau.get(0).connexion(cerveau.get(2));

            for(int i=0;i<getNbNeurones()-2;i++){
                if(i%2!=0){
                    cerveau.get(i).connexion(cerveau.get(i+1));
                    cerveau.get(i+1).connexion(cerveau.get(i+2));
                }    
            }

        }
    }    
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("\n*----------*\n\n");
        str.append("Le cerveau contient "+getNbNeurones()+" neurone(s)\n");
        for(Neurone e:cerveau){
            str.append(e.toString()+"\n");
        }
        str.append("*----------*\n\n");

        return str.toString();

    }
}


/*******************************************
 * Ne pas modifier apres cette ligne
 * pour pr'eserver les fonctionnalit'es et
 * le jeu de test fourni.
 * Votre programme sera test'e avec d'autres
 * donn'ees.
 *******************************************/
public class SimulateurNeurone {

    public static void main(String[] args) {
        // TEST DE LA PARTIE 1
        System.out.println("Test de la partie 1:");
        System.out.println("--------------------");

        Position position1 = new Position(0, 1);
        Position position2 = new Position(1, 0);
        Position position3 = new Position(1, 1);

        Neurone neuron1 = new Neurone(position1, 0.5);
        Neurone neuron2 = new Neurone(position2, 1.0);
        Neurone neuron3 = new Neurone(position3, 2.0);

        neuron1.connexion(neuron2);
        neuron2.connexion(neuron3);
        neuron1.recoitStimulus(10);

        System.out.println("Signaux : ");
        System.out.println(neuron1.getSignal());
        System.out.println(neuron2.getSignal());
        System.out.println(neuron3.getSignal());

        System.out.println();
        System.out.println("Premiere connexion du neurone 1");
        System.out.println(neuron1.getConnexion(0));


        // FIN TEST DE LA PARTIE 1

        // TEST DE LA PARTIE 2
        System.out.println("Test de la partie 2:");
        System.out.println("--------------------");

        Position position5 = new Position(0, 0);
        NeuroneCumulatif neuron5 = new NeuroneCumulatif(position5, 0.5);
        neuron5.recoitStimulus(10);
        neuron5.recoitStimulus(10);
        System.out.println("Signal du neurone cumulatif  -> " + neuron5.getSignal());

        // FIN TEST DE LA PARTIE 2

        // TEST DE LA PARTIE 3
        System.out.println();
        System.out.println("Test de la partie 3:");
        System.out.println("--------------------");
        Cerveau cerveau = new Cerveau();

        // parametres de construction du neurone:
        // la  position et le facteur d'attenuation
        cerveau.ajouterNeurone(new Position(0,0), 0.5);
        cerveau.ajouterNeurone(new Position(0,1), 0.2);
        cerveau.ajouterNeurone(new Position(1,0), 1.0);

        // parametres de construction du neurone cumulatif:
        // la  position et le facteur d'attenuation
        cerveau.ajouterNeuroneCumulatif(new Position(1,1), 0.8);
        cerveau.creerConnexions();
        cerveau.stimuler(0, 10);

        System.out.println("Signal du 3eme neurone -> " + cerveau.sonder(3));
        System.out.println(cerveau);
        // FIN TEST DE LA PARTIE 3
    }
}