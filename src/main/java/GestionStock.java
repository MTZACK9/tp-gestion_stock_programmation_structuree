import java.util.Scanner;

public class GestionStock {
    private static final int MAX_PRODUITS = 100;
    private static int nbrProduits = 0;

    private static int[] codesProduits = new int[MAX_PRODUITS];
    private static String[] nomsProduits = new String[MAX_PRODUITS];
    private static int[] quantitesProduits = new int[MAX_PRODUITS];
    private static double[] prixProduits = new double[MAX_PRODUITS];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choix;

        do {
            printMenu();
            System.out.println("Entrez votre choix : ");
            choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1:
                    ajouterProduit(sc);
                    break;
                case 2:
                    modifierProduit(sc);
                    break;
                case 3:
                    supprimerProduit(sc);
                    break;
                case 4:
                    afficherProduits();
                    break;
                case 5:
                    rechercheProduit(sc);
                    break;
                case 6:
                    calculerValeurStock();
                    break;
                case 7:
                    System.out.println("Au revoire !");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réssayer.");
            }
        } while (choix != 7);
        sc.close();
    }

    private static void printMenu()
    {
        System.out.println("Gestion de Stock");
        System.out.println("1. Ajouter un produit");
        System.out.println("2. Modifier un produit");
        System.out.println("3. Supprimer un produit");
        System.out.println("4. Afficher la liste des produits");
        System.out.println("5. Rechercher un produit par nom");
        System.out.println("6. Calculer la valeur totale du stock");
        System.out.println("7. Quitter");
    }

    private static void ajouterProduit(Scanner sc)
    {
        if (nbrProduits >= MAX_PRODUITS)
        {
            System.out.println("Le stock est plein");
            return;
        }
        System.out.print("Entrer le code du produit : ");
        int code = sc.nextInt();
        sc.nextLine();
        System.out.print("Entrer le nom du produit : ");
        String nom = sc.nextLine();
        System.out.print("Entrer le quantite : ");
        int quantite = sc.nextInt();
        System.out.print("Entrer le prix : ");
        double prix = sc.nextDouble();

        codesProduits[nbrProduits] = code;
        nomsProduits[nbrProduits] = nom;
        quantitesProduits[nbrProduits] = quantite;
        prixProduits[nbrProduits] = prix;

        nbrProduits++;
        System.out.println("Produit ajouté avec succès.");
    }

    public static void modifierProduit(Scanner sc)
    {
        System.out.println("Entrer le code du produit : ");
        int code = sc.nextInt();
        sc.nextLine();

        int index = trouverIndexProduit(code);
        if(index == -1)
        {
            System.out.println("Produit n'existe pas !");
            return;
        }
        System.out.print("Entrer le nom du produit : ");
        String nom = sc.nextLine();

        System.out.print("Entrer le quantite : ");
        int quantite = sc.nextInt();

        System.out.print("Entrer le prix : ");
        double prix = sc.nextDouble();

        nomsProduits[index] = nom;
        quantitesProduits[index] = quantite;
        prixProduits[index] = prix;

        System.out.println("Produit modifié avec succès !");
    }

    public static void supprimerProduit(Scanner sc)
    {
        System.out.println("Entrer le code du produit : ");
        int code = sc.nextInt();
        sc.nextLine();

        int index = trouverIndexProduit(code);
        if(index == -1)
        {
            System.out.println("Produit n'existe pas !");
            return;
        }

        for (int i = 0; i < nbrProduits; i++)
        {
            codesProduits[i] = codesProduits[i + 1];
            nomsProduits[i] = nomsProduits[i + 1];
            quantitesProduits[i] = quantitesProduits[i + 1];
            prixProduits[i] = prixProduits[i + 1];
        }
        nbrProduits--;
        System.out.println("Produit supprimé avec succès !");
    }

    public static void afficherProduits()
    {
        if (nbrProduits == 0)
        {
            System.out.println("Aucun produit dans le stock.");
            return;
        }

        System.out.println("Liste des produits :");
        for (int i = 0; i < nbrProduits; i++) {
            System.out.print("Code : " + codesProduits[i] + "\t" + "Nom : " + nomsProduits[i] + "\t"+ "Quantite : "+ quantitesProduits[i] + "\t" + "Prix : " + prixProduits[i] + "\t");
            System.out.println();
        }
    }

    public static void rechercheProduit(Scanner sc)
    {
        System.out.println("Enter le nom du produit : ");
        String nom = sc.nextLine();

        boolean trouve = false;
        for (int i = 0; i < nbrProduits; i++) {
            if(nomsProduits[i].equals(nom))
            {
                System.out.println("Produit trouvé :");
                System.out.print("Code : " + codesProduits[i] + "\t" + "Nom : " + nomsProduits[i] + "\t"+ "Quantite : "+ quantitesProduits[i] + "\t" + "Prix : " + prixProduits[i] + "\t");
                System.out.println();
                trouve = true;
            }
        }
        if (!trouve) {
            System.out.println("Produit non trouvé.");
        }
    }

    public static void calculerValeurStock()
    {
        double valeurTotale = 0;
        for (int i = 0; i < nbrProduits; i++) {
            valeurTotale += quantitesProduits[i] * prixProduits[i];
        }
        System.out.println("La valeur totale du stock est : " + valeurTotale);
    }

    private static int trouverIndexProduit(int code)
    {
        for(int i = 0; i < nbrProduits; i++)
        {
            if(codesProduits[i] == code)
                return i;
        }
        return -1;
    }
}
