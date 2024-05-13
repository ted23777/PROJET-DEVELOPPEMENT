package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {

	private static Connection connect;

    public static Connection getConnect() {
        try {
            String url = "jdbc:mysql://localhost:3306/GestionPersonne";
            String user = "votre_utilisateur";
            String password = "votre_mot_de_passe";
            connect = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connect;
    }

    public static void closeConnect() {
        try {
            if (connect != null) {
                connect.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

