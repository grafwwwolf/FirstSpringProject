package ru.pigarev;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.pigarev.config.ApplicationConfiguration;
import ru.pigarev.service.CommandHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {

    public static ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CommandHandler handler = context.getBean(CommandHandler.class);

        System.out.println("----------------------------------------------------");
        System.out.println("Для просмотра списка наших товаров воспользуйтесь командой - show all");
        System.out.println("Для выхода воспользуйтесь командой - exit");
        System.out.println("Для просмотра корзины воспользуйтесь командой - show cart");
        System.out.println("----------------------------------------------------");

        while (true) {

            try {
                String command = reader.readLine();
                if (command.equals("exit")) {
                    reader.close();
                    return;
                }
                handler.handling(command);
            } catch (IOException e) {
                e.printStackTrace();
                reader.close();
            }
        }
    }
}
