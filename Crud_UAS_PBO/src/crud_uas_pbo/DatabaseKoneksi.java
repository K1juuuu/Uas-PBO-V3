package crud_uas_pbo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseKoneksi {
    private static Connection koneksi;

    public static Connection connect() {
        if (koneksi == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/dealer_mobil"; // Sesuaikan nama database
                String user = "root"; // Sesuaikan user
                String password = ""; // Sesuaikan password
                koneksi = DriverManager.getConnection(url, user, password);
                System.out.println("Koneksi Berhasil!");
            } catch (SQLException e) {
                System.out.println("Gagal Koneksi: " + e.getMessage());
            }
        }
        return koneksi;
    }
}
