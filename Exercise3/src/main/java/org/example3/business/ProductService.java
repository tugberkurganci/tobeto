package main.java.org.example3.business;

import main.java.org.example3.dataAccess.ProductDao;
import main.java.org.example3.entities.Product;
import main.java.org.example3.logging.Logger;

import java.util.List;

public class ProductService {



    private ProductDao productDao;
    private List<Logger> loggerList;

    public ProductService() {
    }

    public ProductService(ProductDao productDao, List<Logger> loggerList) {
        this.productDao = productDao;
        this.loggerList = loggerList;
    }

    public void add(Product product){

        if (product.getUnitPrice()<10){
            throw  new RuntimeException("product price cant lower than 10");
        }


        productDao.add(product);

        for (Logger logger:loggerList){
            logger.log(product.getName());
        }

    }

    public void isProductExist(){

    }
}
