package dao;

import model.MeterReading;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MeterReadingDAO {

    public void addMeterReading(MeterReading reading) {
        String sql = "INSERT INTO meter_readings(customer_id, reading_date, current_reading, consumption) VALUES (?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, reading.getCustomerId());
            ps.setString(2, reading.getReadingDate());
            ps.setDouble(3, reading.getCurrentReading());
            ps.setDouble(4, reading.getConsumption());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                reading.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<MeterReading> getMeterReadingsByCustomer(String customerId) {
        List<MeterReading> readings = new ArrayList<>();
        String sql = "SELECT * FROM meter_readings WHERE customer_id = ? ORDER BY reading_date DESC";
        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, customerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                readings.add(new MeterReading(
                    rs.getInt("id"),
                    rs.getString("customer_id"),
                    rs.getString("reading_date"),
                    rs.getDouble("current_reading"),
                    rs.getDouble("consumption")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return readings;
    }

    public List<MeterReading> getAllMeterReadings() {
        List<MeterReading> readings = new ArrayList<>();
        String sql = "SELECT * FROM meter_readings ORDER BY reading_date DESC";
        try (Connection con = DBConnection.getConnection();
              PreparedStatement ps = con.prepareStatement(sql);
              ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                readings.add(new MeterReading(
                    rs.getInt("id"),
                    rs.getString("customer_id"),
                    rs.getString("reading_date"),
                    rs.getDouble("current_reading"),
                    rs.getDouble("consumption")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return readings;
    }
}