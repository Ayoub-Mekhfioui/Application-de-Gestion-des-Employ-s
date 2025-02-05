import java.util.Arrays;
import java.util.Scanner;

public class GestionEmployes {
    private static final int MAX_EMPLOYES = 50;
    private static Employe[] employes = new Employe[MAX_EMPLOYES];
    private static int nombreEmployes = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            printMenu();
            choix = scanner.nextInt();
            scanner.nextLine(); // Pour consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    ajouterEmploye(scanner);
                    break;
                case 2:
                    modifierEmploye(scanner);
                    break;
                case 3:
                    supprimerEmploye(scanner);
                    break;
                case 4:
                    afficherEmployes();
                    break;
                case 5:
                    rechercherEmploye(scanner);
                    break;
                case 6:
                    calculerMasseSalariale();
                    break;
                case 7:
                    trierEmployesParSalaire(scanner);
                    break;
                case 8:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix != 8);

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\nMenu Principal:");
        System.out.println("1. Ajouter un employé");
        System.out.println("2. Modifier un employé");
        System.out.println("3. Supprimer un employé");
        System.out.println("4. Afficher la liste des employés");
        System.out.println("5. Rechercher un employé");
        System.out.println("6. Calculer la masse salariale");
        System.out.println("7. Trier les employés par salaire");
        System.out.println("8. Quitter");
        System.out.print("Votre choix : ");
    }

    private static void ajouterEmploye(Scanner scanner) {
        if (nombreEmployes >= MAX_EMPLOYES) {
            System.out.println("Le tableau est plein. Impossible d'ajouter un nouvel employé.");
            return;
        }

        System.out.print("Entrez l'ID de l'employé : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Pour consommer la nouvelle ligne

        System.out.print("Entrez le nom de l'employé : ");
        String nom = scanner.nextLine();

        System.out.print("Entrez le poste de l'employé : ");
        String poste = scanner.nextLine();

        System.out.print("Entrez le salaire de l'employé : ");
        double salaire = scanner.nextDouble();

        Employe employe = new Employe(id, nom, poste, salaire);
        employes[nombreEmployes++] = employe;
        System.out.println("Employé ajouté avec succès !");
    }

    private static void modifierEmploye(Scanner scanner) {
        System.out.print("Entrez l'ID de l'employé à modifier : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Pour consommer la nouvelle ligne

        for (int i = 0; i < nombreEmployes; i++) {
            if (employes[i].getId() == id) {
                System.out.print("Entrez le nouveau nom de l'employé : ");
                String nouveauNom = scanner.nextLine();

                System.out.print("Entrez le nouveau poste de l'employé : ");
                String nouveauPoste = scanner.nextLine();

                System.out.print("Entrez le nouveau salaire de l'employé : ");
                double nouveauSalaire = scanner.nextDouble();

                employes[i].setNom(nouveauNom);
                employes[i].setPoste(nouveauPoste);
                employes[i].setSalaire(nouveauSalaire);
                System.out.println("Employé modifié avec succès !");
                return;
            }
        }

        System.out.println("Aucun employé trouvé avec l'ID spécifié.");
    }

    private static void supprimerEmploye(Scanner scanner) {
        System.out.print("Entrez l'ID de l'employé à supprimer : ");
        int id = scanner.nextInt();

        for (int i = 0; i < nombreEmployes; i++) {
            if (employes[i].getId() == id) {
                for (int j = i; j < nombreEmployes - 1; j++) {
                    employes[j] = employes[j + 1];
                }
                nombreEmployes--;
                System.out.println("Employé supprimé avec succès !");
                return;
            }
        }

        System.out.println("Aucun employé trouvé avec l'ID spécifié.");
    }

    private static void afficherEmployes() {
        if (nombreEmployes == 0) {
            System.out.println("Aucun employé à afficher.");
            return;
        }

        for (int i = 0; i < nombreEmployes; i++) {
            System.out.println(employes[i]);
        }
    }

    private static void rechercherEmploye(Scanner scanner) {
        System.out.print("Entrez le nom ou le poste de l'employé à rechercher : ");
        String critere = scanner.nextLine();

        boolean trouve = false;
        for (int i = 0; i < nombreEmployes; i++) {
            if (employes[i].getNom().equalsIgnoreCase(critere) || employes[i].getPoste().equalsIgnoreCase(critere)) {
                System.out.println(employes[i]);
                trouve = true;
            }
        }

        if (!trouve) {
            System.out.println("Aucun employé trouvé avec le critère spécifié.");
        }
    }

    private static void calculerMasseSalariale() {
        double masseSalariale = 0;
        for (int i = 0; i < nombreEmployes; i++) {
            masseSalariale += employes[i].getSalaire();
        }
        System.out.println("La masse salariale totale est de : " + masseSalariale);
    }

    private static void trierEmployesParSalaire(Scanner scanner) {
        System.out.print("Trier par ordre croissant (true) ou décroissant (false) ? ");
        boolean ordreCroissant = scanner.nextBoolean();

        Employe[] employesTries = Arrays.copyOf(employes, nombreEmployes);
        Arrays.sort(employesTries, (e1, e2) -> ordreCroissant ?
                Employe.compareParSalaire(e1, e2) :
                Employe.compareParSalaire(e2, e1));

        for (Employe employe : employesTries) {
            System.out.println(employe);
        }
    }
}