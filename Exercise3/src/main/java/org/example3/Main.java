package main.java.org.example3;


import main.java.org.example3.business.ProductService;
import main.java.org.example3.dataAccess.HibernateProductDao;
import main.java.org.example3.entities.Product;
import main.java.org.example3.logging.DatabaseLogger;
import main.java.org.example3.logging.FileLogger;
import main.java.org.example3.logging.MailLogger;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Product product1=new Product(1,"Iphone Xr",12);

        ProductService productService=new ProductService(new HibernateProductDao(), List.of(new FileLogger(),new MailLogger(),new DatabaseLogger()));

        productService.add(product1);



    }
}