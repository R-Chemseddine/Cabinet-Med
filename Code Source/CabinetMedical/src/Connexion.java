import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connexion {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public static void Connect(Connection con, PreparedStatement pst, ResultSet rs) {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager
                    .getConnection("jdbc:sqlite:sample.db");

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e2) {
                // TODO: handle exception
            }
        }

    }
}