package ru.pigarev.beans;

import org.springframework.stereotype.Component;
import ru.pigarev.models.Product;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {

    private List<Product> productList;

    public ProductRepository() {
        productList = new ArrayList<>();
        productList.add(new Product(1L, "black leather jacket", 3600.00));
        productList.add(new Product(2L, "black leather bandana", 540.00));
        productList.add(new Product(3L, "black jeans", 2200.00));
        productList.add(new Product(4L, "black leather gloves", 1050.00));
        productList.add(new Product(5L, "T-shirt with print \'AC/DC\' ", 700.00));
    }

    public List<Product> getAllProducts() {
        return productList;
    }

    public Product getProduct(Long id) {

        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                return productList.get(i);
            }
        }
        return null;
    }

    public void showProducts() {
        System.out.println("----------------------------------------------------");
        System.out.println("Список наших товаров:");
        productList.forEach(product -> System.out.println(product));
        System.out.println("Для добавления товара в корзину воспользуйтесь командой - add cart 'id', где 'id' - номер товара");
        System.out.println("или воспользуйтесь командой - addall cart");
        System.out.println("----------------------------------------------------");
    }
}
