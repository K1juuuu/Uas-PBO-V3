package crud_uas_pbo;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class PelangganFrame extends JFrame {

    private JTable table;
    private JButton btnCreate, btnRead, btnUpdate, btnDelete;

    public PelangganFrame() {
        setTitle("Data Pelanggan");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set gaya UIManager untuk dialog
        UIManager.put("OptionPane.background", new Color(240, 240, 240));
        UIManager.put("Panel.background", new Color(240, 240, 240));
        UIManager.put("Button.background", new Color(59, 89, 152));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.font", new Font("Tahoma", Font.BOLD, 12));

        // Panel Tombol CRUD
        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnCreate = new JButton("Create");
        btnRead = new JButton("Read");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        panelTombol.add(btnCreate);
        panelTombol.add(btnRead);
        panelTombol.add(btnUpdate);
        panelTombol.add(btnDelete);

        // Tabel untuk menampilkan data
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);

        // Layout
        setLayout(new BorderLayout(10, 10));
        add(panelTombol, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Tambahkan event listeners untuk tombol CRUD
        addListeners();

        setVisible(true);
    }

    private void addListeners() {
        // Button Create
        btnCreate.addActionListener(e -> {
            JTextField txtNama = new JTextField();
            JTextField txtNIK = new JTextField();
            JTextField txtNoTelp = new JTextField();
            JTextField txtAlamat = new JTextField();

            Object[] form = {
                new JLabel("Nama:"), txtNama,
                new JLabel("NIK:"), txtNIK,
                new JLabel("No. Telp:"), txtNoTelp,
                new JLabel("Alamat:"), txtAlamat
            };

            int result = JOptionPane.showConfirmDialog(
                    this, form, "Tambah Data Pelanggan",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon("path/to/your/icon.png") // Tambahkan path ikon yang Anda miliki
            );
            if (result == JOptionPane.OK_OPTION) {
                try {
                    String nama = txtNama.getText();
                    String nik = txtNIK.getText();
                    String noTelp = txtNoTelp.getText();
                    String alamat = txtAlamat.getText();
                    String query = "INSERT INTO data_pelanggan (nama, nik, notelp, alamat) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement stmt = DatabaseKoneksi.connect().prepareStatement(query)) {
                        stmt.setString(1, nama);
                        stmt.setString(2, nik);
                        stmt.setString(3, noTelp);
                        stmt.setString(4, alamat);
                        stmt.executeUpdate();
                        JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!",
                                "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Gagal menambahkan data: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Button Read, Update, Delete dapat ditambahkan modifikasi yang sama untuk dialog-nya
        btnRead.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Reading data...");
        });
    }

    public static void main(String[] args) {
        new PelangganFrame();
    }
}
