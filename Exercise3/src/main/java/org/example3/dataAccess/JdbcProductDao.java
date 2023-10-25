package main.java.org.example3.dataAccess;

import main.java.org.example3.entities.Product;

public class JdbcProductDao implements ProductDao{
    public void add(Product product){
        System.out.println("added on database with jdbc");
    };
}
