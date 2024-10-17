/*******************************************
 * Completez le programme a partir d'ici.
 *******************************************/
"Nous avons un nouvel employÃ© : "
"Montant de la prime souhaitÃ©e par "
"  A voyagÃ© "
" jours et apportÃ© "
"  A corrigÃ© "
"  A menÃ© Ã  bien "

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

        System.out.println("Affichage des employÃ©s : ");
        for (Employe modele : staff) {
            System.out.println(modele);
        }
        // FIN TEST PARTIE 1
        // TEST PARTIE 2
        System.out.println("Test partie 2 : ");

        staff.get(0).demandePrime();

        System.out.println("Affichage aprÃ¨s demande de prime : ");
        System.out.println(staff.get(0));

        // FIN TEST PARTIE 2
    }
}

