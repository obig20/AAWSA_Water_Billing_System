package model;

public class Customer {
    private String id;
    private String customerCode;
    private String name;
    private String address;
    private String meterNumber;
    private String customerType;
    private double previousReading;
    private double balance;

    // Constructors
    public Customer() {}

    public Customer(String id, String customerCode, String name, String address, String meterNumber, String customerType, double previousReading, double balance) {
        this.id = id;
        this.customerCode = customerCode;
        this.name = name;
        this.address = address;
        this.meterNumber = meterNumber;
        this.customerType = customerType;
        this.previousReading = previousReading;
        this.balance = balance;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCustomerCode() { return customerCode; }
    public void setCustomerCode(String customerCode) { this.customerCode = customerCode; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getMeterNumber() { return meterNumber; }
    public void setMeterNumber(String meterNumber) { this.meterNumber = meterNumber; }

    public String getCustomerType() { return customerType; }
    public void setCustomerType(String customerType) { this.customerType = customerType; }

    public double getPreviousReading() { return previousReading; }
    public void setPreviousReading(double previousReading) { this.previousReading = previousReading; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    // Tariff based on type
    public double getTariff() {
        switch (customerType.toUpperCase()) {
            case "HOUSEHOLD": return 5.0;
            case "BUSINESS": return 10.0;
            case "GOVERNMENT": return 3.0;
            default: return 5.0;
        }
    }

    // Generate customer code
    public static String generateCustomerCode(String type, int number) {
        String prefix = "";
        switch (type.toUpperCase()) {
            case "HOUSEHOLD": prefix = "HH"; break;
            case "BUSINESS": prefix = "BS"; break;
            case "GOVERNMENT": prefix = "GOV"; break;
        }
        return prefix + "-" + String.format("%05d", number);
    }
}
