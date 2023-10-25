package main.java.org.example3.dataAccess;

import main.java.org.example3.entities.Product;

public class HibernateProductDao implements ProductDao{
    @Override
    public void add(Product product) {

        System.out.println("added with hibarnate on database");

    }
}
