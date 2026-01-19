package dao;

import model.Payment;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    public void addPayment(Payment payment) {
        String sql = "INSERT INTO payments(bill_id, amount, payment_date, payment_method) VALUES (?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, payment.getBillId());
            ps.setDouble(2, payment.getAmount());
            ps.setString(3, payment.getPaymentDate());
            ps.setString(4, payment.getPaymentMethod());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                payment.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Payment> getPaymentsByBill(int billId) {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payments WHERE bill_id = ? ORDER BY payment_date DESC";
        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, billId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                payments.add(new Payment(
                    rs.getInt("id"),
                    rs.getInt("bill_id"),
                    rs.getDouble("amount"),
                    rs.getString("payment_date"),
                    rs.getString("payment_method")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payments ORDER BY payment_date DESC";
        try (Connection con = DBConnection.getConnection();
            preparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                payments.add(new Payment(
                    rs.getInt("id"),
                    rs.getInt("bill_id"),
                    rs.getDouble("amount"),
                    rs.getString("payment_date"),
                    rs.getString("payment_method")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }
}