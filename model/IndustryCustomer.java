package model;
public class IndustryCustomer extends Customer {
    public double fixed(){ return 5; }
    public double[] rates(){ return new double[]{4,4.5,5.5,6,6.5}; }
}
