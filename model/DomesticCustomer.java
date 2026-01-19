package model;
public class DomesticCustomer extends Customer {
    public double fixed(){ return 3; }
    public double[] rates(){ return new double[]{3,3.5,4.5,5,5.5}; }
}
