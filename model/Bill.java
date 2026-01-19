package model;

public class Bill {
    private int id;
    private String customerId;
    private String billDate;
    private double consumption;
    private double totalAmount;
    private String status;

    // Constructors
    public Bill() {}

    public Bill(int id, String customerId, String billDate, double consumption, double totalAmount, String status) {
        this.id = id;
        this.customerId = customerId;
        this.billDate = billDate;
        this.consumption = consumption;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getBillDate() { return billDate; }
    public void setBillDate(String billDate) { this.billDate = billDate; }

    public double getConsumption() { return consumption; }
    public void setConsumption(double consumption) { this.consumption = consumption; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
