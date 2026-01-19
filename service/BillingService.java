package service;

import model.*;
import dao.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BillingService {
    private CustomerDAO customerDAO = new CustomerDAO();
    private MeterReadingDAO meterReadingDAO = new MeterReadingDAO();
    private BillDAO billDAO = new BillDAO();

    public double calculateBill(double consumption, String customerType) {
        double rate;
        switch (customerType.toUpperCase()) {
            case "BUSINESS":
                rate = 10.0;
                break;
            case "GOVERNMENT":
                rate = 3.0;
                break;
            default: // HOUSEHOLD
                rate = 5.0;
        }
        return consumption * rate;
    }

    public void recordMeterReading(String customerId, double currentReading) {
        Customer customer = customerDAO.getCustomerById(customerId);
        if (customer != null) {
            double consumption = currentReading - customer.getPreviousReading();
            if (consumption < 0) {
                throw new IllegalArgumentException("Current reading cannot be less than previous reading");
            }

            MeterReading reading = new MeterReading();
            reading.setCustomerId(customerId);
            reading.setReadingDate(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
            reading.setCurrentReading(currentReading);
            reading.setConsumption(consumption);

            meterReadingDAO.addMeterReading(reading);

            // Update customer's previous reading
            customer.setPreviousReading(currentReading);
            customerDAO.updateCustomer(customer);

            // Generate bill
            generateBill(customer, consumption);
        }
    }

    private void generateBill(Customer customer, double consumption) {
        double totalAmount = calculateBill(consumption, customer.getCustomerType());

        Bill bill = new Bill();
        bill.setCustomerId(customer.getId());
        bill.setBillDate(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        bill.setConsumption(consumption);
        bill.setTotalAmount(totalAmount);
        bill.setStatus("Unpaid");

        billDAO.addBill(bill);

        // Update customer balance
        customer.setBalance(customer.getBalance() + totalAmount);
        customerDAO.updateCustomer(customer);
    }

    public double getOutstandingBalance(String customerId) {
        Customer customer = customerDAO.getCustomerById(customerId);
        return customer != null ? customer.getBalance() : 0.0;
    }
}
