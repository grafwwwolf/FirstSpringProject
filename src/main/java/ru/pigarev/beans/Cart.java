package ru.pigarev.beans;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.pigarev.models.Product;

import java.util.List;
import java.util.Objects;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Cart {

    private ProductRepository productRepository;
    private List<Product> products;

    public Cart(ProductRepository productRepository, List<Product> products) {
        this.productRepository = productRepository;
        this.products = products;
    }

    public void addAllProducts() {
        products.clear();
        products.addAll(productRepository.getAllProducts());
        System.out.println("----------------------------------------------------");
        System.out.println("Весь перечнь товаров добавлен в корзину");
        System.out.println("Для просмотра корзины воспользуйтесь командой - show cart");
        System.out.println("Для очистки корзины воспользуйтесь командой - clean cart");
        System.out.println("или воспользуйтесь командой - new cart");
        System.out.println("----------------------------------------------------");
    }

    public void addProduct(Long id) {
        Product product = productRepository.getProduct(id);
        if (Objects.nonNull(product)) {
            products.add(product);
            System.out.println("----------------------------------------------------");
            System.out.println("Товар с номером: " + id + " добавлен в Вашу корзину");
            System.out.println("Для просмотра воспользуйтесь командой - show cart");
            System.out.println("Для удаления товара из корзины воспользуйтесь командой - remove cart 'id', где 'id' - номер товара");
            System.out.println("----------------------------------------------------");
        } else {
            System.out.println("----------------------------------------------------");
            System.out.println("Товара с таким номером не существует, введите верный номер товара");
            System.out.println("----------------------------------------------------");
        }
    }

    public void removeProduct(Long id) {
        Product product = productRepository.getProduct(id);
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == product.getId()) {
                products.remove(i);
                i--;
            }
        }
        System.out.println("----------------------------------------------------");
        System.out.println("Товар с номером: " + id + " удален из корзины");
        System.out.println("Для просмотра корзины воспользуйтесь командой - show cart");
        System.out.println("----------------------------------------------------");
    }

    public void showCart() {
        if (products.isEmpty()) {
            System.out.println("----------------------------------------------------");
            System.out.println("Корзина пуста");
            System.out.println("Для добавления товара в корзину воспользуйтесь командой - add cart 'id', где 'id' - номер товара");
            System.out.println("или воспользуйтесь командой - addall cart");
            System.out.println("----------------------------------------------------");
        } else {
            System.out.println("----------------------------------------------------");
            System.out.println("Товары в корзине:");
            products.forEach(product -> System.out.println(product));
            System.out.println("----------------------------------------------------");
        }
    }

    public void cleanCart() {
        products.clear();
        System.out.println("----------------------------------------------------");
        System.out.println("Корзина пуста");
        System.out.println("----------------------------------------------------");
    }

    @Override
    public String toString() {
        return "products=" + products;
    }
}
