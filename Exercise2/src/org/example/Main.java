package org.example;

import main.java.org.example.CorporateCustomer;
import main.java.org.example.IndividualCustomer;
import main.java.org.example.Product;

public class Main {
    public static void main(String[] args) {


        Product product1=new Product();

        product1.setDiscount(1);
        System.out.println(product1.getDiscount());

        product1.setName("Delongh1 Coffee Machine");
        product1.setUnitPrice(7500);

        CorporateCustomer corporateCustomer=new CorporateCustomer();
        corporateCustomer.setId(1);
        System.out.println(corporateCustomer.getId());
        IndividualCustomer individualCustomer=new IndividualCustomer();


    }
}