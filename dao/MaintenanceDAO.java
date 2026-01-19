package dao;

import java.sql.*;
import util.DBConnection;

public class MaintenanceDAO {

    public void createRequest(int customerId, String type, String description) {
        String sql = "INSERT INTO maintenance_requests(customer_id, request_type, description, request_date) VALUES (?,?,?,CURDATE())";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, customerId);
            ps.setString(2, type);
            ps.setString(3, description);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
