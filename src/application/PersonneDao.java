package application;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonneDao {

	private Connection connection;

    public PersonneDao() {
        this.connection = Connexion.getConnect();
}
    public List<Personne> listerTout() {
        List<Personne> personnes = new ArrayList<>();
        String sql = "SELECT * FROM Personne";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                int age = Integer.parseInt(resultSet.getString("nom"));
                String prenom = resultSet.getString("prenom");
                String situationFamiliale = resultSet.getString("situationfamiliale");
                String image = resultSet.getString("image");
                Personne personne = new Personne( nom, prenom,id, situationFamiliale, age);
                personnes.add(personne);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personnes;
    }
}