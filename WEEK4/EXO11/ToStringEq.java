
abstract class Figure{
    private double largeur;
    private double hauteur;
    public  Figure(double largeur,double hauteur){
        this.largeur=largeur;
        this.hauteur= hauteur;
    }
    public double getLargeur(){
        return largeur;
    }
    public double getHauteur(){
        return hauteur;
    }
    public abstract String toString();
    public abstract boolean equals(Object ob);
}

class Rectangle extends Figure{
    public Rectangle(double largeur,double hauteur ){
        super(largeur,hauteur);
    }
    public String toString(){

        return"Rectangle : \n"+"largeur = "+getLargeur()+"\nhauteur = "+getHauteur();
        
    }
    public boolean equals(Object ob){
        if(ob==null){
            return false;
        }
        else if(ob.getClass()!=getClass()){
            return false;
        }
        else{
            Rectangle r = (Rectangle)ob;
            return (getLargeur()==r.getLargeur() && getHauteur()==r.getHauteur());
        }
    }

}

class RectangleColore extends Rectangle{
    private String couleur;
    public RectangleColore(double largeur, double hauteur, String couleur){
        super(largeur,hauteur);
        this.couleur=couleur;
    }
    public String toString(){

        return"Rectangle : \n"+"largeur = "+getLargeur()+"\nhauteur = "+getHauteur()+("\ncouleur = "+couleur);
        
    }
    public boolean equals(Object ob){
        if(ob==null){
            return false;
        }
        else if(ob.getClass()!=getClass()){
            return false;
        }
        else{
            RectangleColore r = (RectangleColore)ob;
            return (getLargeur()==r.getLargeur() && getHauteur()==r.getHauteur()&&couleur.equals(r.couleur));
        }
    }    
    
}



class ToStringEq
{
    public static void main(String[] args)
    {
        System.out.println("Test 1 :");
        Rectangle rect = new Rectangle(12.5, 4.0);
        System.out.println(rect);
        System.out.println();

        System.out.println("Test 2: ");
        // le type de rect1 est RectangleColore
        // l'objet contenu dans rect1 est de type RectangleColore
        RectangleColore rect1 = new RectangleColore(12.5, 4.0, "rouge");
        System.out.println(rect1);
        System.out.println();

        System.out.println("Test 3 :");
        // le type de rect2 est Rectangle
        // l'objet contenu dans rect2 est de type RectangleColore
        Rectangle rect2 = new RectangleColore(25.0/2, 8.0/2, new String("rouge"));
        System.out.println(rect2);
        System.out.println (rect1.equals(rect2)); // 1.
        System.out.println (rect2.equals(rect1)); // 2.
        System.out.println(rect1.equals(null)); // 3.
        System.out.println (rect.equals(rect1)); // 4.
        System.out.println (rect1.equals(rect)); // 5.
    }
}
