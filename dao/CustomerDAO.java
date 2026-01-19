package dao;

import model.Customer;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO customers(id, customer_code, name, address, meter_number, customer_type, previous_reading, balance) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
              PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, customer.getId());
            ps.setString(2, customer.getCustomerCode());
            ps.setString(3, customer.getName());
            ps.setString(4, customer.getAddress());
            ps.setString(5, customer.getMeterNumber());
            ps.setString(6, customer.getCustomerType());
            ps.setDouble(7, customer.getPreviousReading());
            ps.setDouble(8, customer.getBalance());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Customer getCustomerById(String id) {
        String sql = "SELECT * FROM customers WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
              PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Customer(
                    rs.getString("id"),
                    rs.getString("customer_code"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("meter_number"),
                    rs.getString("customer_type"),
                    rs.getDouble("previous_reading"),
                    rs.getDouble("balance")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Customer getCustomerByCode(String code) {
        String sql = "SELECT * FROM customers WHERE customer_code = ?";
        try (Connection con = DBConnection.getConnection();
              PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Customer(
                    rs.getString("id"),
                    rs.getString("customer_code"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("meter_number"),
                    rs.getString("customer_type"),
                    rs.getDouble("previous_reading"),
                    rs.getDouble("balance")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        try (Connection con = DBConnection.getConnection();
              PreparedStatement ps = con.prepareStatement(sql);
              ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                customers.add(new Customer(
                    rs.getString("id"),
                    rs.getString("customer_code"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("meter_number"),
                    rs.getString("customer_type"),
                    rs.getDouble("previous_reading"),
                    rs.getDouble("balance")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public void updateCustomer(Customer customer) {
        String sql = "UPDATE customers SET customer_code=?, name=?, address=?, meter_number=?, customer_type=?, previous_reading=?, balance=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
              PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, customer.getCustomerCode());
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getMeterNumber());
            ps.setString(5, customer.getCustomerType());
            ps.setDouble(6, customer.getPreviousReading());
            ps.setDouble(7, customer.getBalance());
            ps.setString(8, customer.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomer(String id) {
        String sql = "DELETE FROM customers WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
              PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
