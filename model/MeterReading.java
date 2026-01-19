package model;

public class MeterReading {
    private int id;
    private String customerId;
    private String readingDate;
    private double currentReading;
    private double consumption;

    // Constructors
    public MeterReading() {}

    public MeterReading(int id, String customerId, String readingDate, double currentReading, double consumption) {
        this.id = id;
        this.customerId = customerId;
        this.readingDate = readingDate;
        this.currentReading = currentReading;
        this.consumption = consumption;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getReadingDate() { return readingDate; }
    public void setReadingDate(String readingDate) { this.readingDate = readingDate; }

    public double getCurrentReading() { return currentReading; }
    public void setCurrentReading(double currentReading) { this.currentReading = currentReading; }

    public double getConsumption() { return consumption; }
    public void setConsumption(double consumption) { this.consumption = consumption; }
}