package service;

import model.*;
import dao.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PaymentService {
    private CustomerDAO customerDAO = new CustomerDAO();
    private BillDAO billDAO = new BillDAO();
    private PaymentDAO paymentDAO = new PaymentDAO();

    public boolean processPayment(String customerCode, double amount, String paymentMethod) {
        Customer customer = customerDAO.getCustomerByCode(customerCode);
        if (customer == null) {
            return false;
        }

        // Find unpaid bills
        var bills = billDAO.getBillsByCustomer(customer.getId());
        double totalUnpaid = 0;
        for (Bill bill : bills) {
            if ("Unpaid".equals(bill.getStatus())) {
                totalUnpaid += bill.getTotalAmount();
            }
        }

        if (amount > totalUnpaid) {
            // Partial payment not allowed, must pay full
            return false;
        }

        // Record payment
        Payment payment = new Payment();
        payment.setBillId(bills.get(0).getId()); // Assume paying the oldest bill
        payment.setAmount(amount);
        payment.setPaymentDate(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        payment.setPaymentMethod(paymentMethod);

        paymentDAO.addPayment(payment);

        // Update bill status
        billDAO.updateBillStatus(bills.get(0).getId(), "Paid");

        // Update customer balance
        customer.setBalance(customer.getBalance() - amount);
        customerDAO.updateCustomer(customer);

        return true;
    }

    public String generateReceipt(String customerCode, double amount, String paymentMethod) {
        Customer customer = customerDAO.getCustomerByCode(customerCode);
        if (customer == null) {
            return "Customer not found";
        }

        String receipt = "PAYMENT RECEIPT\n" +
                "================\n" +
                "Customer: " + customer.getName() + "\n" +
                "Code: " + customer.getCustomerCode() + "\n" +
                "Amount Paid: " + amount + " ETB\n" +
                "Method: " + paymentMethod + "\n" +
                "Date: " + LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE) + "\n" +
                "Outstanding Balance: " + customer.getBalance() + " ETB\n" +
                "================\n" +
                "Thank you for your payment!";

        return receipt;
    }

    public double getOutstandingBalance(String customerCode) {
        Customer customer = customerDAO.getCustomerByCode(customerCode);
        return customer != null ? customer.getBalance() : 0.0;
    }
}