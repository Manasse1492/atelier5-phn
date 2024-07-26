import java.lang.reflect.Modifier;
import java.sql.*;

public class Data {


    public void inserer(String nom,String auteur) throws SQLException {
        Connection connection = null;
        String sql = "INSERT INTO livres (nom, auteur) VALUES (?,?)";
        ConnectionDB con=new ConnectionDB();
                connection=con.connect();
        try (
                PreparedStatement statement = connection.prepareStatement(sql)) {


            statement.setString(1, nom);
            statement.setString(2, auteur);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void listerLivresParLettre(char lettre) throws SQLException {
        ConnectionDB con = new ConnectionDB();
        String sql = "SELECT id, nom, auteur FROM livres WHERE nom LIKE ?";
        try (Connection connection = con.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, lettre + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nom = resultSet.getString("nom");
                    String auteur = resultSet.getString("auteur");

                    System.out.println("ID: " + id);
                    System.out.println("Nom: " + nom);
                    System.out.println("Auteur: " + auteur);

                    System.out.println("-----------");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void supprimer(int id) throws SQLException {
        Connection connection = null;
        ConnectionDB con=new ConnectionDB();
        connection=con.connect();
        String sql = "DELETE FROM livres WHERE id = ?";
        try (
                PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void modifier(int id, String nom, String auteur) throws SQLException {
        ConnectionDB con = new ConnectionDB();
        String sql = "UPDATE livres SET nom = ?, auteur = ? WHERE id = ?";
        try (Connection connection = con.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nom);
            statement.setString(2, auteur);
            statement.setInt(3, id); // Ajout de l'ID comme paramètre
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void afficherTousLesLivres() throws SQLException {
        ConnectionDB con = new ConnectionDB();
        String sql = "SELECT id, nom, auteur FROM livres";
        try (Connection connection = con.connect();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String auteur = resultSet.getString("auteur");


                System.out.println("ID: " + id);
                System.out.println("Nom: " + nom);
                System.out.println("Auteur: " + auteur);
                System.out.println("-----------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void rechercherLivreNom(String nom) throws SQLException {
        ConnectionDB con = new ConnectionDB();
        String sql = "SELECT id, nom, auteur FROM livres WHERE nom = ?";
        try (Connection connection = con.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nom);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    System.out.println("ID: " + resultSet.getInt("id"));
                    System.out.println("Nom: " + resultSet.getString("nom"));
                    System.out.println("Auteur: " + resultSet.getString("auteur"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void afficheDetails(int id) throws SQLException {
        ConnectionDB con = new ConnectionDB();
        String sql = "SELECT id, nom, auteur FROM livres WHERE id= ?";
        try (Connection connection = con.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    System.out.println("ID: " + resultSet.getInt("id"));
                    System.out.println("Nom: " + resultSet.getString("nom"));
                    System.out.println("Auteur: " + resultSet.getString("auteur"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void rechercherLivreAut(String nom) throws SQLException {
        ConnectionDB con = new ConnectionDB();
        String sql = "SELECT id, nom, auteur FROM livres WHERE auteur = ?";
        try (Connection connection = con.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nom);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    System.out.println("ID: " + resultSet.getInt("id"));
                    System.out.println("Nom: " + resultSet.getString("nom"));
                    System.out.println("Auteur: " + resultSet.getString("auteur"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void afficherNombreLivre() throws SQLException {
        ConnectionDB con = new ConnectionDB();
        try (Connection connection = con.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS total FROM livres")) {

            if (resultSet.next()) {
                int count = resultSet.getInt("total");
                System.out.println("Nombre total de livres: " + count);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

