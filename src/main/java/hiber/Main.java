package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        Car car1 = new Car("Tesla", 1938);
        Car car2 = new Car("Mercedes", 2948);
        Car car3 = new Car("BMW", 3849);
        Car car4 = new Car("Folkswagen", 4853);

        userService.add(new User("Ivan", "Ivanov", "1@mail.ru", car1));
        userService.add(new User("Alexandr", "Alexandrov", "2@mail.ru", car2));
        userService.add(new User("Petr", "Petrov", "3@mail.ru", car3));
        userService.add(new User("Vasily", "Vasiliev", "4@mail.ru", car4));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id: " + user.getId());
            System.out.println("First Name: " + user.getFirstName());
            System.out.println("Last Name: " + user.getLastName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Car: " + user.getCar());
            System.out.println();
        }
        System.out.println("-------------------");
        System.out.println("Search user by car");
        System.out.println(userService.getUserByModelSeries("BMW", 3849));
        System.out.println("-------------------");
        context.close();
    }
}
