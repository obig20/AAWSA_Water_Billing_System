package dao;

import model.Bill;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {

    public void addBill(Bill bill) {
        String sql = "INSERT INTO bills(customer_id, bill_date, consumption, total_amount, status) VALUES (?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
              PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, bill.getCustomerId());
            ps.setString(2, bill.getBillDate());
            ps.setDouble(3, bill.getConsumption());
            ps.setDouble(4, bill.getTotalAmount());
            ps.setString(5, bill.getStatus());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                bill.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Bill> getBillsByCustomer(String customerId) {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT * FROM bills WHERE customer_id = ? ORDER BY bill_date DESC";
        try (Connection con = DBConnection.getConnection();
              PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, customerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bills.add(new Bill(
                    rs.getInt("id"),
                    rs.getString("customer_id"),
                    rs.getString("bill_date"),
                    rs.getDouble("consumption"),
                    rs.getDouble("total_amount"),
                    rs.getString("status")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }

    public List<Bill> getUnpaidBills() {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT * FROM bills WHERE status = 'Unpaid' ORDER BY bill_date DESC";
        try (Connection con = DBConnection.getConnection();
              PreparedStatement ps = con.prepareStatement(sql);
              ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                bills.add(new Bill(
                    rs.getInt("id"),
                    rs.getString("customer_id"),
                    rs.getString("bill_date"),
                    rs.getDouble("consumption"),
                    rs.getDouble("total_amount"),
                    rs.getString("status")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }

    public void updateBillStatus(int billId, String status) {
        String sql = "UPDATE bills SET status = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
              PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, billId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Bill getBillById(int id) {
        String sql = "SELECT * FROM bills WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
              PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Bill(
                    rs.getInt("id"),
                    rs.getString("customer_id"),
                    rs.getString("bill_date"),
                    rs.getDouble("consumption"),
                    rs.getDouble("total_amount"),
                    rs.getString("status")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}