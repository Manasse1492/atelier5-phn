//import classes.Biographie;
//import classes.Livre;
//import classes.Roman;
//import classes.ScienceFiction;

import java.io.*;
import java.util.HashMap;

public class Bibliotheque {
    private HashMap<Integer, Livre> livres = new HashMap<>();
    private int idCourant = 1;
    private static final String FICHIER_LIVRES = "livres.txt";

    public Bibliotheque() {
        chargerLivres();
    }

    public void ajouterLivre(Livre livre) {
        livres.put(livre.getId(), livre);
        System.out.println("classes.Livre ajouté: " + livre);
        sauvegarderLivres();
    }

    public void supprimerLivre(int id) {
        if (livres.containsKey(id)) {
            livres.remove(id);
            System.out.println("classes.Livre avec ID " + id + " supprimé.");
            sauvegarderLivres();
        } else {
            System.out.println("classes.Livre avec ID " + id + " non trouvé.");
        }
    }

    public void modifierLivre(int id, String nom, String auteur) {
        if (livres.containsKey(id)) {
            Livre livre = livres.get(id);
            livre.setNom(nom);
            livre.setAuteur(auteur);
            System.out.println("classes.Livre modifié: " + livre);
            sauvegarderLivres();
        } else {
            System.out.println("classes.Livre avec ID " + id + " non trouvé.");
        }
    }

    public void rechercherLivreParNom(String nom) {
        livres.values().stream()
                .filter(livre -> livre.getNom().equalsIgnoreCase(nom))
                .forEach(System.out::println);
    }

    public void listerLivresParLettre(char lettre) {
        livres.values().stream()
                .filter(livre -> livre.getNom().toLowerCase().charAt(0) == Character.toLowerCase(lettre))
                .forEach(System.out::println);
    }

    public void afficherNombreDeLivres() {
        System.out.println("Nombre total de livres: " + livres.size());
    }

    public void afficherLivresParCategorie(String categorie) {
        livres.values().stream()
                .filter(livre -> livre.getClass().getSimpleName().equalsIgnoreCase(categorie))
                .forEach(System.out::println);
    }

    public void afficherDetailsLivre(int id) {
        if (livres.containsKey(id)) {
            System.out.println(livres.get(id));
        } else {
            System.out.println("classes.Livre avec ID " + id + " non trouvé.");
        }
    }

    public void listerTousLesLivres() {
        livres.values().forEach(System.out::println);
    }

    public void rechercherLivresParAuteur(String auteur) {
        livres.values().stream()
                .filter(livre -> livre.getAuteur().equalsIgnoreCase(auteur))
                .forEach(System.out::println);
    }

    public int genererId() {
        return idCourant++;
    }

    private void sauvegarderLivres() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHIER_LIVRES))) {
            for (Livre livre : livres.values()) {
                writer.write(livre.getClass().getSimpleName() + "," + livre.getId() + "," + livre.getNom() + "," + livre.getAuteur());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chargerLivres() {
        File fichier = new File(FICHIER_LIVRES);
        if (!fichier.exists()) {
            try {
                fichier.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(FICHIER_LIVRES))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] parts = ligne.split(",");
                String type = parts[0];
                int id = Integer.parseInt(parts[1]);
                String nom = parts[2];
                String auteur = parts[3];
                Livre livre = null;
                switch (type.toLowerCase()) {
                    case "roman":
                        livre = new Roman(id, nom, auteur);
                        break;
                    case "sciencefiction":
                        livre = new ScienceFiction(id, nom, auteur);
                        break;
                    case "biographie":
                        livre = new Biographie(id, nom, auteur);
                        break;
                }
                if (livre != null) {
                    livres.put(id, livre);
                    idCourant = Math.max(idCourant, id + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
