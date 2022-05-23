package ru.pigarev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.pigarev.Application;
import ru.pigarev.beans.Cart;
import ru.pigarev.beans.ProductRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class CommandHandler {

    private Cart cart;
    private ApplicationContext applicationContext = Application.context;

    private List<String> commandList;

    public CommandHandler() {
        commandList = new ArrayList<>();
        commandList.add("new cart");
        commandList.add("show cart");
        commandList.add("addall cart");
        commandList.add("add cart");
        commandList.add("remove cart");
        commandList.add("show all");
        commandList.add("clean cart");
        commandList.add("exit");
    }

    @Autowired
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void handling(String command) {

        String[] stringArr = checkCommand(command);

        if (Objects.isNull(stringArr)) {
            System.out.println("----------------------------------------------------");
            System.out.println("Команда введена некорректно. Попробуйте еще раз");
            System.out.println("Список доступных команд:");
            commandList.forEach(correct -> System.out.println(correct));
            System.out.println("----------------------------------------------------");
        } else {
            processing(Arrays.asList(stringArr));
        }

    }

    private void processing(List<String> command) {

        String comm = command.get(0) + " " + command.get(1);
        if (Objects.isNull(cart)) {
            cart = applicationContext.getBean(Cart.class);
        }

        switch (comm) {
            case "new cart":
                cart = applicationContext.getBean(Cart.class);
                break;
            case "show cart":
                cart.showCart();
                break;
            case "addall cart":
                cart.addAllProducts();
                break;
            case "show all":
                applicationContext.getBean(ProductRepository.class).showProducts();
                break;
            case "clean cart":
                cart.cleanCart();
                break;
            default:
                if (comm.startsWith("add cart")) {
                    cart.addProduct(Long.parseLong(command.get(2)));
                    break;
                }
                if (comm.startsWith("remove cart")) {
                    cart.removeProduct(Long.parseLong(command.get(2)));
                    break;
                }
        }


    }

    private String[] checkCommand(String command) {
        command = command.replaceAll("\\s+", " ").toLowerCase();
        String[] inputCommand = command.split(" ");

        if (inputCommand.length == 2) {
            if (commandList.contains(inputCommand[0] + " " + inputCommand[1])) {
                return inputCommand;
            } else return null;
        }
        if (inputCommand.length == 3) {
            if (commandList.contains(inputCommand[0] + " " + inputCommand[1])) {
                try {
                    Integer.parseInt(inputCommand[2]);
                    return inputCommand;
                } catch (Exception e) {
                    return null;
                }
            } else {
                return null;
            }
        }
        return null;
    }
}
