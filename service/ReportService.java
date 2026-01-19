package service;

import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportService {

    public double getMonthlyRevenue() {
        String sql = "SELECT SUM(total_amount) AS total FROM bills WHERE status='Paid'";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public List<String> getUnpaidBillsReport() {
        List<String> report = new ArrayList<>();
        String sql = "SELECT c.name, c.customer_code, b.total_amount, b.bill_date FROM bills b JOIN customers c ON b.customer_id = c.id WHERE b.status='Unpaid'";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                report.add(rs.getString("name") + " (" + rs.getString("customer_code") + "): " + rs.getDouble("total_amount") + " ETB - " + rs.getString("bill_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return report;
    }

    public double getTotalWaterUsage() {
        String sql = "SELECT SUM(consumption) AS total FROM meter_readings";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public String exportToPDF(String reportType) {
        // Simple text export, in real app use iText or similar
        StringBuilder sb = new StringBuilder();
        sb.append("REPORT: ").append(reportType).append("\n");
        if ("Revenue".equals(reportType)) {
            sb.append("Total Revenue: ").append(getMonthlyRevenue()).append(" ETB\n");
        } else if ("Unpaid".equals(reportType)) {
            sb.append("Unpaid Bills:\n");
            for (String line : getUnpaidBillsReport()) {
                sb.append(line).append("\n");
            }
        } else if ("Usage".equals(reportType)) {
            sb.append("Total Water Usage: ").append(getTotalWaterUsage()).append(" m³\n");
        }
        return sb.toString();
    }

    public String exportToExcel(String reportType) {
        // Simple CSV export
        StringBuilder sb = new StringBuilder();
        sb.append("Report,").append(reportType).append("\n");
        if ("Revenue".equals(reportType)) {
            sb.append("Total Revenue,").append(getMonthlyRevenue()).append("\n");
        } else if ("Unpaid".equals(reportType)) {
            sb.append("Customer,Amount,Date\n");
            for (String line : getUnpaidBillsReport()) {
                sb.append(line.replace(": ", ",").replace(" - ", ",")).append("\n");
            }
        } else if ("Usage".equals(reportType)) {
            sb.append("Total Usage,").append(getTotalWaterUsage()).append("\n");
        }
        return sb.toString();
    }
}
