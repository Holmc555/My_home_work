package Service;

import ClientGetAndSet.Client;
import Connect.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private final Database db;

    public ClientService(Database db) {
        this.db = db;
    }

    public long create(String name) throws SQLException {
        String insertSql = "INSERT INTO client (name) VALUES (?) RETURNING id;";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getLong("id");
            } else {
                throw new SQLException("Creating client failed, no ID obtained.");
            }
        }
    }

    public Client getById(long id) throws SQLException {
        String selectSql = "SELECT id, name FROM client WHERE id = ?;";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(selectSql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Client(rs.getLong("id"), rs.getString("name"));
            } else {
                throw new SQLException("Client with ID " + id + " not found.");
            }
        }
    }

    public void setName(long id, String name) throws SQLException {
        String updateSql = "UPDATE client SET name = ? WHERE id = ?;";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateSql)) {
            pstmt.setString(1, name);
            pstmt.setLong(2, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating client failed, no rows affected.");
            }
        }
    }

    public void deleteById(long id) throws SQLException {
        String deleteSql = "DELETE FROM client WHERE id = ?;";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(deleteSql)) {
            pstmt.setLong(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting client failed, no rows affected.");
            }
        }
    }

    public List<Client> listAll() throws SQLException {
        String selectAllSql = "SELECT id, name FROM client;";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(selectAllSql);
             ResultSet rs = pstmt.executeQuery()) {
            List<Client> clients = new ArrayList<>();
            while (rs.next()) {
                clients.add(new Client(rs.getLong("id"), rs.getString("name")));
            }
            return clients;
        }
    }
}
